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
	public List<ConcentrationRatioModel> getConRatioHHI(String dataSql,String jys){	
		String sql="select vday,jysc,hhi_type,hhi_value from insight_pp_hhi_calculate where 1=1 ";
		if(StringUtils.isNotBlank(dataSql)){
			sql+=" and  jysc in"+ dataSql+"";
		}
		if(StringUtils.isNotBlank(jys)){
			sql+=" and jysc='"+ jys+"'  ";
		}
		sql+="and vday=(select max(vday) from insight_pp_hhi_calculate where jysc ='"+jys+"') group by vday,jysc,hhi_type,hhi_value order by vday,jysc,hhi_type,hhi_value";
		return dao.find(sql);
	}
	public List<ConcentrationRatioModel> getConRatioPro(String dataSql,String jys){	
		String sql="select vday,jysc,rate_type,iterm_value from insight_pp_ledger_rate where 1=1 ";
		if(StringUtils.isNotBlank(dataSql)){
			sql+=" and  jysc in"+ dataSql+"";
		}
		if(StringUtils.isNotBlank(jys)){
			sql+=" and jysc='"+ jys+"'  ";
		}
		sql+=" and vday=(select max(vday) from insight_pp_ledger_rate where jysc ='"+jys+"') group by vday,jysc,rate_type,iterm_value order by vday,jysc,rate_type,iterm_value";
		return dao.find(sql);
	}

	

}
