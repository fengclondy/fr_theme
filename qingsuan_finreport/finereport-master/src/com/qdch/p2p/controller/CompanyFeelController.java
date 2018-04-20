package com.qdch.p2p.controller;

import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.p2p.model.CompanyFeelModel;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月8日
 * @TODO 企业舆情
 */
public class CompanyFeelController extends BaseController {

	public void index(){
		
		render("p2p/pages/04_04pingtaiyuqing.html");
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月18日
	 * @TODO 舆情概览
	 */
	public void getOverviewCompanFeel(){
		List<CompanyFeelModel> companyFeelModels = CompanyFeelModel.dao.getOverviewCompanFeel(getDataScopeByUserNameForP2p());
		mRenderJson(companyFeelModels);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月18日
	 * @TODO 实时舆情
	 */
	public void getRealCompanyFeel(){
		String jysinfo = getPara("jysinfo");
		List<CompanyFeelModel> companyFeelModels = CompanyFeelModel.dao.getRealCompanyFeel(getDataScopeByUserNameForP2p(), jysinfo);
		mRenderJson(companyFeelModels);
	}
}
