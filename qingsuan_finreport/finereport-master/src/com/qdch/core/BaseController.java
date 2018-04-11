package com.qdch.core;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fr.hailian.core.QdchUser;
import com.fr.hailian.model.RoleMenuModel;
import com.fr.hailian.model.RoleModel;
import com.fr.hailian.util.HttpClientUtil;
import com.fr.hailian.util.HttpJsonHelper;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
/**
 * 
 * @todo  p2p 小贷公共父类
 * @time   2018年3月24日 下午4:26:14
 * @author zuoqb
 */
public class BaseController extends Controller{
	/**
	 * 
	 * @todo   获取当前用户数据权限-其实在这边不需要再次分发-以后优化
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
		if(jysIds.length()>0){
			jysIds="("+user.getDataScope()+")";
		}
		return jysIds;
	}
	/**
	 * 
	 * @todo   p2p数据权限-其实在这边不需要再次分发-以后优化
	 * @time   2018年4月10日 下午6:22:09
	 * @author zuoqb
	 * @return_type   String
	 */
	public String getDataScopeByUserNameForP2p(){
		QdchUser user=getLoginUser();//从session或者UserUtil工具类（未封装）中获取
		if(user==null||user.getJysList()==null||user.getJysList().size()==0){
			return null;
		}
		String jysIds="";
		if(jysIds.length()>0){
			jysIds="("+user.getDataScope()+")";
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
	/**
	 * 
	 * @todo 用户认证处理
	 * @time   2018年4月10日18:20:52
	 * @author zuoqb
	 * @return_type   QdchUser
	 */
	public  QdchUser dealUser(BaseController c){
		QdchUser user=new QdchUser();
		String userName=c.getPara("userName");
		String roleType=c.getPara("type");
		if(StringUtils.isNotBlank(userName)){
			//获取用户信息
			String url="http://localhost:8075/WebReport/getAuthorityUserInfo?userName="+userName;
			//String url=com.fr.hailian.core.Constants.WEB_DOMAIN+"/getAuthorityUserInfo?userName="+userName;
			if(StringUtils.isNotBlank(roleType)){
				url+="&roleType="+roleType;
			};
			String result=HttpClientUtil.sendGetRequest(url, null);
			if(StringUtils.isNotBlank(result)){
				JSONObject arr;
				try {
					arr = new JSONObject(result);
					JSONArray ja=new JSONArray(arr.get("roles").toString());
					List<Object> rolsList=HttpJsonHelper.toDoubleList(ja, RoleModel.class, RoleMenuModel.class, "menu");
					List<RoleModel> roles=new ArrayList<RoleModel>();
					for(Object o:rolsList){
						RoleModel r=(RoleModel)o;
						roles.add(r);
					}
					user.setRoles(roles);
					user.setId(arr.get("id").toString());
					user.setUsername(arr.getString("username"));
					user.setRealname(arr.getString("realname"));
					user.setMobile(arr.getString("mobile"));
					user.setEmail(arr.getString("email"));
					user.setDataScope(arr.getString("dataScope"));
					if(StringUtils.isNotBlank(arr.getString("dataScope"))){
						user.setJysList(Arrays.asList(arr.getString("dataScope").replaceAll("'", "").split(",")));
					}
					//user=(QdchUser) HttpJsonHelper.toBeanContainList(arr, QdchUser.class, RoleModel.class, "roles");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		c.setSession(Constants.SESSION_USER, user);
		return user;
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
	/**
	 * 
	 * @todo   统一json数据返回
	 * @time   2018年4月1日 上午10:04:17
	 * @author zuoqb
	 * @return_type   void
	 */
	@SuppressWarnings("unchecked")
	public void mRenderJson(Object result){
		if(StringUtils.isNotBlank(getPara("jsonp"))){
			//跨域处理
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			Map json = new HashMap();
			String callback = getPara("callback");
			json.put("data", result);
			String jsonp = callback + "(" + JsonKit.toJson(json) + ")";//返回的json 格式要加callback()
			renderJson(jsonp);
		}else{
			renderJson(result);
		}
	}

	public void mRenderError(Object result){
		if(StringUtils.isNotBlank(getPara("jsonp"))){
			//跨域处理
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			Map json = new HashMap();
			String callback = getPara("callback");
			json.put("data", result);
			String jsonp = callback + "(" + JsonKit.toJson(json) + ")";//返回的json 格式要加callback()
			renderText(result.toString());
		}else{
			renderJson(result);
		}
	}
	
	
	
	
}
