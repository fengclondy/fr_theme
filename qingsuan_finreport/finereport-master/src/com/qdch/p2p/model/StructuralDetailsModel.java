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
public class StructuralDetailsModel extends Model<StructuralDetailsModel> {

	/** 
	* @Fields serialVersionUID : TODO 
	*/  
	private static final long serialVersionUID = 1L;
	public static final StructuralDetailsModel dao=new StructuralDetailsModel();
	public List<StructuralDetailsModel> getStructuralDetails(String dataSql,String jys){	
		String sql="select jyscmc,jysc,xmlx,ywlx,pjlv,je,jezb,pjqx from insight_pp_iterm_struct where 1=1 ";		
		if(StringUtils.isNotBlank(dataSql)){
			sql+=" and jysc in"+ dataSql+"";
		}
		if(StringUtils.isNotBlank(jys)){
			sql+=" and jysc='"+ jys+"'  ";			
		}
		sql+=" and vday=(select max(vday) from insight_pp_iterm_struct where jysc ='"+jys+"')";
		sql+=" order by jyscmc,jysc,xmlx,ywlx,pjlv,je,jezb,pjqx";
		return dao.find(sql);
	}

	

}
