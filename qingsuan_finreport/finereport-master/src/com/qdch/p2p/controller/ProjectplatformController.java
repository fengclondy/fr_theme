package com.qdch.p2p.controller;

import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.p2p.model.ImportantRatioModel;
import com.qdch.p2p.model.InterestModel;
import com.qdch.p2p.model.PpjyscModel;
import com.qdch.p2p.model.ProjectStructureModel;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月8日
 * @TODO 平台项目
 */
public class ProjectplatformController  extends BaseController{
     
	public void index(){
		setAttr("jyslist",PpjyscModel.dao.getJysc(getDataScopeByUserNameForP2p()));
		setAttr("rangetypeList", InterestModel.dao.getRangeType(getDataScopeByUserNameForP2p()));
		render("p2p/pages/04_01pingtaixiangmuhuaxiang.html");
	}
	/**
	 * 获取平台项目的项目结构
	* @author doushuihai  
	* @date 2018年4月10日上午10:24:13  
	* @TODO
	 */
	public void getProjectStructure(){
		String jys=getPara("jys");
		
		List<ProjectStructureModel> projectstructuremodel=ProjectStructureModel.dao.getProjectStructure(getDataScopeByUserNameForP2p(),jys);
		mRenderJson(projectstructuremodel);
	}
	/**
	 * 获取逾期率，逾期金额，代偿金额，代偿笔数四个重要比率
	* @author doushuihai  
	* @date 2018年4月10日下午5:04:52  
	* @TODO
	 */
	public void getImporttantRatio(){
		String jys=getPara("jys");
		
		List<ImportantRatioModel> importantratiomodel=ImportantRatioModel.dao.getImportantRatio(getDataScopeByUserNameForP2p(),jys);
		mRenderJson(importantratiomodel);
	}
	/**
	 * 平台利率
	* @author doushuihai  
	* @date 2018年4月11日下午6:15:20  
	* @TODO
	 */
	public void getInterest(){
		String jys=getPara("jys");
		String type=getPara("rangeType");
		
		List<InterestModel> importantratiomodel=InterestModel.dao.getInterest(getDataScopeByUserNameForP2p(),jys,type);
		mRenderJson(importantratiomodel);
	}
	

}
