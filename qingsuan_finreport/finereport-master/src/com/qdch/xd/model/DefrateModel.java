package com.qdch.xd.model;

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
	
	public List<DefrateModel> getDefrate(String bigjys,String jyscode,String type){

		String sql="select "+type+" as condition from insight_xd_defrate where 1=1 and "+type+" is not null ";
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and jysc in "+bigjys;
		} 
		if(StringUtils.isNotBlank(jyscode)){
			sql+=" and jysc = '"+jyscode+"'";
		}
		sql+=" group by "+type+" order by "+type;
		List<DefrateModel> lines=dao.find(sql);
		for(DefrateModel model:lines){
			
			String innerSql="select vday_ym as month,round(sum(deftotal)/sum(loantotal),4)*100 as value from insight_xd_defrate where loantotal !=0 and vday_ym is not null and  "
					+type+" = '"+model.get("condition")+"'";
			if(StringUtils.isNotBlank(bigjys)){
				innerSql+=" and jysc in "+bigjys;
			} 
			if(StringUtils.isNotBlank(jyscode)){
				innerSql+=" and jysc = '"+jyscode+"'";
			}
			innerSql+=" group by vday_ym,"+type+" order by vday_ym";
			List<DefrateModel> list=dao.find(innerSql);
			model.put("data", list);
		}
		return lines;
	}

}
