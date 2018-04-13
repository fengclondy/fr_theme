package com.qdch.p2p.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;







import com.qdch.core.BaseController;
import com.qdch.p2p.model.AverageTimeModel;
import com.qdch.p2p.model.CustNumberModel;
import com.qdch.p2p.model.RangeNumberModel;
import com.qdch.p2p.model.TotalTranNumModel;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月8日
 * @TODO  平台交易
 */
public class DealplatformController extends BaseController {

	public void index(){
		setAttr("jysinfolist", TotalTranNumModel.dao.getPlat(getDataScopeByUserName()));
		render("p2p/pages/04_02pingtaijiaoyihuaxiang.html");
	}
	
	//获取总成交量和总成交额
	public void getTotalTranNum(){
		String jysinfo=getPara("jysinfo");//获取参数：交易市场名称
		//获取总成交量
			List totalList = TotalTranNumModel.dao.getTotalTranNumModel(getDataScopeByUserName(),jysinfo);
		//获取总成交额
			List countList = TotalTranNumModel.dao.getCountTranNumModel(getDataScopeByUserName(), jysinfo);
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("totalList", totalList);
		res.put("countList", countList);
		mRenderJson(res);
	}
	//平均满标时间
	public void getAvgTime(){
		String jysinfo=getPara("jysinfo");//获取参数：交易市场名称
		//获取总成交量
		List<AverageTimeModel> avgTime = AverageTimeModel.dao.getAvgTime(getDataScopeByUserName(), jysinfo);
		mRenderJson(avgTime);
	}
	
	//获取人均借款，人均投资
	public void getCustNum(){
		String jysinfo=getPara("jysinfo");//获取参数：交易市场名称
		//人均借款
		List loanList = CustNumberModel.dao.getLoanNumber(getDataScopeByUserName(),jysinfo);
		//人均投资
		List investList = CustNumberModel.dao.getInvestNumber(getDataScopeByUserName(),jysinfo);
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("loanList", loanList);
		res.put("investList", investList);
		mRenderJson(res);
	}
	
	//获取投资区间人数，借款区间人数
	public void getRangeNumber(){
		String jysinfo = getPara("jysinfo");//获取参数：交易市场名称
		String ppType = getPara("ppType");//类型：借款人或者投资人
		List<RangeNumberModel> list = RangeNumberModel.dao.getInvestRangeNumber(getDataScopeByUserName(), jysinfo, ppType);
		mRenderJson(list);
	}

}
