package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * 借款人总览弹出--企业注册时间
 * @author lixiaoyi
 * @date 2018年5月7日上午11:18:40
 * @TODO
 */
public class AlertCompanyTimeModel extends Model<AlertCompanyTimeModel>{
	private static final long serialVersionUID = 1L;
	public static final AlertCompanyTimeModel dao =new AlertCompanyTimeModel();
	
	public List<AlertCompanyTimeModel> gainTime(String name){
		String sql="SELECT jysc,jysinfo,vday_ym,SUM (COALESCE(nums :: NUMERIC, 0)) AS nums"
				+ " FROM insight_pp_create_count  where 1=1";
		
		if (StringUtils.isNotBlank(name)) {
			sql+=" and jysinfo='"+name+"'";
		}
		sql+=" GROUP BY jysc,jysinfo,vday_ym";
		return dao.find(sql);
	}

}
