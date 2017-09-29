/**
 * @author Administrator
 *
 */
package com.stanley.utils;

import com.stanley.common.domain.GeneralConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：常量类
 * 
 * @创建：作者: 创建时间：2013-10-28
 * @修改历史：
 */
final public class Constants {
	
	/**
	 * 操作功能(Create、Retrieve、Update、Delete、TreeDrag、Others)
	 */
	public final static String FUNC_OPER_NM_CREATE = "新增";
	public final static String FUNC_OPER_NM_QUERY  = "查询";
	public final static String FUNC_OPER_NM_UPDATE = "修改";
	public final static String FUNC_OPER_NM_DELETE = "删除";
	public final static String FUNC_OPER_NM_DUPLICATE = "复制";
	public final static String FUNC_OPER_NM_SHARE = "共享";
	public final static String FUNC_OPER_NM_LIST_SHOW = "列表显示";
	public final static String FUNC_OPER_NM_LIST_HIDE = "列表隐藏";
	public final static String FUNC_OPER_NM_PUBLISH = "发布";
	public final static String FUNC_OPER_NM_CANCEL_PUBLISH = "取消发布";
	public final static String FUNC_OPER_NM_PUSH_INDEX = "推送到首页";
	public final static String FUNC_OPER_NM_TREEDRAG = "树节点拖拽";
	public final static String FUNC_OPER_NM_UPLOAD_IMAGE = "上传图片";
	public final static String FUNC_OPER_NM_REMOVE_IMAGE = "删除图片";
	public final static String FUNC_OPER_NM_OTHERS = "Others";		//其它

	/**
	 * 系统使用的字符集 UTF-8
	 */
	public final static String CHARSET = "UTF-8";
	/**
	 * shiro的散列算法
	 */
	public final static String SHIRO_ENCRYPT_ALGORITHM = "SHA-256";
	/**
	 * shiro的散列次数
	 */
	public final static int SHIRO_ENCRYPT_TIMES = 2;
	/**
	 * http请求超时毫秒数
	 */
	public final static int HTTP_CONNECTION_TIMEOUT = 8 * 1000;
	/**
	 * 登录次数redis的前缀
	 */
	public final static String SHIRO_LOGIN_COUNT = "shiro_login_count_";
	/**
	 * 登录锁定redis的前缀
	 */
	public final static String SHIRO_IS_LOCK = "shiro_is_lock_";
	/**
	 * 登录失败最大次数
	 */
	public final static int LOGIN_FAILED_MAXTIMES = 5;
	/***
	 * 数形结构的根节点id   TREE_ROOT_ID = 1
	 */
	public final static Integer TREE_ROOT_ID = 1;



	/**
	 * 日志动作下拉
	 */
	public final static List<GeneralConstant> SYSLOG_CODE_OPERITION = new ArrayList<GeneralConstant>(){
		{
			add(new GeneralConstant(1,"1", Constants.FUNC_OPER_NM_CREATE));
			add(new GeneralConstant(2,"2", Constants.FUNC_OPER_NM_QUERY));
			add(new GeneralConstant(3,"3", Constants.FUNC_OPER_NM_UPDATE));
			add(new GeneralConstant(4,"4", Constants.FUNC_OPER_NM_DELETE));
			add(new GeneralConstant(5,"5", Constants.FUNC_OPER_NM_DUPLICATE));
			add(new GeneralConstant(6,"6", Constants.FUNC_OPER_NM_SHARE));
			add(new GeneralConstant(7,"7", Constants.FUNC_OPER_NM_LIST_SHOW));
			add(new GeneralConstant(8,"8", Constants.FUNC_OPER_NM_LIST_HIDE));
			add(new GeneralConstant(9,"9", Constants.FUNC_OPER_NM_PUBLISH));
			add(new GeneralConstant(10,"10", Constants.FUNC_OPER_NM_CANCEL_PUBLISH));
			add(new GeneralConstant(11,"11", Constants.FUNC_OPER_NM_PUSH_INDEX));
			add(new GeneralConstant(12,"12", Constants.FUNC_OPER_NM_TREEDRAG));
			add(new GeneralConstant(13,"13", Constants.FUNC_OPER_NM_UPLOAD_IMAGE));
			add(new GeneralConstant(14,"14", Constants.FUNC_OPER_NM_REMOVE_IMAGE));
			add(new GeneralConstant(15,"15", Constants.FUNC_OPER_NM_OTHERS));
		}
	};
	/**
	 * 日志数据表名下拉
	 */
	public final static List<GeneralConstant> SYSLOG_CODE_TABLE = new ArrayList<GeneralConstant>(){
		{
			add(new GeneralConstant(1,"1","菜单表"));
			add(new GeneralConstant(2,"2","用户表"));
			add(new GeneralConstant(3,"3","系统角色表"));
			add(new GeneralConstant(4,"4","行政区域表"));
			add(new GeneralConstant(5,"5","组织机构表"));
			add(new GeneralConstant(6,"6","角色表"));
			add(new GeneralConstant(7,"7","资源表(树形结构)"));
			add(new GeneralConstant(8,"8","文章分类表"));
			add(new GeneralConstant(9,"9","文章内容表"));
			add(new GeneralConstant(10,"10","文章套图表"));
			add(new GeneralConstant(11,"11","培训课程内容表"));
			add(new GeneralConstant(12,"12","数据字典表"));
			add(new GeneralConstant(13,"13","服务-商品表"));
			add(new GeneralConstant(14,"14","表单-活动主表"));
			add(new GeneralConstant(15,"15","表单-活动问题表"));
			add(new GeneralConstant(16,"16","表单-活动问题选项表"));
			add(new GeneralConstant(17,"17","文章模版表"));
			add(new GeneralConstant(18,"18","页面模块定义表"));
			add(new GeneralConstant(19,"19","页面内容配置表"));
			add(new GeneralConstant(20,"20","套图明细表"));
			add(new GeneralConstant(21,"21","用户角色对应表"));
			add(new GeneralConstant(22,"22","系统参数表"));
			add(new GeneralConstant(23,"23","上传图片"));
			add(new GeneralConstant(24,"24","删除图片"));
			add(new GeneralConstant(25,"25","上传文件"));
			add(new GeneralConstant(26,"26","精英风采表"));
		}
	};
	/**
	 * 请求类型
	 */
	public final static List<GeneralConstant> HTTP_METHOR = new ArrayList<GeneralConstant>(){
		{
			add(new GeneralConstant(0,"GET","GET"));
			add(new GeneralConstant(1,"POST","POST"));	
		}
	};
	/**
	 * 接收的数据类型
	 */
	public final static List<GeneralConstant> RECEIVED_DATA_FORMAT = new ArrayList<GeneralConstant>(){
		{
			add(new GeneralConstant(0,"JSON","JSON"));
			add(new GeneralConstant(1,"XML","XML"));	
		}
	};
}
