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
public class IncomeAndLossrateModel extends Model<IncomeAndLossrateModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final IncomeAndLossrateModel dao = new IncomeAndLossrateModel();
	
	public List<IncomeAndLossrateModel> getIncome(){	
		return dao.find("select * from insight_xd_income");
	}
	public List<IncomeAndLossrateModel> getLossrate(){	
		return dao.find("select * from insight_xd_lossrate");
	}
	
	
	
	
}
