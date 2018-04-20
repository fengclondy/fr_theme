package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 工商-静态-商标
 * @author lixiaoyi
 * @date 2018年4月20日下午1:31:52
 * @TODO
 */
public class CoTrademarkModel extends Model<CoTrademarkModel> {
	private static final long serialVersionUID = 1L;
	public static final CoTrademarkModel dao =new CoTrademarkModel();
	
	public List<CoTrademarkModel> getTrademark(String name){
		String sql="select * from hub_static_legal where 1=1";
		if(StringUtils.isNotBlank(name)){
			sql+=" and company_name='"+name+"'";
		}
		return dao.find(sql);
		
	}
			
}
