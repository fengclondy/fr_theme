package com.fr.hailian.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.fs.base.entity.User;
import com.fr.fs.control.UserControl;
import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.service.NotReadMsgService;
/**
 * 
 * @todo   P2P、小贷获取未读条数-风险事件
 * @time   2018年4月21日 下午2:35:14
 * @author zuoqb
 */
public class P2PXdFxsjNotReadMsgServlet extends BaseServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getNotReadMsg(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getNotReadMsg(request, response);
	}
	
	private void getNotReadMsg(HttpServletRequest request, HttpServletResponse response) {
		HttpServletRequest hrequest = (HttpServletRequest) request;//web资源
		NotReadMsgService notReadMsg = new NotReadMsgService();
		String uid=hrequest.getParameter("uid");
		String type = hrequest.getParameter("type");
		String jys=hrequest.getParameter("jysStr");
		String username="";
		try {
			User u=UserControl.getInstance().getUser(Long.parseLong(uid));
			if(u!=null){
				username=u.getUsername();
			}
			jys = "("+jys+")";
			long count = notReadMsg.getUnReadMsgCount(username,type,jys);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.append(com.alibaba.fastjson.JSONObject.toJSONString(count));
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
