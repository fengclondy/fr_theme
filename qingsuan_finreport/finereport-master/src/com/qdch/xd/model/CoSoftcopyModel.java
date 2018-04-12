package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * 软件著作权
 * @author lixiaoyi
 * @date 2018年4月10日上午11:37:34
 * @TODO
 */
public class CoSoftcopyModel extends Model<CoSoftcopyModel> {
	private static final long serialVersionUID = 1L;
	public static final CoSoftcopyModel dao = new CoSoftcopyModel();
   /**
    * 获取软件著作权
    * @author lixiaoyi
    * @date 2018年4月10日 下午6:34:08
    * @TODO
    */
	public List<CoSoftcopyModel> getSoft(String name){
		String sql="SELECT T.name,T.register_no,T.category,T.add_time,T.register_date,T.update_time "
				+ "FROM hub_commerce_co_copyright T ";
		if(StringUtils.isNotBlank(name)){
			sql+="where T.company_name= '"+name+"'";
		}
				
		return dao.find(sql);
		
	}
}
