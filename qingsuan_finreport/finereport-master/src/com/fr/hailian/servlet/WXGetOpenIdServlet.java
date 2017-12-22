package com.fr.hailian.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.core.Constants;
import com.fr.hailian.util.HttpClientUtil;
/***
 * 通过code获取openId
 * @author Tom
 *
 */
public class WXGetOpenIdServlet extends BaseServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getOpenId(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getOpenId(request, response);
	}
	
	private void getOpenId(HttpServletRequest request, HttpServletResponse response) {		
		HttpServletRequest hrequest = (HttpServletRequest) request;//web资源
		try {
			String code = java.net.URLDecoder.decode(hrequest.getParameter("code"), "UTF-8");
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+Constants.APP_ID+"&secret="+Constants.APP_SECRET+"&code="+code+"&grant_type=authorization_code";
			String res = HttpClientUtil.sendGetRequest(url, "UTF-8");
			JSONObject r = new JSONObject(res);
			responseOutWithJson(response, r);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
