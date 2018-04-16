package com.qdch.p2p.controller;

import java.util.HashMap;
import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.p2p.model.CompositeInterestModel;
import com.qdch.p2p.model.ImportantRatioModel;
import com.qdch.p2p.model.InterestModel;
import com.qdch.p2p.model.PpjyscModel;
import com.qdch.p2p.model.ProjectStructureModel;
import com.qdch.p2p.model.StructuralDetailsModel;
import com.qdch.p2p.model.TermDetailsModel;
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
		
		List<InterestModel> interestmodel=InterestModel.dao.getInterest(getDataScopeByUserNameForP2p(),jys,type);
		mRenderJson(interestmodel);
	}
	/**
	 * 获取平台综合利率和行业综合利率
	* @author doushuihai  
	* @date 2018年4月11日下午6:15:20  
	* @TODO
	 */
	public void getCompositeInterest(){
		String jys=getPara("jys");	
		List<CompositeInterestModel> platformcominterest=CompositeInterestModel.dao.getPlatformComInterest(getDataScopeByUserNameForP2p(),jys);
		List<CompositeInterestModel> industryinterest=CompositeInterestModel.dao.getIndustryComInterest(getDataScopeByUserNameForP2p(), jys);
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("platformcominterestList", platformcominterest);
		map.put("industryinterestList", industryinterest);
		mRenderJson(map);
	}
	/**
	 * 获取项目结构详情
	* @author doushuihai  
	* @date 2018年4月12日下午4:51:22  
	* @TODO
	 */
	public void getStructuralDetails(){
		String jys=getPara("jys");		
		List<StructuralDetailsModel> structuraldetailsmodel=StructuralDetailsModel.dao.getStructuralDetails(getDataScopeByUserNameForP2p(),jys);
		mRenderJson(structuraldetailsmodel);
	}
	public void getTermDetails(){
		String jys=getPara("jys");		
		List<TermDetailsModel> termdetailsmodel=TermDetailsModel.dao.getTermDetails(getDataScopeByUserNameForP2p(),jys);
		mRenderJson(termdetailsmodel);
	}
	

}
