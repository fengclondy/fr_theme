package com.qdch.p2p.controller;

import com.qdch.core.BaseController;
import com.qdch.p2p.model.PingTaiXinXiModel;
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
		setAttr("tradeList", TradeCountModel.dao.getTrade(getDataScopeByUserNameForP2p(),null)); //获取平台信息
		render("xd/pages/08_02jiekuanrentianbao.html");
	}
}
