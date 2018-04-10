package com.qdch.xd.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

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

	public Page<CustomerInfoModel> getPage(int num, int size,HttpServletRequest request){
		String sql = "select * ";
		StringBuffer sb = new StringBuffer();
		sb.append(" from hub_xd_report_cont where 1=1 ");
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
