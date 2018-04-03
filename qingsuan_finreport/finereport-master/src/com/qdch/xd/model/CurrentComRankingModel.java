package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

//@TableBind(tableName="hub_commerce_ref_jys")
/**
 * 获取业务总览的当前竞争力排名
* @author doushuihai  
* @date 2018年4月2日下午3:32:39  
* @TODO
 */
public class CurrentComRankingModel extends Model<CurrentComRankingModel>{

	private static final long serialVersionUID = 1L;
	
	public static final CurrentComRankingModel dao = new CurrentComRankingModel();

	public List<CurrentComRankingModel> getCurrentComRanking(String bigjys){
		String sql="select jysc,jyscmc,jzl from insight_xd_scability where vday_ym=(select max(vday_ym) from insight_xd_scability) ";
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and jysc in "+bigjys;
		} 
		sql+=" GROUP BY jysc,jyscmc,jzl order by jysc";
		//return dao.find(Db.getSqlPara("index.getByDBFS"));
		return dao.find(sql);
	}
	
	
	
	
}
