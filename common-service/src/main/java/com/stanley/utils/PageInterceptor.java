package com.stanley.utils;

import com.stanley.common.domain.mybatis.Page;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * 分页拦截器，用于拦截需要进行分页查询的操作，然后对其进行分页处理。
 * 利用拦截器实现Mybatis分页的原理：
 * 要利用JDBC对数据库进行操作就必须要有一个对应的Statement对象，
 * Mybatis在执行Sql语句前就会产生一个包含Sql语句的Statement对象，而且对应的Sql语句
 * 是在Statement之前产生的，所以我们就可以在它生成Statement之前对用来生成Statement的Sql语句下手。
 * 在Mybatis中Statement语句是通过RoutingStatementHandler对象的
 * prepare方法生成的。所以利用拦截器实现Mybatis分页的一个思路就是拦截StatementHandler接口的prepare方法，
 * 然后在拦截器方法中把Sql语句改成对应的分页查询Sql语句，之后再调用
 * StatementHandler对象的prepare方法，即调用invocation.proceed()。
 * 对于分页而言，在拦截器里面我们还需要做的一个操作就是统计满足当前条件的记录一共有多少，
 * 这是通过获取到了原始的Sql语句后，把它改为对应的统计语句再利用Mybatis封装好的参数和设
 * 置参数的功能把Sql语句中的参数进行替换，之后再执行查询记录数的Sql语句进行总记录数的统计。
 *
 * 3.4版本的args需添加Integer.class参数，否则会报错 added by ts 2017-8-2
 */
@Intercepts( {
       @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class}) })
public class PageInterceptor implements Interceptor {

    private String databaseType;//数据库类型，不同的数据库有不同的分页方法
   
