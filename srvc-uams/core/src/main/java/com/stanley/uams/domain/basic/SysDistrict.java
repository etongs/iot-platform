package com.stanley.uams.domain.basic;

import com.stanley.common.domain.TreeStructure;

/**
 * @Module SysDistrict
 * @date 2016-04-13
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
 */
public class SysDistrict extends TreeStructure {

	/**地区编码*/
	private String code;
	/**X坐标-百度地图*/
	private Double longitudeBaidu;
	/**Y坐标-百度地图*/
	private Double latitudeBaidu;
	/**地区拼音缩写*/
	private String py;
	/**地区拼音*/
	private String pinyin;
	/**是否是热门城市*/
	private Boolean ishot;

	/**获取地区编码*/
	public String getCode() {
		return code;
	}
	/**设置地区编码*/
	public void setCode(String code) {
		this.code = code;
	}
	/**经度-百度地图*/	
	public Double getLongitudeBaidu() {
		return longitudeBaidu;
	}
	public void setLongitudeBaidu(Double longitudeBaidu) {
		this.longitudeBaidu = longitudeBaidu;
	}
	/**纬度-百度地图*/
	public Double getLatitudeBaidu() {
		return latitudeBaidu;
	}
	public void setLatitudeBaidu(Double latitudeBaidu) {
		this.latitudeBaidu = latitudeBaidu;
	}
	/**获取地区拼音缩写*/
	public String getPy() {
		return py;
	}
	/**设置地区拼音缩写*/
	public void setPy(String py) {
		this.py = py;
	}
	/**获取地区拼音*/
	public String getPinyin() {
		return pinyin;
	}
	/**设置地区拼音*/
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	/**获取是否是热门城市*/
	public Boolean getIshot() {
		return ishot;
	}
	/**设置是否是热门城市*/
	public void setIshot(Boolean ishot) {
		this.ishot = ishot;
	}
}
