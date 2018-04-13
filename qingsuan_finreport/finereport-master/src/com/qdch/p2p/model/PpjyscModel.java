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
public class PpjyscModel extends Model<PpjyscModel> {

	/** 
	* @Fields serialVersionUID : TODO 
	*/  
	private static final long serialVersionUID = 1L;
	public static final PpjyscModel dao=new PpjyscModel();
	public List<PpjyscModel> getJysc(String dataSql){	
		String sql="select jysc,jyscmc,jysinfo,jyscfl,zt,djrq from hub_pp_jysc where 1=1 ";
		
		if(StringUtils.isNotBlank(dataSql)){
			sql+=" and jysc in"+ dataSql+"";
		}
		sql+="order by jysc";
		return dao.find(sql);
	}

}
