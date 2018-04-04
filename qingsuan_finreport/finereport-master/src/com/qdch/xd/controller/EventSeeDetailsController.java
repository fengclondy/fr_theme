package com.qdch.xd.controller;

import com.fr.stable.StringUtils;
import com.qdch.core.BaseController;
import com.qdch.xd.model.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 
* @author wf
 * 风险事件查看明细
* @date 2018年3月30日下午1:07:26  
* @TODO alert
 */
public class EventSeeDetailsController extends BaseController {

	private static RiskEventModel riskEventModelDao = RiskEventModel.dao;
	private static ExchangeInfoModel exchangeInfoModelDao = ExchangeInfoModel.dao;
	private static RiskTypeModel riskTypeModelDao = RiskTypeModel.dao;

	private static GuaranteeContrastModel guaranteeContrastModelDao = GuaranteeContrastModel.dao;
	private static LimitQueryModel limitQueryModelDao = LimitQueryModel.dao;
	/**
	 * 
	* @author wf
	* @date 2018年3月30日下午1:08:45  
	* @TODO alert
	 */
	public void index() {
		setAttr("fxsj_id",getPara("fxsj_id"));
		render("xd/pages/05_02fengxianshijianchakanmingxi.html");
	}

	public void getRiskEvent(){
		if(StringUtils.isBlank(getPara("fxsj_id"))){
			mRenderError("请输入风险事件id");
			return ;
		}
		RiskEventModel model = riskEventModelDao.findById(getPara("fxsj_id"));
		mRenderJson(model);
	}

	/**
	 * 查看明细
	 */
	public void getContent(){
		int pageNum =Integer.parseInt(StringUtils.isBlank(getPara("pageNum"))||
				getPara("pageNum").equals("undefined")==true?
				"1":getPara("pageNum"));
		int pageSize =Integer.parseInt(StringUtils.isBlank(getPara("pageSize"))||
				getPara("pageSize").equals("undefined")
						==true?
				"10":getPara("pageSize"));
		Map<String,Object> results = new HashMap<String,Object>();

		results.put("riskevent",riskEventModelDao.findById(getPara("fxsj_id")));
		results.put("guarantee",guaranteeContrastModelDao.getPage(pageNum,pageSize)); //担保合同
		results.put("limit",limitQueryModelDao.getPage(pageNum,pageSize)); //额度信息
        mRenderJson(results);
	}

	/**
	 * 担保合同
	 */
	public void getGuarantee(){
		int pageNum =Integer.parseInt(StringUtils.isBlank(getPara("pageNum"))||
				getPara("pageNum").equals("undefined")==true?
				"1":getPara("pageNum"));
		int pageSize =Integer.parseInt(StringUtils.isBlank(getPara("pageSize"))||
				getPara("pageSize").equals("undefined")
						==true?
				"10":getPara("pageSize"));

		mRenderJson(guaranteeContrastModelDao.getPage(pageNum,pageSize));
	}


	

}
