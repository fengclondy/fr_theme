package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

public class XinYongInfoModel extends Model<XinYongInfoModel> {
	private static final long serialVersionUID = 1L;
	public static final XinYongInfoModel dao=new XinYongInfoModel();
	/**
     * 获取自然人信用信息
     * @author 高照
     * @date 2018年4月10日
     * @TODO
     */
	public XinYongInfoModel getCreditinfo(String bigjys,String pyType,String hasInfo){
		String sql="select * from insight_pp_credit_info where 1=1 ";
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and jysc in "+bigjys;
		}
		if(StringUtils.isNotBlank(pyType)){
			sql+=" and jysc ='"+pyType+"' ";
		}
		if(StringUtils.isNotBlank(hasInfo)){
			sql+=" ";
		}
		//日期
			sql+=" order by vday desc  ";
		return dao.findFirst(sql);
	}
	
	
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月13日
	 * @TODO 根据平台获取信用信息
	 */
	public XinYongInfoModel getCreditInfoByJysc(String bigjys,String jysc){
		String sql="select * from insight_pp_credit_info  where 1=1 "; 
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
