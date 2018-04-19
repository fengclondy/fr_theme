package com.qdch.xd.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

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
	

	public List<IncomeAndLossrateModel> getIncome(String bigjys,String jys){
		String sql="select vday_ym as month1,jysc,round((rtotl_mm/stotl_mm),4)*100 as incomerate from insight_xd_income_amount where 1=1 and stotl_mm != 0 ";
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and jysc in "+ bigjys;
		}
		if(StringUtils.isNotBlank(jys)){
			sql+=" and jysc = '"+jys+"'";
		}
		sql+=" order by vday_ym,jysc";
		return dao.find(sql);
		
		
	}
	public List<IncomeAndLossrateModel> getLoss(String bigjys,String jys){
		String sql="select vday_ym as month2,jysc,round((sum(ssje)/sum(yqje)),4)*100 as lossrate from insight_xd_lossrate where 1=1 and yqje != 0 ";
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and jysc in "+ bigjys;
		}
		if(StringUtils.isNotBlank(jys)){
			sql+=" and jysc = '"+jys+"'";
		}
		sql+=" group by vday_ym,jysc order by vday_ym,jysc";
		return dao.find(sql);
		
		
	}
	
	
	
	
}
