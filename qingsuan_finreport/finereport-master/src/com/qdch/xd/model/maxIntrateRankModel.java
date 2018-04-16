package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

public class maxIntrateRankModel extends Model<maxIntrateRankModel>{
	private static final long serialVersionUID = 1L;
	public static final maxIntrateRankModel dao = new maxIntrateRankModel();
	/**
	 * @todo   获取最高利率
	 * @time   2018年4月10日
	 * @author ljm
	 */
	public List<maxIntrateRankModel> getMaxIntrateRank(String dataSql){
		String sql = "select jyscmc,max(intrate) As max_intrate "
				+ "from insight_xd_intrate where 1=1  ";
		if(StringUtils.isNotBlank(dataSql)){
			sql+=" and jysc in"+ dataSql+" ";
		}
		sql+=" group by jyscmc order by max(intrate) asc";
		return dao.find(sql);
	}
}
