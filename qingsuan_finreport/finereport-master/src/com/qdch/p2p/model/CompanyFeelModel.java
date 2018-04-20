package com.qdch.p2p.model;


import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

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
		String sql = "select a.publish_date,sum(a.qty) qty from(SELECT M .PUBLISH_DATE,"
				+ " (SELECT jysinfo FROM hub_commerce_ref_jys where enterprise_id = M.enterprise_id) jys,"
				+ " M.create_time,COUNT (1) QTY"
				+ " FROM hub_commerce_meiya_sentiment_news M"
				+ " WHERE m.enterprise_id in(SELECT enterprise_id FROM hub_commerce_ref_jys n left join public.hub_dd_tqs_jys t1"
				+ " on n.company_name=t1.jysmc where 1=1";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and enterprise_id in "+jysIds;
		}
		if (StringUtils.isNotBlank(jysinfo)) {
			sql += " and jysinfo = '"+jysinfo+"'";
		}
		sql += " and t1.jysfl='1') and M.create_time<=to_char(now()- interval'1 day','yyyymmdd') "
				+ " GROUP BY M .PUBLISH_DATE,M.enterprise_id,jys,M.create_time"
				+ " ORDER BY M .PUBLISH_DATE,M .ENTERPRISE_ID) as a group by a.publish_date order by a.publish_date";
		
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
				+ " and M.create_time<=to_char(now()- interval'1 day','yyyymmdd') ";
				if (StringUtils.isNotBlank(jysIds)) {
					sql += " and m.ENTERPRISE_ID in "+jysIds;
				}
				sql += " GROUP BY ENTERPRISE_ID,n. NAME,M.create_time"
					+ " ORDER BY M.create_time desc";
		return dao.find(sql);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月18日
	 * @TODO 获取最新资讯
	 */
	public List<CompanyFeelModel> getNews(String jysIds,String jysinfo){
		String sql = "SELECT title||'  '||T .publish_date||'  '||("
				+ " SELECT jysinfo FROM hub_commerce_ref_jys WHERE enterprise_id = T .enterprise_id";
				if (StringUtils.isNotBlank(jysinfo)) {
					sql += " and jysinfo = '"+jysinfo+"'";
				}
				sql +=") news"
				+ " FROM hub_commerce_meiya_sentiment_news T"
				+ " where t.enterprise_id in (SELECT enterprise_id FROM hub_commerce_ref_jys t left join public.hub_dd_tqs_jys t1"
				+ " on t.company_name=t1.jysmc where t1.jysfl=1";
				if (StringUtils.isNotBlank(jysIds)) {
					sql += " and enterprise_id in "+jysIds;
				}
				sql+= ")"
					+ " ORDER BY T .publish_date DESC LIMIT 7";
		
		return dao.find(sql);
	}
	
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月19日
	 * @TODO 舆情详情
	 */
	public List<CompanyFeelModel> getDetailCompanyFeel(String jysIds,String startTime,String endTime,String jysc,String keyword,int pageSize,int currentPage){
		String sql = "select rm.ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE,content, string_agg(keyword,',')  "
				+ "from ("
				+ " select ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE,keyword ,content,"
				+ " ROW_NUMBER()OVER(partition by ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE ORDER BY keyword) as rank_num "
				+ " from(select DISTINCT  ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE,content,"
				+ " regexp_split_to_table(hit_keyword,'\"') as keyword "
				+ " from public.hub_commerce_meiya_sentiment_news where 1=1";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and ENTERPRISE_ID in "+jysIds;
		}
		if (StringUtils.isNotBlank(startTime)) {
			sql += " and publish_date >= '"+startTime+"'";
		}
		if (StringUtils.isNotBlank(endTime)) {
			sql += " and publish_date <= '"+endTime+"'";
		}
		if (StringUtils.isNotBlank(jysc)) {
			sql += " and ENTERPRISE_ID = '"+jysc+"'";
		}
		if (StringUtils.isNotBlank(keyword)) {
			sql += " )rx WHERE rx.keyword ~ '[\u4e00-\u9fa5]' and SUMMARY like '%"+keyword+"%' "
					+ " )rm left join hub_commerce_ref_jys hcrj "
					+ " on rm.ENTERPRISE_ID = hcrj.ENTERPRISE_ID "
					+ " left join hub_dd_tqs_jys hdtj on hcrj.jys = hdtj.jys "
					+ " where rm.rank_num<=2 and hdtj.jysfl = '1' group by rm.ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE,content limit '"+pageSize+"' OFFSET '"+currentPage+"'";
		}else{
			sql += " )rx where rx.keyword ~ '[\u4e00-\u9fa5]'"
					+ " )rm left join hub_commerce_ref_jys hcrj "
					+ " on rm.ENTERPRISE_ID = hcrj.ENTERPRISE_ID "
					+ " left join hub_dd_tqs_jys hdtj on hcrj.jys = hdtj.jys "
					+ " where rm.rank_num<=2 and hdtj.jysfl = '1' group by rm.ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE,content limit '"+pageSize+"' OFFSET '"+(currentPage-1)*pageSize+"'";
		}
		
		
		
		//return dao.paginate(currentPage, pageSize, null, sql);
		return dao.find(sql);
	}
	
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月19日
	 * @TODO 舆情详情条数
	 */
	public List<CompanyFeelModel> getDetailCount(String jysIds,String startTime,String endTime,String jysc,String keyword){
		String sql = " select count(0) as count from (select rm.ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE,content, string_agg(keyword,',')  "
				+ "from ("
				+ " select ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE,keyword ,content,"
				+ " ROW_NUMBER()OVER(partition by ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE ORDER BY keyword) as rank_num "
				+ " from(select DISTINCT  ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE,content,"
				+ " regexp_split_to_table(hit_keyword,'\"') as keyword "
				+ " from public.hub_commerce_meiya_sentiment_news where 1=1";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and ENTERPRISE_ID in "+jysIds;
		}
		if (StringUtils.isNotBlank(startTime)) {
			sql += " and publish_date >= '"+startTime+"'";
		}
		if (StringUtils.isNotBlank(endTime)) {
			sql += " and publish_date <= '"+endTime+"'";
		}
		if (StringUtils.isNotBlank(jysc)) {
			sql += " and ENTERPRISE_ID = '"+jysc+"'";
		}
		if (StringUtils.isNotBlank(keyword)) {
			sql += " )rx WHERE rx.keyword ~ '[\u4e00-\u9fa5]' and SUMMARY like '%"+keyword+"%' "
					+ " )rm left join hub_commerce_ref_jys hcrj "
					+ " on rm.ENTERPRISE_ID = hcrj.ENTERPRISE_ID "
					+ " left join hub_dd_tqs_jys hdtj on hcrj.jys = hdtj.jys "
					+ " where rm.rank_num<=2 and hdtj.jysfl = '1' group by rm.ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE,content"
					+ " limit 5";
		}else{
			sql += " )rx where rx.keyword ~ '[\u4e00-\u9fa5]'"
					+ " )rm left join hub_commerce_ref_jys hcrj "
					+ " on rm.ENTERPRISE_ID = hcrj.ENTERPRISE_ID "
					+ " left join hub_dd_tqs_jys hdtj on hcrj.jys = hdtj.jys "
					+ " where rm.rank_num<=2 and hdtj.jysfl = '1' group by rm.ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE,content ) as a";
		}
		
		
		
		//return dao.paginate(currentPage, pageSize, null, sql);
		return dao.find(sql);
		
	}
	
}
