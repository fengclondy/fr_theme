package com.qdch.p2p.model;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月12日下午1:34:33
 * @TODO
 */
public class CoCompanyTypeModel extends Model<CoCompanyTypeModel> {
	private static final long serialVersionUID = 1L;
	public static final CoCompanyTypeModel dao=new CoCompanyTypeModel();
	/**
	 * 静态工商公司属性
	 * @author lixiaoyi
	 * @date 2018年4月12日 下午1:34:37
	 * @TODO
	 */
	public CoCompanyTypeModel getType(String name){
		String sql="select * from hub_static_company_type_info ";
		if(StringUtils.isNotBlank(name)){
			sql+=" where company_name='"+name+"'";
		} 
		return dao.findFirst(sql);
		
	}
			

}
