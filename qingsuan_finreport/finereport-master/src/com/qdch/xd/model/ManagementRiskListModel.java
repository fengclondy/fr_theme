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

	public List<ManagementRiskListModel> getManagementRiskList(String bigjys,String jys){
		String sql="select fxsj_id,bjsj,fxlb,fxzb,jgmc from hub_fxsj t LEFT JOIN hub_xd_jysc t2 on t.jgmc=t2.jyscmc where 1=1 and fxlb='信用风险' ";
		
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and t2.jysc in "+ bigjys;
		}
		if(StringUtils.isNotBlank(jys)){
			sql+=" and t2.jysc = '"+jys+"'";
		}
		sql+=" order by t.bjsj DESC limit 30 ";
		//return dao.find(Db.getSqlPara("index.getByDBFS"));
		return dao.find(sql);
	}
	
	
	
	
}
