package com.qdch.p2p.controller;


import java.util.HashMap;
import java.util.List;

import com.jfinal.core.Controller;
import com.qdch.p2p.model.JyscModel;

public class JyscController extends Controller{
	 public void index() {
	        renderText("This is a demo.");
	     }
	
	    //获取交易市场名称
	    public void getJyscmc(){
	    	getResponse().addHeader("Access-Control-Allow-Origin", "*");	    	
	    	List<JyscModel> model=null;
	    	try {
	    		model=JyscModel.dao.getJysc();
			} catch (Exception e) {				
				// TODO: handle exception
				e.printStackTrace();
			}
	    	renderJson(model);
	    }
	
}
