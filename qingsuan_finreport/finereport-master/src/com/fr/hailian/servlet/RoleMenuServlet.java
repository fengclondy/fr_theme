package com.fr.hailian.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.alibaba.fastjson.JSONArray;
import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.model.RoleMenuModel;
import com.fr.hailian.service.UserDataFromRoleService;

/**
 * 
 * @time   2017年12月22日13:53:34
 * @author zuoqb
 * @todo   根据自定义角色获取菜单
 */
public class RoleMenuServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8091436245999618392L;

	/**
	 * Constructor of the object.
	 */
	public RoleMenuServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		overwriteRoleMenu(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		overwriteRoleMenu(request, response);
	}

	private void overwriteRoleMenu(HttpServletRequest request, HttpServletResponse response) {
		JSONObject r = new JSONObject();
		HttpServletRequest hrequest = (HttpServletRequest) request;//web资源
		String roleType = "";
		try {
			//"大宗","权益"
			roleType = hrequest.getParameter("roleType");
			System.err.println(roleType);
			String roleName="";
			if("0".equals(roleType)){
				roleName="大宗";
			}else if("1".equals(roleType)){
				roleName="权益";
			}
			List<RoleMenuModel> list=UserDataFromRoleService.getMenuByRoleName(roleName);
			r.put("rolemenu", JSONArray.toJSONString(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		responseOutWithJson(response, r);
	}

	public void init() throws ServletException {
	}

}
