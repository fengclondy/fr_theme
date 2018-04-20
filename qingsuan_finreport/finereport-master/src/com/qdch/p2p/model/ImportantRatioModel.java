package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 获取字典平台信息
* @author doushuihai  
* @date 2018年4月10日上午10:21:25  
* @TODO
 */
public class ImportantRatioModel extends Model<ImportantRatioModel> {

	/** 
	* @Fields serialVersionUID : TODO 
	*/  
	private static final long serialVersionUID = 1L;
	public static final ImportantRatioModel dao=new ImportantRatioModel();
	public List<ImportantRatioModel> getImportantRatio(String dataSql,String jys){	
		String sql="select vday"
				+ ",jyscmc,jysc,yql,(yqje::NUMERIC/10000) as yqje,(dcje::NUMERIC/10000) as dcje,dcbs  from insight_pp_netinfo where 1=1  ";
		
		if(StringUtils.isNotBlank(dataSql)){
			sql+=" and  jysc in"+ dataSql+"";
		}
		if(StringUtils.isNotBlank(jys)){
			sql+=" and jysc='"+ jys+"'  ";
			
		}
		sql+=" and vday=(select max(vday) from insight_pp_netinfo where jysc ='"+jys+"')";
		sql+=" group by vday,jyscmc,jysc,yql,yqje,dcje,dcbs order by vday,jyscmc,jysc,yql,yqje,dcje,dcbs";
		return dao.find(sql);
	}

	

}
