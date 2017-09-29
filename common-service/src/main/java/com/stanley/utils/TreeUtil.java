package com.stanley.utils;

import com.stanley.common.domain.TreeStructure;
import com.stanley.common.domain.ZtreeModel;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {
	/**
	 * 把数据库的节点数据转化成树形的list以便返回
	 * @Description
	 * @date 2017年3月30日
	 * @author 童晟  13346450@qq.com
	 * @param @param list
	 * @param @return
	 * @return List<ZtreeModel>
	 */
	public static List<ZtreeModel> convertList2Children(List list){
		List<ZtreeModel> children = new ArrayList<ZtreeModel>();
		if(!list.isEmpty()){
			for(int i=0; i<list.size(); i++){
				TreeStructure tree = (TreeStructure) list.get(i);
				ZtreeModel node = new ZtreeModel();
				node.setId(tree.getIdKey());
				node.setName(tree.getCdNm());
				node.setIsParent(!tree.getLastMark());
				children.add(node);
			}
		}
		return children;
	}
	
}
