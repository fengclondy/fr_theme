package com.qdch.controller;

import java.util.HashMap;
import java.util.List;

import com.jfinal.core.Controller;
import com.qdch.model.DemoModel;
//@ControllerBind(controllerKey = "/jfinal/demo", viewPath = "/p2p")
/***
 * demo controller
 * @author Tom
 *
 */
public class DemoController extends Controller {
    public void index() {
        renderText("This is a demo.");
     }
    public void json(){
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("test", "test");
    	List<DemoModel> model=null;
    	try {
    		model=DemoModel.dao.getDemo();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	renderJson(model);
    	
    }
    
    public void test() {
        render("p2p/test/login.html");
     }

}
