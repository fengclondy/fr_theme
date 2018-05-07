package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 *借款人总览弹出--企业注册分布
 * @author lixiaoyi
 * @date 2018年5月7日上午11:28:50
 * @TODO
 */
public class AlertCompanyRegisterModel extends Model<AlertCompanyRegisterModel>{
	private static final long serialVersionUID = 1L;
	public static final AlertCompanyRegisterModel dao=new AlertCompanyRegisterModel();
	
	public List<AlertCompanyRegisterModel> gainRegister(String name){
		String sql="SELECT jysc,jysinfo,zczb,SUM (COALESCE(nums :: NUMERIC, 0)) AS nums"
				+ " FROM insight_pp_zczb_count  where 1=1";
		if (StringUtils.isNotBlank(name)) {
			sql+=" and jysinfo='"+name+"'";
		}
		sql+=" GROUP BY jysc,jysinfo,zczb";
		return dao.find(sql);
	}

}
