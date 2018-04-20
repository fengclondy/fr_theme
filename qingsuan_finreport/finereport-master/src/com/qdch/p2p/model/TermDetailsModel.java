package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 获取项目结构详情
* @author doushuihai  
* @date 2018年4月10日上午10:21:25  
* @TODO
 */
public class TermDetailsModel extends Model<TermDetailsModel> {

	/** 
	* @Fields serialVersionUID : TODO 
	*/  
	private static final long serialVersionUID = 1L;
	public static final TermDetailsModel dao=new TermDetailsModel();
	public List<TermDetailsModel> getTermDetails(String dataSql,String jys){	
		String sql="select jyscmc,jysc,term,pjlv,je,(jezb::NUMERIC*100) as jezb,hkfs from insight_pp_term_distribute where 1=1  ";		
		if(StringUtils.isNotBlank(dataSql)){
			sql+=" and jysc in"+ dataSql+"";
		}
		if(StringUtils.isNotBlank(jys)){
			sql+=" and jysc='"+ jys+"'  ";			
		}
		sql+=" and vday=(select max(vday) from insight_pp_term_distribute where jysc ='"+jys+"')";
		sql+=" order by term,jyscmc,jysc,pjlv,je,jezb,hkfs";
		return dao.find(sql);
	}

	

}
