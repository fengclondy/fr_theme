package com.qdch.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

//@TableBind(tableName="hub_commerce_ref_jys")
/***
 * demo model
 * @author Tom
 *
 */
public class DemoModel extends Model<DemoModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final DemoModel dao = new DemoModel();
	
	public List<DemoModel> getDemo(){
		
		return dao.find("select * from hub_commerce_ref_jys");
	}
}
