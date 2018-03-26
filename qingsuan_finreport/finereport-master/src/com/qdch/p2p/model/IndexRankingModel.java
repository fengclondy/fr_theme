package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

//@TableBind(tableName="hub_commerce_ref_jys")
/**
 * 
 * @author doush
 * @date 2018年3月26日
 * @TODO 获取信用风险的指数排名
 */
public class IndexRankingModel extends Model<IndexRankingModel>{

	private static final long serialVersionUID = 1L;
	
	public static final IndexRankingModel dao = new IndexRankingModel();
	
	public List<IndexRankingModel> getByLoanCount(String dataSql){
		String sql="select t.jyscmc,sum(t.fvalue) from insight_xd_loan_count t group by jyscmc order by t.jyscmc";
		if(StringUtils.isNotBlank(dataSql)){
			sql+=" where jysc in"+ dataSql+"";
		}
		return dao.find(sql);
	}
	public List<IndexRankingModel> getByLoanAmount(String jys,String type){
		String sql="select t.jyscmc as name,sum(t.fvalue) as value from insight_xd_fkamount t where";
		if(StringUtils.isNotBlank(jys)){
			sql+=" jysc in "+jys+" and";
		} 
		sql+=" custype = "+type+" group by t.jyscmc order by t.jyscmc";
		return dao.find(sql);
	}
	public List<IndexRankingModel> getByYeamount(String jys,String type){
		String sql="select t.jyscmc as name,sum(t.fvalue) as value from insight_xd_yeamount t where";
		if(StringUtils.isNotBlank(jys)){
			sql+=" jysc in "+jys+" and";
		} 
		sql+=" custype = "+type+" group by t.jyscmc order by t.jyscmc";
		//return dao.find(Db.getSqlPara("index.getByDBFS"));
		return dao.find(sql);
	}
	
	
	
}
