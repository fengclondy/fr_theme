package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

public class FlowAmountModel extends Model<FlowAmountModel>{
	private static final long serialVersionUID = 1L;
	public static final FlowAmountModel dao = new FlowAmountModel();
	/**
	 * @todo   平台画像，资金流入
	 * @time   2018年4月13日 
	 * @author ljm   
	 * 
	 */
	public List<FlowAmountModel> getFlowAmount(String datasql,String jysinfo){
		String sql = "select vday,fvalue,jysinfo,jysc "
				+ "from insight_pp_flow_amount "
				+ "where vday<to_char(now(),'yyyymmdd') AND vday>=to_char(now()-INTERVAL '20 day','yyyymmdd') ";
		
		if(StringUtils.isNotBlank(datasql)){
			sql+=" and jysc in "+datasql;
		}
		
		if(StringUtils.isNotBlank(jysinfo)){
			sql+=" and jysinfo = '"+jysinfo+"'";
		}
		sql+=" order by vday ";
		
		return dao.find(sql);
	}
}
