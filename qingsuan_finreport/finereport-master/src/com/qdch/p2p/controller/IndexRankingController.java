package com.qdch.p2p.controller;


import java.util.HashMap;
import java.util.List;

import com.fr.hailian.service.UserDataFromRoleService;
import com.jfinal.core.Controller;
import com.qdch.p2p.model.IndexRankingModel;

public class IndexRankingController extends Controller{
	 public void index() {
	        renderText("This is a demo.");
	     }
	
	    //根据指数排名中的贷款次数返回数据
	    public void getByLoanCount(){
	    	getResponse().addHeader("Access-Control-Allow-Origin", "*");	    	
	    	List<IndexRankingModel> model=IndexRankingModel.dao.getByLoanCount();
	    	
	    	renderJson(model);
	    }
	  //根据指数排名中的累计贷款额返回数据
	    public void getByLoanAmount(){
	    	getResponse().addHeader("Access-Control-Allow-Origin", "*");	
	    	String custype = getPara("custype");
	    	System.out.println("custypecustype:"+custype);
	    	List<IndexRankingModel> model=IndexRankingModel.dao.getByLoanAmount(UserDataFromRoleService.getDepartMenByUserName(null),custype);
	    	renderJson(model);
	    	
	    }
	  //根据指数排名中的贷款余额返回数据
	    public void getByYeamount(){
	    	getResponse().addHeader("Access-Control-Allow-Origin", "*");	
	    	
	    	String custype = getPara("custype");
	    	
	    	List<IndexRankingModel> model=IndexRankingModel.dao.getByYeamount(UserDataFromRoleService.getDepartMenByUserName(null),custype);
	    	renderJson(model);
	    	
	    }
}
