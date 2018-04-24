package com.qdch.p2p.controller;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Page;
import com.qdch.core.BaseController;
import com.qdch.p2p.model.MonthlyReportListModelp2p;
import com.qdch.p2p.model.PpjyscModel;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月8日
 * @TODO 监管月报
 */
public class SuperviseController extends BaseController{
	
	public void index() {
		setAttr("jyslist", PpjyscModel.dao.getJysc(getDataScopeByUserNameForP2p()));
		render("p2p/pages/06_01jianguanyuebao.html");
	}
	public void addnew() {
		setAttr("jyslist", PpjyscModel.dao.getJysc(getDataScopeByUserNameForP2p()));
		render("p2p/pages/06_02yuebaoshangchuan.html");
	}
	
	/**
	 * 分页查询
	* @author ljm 
	* @date 2018年4月24日 
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
		Page<MonthlyReportListModelp2p> page = MonthlyReportListModelp2p.getMonthlyReportList(getDataScopeByUserNameForP2p(),pageNum,pageSize,getRequest());
		mRenderJson(page);
	}


}
