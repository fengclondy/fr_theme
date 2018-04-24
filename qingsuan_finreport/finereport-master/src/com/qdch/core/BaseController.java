package com.qdch.core;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
import com.fr.hailian.util.SortListUtil;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.PropKit;
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
		return "("+user.getDataScope()+")";
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
		return "("+user.getDataScope()+")";
	}
	/**
	 * 
	 * @todo  获取当前登录人
	 * @time   2018年3月24日 下午4:41:54
	 * @author zuoqb
	 * @return_type   QdchUser
	 */
	public QdchUser getLoginUser() {
		QdchUser user=this.getSessionAttr(Constants.SESSION_USER);
		if(user==null){
			user=new QdchUser();
		}
		return user;
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
	 * @todo   处理菜单
	 * @time   2018年4月11日 下午4:17:30
	 * @author zuoqb
	 * @return_type   List<RoleMenuModel>
	 */
	public List<RoleMenuModel> dealMenus(QdchUser user){
		List<RoleMenuModel> menus=new ArrayList<RoleMenuModel>();
		if(user!=null&&StringUtils.isNotBlank(user.getId())){
			Set<RoleMenuModel> menuSet = new HashSet<RoleMenuModel>();
			List<RoleModel> roles=user.getRoles();
			//先去除重复
			for(RoleModel role:roles){
				for(RoleMenuModel menu:role.getMenu()){
					menuSet.add(menu);
				}
			}
			//按照pname/pid分组
			List<RoleMenuModel> allMenu=new ArrayList<RoleMenuModel>();
			allMenu.addAll(menuSet);
			Collections.reverse(allMenu) ;
			HashMap<String,Set<RoleMenuModel>> map=new HashMap<String, Set<RoleMenuModel>>();
			for(RoleMenuModel m:allMenu){
				String pname=m.getPname();
				Set<RoleMenuModel> s=map.get(pname);
				if(s==null){
					s = new HashSet<RoleMenuModel>();
					s.add(m);
				}else{
					s.add(m);
				}
				map.put(pname, s);
			}
			//循环map
			Set<String> pNames=map.keySet();
			List<String> names=new ArrayList<String>();
			names.addAll(pNames);
			Collections.reverse(names) ;
			for(String pname:names){
				RoleMenuModel m=new RoleMenuModel();
				m.setName(pname);
				Set<RoleMenuModel> children=map.get(pname);
				List<RoleMenuModel> cmenus=new ArrayList<RoleMenuModel>();
				cmenus.addAll(children);
				//Collections.reverse(cmenus) ;
				List<RoleMenuModel> f = dealMenu(cmenus);
				m.setChildren(f);
				menus.add(m);
			}
			
		}
		return menus;
	}
	private List<RoleMenuModel> dealMenu(List<RoleMenuModel> cmenus) {
		//再次去重
		List<RoleMenuModel> f=new ArrayList<RoleMenuModel>();
		 for (RoleMenuModel cd:cmenus) {
			 boolean has=false;
			 for(RoleMenuModel x:f){
				 if(x.getId().equals(cd.getId())){
					 has=true;
					 break;
				 }
			 }
		    if(!has){
		        f.add(cd);
		    }
		}
		//子排序 按照sortIndex
		SortListUtil.sort(f, "sortIndex",SortListUtil.ASC);
		return f;
	}
	/**
	 * 
	 * @todo   处理审批流权限
	 * @time   2018年4月11日 下午4:46:56
	 * @author zuoqb
	 * @return_type   String
	 */
	public String judgeActivity(QdchUser user) {
		String active = "";
		if (user != null && StringUtils.isNotBlank(user.getId())) {
			List<RoleModel> roles = user.getRoles();
			for (RoleModel role : roles) {
				String roleName = role.getRoleName();
				if (StringUtils.isNotBlank(roleName)) {
					if (roleName.contains("处理")) {
						active = "处理";
					} else if (roleName.contains("审批")) {
						active = "审批";
					} else if (roleName.contains("决策")) {
						active = "决策";
					}
				}
				if (StringUtils.isNotBlank(active)) {
					break;
				}
			}
		}
		return active;
	}
	/**
	 * 
	 * @todo 用户认证处理
	 * @time   2018年4月10日18:20:52
	 * @author zuoqb
	 * @return_type   QdchUser
	 */
	public  QdchUser dealUser(BaseController c){
		QdchUser user=getLoginUser();
		String userName=c.getPara("userName");
		String roleType=c.getPara("type");
		String uid=c.getPara("uid");
		String sessionId=c.getPara("sessionId");
		if(StringUtils.isNotBlank(roleType)||StringUtils.isNotBlank(userName)){
			//切换用户或者类型重新认证
			user.setType(roleType);
			//获取用户信息
			//String url="http://localhost:8075/WebReport/getAuthorityUserInfo?userName="+userName+"&uid="+uid;
			String url="";
			 if("false".equals(PropKit.get("isDev"))){
				 //正式发布 正式环境也需要写localhost  服务器设置了外网权限
				 url="http://localhost/WebReport/getAuthorityUserInfo?userName="+userName+"&uid="+uid;
			 }else{
				 url=PropKit.get("webSite")+"/getAuthorityUserInfo?userName="+userName+"&uid="+uid;
			 }
			
			//
			//String url="http://localhost/WebReport/getAuthorityUserInfo?userName="+userName+"&uid="+uid;
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
					user.setSessionId(sessionId);
					if(arr.has("id")){
						user.setId(arr.get("id").toString());
					}
					if(arr.has("username")){
						user.setUsername(arr.getString("username"));
					}
					if(arr.has("realname")){
						user.setRealname(arr.getString("realname"));
					}
					if(arr.has("mobile")){
						user.setMobile(arr.getString("mobile"));
					}
					if(arr.has("email")){
						user.setEmail(arr.getString("email"));
					}
					if(arr.has("dataScope")){
						user.setDataScope(arr.getString("dataScope"));
						if(StringUtils.isNotBlank(arr.getString("dataScope"))){
							user.setJysList(Arrays.asList(arr.getString("dataScope").replaceAll("'", "").split(",")));
						}
					}
					//user=(QdchUser) HttpJsonHelper.toBeanContainList(arr, QdchUser.class, RoleModel.class, "roles");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			//处理菜单
			List<RoleMenuModel> menus=dealMenus(user);
			user.setMenus(menus);
			//处理审批流权限
			user.setActivity(judgeActivity(user));
		
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
