package com.qdch.xd.controller;


import com.alibaba.fastjson.JSONArray;
import com.fr.hailian.core.QdchUser;
import com.fr.hailian.model.RoleModel;
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
        QdchUser user=getLoginUser();

		String checkstatus = ""; // 处理状态
		List<String> statuss = new ArrayList<>();
		List<RoleModel> roles = getLoginUser().getRoles();
		String rolename = "";
		if(roles!=null && roles.size()>0) {

			List<String> rr = new ArrayList<>();
			for (RoleModel roleModel : roles) {
				if (roleModel.getRoleName().contains("处理人")) {
					rolename = "处理人";
					statuss.add("已提交");
					statuss.add("已排除");
					checkstatus = " and clzt in ('未处理','驳回') ";
					break;
				} else if (roleModel.getRoleName().contains("审核人")) {
					rolename = "审核人";
					checkstatus = " and clzt in ('已提交') ";
					statuss.add("已上报");
					statuss.add("驳回");
					statuss.add("已排查");
					break;
				} else if (roleModel.getRoleName().contains("决策人")) {
					rolename = "决策人";
					checkstatus = " and clzt in ('已上报') ";
					statuss.add("已查阅");
					statuss.add("已确认");
					break;
				}

			}
		}
		setAttr("rolename",rolename);
		setAttr("alstatus",checkstatus);
		setAttr("status",statuss);
		setAttr("exchange",OrganizModel.dao.getListByType("3"));  //机构/市场)
		render("xd/pages/05_05fengxianshijiantianbao.html");
	}


	public void saveEvent(){
		RiskEventModel riskEventModel  = new RiskEventModel();

		StringBuffer sb = new StringBuffer();
		sb.append("insert into hub_fxsj(jysfl,bjsj,fxlb,fxzb,fxzbz,yuzhi,cce,jgmc,jgdm,khmc,cust_id,ywlx,ywbm," +
				"ywcdmc,ywcdbm,clzt,fxsm,bz) values");
		try {

			String length  =  getPara("length"); //总共有几行
			String[] pbjsj = decode(getPara("pbjsj")).split(",");
			String[] ptypes = decode(getPara("ptype")).split(",");
			String[] prisks = decode(getPara("prisk")).split(",");
			String[] priskvalues = decode(getPara("priskvalue")).split(",");
			String[] pyuzhis = decode(getPara("pyuzhi")).split(",");
			String[] pchaochus = decode(getPara("pchaochu")).split(",");
			String[] porgnnames = decode(getPara("porgnname")).split(",");
			String[] porgncodes = decode(getPara("porgncode")).split(",");
			String[] pcusnames = decode(getPara("pcusname")).split(",");
			String[] pcuscodes = decode(getPara("pcuscode")).split(",");
			String[] pbusnames = decode(getPara("pbusname")).split(",");
			String[] pbuscodes = decode(getPara("pbuscode")).split(",");
			String[] pmenunames = decode(getPara("pmenuname")).split(",");
			String[] pmenucodes = decode(getPara("pmenucode")).split(",");
			String[] pstatuses = decode(getPara("pstatus")).split(",");
			String[] pintros = decode(getPara("pintro")).split(",");
			String[] premarks = decode(getPara("premarks")).split(",");



			for(int i=0;i<Integer.parseInt(length);i++){
				sb.append("('");
				sb.append("3','"); //交易所分类 3为小贷
				sb.append(pbjsj[i]).append("','");
				sb.append(ptypes[i]).append("','");
				sb.append(prisks[i]).append("','");
				sb.append(priskvalues[i]).append("','");
				sb.append(pyuzhis[i]).append("','");
				sb.append(pchaochus[i]).append("','");
				sb.append(porgnnames[i]).append("','");
				sb.append(porgncodes[i]).append("','");
				sb.append(pcusnames[i]).append("','");
				sb.append(pcuscodes[i]).append("','");
				sb.append(pbusnames[i]).append("','");
				sb.append(pbuscodes[i]).append("','");
				sb.append(pmenunames[i]).append("','");
				sb.append(pmenucodes[i]).append("','");
				sb.append(pstatuses[i]).append("','");
				sb.append(pintros[i]).append("','");
				sb.append(premarks[i]).append("'),");

			}
			String sql = sb.toString().substring(0,sb.toString().length()-1);
			System.out.println(sql);

//			sb.append()

			JDBCUtil.executeUpdate(sql,null);

//			riskEventModel.dao.save();
			mRenderJson(true);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	public void getRisk(){
		String checkstatus = ""; // 处理状态
		List<String> statuss = new ArrayList<>();
		List<RoleModel> roles = getLoginUser().getRoles();
		String rolename = "";
		if(roles!=null && roles.size()>0) {

			List<String> rr = new ArrayList<>();
			for (RoleModel roleModel : roles) {
				if (roleModel.getRoleName().contains("处理人")) {
					rolename = "处理人";
					statuss.add("已提交");
					statuss.add("已排除");
					checkstatus = " and clzt in ('未处理','驳回') ";
					break;
				} else if (roleModel.getRoleName().contains("审核人")) {
					rolename = "审核人";
					checkstatus = " and clzt in ('已提交') ";
					statuss.add("已上报");
					statuss.add("驳回");
					statuss.add("已排查");
					break;
				} else if (roleModel.getRoleName().contains("决策人")) {
					rolename = "决策人";
					checkstatus = " and clzt in ('已上报') ";
					statuss.add("已查阅");
					statuss.add("已确认");
					break;
				}

			}
		}
		Map<String,Object> result = new HashMap<String, Object>();
		List<RiskTypeModel> riskTypeList =  riskTypeModelDao.getTypeKind("3"); //风险类别
//		setAttr("type",riskTypeList);
		result.put("yuzhi",ThresholdValueModel.dao.getInfoList("3"));
		result.put("type",riskTypeList);
		result.put("status",statuss);
		result.put("risk",riskTypeModelDao.getByType("3"));
//		result.put("exchange",	ExchangeInfoModel.dao.getList()); //机构/市场)
		result.put("exchange",	OrganizModel.dao.getListByType("3")); //机构/市场)
		mRenderJson(result);
	}
	private String decode(String str) throws UnsupportedEncodingException {
		if(StringUtils.isNotBlank(str)){
			return URLDecoder.decode(str,"UTF-8");
		}else
			return  str;

	}




}
