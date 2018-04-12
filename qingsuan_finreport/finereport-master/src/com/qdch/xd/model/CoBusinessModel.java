package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * 经营异常
 * @author lixiaoyi
 * @date 2018年4月10日
 * @TODO
 */
public class CoBusinessModel extends Model<CoBusinessModel> {
	private static final long serialVersionUID = 1L;
	public static final CoBusinessModel dao = new CoBusinessModel();
    /**
     * 获取经营异常信息
     * @author lixiaoyi
     * @date 2018年4月10日 下午6:06:48
     * @TODO
     */
	public List<CoBusinessModel> getBusiness(String name){
		String sql="SELECT T.in_date,T.in_reason,T.in_authority,T.out_date,T.out_reaosn,T.out_authority"
				+ " FROM hub_commerce_co_business_exception T "
				+ "LEFT JOIN hub_commerce_enterprise n ON T .company_name = n. NAME ";
		if(StringUtils.isNotBlank(name)){
			sql+="WHERE T.company_name = '"+ name+"'";
		}
				
		return dao.find(sql);
		
	}
}
