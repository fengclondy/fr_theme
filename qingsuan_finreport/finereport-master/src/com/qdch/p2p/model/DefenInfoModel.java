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
			sql+=" and jysc = '"+pyType+"' ";
		} 
		sql+=" order by fscore desc";
	    return	dao.find(sql);
	}
	
    
	
	
	
	
	
	/**
	 * 获取企业基本信息
	 * @author lixiaoyi
	 * @date 2018年4月8日
	 * @TODO
	 */
	public List<DefenInfoModel> getCompybasicinfo(String bigjys){
		String sql="select * from insight_pp_corp_info";
		return dao.find(sql);
	}
	/**
	 * 获取企业其他信息
	 * @author lixiaoyi
	 * @date 2018年4月8日
	 * @TODO
	 */
	public List<DefenInfoModel> getOtherinfo(String bigjys){
		String sql="select * from insight_pp_othe_info";
		return dao.find(sql);
	}
	/**
	 * 根据平台查询
	 * @author 高照
	 * @date 2018年4月10日
	 * @TODO
	 */
	public List<DefenInfoModel> getPingtai(String bigjys,String pingtai){
		String sql="select jysinfo,fscore from insight_pp_score_info i where i.jysinfo like pingtai ";
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and jysc in "+bigjys;
		} 
		
	    return	dao.find(sql);
	}
}
