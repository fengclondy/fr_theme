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
		String sql="select vday_ym as month,"+type+" as condition,round(sum(deftotal)/sum(loantotal)*100,2)||'%' as value from insight_xd_defrate"; 
		if(StringUtils.isNotBlank(jys)){
			sql+=" where jysc in "+jys+" and loantotal !=0";
		}else{
			sql+=" where loantotal !=0";
		}
		sql+=" GROUP BY vday_ym,"+type+" order by vday_ym,"+type;
		return dao.find(sql);
	}

}
