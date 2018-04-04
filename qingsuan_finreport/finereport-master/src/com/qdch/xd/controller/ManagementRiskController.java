package com.qdch.xd.controller;

import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.xd.model.JyscModel;
import com.qdch.xd.model.ProportionModel;
import com.qdch.xd.model.RiskShowModel;
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
		//获取该用户下的所有交易所
		setAttr("jys", JyscModel.dao.getJysc(getDataScopeByUserName()));
		String jys=getPara("jys");
		//获取该交易所的管理风险警示列表
		List<RiskShowModel> showModel = RiskShowModel.dao.gainShow(getDataScopeByUserName(),jys);
		setAttr("show", showModel);
		render("xd/pages/03_02guanlifengxian.html");
	}
	
	/**
	 * 
	 * @author lixioayi
	 * @date 2018年4月2日
	 * @TODO 管理风险年限占比
	 */
	public void gainSenior(){
		//获取当前交易所
		  String jys=getPara("jys");
		 List<ProportionModel> senior= ProportionModel.dao.getSeniorProportion(getDataScopeByUserName(),jys);
		 mRenderJson(senior);
	}
	
	/**
	 * 
	 * @author lixiaoyi
	 * @date 2018年4月2日
	 * @TODO 管理风险学历占比
	 */
	public void gainEduction(){
		//获取当前交易所
		String jys=getPara("jys");
		 List<ProportionModel> eduction = ProportionModel.dao.getEducationProportion(getDataScopeByUserName(),jys);
		 mRenderJson(eduction);
	}
	
	public void gainShow(){
		String jys=getPara("jys");
		//获取该交易所的管理风险警示列表
		List<RiskShowModel> showModel = RiskShowModel.dao.gainShow(getDataScopeByUserName(),jys);
		setAttr("show1", showModel);
		mRenderJson(showModel);
	}
}
