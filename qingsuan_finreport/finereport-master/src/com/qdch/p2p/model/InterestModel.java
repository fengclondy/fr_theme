package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 获取平台利率期限类型以动态展示在条件下拉框
* @author doushuihai  
* @date 2018年4月10日上午10:21:25  
* @TODO
 */
public class InterestModel extends Model<InterestModel> {

	/** 
	* @Fields serialVersionUID : TODO 
	*/  
	private static final long serialVersionUID = 1L;
	public static final InterestModel dao=new InterestModel();
	public List<InterestModel> getRangeType(String dataSql){	
		String sql="select range_type from insight_pp_interest  where 1=1 ";
		
		if(StringUtils.isNotBlank(dataSql)){
			sql+="  and jysc in"+ dataSql+"";
		}
		sql+="group by range_type order by range_type";
		return dao.find(sql);
	}
	public List<InterestModel> getInterest(String dataSql,String jyscode,String type){	
		String sql="select vday,jysc,interest from insight_pp_interest where 1=1 ";
		
		if(StringUtils.isNotBlank(dataSql)){
			sql+="  and jysc in"+ dataSql+"";
		}
		if(StringUtils.isNotBlank(jyscode)){
			sql+="  and jysc = '"+ jyscode+"'";
		}
		sql+=" and range_type='"+type+"'";
		sql+=" group by vday,jysc,interest order by vday,jysc,interest";
		return dao.find(sql);
	}

}
