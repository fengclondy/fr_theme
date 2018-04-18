package com.qdch.p2p.model;


import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * @author hanpengda
 * @date 2018年4月18日
 * @TODO 企业舆情
 */
public class CompanyFeelModel extends Model<CompanyFeelModel>{

	private static final long serialVersionUID = 1L;
	public static final CompanyFeelModel dao = new CompanyFeelModel();
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月18日
	 * @TODO 实时舆情
	 */
	public List<CompanyFeelModel> getRealCompanyFeel(String jysIds,String jysinfo){
		String sql = "SELECT M .PUBLISH_DATE,"
				+ " (SELECT jysinfo FROM hub_commerce_ref_jys where enterprise_id = M.enterprise_id) jys,"
				+ " M.create_time,COUNT (1) QTY"
				+ " FROM hub_commerce_meiya_sentiment_news M"
				+ " WHERE m.enterprise_id in(SELECT enterprise_id FROM hub_commerce_ref_jys n left join public.hub_dd_tqs_jys t1"
				+ " on n.company_name=t1.jysmc where 1=1";
		if (StringUtils.isNotBlank(jysinfo)) {
			sql += " and jysinfo = '"+jysinfo+"' and t1.jysfl='1') and M.create_time<=to_char(now()- interval'1 day','yyyymmdd') "
					+ " GROUP BY M .PUBLISH_DATE,M.enterprise_id,jys,M.create_time"
					+ " ORDER BY M .PUBLISH_DATE,M .ENTERPRISE_ID";
		}else{
			sql += " and t1.jysfl='1') and M.create_time<=to_char(now()- interval'1 day','yyyymmdd') "
					+ " GROUP BY M .PUBLISH_DATE,M.enterprise_id,jys,M.create_time"
					+ " ORDER BY M .PUBLISH_DATE,M .ENTERPRISE_ID";
		}
		
		return dao.find(sql);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月18日
	 * @TODO 市场舆情概览
	 */
	public List<CompanyFeelModel> getOverviewCompanFeel(String jysIds){
		String sql = "SELECT (SELECT ENTERPRISE_ID FROM hub_commerce_ref_jys WHERE company_name = n. NAME) ENTERPRISE_ID,"
				+ " (SELECT jysinfo FROM hub_commerce_ref_jys t where t.company_name = n. NAME) jysinfo,M.create_time,COUNT (1) QTY"
				+ " FROM PUBLIC .hub_commerce_meiya_sentiment_news M "
				+ " LEFT JOIN PUBLIC .hub_commerce_enterprise n ON M .ENTERPRISE_ID = n. ID"
				+ " WHERE n. NAME IN (SELECT company_name FROM hub_commerce_ref_jys t left join public.hub_dd_tqs_jys t1 on t.company_name=t1.jysmc"
				+ " where t1.jysfl=1 )"
				+ " and M.create_time<=to_char(now()- interval'1 day','yyyymmdd') "
				+ " GROUP BY ENTERPRISE_ID,n. NAME,M.create_time"
				+ " ORDER BY M.create_time desc";
		return dao.find(sql);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月18日
	 * @TODO 获取最新资讯
	 */
	public List<CompanyFeelModel> getNews(String jysIds){
		String sql = "SELECT title||'  '||T .publish_date||'  '||("
				+ " SELECT jysinfo FROM hub_commerce_ref_jys WHERE enterprise_id = T .enterprise_id) news"
				+ " FROM hub_commerce_meiya_sentiment_news T"
				+ " where t.enterprise_id in (SELECT enterprise_id FROM hub_commerce_ref_jys t left join public.hub_dd_tqs_jys t1"
				+ " on t.company_name=t1.jysmc where t1.jysfl=1)"
				+ " ORDER BY T .publish_date DESC LIMIT 5";
		
		return dao.find(sql);
	}
	
}
