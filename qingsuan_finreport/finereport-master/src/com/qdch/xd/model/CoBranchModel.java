package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 工商-分支机构
 * @author lixiaoyi
 * @date 2018年4月10日
 * @TODO
 */
public class CoBranchModel extends Model<CoBranchModel>{
	private static final long serialVersionUID = 1L;
	public static final CoBranchModel dao=new CoBranchModel();
	/**
	 * 分支机构信息
	 * @author lixiaoyi
	 * @date 2018年4月10日 下午4:58:50
	 * @TODO
	 */
	public List<CoBranchModel> getBranch(String name){
		String sql="SELECT T.branch_name,m.legal_person,m.business_status,m.PUBLISH_DATE "
				+ "FROM hub_commerce_co_branch T LEFT JOIN "
				+ "hub_commerce_enterprise n ON T .company_name = n. NAME "
				+ "LEFT JOIN hub_commerce_company_info m ON "
				+ "m.company_name = t.BRANCH_NAME ";
		if(StringUtils.isNotBlank(name)){
			sql+="WHERE T.company_name ='"+ name+"'";
		}
				
		return dao.find(sql);
	}

}
