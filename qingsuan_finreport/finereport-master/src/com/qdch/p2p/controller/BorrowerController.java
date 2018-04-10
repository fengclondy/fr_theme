package com.qdch.p2p.controller;

import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.p2p.model.BorrowerModel;
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
	 * @author lixiaoyi
	 * @date 2018年4月8日
	 * @TODO 借款人总览首页
	 */
	public void index(){
		setAttr("pingtailist", BorrowerModel.dao.getScoreList(getDataScopeByUserNameForP2p()));
		setAttr("ziRanRenInfo",BorrowerModel.dao.getBasicinfo(getDataScopeByUserNameForP2p()));
		render("p2p/pages/04_05jiekuanrenhuaxiang.html");
	}
	
	
	
	
	/**
	 * 
	 * @author gaozhao
	 * @date 2018年4月9日
	 * @TODO 获取自然人基本信息
	 */
	public void getZiRanRenInfo(){
		List<BorrowerModel> borrowerZiRanRenInfo=BorrowerModel.dao.getBasicinfo(getDataScopeByUserNameForP2p());
		mRenderJson(borrowerZiRanRenInfo);
	}
	/**
	 * 
	 * @author gaozhao
	 * @date 2018年4月9日
	 * @TODO 获取自然人信用信息
	 */
	public void getZiRanRenXinYong(){
		List<BorrowerModel> borrowerZiRanRenXinyong=BorrowerModel.dao.getCreditinfo(getDataScopeByUserNameForP2p());
		mRenderJson(borrowerZiRanRenXinyong);
	}
	/**
	 * 
	 * @author gaozhao
	 * @date 2018年4月9日
	 * @TODO 获取自然人资产信息
	 */
	public void getZiRanRenZiChanInfo(){
		List<BorrowerModel>  borrowerZiRanRenZiChanInfo=BorrowerModel.dao.getAssetinfo(getDataScopeByUserNameForP2p());
		mRenderJson(borrowerZiRanRenZiChanInfo);
	}
	/**
	 * 
	 * @author gaozhao
	 * @date 2018年4月9日
	 * @TODO 获取自然人工作信息
	 */
	public void getZiRanRenWorkInfo(){
		List<BorrowerModel>  borrowerZiRanRenWorkInfo=BorrowerModel.dao.getJobinfo(getDataScopeByUserNameForP2p());
		mRenderJson(borrowerZiRanRenWorkInfo);
	}
	/**
	 * 
	 * @author gaozhao
	 * @date 2018年4月9日
	 * @TODO 获取企业基本信息
	 */
	public void getQiYeInfo(){
		 List<BorrowerModel> borrowerQiYeInfo=BorrowerModel.dao.getCompybasicinfo(getDataScopeByUserNameForP2p());
		 mRenderJson(borrowerQiYeInfo);
	}
	/**
	 * 
	 * @author gaozhao
	 * @date 2018年4月9日
	 * @TODO 获取企业其它信息
	 */
	public void getQiYeQiTaInFo(){
		 List<BorrowerModel>  borrowerQiYeQiTaInfo=BorrowerModel.dao.getOtherinfo(getDataScopeByUserNameForP2p());
		 mRenderJson(borrowerQiYeQiTaInfo);
	}
	/**
	 * 
	 * @author gaozhao
	 * @date 2018年4月10日
	 * @TODO 获取自然人基本信息
	 */
	
	public void getPingTai(String pingtainame){
		List<BorrowerModel> borrowerZiRanRenInfo=BorrowerModel.dao.getPingtai(getDataScopeByUserNameForP2p(),pingtainame);
		mRenderJson(borrowerZiRanRenInfo);
	}
}
