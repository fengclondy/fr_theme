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
		String sql="select t1.name,t1.company_name,t1.company_id,t1.position,"
				+ "(select count(1) from hub_commerce_co_change_log where company_id=t1.company_id) "
                +"+(select count(1) from hub_commerce_judgment where title like 't1.company_name%') "
+"+(select count(1) from hub_commerce_court_announce where company_id=t1.company_id) "
+"+(select count(1) from hub_commerce_dishonesty where name=t1.company_name) "
+"+(select count(1) from hub_commerce_bzx_person  where name=t1.company_name) "
+"+(select count(1) from hub_commerce_co_business_exception where company_id=t1.company_id)"
+"+(select count(1) from hub_commerce_co_penalty where company_id=t1.company_id) "
+"as total_num from hub_commerce_co_senior_manager t1 ";
		if(StringUtils.isNotBlank(name)){
			sql+="where t1.name='"+name+"'";
			}
   sql+="GROUP BY t1.name,t1.company_name,t1.company_id,t1.position";
				
		return dao.find(sql);
	}
	
}
