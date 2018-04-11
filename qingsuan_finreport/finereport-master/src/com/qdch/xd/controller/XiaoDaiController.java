package com.qdch.xd.controller;

import com.fr.hailian.core.QdchUser;
import com.qdch.core.BaseController;
//@ControllerBind(controllerKey = "/jfinal/demo", viewPath = "/p2p")
/***
 * demo controller
 * @author Tom
 *
 */
public class XiaoDaiController extends BaseController {
    
    public void p2p() {
    	QdchUser user=getLoginUser();
    	System.out.println(user);
        render("p2p/pages/index.html");
     }
    
    public void xd() {
    	QdchUser user=getLoginUser();
    	System.out.println(user);
        render("xd/pages/index.html");
     }
}
