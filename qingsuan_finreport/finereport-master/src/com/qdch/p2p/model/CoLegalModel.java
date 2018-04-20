package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 静态-工商-法律诉讼
 * @author lixiaoyi
 * @date 2018年4月20日上午11:17:35
 * @TODO
 */
public class CoLegalModel extends Model<CoLegalModel> {
	private static final long serialVersionUID = 1L;
	
	public static final CoLegalModel dao=new CoLegalModel();
	
	public  List<CoLegalModel> getLegal(String name){
		String sql="select * from hub_static_legal where 1=1";
		if(StringUtils.isNotBlank(name)){
			sql+=" and company_name='"+name+"'";
		}
		return dao.find(sql);
		
	}
}
