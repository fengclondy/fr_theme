package com.qdch.xd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jfinal.kit.JsonKit;
import com.qdch.core.BaseController;
import com.qdch.xd.model.RiskCountModel;
/**
 * 
* @author doushuihai  
* @date 2018年3月30日下午12:51:53  
* @TODO 风险分析-管理风险
 */
public class ManagementRiskController extends BaseController {
	/**
	 * 
	* @author doushuihai  
	* @date 2018年3月30日下午12:53:21  
	* @TODO 管理风险页面
	 */
	public void index() {
		 render("xd/pages/03_02guanlifengxian.html");
	}
	

}
