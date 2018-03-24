package com.qdch.p2p.model;

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
public class IndexRankingModel extends Model<IndexRankingModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final IndexRankingModel dao = new IndexRankingModel();
	
	public List<IndexRankingModel> getByLoanCount(){	
		return dao.find(Db.getSqlPara("index.getByLoanCount"));
	}
	public List<IndexRankingModel> getByLoanAmount(String jys,String type){
		String sql="select t.jyscmc as name,sum(t.fvalue) as value from insight_xd_fkamount t where ";
		if(StringUtils.isNotBlank(jys)){
			sql+=" jysc in("+jys+") and";
		} 
		sql+=" custype = "+type+" group by t.jyscmc order by t.jyscmc";
		//return dao.find(Db.getSqlPara("index.getByDBFS"));
		return dao.find(sql);
	
		/*return dao.find(Db.getSqlPara("test.getByLoanAmount",map));*/
	}
	public List<IndexRankingModel> getByYeamount(HashMap<String, String> map){
		return dao.find(Db.getSqlPara("test.getByYeamount",map));
	}
	
	
	
}
