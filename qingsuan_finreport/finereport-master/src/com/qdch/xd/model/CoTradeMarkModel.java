package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * 商标信息
 * @author lixiaoyi
 * @date 2018年4月10日
 * @TODO
 */
public class CoTradeMarkModel extends Model<CoTradeMarkModel> {
	private static final long serialVersionUID = 1L;
	public static final CoTradeMarkModel dao = new CoTradeMarkModel();
	/**
	 * 获取商标信息
	 * @author lixiaoyi
	 * @date 2018年4月10日 下午6:20:06
	 * @TODO
	 */
	public List<CoTradeMarkModel> getMark(String name){
		String sql="SELECT T.apply_date,T.image_url,T.name,T.register_code,T.category,T.flow_status "
				+ "FROM public.hub_commerce_co_trademark T LEFT JOIN hub_commerce_enterprise n ON T .company_name = n. NAME ";
		if(StringUtils.isNotBlank(name)){
			sql+="WHERE T.company_name = '"+ name+"'";
		}		
		
		return dao.find(sql);
		
	}
}
