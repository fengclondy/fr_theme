/**
 * 拦截器
 */

package com.qdch.intercept;


import java.util.ArrayList;
import java.util.List;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.qdch.core.BaseController;




/**
 * 
 * @todo   拦截器
 * @time   2018年4月11日 下午7:12:20
 * @author zuoqb
 */
public class SecurityInterceptor implements Interceptor {
	
	
	/**
	 * 不需要登录路径
	 */
	public final static List<String> passUrls= new ArrayList<String>(){
		private static final long serialVersionUID = 1L;
	{
		
		//登录地址
		add("/login");
	}};
	
	public void intercept(Invocation ai) {
		BaseController c = (BaseController) ai.getController();
		c.dealUser(c);
		c.setAttr("user", c.getLoginUser());
		ai.invoke();
	}
}
