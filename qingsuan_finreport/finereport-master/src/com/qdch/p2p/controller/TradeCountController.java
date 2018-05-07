package com.qdch.p2p.controller;

import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.p2p.model.PingTaiXinXiModel;
import com.qdch.p2p.model.PlatformModel;
import com.qdch.p2p.model.PpjyscModel;
import com.qdch.p2p.model.TradeCountModel;

/**
 * 
 * @author gaozhao
 * @date 2018年5月7日
 * @TODO 交易量
 */
public class TradeCountController extends BaseController{
	/**
	 * 
	 * @author gaozhao
	 * @date 2018年5月7日
	 * @TODO 初始化页面
	 */
	public void index(){
		setAttr("tradeList", TradeCountModel.dao.getTrade(getDataScopeByUserNameForP2p(),null,null)); //获取平台信息
		render("p2p/pages/08_02jiaoyiliang.html");
	}
	/**
	 * 
	 * @author gaozhao
	 * @date 2018年5月7日
	 * @TODO 获取交易量内容
	 */
	public void tradeInfo(){
		String ptmc=getPara("ptmc");
		String pageNum=getPara("pageNum");
	List<TradeCountModel> list = TradeCountModel.dao.getTrade(getDataScopeByUserNameForP2p(),ptmc,pageNum);
	mRenderJson(list);
	}
	/**
	 * 
	 * @author gaozhao
	 * @date 2018年5月7日
	 * @TODO 获取交易平台
	 */
	public void tradePt(){
	
	List<PpjyscModel> list = PpjyscModel.dao.getJysc(getDataScopeByUserNameForP2p());
	mRenderJson(list);
	}
}
