package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * 著作权信息
 * @author lixiaoyi
 * @date 2018年4月10日
 * @TODO
 */
public class CoCopyrightModel extends Model<CoCopyrightModel> {
	private static final long serialVersionUID = 1L;
	public static  final CoCopyrightModel dao = new CoCopyrightModel();
    /**
     * 获取著作权信息
     * @author lixiaoyi
     * @date 2018年4月10日 下午6:41:01
     * @TODO
     */
	public List<CoCopyrightModel> getCopy(String name){
		 String sql="SELECT * FROM public.hub_commerce_co_copyright T LEFT JOIN hub_commerce_enterprise n ON T .company_name = n. NAME ";
				 if(StringUtils.isNotBlank(name)){
						sql+="WHERE T.company_name = '"+ name+"'";
					}
				
		 return dao.find(sql);
				 
		
	}
	
}
