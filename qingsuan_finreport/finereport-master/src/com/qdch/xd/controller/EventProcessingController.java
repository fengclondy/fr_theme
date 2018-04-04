package com.qdch.xd.controller;

import com.fr.stable.StringUtils;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.qdch.core.BaseController;
import com.qdch.util.ExportUtil;
import com.qdch.xd.model.ExchangeInfoModel;
import com.qdch.xd.model.IndexRankingModel;
import com.qdch.xd.model.RiskEventModel;
import com.qdch.xd.model.RiskTypeModel;


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
	/**
	 * 
	* @author doushuihai  
	* @date 2018年3月30日下午1:08:45  
	* @TODO 风险事件页面
	 */
	public void index() {
//		renderJsp("xd/pages/riskSolve.jsp");
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
				"30":getPara("pageSize"));
//		getPara(getRequest());
		getResponse().setCharacterEncoding("UTF-8");
		Page<RiskEventModel> page = riskEventModelDao.getRiskEvent(getDataScopeByUserName(),pageNum,pageSize,getRequest());
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
//			ExportUtil.ExportNoResponse(sheetName, titleName, fileName,
//                    columnNumber, columnWidth, columnName, dataList);
//			<th>风险事件ID</th>
//                <th>报警时间</th>
//                <th>风险类别</th>
//                <th>风险指标</th>
//                <th>风险指标值</th>
//                <th>阙值</th>
//                <th>超出额</th>
//                <th>市场代码</th>
//                <th>市场名称</th>
//                <th>客户号</th>
//                <th>客户名称</th>
//                <th>业务菜单编码</th>
//                <th>业务菜单名称</th>
//                <th>业务类型</th>
//                <th>业务编码</th>
//                <th>处理人</th>
//                <th>处理状态</th>
//                <th>处理时间</th>
//                <th>风险说明</th>
			String[] tablename = new String[]{"风险事件ID","报警时间","风险类别","风险指标值"};
//			List<String> tablelist = new ArrayList<>();
//			tablelist.add("风险事件id");

            List<RiskEventModel> riskEventModels = riskEventModelDao.getRiskEventList(getRequest());

            String[][] content = new String[riskEventModels.size()][];
            List<String> list = new ArrayList<String>();
//            for(RiskEventModel model:riskEventModels){
//                List<String> list1 = new ArrayList<String>();
//                list1.add(model.getStr("fxsj_id"));
//                list1.add(model.getStr("bjsj"));
//            }

            for (int i = 0; i < riskEventModels.size(); i++) {
                content[i] = new String[tablename.length];
                RiskEventModel obj = riskEventModels.get(i);
                content[i][0] = obj.get("bjsj").toString();
                content[i][1] = obj.get("bjsj").toString();
                content[i][2] = obj.get("fxlb").toString();
                content[i][3] = obj.get("fxzb").toString();
//                content[i][4] = obj.get("stuClassName").toString();
            }



            ExportUtil.toexcel(tablename,content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mRenderJson(null);
	}





}
