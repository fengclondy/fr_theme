package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * @author hanpengda
 * @date 2018年4月11日
 * @TODO 信息披露
 */ 
public class XinXiPiLouModel extends Model<XinXiPiLouModel>{

	private static final long serialVersionUID = 1L;
	public static final XinXiPiLouModel dao = new XinXiPiLouModel();
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月11日
	 * @TODO 根据平台获取信息披露
	 */
	public List<XinXiPiLouModel> getXxplByJysc(String jysIds,String jysc){
		String sql = "select pm.* from (select *,ROW_NUMBER() OVER(ORDER BY fvalue1 DESC) as pm "
						+ "from insight_pp_show_info  order by fvalue1 desc) as pm where 1=1";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and jysc in "+jysIds+"";
		}
		if (StringUtils.isNotBlank(jysc)) {
			sql += " and jysc = '"+jysc+"'";
		}
		return dao.find(sql);
	}
}
