
package com.qdch.p2p.controller;


import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.p2p.model.DefrateModel;


public class DefrateController extends BaseController{
	 public void index() {
	        renderText("This is a demo.");
	     }
	
	   
	  //根据前端不同筛选条件查询不良率
	    public void getByDBFS(){
	    	getResponse().addHeader("Access-Control-Allow-Origin", "*");	
	    	String type = getPara("dbfs");
	    	type="dbfs";
	    	List<DefrateModel> list =  DefrateModel.dao.getDefrate(getDataScopeByUserName(),type);
	    	renderJson(list);
	    	
	    }
	  
}

