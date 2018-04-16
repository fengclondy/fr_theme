package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * @author hanpengda
 * @date 2018年4月10日
 * @TODO 平台信息
 */
public class PingTaiXinXiModel extends Model<PingTaiXinXiModel>{

	private static final long serialVersionUID = 1L;
	public static final PingTaiXinXiModel dao = new PingTaiXinXiModel();

	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月10日
	 * @TODO 获取平台信息
	 */
	public List<PingTaiXinXiModel> getPtxx(String jysIds){
		String sql = "select * from hub_pp_jysc where 1=1";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and jysc in "+jysIds+"";
		}
		sql += " order by jysc";
		return dao.find(sql);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月11日
	 * @TODO 根据平台获取平台信息
	 */
	public List<PingTaiXinXiModel> getPtxxByJysc(String jysIds,String jysc){
		String sql = "select * from hub_pp_jysc where 1=1";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and jysc in "+jysIds+"";
		}
		if (StringUtils.isNotBlank(jysc)) {
			sql += " and jysc = '"+jysc+"'";
		}
		return dao.find(sql);
	}
}
