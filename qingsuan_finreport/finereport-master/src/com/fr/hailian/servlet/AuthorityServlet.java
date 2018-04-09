package com.fr.hailian.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.model.RoleMenuModel;
import com.fr.hailian.util.RoleUtil;
import com.fr.json.JSONObject;
import com.qdch.core.QdchUser;

/**
 * 
 * @todo   根据用户名称获取用户信息（P2P、小贷调用）
 * @time   2018年4月8日 下午2:55:03
 * @author zuoqb
 */
public class AuthorityServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8091436245999618392L;

	/**
	 * Constructor of the object.
	 */
	public AuthorityServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getAuthorityUserInfo(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getAuthorityUserInfo(request, response);
	}
	/**
	 * 
	 * @todo   获取用户相关信息
	 * @time   2018年4月8日 下午6:17:39
	 * @author zuoqb
	 * @return_type   void
	 */
	private void getAuthorityUserInfo(HttpServletRequest request, HttpServletResponse response) {
		JSONObject r = new JSONObject();
		HttpServletRequest hrequest = (HttpServletRequest) request;//web资源
		String roleType = "";
		String userName="";//用户名字 这个必须传 跨系统了 session不共享
		try {
			//"大宗","权益"
			roleType = hrequest.getParameter("roleType");
			userName = hrequest.getParameter("userName");
			String roleName="";
			if("0".equals(roleType)){
				roleName="大宗";
			}else if("1".equals(roleType)){
				roleName="权益";
			}else if("2".equals(roleType)){
				roleName="小贷";
			}else if("3".equals(roleType)){
				roleName="P2P";
			}
			System.out.println("---roleName-------"+roleName);
			//QdchUser user=RoleUtil.authorityUser(request, userName, roleName);
			QdchUser user=new QdchUser();
			List<RoleMenuModel> list=new ArrayList<RoleMenuModel>();
			r.put("user", JSONArray.toJSONString(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		responseOutWithJson(response, r);
	}

	public void init() throws ServletException {
	}

}
