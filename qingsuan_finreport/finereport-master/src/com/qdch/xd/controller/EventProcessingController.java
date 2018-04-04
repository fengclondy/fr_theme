package com.qdch.xd.controller;

import com.jfinal.plugin.activerecord.Page;
import com.qdch.core.BaseController;
import com.qdch.xd.model.ExchangeInfoModel;
import com.qdch.xd.model.IndexRankingModel;
import com.qdch.xd.model.RiskEventModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 
* @author doushuihai  
* @date 2018年3月30日下午1:07:26  
* @TODO 风险管理-风险事件处理
 */
public class EventProcessingController extends BaseController {
	private static RiskEventModel riskEventModelDao = RiskEventModel.dao;
	private static ExchangeInfoModel exchangeInfoModelDao = ExchangeInfoModel.dao;
	/**
	 * 
	* @author doushuihai  
	* @date 2018年3月30日下午1:08:45  
	* @TODO 风险事件页面
	 */
	public void index() {
//		renderJsp("xd/pages/riskSolve.jsp");
		 render("xd/pages/05_01fengxianshijianchuli.html");
	}

    /**
     *  交易所信息 /市场名称
     */
	public void getExchageList(){
	    List<ExchangeInfoModel> list = exchangeInfoModelDao.getList();
        mRenderJson(list);

    }
//	public
	public void getList(){
		int pageNum =Integer.parseInt(getPara("pageNum")==null?"1":getPara("pageNum"));
		int pageSize =Integer.parseInt(getPara("pageSize")==null?"30":getPara("pageSize"));
//		getPara(getRequest());
		Page<RiskEventModel> page = riskEventModelDao.getRiskEvent(getDataScopeByUserName(),pageNum,pageSize,getRequest());
		mRenderJson(page);
	}


}
