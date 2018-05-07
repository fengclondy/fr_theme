package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * 借款人总览弹出——个人收入分布
 * @author lixiaoyi
 * @date 2018年5月7日上午11:13:23
 * @TODO
 */
public class AlertPersonIncomeModel extends Model<AlertPersonIncomeModel> {
	private static final long serialVersionUID = 1L;
	
	public static final AlertPersonIncomeModel dao =new AlertPersonIncomeModel();
	
	public  List<AlertPersonIncomeModel> gainIncome(String name){
		
		String sql="SELECT jysc,jysinfo,income,SUM (COALESCE(nums :: NUMERIC, 0)) AS nums"
				+ " FROM insight_pp_income_count  where 1=1";
		if (StringUtils.isNotBlank(name)) {
			sql+=" and jysinfo='"+name+"'";
		}
		sql+=" GROUP BY jysc,jysinfo,income";
		return dao.find(sql);
	}
	

}
