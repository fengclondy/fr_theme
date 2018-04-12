
package com.qdch.p2p.controller;

import java.util.List;

import com.fr.report.core.A.m;
import com.qdch.core.BaseController;
import com.qdch.p2p.model.JiaoYiLiangModel;
import com.qdch.p2p.model.PingTaiRenShuModel;
import com.qdch.p2p.model.PlatformModel;
import com.qdch.p2p.model.XinXiPiLouModel;
import com.qdch.p2p.model.ptxxModel;

/**
 * 
 * @author lixiaoyi
 * @date 2018年4月8日
 * @TODO 平台总览alert
 */
public class PlatformAlertController extends BaseController{

	public void index(){
		setAttr("ptxxList", ptxxModel.dao.getPtxx(getDataScopeByUserNameForP2p())); //获取平台信息
		setAttr("jysc", getPara("jysc")); //获取平台编号
		render("p2p/pages/01_01pingtaizonglanbiaoalert.html");
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月11日
	 * @TODO 根据平台获取平台信息
	 */
	public void getPtxxByJysc(){
		String jysc = getPara("jysc");
		List<ptxxModel> ptxxList = ptxxModel.dao.getPtxxByJysc(getDataScopeByUserNameForP2p(), jysc);
		mRenderJson(ptxxList);
	}
	
	/**
	 * 
	 * 
	 * @author hanpengda
	 * @date 2018年4月11日
	 * @TODO 根据平台获取总览信息
	 */
	public void getPlatfromByJysc(){
		String jysc = getPara("jysc");
		List<PlatformModel> platfromList = PlatformModel.dao.getPlatfromByJysc(getDataScopeByUserNameForP2p(), jysc);
		mRenderJson(platfromList);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月11日
	 * @TODO 根据平台获取信息披露
	 */
	public void getXxplByJysc(){
		String jysc = getPara("jysc");
		List<XinXiPiLouModel> xxplList = XinXiPiLouModel.dao.getXxplByJysc(getDataScopeByUserNameForP2p(), jysc);
		mRenderJson(xxplList);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月11日
	 * @TODO 获取总成交量
	 */
	public void getZcjl(){
		String jysc = getPara("jysc");
		List<JiaoYiLiangModel> cjlList = JiaoYiLiangModel.dao.getZcjl(getDataScopeByUserNameForP2p(), jysc);
		mRenderJson(cjlList);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月11日
	 * @TODO 获取总成交额
	 */
	public void getZcje(){
		String jysc = getPara("jysc");
		List<JiaoYiLiangModel> cjeList = JiaoYiLiangModel.dao.getZcje(getDataScopeByUserNameForP2p(), jysc);
		mRenderJson(cjeList);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月11日
	 * @TODO 获取总借款人数
	 */
	public void getZjkrs(){
		String jysc = getPara("jysc");
		List<PingTaiRenShuModel> zjkrs = PingTaiRenShuModel.dao.getZjkrs(getDataScopeByUserNameForP2p(), jysc);
		mRenderError(zjkrs);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月11日
	 * @TODO 获取总投资人数
	 */
	public void getZtzrs(){
		String jysc = getPara("jysc");
		List<PingTaiRenShuModel> ztzrs = PingTaiRenShuModel.dao.getZtzrs(getDataScopeByUserNameForP2p(), jysc);
		mRenderError(ztzrs);
	}
}

