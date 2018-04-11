package com.qdch.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
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
	public List<DemoModel> getTest(){
		return dao.find(Db.getSqlPara("test.test"));
	}
	
	public List<DemoModel> getJys(){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("jys", "0002");
		return dao.find(Db.getSqlPara("test.getByJys",map));
	}
	
	public List<DemoModel> getLog(HashMap<String, String> map){
		return dao.find(Db.getSqlPara("log.logDetail",map));
	}
	
	public List<DemoModel> getLog2(HashMap<String, String> map){
		return dao.find(Db.getSqlPara("log.logIn",Kv.by("map", map)));
	}
	public List<DemoModel> getLog3(ArrayList<String> list){
		return dao.find(Db.getSqlPara("log.logIn2",Kv.by("list", list)));
	}
}
