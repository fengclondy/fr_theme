package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月12日下午3:21:45
 * @TODO
 */
public class CoShareholderInfoModel extends Model<CoShareholderInfoModel> {
	private static final long serialVersionUID = 1L;
	public static final CoShareholderInfoModel dao=new CoShareholderInfoModel();
 /**
  * 股东信息
  * @author lixiaoyi
  * @date 2018年4月12日 下午3:39:10
  * @TODO
  */
    public List<CoShareholderInfoModel> getShareholder(String name){
		String sql ="select * from hub_static_shareholder_info ";
		if(StringUtils.isNotBlank(name)){
			sql+=" where company_name='"+name+"'";
		} 
		return dao.find(sql);
	}

}
