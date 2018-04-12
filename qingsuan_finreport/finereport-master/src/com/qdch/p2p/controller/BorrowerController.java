package com.qdch.p2p.controller;

import java.util.ArrayList;
import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.p2p.model.DefenInfoModel;
import com.qdch.p2p.model.PpjyscModel;
import com.qdch.p2p.model.QiYeJiBenInFoModel;
import com.qdch.p2p.model.QiYeQiTaInFoModel;
import com.qdch.p2p.model.WorkInfoModel;
import com.qdch.p2p.model.XinYongInfoModel;
import com.qdch.p2p.model.ZiChanInfoModel;
import com.qdch.p2p.model.ZiRanRenJiChuInfoMoDel;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月8日
 * @TODO 借款人总览
 */
public class BorrowerController extends BaseController {
	/**
	 * 
	 * @author 高照
	 * @date 2018年4月10日
	 * @TODO 借款人总览首页
	 */
	public void index(){
		String ptType=getPara("jys");
		String hasInfo=getPara("hasInfo");
		List<DefenInfoModel> ptList=new ArrayList<DefenInfoModel>();
		ptList=DefenInfoModel.dao.getScoreList(getDataScopeByUserNameForP2p(),ptType);
		setAttr("jyslist", PpjyscModel.dao.getJysc(getDataScopeByUserName()));
		for(DefenInfoModel model:ptList){
			ZiRanRenJiChuInfoMoDel zrr=ZiRanRenJiChuInfoMoDel.dao.getBasicinfo(getDataScopeByUserNameForP2p(),model.getStr("jysinfo"),hasInfo);
			model.put("zrr",zrr);
			XinYongInfoModel xinyon=XinYongInfoModel.dao.getCreditinfo(getDataScopeByUserNameForP2p(),model.getStr("jysinfo"),hasInfo);
			model.put("xinyon",xinyon);
			ZiChanInfoModel zichan=ZiChanInfoModel.dao.getAssetinfo(getDataScopeByUserNameForP2p(),model.getStr("jysinfo"),hasInfo);
			model.put("zichan",zichan);
			WorkInfoModel job=WorkInfoModel.dao.getJobinfo(getDataScopeByUserNameForP2p(),model.getStr("jysinfo"),hasInfo);
			model.put("job",job);
			QiYeJiBenInFoModel qjb=QiYeJiBenInFoModel.dao.getCompybasicinfo(getDataScopeByUserNameForP2p(),model.getStr("jysinfo"),hasInfo);
			model.put("qjb",qjb);
			QiYeQiTaInFoModel qqt=QiYeQiTaInFoModel.dao.getOtherinfo(getDataScopeByUserNameForP2p(),model.getStr("jysinfo"),hasInfo);
			model.put("qqt",qqt);
			
		}
		setAttr("pingtailist",ptList);
		setAttr("jys",ptType);
		setAttr("hasInfo",hasInfo);
		
		
		
		render("p2p/pages/04_05jiekuanrenhuaxiang.html");
	}
	
	
	
	
	
	
}
