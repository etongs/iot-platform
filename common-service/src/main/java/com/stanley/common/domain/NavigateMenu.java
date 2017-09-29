package com.stanley.common.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 主界面左边的导航菜单（多级结构）
 * @ClassName: NavigateMenu
 * @Description
 * @date 2017年3月28日
 * @since 1.0 
 * @version 1.0  
 * @author 童晟  13346450@qq.com
 */
public class NavigateMenu implements Serializable{
	private static final long serialVersionUID = 2063943429916423392L;
	
	private String id;
	private String text;
	private String icon;
	private String url;
	private boolean close = true;
	private List<NavigateMenu> menus;	//子菜单
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isClose() {
		return close;
	}
	public void setClose(boolean close) {
		this.close = close;
	}
	public List<NavigateMenu> getMenus() {
		return menus;
	}
	public void setMenus(List<NavigateMenu> menus) {
		this.menus = menus;
	}
	
}
