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
	public List<DefenInfoModel> getScoreList(String bigjys,String pyType){
		String sql="select jysinfo,fscore,jysc from insight_pp_score_info where 1=1 ";
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and jysc in "+bigjys;
		} 
		if(StringUtils.isNotBlank(pyType)){
			sql+=" and jysinfo = '"+pyType+"' ";
		} 
		sql+=" order by fscore desc";
	    return	dao.find(sql);
	}
	
	
	
}
