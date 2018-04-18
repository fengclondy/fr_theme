package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * 获取平台项目画像-集中度
* @author doushuihai  
* @date 2018年4月18日下午2:10:49  
* @TODO
 */
public class ConcentrationRatioModel extends Model<ConcentrationRatioModel> {

	/** 
	* @Fields serialVersionUID : TODO 
	*/  
	private static final long serialVersionUID = 1L;
	public static final ConcentrationRatioModel dao=new ConcentrationRatioModel();
	public List<ConcentrationRatioModel> getConRatio(String dataSql,String jys){	
		String sql="select vday_ym,jysc,hhi_type,hhi_value from insight_pp_hhi_calculate where 1=1 ";
		if(StringUtils.isNotBlank(dataSql)){
			sql+=" and  jysc in"+ dataSql+"";
		}
		if(StringUtils.isNotBlank(jys)){
			sql+=" and jysc='"+ jys+"'  ";
		}
		sql+="and vday_ym=(select max(vday_ym) from insight_pp_hhi_calculate) group by vday_ym,jysc,hhi_type,hhi_value order by vday_ym,jysc,hhi_type,hhi_value";
		return dao.find(sql);
	}

	

}
