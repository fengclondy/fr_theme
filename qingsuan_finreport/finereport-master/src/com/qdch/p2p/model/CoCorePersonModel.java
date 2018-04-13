package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月12日下午3:39:59
 * @TODO
 */
public class CoCorePersonModel extends Model<CoCorePersonModel>{
	private static final long serialVersionUID = 1L;
	public static final CoCorePersonModel dao=new CoCorePersonModel();
	/**
	 * 主要人员
	 * @author lixiaoyi
	 * @date 2018年4月12日 下午3:40:03
	 * @TODO
	 */
	public List<CoCorePersonModel> getMainperson(String name){
		String sql ="select * from hub_static_important_person ";
		if(StringUtils.isNotBlank(name)){
			sql+=" where company_name='"+name+"'";
		} 
		return dao.find(sql);
	}

}
