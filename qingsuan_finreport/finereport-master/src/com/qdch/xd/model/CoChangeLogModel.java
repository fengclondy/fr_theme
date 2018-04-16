package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hyperic.sigar.test.GetPass;

import com.jfinal.plugin.activerecord.Model;
/**
 * 工商企业变更记录
 * @author lixiaoyi
 * @date 2018年4月10日
 * @TODO
 */
public class CoChangeLogModel extends Model<CoChangeLogModel>{
	private static final long serialVersionUID = 1L;
	public static final CoChangeLogModel dao=new CoChangeLogModel();
	/**
	 * 变更记录查询
	 * @author lixiaoyi
	 * @date 2018年4月10日 下午4:11:00
	 * @TODO
	 */
	public List<CoChangeLogModel> getChange(String name){
		String sql="SELECT T .change_date,T .change_object,T .old_log,T .new_log FROM hub_commerce_co_change_log T "
				+ "LEFT JOIN hub_commerce_enterprise n ON T .company_name = n. NAME ";
		if(StringUtils.isNotBlank(name)){
			sql+="WHERE T .company_name ='"+ name+"'";
		}		
		return dao.find(sql);
	}
	
	/**
	 * 获取工商信息变更
	 * @author lixiaoyi
	 * @date 2018年4月11日 上午9:17:21
	 * @TODO
	 */
	public List<CoChangeLogModel> getCommerce(String name){
		String sql="SELECT ROW_NUMBER()OVER() row,T.change_object,T.old_log,T.new_log,T.change_date "
				+ "FROM hub_commerce_co_change_log T "
		        + "LEFT JOIN hub_commerce_enterprise n  ON T .company_name = n. NAME ";
		if(StringUtils.isNotBlank(name)){
			sql+="WHERE T .company_name = '"+ name+"'";
			}	
		sql+="ORDER BY row,T.change_date DESC ";
		return dao.find(sql);
	}
	/**
	 * 名称变更
	 * @author lixiaoyi
	 * @date 2018年4月13日 下午3:52:41
	 * @TODO
	 */
	public List<CoChangeLogModel> getNamechange(String name){
		String sql="SELECT T .change_date,T .guid,T .old_log,T .new_log FROM hub_commerce_co_change_log T "
				+ "LEFT JOIN hub_commerce_enterprise n ON T .company_name = n. NAME ";
		if(StringUtils.isNotBlank(name)){
			sql+="WHERE T .company_name ='"+ name+"' and T.change_object like '%名称变更%'";
		}		
		return dao.find(sql);
	}
	/**
	 * 投资人股权变更
	 * @author lixiaoyi
	 * @date 2018年4月13日 下午4:01:34
	 * @TODO
	 */
	public List<CoChangeLogModel> getInvestchange(String name){
		String sql="SELECT T .change_date,T .guid,T .old_log,T .new_log FROM hub_commerce_co_change_log T "
				+ "LEFT JOIN hub_commerce_enterprise n ON T .company_name = n. NAME ";
		if(StringUtils.isNotBlank(name)){
			sql+="WHERE T .company_name ='"+ name+"' and T.change_object like '%投资人（股权）%'";
		}		
		return dao.find(sql);
	}
	/**
	 * 联络员变更
	 * @author lixiaoyi
	 * @date 2018年4月13日 下午4:02:54
	 * @TODO
	 */
	public List<CoChangeLogModel> getLiaisonchange(String name){
		String sql="SELECT T .change_date,T .guid,T .old_log,T .new_log FROM hub_commerce_co_change_log T "
				+ "LEFT JOIN hub_commerce_enterprise n ON T .company_name = n. NAME ";
		if(StringUtils.isNotBlank(name)){
			sql+="WHERE T .company_name ='"+ name+"' and T.change_object like '%联络员%'";
		}		
		return dao.find(sql);
	}
	/**
	 * 经营范围变更
	 * @author lixiaoyi
	 * @date 2018年4月13日 下午4:04:35
	 * @TODO
	 */
	public List<CoChangeLogModel> getBuinesschange(String name){
		String sql="SELECT T .change_date,T .guid,T .old_log,T .new_log FROM hub_commerce_co_change_log T "
				+ "LEFT JOIN hub_commerce_enterprise n ON T .company_name = n. NAME ";
		if(StringUtils.isNotBlank(name)){
			sql+="WHERE T .company_name ='"+ name+"' and T.change_object like '%经营范围%'";
		}		
		return dao.find(sql);
	}
	/**
	 * 企业类型变更
	 * @author lixiaoyi
	 * @date 2018年4月13日 下午4:05:27
	 * @TODO
	 */
	public List<CoChangeLogModel> getComtypechange(String name){
		String sql="SELECT T .change_date,T .guid,T .old_log,T .new_log FROM hub_commerce_co_change_log T "
				+ "LEFT JOIN hub_commerce_enterprise n ON T .company_name = n. NAME ";
		if(StringUtils.isNotBlank(name)){
			sql+="WHERE T .company_name ='"+ name+"' and T.change_object like '%企业类型%'";
		}		
		return dao.find(sql);
	}
	/**
	 * 注册资本变更
	 * @author lixiaoyi
	 * @date 2018年4月13日 下午4:06:03
	 * @TODO
	 */
	public List<CoChangeLogModel> getRegisterchange(String name){
		String sql="SELECT T .change_date,T .guid,T .old_log,T .new_log FROM hub_commerce_co_change_log T "
				+ "LEFT JOIN hub_commerce_enterprise n ON T .company_name = n. NAME ";
		if(StringUtils.isNotBlank(name)){
			sql+="WHERE T .company_name ='"+ name+"' and T.change_object like '%注册资本%'";
		}		
		return dao.find(sql);
	}
	
	
}
