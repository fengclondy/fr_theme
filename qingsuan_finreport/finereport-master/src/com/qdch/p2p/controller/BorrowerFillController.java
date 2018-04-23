package com.qdch.p2p.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fr.hailian.util.JDBCUtil;
import com.fr.report.core.A.m;
import com.qdch.core.BaseController;
import com.qdch.p2p.model.DefenInfoModel;
import com.qdch.p2p.model.PingTaiXinXiModel;
import com.qdch.p2p.model.QiYeJiBenInFoModel;
import com.qdch.p2p.model.QiYeQiTaInFoModel;
import com.qdch.p2p.model.WorkInfoModel;
import com.qdch.p2p.model.XinYongInfoModel;
import com.qdch.p2p.model.ZiChanInfoModel;
import com.qdch.p2p.model.ZiRanRenJiChuInfoMoDel;

/**
 * @author hanpengda
 * @date 2018年4月13日
 * @TODO 借款人填报
 */
public class BorrowerFillController extends BaseController{
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月13日
	 * @TODO 初始化页面
	 */
	public void index(){
		setAttr("ptxxList", PingTaiXinXiModel.dao.getPtxx(getDataScopeByUserNameForP2p())); //获取平台信息
		render("p2p/pages/07_01jiekuanrentianbao.html");
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月13日
	 * @TODO 根据平台获取自然人基本信息
	 */
	public void getBasicInfo(){
		String jysc = getPara("jysc");
		ZiRanRenJiChuInfoMoDel zrr = ZiRanRenJiChuInfoMoDel.dao.getInfoByJysc(getDataScopeByUserNameForP2p(), jysc);
		mRenderJson(zrr);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月13日
	 * @TODO 根据平台获取资产信息
	 */
	public void getAssetInfoByJysc(){
		String jysc = getPara("jysc");
		ZiChanInfoModel zc = ZiChanInfoModel.dao.getAssetInfoByJysc(getDataScopeByUserNameForP2p(), jysc);
		mRenderJson(zc);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月13日
	 * @TODO 根据平台获取信用信息
	 */
	public void getCreditinfoByJysc(){
		String jysc = getPara("jysc");
		XinYongInfoModel xy = XinYongInfoModel.dao.getCreditInfoByJysc(getDataScopeByUserNameForP2p(), jysc);
		mRenderJson(xy);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月13日
	 * @TODO 根据平台获取工作信息
	 */
	public void getJobInfoByJysc(){
		String jysc = getPara("jysc");
		WorkInfoModel job = WorkInfoModel.dao.getJobInfoByJysc(getDataScopeByUserNameForP2p(), jysc);
		mRenderJson(job);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月13日
	 * @TODO 根据平台获取企业基本信息
	 */
	public void getCompyBasicInfoByJysc(){
		String jysc = getPara("jysc");
		QiYeJiBenInFoModel qyjbxx = QiYeJiBenInFoModel.dao.getCompyBasicInfoByJysc(getDataScopeByUserNameForP2p(), jysc);
		mRenderJson(qyjbxx);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月13日
	 * @TODO 根据平台获取企业其他信息
	 */
	public void getOtherInfoByJysc(){
		String jysc = getPara("jysc");
		QiYeQiTaInFoModel qyqt = QiYeQiTaInFoModel.dao.getOtherInfoByJysc(getDataScopeByUserNameForP2p(), jysc);
		mRenderJson(qyqt);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月13日
	 * @TODO 录入自然人信息
	 */
	public void submitZrrxx(){
		String jysc = getPara("jysc");
		ZiRanRenJiChuInfoMoDel zrr = ZiRanRenJiChuInfoMoDel.dao.getInfoByJysc(getDataScopeByUserNameForP2p(), jysc);
		String sql = "insert into insight_pp_person_info(vday,jysc,jysinfo,jyscmc,jkf,pname,sfz,jkyt,sex,age,xuel,hyzk,hjszd,hkly,jyscfl)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object params [] = {
							new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis()),
							jysc,
							zrr.get("jysinfo"),
							zrr.get("jyscmc"),
							zrr.get("jkf"),
							getPara("pname"),
							getPara("sfz"),
							getPara("jkyt"),
							getPara("sex"),
							getPara("age"),
							getPara("xuel"),
							getPara("hyzk"),
							getPara("hjszd"),
							getPara("hkly"),
							zrr.get("jyscfl")
							};
		JDBCUtil.executeUpdate(sql, params,"insight");
		mRenderJson(null);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月13日
	 * @TODO 录入信用信息
	 */
	public void submitXyxx(){
		String jysc = getPara("jysc");
		XinYongInfoModel xModel = XinYongInfoModel.dao.getCreditInfoByJysc(getDataScopeByUserNameForP2p(), jysc);
		String sql = "insert into insight_pp_credit_info(vday,jysc,jyscmc,jysinfo,jkf,yqcs,yqje,zxbg,jkbs,jkze,zxcs,jyscfl,jkrxy,qijk)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object params [] = {
							new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis()),
							jysc,
							xModel.get("jyscmc"),
							xModel.get("jysinfo"),
							xModel.get("jkf"),
							getPara("yqcs"),
							getPara("yqje"),
							getPara("zxbg"),
							getPara("jkbs"),
							getPara("jkze"),
							getPara("zxcs"),
							xModel.get("jyscfl"),
							getPara("jkrxy"),
							xModel.get("qijk")
							}; 
		JDBCUtil.executeUpdate(sql, params,"insight");
		mRenderJson(null);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月13日
	 * @TODO 录入资产信息
	 */
	public void submitZcxx(){
		String jysc = getPara("jysc");
		ZiChanInfoModel zModel = ZiChanInfoModel.dao.getAssetInfoByJysc(getDataScopeByUserNameForP2p(), jysc);
		String sql = "insert into insight_pp_asset_info(vday,jysc,jyscmc,jysinfo,jkf,srqk,gf_flag,gc_flag,fd_flag,cd_flag,jyscfl)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?)";
		Object params [] = {
							new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis()),
							jysc,
							zModel.get("jyscmc"),
							zModel.get("jysinfo"),
							zModel.get("jkf"),
							getPara("srqk"),
							getPara("gf_flag"),
							getPara("gc_flag"),
							getPara("fd_flag"),
							getPara("cd_flag"),
							zModel.get("jyscfl")
							}; 
		JDBCUtil.executeUpdate(sql, params,"insight");
		mRenderJson(null);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月13日
	 * @TODO 录入工作信息
	 */
	public void submitGzxx(){
		String jysc = getPara("jysc");
		WorkInfoModel wModel = WorkInfoModel.dao.getJobInfoByJysc(getDataScopeByUserNameForP2p(), jysc);
		String sql = "insert into insight_pp_job_info(vday,jysc,jyscmc,jysinfo,jkf,city,industry,gsgm,gzgw,gznx,jyscfl)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?)";
		Object params [] = {
							new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis()),
							jysc,
							wModel.get("jyscmc"),
							wModel.get("jysinfo"),
							wModel.get("jkf"),
							getPara("city"),
							getPara("industry"),
							getPara("gsgm"),
							getPara("gzgw"),
							getPara("gznx"),
							wModel.get("jyscfl")
							}; 
		JDBCUtil.executeUpdate(sql, params,"insight");
		mRenderJson(null);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月13日
	 * @TODO 录入企业基本信息
	 */
	public void submitQyjbxx(){
		String jysc = getPara("jysc");
		QiYeJiBenInFoModel qModel = QiYeJiBenInFoModel.dao.getCompyBasicInfoByJysc(getDataScopeByUserNameForP2p(), jysc);
		String sql = "insert into insight_pp_corp_info(vday,jysc,jyscmc,jysinfo,jkf,gsmc,frdb,clsj,zczb,zcdz,gsgm,yyfw,zjyt,hkly,jyscfl)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object params [] = {
							new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis()),
							jysc,
							qModel.get("jyscmc"),
							qModel.get("jysinfo"),
							qModel.get("jkf"),
							getPara("gsmc"),
							getPara("frdb"),
							getPara("clsj"),
							getPara("zczb"),
							getPara("zcdz"),
							getPara("gsgm"),
							getPara("yyfw"),
							getPara("zjyt"),
							getPara("hkly"),
							qModel.get("jyscfl")
							}; 
		JDBCUtil.executeUpdate(sql, params,"insight");
		mRenderJson(null);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月13日
	 * @TODO 录入企业其他信息
	 */
	public void submitQtxx(){
		String jysc = getPara("jysc");
		QiYeQiTaInFoModel qModel = QiYeQiTaInFoModel.dao.getOtherInfoByJysc(getDataScopeByUserNameForP2p(), jysc);
		String sql = "insert into insight_pp_othe_info(vday,jysc,jyscmc,jysinfo,jkf,srqk,fzqk,zxbg,qijk_flag,gdxx,frxyxx,zxcs,ssxx,jyscfl)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object params [] = {
							new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis()),
							jysc,
							qModel.get("jyscmc"),
							qModel.get("jysinfo"),
							qModel.get("jkf"),
							getPara("srqk"),
							getPara("fzqk"),
							getPara("zxbg"),
							getPara("qijk_flag"),
							getPara("gdxx"),
							getPara("xyxx"),
							getPara("zxcs"),
							getPara("ssxx"),
							qModel.get("jyscfl")
							}; 
		JDBCUtil.executeUpdate(sql, params,"insight");
		mRenderJson(null);
	}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月17日
	 * @TODO 录入得分信息
	 */
	public void submitDfxx(){
		String jysc = getPara("jysc");
		String df = "0";
		if (StringUtils.isNotBlank(getPara("fscore"))) {
			 df = getPara("fscore");
		}
		DefenInfoModel dModel = DefenInfoModel.dao.getDeFenByJysc(getDataScopeByUserNameForP2p(), jysc);
		String sql = "insert into insight_pp_score_info(vday,jysc,jyscmc,jysinfo,fscore,jyscfl)"
				+ " values(?,?,?,?,?,?)";
		Object params [] = {
							new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis()),
							jysc,
							dModel.get("jyscmc"),
							dModel.get("jysinfo"),
							Double.parseDouble(df),
							dModel.get("jyscfl")
							}; 
		JDBCUtil.executeUpdate(sql, params,"insight");
		mRenderJson(null);
	}
}
