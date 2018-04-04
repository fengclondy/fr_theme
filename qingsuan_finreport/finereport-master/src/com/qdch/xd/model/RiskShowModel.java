package com.qdch.xd.model;



import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月4日
 * @TODO 管理风险警示
 */
public class RiskShowModel extends Model<RiskShowModel>{
	
	private static final long  serialVersionUID=1L;
	public static final RiskShowModel dao=new RiskShowModel();
	
	public List<RiskShowModel> gainShow(String datasql,String jys){
		String sql="select bjsj ,fxlb ,fxzb,jgmc from hub_fxsj where jysfl='3'";
				if(StringUtils.isNotBlank(jys)){
					sql+=" and jgmc = '"+jys+"'";
				}
			 sql+=" ORDER BY bjsj limit 30";
		return dao.find(sql);
				
		
	}
	
	

}
