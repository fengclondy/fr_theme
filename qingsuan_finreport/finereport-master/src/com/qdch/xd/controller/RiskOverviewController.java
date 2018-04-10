package com.qdch.xd.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.qdch.core.BaseController;
import com.qdch.xd.model.RiskCountModel;
import com.qdch.xd.model.RiskTrendDetailedModel;
import com.qdch.xd.model.RiskTrendModel;
/**
 * 
 * @author ljm
 * @todo 风险总览
 */
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
		
		List<RiskCountModel> ranking = RiskCountModel.dao.getRiskRanking(getDataScopeByUserName());
		mRenderJson(ranking);
	}
	/**
	 * @todo   平台运营监控 (环形图)
	 * @time   2018年4月4日 下午14:40:09
	 * @author ljm
	 */
	public void getAllPlatform(){
		List<RiskCountModel> ranking = RiskCountModel.dao.getAllPlatform(getDataScopeByUserName());
		mRenderJson(ranking);
	}
	
	/**
	 * @todo   平台运营监控 (风险种类，数量)
	 * @time   2018年4月4日 下午14:40:09
	 * @author ljm
	 */
	public void getRiskCount(){
		List<RiskCountModel> ranking = RiskCountModel.dao.getRiskCount(getDataScopeByUserName());
		mRenderJson(ranking);
	}
	
	
	/**
	 * @todo   风险趋势/风险趋势明细
	 * @time   2018年4月9日 
	 * @author ljm
	 */
	public void getRiskTrend(){
		String jysc=getPara("jysc");//获取参数1：交易市场	
		String fxlb = getPara("fxlb");//获取参数2：风险类型
		List list = new ArrayList();
		if(StringUtils.isNotBlank(fxlb)){
			list = RiskTrendDetailedModel.dao.getRiskTrendDetailed(getDataScopeByUserName(),jysc,fxlb);
		}else{
			list = RiskTrendModel.dao.getRiskTrend(getDataScopeByUserName(),jysc);
		}
		mRenderJson(list);
	}
}
