package com.qdch.xd.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * 
 * @todo   合同信息-基本信息 hub_xd_report_cont
 * @time   2018年4月2日14:59:19
 * @author wf
 */
public class CustomerInfoModel extends Model<CustomerInfoModel>{
	private static final long serialVersionUID = 1L;
	public static final CustomerInfoModel dao = new CustomerInfoModel();


	/**
	 *
	 * @param num
	 * @param size
	 * @return
	 */

	public Page<CustomerInfoModel> getPage(int num, int size){
		String sql = "select * ";
		StringBuffer sb = new StringBuffer();
		sb.append(" from hub_xd_report_cont");
//		return dao.paginate();
		return dao.paginate(num,size," select * ",sb.toString());

	}



}
