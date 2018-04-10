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
public class ManagementRiskListModel extends Model<ManagementRiskListModel>{

	private static final long serialVersionUID = 1L;
	
	public static final ManagementRiskListModel dao = new ManagementRiskListModel();

	public List<ManagementRiskListModel> getManagementRiskList(String bigjys,String jyscode){
		String sql="select jysc,jyscmc,jzl from insight_xd_scability where vday_ym=(select max(vday_ym) from insight_xd_scability) ";
		String sql2="select t.jyscmc,sum(t.fvalue) from insight_xd_loan_count t where 1=1 " ;
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and jysc in "+ bigjys;
		}
		if(StringUtils.isNotBlank(jyscode)){
			sql+=" and jysc = '"+jyscode+"'";
		}
		sql+=" GROUP BY jysc,jyscmc,jzl order by jysc";
		//return dao.find(Db.getSqlPara("index.getByDBFS"));
		return dao.find(sql);
	}
	
	
	
	
}
