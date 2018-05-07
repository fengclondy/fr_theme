package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * 借款人总览弹出--个人年龄分布
 * @author lixiaoyi
 * @date 2018年5月7日上午11:04:15
 * @TODO
 */
public class AlertPersonAgeModel extends Model<AlertPersonAgeModel> {

	private static final long serialVersionUID = 1L;
	public static final AlertPersonAgeModel dao=new AlertPersonAgeModel();
	
	public List<AlertPersonAgeModel> gainAge(String name){
		String sql="SELECT jysc,jysinfo,age,SUM (COALESCE(nums :: NUMERIC, 0)) AS nums"
				+ " FROM insight_pp_age_count where 1=1 ";
		if (StringUtils.isNotBlank(name)) {
			sql+=" and jysinfo='"+name+"'";
		}
		sql+=" GROUP BY jysc,jysinfo,age";
		return dao.find(sql);
	}
}
