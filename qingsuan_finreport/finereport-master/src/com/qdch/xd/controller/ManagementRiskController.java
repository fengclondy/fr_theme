package com.qdch.xd.controller;

import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.xd.model.JyscModel;
import com.qdch.xd.model.ProportionModel;
/**
 * 
* @author doushuihai  
* @date 2018年3月30日下午12:51:53  
* @TODO 风险分析-管理风险
 */
public class ManagementRiskController extends BaseController {
	/**
	 * 
	* @author lixioayi
	* @date 2018年4月2日下午
	* @TODO 管理风险页面
	 */
	public void index() {
		setAttr("jyslist", JyscModel.dao.getJysc(getDataScopeByUserName()));
		 render("xd/pages/03_02guanlifengxian.html");
	}
	
	/**
	 * 
	 * @author lixioayi
	 * @date 2018年4月2日
	 * @TODO 管理风险年限占比
	 */
	public void gainSenior(){
		 List<ProportionModel> senior= ProportionModel.dao.getSeniorProportion(getDataScopeByUserName());
		 
	}
	
	/**
	 * 
	 * @author lixiaoyi
	 * @date 2018年4月2日
	 * @TODO 管理风险学历占比
	 */
	public void gainEduction(){
		 List<ProportionModel> senior= ProportionModel.dao.getEducationProportion(getDataScopeByUserName());
		 
	}
  
}
