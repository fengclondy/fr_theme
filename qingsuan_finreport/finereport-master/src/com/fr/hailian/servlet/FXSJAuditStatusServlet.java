package com.fr.hailian.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.core.Constants;
import com.fr.hailian.service.FXSJAuditService;
import com.fr.json.JSONObject;

public class FXSJAuditStatusServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getAuditStatus(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getAuditStatus(request, response);
	}
	
	private void getAuditStatus(HttpServletRequest request, HttpServletResponse response) {
		HttpServletRequest hrequest = (HttpServletRequest) request;//web资源
		JSONObject r = new JSONObject();
		FXSJAuditService fxsj = new FXSJAuditService();
		try {
			//风险事件id
			String fxsjId = java.net.URLDecoder.decode(hrequest.getParameter("fxsjid"), "UTF-8");
			//提报人id
			String reportId = java.net.URLDecoder.decode(hrequest.getParameter("reportid"), "UTF-8");
			//处理人id
			String dealId = java.net.URLDecoder.decode(hrequest.getParameter("dealid"), "UTF-8");
			//主键id
			String id = UUID.randomUUID().toString().trim().replaceAll("-", "");
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			//插入时间
			String insertTime = dateFormat.format(now);
			fxsj.doAudit(id, fxsjId, reportId, dealId, insertTime, Constants.FXSJ_AUDITING);
			//System.out.println("name:" + name);
			//先获取消息总数
			r.put("unReadCount", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		responseOutWithJson(response, r);
	}
}
