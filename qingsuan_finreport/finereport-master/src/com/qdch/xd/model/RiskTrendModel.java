package com.qdch.xd.model;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.jfinal.plugin.activerecord.Model;


public class RiskTrendModel extends Model<RiskTrendModel>{
	private static final long serialVersionUID = 1L;
	public static final RiskTrendModel dao = new RiskTrendModel();
	/**
	 * @todo   风险趋势
	 * @time   2018年4月9日 
	 * @author ljm 
	 */
	public List<RiskTrendModel> getRiskTrend(String datasql,String jysc){
		String sql = "select jyscmc,fvalue,jysc,vday from hub_xd_fxzs where 1=1";
		if(StringUtils.isNotBlank(datasql)){
			sql+=" and jysc in "+datasql;
		}
		if(StringUtils.isNotBlank(jysc)){
			sql+=" and jysc = '"+jysc+"'";
		}
		sql+=" group by jyscmc,fvalue,jysc,vday order by vday";
		return dao.find(sql);
	}
	
	
}
