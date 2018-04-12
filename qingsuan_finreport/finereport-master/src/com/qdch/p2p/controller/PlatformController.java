
package com.qdch.p2p.controller;

import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.p2p.model.PlatformModel;
import com.qdch.p2p.model.ptxxModel;


/**
 * 
 * @author lixiaoyi
 * @date 2018年4月8日
 * @TODO 平台总览
 */
public class PlatformController extends BaseController {
	
	public void index(){
		setAttr("ptxxList", ptxxModel.dao.getPtxx(getDataScopeByUserNameForP2p())); //获取平台信息
		render("p2p/pages/01_01pingtaizonglanbiao.html");
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月10日
	 * @TODO 获取平台总览信息
	 */
	public void findOverviewInfo(){
		String ptmc = getPara("ptmc"); //获取平台名称
		String cgyh = getPara("cgyh"); //获取银行存管
		String zczb = getPara("zczb"); //获取注册资本
		String db = getPara("db");    //获取等保
		String ptsl = getPara("ptsl"); // 获取平台实力
		List<PlatformModel> list = PlatformModel.dao.findOverviewInfo(getDataScopeByUserNameForP2p(), ptmc, cgyh, zczb, db, ptsl);
		mRenderJson(list);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月10日
	 * @TODO 获取平台总览信息条数
	 */
	public void countOverviewInfo(){
		String ptmc = getPara("ptmc"); //获取平台名称
		String cgyh = getPara("cgyh"); //获取银行存管
		String zczb = getPara("zczb"); //获取注册资本
		String db = getPara("db");    //获取等保
		String ptsl = getPara("ptsl"); // 获取平台实力
		List<PlatformModel> list = PlatformModel.dao.countOverviewInfo(getDataScopeByUserNameForP2p(), ptmc, cgyh, zczb, db, ptsl);
		mRenderJson(list);
	}
	
}

