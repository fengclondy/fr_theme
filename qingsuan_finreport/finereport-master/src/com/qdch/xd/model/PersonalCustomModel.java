package com.qdch.xd.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * 
 * @todo   个人客户  hub_xd_cust_pers
 * @time   2018年4月2日14:59:19
 * @author wf
 */
public class PersonalCustomModel extends Model<PersonalCustomModel>{
	private static final long serialVersionUID = 1L;
	public static final PersonalCustomModel dao = new PersonalCustomModel();

	/**
	 *
	 * @param num
	 * @param size
	 * @return
	 */

	public Page<PersonalCustomModel> getPage(int num, int size){
		String sql = "select * ";
		StringBuffer sb = new StringBuffer();
		sb.append(" from hub_xd_cust_pers");
//		return dao.paginate();
		return dao.paginate(num,size," select * ",sb.toString());

	}


}
