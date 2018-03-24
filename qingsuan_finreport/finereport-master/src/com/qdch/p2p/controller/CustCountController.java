package com.qdch.p2p.controller;


import java.util.HashMap;
import java.util.List;

import com.jfinal.core.Controller;
import com.qdch.p2p.model.IndexRankingModel;

public class CustCountController extends Controller{
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
