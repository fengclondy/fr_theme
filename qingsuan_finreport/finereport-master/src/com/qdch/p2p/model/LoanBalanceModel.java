package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * @author hanpengda
 * @date 2018年4月12日
 * @TODO 贷款余额
 */
public class LoanBalanceModel extends Model<LoanBalanceModel>{

	private static final long serialVersionUID = 1L;
	public static final LoanBalanceModel dao = new LoanBalanceModel();
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月12日
	 * @TODO 获取贷款余额
	 */
	public List<LoanBalanceModel> getDkye(String jysIds,String jysc){
		String sql = "select * from insight_pp_collect_principal where 1=1";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and jysc in "+jysIds+"";
		}
		if (StringUtils.isNotBlank(jysc)) {
			sql += " and jysc = '"+jysc+"'";
		}
		sql += " and vday = (SELECT  to_char(CURRENT_DATE + cast(-1*(TO_NUMBER(to_char(CURRENT_DATE,'DD'),'99')) ||' days' as interval), 'yyyymmdd'))";
		return find(sql);
	}
}
