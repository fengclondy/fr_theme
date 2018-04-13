package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月12日下午2:54:15
 * @TODO
 */
public class CoChangeModel extends Model<CoChangeModel> {
	private static final long serialVersionUID = 1L;
	public static final CoChangeModel dao=new CoChangeModel();
	/**
	 * 变更记录
	 * @author lixiaoyi
	 * @date 2018年4月12日 下午2:54:24
	 * @TODO
	 */
	public  List<CoChangeModel> getChange(String name){
		String sql ="select * from hub_static_change_log ";
		if(StringUtils.isNotBlank(name)){
			sql+=" where company_name='"+name+"'";
		} 
		return dao.find(sql);
	}

}
