package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 市场画像
 * @author lixiaoyi
 * @date 2018年4月17日上午10:54:21
 * @TODO
 */
public class MarkNewsModel extends Model<MarkNewsModel> {
	private static final long serialVersionUID = 1L;
	public static final MarkNewsModel dao = new MarkNewsModel();
	/**
	 * 市场画像--详情-p2p
	 * @author lixiaoyi
	 * @date 2018年4月17日 上午10:54:43
	 * @TODO
	 */
	public List<MarkNewsModel> getNews(String cid,String keyword,String begintime,String endtime){
		String sql="select rm.ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE,content, string_agg(keyword,',')  from ("
+" select ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE,keyword ,content,"
+" ROW_NUMBER()OVER(partition by ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE ORDER BY keyword) as rank_num from("
+" select DISTINCT  ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE,content,regexp_split_to_table(hit_keyword,'') "

+" as keyword from public.hub_commerce_meiya_sentiment_news"
+" where 1=1 ";
		if(StringUtils.isNotBlank(cid)){
			sql+="and ENTERPRISE_ID = '" + cid + "'";
		}
		if(StringUtils.isNotBlank(begintime)){
			sql+="and PUBLISH_DATE >= '" + begintime + "'";
		}
		if(StringUtils.isNotBlank(endtime)){
			sql+="and PUBLISH_DATE <= '" + endtime + "'";
		}
		   sql+=")rx";
		if(StringUtils.isNotBlank(keyword)){
			sql+=" WHERE rx.keyword ~ '[\u4e00-\u9fa5]' and  SUMMARY like  '%"+keyword+"%' ";
		}

    sql+=" )rm left join hub_commerce_ref_jys hcrj"
        +" on rm.ENTERPRISE_ID = hcrj.ENTERPRISE_ID left join hub_dd_tqs_jys hdtj"
       +" on hcrj.jys = hdtj.jys where rm.rank_num<=2 and hdtj.jysfl = '4'"
        +" group by rm.ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE,content";
		return dao.find(sql);
	}
	
	/**
	 * 市场画像--详情-小贷
	 * @author lixiaoyi
	 * @date 2018年4月17日 上午10:54:43
	 * @TODO
	 */
	public List<MarkNewsModel> getNews2(String cid,String keyword,String begintime,String endtime){
		String sql="select rm.ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE,content, string_agg(keyword,',')  from ("
+" select ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE,keyword ,content,"
+" ROW_NUMBER()OVER(partition by ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE ORDER BY keyword) as rank_num from("
+" select DISTINCT  ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE,content,regexp_split_to_table(hit_keyword,'') "

+" as keyword from public.hub_commerce_meiya_sentiment_news"
+" where 1=1 ";
		if(StringUtils.isNotBlank(cid)){
			sql+="and ENTERPRISE_ID = '" + cid + "'";
		}
		if(StringUtils.isNotBlank(begintime)){
			sql+="and PUBLISH_DATE >= '" + begintime + "'";
		}
		if(StringUtils.isNotBlank(endtime)){
			sql+="and PUBLISH_DATE <= '" + endtime + "'";
		}
		   sql+=")rx";
		if(StringUtils.isNotBlank(keyword)){
			sql+=" WHERE rx.keyword ~ '"+keyword+"'";
		}

    sql+=" )rm left join hub_commerce_ref_jys hcrj"
        +" on rm.ENTERPRISE_ID = hcrj.ENTERPRISE_ID left join hub_dd_tqs_jys hdtj"
       +" on hcrj.jys = hdtj.jys where rm.rank_num<=2 and hdtj.jysfl = '3'"
        +" group by rm.ENTERPRISE_ID,TITLE,SUMMARY,URL,DATA_SOURCE,PUBLISH_DATE,content";
		return dao.find(sql);
	}

}
