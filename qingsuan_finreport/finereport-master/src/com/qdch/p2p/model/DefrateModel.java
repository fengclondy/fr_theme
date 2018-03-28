package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

//@TableBind(tableName="hub_commerce_ref_jys")
/**
 * 
 * @author doush
 * @date 2018年3月26日
 * @TODO 获取不良率
 */
public class DefrateModel extends Model<DefrateModel>{

	private static final long serialVersionUID = 1L;
	
	public static final DefrateModel dao = new DefrateModel();
	
	public List<DefrateModel> getDefrate(String jys,String type){
		/*
		String sql="select vday_ym as month,"+type+" as condition,round(sum(deftotal)/sum(loantotal)*100,2)||'%' as value from insight_xd_defrate"; 
		if(StringUtils.isNotBlank(jys)){
			sql+=" where jysc in "+jys+" and loantotal !=0";
		}else{
			sql+=" where loantotal !=0";
		}
		sql+=" GROUP BY vday_ym,"+type+" order by vday_ym,"+type;
		return dao.find(sql);*/
		
		
	/*	String sql="select dbfs as name from insight_xd_defrate where dbfs is not null group by dbfs order by dbfs";
		List<DefrateModel> lines=dao.find(sql);
		for(DefrateModel model:lines){
			String innerSql="SELECT * from insight_xd_defrate where dbfs='"+model.get("name")+"'  ";
			List<DefrateModel> list=dao.find(innerSql);
			model.put("data", list);
		}
		return lines;*/
		String sql="select "+type+" as condition from insight_xd_defrate where "+type+" is not null group by "+type
				+" order by "+type;
		List<DefrateModel> lines=dao.find(sql);
		for(DefrateModel model:lines){
			String innerSql="select vday_ym as month,round(sum(deftotal)/sum(loantotal),2) as value from insight_xd_defrate where loantotal !=0 and "
					+type+" = '"+model.get("condition")+"'"+" group by vday_ym,"+type+" order by vday_ym";
			List<DefrateModel> list=dao.find(innerSql);
			model.put("data", list);
		}
		return lines;
	}

}
