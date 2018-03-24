package com.qdch.core;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.core.Controller;
/**
 * 
 * @todo  p2p 小贷公共父类
 * @time   2018年3月24日 下午4:26:14
 * @author zuoqb
 */
public class BaseController extends Controller{
	/**
	 * 
	 * @todo   获取当前用户数据权限-需要测试
	 * @time   2018年3月24日 下午4:27:14
	 * @author zuoqb
	 * @return_type   String
	 */
	public String getDataScopeByUserName(){
		QdchUser user=getLoginUser();//从session或者UserUtil工具类（未封装）中获取
		if(user==null||user.getJysList()==null||user.getJysList().size()==0){
			return null;
		}
		String jysIds="";
		for(String str:user.getJysList()){
			jysIds+="'"+str+"',";
		}
		if(jysIds.length()>0){
			jysIds=jysIds.substring(0,jysIds.length()-1);
		}
		if(jysIds.length()>0){
			jysIds="("+jysIds+")";
		}
		return jysIds;
	}
	/**
	 * 
	 * @todo  获取当前登录人
	 * @time   2018年3月24日 下午4:41:54
	 * @author zuoqb
	 * @return_type   QdchUser
	 */
	public QdchUser getLoginUser() {
		return this.getSessionAttr(Constants.SESSION_USER);
	}
	/**
	 * 设置session
	 * */
	public void setSession(String key,Object value) {
		this.setSessionAttr(key, value);
	}
	public String getParasUrl() {
		String parasUrl = null;
		if (getParaMap() != null && getParaMap().size() > 0) {
			Set<String> keySet = getParaMap().keySet();
			parasUrl = "?";
			for (String key : keySet) {
				parasUrl = parasUrl + key + "=" + getParaMap().get(key)[0]
						+ "&";
			}
			parasUrl = parasUrl.substring(0, parasUrl.length() - 1);
		}
		return parasUrl;
	}


	public String getRequestUrl() {
		return getRequest().getRequestURL()
				+ (getParasUrl() == null ? "" : getParasUrl());
	}

	public String getRequestShortUrl() {
		return getRequest().getRequestURI()
				+ (getParasUrl() == null ? "" : getParasUrl());
	}

	public String getIpAddr() {
		HttpServletRequest request = this.getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}



	// ***********************重写cookie操作，用于对汉字的转码************************//

	@Override
	public Controller setCookie(String name, String value, int maxAgeInSeconds) {
		if (value != null) {
			try {
				value = URLEncoder.encode(value, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return super.setCookie(name, value, maxAgeInSeconds);
	}

	@Override
	public String getCookie(String name) {
		String value = super.getCookie(name);
		if (value != null) {
			try {
				value = URLDecoder.decode(value, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	
	
	
	
	
}
