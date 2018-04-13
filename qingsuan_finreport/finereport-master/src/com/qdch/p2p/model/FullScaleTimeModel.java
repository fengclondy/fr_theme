package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 
 * @author hanpengda
 * @date 2018年4月12日
 * @TODO 平均满标用时
 */
public class FullScaleTimeModel extends Model<FullScaleTimeModel>{

	
	private static final long serialVersionUID = 1L;
	public static final FullScaleTimeModel dao = new FullScaleTimeModel();
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月12日
	 * @TODO 获取上个月的时间不为0的平均满标用时
	 */
	public List<FullScaleTimeModel> getMbys(String jysIds,String jysc){
		String sql = "select AVG(usetime) as mbys from insight_pp_average_time where 1=1";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and jysc in '"+jysIds+"'";
		}
		if (StringUtils.isNotBlank(jysc)) {
			sql += " and jysc = '"+jysc+"'";
		}
		sql += " and vday_ym = (select to_char(now() - INTERVAL '1 month','yyyymm')) and usetime != 0";
		return dao.find(sql);
	}
}
