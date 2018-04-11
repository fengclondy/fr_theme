package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 被执行人信息
 * @author lixiaoyi
 * @date 2018年4月10日
 * @TODO
 */
public class CoExecutorModel extends Model<CoExecutorModel> {
	private static final long serialVersionUID = 1L;
	public  static final CoExecutorModel dao = new CoExecutorModel();
   /**
    * 获取被执行人信息
    * @author lixiaoyi
    * @date 2018年4月10日 下午5:37:40
    * @TODO
    */
	public List<CoExecutorModel> getExecutor(String name){
		String sql="SELECT T.create_date,T.case_no,T.exec_object,T.court "
				+ "FROM hub_commerce_bzx_person T LEFT JOIN hub_commerce_enterprise n ON T.name = n. NAME ";
		if(StringUtils.isNotBlank(name)){
			sql+="WHERE T.name = '"+ name+"'";
		}
				
		return dao.find(sql);
	}
}