    /**
     * 拦截后要执行的方法
     * @Description
     * StatementHandler有两个实现类，RoutingStatementHandler,抽象类BaseStatementHandler
	        BaseStatementHandler有三个子类，分别是SimpleStatementHandler,PreparedStatementHandler,CallableStatementHandler
	        SimpleStatementHandler是用于处理Statement的，PreparedStatementHandler是处理PreparedStatement的，
	        CallableStatementHandler是处理CallableStatement的。
	        Mybatis在进行Sql语句处理的时候都是建立的RoutingStatementHandler，
	                     而在RoutingStatementHandler里面拥有一个StatementHandler类型的delegate属性，
	    	RoutingStatementHandler会依据Statement的不同建立对应的BaseStatementHandler，
	    	即SimpleStatementHandler、PreparedStatementHandler或CallableStatementHandler，
	    	在RoutingStatementHandler里面所有StatementHandler接口方法的实现都是调用的delegate对应的方法。
	                     我们在PageInterceptor类上已经用@Signature标记了该Interceptor只拦截StatementHandler接口的prepare方法
	    	又因为Mybatis只有在建立RoutingStatementHandler的时候是通过Interceptor的plugin方法进行包裹的，
	    	所以我们这里拦截到的目标对象肯定是RoutingStatementHandler对象。
     */
    public Object intercept(Invocation invocation) throws Throwable {
    	RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        //通过反射获取到当前RoutingStatementHandler对象的delegate属性
        StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
        //获取到当前StatementHandler的 boundSql，这里不管是调用handler.getBoundSql()还是直接调用delegate.getBoundSql()结果是一样的，
        //因为之前已经说过了RoutingStatementHandler实现的所有StatementHandler接口方法里面都是调用的delegate对应的方法。
        BoundSql boundSql = delegate.getBoundSql();
        //拿到当前绑定Sql的参数对象，就是我们在调用对应的Mapper映射语句时所传入的参数对象
        Object obj = boundSql.getParameterObject();
        //这里我们简单的通过传入的是Page对象就认定它是需要进行分页操作的。
        if (obj instanceof Page<?>) {
        	Page<?> page = (Page<?>) obj;
        	/*
        	 * 给当前的page参数对象设置总记录数
        	 * 通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
        	 * 拦截到的prepare方法参数是一个Connection对象
        	 */
            this.setTotalRecord(page,  
            		(MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement"),
            		(Connection)invocation.getArgs()[0]);
            /*  利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句   */
            ReflectUtil.setFieldValue(boundSql, "sql", this.getPageSql(page, boundSql.getSql()));
       }
       return invocation.proceed();
    }

    /**
     * 拦截器对应的封装原始对象的方法
     */
    public Object plugin(Object target) {
       return Plugin.wrap(target, this);
    }
 
    /**
     * 设置注册拦截器时设定的属性
     */
    public void setProperties(Properties properties) {
       this.databaseType = properties.getProperty("dialect");
    }

    /**
     * 设置数据库方言 2017-8-1
     * @param dialect
     */
    public void setDialect(String dialect){
        this.databaseType = dialect;
    }
   
    /**
     * 根据page对象获取对应的分页查询Sql语句，这里只做了两种数据库类型，Mysql和Oracle
     * 其它的数据库都 没有进行分页
     * @param page 分页对象
     * @param sql  原始sql语句
     * @return
     */
    private String getPageSql(Page<?> page, String sql) {
    	StringBuffer sqlBuffer = new StringBuffer(sql);
        if ("mysql".equals(databaseType)) {
            return getMysqlPageSql(page, sqlBuffer);
        } else if ("oracle".equals(databaseType)) {
            return getOraclePageSql(page, sqlBuffer);
        }
        return sqlBuffer.toString();
    }
    
    /**
     * mysql分页的sql拼装
     * @Description mysql的limit初始记录行的偏移量是 0（而不是1）
     * @date 2017年2月15日
     * @author 童晟  13346450@qq.com
     * @param @param page
     * @param @param sqlBuffer
     * @param @return
     * @return String
     */
    private String getMysqlPageSql(Page<?> page, StringBuffer sqlBuffer) {
    	if (page.getParams().get("sort") != null) {
    		sqlBuffer.append(" order by "+ StringUtils.getTrueField(page.getParams().get("sort"),page.getParams().get("order")));
    	}
    	sqlBuffer.append(" limit ").append(page.getOffset()).append(",").append(page.getLimit());
    	return sqlBuffer.toString();
    }
   
    /**
     * oracle分页的sql拼装
     * @Description  Oracle分页是通过rownum进行的，而rownum是从1开始的<br>
     * Sql语句拼接之后大概是这个样子：<br>
     *  select * from (select u.*, rownum r from (select * from t_user) u <br>
		where rownum < 31) where r >= 16
     * @date 2017年2月15日
     * @author 童晟  13346450@qq.com
     * @param @param page
     * @param @param sqlBuffer
     * @param @return
     * @return String
     */
    private String getOraclePageSql(Page<?> page, StringBuffer sqlBuffer) {
		sqlBuffer.insert(0, "select u.*, rownum r from (");
		if (page.getParams().get("sort") != null) {
			sqlBuffer.append(" order by "+ StringUtils.getTrueField(page.getParams().get("sort"),page.getParams().get("order")));
		}
		sqlBuffer.append(") u where rownum < ");
		sqlBuffer.append(page.getOffset() + page.getLimit() + 1);
		sqlBuffer.insert(0, "select * from (").append(") where r >= ")
				.append(page.getOffset());
		return sqlBuffer.toString();
    }
   
    /**
     * 给当前的参数对象page设置总记录数total
     * @param page Mapper映射语句对应的参数对象
     * @param mappedStatement Mapper映射语句
     * @param connection 当前的数据库连接
     */
    private void setTotalRecord(Page<?> page, MappedStatement mappedStatement, Connection connection) {
    	//获取到我们自己写在Mapper映射语句中对应的Sql语句
        BoundSql boundSql = mappedStatement.getBoundSql(page);
        //通过查询Sql语句获取到对应的计算总记录数的sql语句
        String countSql = this.getCountSql(boundSql.getSql());
        //利用Configuration、查询记录数的Sql语句countSql、参数映射关系parameterMappings和参数对象page建立查询记录数对应的BoundSql对象。
        BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(),
        			countSql, boundSql.getParameterMappings(), page);
        //通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);
        //通过connection建立一个countSql对应的PreparedStatement对象。
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(countSql);
            parameterHandler.setParameters(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
               page.setTotal(rs.getInt(1));
            }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           try {
              if (rs != null)
                  rs.close();
               if (pstmt != null)
                  pstmt.close();
           } catch (SQLException e) {
              e.printStackTrace();
           }
       }
    }
   
    /**
     * 根据原Sql语句获取对应的查询总记录数的Sql语句
     * @param sql
     * @return
     */
    private String getCountSql(String sql) {    	
    	int index = sql.indexOf("from");
        return "select count(1) " + sql.substring(index);
    }

    /**
     * 利用反射进行操作的一个工具类
     *
     */
    private static class ReflectUtil {
       /**
        * 利用反射获取指定对象的指定属性
        * @param obj 目标对象
        * @param fieldName 目标属性
        * @return 目标属性的值
        */
       public static Object getFieldValue(Object obj, String fieldName) {
           Object result = null;
           Field field = ReflectUtil.getField(obj, fieldName);
           if (field != null) {
              field.setAccessible(true);
              try {
                  result = field.get(obj);
              } catch (IllegalArgumentException e) {
                  e.printStackTrace();
              } catch (IllegalAccessException e) {
                  e.printStackTrace();
              }
           }
           return result;
       }
      
       /**
        * 利用反射获取指定对象里面的指定属性
        * @param obj 目标对象
        * @param fieldName 目标属性
        * @return 目标字段
        */
       private static Field getField(Object obj, String fieldName) {
           Field field = null;
          for (Class<?> clazz=obj.getClass(); clazz != Object.class; clazz=clazz.getSuperclass()) {
              try {
                  field = clazz.getDeclaredField(fieldName);
                  break;
              } catch (NoSuchFieldException e) {
                  //这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
              }
           }
           return field;
       }
 
       /**
        * 利用反射设置指定对象的指定属性为指定的值
        * @param obj 目标对象
        * @param fieldName 目标属性
         * @param fieldValue 目标值
        */
       public static void setFieldValue(Object obj, String fieldName,
              String fieldValue) {
           Field field = ReflectUtil.getField(obj, fieldName);
           if (field != null) {
              try {
                  field.setAccessible(true);
                  field.set(obj, fieldValue);
              } catch (IllegalArgumentException e) {
                  e.printStackTrace();
              } catch (IllegalAccessException e) {
                  e.printStackTrace();
              }
           }
        }
    }
 
}