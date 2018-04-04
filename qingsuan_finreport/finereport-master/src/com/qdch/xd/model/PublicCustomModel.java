package com.qdch.xd.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * 
 * @todo   对公客户 hub_xd_cust_corp
 * @time   2018年4月2日14:59:19
 * @author wf
 */
public class PublicCustomModel extends Model<PublicCustomModel>{
	private static final long serialVersionUID = 1L;
	public static final PublicCustomModel dao = new PublicCustomModel();

	/**
	 *
	 * @param num
	 * @param size
	 * @return
	 */

	public Page<PublicCustomModel> getPage(int num, int size){
		String sql = "select * ";
		StringBuffer sb = new StringBuffer();
		sb.append(" from hub_xd_cust_corp");
//		return dao.paginate();
		return dao.paginate(num,size," select * ",sb.toString());

	}


}
