

package com.qdch.p2p.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fr.report.core.A.m;
import com.qdch.core.BaseController;
import com.qdch.p2p.model.AvgTermTimeModel;
import com.qdch.p2p.model.FenSanDuJiSuanModel;
import com.qdch.p2p.model.FullScaleTimeModel;
import com.qdch.p2p.model.JiaoYiLiangModel;
import com.qdch.p2p.model.LoanBalanceModel;
import com.qdch.p2p.model.PingTaiRenShuModel;
import com.qdch.p2p.model.PingTaiZhuangTaiModel;
import com.qdch.p2p.model.PlatformModel;
import com.qdch.p2p.model.XinXiPiLouModel;
import com.qdch.p2p.model.PingTaiXinXiModel;
import com.qdch.p2p.model.RenJunCiShuModel;

/**
 * 
 * @author lixiaoyi
 * @date 2018年4月8日
 * @TODO 平台总览alert
 */
public class PlatformAlertController extends BaseController{

	public void index(){
		setAttr("ptxxList", PingTaiXinXiModel.dao.getPtxx(getDataScopeByUserNameForP2p())); //获取平台信息
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
		List<PingTaiXinXiModel> ptxxList = PingTaiXinXiModel.dao.getPtxxByJysc(getDataScopeByUserNameForP2p(), jysc);
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
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月12日
	 * @TODO 获取人均借款
	 */
	public void getRjjk(){
		String jysc = getPara("jysc");
		List<JiaoYiLiangModel> cjeList = JiaoYiLiangModel.dao.getZcje(getDataScopeByUserNameForP2p(), jysc); //获取上个月总成交额
		List<PingTaiRenShuModel> zjkrs = PingTaiRenShuModel.dao.getZjkrs(getDataScopeByUserNameForP2p(), jysc);//获取总借款人数
		Map<String, Object> map = new HashMap<>();
		for(JiaoYiLiangModel m:cjeList){
			
			map.put("zcje", m.get("zcje"));
		}
		for(PingTaiRenShuModel m:zjkrs){
			map.put("zjkrs", m.get("zjkrs"));
		}
		mRenderJson(map);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月12日
	 * @TODO 获取人均投资
	 */
	public void getRjtz(){
		String jysc = getPara("jysc");
		List<JiaoYiLiangModel> cjeList = JiaoYiLiangModel.dao.getZcje(getDataScopeByUserNameForP2p(), jysc); //获取上个月总成交额
		List<PingTaiRenShuModel> ztzrs = PingTaiRenShuModel.dao.getZtzrs(getDataScopeByUserNameForP2p(), jysc); //获取总投资人数
		Map<String, Object> map = new HashMap<>();
		for(JiaoYiLiangModel m:cjeList){
			map.put("zcje", m.get("zcje"));
		}
		for(PingTaiRenShuModel m:ztzrs){
			map.put("ztzrs", m.get("ztzrs"));
		}
		mRenderJson(map);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月12日
	 * @TODO 获取借款分散度
	 */
	public void getJkfsd(){
		String jysc = getPara("jysc");
		List<FenSanDuJiSuanModel> fsdList = FenSanDuJiSuanModel.dao.getJkfsd(getDataScopeByUserNameForP2p(), jysc);
		mRenderJson(fsdList);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月12日
	 * @TODO 获取投资分散度
	 */
	public void getTzfsd(){
		String jysc = getPara("jysc");
		List<FenSanDuJiSuanModel> fsdList = FenSanDuJiSuanModel.dao.getTzfsd(getDataScopeByUserNameForP2p(), jysc);
		mRenderJson(fsdList);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月12日
	 * @TODO 获取人均借款次数
	 */
	public void getRjjkcs(){
		String jysc = getPara("jysc");
		List<RenJunCiShuModel> rjjkcs = RenJunCiShuModel.dao.getRjjkcs(getDataScopeByUserNameForP2p(), jysc);
		mRenderJson(rjjkcs);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月12日
	 * @TODO 获取人均投资次数
	 */
	public void getRjtzcs(){
		String jysc = getPara("jysc");
		List<RenJunCiShuModel> rjtzcs = RenJunCiShuModel.dao.getRjjkcs(getDataScopeByUserNameForP2p(), jysc);
		mRenderJson(rjtzcs);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月12日
	 * @TODO 获取平台状态
	 */
	public void getPtzt(){
		String jysc = getPara("jysc");
		List<PingTaiZhuangTaiModel> ptzt = PingTaiZhuangTaiModel.dao.getPtzt(getDataScopeByUserNameForP2p(), jysc);
		mRenderJson(ptzt);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月12日
	 * @TODO 获取平均借款期限
	 */
	public void getPjjkqx(){
		String jysc = getPara("jysc");
		List<AvgTermTimeModel> pjjkqx = AvgTermTimeModel.dao.getPjjkqx(getDataScopeByUserNameForP2p(), jysc);
		mRenderJson(pjjkqx);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月12日
	 * @TODO 获取贷款余额
	 */
	public void getDkye(){
		String jysc = getPara("jysc");
		List<LoanBalanceModel> dkye = LoanBalanceModel.dao.getDkye(getDataScopeByUserNameForP2p(), jysc);
		mRenderJson(dkye);
	}
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月12日
	 * @TODO 获取上个月时间不为0的平均满标用时
	 */
	public void getMbys(){
		String jysc = getPara("jysc");
		List<FullScaleTimeModel> mbys = FullScaleTimeModel.dao.getMbys(getDataScopeByUserNameForP2p(), jysc);
		mRenderJson(mbys);
	}
}

