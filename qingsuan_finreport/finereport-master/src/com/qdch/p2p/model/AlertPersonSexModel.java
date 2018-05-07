package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * 借款人总览弹出--个人性别分布
 * @author lixiaoyi
 * @date 2018年5月7日上午10:18:48
 * @TODO
 */
public class AlertPersonSexModel extends Model<AlertPersonSexModel> {
	private static final long serialVersionUID = 1L;
	public static final AlertPersonSexModel dao =new AlertPersonSexModel();
	
	public List<AlertPersonSexModel> gainSex(String name){
		String sql="SELECT jysc,jysinfo,sex,SUM (COALESCE(nums :: NUMERIC, 0)) AS nums"
				+ " FROM insight_pp_sex_count where 1=1";
		if (StringUtils.isNotBlank(name)) {
			sql+=" and jysinfo='"+name+"'";
		}
		sql+=" GROUP BY jysc,jysinfo,sex";
		return dao.find(sql);
		
	}

}
