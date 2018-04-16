package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月12日下午3:13:13
 * @TODO
 */
public class CoExecptionModel extends Model<CoExecptionModel> {
	private static final long serialVersionUID = 1L;
	public static final CoExecptionModel dao=new CoExecptionModel();
	/**
	 * 经营异常
	 * @author lixiaoyi
	 * @date 2018年4月12日 下午3:13:17
	 * @TODO
	 */
	public List<CoExecptionModel> getExecption (String name){
		String sql ="select * from hub_static_operation_exception ";
		if(StringUtils.isNotBlank(name)){
			sql+=" where company_name='"+name+"'";
		} 
		return dao.find(sql);
		
	}
	

}
