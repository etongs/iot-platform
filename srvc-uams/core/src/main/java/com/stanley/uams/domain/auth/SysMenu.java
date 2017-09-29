package com.stanley.uams.domain.auth;

import com.stanley.common.domain.TreeStructure;

/**
 * 当一个父类实现序列化，子类自动实现序列化，不需要显式实现Serializable接口
 * @Module SysMenu
 * @date 2016-04-07
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
 */
public class SysMenu extends TreeStructure {

	/**图标地址*/
	private String iconAddress;
	/**链接地址*/
	private String linkAddress;
	/**菜单标识*/
	private String menuMarker;
	/**资源id*/
	private Integer resourceId;

	/**获取图标地址*/
	public String getIconAddress() {
		return iconAddress;
	}
	/**设置图标地址*/
	public void setIconAddress(String iconAddress) {
		this.iconAddress = iconAddress;
	}
	/**获取链接地址*/
	public String getLinkAddress() {
		return linkAddress;
	}
	/**设置链接地址*/
	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}
	/**获取菜单标识*/
	public String getMenuMarker() {
		return menuMarker;
	}
	/**设置菜单标识*/
	public void setMenuMarker(String menuMarker) {
		this.menuMarker = menuMarker;
	}
	/**获取资源id*/
	public Integer getResourceId() {
		return resourceId;
	}
	/**设置资源id*/
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
}
