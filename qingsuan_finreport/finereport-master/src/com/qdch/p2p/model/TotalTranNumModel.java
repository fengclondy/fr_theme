package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
import com.qdch.xd.model.RiskTrendDetailedModel;


public class TotalTranNumModel extends Model<TotalTranNumModel>{
	private static final long serialVersionUID = 1L;
	public static final TotalTranNumModel dao = new TotalTranNumModel();
	/**
	 * @todo   平台画像，总成交量
	 * @time   2018年4月10日 
	 * @author ljm 
	 */
	public List<TotalTranNumModel> getTotalTranNumModel(String datasql,String jysinfo){
		//查询本月之前所有月份的数据
		String sql = "select vday_ym As month,tran_type As jyzl,tran_num As jyzlvalue,jysinfo "
				+ "from insight_pp_tran_number where tran_type='总成交量' and vday_ym<to_char(now(),'yyyymm') ";
		
		if(StringUtils.isNotBlank(datasql)){
			sql+=" and jysc in "+datasql;
		}
		if(StringUtils.isNotBlank(jysinfo)){
			sql+=" and jysinfo = '"+jysinfo+"'";
		}
		sql+=" order by jysc,vday_ym ";
		return dao.find(sql);
	}
	/**
	 * @todo   平台画像，总成交额
	 * @time   2018年4月10日 
	 * @author ljm 
	 */
	public List<TotalTranNumModel> getCountTranNumModel(String datasql,String jysinfo){
		//查询本月之前所有月份的数据
		String sql = "select vday_ym As month,tran_type As jyze,tran_num AS jyzevalue,jysinfo "
				+ "from insight_pp_tran_number where tran_type='总成交额' and vday_ym<to_char(now(),'yyyymm') ";
		
		if(StringUtils.isNotBlank(datasql)){
			sql+=" and jysc in "+datasql;
		}
		
		if(StringUtils.isNotBlank(jysinfo)){
			sql+=" and jysinfo = '"+jysinfo+"'";
		}
		sql+=" order by jysc,vday_ym";
		
		return dao.find(sql);
		
	}
	/**
	 * @todo   平台画像，获取平台名称
	 * @time   2018年4月11日 
	 * @author ljm 
	 */
	public List<TotalTranNumModel> getPlat(String datasql){
		String sql = "select jysinfo from hub_pp_jysc where 1=1";
		if(StringUtils.isNotBlank(datasql)){
			sql+=" and jysc in "+datasql;
		}
		sql+=" group by jysinfo order by jysinfo";
		return dao.find(sql);
	}
}
