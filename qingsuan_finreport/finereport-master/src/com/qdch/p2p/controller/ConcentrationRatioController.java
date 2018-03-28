package com.qdch.p2p.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jfinal.kit.JsonKit;
import com.qdch.core.BaseController;
import com.qdch.p2p.model.DefrateModel;
import com.qdch.p2p.model.IndexRankingModel;
import com.qdch.p2p.model.JyscModel;
import com.qdch.p2p.model.MigrationRateModel;

/**
 * 
 * @author doush
 * @date 2018年3月25日
 * @TODO 风险分析-信用风险
 */
public class ConcentrationRatioController extends BaseController {
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getJys(){
		List<JyscModel> jys=JyscModel.dao.getJysc(getDataScopeByUserName());
		if(StringUtils.isNotBlank(getPara("jsonp"))){
			//跨域处理
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			Map json = new HashMap();
			String callback = getPara("callback");
			json.put("data", jys);
			String jsonp = callback + "(" + JsonKit.toJson(json) + ")";//返回的json 格式要加callback()
			renderJson(jsonp);
		}else{
			renderJson(jys);
		}
    }
	/**
	 * 
	 * @author doush
	 * @date 2018年3月25日
	 * @TODO 根据贷款次数获取指标排名
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getRankByLoanCount(){
		String jys=getPara("jys");//获取信用风险总体限制的筛选条件参数
		System.out.println("jys================================"+jys);
		List<IndexRankingModel> RankByLoanCount=IndexRankingModel.dao.getByLoanCount(getDataScopeByUserName(),jys);
		if(StringUtils.isNotBlank(getPara("jsonp"))){
			//跨域处理
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			Map json = new HashMap();
			String callback = getPara("callback");
			json.put("data", RankByLoanCount);
			String jsonp = callback + "(" + JsonKit.toJson(json) + ")";//返回的json 格式要加callback()
			renderJson(jsonp);
		}else{
			renderJson(RankByLoanCount);
		}
		
	}
	/**
	 * 
	 * @author doush
	 * @date 2018年3月25日
	 * @TODO 根据累计贷款额返回指标排名
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getRankByLoanAmount(){
		String jys=getPara("jys");//获取信用风险总体限制的筛选条件参数
		String custype = getPara("custype");
    	System.out.println("custypecustype:"+custype);
		List<IndexRankingModel> RankByLoanAmount=IndexRankingModel.dao.getByLoanAmount(getDataScopeByUserName(),jys,custype);
		if(StringUtils.isNotBlank(getPara("jsonp"))){
			//跨域处理
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			Map json = new HashMap();
			String callback = getPara("callback");
			json.put("data", RankByLoanAmount);
			String jsonp = callback + "(" + JsonKit.toJson(json) + ")";//返回的json 格式要加callback()
			renderJson(jsonp);
		}else{
			renderJson(RankByLoanAmount);
		}
	}
	/**
	 * 
	 * @author doush
	 * @date 2018年3月26日
	 * @TODO 根据贷款余额返回指标排名
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getRankByYeamount(){
		String jys=getPara("jys");//获取信用风险总体限制的筛选条件参数
		String custype = getPara("custype");
    	System.out.println("custypecustype:"+custype);
		List<IndexRankingModel> RankByYeamount=IndexRankingModel.dao.getByYeamount(getDataScopeByUserName(),jys,custype);
		if(StringUtils.isNotBlank(getPara("jsonp"))){
			//跨域处理
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			Map json = new HashMap();
			String callback = getPara("callback");
			json.put("data", RankByYeamount);
			String jsonp = callback + "(" + JsonKit.toJson(json) + ")";//返回的json 格式要加callback()
			renderJson(jsonp);
		}else{
			renderJson(RankByYeamount);
		}
		
	}
	/**
	 * 
	 * @author doush
	 * @date 2018年3月26日
	 * @TODO 根据不同筛选方式获取不良率
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getDefrateByCondition(){
		String condition = getPara("condition");
    	System.out.println("condition:"+condition);
		List<DefrateModel> DefrateByCondition=DefrateModel.dao.getDefrate(getDataScopeByUserName(),condition);
		if(StringUtils.isNotBlank(getPara("jsonp"))){
			//跨域处理
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			Map json = new HashMap();
			String callback = getPara("callback");
			json.put("data", DefrateByCondition);
			String jsonp = callback + "(" + JsonKit.toJson(json) + ")";//返回的json 格式要加callback()
			renderJson(jsonp);
		}else{
			renderJson(DefrateByCondition);
		}		
	}
	/**
	 * 
	 * @author doush
	 * @date 2018年3月26日
	 * @TODO 获取信用风险中的迁徙率
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getMigrationRrate(){
		String condition = getPara("condition");
    	System.out.println("condition:"+condition);
    	String jys=getPara("jys");//获取信用风险总体限制的筛选条件参数
		List<MigrationRateModel> MigrationRate=MigrationRateModel.dao.getMigrationRate(getDataScopeByUserName(),jys,condition);
		if(StringUtils.isNotBlank(getPara("jsonp"))){
			//跨域处理
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			Map json = new HashMap();
			String callback = getPara("callback");
			json.put("data", MigrationRate);
			String jsonp = callback + "(" + JsonKit.toJson(json) + ")";//返回的json 格式要加callback()
			renderJson(jsonp);
		}else{
			renderJson(MigrationRate);
		}
	}

}
