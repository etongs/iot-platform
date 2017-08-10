package com.stanley.common.domain.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页的实体类
 * @ClassName: Page
 * @Description
 * @date 2017年2月15日
 * @since 1.0 
 * @version 1.0  
 * @author 童晟  13346450@qq.com
 * @param <T>
 */
public class Page<T> {
	/** 偏移量（从第几条开始）  */
	private int offset;
	/** 每页条数（取几条）  */
    private int limit;
    /** 总条数(前端bootstrap table用) */
    private int total;
    /** 本页数据 */
    private List<T> rows;
    /** 查询参数 */
    private Map<String, Object> params = new HashMap<String, Object>();
    
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
    
}
