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
	 * 操作功能(Create、Retrieve、Update、Delete、TreeDrag、Others)
	 */
	public final static String OPERITION_INSERT = "insert";
	public final static String OPERITION_UPDATE = "update";
	public final static String OPERITION_DELETE = "delete";
	public final static String OPERITION_DELETE_BATCH = "deleteBatch";
	public final static String OPERITION_TREE_DRAG = "saveDragAndDrop";

	/**
	 * 日志动作下拉
	 */
	public final static List<GeneralConstant> SYSLOG_CODE_OPERITION = new ArrayList<GeneralConstant>(){
		{
			add(new GeneralConstant(10,"insert", "新增"));
			add(new GeneralConstant(20,"update", "修改"));
			add(new GeneralConstant(30,"delete", "删除"));
			add(new GeneralConstant(40,"deleteBatch", "批量删除"));
			add(new GeneralConstant(50,"saveDragAndDrop", "树节点拖拽"));
			add(new GeneralConstant(60,"listPage", "查询"));

			add(new GeneralConstant(1111,"operate", "其他操作"));
		}
	};
	/**
	 * 日志数据表名下拉
	 */
	public final static List<GeneralConstant> SYSLOG_CODE_TABLE = new ArrayList<GeneralConstant>(){
		{
			add(new GeneralConstant(10,"SysUserController","用户管理"));
			add(new GeneralConstant(20,"SysOrganizationController","组织机构管理"));
			add(new GeneralConstant(30,"SysRoleController","角色管理"));
			add(new GeneralConstant(40,"SysPermissionController","系统授权"));
			add(new GeneralConstant(50,"SysDistrictController","行政区域维护"));
			add(new GeneralConstant(60,"SysParmsController","系统参数配置"));
			add(new GeneralConstant(70,"SysOpenApiController","开放接口接入管理"));
			add(new GeneralConstant(80,"SysInterfaceController","定时任务调度"));
			add(new GeneralConstant(90,"SysResourceController","资源管理"));
			add(new GeneralConstant(100,"SysDictController","数据字典维护"));
			add(new GeneralConstant(110,"SysMenuController","菜单管理"));

			add(new GeneralConstant(1111,"Controller","其他功能"));
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
