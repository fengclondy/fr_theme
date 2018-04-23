package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 工商-静态-年报
 * @author lixiaoyi
 * @date 2018年4月20日下午1:42:10
 * @TODO
 */
public class CoComReportModel extends Model<CoComReportModel>{
	private static final long serialVersionUID = 1L;
	public static final CoComReportModel dao=new CoComReportModel();
	
	public  List<CoComReportModel> getReport(String name){
		String sql="select * from hub_static_company_report where 1=1";
		if(StringUtils.isNotBlank(name)){
			sql+=" and company_name='"+name+"'";
		}
		sql+=" limit 1";
		return dao.find(sql);
		
	}
			

}
