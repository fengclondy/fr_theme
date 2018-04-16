package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * @author hanpengda
 * @date 2018年4月12日
 * @TODO 分散度计算
 */
public class FenSanDuJiSuanModel extends Model<FenSanDuJiSuanModel>{

	
	private static final long serialVersionUID = 1L;
	public static final FenSanDuJiSuanModel dao = new FenSanDuJiSuanModel();
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月12日
	 * @TODO 借款分散度
	 */
	public List<FenSanDuJiSuanModel> getJkfsd(String jysIds,String jysc){

		String sql = "select hhi_value from insight_pp_hhi_calculate where 1=1";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and jysc in "+jysIds+"";
		}
		if (StringUtils.isNotBlank(jysc)) {
			sql += " and jysc = '"+jysc+"'";
		}
		sql += " and vday_ym = (select to_char(now() - INTERVAL '1 month','yyyymm')) and hhi_type = '借款人HHI'";
		
		return dao.find(sql);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月12日
	 * @TODO 投资分散度
	 */
	public List<FenSanDuJiSuanModel> getTzfsd(String jysIds,String jysc){

		String sql = "select hhi_value from insight_pp_hhi_calculate where 1=1";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and jysc in "+jysIds+"";
		}
		if (StringUtils.isNotBlank(jysc)) {
			sql += " and jysc = '"+jysc+"'";
		}
		sql += " and vday_ym = (select to_char(now() - INTERVAL '1 month','yyyymm')) and hhi_type = '投资人HHI'";
		
		return dao.find(sql);
	}
}
