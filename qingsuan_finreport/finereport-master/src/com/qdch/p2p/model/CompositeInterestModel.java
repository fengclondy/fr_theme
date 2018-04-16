package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 获取平台项目画像综合利率
* @author doushuihai  
* @date 2018年4月12日上午10:04:19  
* @TODO
 */
public class CompositeInterestModel extends Model<CompositeInterestModel> {

	/** 
	* @Fields serialVersionUID : TODO 
	*/  
	private static final long serialVersionUID = 1L;
	public static final CompositeInterestModel dao=new CompositeInterestModel();
	public List<CompositeInterestModel> getPlatformComInterest(String dataSql,String jyscode){	
		String sql="select avg(interest) as interestNum,vday_ym,jysc from insight_pp_interest  where 1=1 and interest != 0 and interest is not null";
		
		if(StringUtils.isNotBlank(dataSql)){
			sql+="  and jysc in"+ dataSql+"";
		}
		if(StringUtils.isNotBlank(jyscode)){
			sql+="  and jysc = '"+ jyscode+"'";
		}
		sql+=" group by vday_ym,interest,jysc order by vday_ym,interest,jysc";
		return dao.find(sql);
	}
	public List<CompositeInterestModel> getIndustryComInterest(String dataSql,String jyscode){	
		String sql="select vday_ym,jysc,avg(interest) as interestNum from insight_pp_indust_int where 1=1 and interest != 0 and interest is not null";
		
		if(StringUtils.isNotBlank(dataSql)){
			sql+="  and jysc in"+ dataSql+"";
		}
		if(StringUtils.isNotBlank(jyscode)){
			sql+="  and jysc = '"+ jyscode+"'";
		}
		
		sql+=" group by vday_ym,jysc,interest order by vday_ym,jysc,interest";
		return dao.find(sql);
	}

}
