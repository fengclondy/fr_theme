package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fr.function.MOD;
import com.jfinal.plugin.activerecord.Model;
/**
 * 工商-静态-招聘
 * @author lixiaoyi
 * @date 2018年4月20日下午2:35:09
 * @TODO
 */
public class CoComjobModel extends Model<CoComjobModel>{
	private static final long serialVersionUID = 1L;
	public static final CoComjobModel dao =new CoComjobModel();
	
	public  List<CoComjobModel> getJob(String name){
		String sql="select * from hub_static_company_job_info where 1=1";
		if(StringUtils.isNotBlank(name)){
			sql+=" and company_name='"+name+"'";
		}
		return dao.find(sql);
	}
}
