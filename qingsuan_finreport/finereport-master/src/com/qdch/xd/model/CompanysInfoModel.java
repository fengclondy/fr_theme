package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 获取工商企业基本信息数据
 * @author lixiaoyi
 * @date 2018年4月10日
 * @TODO
 */
public class CompanysInfoModel extends Model<CompanysInfoModel> {
	
	private static final long serialVersionUID = 1L;
	
	public static final CompanysInfoModel dao=new CompanysInfoModel();
	/**
	 * 查询所有企业名称
	 * @author lixiaoyi
	 * @date 2018年4月10日
	 * @TODO
	 */
	public List<CompanysInfoModel> getInfo(String data,String name){
		String sql="select m.company_name from hub_commerce_company_info m where ";
				if(StringUtils.isNotBlank(name)){
				sql+=" m.company_name !='"+name+"'";
				}
				sql+=" and m.company_name in (select n.company_name from hub_commerce_ref_jys n ";
				if(StringUtils.isNotBlank(data)){
					sql+="where n.jys in "+ data;
				}
				 sql+=")";
	  return dao.find(sql);
		
	}
	/**
	 * 获取企业基本信息
	 * @author lixiaoyi
	 * @date 2018年4月10日 下午1:27:36
	 * @TODO
	 */
	public List<CompanysInfoModel> getBasicinfo(String data,String name){
		String sql="select * from hub_commerce_company_info m ";
		if(StringUtils.isNotBlank(name)){
			sql+="where m.company_name='"+ name+"'";
		}
		
		return dao.find(sql);
	}
	
	
	

}
