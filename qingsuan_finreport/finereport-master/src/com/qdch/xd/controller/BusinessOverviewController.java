package com.qdch.xd.controller;

import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.xd.model.ComparisonOfCompeModel;
import com.qdch.xd.model.CompetitiveRrendModel;
import com.qdch.xd.model.CurrentComRankingModel;
import com.qdch.xd.model.JyscModel;
import com.qdch.xd.model.KeyIndicatorsModel;
/**
 * 
 * @todo   监管全景-业务总览
 * @time   2018年3月24日 下午2:38:49
 * @author zuoqb
 */
public class BusinessOverviewController extends BaseController {
	/**
	 * 
	 * @todo   风险总览页面
	 * @time   2018年3月24日 下午2:40:42
	 * @author zuoqb
	 */
	public void index() {
		setAttr("jyslist", JyscModel.dao.getJysc(getDataScopeByUserName()));
		render("xd/pages/01_02yewuzonglan.html");
	}
	/**
	 * 获取贷款余额的关键指标排名
	* @author doushuihai  
	* @date 2018年4月2日上午10:42:06  
	* @TODO
	 */
	
	public void getKeyIndByYeamount(){
		List<KeyIndicatorsModel> KeyIndByYeamount=KeyIndicatorsModel.dao.getByYeamount(getDataScopeByUserName());
		mRenderJson(KeyIndByYeamount);
	}
	/**
	 * 获取放款额的关键指标排名
	* @author doushuihai  
	* @date 2018年4月2日上午10:42:06  
	* @TODO
	 */
	
	public void getKeyIndByAmount(){
		List<KeyIndicatorsModel> KeyIndByAmount=KeyIndicatorsModel.dao.getByAmount(getDataScopeByUserName());
		mRenderJson(KeyIndByAmount);
	}
	/**
	 * 获取日均放款额的关键指标排名
	* @author doushuihai  
	* @date 2018年4月2日上午10:42:06  
	* @TODO
	 */
	
	public void getKeyIndByDayAmount(){
		List<KeyIndicatorsModel> KeyIndByDayAmount=KeyIndicatorsModel.dao.getByDayAmount(getDataScopeByUserName());
		mRenderJson(KeyIndByDayAmount);
	}
	/**
	 * 获取三农比重的关键指标排名
	* @author doushuihai  
	* @date 2018年4月2日上午10:42:06  
	* @TODO
	 */

	public void getKeyIndByThreeRuralIssue(){
		List<KeyIndicatorsModel> KeyIndByThreeRuralIssue=KeyIndicatorsModel.dao.getByThreeRuralIssue(getDataScopeByUserName());
		mRenderJson(KeyIndByThreeRuralIssue);
	}
	/**
	 * 获取业务总揽的当前竞争力排名
	* @author doushuihai  
	* @date 2018年4月2日下午3:28:45  
	* @TODO
	 */

	public void getCurrentComRanking(){
		List<CurrentComRankingModel> currentcomrankingmodel=CurrentComRankingModel.dao.getCurrentComRanking(getDataScopeByUserName());
		mRenderJson(currentcomrankingmodel);
	}
	/**
	 * 获取业务总揽的竞争力各指标对比
	* @author doushuihai  
	* @date 2018年4月2日下午3:28:45  
	* @TODO
	 */
	
	public void getComparisonOfCompe(){
		List<ComparisonOfCompeModel> comparisonofcompemodel=ComparisonOfCompeModel.dao.getComparisonOfCompe(getDataScopeByUserName());
		mRenderJson(comparisonofcompemodel);
	}
	/**
	 * 获取业务总揽的市场竞争力趋势
	* @author doushuihai  
	* @date 2018年4月2日下午5:41:29  
	* @TODO
	 */
	
	public void getCompetitiveRrend(){
		String jys=getPara("jys");//获取第一个条件参数：交易市场		
		String condition = getPara("condition");//获取第二个条件参数：竞争力指标		
		List<CompetitiveRrendModel> competitiverrendmodel=CompetitiveRrendModel.dao.getCompetitiveRrend(getDataScopeByUserName(),jys,condition);
		mRenderJson(competitiverrendmodel);
	}



}