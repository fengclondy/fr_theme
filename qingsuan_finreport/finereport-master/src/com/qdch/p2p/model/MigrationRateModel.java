package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

//@TableBind(tableName="hub_commerce_ref_jys")
/**
 * 
 * @author doush
 * @date 2018年3月26日
 * @TODO 获取不良率
 */
public class MigrationRateModel extends Model<MigrationRateModel>{

	private static final long serialVersionUID = 1L;
	
	public static final MigrationRateModel dao = new MigrationRateModel();
	
	public List<MigrationRateModel> getMigrationRate(String jys,String type){
		String sql="select ym as name,"+type+" as value from insight_xd_mobiratio"; 
		if(StringUtils.isNotBlank(jys)){
			sql+=" where jysc in "+jys;
		}
		sql+=" order by ym";
		return dao.find(sql);
	}

}
