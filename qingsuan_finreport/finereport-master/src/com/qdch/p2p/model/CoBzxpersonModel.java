package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 工商-静态-被执行人
 * @author lixiaoyi
 * @date 2018年4月20日下午1:28:00
 * @TODO
 */
public class CoBzxpersonModel extends Model<CoBzxpersonModel>{
	private static final long serialVersionUID = 1L;
	public static final CoBzxpersonModel dao =new CoBzxpersonModel();
	
	public List<CoBzxpersonModel> getPerson(String name){
		String sql="select * from hub_static_bzx_person where 1=1";
		if(StringUtils.isNotBlank(name)){
			sql+=" and company_name='"+name+"'";
		}
		return dao.find(sql);
		
	}
}
