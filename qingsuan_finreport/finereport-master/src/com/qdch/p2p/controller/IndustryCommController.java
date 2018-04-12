package com.qdch.p2p.controller;

import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.p2p.model.CoBusinessTypeModel;
import com.qdch.p2p.model.CoCompanyBrachModel;
import com.qdch.p2p.model.CoChangeModel;
import com.qdch.p2p.model.CoCompanyInfoModel;
import com.qdch.p2p.model.CoCompanyTypeModel;
import com.qdch.p2p.model.CoCorePersonModel;
import com.qdch.p2p.model.CoExecptionModel;
import com.qdch.p2p.model.CoShareholderInfoModel;
/**
 * p2p 工商静态内容 
 * @author lixiaoyi
 * @date 2018年4月12日上午11:34:41
 * @TODO
 */
public class IndustryCommController extends BaseController {

	/**
	 * 获取公司基本信息
	 * @author lixiaoyi
	 * @date 2018年4月12日 下午1:20:25
	 * @TODO
	 */
	public void gainInfo(){
		String name=getPara("name");
		CoCompanyInfoModel company = CoCompanyInfoModel.dao.getInfo(name);
		mRenderJson(company);
	}
	/**
	 * 获取公司属性
	 * @author lixiaoyi
	 * @date 2018年4月12日 下午2:01:57
	 * @TODO
	 */
	public  void gainType(){
		String name=getPara("name");
	    CoCompanyTypeModel type = CoCompanyTypeModel.dao.getType(name);
	    mRenderJson(type);
	}
	/**
	 * 获取工商主要人员
	 * @author lixiaoyi
	 * @date 2018年4月12日 下午2:32:57
	 * @TODO
	 */
	public void gainCore(){
		String name=getPara("name");
		List<CoCorePersonModel> corePerson = CoCorePersonModel.dao.getMainperson(name);
	    mRenderJson(corePerson);
	}
	/**
	 * 获取工商变更记录
	 * @author lixiaoyi
	 * @date 2018年4月12日 下午2:53:33
	 * @TODO
	 */
	public void gainChange(){
		String name=getPara("name");
		List<CoChangeModel> change = CoChangeModel.dao.getChange(name);
		mRenderJson(change);
	}
	/**
	 * 获取分支机构
	 * @author lixiaoyi
	 * @date 2018年4月12日 下午3:03:19
	 * @TODO
	 */
	public void gainBrach(){
		String name=getPara("name");
		List<CoCompanyBrachModel> brachs=   CoCompanyBrachModel.dao.getBrach(name);
	    mRenderJson(brachs);
	}
	/**
	 * 经营异常
	 * @author lixiaoyi
	 * @date 2018年4月12日 下午3:15:40
	 * @TODO
	 */
	public void gainExecption(){
    String name=getPara("name");
	List<CoExecptionModel> execption =	CoExecptionModel.dao.getExecption(name);
	mRenderJson(execption);
	}
	/**
	 * 股东信息
	 * @author lixiaoyi
	 * @date 2018年4月12日 下午3:26:51
	 * @TODO
	 */
	public void gainShareholder(){
		String name=getPara("name");
		List<CoShareholderInfoModel> shareholder = CoShareholderInfoModel.dao.getShareholder(name);
	    mRenderJson(shareholder);
	}
	/**
	 * 行业分类
	 * @author lixiaoyi
	 * @date 2018年4月12日 下午3:37:22
	 * @TODO
	 */
	public void gainBusinesstype(){
		 String name=getPara("name");
		 CoBusinessTypeModel business = CoBusinessTypeModel.dao.getBusinessType(name);
	     mRenderJson(business);
	}
}
