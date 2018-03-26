package com.qdch.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jfinal.core.Controller;
import com.qdch.model.DemoModel;
import com.qdch.p2p.model.JyscModel;
//@ControllerBind(controllerKey = "/jfinal/demo", viewPath = "/p2p")
/***
 * demo controller
 * @author Tom
 *
 */
public class DemoController extends Controller {
	//默认方法
    public void index() {
        renderText("This is a demo.");
     }
    //返回json
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
    
    public void test2(){
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("test", "test");
    	List<DemoModel> model=null;
    	try {
    		model=DemoModel.dao.getTest();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	renderJson(model);
    	
    }
    
    public void test3(){
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("test", "test");
    	List<DemoModel> model=null;
    	try {
    		model=DemoModel.dao.getJys();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	renderJson(model);
    	
    }
    //参数查询数据
    public void log(){
    	String id = getPara("id");
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("id", id);
    	renderJson(DemoModel.dao.getLog(map));
    	
    }
    //map类型的参数在SQL的in条件中
    public void log2(){
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("id1", "1");
    	map.put("id2", "2");
    	renderJson(DemoModel.dao.getLog2(map));
    }
    //List类型的参数在sqlzhong
    public void log3(){
    	ArrayList<String> list = new ArrayList<String>();
    	list.add("1");
    	list.add("2");
    	list.add("3");
    	renderJson(DemoModel.dao.getLog3(list));
    }
    
    public void test() {
    	setAttr("name", "value");
        render("p2p/test/login.html");
     }
    
    public void xd() {
    	setAttr("name", "value");
        render("xd/pages/index.html");
     }
    /*public void fxzl() {
        render("xd/pages/01_01fengxianzonglan.html");
     }*/
   /* public void ywzl() {
        render("xd/pages/01_02yewuzonglan.html");
     }*/
    public void xyfx() {
    	setAttr("jyslist", JyscModel.dao.getJysc(""));
        render("xd/pages/03_01xinyongfengxian.html");
     }
}
