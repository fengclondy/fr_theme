package com.qdch.p2p.model;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

public class ShenHeZiLiaoZiRanRenModel extends Model<ShenHeZiLiaoZiRanRenModel>{
	private static final long serialVersionUID = 1L;
	public static final ShenHeZiLiaoZiRanRenModel dao=new ShenHeZiLiaoZiRanRenModel();
	/**
	 * 获取审核自然人资产信息
	 * @author 高照
	 * @date 2018年4月12日
	 * @TODO
	 */
	public ShenHeZiLiaoZiRanRenModel getShenHeZiRanRen(String bigjys,String pyType,String hasInfo){
		String sql="select * from  insight_pp_person_audit where 1=1 ";
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
}
