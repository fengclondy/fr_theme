package com.qdch.xd.controller;

import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.xd.model.RiskCountModel;

public class RiskOverviewController extends BaseController {
	
	public void index() {
		 render("xd/pages/01_01fengxianzonglan.html");
	}
	/**
	 * @todo   获取风险排名
	 * @time   2018年3月24日 下午4:49:49
	 * @author zuoqb
	 * @return_type   void
	 */
	public void getRiskRanking(){
		List<RiskCountModel> ranking=RiskCountModel.dao.getRiskRanking(getDataScopeByUserName());
		mRenderJson(ranking);
	}
}
