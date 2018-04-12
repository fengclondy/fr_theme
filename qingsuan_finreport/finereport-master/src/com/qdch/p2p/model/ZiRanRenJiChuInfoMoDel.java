
package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

public class ZiRanRenJiChuInfoMoDel extends Model<ZiRanRenJiChuInfoMoDel>{
	private static final long serialVersionUID=1L;
	public static final ZiRanRenJiChuInfoMoDel dao=new ZiRanRenJiChuInfoMoDel();
	/**
	 * 获取自然人基本信息
	 * @author lixiaoyi
	 * @date 2018年4月8日
	 * @TODO
	 */
	public ZiRanRenJiChuInfoMoDel getBasicinfo(String bigjys,String pyType,String hasInfo){
		String sql="select * from insight_pp_person_info  where 1=1 "; 
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and jysc in "+bigjys;
		} 
		//
		if(StringUtils.isNotBlank(pyType)){
			sql+=" and jysinfo = '"+pyType+"' ";
		} 
		/*if(StringUtils.isNotBlank(hasInfo)){
			
			sql+=" ";
		} */
		//日期
		sql+=" order by vday desc  ";
		return dao.findFirst(sql);
	}
}

