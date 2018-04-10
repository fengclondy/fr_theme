package com.qdch.xd.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 
 * @todo   合同信息-基本信息  hub_xd_cont_info
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
		sb.append(" from hub_xd_cont_info where 1=1 ");
//		return dao.paginate();
		if(StringUtils.isNotBlank(request.getParameter("cuscode"))){ //风险事件id
			sb.append("  and custid  like '%").append(request.getParameter("cuscode")).append("%'");
		}
		try {
			if(StringUtils.isNotBlank(request.getParameter("cusname"))){ //风险事件id
				sb.append("  and custname  like '%").append(decode(request.getParameter("cusname"))).append("%'");
			}
		} catch (Exception e){
			e.getStackTrace();
		}
		return dao.paginate(num,size," select * ",sb.toString());

	}

	private String decode(String str) throws UnsupportedEncodingException {
		return URLDecoder.decode(str,"UTF-8");
	}



}
