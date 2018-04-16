package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * 
 * @author hanpengda
 * @date 2018年4月11日
 * @TODO 平台总览-交易量
 */
public class JiaoYiLiangModel extends Model<JiaoYiLiangModel>{

	
	private static final long serialVersionUID = 1L;
	public static final JiaoYiLiangModel dao = new JiaoYiLiangModel();
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月11日
	 * @TODO 获取上月总成交额
	 */
	public List<JiaoYiLiangModel> getZcje(String jysIds,String jysc){
	
		String sql = "select tran_num as zcje "
				+ "from insight_pp_tran_number where tran_type = '总成交额' "
				+ "and vday_ym = (select to_char(now() - INTERVAL '1 month','yyyymm'))";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and jysc in "+jysIds+"";
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
	 * @TODO 获取上月总成交量
	 */
	public List<JiaoYiLiangModel> getZcjl(String jysIds,String jysc){
		String sql = "select tran_num as zcjl "
				+ "from insight_pp_tran_number where tran_type = '总成交量' "
				+ "and vday_ym = (select to_char(now() - INTERVAL '1 month','yyyymm'))";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and jysc in "+jysIds+"";
		}
		if (StringUtils.isNotBlank(jysc)) {
			sql += " and jysc = '"+jysc+"'";
		}
		return dao.find(sql);
	}
}
