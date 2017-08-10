package com.stanley.common.domain;
/**
 * 查询表单中的参数集合
 * @ClassName: SearchParam
 * @Description 统一处理所有功能的查询表单项
 * @date 2017年2月16日
 * @since 1.0 
 * @version 1.0  
 * @author 童晟  13346450@qq.com
 */
public class SearchParam {
	private String startDate;			//开始日期
	private String endDate;		    	//结束日期
	private String startDatetime;		//开始时间
	private String endDatetime;		    //结束时间
	private String sort; 				//排序的字段
	private String order;  				//排序的方式  asc和desc
	private int   offset;  				//分页的起始条
	private int   limit;  				//分页的每页数量
	private Integer searchId;			//查询id
	private String searchName; 			//输入文本项
	private String searchName2; 		//输入文本项2
	private String checkedIds; 			//下拉菜单、复选框等选中的ids
	private String checkedIds2; 		//下拉菜单、复选框等选中的ids2
	private String selectedId; 	        //下拉菜单选中的value
	private String selectedId2;	        //下拉菜单选中的value2
	private boolean enabled;			//是否启用
	private Integer province;			//省、直辖市id
	private Integer city;				//城市id
	private Integer district;			//区县id
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartDatetime() {
		return startDatetime;
	}
	public void setStartDatetime(String startDatetime) {
		this.startDatetime = startDatetime;
	}
	public String getEndDatetime() {
		return endDatetime;
	}
	public void setEndDatetime(String endDatetime) {
		this.endDatetime = endDatetime;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public Integer getSearchId() {
		return searchId;
	}
	public void setSearchId(Integer searchId) {
		this.searchId = searchId;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public String getSearchName2() {
		return searchName2;
	}
	public void setSearchName2(String searchName2) {
		this.searchName2 = searchName2;
	}
	public String getCheckedIds() {
		return checkedIds;
	}
	public void setCheckedIds(String checkedIds) {
		this.checkedIds = checkedIds;
	}
	public String getCheckedIds2() {
		return checkedIds2;
	}
	public void setCheckedIds2(String checkedIds2) {
		this.checkedIds2 = checkedIds2;
	}
	public String getSelectedId() {
		return selectedId;
	}
	public void setSelectedId(String selectedId) {
		this.selectedId = selectedId;
	}
	public String getSelectedId2() {
		return selectedId2;
	}
	public void setSelectedId2(String selectedId2) {
		this.selectedId2 = selectedId2;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public Integer getDistrict() {
		return district;
	}
	public void setDistrict(Integer district) {
		this.district = district;
	}
	
}
