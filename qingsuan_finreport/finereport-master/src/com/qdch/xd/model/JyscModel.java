package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

//@TableBind(tableName="hub_commerce_ref_jys")
/**
 * 
 * @author doush
 * @date 2018年3月26日
 * @TODO 获取交易市场 --小贷
 */
public class JyscModel extends Model<JyscModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final JyscModel dao = new JyscModel();
	
	public List<JyscModel> getJysc(String dataSql){	
		String sql="select * from hub_xd_jysc where 1=1 ";
		
		if(StringUtils.isNotBlank(dataSql)){
			sql+=" and jysc in"+ dataSql+"";
		}
		return dao.find(sql);
	}

	
	
	
}
