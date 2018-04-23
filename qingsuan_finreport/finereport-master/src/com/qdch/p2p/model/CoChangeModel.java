package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月12日下午2:54:15
 * @TODO
 */
public class CoChangeModel extends Model<CoChangeModel> {
	private static final long serialVersionUID = 1L;
	public static final CoChangeModel dao=new CoChangeModel();
	/**
	 * 变更记录
	 * @author lixiaoyi
	 * @date 2018年4月12日 下午2:54:24
	 * @TODO
	 */
	public  List<CoChangeModel> getChange(String name){
		String sql ="select * from hub_static_change_log ";
		if(StringUtils.isNotBlank(name)){
			sql+=" where company_name='"+name+"'";
		} 
		return dao.find(sql);
	}
	/**
	 * 名称变更
	 * @author lixiaoyi
	 * @date 2018年4月20日 下午6:51:22
	 * @TODO
	 */
	public List<CoChangeModel> getName(String name){
		String sql="SELECT * from hub_static_change_log  where change_info like '%名称%' ";
		if(StringUtils.isNotBlank(name)){
			sql+="and company_name='+"+name+"'";
		}
		
		return dao.find(sql);
		
	}

  
	/**
	 * 投资人股权变更
	 * @author lixiaoyi
	 * @date 2018年4月20日 下午6:52:30
	 * @TODO
	 */
	public List<CoChangeModel> getStock(String name){
		String sql="SELECT * from hub_static_change_log  where change_info like '%投资人股权%' ";
		if(StringUtils.isNotBlank(name)){
			sql+="and company_name='+"+name+"'";
		}
		
		return dao.find(sql);
		
	}
	/**
	 * 联络员
	 * @author lixiaoyi
	 * @date 2018年4月20日 下午6:53:22
	 * @TODO
	 */
	public List<CoChangeModel> getliain(String name){
		String sql="SELECT * from hub_static_change_log  where change_info like '%联络员%' ";
		if(StringUtils.isNotBlank(name)){
			sql+="and company_name='+"+name+"'";
		}
		
		return dao.find(sql);
		
	}
	/**
	 * 经营范围
	 * @author lixiaoyi
	 * @date 2018年4月20日 下午6:54:30
	 * @TODO
	 */
	public List<CoChangeModel> getBusiness(String name){
		String sql="SELECT * from hub_static_change_log  where change_info like '%经营范围%' ";
		if(StringUtils.isNotBlank(name)){
			sql+="and company_name='+"+name+"'";
		}
		
		return dao.find(sql);
		
	}
	/**
	 * 企业类型
	 * @author lixiaoyi
	 * @date 2018年4月20日 下午6:55:48
	 * @TODO
	 */
	public List<CoChangeModel> getType(String name){
		String sql="SELECT * from hub_static_change_log  where change_info like '%企业类型%' ";
		if(StringUtils.isNotBlank(name)){
			sql+="and company_name='+"+name+"'";
		}
		
		return dao.find(sql);
		
	}
	/**
	 * 注册资本
	 * @author lixiaoyi
	 * @date 2018年4月20日 下午6:55:37
	 * @TODO
	 */
	public List<CoChangeModel> getRegist(String name){
		String sql="SELECT * from hub_static_change_log  where change_info like '%注册资本%' ";
		if(StringUtils.isNotBlank(name)){
			sql+="and company_name='+"+name+"'";
		}
		
		return dao.find(sql);
		
	}
}
