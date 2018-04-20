package com.qdch.p2p.controller;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
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
		setAttr("flist", CompanyFeelModel.dao.getOverviewCompanFeel(getDataScopeByUserNameForP2p()));
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
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月18日
	 * @TODO 最新资讯
	 */
	public void getNews(){
		String jysinfo = getPara("jysinfo");
		List<CompanyFeelModel> companyFeelModels = CompanyFeelModel.dao.getNews(getDataScopeByUserNameForP2p(),jysinfo);
		mRenderJson(companyFeelModels);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月19日
	 * @TODO 舆情详情
	 */
	public void getDetailCompanyFeel(){
		String jysc = getPara("jysc");
		String startTime = getPara("startTime");
		String endTime = getPara("endTime");
		String keyword = getPara("keyword");
		int pageSize = Integer.valueOf(getPara("pageSize"));
		int currentPage = Integer.valueOf(getPara("currentPage"));
		List<CompanyFeelModel> companyFeelModels = CompanyFeelModel.dao.getDetailCompanyFeel(getDataScopeByUserNameForP2p(), startTime, endTime, jysc,keyword,pageSize,currentPage);
		mRenderJson(companyFeelModels);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月19日
	 * @TODO 舆情详情
	 */
	public void getDetailCount(){
		String jysc = getPara("jysc");
		String startTime = getPara("startTime");
		String endTime = getPara("endTime");
		String keyword = getPara("keyword");
		
		List<CompanyFeelModel> companyFeelModels = CompanyFeelModel.dao.getDetailCount(getDataScopeByUserNameForP2p(), startTime, endTime, jysc,keyword);
		mRenderJson(companyFeelModels);
	}
	
	
	
}
