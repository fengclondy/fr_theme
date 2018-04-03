package com.qdch.xd.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * 
 * @todo   交易所信息 /市场名称
 * @time   2018年4月2日14:59:19
 * @author wf
 */
public class ExchangeInfoModel extends Model<ExchangeInfoModel>{
	private static final long serialVersionUID = 1L;
	public static final ExchangeInfoModel dao = new ExchangeInfoModel();



	public List<ExchangeInfoModel> getList(){
		String sql = "SELECT jys,jysinfo,jysmc,company_name,enterprise_id,modifydate from hub_commerce_ref_jys ";
		return dao.find(sql);
	}




}
