package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * @author hanpengda
 * @date 2018年4月12日
 * @TODO 平台状态
 */
public class PingTaiZhuangTaiModel extends Model<PingTaiZhuangTaiModel>{

	
	private static final long serialVersionUID = 1L;
	public static final PingTaiZhuangTaiModel dao = new PingTaiZhuangTaiModel();
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月12日
	 * @TODO 获取平台状态
	 */
	public List<PingTaiZhuangTaiModel> getPtzt(String jysIds,String jysc){
		String sql = "select status from insight_pp_jyscstatus where 1=1";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and jysc in '"+jysc+"'";
		}
		if (StringUtils.isNotBlank(jysc)) {
			sql += " and jysc = '"+jysc+"'";
		}
		return dao.find(sql);
	}
}
