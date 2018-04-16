
package com.qdch.p2p.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;










import com.qdch.core.BaseController;
import com.qdch.p2p.model.AverageTimeModel;
import com.qdch.p2p.model.CollectPrincipalModel;
import com.qdch.p2p.model.CustNumberModel;
import com.qdch.p2p.model.FlowAmountModel;
import com.qdch.p2p.model.RangeNumberModel;
import com.qdch.p2p.model.TotalTranNumModel;
import com.qdch.p2p.model.TranAmountModel;
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
		List<AverageTimeModel> avgTime = AverageTimeModel.dao.getAvgTime(getDataScopeByUserName(), jysinfo);
		mRenderJson(avgTime);
	}
	
	//获取平台借款人数，平台投资人数
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

	
	//获取 投资区间人数
	public void getRangeNumber(){
		String jysinfo = getPara("jysinfo");//获取参数：交易市场名称
		String ppType = getPara("ppType");//类型：借款人或者投资人
		List<RangeNumberModel> list = RangeNumberModel.dao.getInvestRangeNumber(getDataScopeByUserName(), jysinfo, ppType);
		mRenderJson(list);
	}
	//获取 借款区间人数
	public void getLoanRangeNumber(){
		String jysinfo = getPara("jysinfo");//获取参数：交易市场名称
		String ppType = getPara("ppType");//类型：借款人或者投资人
		List<RangeNumberModel> list = RangeNumberModel.dao.getLoanRangeNumber(getDataScopeByUserName(), jysinfo, ppType);
		mRenderJson(list);
	}
	//获取人均借款金额 人均投资金额
	public void getLoanAndInvestNumber(){
		String jysinfo=getPara("jysinfo");//获取参数：交易市场名称
		//人均借款金额 
		List<TranAmountModel> loanList = TranAmountModel.dao.getLoanNumber(getDataScopeByUserName(),jysinfo);
		//人均投资金额
		List<TranAmountModel> investList = TranAmountModel.dao.getInvestNumber(getDataScopeByUserName(), jysinfo);
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("loanList", loanList);
		res.put("investList", investList);
		mRenderJson(res);
	}
	//资金流入
	public void getFlowAmount(){
		String jysinfo = getPara("jysinfo");//获取参数：交易市场名称
		List<FlowAmountModel> flowAmountList = FlowAmountModel.dao.getFlowAmount(getDataScopeByUserName(), jysinfo);
		mRenderJson(flowAmountList);
	}
	//代收本金
	public void getCollectPrincipal(){
		String jysinfo = getPara("jysinfo");//获取参数：交易市场名称
		List<CollectPrincipalModel> collectPrincipal = CollectPrincipalModel.dao.getCollectPrincipal(getDataScopeByUserName(), jysinfo);
		mRenderJson(collectPrincipal);
	}
}










