package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 静态-工商-失信人
 * @author lixiaoyi
 * @date 2018年4月20日上午11:52:20
 * @TODO
 */
public class CoDishonestModel extends Model<CoDishonestModel> {
	private static final long serialVersionUID = 1L;
	public static final CoDishonestModel dao=new CoDishonestModel();
	
	public  List<CoDishonestModel> getDishonest(String name){
		String sql="select * from hub_static_dishonest where 1=1";
		if (StringUtils.isNotBlank(name)) {
			sql+="and company_name='"+name+"'";
			
		}
		sql+=" limit 1";
		return  dao.find(sql);
		
	}
	/**
	 * 失信人个数
	 * @author lixiaoyi
	 * @date 2018年4月21日 下午2:54:07
	 * @TODO
	 */
	public  List<CoDishonestModel> getDissize(String name){
		String sql="select * from hub_static_dishonest where 1=1";
		if (StringUtils.isNotBlank(name)) {
			sql+="and company_name='"+name+"'";
			
		}
		
		return  dao.find(sql);
		
	}
}
