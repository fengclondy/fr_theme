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
	
	public List<DefrateModel> getByDBFS(String jys,String type){
		String sql="select substr(t.vday,0,7) as vmonth,t.dbfs,sum(t.loantotal) from insight_xd_defrate_test t where substr(t.vday,0,7) is not null and";
		if(StringUtils.isNotBlank(jys)){
			sql+=" jysc in("+jys+") ";
		}
		sql+="  GROUP BY vmonth,"+type+" ORDER BY "+type+",substr(t.vday,0,7)";
		//return dao.find(Db.getSqlPara("index.getByDBFS"));
		return dao.find(sql);
	}

}
