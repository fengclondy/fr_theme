package com.qdch.core;

import org.apache.commons.lang.StringUtils;

import com.fr.hailian.core.QdchUser;
import com.fr.hailian.util.HttpClientUtil;
import com.jfinal.kit.PropKit;
/**
 * 
 * @todo   p2p 小贷权限认证
 * @time   2018年4月10日 下午6:05:48
 * @author zuoqb
 */
public class QdchController extends BaseController {
	/**
	 * 
	 * @todo   中转
	 * @time   2018年4月20日 上午9:46:36
	 * @author zuoqb
	 * @return_type   void
	 */
	 public void index() {
		 QdchUser user=dealUser(this);
		 if(user!=null&&StringUtils.isNotBlank(user.getId())){
			 //认证成功
			 String type=getPara("type");//认证类型 2-P2P  3-小贷
			 if("false".equals(PropKit.get("isDev"))){
				 //不是开发模式 进入真正首页
				 redirect("/qdch/auth/home");
			 }else{
				 //进入静态页面
				 if("2".equals(type)){
					 redirect("/qdch/xiaodai/p2p");
				 }else  if("3".equals(type)){
					 redirect("/qdch/xiaodai/xd");
				 }
			 }
		 }else{
			 //认证失败
			 renderText("认证失败!");
		 }
	 }
	 /**
	  * @todo   系统首页
	  * @time   2018年4月20日 上午11:44:15
	  * @author zuoqb
	  * @return_type   void
	  */
	 public void home(){
		 QdchUser user=getLoginUser();
		 if("2".equals(user.getType())){
			 setAttr("title", "青岛P2P监控服务平台");
		 }else  if("3".equals(user.getType())){
			 setAttr("title", "小贷");
		 }
		 render("home/index.html");
	 }
	 /**
	  * 
	  * @todo   获取全部未处理事件数量（小信封）
	  * @time   2018年4月21日 上午8:57:07
	  * @author zuoqb
	  * @return_type   void
	  */
	 public void getUnReadMsg(){
		 QdchUser user=getLoginUser();
		 String jysStr=user.getDataScope();
		 String type="";
		 if("2".equals(user.getType())){
			 type="3";
		 }else  if("3".equals(user.getType())){
			 type="4";
		 }
		 String url=PropKit.get("webSite")+"/getP2PXdAllUnReadMsg?jysStr="+jysStr+"&type="+type+"&uid="+user.getId();
		 //String url="http://localhost:8075/WebReport/getP2PXdAllUnReadMsg?jysStr="+jysStr+"&type="+type+"&uid="+user.getId();
		
		 String result=HttpClientUtil.sendGetRequest(url, null);
		 //System.out.println(result);
		 renderJson(result);
	 }
	 /**
	  * @todo   将全部事件未读数量清空（小信封）
	  * @time   2018年4月21日 上午8:57:40
	  * @author zuoqb
	  * @return_type   void
	  */
	 public void readAllMsg(){
		 QdchUser user=getLoginUser();
		 String type="";
		 if("2".equals(user.getType())){
			 type="3";
		 }else  if("3".equals(user.getType())){
			 type="4";
		 }
		 String url=PropKit.get("webSite")+"/readAllP2PXdMsg?type="+type+"&uid="+user.getId();
		 //String url="http://localhost:8075/WebReport/readAllP2PXdMsg?type="+type+"&uid="+user.getId();
		 String result=HttpClientUtil.sendGetRequest(url, null);
		 renderJson(result);
	 }
	 
	 
	 
	 
	 
	 /**
	  * 
	  * @todo   获取全部未处理事件数量（小信封）
	  * @time   2018年4月21日 上午8:57:07
	  * @author zuoqb
	  * @return_type   void
	  */
	 public void getP2PXdAllFxsjUnReadMsg(){
		 QdchUser user=getLoginUser();
		 String jysStr=user.getDataScope();
		 String type="";
		 if("2".equals(user.getType())){
			 type="3";
		 }else  if("3".equals(user.getType())){
			 type="4";
		 }
		 String url=PropKit.get("webSite")+"/getP2PXdAllFxsjUnReadMsg?jysStr="+jysStr+"&type="+type+"&uid="+user.getId();
		 //String url="http://localhost:8075/WebReport/getP2PXdAllFxsjUnReadMsg?jysStr="+jysStr+"&type="+type+"&uid="+user.getId();
		
		 String result=HttpClientUtil.sendGetRequest(url, null);
		 renderJson(result);
	 }
	 /**
	  * @todo   将全部事件未读数量清空（小信封）
	  * @time   2018年4月21日 上午8:57:40
	  * @author zuoqb
	  * @return_type   void
	  */
	 public void p2pXdFxsjReadMsg(){
		 QdchUser user=getLoginUser();
		 String type="";
		 if("2".equals(user.getType())){
			 type="3";
		 }else  if("3".equals(user.getType())){
			 type="4";
		 }
		 String url=PropKit.get("webSite")+"/p2pXdFxsjReadMsg?type="+type+"&uid="+user.getId();
		 //String url="http://localhost:8075/WebReport/p2pXdFxsjReadMsg?type="+type+"&uid="+user.getId();
		 String result=HttpClientUtil.sendGetRequest(url, null);
		 renderJson(result);
	 }
	 
	 /**
	  * @todo   退出
	  * @time   2018年4月21日 上午8:57:40
	  * @author zuoqb
	  * @return_type   void
	  */
	 public void logout(){
		 QdchUser user=getLoginUser();
		 String url=PropKit.get("webSite")+"/logoutP2PXd?uid="+user.getId()+"&sessionId="+user.getSessionId();
		 //String url="http://localhost:8075/WebReport/logoutP2PXd?uid="+user.getId()+"&sessionId="+user.getSessionId();
		 String result=HttpClientUtil.sendGetRequest(url, null);
		 setSession(Constants.SESSION_USER, null);
		 renderJson(result);
	 }
	/* public static void main(String[] args) {
		 String url="http://localhost:8075/WebReport/getP2PXdAllFxsjUnReadMsg?type=3&uid=41&jysStr=('1')";
		 String result=HttpClientUtil.sendGetRequest(url, null);
		 System.out.println(result);
	}*/
}
