package com.qdch.p2p.model;

import java.util.List;

import com.fr.report.core.A.S;
import com.jfinal.plugin.activerecord.Model;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月8日
 * @TODO 借款人总览信息
 */
public class BorrowerModel extends Model<BorrowerModel> {
	
	private static final long serialVersionUID = 1L;
	public static final BorrowerModel dao=new BorrowerModel();
	
	/**
	 * 
	 * @author lixiaoyi
	 * @date 2018年4月8日
	 * @TODO 平台得分
	 */
	public List<BorrowerModel> getScoreList(String bigjys){
		String sql="select jysinfo,fscore from insight_pp_score_info order by fscore desc";
	
	    return	dao.find(sql);
	}
	/**
	 * 获取自然人基本信息
	 * @author lixiaoyi
	 * @date 2018年4月8日
	 * @TODO
	 */
	public List<BorrowerModel> getBasicinfo(String bigjys){
		String sql="select * from insight_pp_person_info";
		return dao.find(sql);
	}
    /**
     * 获取自然人信用信息
     * @author lixiaoyi
     * @date 2018年4月8日
     * @TODO
     */
	public List<BorrowerModel> getCreditinfo(String bigjys){
		String sql="select * from insight_pp_credit_info";
		return dao.find(sql);
	}
	
	/**
	 * 获取自然人资产信息
	 * @author lixiaoyi
	 * @date 2018年4月8日
	 * @TODO
	 */
	public List<BorrowerModel> getAssetinfo(String bigjys){
		String sql="select * from insight_pp_asset_info";
		return dao.find(sql);
	}
	
	/**
	 * 获取自然人工作信息
	 * @author lixiaoyi
	 * @date 2018年4月8日
	 * @TODO
	 */
	public List<BorrowerModel> getJobinfo(String bigjys){
		String sql="select * from insight_pp_job_info";
		return dao.find(sql);
	}
	
	/**
	 * 获取企业基本信息
	 * @author lixiaoyi
	 * @date 2018年4月8日
	 * @TODO
	 */
	public List<BorrowerModel> getCompybasicinfo(String bigjys){
		String sql="select * from insight_pp_corp_info";
		return dao.find(sql);
	}
	/**
	 * 获取企业其他信息
	 * @author lixiaoyi
	 * @date 2018年4月8日
	 * @TODO
	 */
	public List<BorrowerModel> getOtherinfo(String bigjys){
		String sql="select * from insight_pp_othe_info";
		return dao.find(sql);
	}
	
}
