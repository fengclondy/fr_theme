package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * @todo   获取不良率排名
 * @time   2018年4月10日
 * @author ljm
 */
public class defrateRankModel extends Model<defrateRankModel>{
	private static final long serialVersionUID = 1L;
	public static final defrateRankModel dao = new defrateRankModel();
	
	public List<defrateRankModel> getDefrateRank(String dataSql){
		String sql = "select max(vday),jysc,jysmc,round(sum(deftotal)/sum(loantotal),4) AS defrate "
				   + "FROM insight_xd_defrate "
				   + "where vday=(select max(vday)from insight_xd_defrate) ";
				  
		if(StringUtils.isNotBlank(dataSql)){
			sql+=" and jysc in"+ dataSql+" ";
		}
		sql+=" group by jysc,jysmc";
		return dao.find(sql);
	}
}
