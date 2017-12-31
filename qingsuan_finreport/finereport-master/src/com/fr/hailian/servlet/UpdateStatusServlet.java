package com.fr.hailian.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.service.SynchronizeStatusService;
import com.fr.hailian.service.UpdateRejectStatusService;
import com.fr.hailian.service.UserDataFromRoleService;
import com.fr.hailian.util.KeyUtil;
import com.fr.hailian.util.SendMsgUtil;
/***
 * 更新驳回时，添加处理人，并发送短信
 * 更新处理逻辑
 * 1.先判断是通过还是驳回
 * 2.驳回添加处理人
 * 3.通过直接发短信
 * @author Tom
 *
 */
public class UpdateStatusServlet extends BaseServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		updateStatus(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		updateStatus(request, response);
	}
	
	private void updateStatus(HttpServletRequest request, HttpServletResponse response) {
		HttpServletRequest hrequest = (HttpServletRequest) request;//web资源
		JSONObject r = new JSONObject();
		UpdateRejectStatusService update = new UpdateRejectStatusService();
		UserDataFromRoleService user = new UserDataFromRoleService();
		SynchronizeStatusService service = new SynchronizeStatusService();
		try {
			//获取风险事件id
			String fxsjId = java.net.URLDecoder.decode(hrequest.getParameter("fxsj_id"), "UTF-8");
			String status = java.net.URLDecoder.decode(hrequest.getParameter("status"), "UTF-8");
			System.out.println("status:"+status);
			//获取类型，大宗或者权益
			String type = update.getTypeById(fxsjId);
			System.out.println("获取到的type："+type);
			String phoneNum = "";
			//驳回，更新，发送短信 1
			//type=1是大宗，type=2是权益
			if(KeyUtil.getKeyValue("BH").equals(status)){
				//System.out.println("name:" + name);
				System.out.println("状态是驳回");
				phoneNum = update.doUpdateStatus(fxsjId);
				System.out.println("发送号码："+phoneNum);
				System.out.println("状态驳回");
				//发送驳回短信
				System.out.println("-----------发送短信开始-------------");
				SendMsgUtil.sentMsg(fxsjId, 1, phoneNum);
				System.out.println("-----------发送短信结束-------------");
			}else if(KeyUtil.getKeyValue("SB").equals(status)&&"1".equals(type)){
				//通过直接给上级发送短信 2
				phoneNum = user.getUserPhoneByRoleName(KeyUtil.getKeyValue("DZJCR"));
				//String phoneNum = "123";
				System.out.println("-----------发送短信开始-------------");
				SendMsgUtil.sentMsg(fxsjId, 2, phoneNum);
				System.out.println("-----------发送短信结束-------------");
				//System.out.println("发送号码："+phoneNum);
				System.out.println("状态是已上报");
			}else if(KeyUtil.getKeyValue("SB").equals(status)&&"2".equals(type)){
				//通过直接给上级发送短信 2
				phoneNum = user.getUserPhoneByRoleName(KeyUtil.getKeyValue("QYJCR"));
				//String phoneNum = "123";
				System.out.println("-----------发送短信开始-------------");
				SendMsgUtil.sentMsg(fxsjId, 2, phoneNum);
				System.out.println("-----------发送短信结束-------------");
				//System.out.println("发送号码："+phoneNum);
				System.out.println("状态是已上报");
			}else{
				System.out.println("已排查，不用发送");
			}
			//同步数据 先进行数据同步
			System.out.println("风险事件id："+fxsjId);
			HashMap<String, String> map = service.getDataById(fxsjId);
			//1说明获取是成功的
			System.out.println("获取状态："+map.get("success"));
			if("1".equals(map.get("success"))){
				service.synchronizeStatus(map, fxsjId);
				System.out.println("同步成功");
			}else{
				System.out.println("未查询到相关数据");
			}
			//驳回，更新，发送短信 1
			/*if(KeyUtil.getKeyValue("BH").equals(status)){
				//System.out.println("name:" + name);
				//String phoneNum = update.doUpdateStatus(fxsjId);
				System.out.println("发送号码："+phoneNum);
				//发送驳回短信
				//SendMsgUtil.sentMsg(fxsjId, 1, phoneNum);
			}else if(KeyUtil.getKeyValue("SB").equals(status)){
				//通过直接给上级发送短信 2
				//phoneNum = user.getUserPhoneByRoleName(KeyUtil.getKeyValue("JC"));
				//String phoneNum = "123";
				//SendMsgUtil.sentMsg(fxsjId, 2, phoneNum);
				System.out.println("发送号码："+phoneNum);
			}else{
				System.out.println("已排查，不用发送");
			}*/
			r.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			r.put("success", false);
		}
		responseOutWithJson(response, r);
	}

}
