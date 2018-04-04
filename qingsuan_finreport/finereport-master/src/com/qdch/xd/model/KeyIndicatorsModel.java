package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

//@TableBind(tableName="hub_commerce_ref_jys")
/**
 * 获取业务总览的关键指标排名
* @author doushuihai  
* @date 2018年4月2日上午10:45:57  
* @TODO
 */
public class KeyIndicatorsModel extends Model<KeyIndicatorsModel>{

	private static final long serialVersionUID = 1L;
	
	public static final KeyIndicatorsModel dao = new KeyIndicatorsModel();

	public List<KeyIndicatorsModel> getByYeamount(String bigjys){
		String sql="select t.jyscmc as name,round(sum(t.fvalue)/10000,2) as value from insight_xd_yeamount t where vday=(select max(vday) from insight_xd_yeamount)";
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" jysc in "+bigjys;
		} 
		sql+=" group by t.jyscmc order by value,t.jyscmc";
		//return dao.find(Db.getSqlPara("index.getByDBFS"));
		return dao.find(sql);
	}
	public List<KeyIndicatorsModel> getByAmount(String bigjys){
		String sql="select t.jysc,t.jyscmc as name,round(sum(t.fvalue)/10000,2) as value from insight_xd_fkamount t where 1=1 ";
		String sql2="select jysc,jyscmc as name,round(SUM(fvalue),0) as value from insight_xd_fkamount WHERE vday>=to_char(now()-INTERVAL '31 day','yyyymmdd') and ";
		if(StringUtils.isNotBlank(bigjys)){
			sql+="and jysc in "+bigjys;
		} 
		sql+=" GROUP BY jysc,jyscmc order by jysc,jyscmc";
		return dao.find(sql);
	}
	public List<KeyIndicatorsModel> getByDayAmount(String bigjys){
		String sql="select t.jyscmc as name,round(sum(t.fvalue)/10000/(SELECT COUNT(t1.vday) FROM (SELECT vday from insight_xd_fkamount WHERE jysc=t.jysc GROUP BY vday) t1),2) as value from insight_xd_fkamount t where 1=1 ";
		if(StringUtils.isNotBlank(bigjys)){
			sql+="and jysc in "+bigjys;
		} 
		sql+=" group by t.jysc ,t.jyscmc order by t.jyscmc";
		return dao.find(sql);
	}
	public List<KeyIndicatorsModel> getByThreeRuralIssue(String bigjys){
		String sql="SELECT t1.vday,t1.jysc,t1.jyscmc,SUM(CASE WHEN loanobject='410003' THEN fvalue ELSE 0 END)/SUM(fvalue) AS nums FROM insight_xd_yeamount t1 WHERE vday=(SELECT max(vday) FROM insight_xd_yeamount) ";
		if(StringUtils.isNotBlank(bigjys)){
			sql+="and jysc in "+bigjys;
		} 
		sql+=" GROUP BY jysc,t1.vday ,t1.jyscmc";
		return dao.find(sql);
	}

	
	
	
}
