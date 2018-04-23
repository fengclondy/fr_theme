package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 工商-静态-专利
 * @author lixiaoyi
 * @date 2018年4月20日下午2:03:14
 * @TODO
 */
public class CoComPatentModel extends Model<CoComPatentModel>{
	private static final long serialVersionUID = 1L; 
	public static final CoComPatentModel dao =new CoComPatentModel();
	
	public  List<CoComPatentModel> getPatent(String name){
		String sql="select * from hub_static_patent_info where 1=1";
		if(StringUtils.isNotBlank(name)){
			sql+=" and company_name='"+name+"'";
		}
		return dao.find(sql);
		
		
	}

}
