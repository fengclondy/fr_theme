package com.qdch.p2p.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;



/**
 * @author hanpengda
 * @date 2018年4月9日
 * @TODO 平台总览表
 */
public class PlatformModel extends Model<PlatformModel>{

	private static final long serialVersionUID = 1L;
	public static final PlatformModel dao = new PlatformModel();
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月9日
	 * @TODO 获取平台总览信息
	 */
	public List<PlatformModel> getOverviewInfo(){
		String sql = "select * from insight_pp_overview";
		
		return dao.find(sql);
	} 
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月9日
	 * @TODO 获取平台名称
	 */
	public List<PlatformModel> getJysc(){
		String sql = "select jyscmc from insight_pp_overview";
		return dao.find(sql);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月9日
	 * @TODO 获取存管银行
	 */
	public List<PlatformModel> getCgyh(){
		String sql = "select cgyh from insight_pp_overview";
		return dao.find(sql);
	}   
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月9日
	 * @TODO 获取注册资本
	 */
	public List<PlatformModel> getZczb(){
		String sql = "select zczb from insight_pp_overview";
		return dao.find(sql);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月9日
	 * @TODO 获取等保
	 */
	public List<PlatformModel> getDb(){
		String sql = "select db  from insight_pp_overview";
		return dao.find(sql);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月9日
	 * @TODO 获取平台实力
	 */
	public List<PlatformModel> getPtsl(){
		String sql = "select ptsl  from insight_pp_overview";
		return dao.find(sql);
	}
}
