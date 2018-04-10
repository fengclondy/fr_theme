package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

public class RiskTrendDetailedModel extends Model<RiskTrendDetailedModel>{
	private static final long serialVersionUID = 1L;
	public static final RiskTrendDetailedModel dao = new RiskTrendDetailedModel();
	/**
	 * @todo   风险趋势明细
	 * @time   2018年4月9日 
	 * @author ljm 
	 */
	public List<RiskTrendDetailedModel> getRiskTrendDetailed(String datasql,String jysc,String riskType){
		String sql = "select jyscmc,fxlb,nums As fvalue ,jysc,vday from hub_xd_fxzsmx where 1=1";
		if(StringUtils.isNotBlank(datasql)){
			sql+=" and jysc in "+datasql;
		}
		if(StringUtils.isNotBlank(jysc)){
			sql+=" and jysc = '"+jysc+"'";
		}
		if(StringUtils.isNotBlank(riskType)){
			sql+=" and fxlb = '"+riskType+"'";
		}
		//sql+=" group by jyscmc,fxlb,nums,jysc,vday order by jyscmc,fxlb,fvalue,jysc,vday";
		sql+=" group by jyscmc,fxlb,nums,jysc,vday order by vday";
		return dao.find(sql);
	}
	
	
	public List<RiskTrendDetailedModel> getPlat(String datasql){
		String sql = "SELECT jyscmc from hub_xd_fxzsmx GROUP BY jyscmc";
		return dao.find(sql);
	}
}
