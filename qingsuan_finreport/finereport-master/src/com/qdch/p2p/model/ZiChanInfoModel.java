package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

public class ZiChanInfoModel extends Model<ZiChanInfoModel>{
	private static final long serialVersionUID = 1L;
	public static final ZiChanInfoModel dao=new ZiChanInfoModel();
	/**
	 * 获取自然人资产信息
	 * @author lixiaoyi
	 * @date 2018年4月8日
	 * @TODO
	 */
	public ZiChanInfoModel getAssetinfo(String bigjys,String pyType,String hasInfo){
		String sql="select * from insight_pp_asset_info where 1=1 ";
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
