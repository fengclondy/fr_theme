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
	/**
	 * 工商风险指数
	 * @author lixiaoyi
	 * @date 2018年4月20日 上午11:08:40
	 * @TODO
	 */
	public RiskTrendModel getFxzs(String data,String name){
		String sql="select fvalue from hub_xd_fxzs where vday=(select max(vday) from hub_xd_fxzsmx where 1=1";
		if(StringUtils.isNotBlank(data)){
			sql+=" and jysc in "+data;
		}
		if(StringUtils.isNotBlank(name)){
			sql+=" and jyscmc = '"+name+"'";
		}
		sql+=") ";
		return dao.findFirst(sql);
		
	}
	
	
}
