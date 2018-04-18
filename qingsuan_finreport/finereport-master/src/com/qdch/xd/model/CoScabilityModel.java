package com.qdch.xd.model;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 交易市场
 * @author lixiaoyi
 * @date 2018年4月17日下午6:47:16
 * @TODO
 */
public class CoScabilityModel extends Model<CoScabilityModel> {
	private static final long serialVersionUID = 1L;
	public  static final CoScabilityModel dao=new CoScabilityModel();
	/**
	 * 交易市场得分
	 * @author lixiaoyi
	 * @date 2018年4月17日 下午6:47:19
	 * @TODO
	 */
	public CoScabilityModel getScore(String name,String data){
		String sql="select * from insight_xd_scability where 1=1";
		if(StringUtils.isNotBlank(name)){
			sql+=" jyscmc'"+name+"'";
			}
		if(StringUtils.isNotBlank(data)){
			sql+="and  jysc in "+ data;
		}
		return dao.findFirst(sql);
		
	}

}
