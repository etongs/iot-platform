package com.stanley.uams.domain.basic;

import com.stanley.utils.JsonDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Module SysOrganization
 * @date 2016-04-11
 * @since 1.0 
 * @version 1.0 
 * @author 13346450@qq.com 童晟
 */
public class SysOrganization implements Serializable{

	private static final long serialVersionUID = 1L;

	/**主键*/
	private Integer idKey;
	/**机构名称*/
	private String cdNm;
	/**机构类型*/
	private Integer category;
	/**上级机构(可空)*/
	private Integer parentOrgId;
	/**机构简称*/
	private String shortName;
	/**机构编码*/
	private String code;
	/**简拼*/
	private String py;
	/**拼音*/
	private String pinyin;
	/**传真*/
	private String linkFax;
	/**联系人*/
	private String linkMan;
	/**联系电话*/
	private String linkTel;
	/**负责人手机号*/
	private String linkMobile;
	/**Email*/
	private String email;
	/**机构网址*/
	private String url;
	/**地址*/
	private String address;
	/**X坐标-GPS*/
	private Double gpsX;
	/**Y坐标-GPS*/
	private Double gpsY;
	/**X坐标-百度地图*/
	private Double baiduX;
	/**Y坐标-百度地图*/
	private Double baiduY;
	/**邮政编码*/
	private String postCode;
	/**开户银行*/
	private String bankName;
	/**收款银行账号*/
	private String bankAccount;
	/**收款人姓名*/
	private String bankAccountName;
	/**银行卡预留手机号码*/
	private String bankReservedMobile;
	/**创建人*/
	private Integer createId;
	/**创建时间*/
	private Date createDt;
	/**省id*/
	private Integer provinceId;
	/**省名*/
	private String provinceName;
	/**市id*/
	private Integer cityId;
	/**市名*/
	private String cityName;
	/**县、区id*/
	private Integer districtId;
	/**县、区名*/
	private String districtName;
	/**机构图片缩略图*/
	private String imageMinUrl;
	/**机构图片原图*/
	private String imageUrl;
	/**机构描述*/
	private String description;

	/**获取主键(组织机构表，包括学校、运营平台)*/
	public Integer getIdKey() {
		return idKey;
	}
	/**设置主键(组织机构表，包括学校、运营平台)*/
	public void setIdKey(Integer idKey) {
		this.idKey = idKey;
	}
	/**获取机构名称*/
	public String getCdNm() {
		return cdNm;
	}
	/**设置机构名称*/
	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}
	/**获取机构类型(1管理平台(厦门会聚)、2学校、3、赞助商)*/
	public Integer getCategory() {
		return category;
	}
	/**设置机构类型(1管理平台(厦门会聚)、2学校、3、赞助商)*/
	public void setCategory(Integer category) {
		this.category = category;
	}
	/**获取上级机构(可空)*/
	public Integer getParentOrgId() {
		return parentOrgId;
	}
	/**设置上级机构(可空)*/
	public void setParentOrgId(Integer parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	/**获取机构简称*/
	public String getShortName() {
		return shortName;
	}
	/**设置机构简称*/
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	/**获取机构编码*/
	public String getCode() {
		return code;
	}
	/**设置机构编码*/
	public void setCode(String code) {
		this.code = code;
	}
	/**获取简拼*/
	public String getPy() {
		return py;
	}
	/**设置简拼*/
	public void setPy(String py) {
		this.py = py;
	}
	/**获取拼音*/
	public String getPinyin() {
		return pinyin;
	}
	/**设置拼音*/
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	/**获取传真*/
	public String getLinkFax() {
		return linkFax;
	}
	/**设置传真*/
	public void setLinkFax(String linkFax) {
		this.linkFax = linkFax;
	}
	/**获取联系人*/
	public String getLinkMan() {
		return linkMan;
	}
	/**设置联系人*/
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	/**获取联系电话*/
	public String getLinkTel() {
		return linkTel;
	}
	/**设置联系电话*/
	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}
	/**获取负责人手机号*/
	public String getLinkMobile() {
		return linkMobile;
	}
	/**设置负责人手机号*/
	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile;
	}
	/**获取Email*/
	public String getEmail() {
		return email;
	}
	/**设置Email*/
	public void setEmail(String email) {
		this.email = email;
	}
	/**获取机构网址*/
	public String getUrl() {
		return url;
	}
	/**设置机构网址*/
	public void setUrl(String url) {
		this.url = url;
	}
	/**获取地址*/
	public String getAddress() {
		return address;
	}
	/**设置地址*/
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getGpsX() {
		return gpsX;
	}
	public void setGpsX(Double gpsX) {
		this.gpsX = gpsX;
	}
	public Double getGpsY() {
		return gpsY;
	}
	public void setGpsY(Double gpsY) {
		this.gpsY = gpsY;
	}
	public Double getBaiduX() {
		return baiduX;
	}
	public void setBaiduX(Double baiduX) {
		this.baiduX = baiduX;
	}
	public Double getBaiduY() {
		return baiduY;
	}
	public void setBaiduY(Double baiduY) {
		this.baiduY = baiduY;
	}
	/**获取邮政编码*/
	public String getPostCode() {
		return postCode;
	}
	/**设置邮政编码*/
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	/**获取开户银行*/
	public String getBankName() {
		return bankName;
	}
	/**设置开户银行*/
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**获取收款银行账号*/
	public String getBankAccount() {
		return bankAccount;
	}
	/**设置收款银行账号*/
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	/**获取收款人姓名*/
	public String getBankAccountName() {
		return bankAccountName;
	}
	/**设置收款人姓名*/
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	/**获取银行卡预留手机号码*/
	public String getBankReservedMobile() {
		return bankReservedMobile;
	}
	/**设置银行卡预留手机号码*/
	public void setBankReservedMobile(String bankReservedMobile) {
		this.bankReservedMobile = bankReservedMobile;
	}
	/**获取创建人*/
	public Integer getCreateId() {
		return createId;
	}
	/**设置创建人*/
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	/**获取创建时间*/
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getCreateDt() {
		return createDt;
	}
	/**设置创建时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	/**获取省id*/
	public Integer getProvinceId() {
		return provinceId;
	}
	/**设置省id*/
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	/**获取省名*/
	public String getProvinceName() {
		return provinceName;
	}
	/**设置省名*/
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	/**获取市id*/
	public Integer getCityId() {
		return cityId;
	}
	/**设置市id*/
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/**获取市名*/
	public String getCityName() {
		return cityName;
	}
	/**设置市名*/
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**获取县、区id*/
	public Integer getDistrictId() {
		return districtId;
	}
	/**设置县、区id*/
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	/**获取县、区名*/
	public String getDistrictName() {
		return districtName;
	}
	/**设置县、区名*/
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	/**获取机构图片缩略图*/
	public String getImageMinUrl() {
		return imageMinUrl;
	}
	/**设置机构图片缩略图*/
	public void setImageMinUrl(String imageMinUrl) {
		this.imageMinUrl = imageMinUrl;
	}
	/**获取机构图片原图*/
	public String getImageUrl() {
		return imageUrl;
	}
	/**设置机构图片原图*/
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**获取机构描述*/
	public String getDescription() {
		return description;
	}
	/**设置机构描述*/
	public void setDescription(String description) {
		this.description = description;
	}
}
