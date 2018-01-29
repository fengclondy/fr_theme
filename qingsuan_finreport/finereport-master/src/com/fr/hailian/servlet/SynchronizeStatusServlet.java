package com.fr.hailian.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.service.SynchronizeStatusService;
import com.fr.json.JSONObject;

/***
 * 同步状态
 * 把审核历史的表中的状态同步到风险事件表中
 * @author Tom
 *
 */
public class SynchronizeStatusServlet extends BaseServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		synchronizeStatus(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		synchronizeStatus(request, response);
	}
	
	private void synchronizeStatus(HttpServletRequest request, HttpServletResponse response) {
		HttpServletRequest hrequest = (HttpServletRequest) request;//web资源
		JSONObject r = new JSONObject();
		SynchronizeStatusService service = new SynchronizeStatusService();
		try {
			//获取风险事件id
			String fxsjId = java.net.URLDecoder.decode(hrequest.getParameter("fxsj_id"), "UTF-8");
			HashMap<String, String> map = service.getDataById(fxsjId);
			//1说明获取是成功的
			if("1".equals(map.get("success"))){
				service.synchronizeStatus(map, fxsjId);
				System.out.println("同步成功");
			}else{
				System.out.println("未查询到相关数据");
			}
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
