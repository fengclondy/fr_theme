package com.fr.hailian.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.service.OrgInfoService;
/***
 * 获取机构的信息
 * @author Tom
 *
 */
public class OrgInfoServlet extends BaseServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getOrgInfo(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getOrgInfo(request, response);
	}
	
	private void getOrgInfo(HttpServletRequest request, HttpServletResponse response) {
		HttpServletRequest hrequest = (HttpServletRequest) request;//web资源
		JSONObject r = new JSONObject();
		//通过帆软方法获取用户名
		OrgInfoService org = new OrgInfoService();
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			String name = java.net.URLDecoder.decode(hrequest.getParameter("jysname"), "UTF-8");
			map = org.getOrgInfo(name);
			//1说明有数据
			if("1".equals(map.get("success"))){
				r.put("userCount", (map.get("userCount")!=null)?map.get("userCount"):"0");
				r.put("cdzj", (map.get("cdzj")!=null)?map.get("cdzj"):"0");
				r.put("jye", (map.get("jye")!=null)?map.get("jye"):"0");
				r.put("success", true);
			}else{
				r.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		responseOutWithJson(response, r);
	}

}
