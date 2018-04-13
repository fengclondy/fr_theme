package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

public class TranAmountModel extends Model<TranAmountModel>{
	private static final long serialVersionUID = 1L;
	public static final TranAmountModel dao = new TranAmountModel();
	/**
	 * @todo   平台画像，人均借款金额
	 * @time   2018年4月13日 
	 * @author ljm   
	 */
	public List<TranAmountModel> getLoanNumber(String datasql,String jysinfo){
		//查询本月之前所有月份借款的数据
		String sql = "select vday_ym As month,tran_type As rjjk,fvalue As rjjkvalue,jysinfo "
				+ "from insight_pp_tran_amount "
				+ "where tran_type='人均借款' and vday_ym<to_char(now(),'yyyymm') ";
		
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
	 * @todo   平台画像，人均投资金额
	 * @time   2018年4月13日 
	 * @author ljm   
	 */
	public List<TranAmountModel> getInvestNumber(String datasql,String jysinfo){
		String sql = "select vday_ym As month,tran_type As rjtz,fvalue As rjtzvalue,jysinfo "
				+ "from insight_pp_tran_amount "
				+ "where tran_type='人均投资' and vday_ym<to_char(now(),'yyyymm') ";
		
		if(StringUtils.isNotBlank(datasql)){
			sql+=" and jysc in "+datasql;
		}
		
		if(StringUtils.isNotBlank(jysinfo)){
			sql+=" and jysinfo = '"+jysinfo+"'";
		}
		sql+=" order by jysc,vday_ym";
		
		return dao.find(sql);
	}
	
}
