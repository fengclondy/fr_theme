package com.qdch.xd.controller;


import com.alibaba.fastjson.JSONArray;
import com.fr.stable.StringUtils;
import com.fr.web.core.A.O;
import com.jfinal.plugin.activerecord.Page;
import com.qdch.core.BaseController;
import com.qdch.util.ExportUtil;
import com.qdch.xd.model.*;

import java.util.*;

/**
 * 
* @author wf
* @date 2018年4月10日12:38:59
* @TODO 风险管理-风险事件填报
 */
public class EventInputController extends BaseController {

	private static  RiskTypeModel riskTypeModelDao = RiskTypeModel.dao;
	private static DictModel dictModelDao = DictModel.dao;

	public void index(){
		List<RiskTypeModel> riskTypeList =  riskTypeModelDao.getTypeKind("3"); //风险类别
		setAttr("type",riskTypeList);
		render("xd/pages/05_05fengxianshijiantianbao.html");
	}


	public void saveEvent(){
		RiskEventModel riskEventModel  = new RiskEventModel();
		riskEventModel.dao.save();
		mRenderJson(true);
	}

	public void getRisk(){
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("status",dictModelDao.getLabel(getPara("note")));
		result.put("risk",riskTypeModelDao.getByType("3"));
		result.put("exchange",	ExchangeInfoModel.dao.getList()); //机构/市场)
		mRenderJson(result);
	}





}
