package com.fr.hailian.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.service.UserDataFromRoleService;
import com.fr.hailian.util.RoleUtil;
/***
 * 通过username返回的角色名
 * @author Tom
 *
 */
public class RolePageServlet extends BaseServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getPage(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getPage(request, response);
	}
	
	private void getPage(HttpServletRequest request, HttpServletResponse response) {
		HttpServletRequest hrequest = (HttpServletRequest) request;//web资源
		JSONObject r = new JSONObject();
		String userId = RoleUtil.getCurrentUser(hrequest).getId()+"";
		UserDataFromRoleService user = new UserDataFromRoleService();
		try {
			//name = java.net.URLDecoder.decode(hrequest.getParameter("username"), "UTF-8");
			//System.out.println("userId:" + userId);
			String roleName = user.getRoleNameByUserId(userId);
			//System.out.println("roleName:" + roleName);
			r.put("roleName", roleName);
			r.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			r.put("success", false);
		}
		responseOutWithJson(response, r);
	}
}
