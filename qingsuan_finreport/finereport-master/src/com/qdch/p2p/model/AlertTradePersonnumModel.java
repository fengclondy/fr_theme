package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * 借款人总览弹出--行业人数分布
 * @author lixiaoyi
 * @date 2018年5月7日上午10:08:50
 * @TODO
 */
public class AlertTradePersonnumModel extends Model<AlertTradePersonnumModel> {
	private static final long serialVersionUID = 1L;
	public static final AlertTradePersonnumModel dao =new AlertTradePersonnumModel();
	
	public List<AlertTradePersonnumModel> gainTrdae(String name){
		String  sql="SELECT jysc,jysinfo,indust,SUM (COALESCE(nums :: NUMERIC, 0)) AS nums "
				+ "FROM insight_pp_indust_count  where 1=1";
		
		if (StringUtils.isNotBlank(name)) {
			sql+=" and  jysinfo='"+name+"'";
		}
		sql+=" GROUP BY jysc,jysinfo,indust";
		return dao.find(sql);
		
	}

}
