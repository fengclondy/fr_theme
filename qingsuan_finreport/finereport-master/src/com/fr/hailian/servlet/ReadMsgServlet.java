package com.fr.hailian.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.service.ReadMsgService;
import com.fr.hailian.util.RoleUtil;
import com.fr.json.JSONException;
import com.fr.json.JSONObject;
/***
 * 读消息
 * @author Tom
 *
 */
public class ReadMsgServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		readMsg(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		readMsg(request, response);
	}
	
	private void readMsg(HttpServletRequest request, HttpServletResponse response) {
		HttpServletRequest hrequest = (HttpServletRequest) request;//web资源
		JSONObject r = new JSONObject();
		ReadMsgService readMsg = new ReadMsgService();
		String username = RoleUtil.getCurrentUser(hrequest).getUsername();
		//String username = "Test";
		try {
			//name = java.net.URLDecoder.decode(hrequest.getParameter("username"), "UTF-8");
			String type = java.net.URLDecoder.decode(hrequest.getParameter("type"), "UTF-8");
			System.out.println("username:" + username);
			System.out.println("type:" + type);
			readMsg.setAllToRead(username,type);
			r.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				r.put("success", false);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		responseOutWithJson(response, r);
	}

}
