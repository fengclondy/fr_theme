package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 借款人总览弹出--个人公司占比
 * @author lixiaoyi
 * @date 2018年5月7日上午10:01:12
 * @TODO
 */
public class AlertPersonCompanyModel extends Model<AlertPersonCompanyModel>{
	private static final long serialVersionUID = 1L;
	public static final AlertPersonCompanyModel dao =new AlertPersonCompanyModel();
	
	public List<AlertPersonCompanyModel> gainPersonCompany(String name){
		String sql="SELECT jysc,jysinfo,jkf_type,SUM (COALESCE(nums :: NUMERIC, 0)) AS nums"
				+ " FROM insight_pp_jkf_rate  where 1=1";
		if (StringUtils.isNotBlank(name)) {
			sql+=" and jysinfo='"+name+"'";
		}
		sql+=" GROUP BY jysc,jysinfo,jkf_type";
		return dao.find(sql);
		
	}

}
