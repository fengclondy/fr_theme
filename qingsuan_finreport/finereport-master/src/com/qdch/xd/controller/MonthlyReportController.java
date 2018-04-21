package com.qdch.xd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;
import com.qdch.core.BaseController;
import com.qdch.xd.model.JyscModel;
import com.qdch.xd.model.MonthlyReportListModel;
import com.qdch.xd.model.MonthlyReportModel;
import com.qdch.xd.model.RiskEventModel;
/**
 * 
* @author doushuihai  
* @date 2018年3月30日下午1:07:26  
* @TODO 监管报告-监管月报
 */
public class MonthlyReportController extends BaseController {
	/**
	 * 
	* @author doushuihai  
	* @date 2018年3月30日下午1:08:45  
	* @TODO 监管月报页面
	 */
	public void index() {
		setAttr("jyslist", JyscModel.dao.getJysc(getDataScopeByUserName()));//在进入页面时就获取权限内表为条件下拉框做准备
		render("xd/pages/06_01jianguanyuebao.html");
	}
	
	public void addnew() {
		//setAttr("jyslist", MonthlyReportModel.dao.getJys(getDataScopeByUserName()));//在进入页面时就获取权限内表为条件下拉框做准备
		setAttr("jyslist", JyscModel.dao.getJysc(getDataScopeByUserName()));//在进入页面时就获取权限内表为条件下拉框做准备
		render("xd/pages/06_02yuebaoshangchuan.html");
	}
	/**
	 * 获取监管月报
	* @author doushuihai  
	* @date 2018年4月4日上午9:50:39  
	* @TODO
	 */
	public void getMonthlyReport(){
		String jys=getPara("jys");//获取第一个条件参数：交易市场		
		String reportType = getPara("reportType");//获取第二个条件参数：报告类型
		String date = getPara("date");//获取第二个条件参数：报告类型
		
		List<MonthlyReportModel> monthlyreportmodel=MonthlyReportModel.dao.getMonthlyReport(getDataScopeByUserName(),jys,reportType,date);
		
		mRenderJson(monthlyreportmodel);
		
	}
	/**
	 * 分页查询
	* @author doushuihai  
	* @date 2018年4月8日下午2:57:58  
	* @TODO
	 */
	public void getMonthlyReportList(){
		int pageNum =Integer.parseInt(StringUtils.isBlank(getPara("pageNum"))||
				getPara("pageNum").equals("undefined")==true?
				"1":getPara("pageNum"));
		int pageSize =Integer.parseInt(StringUtils.isBlank(getPara("pageSize"))||
				getPara("pageSize").equals("undefined")
				==true?
				"10":getPara("pageSize"));
		getResponse().setCharacterEncoding("UTF-8");
		Page<MonthlyReportListModel> page = MonthlyReportListModel.getMonthlyReportList(getDataScopeByUserName(),pageNum,pageSize,getRequest());
		mRenderJson(page);
	}
}
