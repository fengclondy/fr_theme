package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 失信人信息
 * @author lixiaoyi
 * @date 2018年4月10日
 * @TODO
 */
public class CoDishonestyModel extends Model<CoDishonestyModel>{
	private static final long serialVersionUID = 1L;
	public static final CoDishonestyModel dao = new CoDishonestyModel();
   /**
    * 获取失信人信息
    * @author lixiaoyi
    * @date 2018年4月10日 下午5:26:53
    * @TODO
    */
	public List<CoDishonestyModel> getDishonesty(String name){
		String sql="SELECT T.name,T.legal_person,T.court,T.according_no,T.case_no,T.performance,T.duty,T.group_no,"
				+ "T.public_date,T.province,T.create_date,T.according_court "
				+ "FROM hub_commerce_dishonesty T LEFT JOIN hub_commerce_enterprise n ON T .name = n. NAME ";
		if(StringUtils.isNotBlank(name)){
			sql+="WHERE T.name = '"+ name+"'";
		}		
		
		return  dao.find(sql);
		
	}
}
