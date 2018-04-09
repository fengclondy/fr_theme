package com.qdch.p2p.controller;

import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.p2p.model.PlatformModel;


/**
 * 
 * @author lixiaoyi
 * @date 2018年4月8日
 * @TODO 平台总览
 */
public class PlatformController extends BaseController {
	
	public void index(){
		
		render("p2p/pages/01_01pingtaizonglanbiao.html");
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月9日
	 * @TODO 获取平台总览信息
	 */
	public void getOverviewInfo(){
		List<PlatformModel> pList = PlatformModel.dao.getOverviewInfo();
		mRenderJson(pList);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月9日
	 * @TODO 获取平台名称
	 */
	public void getJysc(){
		List<PlatformModel> pList = PlatformModel.dao.getJysc();
		mRenderJson(pList);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月9日
	 * @TODO 获取存管银行
	 */
	public void getCgyh(){
		List<PlatformModel> pList = PlatformModel.dao.getCgyh();
		mRenderJson(pList);
	} 
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月9日
	 * @TODO 获取注册资本
	 */
	public void getZczb(){
		List<PlatformModel> pList = PlatformModel.dao.getZczb();
		mRenderJson(pList);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月9日
	 * @TODO 获取等保
	 */
	public void getDb(){
		List<PlatformModel> pList = PlatformModel.dao.getDb();
		mRenderJson(pList);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月9日
	 * @TODO 获取平台实力
	 */
	public void getPtsl(){
		List<PlatformModel> pList = PlatformModel.dao.getPtsl();
		mRenderJson(pList);
	}
}
