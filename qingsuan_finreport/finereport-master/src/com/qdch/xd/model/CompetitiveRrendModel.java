package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

//@TableBind(tableName="hub_commerce_ref_jys")
/**
 * 获取业务总览的当前竞争力排名
* @author doushuihai  
* @date 2018年4月2日下午3:32:39  
* @TODO
 */
public class CompetitiveRrendModel extends Model<CompetitiveRrendModel>{

	private static final long serialVersionUID = 1L;
	
	public static final CompetitiveRrendModel dao = new CompetitiveRrendModel();

	public List<CompetitiveRrendModel> getCompetitiveRrend(String bigjys,String jyscode,String type){

		String sql="select jyscmc as condition from insight_xd_scability where 1=1 ";
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and jysc in "+bigjys;
		} 
		if(StringUtils.isNotBlank(jyscode)){
			sql+=" and jysc = '"+jyscode+"'";
		}
		sql+=" group by jyscmc order by jyscmc";
		List<CompetitiveRrendModel> lines=dao.find(sql);
		for(CompetitiveRrendModel model:lines){
			String innerSql="select vday_ym,jysc,jyscmc,"+type+" as zhibiao from insight_xd_scability where 1=1 and jyscmc='"+model.get("condition")+"' ";
			innerSql+="group by vday_ym,jysc,jyscmc,"+type
					+ " order by vday_ym,jysc,jyscmc,"+type;
			List<CompetitiveRrendModel> list=dao.find(innerSql);
			model.put("data", list);
		}
		return lines;
	}
	
	
	
	
}
