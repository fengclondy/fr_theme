package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月12日上午11:37:27
 * @TODO
 */
public class CoCompanyInfoModel extends Model<CoCompanyInfoModel> {
	private static final long serialVersionUID = 1L;
	public static final CoCompanyInfoModel dao=new CoCompanyInfoModel();
	/**
	 * 静态-工商 公司基本信息
	 * @author lixiaoyi
	 * @date 2018年4月12日 下午1:22:12
	 * @TODO
	 */
	public  CoCompanyInfoModel getInfo(String name){
		String sql ="select * from hub_static_company_info ";
		if(StringUtils.isNotBlank(name)){
			sql+=" where company_name='"+name+"'";
		} 
		return dao.findFirst(sql);
	}
 /**
  * 根据法人名字查询相关企业
  * @author lixiaoyi
  * @date 2018年4月21日 下午3:19:58
  * @TODO
  */
	public List<CoCompanyInfoModel> getRe(String name){
		String sql="select * from hub_static_company_info";
		if(StringUtils.isNotBlank(name)){
			sql+=" where legal_person='"+name+"'";
		} 
		return dao.find(sql);
	}
}
