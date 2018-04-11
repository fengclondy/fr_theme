package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

public class CustNumberModel extends Model<CustNumberModel>{
	private static final long serialVersionUID = 1L;
	public static final CustNumberModel dao = new CustNumberModel();
	
	/**
	 * @todo   平台画像，人均借款
	 * @time   2018年4月10日 
	 * @author ljm 
	 */
	public List<CustNumberModel> getLoanNumber(String datasql,String jysinfo){
		//查询本月之前所有月份借款的数据
		String sql = "select substr(vday_ym,5,2) As month,cust_type As rjjk,cust_num As rjjkvalue,jysinfo "
				+ "from insight_pp_cust_number "
				+ "where cust_type='人均借款' and vday_ym<to_char(now(),'yyyymm') ";
		
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
	 * @todo   平台画像，人均投资
	 * @time   2018年4月10日 
	 * @author ljm 
	 */
	public List<CustNumberModel> getInvestNumber(String datasql,String jysinfo){
		//查询本月之前所有月份投资的数据
		String sql = "select substr(vday_ym,5,2) As month,cust_type As rjtz,cust_num As rjtzvalue,jysinfo "
				+ "from insight_pp_cust_number "
				+ "where cust_type='人均投资' and vday_ym<to_char(now(),'yyyymm') ";
		
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
