package com.qdch.p2p.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Record;
import com.qdch.core.BaseController;
import com.qdch.p2p.model.DefenInfoModel;
import com.qdch.p2p.model.WorkInfoModel;
import com.qdch.p2p.model.XinYongInfoModel;
import com.qdch.p2p.model.ZiChanInfoModel;
import com.qdch.p2p.model.ZiRanRenJiChuInfoMoDel;
import com.qdch.xd.model.JyscModel;
import com.qdch.xd.model.KeyIndicatorsModel;
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
		setAttr("pingtailist", DefenInfoModel.dao.getScoreList(getDataScopeByUserNameForP2p(),null));
		//setAttr("ziRanRenInfo",ZiRanRenJiChuInfoMoDel.dao.getBasicinfo(getDataScopeByUserNameForP2p()));
		render("p2p/pages/04_05jiekuanrenhuaxiang.html");
	}
	
	public void getBasicinfo(){
		String ptType=getPara("jys");
		String hasInfo=getPara("hasInfo");
		List<DefenInfoModel> ptList=new ArrayList<DefenInfoModel>();
		
		//查询全部
		ptList=DefenInfoModel.dao.getScoreList(getDataScopeByUserNameForP2p(),ptType);
		for(DefenInfoModel model:ptList){
			ZiRanRenJiChuInfoMoDel zrr=ZiRanRenJiChuInfoMoDel.dao.getBasicinfo(getDataScopeByUserNameForP2p(),model.getStr("jysc"),hasInfo);
			model.put("zrr", zrr);
			XinYongInfoModel xinyon=XinYongInfoModel.dao.getCreditinfo(getDataScopeByUserNameForP2p(),model.getStr("jysc"),hasInfo);
			model.put("xinyon",xinyon);
			ZiChanInfoModel zichan=ZiChanInfoModel.dao.getAssetinfo(getDataScopeByUserNameForP2p(),model.getStr("jysc"),hasInfo);
			model.put("zichan",zichan);
			WorkInfoModel job=WorkInfoModel.dao.getJobinfo(getDataScopeByUserNameForP2p(),model.getStr("jysc"),hasInfo);
			model.put("job",job);
		}
		mRenderJson(ptList);
	}
	
	
	
	
	
}
