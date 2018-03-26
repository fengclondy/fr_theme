package com.qdch.p2p.controller;


import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.p2p.model.JyscModel;

public class JyscController extends BaseController{
	 public void index() {
	        renderText("This is a demo.");
	     }
	
	    //获取交易市场名称
	    public void getJyscmc(){
	    	getResponse().addHeader("Access-Control-Allow-Origin", "*");	    	
	    	List<JyscModel> model=null;
	    	try {
	    		model=JyscModel.dao.getJysc("");
			} catch (Exception e) {				
				// TODO: handle exception
				e.printStackTrace();
			}
	    	renderJson(model);
	    }
	
}
