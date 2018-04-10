package com.qdch.xd.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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

	public Page<LimitQueryModel> getPage(int num, int size,HttpServletRequest request){
		String sql = "select * ";
		StringBuffer sb = new StringBuffer();
		sb.append(" from hub_xd_cred_indus_info where 1=1 ");
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
