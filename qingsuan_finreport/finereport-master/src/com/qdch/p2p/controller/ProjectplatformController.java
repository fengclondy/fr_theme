package com.qdch.p2p.controller;

import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.p2p.model.PpjyscModel;
import com.qdch.p2p.model.ProjectStructureModel;
import com.qdch.xd.model.KeyIndicatorsModel;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月8日
 * @TODO 平台项目
 */
public class ProjectplatformController  extends BaseController{
     
	public void index(){
		setAttr("jyslist",PpjyscModel.dao.getJysc(getDataScopeByUserNameForP2p()));
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
		jys="PP01";
		List<ProjectStructureModel> projectstructuremodel=ProjectStructureModel.dao.getProjectStructure(getDataScopeByUserNameForP2p(),jys);
		mRenderJson(projectstructuremodel);
	}
	

}
