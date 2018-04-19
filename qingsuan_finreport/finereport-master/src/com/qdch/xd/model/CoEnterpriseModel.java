package com.qdch.xd.model;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * enterprice企业表ID获取
 * @author lixiaoyi
 * @date 2018年4月17日下午2:13:16
 * @TODO
 */
public class CoEnterpriseModel extends Model<CoEnterpriseModel>{
	private static final long serialVersionUID = 1L;
	public static final CoEnterpriseModel dao =new CoEnterpriseModel();
	/**
	 * 查询hub_commerce_enterprise表ID
	 * @author lixiaoyi
	 * @date 2018年4月17日 下午1:31:26
	 * @TODO
	 */
	public CoEnterpriseModel getid(String name){
		String sql="select id from hub_commerce_enterprise  ";
		if(StringUtils.isNotBlank(name)){
			sql+="where name='"+ name+"'";
		}
	
		return dao.findFirst(sql);
	}
	

}
