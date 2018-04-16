package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

public class AverageTimeModel extends Model<AverageTimeModel>{
	private static final long serialVersionUID = 1L;
	public static final AverageTimeModel dao = new AverageTimeModel();
	
	/**
	 * @todo   平台画像，平均满标用时
	 * @time   2018年4月11日 
	 * @author ljm 
	 */
	public List<AverageTimeModel> getAvgTime(String datasql,String jysinfo){
		//查询本月之前所有月份的数据
		String sql = "select vday_ym As month,jysc,jyscmc,jysinfo,CEIL(avg(usetime)) As avgtime "
				+ "from insight_pp_average_time "
				+ "where vday_ym < to_char(now(),'yyyymm') and usetime != 0";
		
		if(StringUtils.isNotBlank(datasql)){
			sql+=" and jysc in "+datasql;
		}
		if(StringUtils.isNotBlank(jysinfo)){
			sql+=" and jysinfo = '"+jysinfo+"'";
		}
		sql+=" group by vday_ym,jysc,jyscmc,jysinfo order by month ";
		return dao.find(sql);
	}
	
}
