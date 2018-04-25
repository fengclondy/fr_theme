package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * 工商-企业主要人员信息
 * @author lixiaoyi
 * @date 2018年4月10日
 * @TODO
 */
public class CoMainPersonModel extends Model<CoMainPersonModel> {
	private static final long serialVersionUID = 1L;
	public  static final CoMainPersonModel dao =new CoMainPersonModel();
	
	public  List<CoMainPersonModel> getRelact(String name){
		String sql="SELECT t1. NAME,t1.company_name,t1.company_id,t1. holder_type,"
				+ "(SELECT COUNT (1) FROM hub_static_change_log WHERE company_id = t1.company_id) "
				+ "+ (SELECT COUNT (1) FROM hub_static_legal WHERE company_id = t1.company_id) "
				+ "+ (SELECT COUNT (1) FROM hub_static_legal_public WHERE company_id = t1.company_id) "
				+ "+ (SELECT COUNT (1) FROM hub_static_dishonest WHERE NAME = t1.company_name) "
				+ "+ (SELECT COUNT (1) FROM hub_static_bzx_person WHERE NAME = t1.company_name) "
				+ "+ (SELECT COUNT (1) FROM hub_static_operation_exception WHERE company_id = t1.company_id)  "
				+ "AS total_num FROM hub_static_important_person t1 ";
	
		if(StringUtils.isNotBlank(name)){
			sql+="where t1.name='"+name+"'";
			}
   sql+="GROUP BY t1.name,t1.company_name,t1.company_id,t1.holder_type";
				
		return dao.find(sql);
	}
	
}
