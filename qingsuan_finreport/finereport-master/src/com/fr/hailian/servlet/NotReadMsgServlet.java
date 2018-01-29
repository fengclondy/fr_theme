package com.fr.hailian.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.service.NotReadMsgService;
import com.fr.hailian.service.UserDataFromRoleService;
import com.fr.hailian.util.RoleUtil;
import com.fr.json.JSONObject;
/***
 * 获取未读条数
 * @author Tom
 *
 */
public class NotReadMsgServlet extends BaseServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getNotReadMsg(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getNotReadMsg(request, response);
	}
	
	private void getNotReadMsg(HttpServletRequest request, HttpServletResponse response) {
		HttpServletRequest hrequest = (HttpServletRequest) request;//web资源
		JSONObject r = new JSONObject();
		NotReadMsgService notReadMsg = new NotReadMsgService();
		//通过帆软方法获取用户名
		String username = RoleUtil.getCurrentUser(hrequest).getUsername();
		//String username = "主管";
		try {
			//name = java.net.URLDecoder.decode(hrequest.getParameter("username"), "UTF-8");
			String type = java.net.URLDecoder.decode(hrequest.getParameter("type"), "UTF-8");
			System.out.println("username:" + username);
			System.out.println("type:" + type);
			//获取角色具有查看信息的交易所
			String jys=UserDataFromRoleService.getDepartMenByUserName(username);
			jys = "("+jys+")";
			long count = notReadMsg.getUnReadMsgCount(username,type,jys);
			//先获取消息总数
			r.put("unReadCount", count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		responseOutWithJson(response, r);
	}

}
