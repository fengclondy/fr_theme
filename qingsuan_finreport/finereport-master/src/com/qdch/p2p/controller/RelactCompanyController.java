package com.qdch.p2p.controller;

import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.p2p.model.CoBusinessTypeModel;
import com.qdch.p2p.model.CoChangeModel;
import com.qdch.p2p.model.CoCompanyBrachModel;
import com.qdch.p2p.model.CoCompanyInfoModel;
import com.qdch.p2p.model.CoCompanyTypeModel;
import com.qdch.p2p.model.CoCorePersonModel;
import com.qdch.p2p.model.CoExecptionModel;
import com.qdch.p2p.model.CoShareholderInfoModel;
/**
 * 关联企业
 * @author lixiaoyi
 * @date 2018年4月19日上午10:01:04
 * @TODO
 */
public class RelactCompanyController extends BaseController{
	
	/**
	 * 关联企业信息首页
	 * @author lixiaoyi
	 * @date 2018年4月19日 上午10:01:50
	 * @TODO
	 */
	public  void index(){
		String name=getPara("name");
		CoCompanyInfoModel company = CoCompanyInfoModel.dao.getInfo(name);
		 setAttr("company", company);
		CoCompanyTypeModel type = CoCompanyTypeModel.dao.getType(name);
		 setAttr("type", type);
		List<CoCorePersonModel> corePerson = CoCorePersonModel.dao.getMainperson(name);
          setAttr("person", corePerson);
        List<CoChangeModel> change = CoChangeModel.dao.getChange(name);
          setAttr("change", change);
        List<CoCompanyBrachModel> brachs=   CoCompanyBrachModel.dao.getBrach(name);  
          setAttr("brach", brachs);
        List<CoExecptionModel> execption =	CoExecptionModel.dao.getExecption(name);
           setAttr("business", execption);
       	List<CoShareholderInfoModel> shareholder = CoShareholderInfoModel.dao.getShareholder(name);
       	   setAttr("holder", shareholder);
  		CoBusinessTypeModel business = CoBusinessTypeModel.dao.getBusinessType(name);
  		   setAttr("businessType",business);

		 
		 render("p2p/pages/04_01guanlianqiyeInfor.html");

	}

}
