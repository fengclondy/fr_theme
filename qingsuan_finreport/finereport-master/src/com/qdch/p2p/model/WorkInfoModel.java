package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

public class WorkInfoModel extends Model<WorkInfoModel>{
	private static final long serialVersionUID = 1L;
	public static final WorkInfoModel dao=new WorkInfoModel();
	/**
	 * 获取自然人工作信息
	 * @author 高照
	 * @date 2018年4月10日
	 * @TODO
	 */
	public WorkInfoModel getJobinfo(String bigjys,String pyType,String hasInfo){
		String sql="select * from insight_pp_job_info where 1=1 ";
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and jysc in "+bigjys;
		} 
		//
		if(StringUtils.isNotBlank(pyType)){
			sql+=" and jysc = '"+pyType+"' ";
		} 
		if(StringUtils.isNotBlank(hasInfo)){
			sql+="  ";
		} 
		//日期
		sql+=" order by vday desc  ";
		return dao.findFirst(sql);
	}
	
	
	
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月13日
	 * @TODO 根据平台获取工作信息
	 */
	public WorkInfoModel getJobInfoByJysc(String bigjys,String jysc){
		String sql="select * from insight_pp_job_info  where 1=1 "; 
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
