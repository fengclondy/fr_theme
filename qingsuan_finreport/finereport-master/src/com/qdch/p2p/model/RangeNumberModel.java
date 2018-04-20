package com.qdch.p2p.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

public class RangeNumberModel extends Model<RangeNumberModel>{
	private static final long serialVersionUID = 1L;
	public static final RangeNumberModel dao = new RangeNumberModel();
	/**
	 * @todo   平台画像，借款金额区间人数
	 * @time   2018年4月13日 
	 * @author ljm   
	 */
	public List<RangeNumberModel> getLoanRangeNumber(String datasql,String jysinfo,String ppType){
		//借款金额区间
		String rangeSql = "select distinct(pp_range)  as pp_range from insight_pp_range_number where pp_type= "+"'"+ppType+"'";
		if(StringUtils.isNotBlank(jysinfo)){
			rangeSql+=" and jysinfo = '"+jysinfo+"'";
		}
		rangeSql+=" order by pp_range ";
		List<RangeNumberModel> rangeList = dao.find(rangeSql);
		for(RangeNumberModel m:rangeList){
			List<RangeNumberModel> numList = new ArrayList<RangeNumberModel>();
			String numSql = "select vday_ym ,jysc,jyscmc,jysinfo,pp_type AS jkr,pp_num AS jkrnum,pp_range "
					+ "from insight_pp_range_number "
					+ "where vday_ym < to_char(now(),'yyyymm') and pp_range = "+" '"+m.getStr("pp_range")+"' ";
			if(StringUtils.isNotBlank(datasql)){
				numSql+=" and jysc in "+datasql;
			}
			if(StringUtils.isNotBlank(jysinfo)){
				numSql+=" and jysinfo = '"+jysinfo+"'";
			}
			if(StringUtils.isNotBlank(ppType)){
				numSql+=" and pp_type = '"+ppType+"'";
			}
			numSql+=" group by vday_ym,jysc,jyscmc,jysinfo,pp_type,pp_num,pp_range order by vday_ym ";
			numList = dao.find(numSql);
			m.put("numlist", numList);
		}
		return rangeList;
	}
	
	
	/**
	 * @todo   平台画像，投资金额区间人数
	 * @time   2018年4月12日 
	 * @author ljm   
	 */
	public List<RangeNumberModel> getInvestRangeNumber(String datasql,String jysinfo,String ppType){
		//投资金额区间
		String rangeSql = "select distinct(pp_range)  as pp_range from insight_pp_range_number where pp_type="+"'"+ppType+"'";
		if(StringUtils.isNotBlank(jysinfo)){
			rangeSql+=" and jysinfo = '"+jysinfo+"'";
		}
		rangeSql+=" order by pp_range ";
		List<RangeNumberModel> rangeList = dao.find(rangeSql);
		for(RangeNumberModel m:rangeList){
			List<RangeNumberModel> numList = new ArrayList<RangeNumberModel>();
			String numSql = "select jysc,vday_ym ,jyscmc,jysinfo,pp_type AS tzr,pp_num AS tzrnum,pp_range "
					+ "from insight_pp_range_number "
					+ "where vday_ym < to_char(now(),'yyyymm') and pp_range = "+"'"+m.getStr("pp_range")+"'";
			if(StringUtils.isNotBlank(datasql)){
				numSql+=" and jysc in "+datasql;
			}
			if(StringUtils.isNotBlank(jysinfo)){
				numSql+=" and jysinfo = '"+jysinfo+"'";
			}
			if(StringUtils.isNotBlank(ppType)){
				numSql+=" and pp_type = '"+ppType+"'";
			}
			numSql+=" group by vday_ym,jysc,jyscmc,jysinfo,pp_type,pp_num,pp_range order by vday_ym ";
			numList = dao.find(numSql);
			m.put("numlist", numList);
		}
		return rangeList;
	}
}
