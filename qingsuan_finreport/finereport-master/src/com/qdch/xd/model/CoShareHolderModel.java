package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fr.report.core.A.S;
import com.jfinal.plugin.activerecord.Model;
/**
 * 股东/对外投资信息
 * @author lixiaoyi
 * @date 2018年4月10日
 * @TODO
 */
public class CoShareHolderModel extends Model<CoShareHolderModel>{
	private static final long serialVersionUID = 1L;
	public static final CoShareHolderModel dao=new CoShareHolderModel();
	/**
	 * 股权结构
	 * @author lixiaoyi
	 * @date 2018年4月10日 下午3:16:55
	 * @TODO
	 */
	public List<CoShareHolderModel> getStock(String name){
		String sql="SELECT distinct t.name,t.company_name from hub_commerce_co_shareholder t ";
				if(StringUtils.isNotBlank(name)){
					sql+="WHERE t.company_name='"+ name+"'";
				}
				sql+="AND t.type='1' limit 1 ";
				return dao.find(sql);
	}
	
	/**
	 * 获取核心人员-自然人信息
	 * @author lixiaoyi
	 * @date 2018年4月10日 下午3:27:21
	 * @TODO
	 */
	public List<CoShareHolderModel> getMainperson(String name){
		String sql="SELECT string_agg(T.NAME,',') FROM hub_commerce_co_shareholder T WHERE T .stockholder_type = '自然人股东' ";
		if(StringUtils.isNotBlank(name)){
			sql+="AND T.company_name='"+ name+"'";
		}		
		return dao.find(sql);
}  
	/**
	 * 获取核心人员-总经理人信息
	 * @author lixiaoyi
	 * @date 2018年4月10日 下午3:30:03
	 * @TODO
	 */
	public List<CoShareHolderModel> getManager(String name){
		String sql="SELECT string_agg(T.NAME,',') FROM hub_commerce_co_shareholder T WHERE T .stockholder_type = '总经理' ";
		if(StringUtils.isNotBlank(name)){
			sql+="AND T.company_name='"+ name+"'";
		}		
		return dao.find(sql);
}
	/**
	 * 获取核心人员-董事信息
	 * @author lixiaoyi
	 * @date 2018年4月10日 下午3:30:39
	 * @TODO
	 */
	public List<CoShareHolderModel> getDong(String name){
		String sql="SELECT string_agg(T.NAME,',') FROM hub_commerce_co_shareholder T WHERE T .stockholder_type = '董事' ";
		if(StringUtils.isNotBlank(name)){
			sql+="AND T.company_name='"+ name+"'";
		}		
		return dao.find(sql);
}
	/**
	 * 获取核心人员-监事信息
	 * @author lixiaoyi
	 * @date 2018年4月10日 下午3:30:58
	 * @TODO
	 */
	public List<CoShareHolderModel> getJian(String name){
		String sql="SELECT string_agg(T.NAME,',') FROM hub_commerce_co_shareholder T WHERE T .stockholder_type = '监事' ";
		if(StringUtils.isNotBlank(name)){
			sql+="AND T.company_name='"+ name+"'";
		}		
		return dao.find(sql);
}
	/**
	 * 获取股东信息
	 * @author lixiaoyi
	 * @date 2018年4月10日 下午3:44:34
	 * @TODO
	 */
	public List<CoShareHolderModel> getStackInfo(String name){
		String sql="SELECT T.name,T.scale,T.pay FROM hub_commerce_co_shareholder T LEFT JOIN hub_commerce_enterprise n ON T .company_name = n. NAME ";
				if(StringUtils.isNotBlank(name)){
					sql+="WHERE T.company_name ='"+ name+"'";
				}	
				sql+="and T.type='1'";
				return dao.find(sql);
	}
	/**
	 * 对外投资信息
	 * @author lixiaoyi
	 * @date 2018年4月10日 下午3:54:37
	 * @TODO
	 */
	public List<CoShareHolderModel> getInvest(String name){
		String  sql="SELECT T.company_name,T.pay,T.scale,m.REGISTER_MONEY,m.LEGAL_PERSON,m.BUSINESS_STATUS,m.PUBLISH_DATE FROM "
				+ "hub_commerce_co_shareholder T LEFT JOIN hub_commerce_enterprise n  ON T .company_name = n. NAME "
				+ "LEFT JOIN hub_commerce_company_info m ON m.company_name = t.name ";
				if(StringUtils.isNotBlank(name)){
					sql+="WHERE T .company_name ='"+ name+"'";
				}	
				sql+="AND T.type = '1'";
				return dao.find(sql);
	}
}