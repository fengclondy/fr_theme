package com.fr.hailian.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.fs.base.entity.User;
import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.service.UserDataFromRoleService;
import com.fr.hailian.util.KeyUtil;
import com.fr.hailian.util.RoleUtil;
import com.fr.json.JSONObject;
/***
 * 审核使用的servlet
 * @author Tom
 *
 */
public class FXSJAuditServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getAudit(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getAudit(request, response);
	}
	
	private void getAudit(HttpServletRequest request, HttpServletResponse response) {
		HttpServletRequest hrequest = (HttpServletRequest) request;//web资源
		JSONObject r = new JSONObject();
		//FXSJAuditService fxsj = new FXSJAuditService();
		UserDataFromRoleService userData = new UserDataFromRoleService();
		User user = RoleUtil.getCurrentUser(hrequest);
		System.out.println("当前用户名："+user.getUsername());
		try {
			/*//风险事件id
			String fxsjId = java.net.URLDecoder.decode(hrequest.getParameter("fxsjid"), "UTF-8");
			//提报人id
			String reportId = java.net.URLDecoder.decode(hrequest.getParameter("reportid"), "UTF-8");
			//处理人id
			String dealId = java.net.URLDecoder.decode(hrequest.getParameter("dealid"), "UTF-8");
			//主键id
			String id = UUID.randomUUID().toString().trim().replaceAll("-", "");
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			//插入时间
			String insertTime = dateFormat.format(now);
			fxsj.doAuditInsertAndUpdate(id, fxsjId, reportId, dealId, insertTime, Constants.FXSJ_AUDITING);*/
			String key = KeyUtil.getKeyValue("TEST");
			String phone = userData.getUserPhoneByRoleName(key);
			System.out.println("获取到的手机号码："+phone);
			//System.out.println("name:" + name);
			//先获取消息总数
			r.put("unReadCount", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		responseOutWithJson(response, r);
	}

}
