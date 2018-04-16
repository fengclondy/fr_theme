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
public class ProjectStructureModel extends Model<ProjectStructureModel> {

	/** 
	* @Fields serialVersionUID : TODO 
	*/  
	private static final long serialVersionUID = 1L;
	public static final ProjectStructureModel dao=new ProjectStructureModel();
	public List<ProjectStructureModel> getProjectStructure(String dataSql,String jys){	
		String sql="select vday"
				+ ",jyscmc,jysc,iterm_type,iterm_term,sum(iterm_value) as value from insight_pp_iterm_count where 1=1  and vday=(select max(vday) from insight_pp_iterm_count) ";
		
		if(StringUtils.isNotBlank(dataSql)){
			sql+=" and jysc in"+ dataSql+"";
		}
		if(StringUtils.isNotBlank(jys)){
			sql+="and jysc='"+ jys+"'  ";
			
		}
		sql+="group by vday,jyscmc,jysc,iterm_type,iterm_term,iterm_value order by vday,jyscmc,jysc,iterm_type,iterm_term,iterm_value";
		return dao.find(sql);
	}

	

}
