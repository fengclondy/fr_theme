package com.fr.hailian.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.service.UpdateRejectStatusService;
import com.fr.hailian.service.UserDataFromRoleService;
import com.fr.hailian.util.KeyUtil;
import com.fr.hailian.util.SendMsgUtil;
/***
 * 发送短信的servlet
 * @author Tom
 *
 */
public class SendMsgServlet extends BaseServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendMsg(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendMsg(request, response);
	}
	
	private void sendMsg(HttpServletRequest request, HttpServletResponse response) {
		HttpServletRequest hrequest = (HttpServletRequest) request;//web资源
		JSONObject r = new JSONObject();
		UpdateRejectStatusService update = new UpdateRejectStatusService();
		UserDataFromRoleService user = new UserDataFromRoleService();
		try {
			//获取风险事件id
			String fxsjId = java.net.URLDecoder.decode(hrequest.getParameter("fxsj_id"), "UTF-8");
			//System.out.println("name:" + name);
			//获取状态
			String status = update.getStatusById(fxsjId);
			//如果状态已提交，发给审核人
			if(KeyUtil.getKeyValue("YTJ").equals(status)){
				String phoneNum = user.getUserPhoneByRoleName(KeyUtil.getKeyValue("SH"));
				System.out.println("获取手机号码："+phoneNum);
				SendMsgUtil.sentMsg(fxsjId, 0, phoneNum);
			}else if(KeyUtil.getKeyValue("YSB").equals(status)){
				String phoneNum = user.getUserPhoneByRoleName(KeyUtil.getKeyValue("JC"));
				System.out.println("获取手机号码："+phoneNum);
				SendMsgUtil.sentMsg(fxsjId, 2, phoneNum);
			}else{
				System.out.println("-------没有匹配到状态"+status+"---------");
				System.out.println("-------风险事件id"+fxsjId+"---------");
			}
			r.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			r.put("success", false);
		}
		responseOutWithJson(response, r);
	}

}
