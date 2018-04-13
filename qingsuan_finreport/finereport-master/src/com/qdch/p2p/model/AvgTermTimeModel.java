package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 
 * @author hanpengda
 * @date 2018年4月12日
 * @TODO 平均借款期限
 */
public class AvgTermTimeModel extends Model<AvgTermTimeModel>{

	
	private static final long serialVersionUID = 1L;
	public static final AvgTermTimeModel dao = new AvgTermTimeModel();
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月12日
	 * @TODO 根据平台获取平均借款期限
	 */
	public List<AvgTermTimeModel> getPjjkqx(String jysIds,String jysc){
		String sql = "select * from insight_pp_avge_term where 1=1";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and jysc in "+jysIds+"";
		}
		if (StringUtils.isNotBlank(jysc)) {
			sql += " and jysc = '"+jysc+"'";
		}
		return find(sql);
	}
}
