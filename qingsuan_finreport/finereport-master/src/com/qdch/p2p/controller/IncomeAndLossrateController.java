package com.qdch.p2p.controller;


import java.util.HashMap;
import java.util.List;

import com.jfinal.core.Controller;
import com.qdch.p2p.model.IncomeAndLossrateModel;
import com.qdch.p2p.model.IndexRankingModel;

public class IncomeAndLossrateController extends Controller{
	 public void index() {
	        renderText("This is a demo.");
	     }
	
	    //获取贷款收入和贷款逾期损失率
	    public void getIncome(){
	    	getResponse().addHeader("Access-Control-Allow-Origin", "*");	    	
	    	List<IncomeAndLossrateModel> model=null;
	    	try {
	    		model=IncomeAndLossrateModel.dao.getIncome();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	    	renderJson(model);
	    }
	    //获取贷款收入和贷款逾期损失率
	    public void getLossrate(){
	    	getResponse().addHeader("Access-Control-Allow-Origin", "*");	    	
	    	List<IncomeAndLossrateModel> model=null;
	    	try {
	    		model=IncomeAndLossrateModel.dao.getLossrate();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	    	renderJson(model);
	    }
	 
}
