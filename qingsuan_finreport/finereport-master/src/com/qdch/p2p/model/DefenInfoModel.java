
package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;


public class DefenInfoModel extends Model<DefenInfoModel>{
	private static final long serialVersionUID = 1L;
	public static final DefenInfoModel dao=new DefenInfoModel();
	/**
	 * 
	 * @author 高照
	 * @date 2018年4月10日  
	 * @TODO 平台得分
	 */
	public List<DefenInfoModel> getScoreList(String bigjys,String pyType,String hasInfo){
		String sql = "select"
				+ " t1.jysc,"
				+ " t1.jyscmc,"
				+ " t1.jysinfo,"
				+ " t1.vday,"
				+ " t1.fscore"
				+ " from (select jysc,jyscmc,jysinfo,vday,max(fscore) as fscore from insight_pp_score_info p1"
				+ " where 1=1";
				if(StringUtils.isNotBlank(bigjys)){
					sql+=" and jysc in "+bigjys;
				} 
				if(StringUtils.isNotBlank(pyType)){
					sql+=" and jysinfo = '"+pyType+"' ";
				} 
				sql+= "and vday=(SELECT max(vday) from insight_pp_score_info where jysc=p1.jysc)"
				+ " GROUP BY jysc,jyscmc,jysinfo,vday) t1"
				+ " order by t1.fscore desc";
		 return	dao.find(sql);
	}	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月13日
	 * @TODO 根据平台获取得分信息
	 */
	public DefenInfoModel getDeFenByJysc(String bigjys,String jysc){
		String sql="select * from insight_pp_score_info  where 1=1 "; 
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and jysc in "+bigjys;
		}
		if (StringUtils.isNotBlank(jysc)) {
			sql += " and jysc = '"+jysc+"'";
		}
		sql += " order by vday desc";
		return dao.findFirst(sql);
	}
}
