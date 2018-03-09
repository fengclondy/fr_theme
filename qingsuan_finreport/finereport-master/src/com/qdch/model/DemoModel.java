package com.qdch.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

public class DemoModel extends Model<DemoModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final DemoModel dao = new DemoModel().dao();
	
	public List<DemoModel> getDemo(){
		return dao.find("select * from hub_commerce_ref_jys");
	}
}
