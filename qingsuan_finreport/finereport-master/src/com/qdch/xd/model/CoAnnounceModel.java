package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 法院公告
 * @author lixiaoyi
 * @date 2018年4月10日
 * @TODO
 */
public class CoAnnounceModel  extends Model<CoAnnounceModel>{
	private static final long serialVersionUID = 1L;
	public static final CoAnnounceModel dao = new CoAnnounceModel();
    /**
     * 获取法律公告
     * @author lixiaoyi
     * @date 2018年4月10日 下午5:23:22
     * @TODO
     */
	public List<CoAnnounceModel> getAnnounce(String name){
		String sql="SELECT T.public_date,T.litigant,T.classify,T.court,T.detail,T.litigant "
				+ "FROM public.hub_commerce_court_announce T "
				+ "LEFT JOIN hub_commerce_enterprise n ON T .company_name = n. NAME ";
		if(StringUtils.isNotBlank(name)){
			sql+="WHERE T.company_name = '"+ name+"'";
		}
		return dao.find(sql);
		
	}
}
