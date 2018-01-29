package com.fr.hailian.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.service.UpdateRejectStatusService;
import com.fr.hailian.service.UserDataFromRoleService;
import com.fr.hailian.util.KeyUtil;
import com.fr.hailian.util.SendMsgUtil;
import com.fr.json.JSONObject;
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
			//获取类型，大宗或者权益
			String type = update.getTypeById(fxsjId);
			System.out.println("获取到的type："+type);
			//如果状态已提交，发给审核人
			System.out.println("-----------发送短信开始-------------");
			//type=1是大宗，type=2是权益
			if(KeyUtil.getKeyValue("YTJ").equals(status)&&"1".equals(type)){
				String phoneNum = user.getUserPhoneByRoleName(KeyUtil.getKeyValue("DZSHR"));
				System.out.println("获取手机号码："+phoneNum);
				String res = SendMsgUtil.sentMsg(fxsjId, 0, phoneNum);
				System.out.println("短信返回值："+res);
			}else if(KeyUtil.getKeyValue("YSB").equals(status)&&"1".equals(type)){
				String phoneNum = user.getUserPhoneByRoleName(KeyUtil.getKeyValue("DZJCR"));
				System.out.println("获取手机号码："+phoneNum);
				String res = SendMsgUtil.sentMsg(fxsjId, 2, phoneNum);
				System.out.println("短信返回值："+res);
			}else if(KeyUtil.getKeyValue("YTJ").equals(status)&&"2".equals(type)){
				String phoneNum = user.getUserPhoneByRoleName(KeyUtil.getKeyValue("QYSHR"));
				System.out.println("获取手机号码："+phoneNum);
				String res = SendMsgUtil.sentMsg(fxsjId, 0, phoneNum);
				System.out.println("短信返回值："+res);
			}else if(KeyUtil.getKeyValue("YSB").equals(status)&&"2".equals(type)){
				String phoneNum = user.getUserPhoneByRoleName(KeyUtil.getKeyValue("QYJCR"));
				System.out.println("获取手机号码："+phoneNum);
				String res = SendMsgUtil.sentMsg(fxsjId, 2, phoneNum);
				System.out.println("短信返回值："+res);
			}else{
				System.out.println("-------没有匹配到状态"+status+"---------");
				System.out.println("-------风险事件id"+fxsjId+"---------");
			}
			System.out.println("-----------发送短信结束-------------");
			r.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				r.put("success", false);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		responseOutWithJson(response, r);
	}

}
