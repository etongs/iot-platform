/**
 * 
 */
package com.stanley.common.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stanley.utils.JsonDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/** 
 * 树形结构,所有要实现树形结构的类都要继承此类,根节点是-1
 * @ClassName: TreeStructure 
 * @Description: 
 * @author 童晟
 * @date 2013-10-11 下午2:48:34 
 *  
 */
public class TreeStructure implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer idKey;
	/** 名称 */
	private String cdNm;
	/** 是否是末级节点,1:是,0:不是.(异步加载有用到) */
	private Boolean lastMark;
	/** 父节点Id */
	private Integer parentId;
	/** 排序号 */
	private Integer orderCd;
	/** 是否启用,1:启用,0:不启用 */
	private Boolean isuse;
	/** 节点级别 */
	private Integer treeLevel;
	/** 创建人 */
	private Integer createId;
	/** 创建时间 */
	private Timestamp createDt;
	/** 备注 */
	private String remarks;
	/** 子节点 */
	private List<TreeStructure> childList = new ArrayList<>();
	
	/** 获取主键 */
	public Integer getIdKey() {
		return idKey;
	}
	/** 设置主键 */
	public void setIdKey(Integer idKey) {
		this.idKey = idKey;
	}
	/** 获得名称 */
	public String getCdNm() {
		return cdNm;
	}
	/** 设置名称 */
	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}
	/** 获得末级节点 */
	public Boolean getLastMark() {
		return lastMark;
	}
	/** 设置末级节点 */
	public void setLastMark(Boolean lastMark) {
		this.lastMark = lastMark;
	}
	/** 获得父节点Id */
	public Integer getParentId() {
		return parentId;
	}
	/** 设置父节点Id */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/** 获得排序号 */
	public Integer getOrderCd() {
		return orderCd;
	}
	/** 设置排序号 */
	public void setOrderCd(Integer orderCd) {
		this.orderCd = orderCd;
	}
	/** 获得是否启用 */
	public Boolean getIsuse() {
		return isuse;
	}
	/** 设置启用否 */
	public void setIsuse(Boolean isuse) {
		this.isuse = isuse;
	}
	/** 获得节点级别 */
	public Integer getTreeLevel() {
		return treeLevel;
	}
	/** 设置节点级别 */
	public void setTreeLevel(Integer treeLevel) {
		this.treeLevel = treeLevel;
	}
	/** 获得创建人 */
	public Integer getCreateId() {
		return createId;
	}
	/** 设置创建人 */
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	/** 获得创建时间 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Timestamp getCreateDt() {
		return createDt;
	}
	/** 设置创建时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}
	/** 获得备注 */
	public String getRemarks() {
		return remarks;
	}
	/** 设置备注 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/** 获得子节点 */
	public List<TreeStructure> getChildList() {
		return childList;
	}
	/** 设置子节点 */
	public void setChildList(List<TreeStructure> childList) {
		this.childList = childList;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idKey == null) ? 0 : idKey.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreeStructure other = (TreeStructure) obj;
		if (idKey == null) {
			if (other.idKey != null)
				return false;
		} else if (!idKey.equals(other.idKey))
			return false;
		return true;
	}
	
}
