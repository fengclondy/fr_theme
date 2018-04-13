package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;


/**
 * @author hanpengda
 * @date 2018年4月11日
 * @TODO 平台人数
 */
public class PingTaiRenShuModel extends Model<PingTaiRenShuModel>{


	private static final long serialVersionUID = 1L;
	public static final PingTaiRenShuModel dao = new PingTaiRenShuModel();
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月11日
	 * @TODO 获取总借款人数
	 */
	public List<PingTaiRenShuModel> getZjkrs(String jysIds,String jysc){
		
		String sql = "select cust_num as zjkrs from insight_pp_cust_number "
				+ "where cust_type = '借款人' "
				+ "and vday_ym = (select to_char(now() - INTERVAL '1 month','yyyymm'))";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and jysc in '"+jysIds+"'";
		}
		if (StringUtils.isNotBlank(jysc)) {
			sql += " and jysc = '"+jysc+"'";
		}
		return dao.find(sql);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月11日
	 * @TODO 获取总投资人数
	 */
	public List<PingTaiRenShuModel> getZtzrs(String jysIds,String jysc){
		String sql = "select cust_num ztzrs from insight_pp_cust_number "
				+ "where cust_type = '投资人' "
				+ "and vday_ym = (select to_char(now() - INTERVAL '1 month','yyyymm'))";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and jysc in '"+jysIds+"'";
		}
		if (StringUtils.isNotBlank(jysc)) {
			sql += " and jysc = '"+jysc+"'";
		}
		return dao.find(sql);
	}
}
