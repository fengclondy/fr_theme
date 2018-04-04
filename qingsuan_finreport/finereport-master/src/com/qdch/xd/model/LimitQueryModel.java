package com.qdch.xd.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * 
 * @todo   业务查询-额度查询   hub_xd_cred_indus_info
 * @time   2018年4月2日14:59:19
 * @author wf
 */
public class LimitQueryModel extends Model<LimitQueryModel>{
	private static final long serialVersionUID = 1L;
	public static final LimitQueryModel dao = new LimitQueryModel();

	/**
	 *
	 * @param num
	 * @param size
	 * @return
	 */

	public Page<LimitQueryModel> getPage(int num, int size){
		String sql = "select * ";
		StringBuffer sb = new StringBuffer();
		sb.append(" from hub_xd_cred_indus_info");
//		return dao.paginate();
		return dao.paginate(num,size," select * ",sb.toString());

	}


}
