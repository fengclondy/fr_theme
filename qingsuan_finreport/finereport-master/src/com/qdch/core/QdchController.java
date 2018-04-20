package com.qdch.core;

import org.apache.commons.lang.StringUtils;

import com.fr.hailian.core.QdchUser;
import com.jfinal.kit.PropKit;
import com.qdch.core.BaseController;
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
	 public void getUnReadMsg(){
		 QdchUser user=getLoginUser();
		 String jysStr=user.getDataScope();
		 //String type="";
		 //String url=com.fr.hailian.core.Constants.WEB_DOMAIN+"/getNotReadAllMsg?jysStr="+jysStr+"&uid="+uid;
	 }

}
