package com.qdch.p2p.controller;


import java.util.HashMap;

import com.qdch.core.BaseController;

public class CustCountController extends BaseController{
	 public void index() {
	        renderText("This is a demo.");
	     }
	

	  //根据指数排名中的累计贷款额返回数据
	    public void getByCustCount(){
	    	getResponse().addHeader("Access-Control-Allow-Origin", "*");	
	    	String custcount = getPara("custcount");
	    	HashMap<String, String> map = new HashMap<String, String>();
	    	map.put("custcount", custcount);
	    	/*renderJson(IndexRankingModel.dao.getByLoanAmount(map));*/
	    	
	    }

}
