package com.qdch.p2p.controller;

import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.p2p.model.CoBusinessTypeModel;
import com.qdch.p2p.model.CoBzxpersonModel;
import com.qdch.p2p.model.CoChangeModel;
import com.qdch.p2p.model.CoComPatentModel;
import com.qdch.p2p.model.CoComReportModel;
import com.qdch.p2p.model.CoComcopyrightModel;
import com.qdch.p2p.model.CoComjobModel;
import com.qdch.p2p.model.CoCompanyBrachModel;
import com.qdch.p2p.model.CoCompanyInfoModel;
import com.qdch.p2p.model.CoCompanyTypeModel;
import com.qdch.p2p.model.CoCorePersonModel;
import com.qdch.p2p.model.CoDishonestModel;
import com.qdch.p2p.model.CoExecptionModel;
import com.qdch.p2p.model.CoLegalModel;
import com.qdch.p2p.model.CoLegalPublicModel;
import com.qdch.p2p.model.CoShareholderInfoModel;
import com.qdch.p2p.model.CoSoftrightModel;
import com.qdch.p2p.model.CoTrademarkModel;
import com.qdch.xd.model.CompanysInfoModel;
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
		 List<CompanysInfoModel> basic = CompanysInfoModel.dao.getBasicinfo(getDataScopeByUserName(),name);
	     setAttr("basic", basic.get(0));
		CoCompanyInfoModel company = CoCompanyInfoModel.dao.getInfo(name);
		 setAttr("company", company);
		CoCompanyTypeModel type = CoCompanyTypeModel.dao.getType(name);
		 setAttr("type", type);
		CoShareholderInfoModel holder = CoShareholderInfoModel.dao.getHolder(name);
		 setAttr("hold",holder );

		List<CoCorePersonModel> corePerson = CoCorePersonModel.dao.getMainperson(name);
          setAttr("person", corePerson);
  		List<CoCorePersonModel> manager = CoCorePersonModel.dao.getManger(name);
          setAttr("manager", manager);
		List<CoCorePersonModel> dongshi = CoCorePersonModel.dao.getDongshi(name);
          setAttr("dongshi", dongshi);
		List<CoCorePersonModel> jianshi = CoCorePersonModel.dao.getJianshi(name);
          setAttr("jianshi", jianshi);
        List<CoChangeModel> change = CoChangeModel.dao.getChange(name);
          setAttr("change", change);
        List<CoCompanyBrachModel> brachs=   CoCompanyBrachModel.dao.getBrach(name);  
          setAttr("brach", brachs);
        List<CoExecptionModel> execption =	CoExecptionModel.dao.getExecption(name);
           setAttr("business", execption);
       	List<CoShareholderInfoModel> shareholder = CoShareholderInfoModel.dao.getShareholder(name);
       	   setAttr("holder", shareholder);
        List<CoShareholderInfoModel> invest = CoShareholderInfoModel.dao.getInvers(name);
           setAttr("invest", invest);
  		CoBusinessTypeModel business = CoBusinessTypeModel.dao.getBusinessType(name);
  		   setAttr("businessType",business);
        List<CoLegalModel> legalModels= CoLegalModel.dao.getLegal(name);
		   setAttr("legal", legalModels); 
		List<CoLegalPublicModel> publicc=  CoLegalPublicModel.dao.getLegalPublic(name);   
		    setAttr("public", publicc);
	    List<CoDishonestModel> dishonestModels =CoDishonestModel.dao.getDishonest(name);    
		    setAttr("dishonest", dishonestModels);
		List<CoBzxpersonModel> perosn=     CoBzxpersonModel.dao.getPerson(name);
		    setAttr("person", perosn);
		List<CoTrademarkModel> mark=     CoTrademarkModel.dao.getTrademark(name);
		    setAttr("mark", mark);
		List<CoComReportModel>reportModels=CoComReportModel.dao.getReport(name);
		    setAttr("report", reportModels);
	    List<CoComPatentModel> patentModels=	CoComPatentModel.dao.getPatent(name);
		    setAttr("patent", patentModels);
	    List<CoComcopyrightModel> copyright= CoComcopyrightModel.dao.getCopyrigt(name);
		    setAttr("copy", copyright);
	    List<CoSoftrightModel>soft= CoSoftrightModel.dao.getSoft(name);   
		     setAttr("soft", soft);
		List<CoComjobModel> job= CoComjobModel.dao.getJob(name);
		     setAttr("job", job);
		render("p2p/pages/04_01guanlianqiyeInfor.html");

	}

}
