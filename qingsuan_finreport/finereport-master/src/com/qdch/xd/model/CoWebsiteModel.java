package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * 网站信息
 * @author lixiaoyi
 * @date 2018年4月10日上午11:38:59
 * @TODO
 */
public class CoWebsiteModel extends Model<CoWebsiteModel>{
	private static final long serialVersionUID = 1L;
	public static final CoWebsiteModel dao = new  CoWebsiteModel();
	/**
	 * 获取网站备案信息
	 * @author lixiaoyi
	 * @date 2018年4月11日 上午9:01:58
	 * @TODO
	 */
	public List<CoWebsiteModel> getWebsite(String name){
		String sql="SELECT n.nature,T.audit_date,T.name,T.url,T.domain,T.register_no "
				+ "FROM public.hub_commerce_co_website T LEFT JOIN hub_commerce_enterprise n  ON T .company_name = n. NAME ";
		if(StringUtils.isNotBlank(name)){
			sql+="WHERE T.company_name = '"+ name+"'";
		}
				
		return dao.find(sql);
		
		
	}

}
