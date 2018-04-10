package com.qdch.xd.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

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

	public Page<DetailsQueryModel> getPage(int num, int size,HttpServletRequest request){
		String sql = "select * ";
		StringBuffer sb = new StringBuffer();
		sb.append(" from hub_xd_loan_ledger where 1=1");
//		return dao.paginate();
		if(StringUtils.isNotBlank(request.getParameter("cuscode"))){ //风险事件id
			sb.append("  and custid  like '%").append(request.getParameter("custid")).append("%'");
		}
		if(StringUtils.isNotBlank(request.getParameter("cusname"))){ //风险事件id
			sb.append("  and custname  like '%").append(request.getParameter("custname")).append("%'");
		}
		return dao.paginate(num,size," select * ",sb.toString());

	}



}
