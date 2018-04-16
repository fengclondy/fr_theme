package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月12日下午2:56:43
 * @TODO
 */
public class CoCompanyBrachModel extends Model<CoCompanyBrachModel>{
	private static final long serialVersionUID = 1L;
	public static final CoCompanyBrachModel dao =new CoCompanyBrachModel();
	/**
	 * 分支机构
	 * @author lixiaoyi
	 * @date 2018年4月12日 下午3:00:30
	 * @TODO
	 */
	public List<CoCompanyBrachModel> getBrach(String name){
		String sql ="select * from hub_static_company_branch ";
		if(StringUtils.isNotBlank(name)){
			sql+=" where company_name='"+name+"'";
		} 
		return dao.find(sql);
		
	}

}
