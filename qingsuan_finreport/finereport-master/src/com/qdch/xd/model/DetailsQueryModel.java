package com.qdch.xd.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * 
 * @todo   业务查询-明细查询  hub_xd_loan_ledger
 * @time   2018年4月2日14:59:19
 * @author wf
 */
public class DetailsQueryModel extends Model<DetailsQueryModel>{
	private static final long serialVersionUID = 1L;
	public static final DetailsQueryModel dao = new DetailsQueryModel();

	/**
	 *
	 * @param num
	 * @param size
	 * @return
	 */

	public Page<DetailsQueryModel> getPage(int num, int size){
		String sql = "select * ";
		StringBuffer sb = new StringBuffer();
		sb.append(" from hub_xd_loan_ledger");
//		return dao.paginate();
		return dao.paginate(num,size," select * ",sb.toString());

	}



}
