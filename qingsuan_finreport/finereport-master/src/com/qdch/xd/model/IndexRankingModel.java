package com.qdch.xd.model;

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
	
	public List<IndexRankingModel> getByLoanCount(String dataSql,String jyscode){
		String sql="select t.jyscmc,sum(t.fvalue) from insight_xd_loan_count t where 1=1 " ;
		if(StringUtils.isNotBlank(dataSql)){
			sql+=" and jysc in "+ dataSql;
		}
		if(StringUtils.isNotBlank(jyscode)){
			sql+=" and jysc = '"+jyscode+"'";
		}
		sql+=" group by jyscmc order by t.jyscmc";
		
		return dao.find(sql);
	}
	public List<IndexRankingModel> getByLoanAmount(String bigjys,String jyscode,String type){
		String sql="select t.jyscmc as name,sum(t.fvalue) as value from insight_xd_fkamount t where 1=1 ";
		if(StringUtils.isNotBlank(bigjys)){
			sql+="and jysc in "+bigjys;
		} 
		if(StringUtils.isNotBlank(jyscode)){
			sql+=" and jysc = '"+jyscode+"'";
		}
		sql+=" and custype = "+type+" group by t.jyscmc order by t.jyscmc";
		return dao.find(sql);
	}
	public List<IndexRankingModel> getByYeamount(String bigjys,String jyscode,String type){
		String sql="select t.jyscmc as name,sum(t.fvalue) as value from insight_xd_yeamount t where vday=(select max(vday) from insight_xd_yeamount)";
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" jysc in "+bigjys;
		} 
		if(StringUtils.isNotBlank(jyscode)){
			sql+=" and jysc = '"+jyscode+"'";
		}
		sql+=" and custype = "+type+" group by t.jyscmc order by t.jyscmc";
		//return dao.find(Db.getSqlPara("index.getByDBFS"));
		return dao.find(sql);
	}
	
	
	
}
