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
	private static CustomerInfoModel customerInfoModelDao = CustomerInfoModel.dao;
	private static  PublicCustomModel publicCustomModelDao = PublicCustomModel.dao;
	private static PersonalCustomModel personalCustomModelDao = PersonalCustomModel.dao;
	private static DetailsQueryModel detailsQueryModelDao = DetailsQueryModel.dao;
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
		results.put("guarantee",guaranteeContrastModelDao.getPage(pageNum,pageSize,getRequest())); //担保合同
		results.put("limit",limitQueryModelDao.getPage(pageNum,pageSize,getRequest())); //额度信息
		results.put("custominfo",customerInfoModelDao.getPage(pageNum,pageSize,getRequest())); //合同-基本信息
		results.put("public",publicCustomModelDao.getPage(pageNum,pageSize,getRequest()));
		results.put("personal",personalCustomModelDao.getPage(pageNum,pageSize,getRequest()));
		results.put("details",detailsQueryModelDao.getPage(pageNum,pageSize,getRequest()));
        mRenderJson(results);
	}

	public void getRiskdata(){
		int pageNum =Integer.parseInt(StringUtils.isBlank(getPara("pageNum"))||
				getPara("pageNum").equals("undefined")==true?
				"1":getPara("pageNum"));
		int pageSize =Integer.parseInt(StringUtils.isBlank(getPara("pageSize"))||
				getPara("pageSize").equals("undefined")
						==true?
				"10":getPara("pageSize"));
		mRenderJson(riskEventModelDao.findById(getPara("fxsj_id")));
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

		mRenderJson(guaranteeContrastModelDao.getPage(pageNum,pageSize,getRequest()));
	}

//	/**
//	 * 合同信息-基本信息
//	 */
//	public void getContrastInfo(){
//		int pageNum =Integer.parseInt(StringUtils.isBlank(getPara("pageNum"))||
//				getPara("pageNum").equals("undefined")==true?
//				"1":getPara("pageNum"));
//		int pageSize =Integer.parseInt(StringUtils.isBlank(getPara("pageSize"))||
//				getPara("pageSize").equals("undefined")
//						==true?
//				"10":getPara("pageSize"));
//
//		mRenderJson(customerInfoModelDao.getPage(pageNum,pageSize));
//	}

	/**
	 *合同信息-基本信息
	 */
	public void getCustomInfo(){
		int pageNum =Integer.parseInt(StringUtils.isBlank(getPara("pageNum"))||
				getPara("pageNum").equals("undefined")==true?
				"1":getPara("pageNum"));
		int pageSize =Integer.parseInt(StringUtils.isBlank(getPara("pageSize"))||
				getPara("pageSize").equals("undefined")
						==true?
				"10":getPara("pageSize"));

		mRenderJson(customerInfoModelDao.getPage(pageNum,pageSize,getRequest()));
	}

	/**
	 * 对公客户
	 */
	public void getPublic(){
		int pageNum =Integer.parseInt(StringUtils.isBlank(getPara("pageNum"))||
				getPara("pageNum").equals("undefined")==true?
				"1":getPara("pageNum"));
		int pageSize =Integer.parseInt(StringUtils.isBlank(getPara("pageSize"))||
				getPara("pageSize").equals("undefined")
						==true?
				"10":getPara("pageSize"));

		mRenderJson(publicCustomModelDao.getPage(pageNum,pageSize,getRequest()));
	}
	/**
	 * 个人客户
	 */
	public void getPersonal(){
		int pageNum =Integer.parseInt(StringUtils.isBlank(getPara("pageNum"))||
				getPara("pageNum").equals("undefined")==true?
				"1":getPara("pageNum"));
		int pageSize =Integer.parseInt(StringUtils.isBlank(getPara("pageSize"))||
				getPara("pageSize").equals("undefined")
						==true?
				"10":getPara("pageSize"));

		mRenderJson(personalCustomModelDao.getPage(pageNum,pageSize,getRequest()));
	}

	/**
	 * 额度查询
	 */
	public void getLimit(){
		int pageNum =Integer.parseInt(StringUtils.isBlank(getPara("pageNum"))||
				getPara("pageNum").equals("undefined")==true?
				"1":getPara("pageNum"));
		int pageSize =Integer.parseInt(StringUtils.isBlank(getPara("pageSize"))||
				getPara("pageSize").equals("undefined")
						==true?
				"10":getPara("pageSize"));

		mRenderJson(limitQueryModelDao.getPage(pageNum,pageSize,getRequest()));
	}

	/**
	 * 明细查询
	 */
	public void getDetails(){
		int pageNum =Integer.parseInt(StringUtils.isBlank(getPara("pageNum"))||
				getPara("pageNum").equals("undefined")==true?
				"1":getPara("pageNum"));
		int pageSize =Integer.parseInt(StringUtils.isBlank(getPara("pageSize"))||
				getPara("pageSize").equals("undefined")
						==true?
				"10":getPara("pageSize"));

		mRenderJson(detailsQueryModelDao.getPage(pageNum,pageSize,getRequest()));
	}










}
