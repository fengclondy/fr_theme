package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 静态-工商-著作权
 * @author lixiaoyi
 * @date 2018年4月20日下午2:10:21
 * @TODO
 */
public class CoComcopyrightModel extends Model<CoComcopyrightModel> {
	private static final long serialVersionUID = 1L;
	public static final CoComcopyrightModel dao =new CoComcopyrightModel();
	
	public List<CoComcopyrightModel> getCopyrigt(String name){
		String sql="select * from hub_static_copyright_info where 1=1";
		if(StringUtils.isNotBlank(name)){
			sql+=" and company_name='"+name+"'";
		}
		return dao.find(sql);
	}
}
