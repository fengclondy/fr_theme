package com.qdch.industry.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr.Item;
import com.fr.hailian.util.HttpClientUtil;
import com.fr.hailian.util.JsonKit;
import com.qdch.core.BaseController;
import com.qdch.core.Constants;
/**
 * 工商信息接口获取
 * @author lixiaoyi
 * @date 2018年4月9日
 * @TODO
 */
public class IndustryComController extends BaseController {
	
	/**
	 * 获取企业简介企业背景 基本信息工商信息 企业年报 变更记录 核心人员 分支机构
	 * @author lixiaoyi
	 * @date 2018年4月9日
	 * @TODO
	 */
	public void getInfo(){
	   List<Map<String,Object>>   lMaps=  new ArrayList<Map<String,Object>>();
	   String keyword= getPara("keyword");
	    if (keyword==null||"".equals(keyword)) {
	    keyword="小米科技";
	   }
		//企业基本信息获取包括changerecords（变更记录）employees（核心人员）branches（分支机构）
		String url=Constants.URL_STRING+"enterprise/getDetailAndContactByName?appkey="+Constants.appkey+"&keyword="+keyword;
	    String result=	HttpClientUtil.sendGetRequest(url, "UTF-8");
	    Map<String,Object>  infomap=   JsonKit.json2map(result);
	     
	    System.out.println(infomap.get("data"));
	    lMaps.add(infomap);
		//获取企业年报
	    String report=Constants.URL_STRING+"reports/getReportListByName?appkey="+Constants.appkey+"&keyword="+keyword;
		String result2=	HttpClientUtil.sendGetRequest(report, "UTF-8");
	    Map<String,Object> map=	JsonKit.json2map(result2);
	    lMaps.add(map);
	    //获取对外投资信息
	    String entinvest=Constants.URL_STRING+"v2/entinvest/getEntInvestByName?appkey="+Constants.appkey+"&name="+keyword;
        String entinresult=	HttpClientUtil.sendGetRequest(entinvest, "UTF-8");
	    Map<String,Object> entinmap=	JsonKit.json2map(entinresult);
		lMaps.add(entinmap);
	    mRenderJson(lMaps);
	
	}

	/**
	 * 获取司法风险信息
	 * @author lixiaoyi
	 * @date 2018年4月9日
	 * @TODO
	 */
	public void getJudicial(){
		 List<Map<String,Object>>   lMaps=  new ArrayList<Map<String,Object>>();
		 String keyword= getPara("keyword");
		 String name=getPara("name");
		  if (keyword==null||"".equals(keyword)) {
		     keyword="小米科技";
		   }
		  //根据失信人姓名查询失信人信息
		  String url=Constants.URL_STRING+"execution/getExecutionListByNameOrCId?appkey="+Constants.appkey+"&skip=0&keyword="+name;
		  String result=	HttpClientUtil.sendGetRequest(url, "UTF-8");
		  Map<String,Object> map=  JsonKit.json2map(result);
		  lMaps.add(map);
		  //法律诉讼列表
		  String law=Constants.URL_STRING+"lawsuit/getLawsuitListByName?appkey="+Constants.appkey+"&skip=0&keyword="+keyword;
		  String lawresult=	HttpClientUtil.sendGetRequest(law, "UTF-8");
		  Map<String,Object> lawMap=  JsonKit.json2map(lawresult);
		  lMaps.add(lawMap);
		  //查询法院公告
		  String notice=Constants.URL_STRING+"notice/getNoticeListByName?appkey="+Constants.appkey+"&skip=0&keyword="+keyword;
		  String noticeresult=	HttpClientUtil.sendGetRequest(notice, "UTF-8");
		  Map<String,Object> noticeMap=  JsonKit.json2map(noticeresult);
		  lMaps.add(noticeMap);
		  //执行人查询
		  String doe=Constants.URL_STRING+"v2/execution/getExecutedpersonListByName?appkey="+Constants.appkey+"&skip=0&keyword="+keyword;
		  String doeresult=	HttpClientUtil.sendGetRequest(doe, "UTF-8");
		  Map<String,Object> doeMap=  JsonKit.json2map(doeresult);
		  lMaps.add(doeMap);
		  mRenderJson(lMaps);
		  
	}

	/**
	 * 获取企业经营信息
	 * @author lixiaoyi
	 * @date 2018年4月9日
	 * @TODO
	 */
	public void getManage(){
		 List<Map<String,Object>>   lMaps=  new ArrayList<Map<String,Object>>();
		 String keyword= getPara("keyword");
		  if (keyword==null||"".equals(keyword)) {
		     keyword="小米科技";
		   }
		 //获取企业经营异常
		 String manage=Constants.URL_STRING+"v2/abnormal/getAbnormalListByName?appkey="+Constants.appkey+"&skip=0&keyword="+keyword;
		 String manageresult=	HttpClientUtil.sendGetRequest(manage, "UTF-8");
		 Map<String,Object> manageMap=  JsonKit.json2map(manageresult);
		 lMaps.add(manageMap);
		 //获取行政处罚
		 String admin=Constants.URL_STRING+"v2/adminPunish/getAdminPunishByName?appkey="+Constants.appkey+"&skip=0&keyword="+keyword;
		 String adminresult=	HttpClientUtil.sendGetRequest(admin, "UTF-8");
		 Map<String,Object> adminMap=  JsonKit.json2map(adminresult);
		 lMaps.add(adminMap);
		 mRenderJson(lMaps);
	}
	
	/**
	 * 获取知识产权内容
	 * @author lixiaoyi
	 * @date 2018年4月9日
	 * @TODO
	 */
	public void getKnowledge(){
		 List<Map<String,Object>>   lMaps=  new ArrayList<Map<String,Object>>();
		 String keyword= getPara("keyword");
		  if (keyword==null||"".equals(keyword)) {
		     keyword="小米科技";
		   }
		  //获取专利信息查询
		  String konwledge=Constants.URL_STRING+"patent/getPatentListByName?appkey="+Constants.appkey+"&skip=0&keyword="+keyword;
		  String konwresult=	HttpClientUtil.sendGetRequest(konwledge, "UTF-8");
		  Map<String,Object> knowMap=  JsonKit.json2map(konwresult);
		  lMaps.add(knowMap);
		  //获取商标信息
		  String trademark=Constants.URL_STRING+"trademark/getTrademarkByName?appkey="+Constants.appkey+"&skip=0&keyword="+keyword;
		  String traderesult=	HttpClientUtil.sendGetRequest(trademark, "UTF-8");
		  Map<String,Object> tradeMap=  JsonKit.json2map(traderesult);
		  lMaps.add(tradeMap);
		  //获取著作权
		  String copy=Constants.URL_STRING+"copyright/getCopyrightByName?appkey="+Constants.appkey+"&skip=0&keyword="+keyword;
		  String copyresult=	HttpClientUtil.sendGetRequest(copy, "UTF-8");
		  Map<String,Object> copyMap=  JsonKit.json2map(copyresult);
		  lMaps.add(copyMap);
		  //获取软件著作权
		  String soft=Constants.URL_STRING+"copyright/getCopyrightSoftByName?appkey="+Constants.appkey+"&skip=0&keyword="+keyword;
		  String softresult=	HttpClientUtil.sendGetRequest(soft, "UTF-8");
		  Map<String,Object> softMap=  JsonKit.json2map(softresult);
		  lMaps.add(softMap);
		  
		  
	}
}
