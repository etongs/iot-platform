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
	 * http请求超时毫秒数
	 */
	public final static int HTTP_CONNECTION_TIMEOUT = 8 * 1000;
	
	/**
	 * 页面上记录的条数，默认无限制 ROW_COUNT = 99
	 */
	public final static Integer ROW_COUNT = 99;

	/***
	 * 数形结构的根节点id   TREE_ROOT_ID = 1
	 */
	public final static Integer TREE_ROOT_ID = 1;

	/**
	 * 保持登录状态的天数
	 */
	public final static int REMEMBER_LOGIN_DAYS = 7;

	/**
	 * 登录失败最大次数
	 */
	public final static int LOGIN_FAILED_MAXTIMES = 6;

	/**
	 * 图片目录
	 */
	public final static String UPLOAD_IMAGE_DIR = "images";
	/**
	 * 文件目录
	 */
	public final static String UPLOAD_FILE_DIR = "files";
	/**
	 * 文件临时目录
	 */
	public final static String UPLOAD_TEMP_DIR = "temporarys";
	
	/**
	 * 数据字典：组织机构类别
	 */
	public final static String ORGANIZATION_TYPE = "organization_type";
	/**
	 * 数据字典：培训课程类别
	 */
	public final static String TRAINING_COURSE_TYPE = "training_course_type";
	/**
	 * 数据字典：培训课程状态
	 */
	public final static String TRAINING_COURSE_STATUS = "training_course_status";
	/**
	 * 数据字典：培训课程时间
	 */
	public final static String TRAINING_COURSE_PERIOD = "training_course_period";
	/**
	 * 数据字典：培训课程标签
	 */
	public final static String CONTENT_TRAINING_TAG = "content_training_tag";
	/**
	 * 数据字典：服务商品状态
	 */
	public final static String SERVICE_GOODS_STATUS = "service_goods_status";
	/**
	 * 数据字典：内容文章标签
	 */
	public final static String CONTENT_ARTICLE_TAG = "content_article_tag";
	/**
	 * 数据字典：活动标签
	 */
	public final static String FORM_EVENT_TAG = "form_event_tag";
	
	/**
	 * 角色：系统管理员
	 */
	public final static String ROLE_SYS_MANAGER = "1001";
	/**
	 * 角色：省级管理员
	 */
	public final static String ROLE_PROVINCE_MANAGER = "1026";
	/**
	 * 角色：市级管理员
	 */
	public final static String ROLE_CITY_MANAGER = "1027";
	/**
	 * 角色：区级管理员
	 */
	public final static String ROLE_DISTRICT_MANAGER = "1028";
	/**
	 * 角色：平台管理员
	 */
	public final static String ROLE_PLATFORM_MANAGER = "1029";
	
	/**
	 * 日志动作下拉
	 */
	@SuppressWarnings("serial")
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
	@SuppressWarnings("serial")
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
	@SuppressWarnings("serial")
	public final static List<GeneralConstant> HTTP_METHOR = new ArrayList<GeneralConstant>(){
		{
			add(new GeneralConstant(0,"GET","GET"));
			add(new GeneralConstant(1,"POST","POST"));	
		}
	};
	
	/**
	 * 接收的数据类型
	 */
	@SuppressWarnings("serial")
	public final static List<GeneralConstant> RECEIVED_DATA_FORMAT = new ArrayList<GeneralConstant>(){
		{
			add(new GeneralConstant(0,"JSON","JSON"));
			add(new GeneralConstant(1,"XML","XML"));	
		}
	};
}
