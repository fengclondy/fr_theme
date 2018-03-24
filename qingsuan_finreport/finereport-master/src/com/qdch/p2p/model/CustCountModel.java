package com.qdch.p2p.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
public class CustCountModel extends Model<CustCountModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final CustCountModel dao = new CustCountModel();
	
	public List<CustCountModel> getByCustCount(HashMap<String, String> map){
		return dao.find(Db.getSqlPara("test.getByDBFS",map));
	}

}
