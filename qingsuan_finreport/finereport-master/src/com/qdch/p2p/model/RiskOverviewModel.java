package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 获取风险总览信息
* @author doushuihai  
* @date 2018年4月13日下午12:55:44  
* @TODO
 */
public class RiskOverviewModel extends Model<RiskOverviewModel> {

	/** 
	* @Fields serialVersionUID : TODO 
	*/  
	private static final long serialVersionUID = 1L;
	public static final RiskOverviewModel dao=new RiskOverviewModel();

	public List<RiskOverviewModel> getRiskIndex(String dataSql){	
		String sql="select vday as day,jysc,jysinfo,fvalue as value,pm  as keypm from hub_pp_fxzs where 1=1 and vday=(select max(vday) from hub_pp_fxzs)";
		
		if(StringUtils.isNotBlank(dataSql)){
			sql+="  and jysc in"+ dataSql+" ";
		}
		
		List<RiskOverviewModel> lines=dao.find(sql);
		for(RiskOverviewModel model:lines){
			String innersql="select fxlb,fvalue as detailvalue,pm as detailpm from hub_pp_fxzsmx where vday='"+model.get("day")+"' and jysc='"+model.get("jysc")+"'";
			List<RiskOverviewModel> list=dao.find(innersql);
			model.put("detaildata",list);
		}
		return lines;
	}

}
