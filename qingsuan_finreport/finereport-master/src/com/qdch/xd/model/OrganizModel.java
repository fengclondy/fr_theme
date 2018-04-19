package com.qdch.xd.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * 
 * @todo   机构/交易所 hub_dd_tqs_jys
 * @time   2018年4月18日15:23:01
 * @author wf
 */
public class OrganizModel extends Model<OrganizModel>{
	private static final long serialVersionUID = 1L;
	public static final OrganizModel dao = new OrganizModel();


	/**
	 * 交易所下拉列表
	 * @param jysfl
	 * @return
	 */

	public List<OrganizModel> getListByType(String jysfl){
		String sql = "select jys,jysmc from hub_dd_tqs_jys WHERE jysfl='"+jysfl+"' ORDER BY djrq ";
		return dao.find(sql);
	}




}
