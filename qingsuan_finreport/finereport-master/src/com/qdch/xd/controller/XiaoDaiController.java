package com.qdch.xd.controller;

import com.qdch.core.BaseController;
//@ControllerBind(controllerKey = "/jfinal/demo", viewPath = "/p2p")
/***
 * demo controller
 * @author Tom
 *
 */
public class XiaoDaiController extends BaseController {
	//默认方法
    public void index() {
        renderText("This is a demo.");
     }

    



  

    
    public void test() {
    	setAttr("name", "value");
        render("p2p/test/login.html");
     }
    
    public void xd() {
    	setAttr("name", "value");
        render("xd/pages/index.html");
     }
}
