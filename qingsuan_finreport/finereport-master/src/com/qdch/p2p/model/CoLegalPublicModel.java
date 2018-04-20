package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 法律公告
 * @author lixiaoyi
 * @date 2018年4月20日上午11:43:18
 * @TODO
 */
public class CoLegalPublicModel extends Model<CoLegalPublicModel>{
	private static final long serialVersionUID = 1L;
	public static final CoLegalPublicModel dao=new CoLegalPublicModel();
	
	public  List<CoLegalPublicModel> getLegalPublic(String name){
		String sql="select * from hub_static_legal_public where 1=1";
		if(StringUtils.isNotBlank(name)){
			sql+=" and company_name='"+name+"'";
		}
		return dao.find(sql);
		
	}
			
}
