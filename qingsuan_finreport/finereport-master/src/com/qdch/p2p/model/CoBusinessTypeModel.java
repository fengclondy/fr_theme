package com.qdch.p2p.model;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月12日下午3:33:03
 * @TODO
 */
public class CoBusinessTypeModel extends Model<CoBusinessTypeModel> {
	private static final long serialVersionUID = 1L;
	public static final CoBusinessTypeModel dao=new CoBusinessTypeModel();
	/**
	 * 行业分类
	 * @author lixiaoyi
	 * @date 2018年4月12日 下午3:35:35
	 * @TODO
	 */
	public CoBusinessTypeModel getBusinessType(String  name){
		String sql ="select * from hub_static_business_type ";
		if(StringUtils.isNotBlank(name)){
			sql+=" where company_name='"+name+"'";
		} 
		return dao.findFirst(sql);
		
	}

}
