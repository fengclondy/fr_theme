package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * @author hanpengda
 * @date 2018年4月10日
 * @TODO 平台信息
 */
public class ptxxModel extends Model<ptxxModel>{

	private static final long serialVersionUID = 1L;
	public static final ptxxModel dao = new ptxxModel();

	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月10日
	 * @TODO 获取平台信息
	 */
	public List<ptxxModel> getPtxx(String jysIds){
		String sql = "select * from hub_pp_jysc";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " jysc in "+jysIds+"";
		}
		return dao.find(sql);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月11日
	 * @TODO 根据平台获取平台信息
	 */
	public List<ptxxModel> getPtxxByJysc(String jysIds,String jysc){
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
