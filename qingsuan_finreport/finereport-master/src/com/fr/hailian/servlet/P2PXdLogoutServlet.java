package com.fr.hailian.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fr.fs.base.entity.User;
import com.fr.fs.control.UserControl;
import com.fr.fs.web.FSConstants;
import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.core.MySessionContext;
import com.fr.hailian.util.RoleUtil;
import com.fr.json.JSONObject;
import com.fr.stable.Constants;

/**
 * 
 * @todo   P2P、小贷注销改造-与大宗权益打通
 * @time   2018年4月21日 上午10:57:34
 * @author zuoqb
 */
public class P2PXdLogoutServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8091436245999618392L;

	/**
	 * Constructor of the object.
	 */
	public P2PXdLogoutServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		overwriteLogout(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		overwriteLogout(request, response);
	}

	private void overwriteLogout(HttpServletRequest request, HttpServletResponse response) {
		try {
			String userId = request.getParameter("uid");
			String sessionId=request.getParameter("sessionId");
			UserControl.getInstance().logout(Long.parseLong(userId));
			//HttpSession session = request.getSession();
			HttpSession session = MySessionContext.getSession(sessionId);
			System.out.println("P2P、小贷退出----"+userId+"---"+sessionId+"---"+session);
			if(session!=null){
				session.removeAttribute(FSConstants.P_KEYS.PRIVILEGE_AUTHENCATION_KEY);
				session.removeAttribute(Constants.P.PRIVILEGE_USERNAME);
				session.removeAttribute(Constants.P.PRIVILEGE_AUTHORITY);
				session.removeAttribute(Constants.P.PRIVILEGE_DEPARTMETN_AND_POST);
				session.removeAttribute(Constants.P.FR_CURRENT_PRIVILEGE_LOADER);
			}
			//去首页
			//response.sendRedirect("/WebReport/ReportServer?op=fs");
			System.out.println("退出成功----");
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

	public void init() throws ServletException {
	}

}
