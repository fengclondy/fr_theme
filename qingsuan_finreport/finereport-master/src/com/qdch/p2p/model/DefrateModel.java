package com.qdch.p2p.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fr.hailian.service.UserDataFromRoleService;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.qdch.model.DemoModel;

//@TableBind(tableName="hub_commerce_ref_jys")
/***
 * demo model
 * @author Tom
 *
 */
public class DefrateModel extends Model<DefrateModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final DefrateModel dao = new DefrateModel();
	
	public List<DefrateModel> getDefrate(String jys,String type){
		String sql="select vday_ym as month,"+type+",round(SUM(deftotal)/SUM(loantotal)*100,2)||'%' as value from insight_xd_defrate"; 
		if(StringUtils.isNotBlank(jys)){
			sql+=" where jysc in("+jys+") and loantotal !=0";
		}else{
			sql+=" where loantotal !=0";
		}
		sql+=" GROUP BY vday_ym,"+type+" order by vday_ym,"+type;
		//return dao.find(Db.getSqlPara("index.getByDBFS"));
		return dao.find(sql);
	}

}
