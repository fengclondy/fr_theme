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
		sql+=" group by jyscmc,fxlb,nums,jysc,vday order by vday";
		return dao.find(sql);
	}
	
	
	public List<RiskTrendDetailedModel> getPlat(String datasql){
		String sql = "SELECT jyscmc from hub_pp_jysc where 1=1";
		if(StringUtils.isNotBlank(datasql)){
			sql+=" and jyscmc in "+datasql;
		}
		sql+=" GROUP BY jyscmc order by jyscmc";
		return dao.find(sql);
	}
	/**
	 * 风险雷达图指数获取
	 * @author lixiaoyi
	 * @date 2018年4月18日 上午11:19:04
	 * @TODO
	 */
	public RiskTrendDetailedModel getScore(String data,String name){
		String sql="select t1.jysc,t1.jyscmc, "
  +"SUM(CASE WHEN fxlb='信用风险' THEN nums ELSE 0 END) as xyfx,"
  +"SUM(CASE WHEN fxlb='管理风险' THEN nums ELSE 0 END) as xyfx1,"
  +"SUM(CASE WHEN fxlb='资产风险' THEN nums ELSE 0 END) as xyfx2, "
  +"SUM(CASE WHEN fxlb='合规风险' THEN nums ELSE 0 END) as xyfx3, "
  +"SUM(CASE WHEN fxlb='声誉风险' THEN nums ELSE 0 END) as xyfx4 "
  +"from hub_xd_fxzsmx t1 where vday=(select max(vday) from hub_xd_fxzsmx where 1=1 ";
		if(StringUtils.isNotBlank(name)){
			sql+=" and jyscmc='"+name+"'";
		}	
	if(StringUtils.isNotBlank(data)){
		sql+=" and jysc in "+data;
	}
  
     sql+=" )GROUP BY t1.jysc,t1.jyscmc";
     return dao.findFirst(sql);
	}
}
