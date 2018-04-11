package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * 专利信息
 * @author lixiaoyi
 * @date 2018年4月10日
 * @TODO
 */
public class CoPatentModel extends Model<CoPatentModel> {
	private static final long serialVersionUID = 1L;
	public static final CoPatentModel dao = new CoPatentModel();
    /**
     * 获取专利信息
     * @author lixiaoyi
     * @date 2018年4月10日 下午6:28:21
     * @TODO
     */
	public List<CoPatentModel> getPatent(String name){
		String sql="SELECT T.name,T.agents,T.category_no,T.inventors,T.apply_no,T.country_code,T.apply_date "
				+ "FROM public.hub_commerce_co_patent T LEFT JOIN hub_commerce_enterprise n ON T .company_name = n. NAME ";
		if(StringUtils.isNotBlank(name)){
			sql+="WHERE T.company_name = '"+ name+"'";
		}
				
		return dao.find(sql);
		
	}
}
