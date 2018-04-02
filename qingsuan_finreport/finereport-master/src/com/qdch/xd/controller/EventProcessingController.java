package com.qdch.xd.controller;

import com.qdch.core.BaseController;
/**
 * 
* @author doushuihai  
* @date 2018年3月30日下午1:07:26  
* @TODO 风险管理-风险事件处理
 */
public class EventProcessingController extends BaseController {
	/**
	 * 
	* @author doushuihai  
	* @date 2018年3月30日下午1:08:45  
	* @TODO 风险事件页面
	 */
	public void index() {
		System.out.println("33ccc33");
		renderJsp("xd/pages/riskSolve.jsp");
//		 render("xd/pages/riskSolve");
	}
	

}
