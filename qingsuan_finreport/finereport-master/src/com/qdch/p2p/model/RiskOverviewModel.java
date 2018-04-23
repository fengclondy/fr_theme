
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
		String sql="select t1.vday as day,t1.jysc,t1.jysinfo,t1.fvalue as value,t1.pm  as keypm ,t2.pm as pre_keypm from hub_pp_fxzs t1 left join hub_pp_fxzs t2 ON t1.jysc=t2.jysc and substr(t1.vday,1,6)=to_char(to_date(substr(t2.vday,1,6),'yyyymm')+INTERVAL '1 month','yyyymm') where 1=1 and t1.vday=(select max(vday) from hub_pp_fxzs where jysc=t1.jysc)";
		
		if(StringUtils.isNotBlank(dataSql)){
			sql+="  and t1.jysc in"+ dataSql+" ";
		}
		sql+=" order by keypm,day,jysc,jysinfo,value";
		List<RiskOverviewModel> lines=dao.find(sql);
		for(RiskOverviewModel model:lines){
			String innersql="select t1.fxlb,t1.fvalue as detailvalue,t1.pm as detailpm , t2.pm as old_pm from hub_pp_fxzsmx t1 left join hub_pp_fxzsmx t2 ON t1.jysc=t2.jysc and t1.fxlb=t2.fxlb and substr(t1.vday,1,6)=to_char(to_date(substr(t2.vday,1,6),'yyyymm')+INTERVAL '1 month','yyyymm') where t1.vday='"+model.get("day")+"' and t1.jysc='"+model.get("jysc")+"'";
			innersql+=" order by t1.fxlb,detailvalue,detailpm";
			List<RiskOverviewModel> list=dao.find(innersql);
			model.put("detaildata",list);
		}
		return lines;
	}

}
