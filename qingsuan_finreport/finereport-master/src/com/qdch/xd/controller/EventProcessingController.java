package com.qdch.xd.controller;

import com.fr.stable.StringUtils;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.qdch.core.BaseController;
import com.qdch.util.ExportUtil;
import com.qdch.xd.model.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
* @author doushuihai  
* @date 2018年3月30日下午1:07:26  
* @TODO 风险管理-风险事件处理
 */
public class EventProcessingController extends BaseController {
	private static RiskEventModel riskEventModelDao = RiskEventModel.dao;
	private static ExchangeInfoModel exchangeInfoModelDao = ExchangeInfoModel.dao;
	private static RiskTypeModel riskTypeModelDao = RiskTypeModel.dao;
	private static RiskEventHistoryModel riskEventHistoryModel  = RiskEventHistoryModel.dao;

	/**
	 * 
	* @author doushuihai  
	* @date 2018年3月30日下午1:08:45  
	* @TODO 风险事件页面
	 */
	public void index() {
//		renderJsp("xd/pages/riskSolve.jsp");
		//setAttr("type",getPara("type"));
		render("xd/pages/05_01fengxianshijianchuli.html");
	}

    /**
     *  交易所信息 /市场名称
     */
//	public void getExchageList(){
//	    List<ExchangeInfoModel> list = exchangeInfoModelDao.getList();
//        mRenderJson(list);
//
//    }
//	public
	public void getList(){
		int pageNum =Integer.parseInt(StringUtils.isBlank(getPara("pageNum"))||
				getPara("pageNum").equals("undefined")==true?
				"1":getPara("pageNum"));
		int pageSize =Integer.parseInt(StringUtils.isBlank(getPara("pageSize"))||
				getPara("pageSize").equals("undefined")
				==true?
				"10":getPara("pageSize"));
//		getPara(getRequest());
		getResponse().setCharacterEncoding("UTF-8");
		String checkstatus = ""; // 处理状态
		String role = "";
		if(role.equals("处理人")){
			checkstatus = " and clzt in (\"未处理\",\"驳回\",\"已排除\") ";
		}else if(role.equals("审核人")){
			checkstatus = " and clzt in (\"已提交\",\"已排查\") ";
		}else if(role.equals("决策人")){
			checkstatus = " and clzt in (\"已上报\",\"已查阅\"\"已确认\") ";
		}
		Page<RiskEventModel> page = riskEventModelDao.getRiskEvent(checkstatus,pageNum,pageSize,getRequest());
		mRenderJson(page);
	}

//	public void getRiskType(){
//		mRenderJson(riskTypeModelDao.getByType("3")); //3--小贷 4--p2p
//	}

	/**
	 * 下拉框
	 */
	public void  getPullDown(){
		Map<String,Object> result  = new HashMap<String, Object>();
		List<RiskTypeModel> riskTypeList =  riskTypeModelDao.getTypeKind("3"); //风险类别
		List<RiskTypeModel> riskList =  riskTypeModelDao.getByType("3");
		List<ExchangeInfoModel> exchangeInfoModelList = exchangeInfoModelDao.getList(); //机构/市场
		result.put("type",riskTypeList);
		result.put("risk",riskList);
		result.put("exchange",exchangeInfoModelList);
		mRenderJson(result);
	}

	public void exportExcel(){

		try {
			String[] tablename = new String[]{"风险事件ID","报警时间","风险类别","风险指标","风险指标值","阙值","超出额",
            "市场代码","市场名称","客户号","客户名称","业务菜单编码","业务菜单名称","业务类型","业务编码",
            "处理人","处理状态","处理时间"};
            List<RiskEventModel> riskEventModels = riskEventModelDao.getRiskEventList("",getRequest());
            String[][] content = new String[riskEventModels.size()][];
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < riskEventModels.size(); i++) {
                content[i] = new String[tablename.length];
                RiskEventModel obj = riskEventModels.get(i);
                content[i][0] = toString(obj.get("fxsj_id"));
                content[i][1] = toString(obj.get("bjsj"));
                content[i][2] = toString(obj.get("fxlb"));
                content[i][3] = toString(obj.get("fxzb"));
                content[i][4] = toString(obj.get("fxzbz"));
                content[i][5] = toString(obj.get("yuzhi"));
                content[i][6] = toString(obj.get("cce"));
                content[i][7] = toString(obj.get("jgdm"));
                content[i][8] = toString(obj.get("jgmc"));
                content[i][9] = toString(obj.get("cust_id"));
                content[i][10] = toString(obj.get("khmc"));
                content[i][11] =toString(obj.get("ywcdbm"));
                content[i][12] = toString(obj.get("ywcdmc"));
                content[i][13] = toString(obj.get("ywlx"));
                content[i][14] = toString(obj.get("ywbm"));
                content[i][15] = toString(obj.get("shr"));
                content[i][16] = toString(obj.get("clzt"));
                content[i][17] = toString(obj.get("update_time"));

            }



            ExportUtil.toexcel(tablename,content,getResponse(),"风险事件");
		} catch (Exception e) {
			e.printStackTrace();
		}
		mRenderJson(null);
	}

	public String toString (Object object){
	    if(object==null){
	        return  "";
        }else{
	        return object.toString();
        }
    }

    public void submitCheck(){
		riskEventHistoryModel.save();
		mRenderJson(null);
	}


}
