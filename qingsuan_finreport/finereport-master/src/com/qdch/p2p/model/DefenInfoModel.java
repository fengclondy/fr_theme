
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
				+ " from (select pp1.jysc,pp1.jyscmc,pp1.jysinfo,p1.vday,max(coalesce(p1.fscore,0.00)) as fscore from hub_pp_jysc pp1 left join insight_pp_score_info p1 on pp1.jysc=p1.jysc"
				+ " where 1=1";
				if(StringUtils.isNotBlank(bigjys)){
					sql+=" and pp1.jysc in "+bigjys;
				} 
				if(StringUtils.isNotBlank(pyType)){
					sql+=" and pp1.jysinfo = '"+pyType+"' ";
				} 
				sql+= "and coalesce(p1.vday,'~')=coalesce((SELECT max(vday) from insight_pp_score_info where jysc=p1.jysc),'~')"
				+ " GROUP BY pp1.jysc,pp1.jyscmc,pp1.jysinfo,p1.vday) t1"
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
