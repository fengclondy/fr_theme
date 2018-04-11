package com.qdch.core;

import org.apache.commons.lang.StringUtils;

import com.fr.hailian.core.QdchUser;
import com.qdch.core.BaseController;
/**
 * 
 * @todo   p2p 小贷权限认证
 * @time   2018年4月10日 下午6:05:48
 * @author zuoqb
 */
public class QdchController extends BaseController {
	 public void index() {
		 QdchUser user=dealUser(this);
		 if(user!=null&&StringUtils.isNotBlank(user.getId())){
			 //认证成功
			 String type=getPara("type");//认证类型 2-P2P  3-小贷
			 if("2".equals(type)){
				 redirect("/qdch/xiaodai/p2p");
			 }else  if("3".equals(type)){
				 redirect("/qdch/xiaodai/xd");
			 }
		 }else{
			 //认证失败
			 renderText("认证失败!");
		 }
	 }

}
