package com.fr.hailian.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.core.Constants;
import com.fr.hailian.model.RoleMenuModel;
import com.fr.hailian.service.UserDataFromRoleService;
import com.fr.json.JSONObject;

/**
 * 
 * @todo  获取菜单明细信息
 * @time   2018年4月5日 下午3:22:21
 * @author zuoqb
 */
public class MenuDetailServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8091436245999618392L;

	/**
	 * Constructor of the object.
	 */
	public MenuDetailServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getMenuDetail(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getMenuDetail(request, response);
	}

	private void getMenuDetail(HttpServletRequest request, HttpServletResponse response) {
		JSONObject r = new JSONObject();
		HttpServletRequest hrequest = (HttpServletRequest) request;//web资源
		String menuId = "";
		try {
			menuId = hrequest.getParameter("menuId");
			RoleMenuModel menu=UserDataFromRoleService.getMenuDetail(menuId);
			System.out.println(menuId);
			System.out.println(menu);
			System.out.println(JSONArray.toJSONString(menu));
			r.put("menu", JSONArray.toJSONString(menu));
		} catch (Exception e) {
			e.printStackTrace();
		}
		responseOutWithJson(response, r);
	}

	public void init() throws ServletException {
	}

}
