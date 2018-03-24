package com.qdch.p2p.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fr.hailian.service.UserDataFromRoleService;
import com.qdch.core.BaseController;
import com.qdch.p2p.model.DefrateModel;


public class DefrateController extends BaseController{
	 public void index() {
	        renderText("This is a demo.");
	     }
	
	   
	  //根据担保方式返回数据
	    public void getByDBFS(){
	    	getResponse().addHeader("Access-Control-Allow-Origin", "*");	
	    	String dbfs = getPara("dbfs");
//	    	HashMap<String, String> map = new HashMap<String, String>();
//	    	map.put("dbfs", dbfs);
	    	List<DefrateModel> list =  DefrateModel.dao.getByDBFS(getDataScopeByUserName(),dbfs);
	    	HashMap<String, Object> reslut = new HashMap<String, Object>();
	    	ArrayList<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
	    	ArrayList<String> arry = new ArrayList<String>();
	    	ArrayList<String> time = new ArrayList<String>();
	    	for(int i = 0 ; i < list.size() ; i++){
	    		if(i!=0){
	    			if(list.get(i).get("dbfs").equals(list.get(i-1).get("dbfs"))){
	    				arry.add(list.get(i).getBigDecimal("sum").toString());
	    			}else{
	    				reslut.put("name", list.get(i).get("dbfs"));
	    				reslut.put("arry", arry.toArray());
	    				resultList.add(reslut);
	    				arry.clear();
	    				reslut.clear();
	    				arry.add(list.get(i).getBigDecimal("sum").toString());
	    			}
	    		}else{
	    			arry.add(list.get(i).getBigDecimal("sum").toString());
	    		}
	    		if(i==list.size()-1){
	    			reslut.put("name", list.get(i).get("dbfs"));
    				reslut.put("arry", arry.toArray());
	    			reslut.put("month" ,time.toArray());
	    			resultList.add(reslut);
	    		}
	    		if(list.get(i).get("dbfs").equals(list.get(0).get("dbfs"))){
	    			time.add(list.get(i).get("vmonth"));
	    		}
	    	}
	    	System.err.println(list.get(0).get("vday"));
	    	renderJson(resultList);
	    	
	    }
	  
}
