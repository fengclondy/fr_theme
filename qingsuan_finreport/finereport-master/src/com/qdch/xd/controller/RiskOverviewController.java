package com.qdch.xd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jfinal.kit.JsonKit;
import com.qdch.core.BaseController;
import com.qdch.xd.model.RiskCountModel;
/**
 * 
 * @todo   监管全景-风险总览
 * @time   2018年3月24日 下午2:38:49
 * @author zuoqb
 */
public class RiskOverviewController extends BaseController {
	/**
	 * 
	 * @todo   风险总览页面
	 * @time   2018年3月24日 下午2:40:42
	 * @author zuoqb
	 */
	public void index() {
		 render("xd/pages/01_01fengxianzonglan.html");
	}
	/**
	 * @todo   获取风险排名
	 * @time   2018年3月24日 下午4:49:49
	 * @author zuoqb
	 * @return_type   void
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getRiskRanking(){
		List<RiskCountModel> ranking=RiskCountModel.dao.getRiskRanking(getDataScopeByUserName());
		if(StringUtils.isNotBlank(getPara("jsonp"))){
			//跨域处理
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			Map json = new HashMap();
			String callback = getPara("callback");
			json.put("data", ranking);
			String jsonp = callback + "(" + JsonKit.toJson(json) + ")";//返回的json 格式要加callback()
			renderJson(jsonp);
		}else{
			renderJson(ranking);
		}
	}

}
