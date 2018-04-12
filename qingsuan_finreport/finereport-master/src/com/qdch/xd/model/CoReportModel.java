package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * 企业年报-财务信息
 * @author lixiaoyi
 * @date 2018年4月10日上午11:45:22
 * @TODO
 */
public class CoReportModel extends Model<CoReportModel>{
	private static final long serialVersionUID = 1L;
	public static final CoReportModel dao = new CoReportModel();

	/**
	 * 获取企业年报信息
	 * @author lixiaoyi
	 * @date 2018年4月10日 下午4:46:59
	 * @TODO
	 */
	public List<CoReportModel> getReport(String name){
		String sql="SELECT T.asset_num,T.turnover,T.main_income,T.tax_num,T.employee_num,T.owner_interest,T.profit_num,T.retained_profit,T.debt_num, "
				+ "m.REGISTER_CODE,m.WEBSITES FROM hub_commerce_co_finance_state T "
				+ "LEFT JOIN hub_commerce_enterprise n ON T .company_name = n. NAME "
				+ "LEFT JOIN hub_commerce_company_info m ON ";
		   if(StringUtils.isNotBlank(name)){
			sql+="m.company_name ='"+ name+"' WHERE T .company_name ='"+name+"'";
		     }
			return dao.find(sql);
		
	}
}
