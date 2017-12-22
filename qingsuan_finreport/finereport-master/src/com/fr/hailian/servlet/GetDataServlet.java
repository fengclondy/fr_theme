package com.fr.hailian.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.util.HttpClientUtil;

/***
 * 通过接口获取数据
 * @author Tom
 *
 */
public class GetDataServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//overwriteImportInfo(request, response);
	}
	
	public static void main(String[] args){
		String url = "http://manager.wxtxsms.cn/smsport/feePost.aspx";
		//Map<String,String> param = null;
//		HashMap param = new HashMap();
		Map<String,String> param = new HashMap<String, String>();
		param.put("uid", "qdqszx");
		param.put("upsd", "b0ecbc94863ba01db82b67a3a4b01c7f");
		String result = HttpClientUtil.sendPostRequest(url,param,null,null);
		System.out.println(result);
	}

}
