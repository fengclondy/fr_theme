package com.fr.hailian.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.fs.base.entity.User;
import com.fr.fs.control.UserControl;
import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.service.ReadAllMsgService;
import com.fr.hailian.service.ReadMsgService;
import com.fr.hailian.service.UserDataFromRoleService;
import com.fr.hailian.util.KeyUtil;
/**
 * 
 * @todo  将所有的消息未读数置0--P2P、小贷
 * @time   2018年4月21日 下午1:04:25
 * @author zuoqb
 */
public class P2PXdReadAllMsgServlet extends BaseServlet{
	
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
		ReadAllMsgService readAllMsg = new ReadAllMsgService();
		UserDataFromRoleService user = new UserDataFromRoleService();
		ReadMsgService readMsg = new ReadMsgService();
		String username="";
		String userId = hrequest.getParameter("uid");
		String type = hrequest.getParameter("type");
		try {
			User u=UserControl.getInstance().getUser(Long.parseLong(userId));
			if(u!=null){
				username=u.getUsername();
			}
			String roleName = user.getRoleNameByUserId(userId+"");
			//判断当前用户为何种角色，通过角色去寻找当前的用户
			//如果角色为处理人,把未读的风险事件也置为已读
			if(!"".equals(roleName)){
				if(roleName.contains(KeyUtil.getKeyValue("DZ"))){
					readAllMsg.setAllToRead("DEAL",type);
					readMsg.setAllToRead(username,type);
				}else if(KeyUtil.getKeyValue("SH").equals(roleName)){
					readAllMsg.setAllToRead("AUDIT",type);
				}else if(KeyUtil.getKeyValue("JC").equals(roleName)){
					readAllMsg.setAllToRead("JUDGE",type);
				}
			}
			//先获取消息总数
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.append(com.alibaba.fastjson.JSONObject.toJSONString(true));
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
