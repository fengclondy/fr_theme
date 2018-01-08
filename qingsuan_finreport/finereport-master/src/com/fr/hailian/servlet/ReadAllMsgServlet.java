package com.fr.hailian.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.service.ReadAllMsgService;
import com.fr.hailian.service.ReadMsgService;
import com.fr.hailian.service.UserDataFromRoleService;
import com.fr.hailian.util.KeyUtil;
import com.fr.hailian.util.RoleUtil;
/***
 * 将所有的消息未读数置0
 * @author Tom
 *
 */
public class ReadAllMsgServlet extends BaseServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		readAllMsg(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		readAllMsg(request, response);
	}
	
	private void readAllMsg(HttpServletRequest request, HttpServletResponse response) {
		HttpServletRequest hrequest = (HttpServletRequest) request;//web资源
		JSONObject r = new JSONObject();
		ReadAllMsgService readAllMsg = new ReadAllMsgService();
		UserDataFromRoleService user = new UserDataFromRoleService();
		ReadMsgService readMsg = new ReadMsgService();
		String username = RoleUtil.getCurrentUser(hrequest).getUsername();
		long userId = RoleUtil.getCurrentUser(hrequest).getId();
		//String username = "Test";
		try {
			//name = java.net.URLDecoder.decode(hrequest.getParameter("username"), "UTF-8");
			//System.out.println("name:" + name);
			String type = java.net.URLDecoder.decode(hrequest.getParameter("type"), "UTF-8");
			//System.out.println("type:" + type);
			String roleName = user.getRoleNameByUserId(userId+"");
			//String roleName = KeyUtil.getKeyValue("DZ");
			//System.out.println("roleName:" + roleName);
			//判断当前用户为何种角色，通过角色去寻找当前的用户
			//如果角色为处理人,把未读的风险事件也置为已读
			if(KeyUtil.getKeyValue("DZ").equals(roleName)){
				readAllMsg.setAllToRead("DEAL",type);
				readMsg.setAllToRead(username,type);
			}else if(KeyUtil.getKeyValue("QY").equals(roleName)){
				readAllMsg.setAllToRead("DEAL",type);
				readMsg.setAllToRead(username,type);
			}else if(KeyUtil.getKeyValue("SH").equals(roleName)){
				readAllMsg.setAllToRead("AUDIT",type);
			}else if(KeyUtil.getKeyValue("JC").equals(roleName)){
				readAllMsg.setAllToRead("JUDGE",type);
			}
			//先获取消息总数
			r.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			r.put("success", false);
		}
		responseOutWithJson(response, r);
	}

}
