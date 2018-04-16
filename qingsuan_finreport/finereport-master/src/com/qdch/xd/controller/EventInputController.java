package com.qdch.xd.controller;


import com.alibaba.fastjson.JSONArray;
import com.fr.hailian.util.JDBCUtil;
import com.fr.stable.StringUtils;
import com.fr.web.core.A.O;
import com.jfinal.plugin.activerecord.Page;
import com.qdch.core.BaseController;
import com.qdch.util.ExportUtil;
import com.qdch.xd.model.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * 
* @author wf
* @date 2018年4月10日12:38:59
* @TODO 风险管理-风险事件填报
 */
public class EventInputController extends BaseController {

	private static  RiskTypeModel riskTypeModelDao = RiskTypeModel.dao;
	private static DictModel dictModelDao = DictModel.dao;

	public void index(){
		List<RiskTypeModel> riskTypeList =  riskTypeModelDao.getTypeKind("3"); //风险类别
		setAttr("type",riskTypeList);
		setAttr("status",dictModelDao.getLabel(getPara("note")));
		setAttr("exchange",ExchangeInfoModel.dao.getList());  //机构/市场)
		render("xd/pages/05_05fengxianshijiantianbao.html");
	}


	public void saveEvent(){
		RiskEventModel riskEventModel  = new RiskEventModel();

		StringBuffer sb = new StringBuffer();
		sb.append("insert into hub_fxsj(fxlb,fxzb,fxzbz,yuzhi) values");
		try {
			String length  =  getPara("length"); //总共有几行
			String[] types = decode(getPara("ptype")).split(",");
			String[] risks = decode(getPara("prisk")).split(",");
			String[] riskvalues = decode(getPara("priskvalue")).split(",");
			String[] yuzhis = decode(getPara("priskvalue")).split(",");
			for(int i=0;i<Integer.parseInt(length);i++){
				sb.append("('");
				sb.append(types[i]).append("','");
				sb.append(risks[i]).append("','");
//				sb.append(riskvalues[i]).append(",");
//				sb.append(yuzhis[i]).append("),");


			}
			String sql = sb.toString().substring(0,sb.toString().length()-1);
			System.out.println(sql);

//			sb.append()

//			JDBCUtil.executeUpdate(sb.toString(),null);

//			riskEventModel.dao.save();
			mRenderJson(true);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	public void getRisk(){
		Map<String,Object> result = new HashMap<String, Object>();
		List<RiskTypeModel> riskTypeList =  riskTypeModelDao.getTypeKind("3"); //风险类别
//		setAttr("type",riskTypeList);
		result.put("yuzhi",ThresholdValueModel.dao.getInfoList("3"));
		result.put("type",riskTypeList);
		result.put("status",dictModelDao.getLabel(getPara("note")));
		result.put("risk",riskTypeModelDao.getByType("3"));
		result.put("exchange",	ExchangeInfoModel.dao.getList()); //机构/市场)
		mRenderJson(result);
	}
	private String decode(String str) throws UnsupportedEncodingException {
		if(StringUtils.isNotBlank(str)){
			return URLDecoder.decode(str,"UTF-8");
		}else
			return  str;

	}




}
