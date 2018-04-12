package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import com.jfinal.plugin.activerecord.Model;

/**
 * @author hanpengda
 * @date 2018年4月12日
 * @TODO 人均次数：借款，投资
 */
public class RenJunCiShuModel extends Model<RenJunCiShuModel>{

	
	private static final long serialVersionUID = 1L;
	public static final RenJunCiShuModel dao = new RenJunCiShuModel();
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月12日
	 * @TODO 获取人均借款次数
	 */
	public List<RenJunCiShuModel> getRjjkcs(String jysIds,String jysc){
		String sql = "select avge_value as rjjkcs from insight_pp_avge_count where 1=1";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and jysc in '"+jysIds+"'";
		}
		if (StringUtils.isNotBlank(jysc)) {
			sql += " and jysc = '"+jysc+"'";
		}
		sql += " and avge_type = '借款' and vday_ym = (select to_char(now() - INTERVAL '1 month','yyyymm'))";
		return dao.find(sql);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月12日
	 * @TODO 获取人均投资次数
	 */
	public List<RenJunCiShuModel> getRjtzcs(String jysIds,String jysc){
		String sql = "select avge_value as rjtzcs from insight_pp_avge_count where 1=1";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and jysc in '"+jysIds+"'";
		}
		if (StringUtils.isNotBlank(jysc)) {
			sql += " and jysc = '"+jysc+"'";
		}
		sql += " and avge_type = '投资' and vday_ym = (select to_char(now() - INTERVAL '1 month','yyyymm'))";
		return dao.find(sql);
	}
}
