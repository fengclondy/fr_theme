package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * 招聘信息
 * @author lixiaoyi
 * @date 2018年4月10日
 * @TODO
 */
public class CoJobModel extends Model<CoJobModel> {
	private static final long serialVersionUID = 1L;
	public static final CoJobModel dao = new CoJobModel();
   /**
    * 获取招聘信息
    * @author lixiaoyi
    * @date 2018年4月10日 下午6:16:27
    * @TODO
    */
	public List<CoJobModel> getJob(String name){
		String sql="SELECT T.public_date,T.job_name,T.salary,T.job_locate,T.info_from_site "
				+ "FROM public.hub_commerce_co_job_info T LEFT JOIN hub_commerce_enterprise n ON T .company_name = n. NAME ";
		if(StringUtils.isNotBlank(name)){
			sql+="WHERE T.company_name = '"+ name+"'";
		}
				
		return dao.find(sql);
		
	}
	
}
