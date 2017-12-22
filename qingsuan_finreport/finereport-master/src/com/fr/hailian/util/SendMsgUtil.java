package com.fr.hailian.util;

import com.fr.hailian.core.Constants;

/***
 * 发送短信工具类
 * @author Tom
 *
 */
public class SendMsgUtil {
	
	/***
	 * 发送短信
	 * 发送号码。多个号码请用“,”分隔。最多1000个号码
	 */
	public static String sentMsg(String fxsjId,int type,String phoneNum){
		String param = "";
		param += "uid="+Constants.MSG_USER;
		param += "&upsd="+Constants.MSG_USER_PWD;
		param += "&sendtele="+phoneNum;
		String msg = Constants.MSG_MODEL[type];
		msg = msg.replace("NUM", fxsjId);
		System.out.println(msg);
		param += "&msg="+msg;
		param += "&sign="+Constants.MSG_USER_SIGN;
		return HttpClientUtil.sendPostRequest(Constants.MSG_API_URL, param,true);
	}
	
	public static void main(String[] args){
		String result = sentMsg("111",1,"");
		System.out.println(result);
	}

}
