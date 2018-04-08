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
	
	public List<IncomeAndLossrateModel> getIncomeAndLoss(String bigjys,String jys){
		String sql="SELECT COALESCE(t1.vday_ym,t2.vday_ym) as month,COALESCE(t1.jyscmc,t2.jyscmc) as jyscmc,round(t1.rate1,4)*100 as value1,round(t2.rate2,4)*100 as value2 "
				+ "FROM (SELECT p1.jysc,p1.jyscmc,p1.vday_ym,(SELECT SUM(rtotal) FROM insight_xd_income "
				+ "WHERE vday_ym<=p1.vday_ym AND jysc=p1.jysc )::NUMERIC/(SELECT SUM(stotal) FROM insight_xd_income "
				+ "WHERE vday_ym<=p1.vday_ym AND jysc=p1.jysc AND rinte<>0 ) AS rate1 FROM insight_xd_income p1 where 1=1 ";
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and p1.jysc in "+ bigjys;
		}
		if(StringUtils.isNotBlank(jys)){
			sql+=" and p1.jysc = '"+jys+"'";
		}
				sql+= "GROUP BY p1.jysc,p1.jyscmc,p1.vday_ym) t1 "
				+ "FULL JOIN (SELECT p2.jysc,p2.jyscmc,p2.vday_ym,(SELECT SUM(ssje) FROM insight_xd_lossrate "
				+ "WHERE vday_ym<=p2.vday_ym AND jysc=p2.jysc )::NUMERIC/(SELECT SUM(yqje) FROM insight_xd_lossrate "
				+ "WHERE vday_ym<=p2.vday_ym AND jysc=p2.jysc AND yqje<>0 ) AS rate2 FROM insight_xd_lossrate p2 where 1=1 ";
				if(StringUtils.isNotBlank(bigjys)){
					sql+=" and p2.jysc in "+ bigjys;
				}
				if(StringUtils.isNotBlank(jys)){
					sql+=" and p2.jysc = '"+jys+"'";
				}
				sql+= "GROUP BY p2.jysc,p2.jyscmc,p2.vday_ym) t2 ON t1.jysc=t2.jysc AND t1.vday_ym=t2.vday_ym order by month asc";
		return dao.find(sql);
		
		
	}
	
	
	
	
}