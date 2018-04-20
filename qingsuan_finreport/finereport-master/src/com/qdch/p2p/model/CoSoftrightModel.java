package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 工商-静态-软件著作权
 * @author lixiaoyi
 * @date 2018年4月20日下午2:14:58
 * @TODO
 */
public class CoSoftrightModel extends Model<CoSoftrightModel>{
	private static final long serialVersionUID = 1L;
	public static final CoSoftrightModel dao=new CoSoftrightModel();
	
	public  List<CoSoftrightModel> getSoft(String name){
		String sql="select * from hub_static_legal where 1=1";
		if(StringUtils.isNotBlank(name)){
			sql+=" and company_name='"+name+"'";
		}
		return dao.find(sql);
		
	}
}
