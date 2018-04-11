package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * 行政处罚
 * @author lixiaoyi
 * @date 2018年4月10日
 * @TODO
 */
public class CoPenaltvModel extends Model<CoPenaltvModel> {
	private static final long serialVersionUID = 1L;
	public static final CoPenaltvModel dao =new CoPenaltvModel();
   /**
    * 获取行政处罚信息
    * @author lixiaoyi
    * @date 2018年4月10日 下午6:12:44
    * @TODO
    */
	public List<CoPenaltvModel> getPenalt(String name){
		String sql="SELECT T.decide_date,T.doc_no,T.classify,T.decide_organs,T.site,T.detail,n.corporate "
				+ "FROM public.hub_commerce_co_penalty T LEFT JOIN hub_commerce_enterprise n  ON T .company_name = n. NAME ";
		if(StringUtils.isNotBlank(name)){
			sql+="WHERE T.company_name ='"+ name+"'";
		}	
		
		return dao.find(sql);
		
	}
}
