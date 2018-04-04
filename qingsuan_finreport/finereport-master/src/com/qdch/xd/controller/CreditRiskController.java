package com.qdch.xd.controller;

import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.xd.model.ConRatioModel;
import com.qdch.xd.model.DefrateModel;
import com.qdch.xd.model.IncomeAndLossrateModel;
import com.qdch.xd.model.IndexRankingModel;
import com.qdch.xd.model.JyscModel;
import com.qdch.xd.model.MigrationRateModel;

/**
 * 
 * @author doush
 * @date 2018年3月25日
 * @TODO 风险分析-信用风险
 */
public class CreditRiskController extends BaseController {
	public void index() {
		setAttr("jyslist", JyscModel.dao.getJysc(getDataScopeByUserName()));
		render("xd/pages/03_01xinyongfengxian.html");
	}
	/**
	 * 
	 * @author doush
	 * @date 2018年3月25日
	 * @TODO 获取交易所
	 */
	public void getJys(){
		List<JyscModel> jys=JyscModel.dao.getJysc(getDataScopeByUserName());
		mRenderJson(jys);
    }
	/**
	 * 
	 * @author doush
	 * @date 2018年3月25日
	 * @TODO 根据贷款次数获取指标排名
	 */
	public void getRankByLoanCount(){
		String jys=getPara("jys");//获取信用风险总体限制的筛选条件参数
		List<IndexRankingModel> rankByLoanCount=IndexRankingModel.dao.getByLoanCount(getDataScopeByUserName(),jys);
		mRenderJson(rankByLoanCount);
		
	}
	/**
	 * 
	 * @author doush
	 * @date 2018年3月25日
	 * @TODO 根据累计贷款额返回指标排名
	 */
	public void getRankByLoanAmount(){
		String jys=getPara("jys");//获取信用风险总体限制的筛选条件参数
		String custype = getPara("custype");
    	System.out.println("custypecustype:"+custype);
		List<IndexRankingModel> RankByLoanAmount=IndexRankingModel.dao.getByLoanAmount(getDataScopeByUserName(),jys,custype);
		mRenderJson(RankByLoanAmount);
	}
	/**
	 * 
	 * @author doush
	 * @date 2018年3月26日
	 * @TODO 根据贷款余额返回指标排名
	 */
	public void getRankByYeamount(){
		String jys=getPara("jys");//获取信用风险总体限制的筛选条件参数
		String custype = getPara("custype");
    	System.out.println("custypecustype:"+custype);
		List<IndexRankingModel> rankByYeamount=IndexRankingModel.dao.getByYeamount(getDataScopeByUserName(),jys,custype);
		mRenderJson(rankByYeamount);
		
	}
	/**
	 * 
	 * @author doush
	 * @date 2018年3月26日
	 * @TODO 根据不同筛选方式获取不良率
	 */
	public void getDefrateByCondition(){
		String jys=getPara("jys");//获取信用风险总体限制的筛选条件参数
		String condition = getPara("condition");
    	System.out.println("condition:"+condition);
		List<DefrateModel> defrateByCondition=DefrateModel.dao.getDefrate(getDataScopeByUserName(),jys,condition);
		mRenderJson(defrateByCondition);	
	}
	/**
	 * 
	 * @author doush
	 * @date 2018年3月26日
	 * @TODO 获取信用风险中的迁徙率
	 */
	public void getMigrationRrate(){
		String condition = getPara("condition");
    	System.out.println("condition:"+condition);
    	String jys=getPara("jys");//获取信用风险总体限制的筛选条件参数
		List<MigrationRateModel> migrationRate=MigrationRateModel.dao.getMigrationRate(getDataScopeByUserName(),jys,condition);
		mRenderJson(migrationRate);
	}
	//获取开户人数集中度
	public void getConRatioByCustCount(){
		String jys=getPara("jys");//获取信用风险总体限制的筛选条件参数
		List<ConRatioModel> conRatioByCustCount=ConRatioModel.dao.getConRatioByCustCount(getDataScopeByUserName(), jys);
		mRenderJson(conRatioByCustCount);
	}
	public void getConRatioByTime(){
		String jys=getPara("jys");//获取信用风险总体限制的筛选条件参数
		List<ConRatioModel> conRatioBytime=ConRatioModel.dao.getConRatioByTime(getDataScopeByUserName(), jys);
		mRenderJson(conRatioBytime);
	}
	public void getConRatioByAge(){
		String jys=getPara("jys");//获取信用风险总体限制的筛选条件参数
		List<ConRatioModel> conRatioByAge=ConRatioModel.dao.getConRatioByAge(getDataScopeByUserName(), jys);
		mRenderJson(conRatioByAge);
	}
	/**
	 * 获取地域集中度
	 * @author doush
	 * @date 2018年3月28日
	 * @TODO
	 */
	public void getConRatioByRegion(){
		String jys=getPara("jys");//获取信用风险总体限制的筛选条件参数
		List<ConRatioModel> conRatioByRegion=ConRatioModel.dao.getConRatioByRegion(getDataScopeByUserName(), jys);
		mRenderJson(conRatioByRegion);
	}
	/**
	 * h获取行业集中度
	 * @author doush
	 * @date 2018年3月28日
	 * @TODO
	 */
	public void getConRatioByIndustry(){
		String jys=getPara("jys");//获取信用风险总体限制的筛选条件参数
		List<ConRatioModel> conRatioByIndustry=ConRatioModel.dao.getConRatioByIndustry(getDataScopeByUserName(), jys);
		mRenderJson(conRatioByIndustry);
	}
	public void getIncomeAndLoss(){
		String jys=getPara("jys");//获取信用风险总体限制的筛选条件参数
		
		List<IncomeAndLossrateModel> incomeAndLoss=IncomeAndLossrateModel.dao.getIncomeAndLoss(getDataScopeByUserName(),jys);
		mRenderJson(incomeAndLoss);	
	}

}
