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
public class JyscModel extends Model<JyscModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final JyscModel dao = new JyscModel();
	
	public List<JyscModel> getJysc(){	
		return dao.find("select * from hub_xd_jysc");
	}

	
	
	
}
