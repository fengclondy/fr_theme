package com.qdch.p2p.controller;

import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.p2p.model.RiskOverviewModel;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月8日
 * @TODO 风险总览
 */
public class RiskController extends BaseController {

	
	public void index(){
		render("p2p/pages/01_02fengxianzonglan.html");
	}
	public void getRiskIndex(){
			
		List<RiskOverviewModel> riskIndex = RiskOverviewModel.dao.getRiskIndex(getDataScopeByUserNameForP2p());
		mRenderJson(riskIndex);
	}
}
