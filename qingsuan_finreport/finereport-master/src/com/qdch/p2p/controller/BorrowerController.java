
package com.qdch.p2p.controller;

import java.util.ArrayList;
import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.p2p.model.DefenInfoModel;
import com.qdch.p2p.model.DiYaShenHeModel;
import com.qdch.p2p.model.PpjyscModel;
import com.qdch.p2p.model.QiYeJiBenInFoModel;
import com.qdch.p2p.model.QiYeQiTaInFoModel;
import com.qdch.p2p.model.ShenHeQiYeModel;
import com.qdch.p2p.model.ShenHeZiLiaoZiRanRenModel;
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
		
		String ptType=getPara("jys","");
		if("全部".equals(ptType)){
			ptType=null;
		}
		String hasInfo=getPara("hasInfo","");
		List<DefenInfoModel> ptList=new ArrayList<DefenInfoModel>();
		//List<PpjyscModel> ptList=new ArrayList<PpjyscModel>();
		//ptList=PpjyscModel.dao.getJyscP2P(getDataScopeByUserNameForP2p(),ptType);
		setAttr("jyslist", PpjyscModel.dao.getJysc(getDataScopeByUserName()));
		ptList= DefenInfoModel.dao.getScoreList(getDataScopeByUserNameForP2p(),ptType, hasInfo);
		for(DefenInfoModel model:ptList){
			ZiRanRenJiChuInfoMoDel zrr=ZiRanRenJiChuInfoMoDel.dao.getBasicinfo(getDataScopeByUserNameForP2p(),model.getStr("jysc"),hasInfo);
			model.put("zrr",zrr);
			XinYongInfoModel xinyon=XinYongInfoModel.dao.getCreditinfo(getDataScopeByUserNameForP2p(),model.getStr("jysc"),hasInfo);
			model.put("xinyon",xinyon);
			ZiChanInfoModel zichan=ZiChanInfoModel.dao.getAssetinfo(getDataScopeByUserNameForP2p(),model.getStr("jysc"),hasInfo);
			model.put("zichan",zichan);
			WorkInfoModel job=WorkInfoModel.dao.getJobinfo(getDataScopeByUserNameForP2p(),model.getStr("jysc"),hasInfo);
			model.put("job",job);
			QiYeJiBenInFoModel qjb=QiYeJiBenInFoModel.dao.getCompybasicinfo(getDataScopeByUserNameForP2p(),model.getStr("jysc"),hasInfo);
			model.put("qjb",qjb);
			QiYeQiTaInFoModel qqt=QiYeQiTaInFoModel.dao.getOtherinfo(getDataScopeByUserNameForP2p(),model.getStr("jysc"),hasInfo);
			model.put("qqt",qqt);
			DiYaShenHeModel diya=DiYaShenHeModel.dao.getDiYa(getDataScopeByUserNameForP2p(),model.getStr("jysc"),hasInfo);
			model.put("diya",diya);
			ShenHeQiYeModel shqy=ShenHeQiYeModel.dao.getShenHeQiYe(getDataScopeByUserNameForP2p(),model.getStr("jysc"),hasInfo);
			model.put("shqy",shqy);
			ShenHeZiLiaoZiRanRenModel shzr=ShenHeZiLiaoZiRanRenModel.dao.getShenHeZiRanRen(getDataScopeByUserNameForP2p(),model.getStr("jysc"),hasInfo);
			model.put("shzr",shzr);
			/*DefenInfoModel defen=DefenInfoModel.dao.getScoreList(getDataScopeByUserNameForP2p(),model.getStr("jysc"),hasInfo);
			model.put("defen",defen);*/
		}
		if(ptList!=null&&ptList.size()==1){
			setAttr("single",1);
		}else{
			setAttr("single",ptList.size());
		}
		setAttr("pingtailist",ptList);
		setAttr("jys",ptType);
		setAttr("hasInfo",hasInfo);
	
		
		render("p2p/pages/04_05jiekuanrenhuaxiang.html");
	}
	
	
	
	
	
	
}


