package com.fr.hailian.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.fs.base.entity.User;
import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.util.RoleUtil;
import com.fr.json.JSONObject;

/**
 * 
 * @todo   获取用户名称
 * @time   2018年4月8日 下午2:55:03
 * @author zuoqb
 */
public class LoginUserInfoServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8091436245999618392L;

	/**
	 * Constructor of the object.
	 */
	public LoginUserInfoServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getLoginUserInfo(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getLoginUserInfo(request, response);
	}
	/**
	 * 
	 * @todo   获取用户相关信息
	 * @time   2018年4月8日 下午6:17:39
	 * @author zuoqb
	 * @return_type   void
	 */
	private void getLoginUserInfo(HttpServletRequest request, HttpServletResponse response) {
		JSONObject r = new JSONObject();
		try {
			User  user = RoleUtil.getCurrentUser(request);
			user.setEmail(request.getSession().getId());
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.append(com.alibaba.fastjson.JSONObject.toJSONString(user));
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
		responseOutWithJson(response, r);
	}

	public void init() throws ServletException {
	}

}
