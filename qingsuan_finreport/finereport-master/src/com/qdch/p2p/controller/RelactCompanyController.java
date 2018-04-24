
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
		 if (company!=null) {
			 List<CoCompanyInfoModel> comInfo= CoCompanyInfoModel.dao.getRe(company.get("legal_person")+"");
		     setAttr("comInfo", comInfo);
		 }
		  
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
          setAttr("core",CoCorePersonModel.dao.getMainperson(name).size()+CoCorePersonModel.dao.getManger(name).size()
        		  +CoCorePersonModel.dao.getDongshi(name).size()+CoCorePersonModel.dao.getJianshi(name).size());
        List<CoChangeModel> change = CoChangeModel.dao.getChange(name);
          setAttr("change", change);
          setAttr("chanegsiez", change.size());
        List<CoChangeModel> nameChange =   CoChangeModel.dao.getName(name);
          setAttr("namechange", nameChange);
        List<CoChangeModel> stocks =  CoChangeModel.dao.getStock(name);
          setAttr("stock", stocks);
        List<CoChangeModel> lian= CoChangeModel.dao.getliain(name);
          setAttr("lian", lian);
          setAttr("liansize", lian.size());
         List<CoChangeModel> businesscope= CoChangeModel.dao.getBusiness(name);
           setAttr("busi", businesscope);
        List<CoChangeModel> comtype=   CoChangeModel.dao.getType(name);
           setAttr("comtype", comtype);
           setAttr("typesize", comtype.size());
        List<CoChangeModel> regist=   CoChangeModel.dao.getRegist(name);
           setAttr("regist", regist);
           setAttr("registSize", regist.size());
        List<CoCompanyBrachModel> brachs=   CoCompanyBrachModel.dao.getBrach(name);  
          setAttr("brach", brachs);
          setAttr("brachsize", brachs.size());
        List<CoExecptionModel> execption =	CoExecptionModel.dao.getExecption(name);
           setAttr("business", execption);
           setAttr("businessSize", execption.size());
       	List<CoShareholderInfoModel> shareholder = CoShareholderInfoModel.dao.getShareholder(name);
       	   setAttr("holder", shareholder);
       	   setAttr("holdsize", shareholder.size());
        List<CoShareholderInfoModel> invest = CoShareholderInfoModel.dao.getInvers(name);
           setAttr("invest", invest);
           setAttr("investSize", invest.size());
  		CoBusinessTypeModel business = CoBusinessTypeModel.dao.getBusinessType(name);
  		   setAttr("businessType",business);
        List<CoLegalModel> legalModels= CoLegalModel.dao.getLegal(name);
		   setAttr("legal", legalModels); 
		   setAttr("legalsize", legalModels.size());
		List<CoLegalPublicModel> publicc=  CoLegalPublicModel.dao.getLegalPublic(name);   
		    setAttr("public", publicc);
		    setAttr("publicsize", publicc.size());
	    List<CoDishonestModel> dishonestModels =CoDishonestModel.dao.getDishonest(name);    
		    setAttr("dishonest", dishonestModels);
		    setAttr("dishonestsize", CoDishonestModel.dao.getDissize(name).size());
		List<CoBzxpersonModel> perosn=     CoBzxpersonModel.dao.getPerson(name);
		    setAttr("bzxperson", perosn);
		    setAttr("bzxsize", perosn.size());
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
		     setAttr("jobsize", job.size());
	    setAttr("all", CoCorePersonModel.dao.getMainperson(name).size()+CoCorePersonModel.dao.getManger(name).size()
        		  +CoCorePersonModel.dao.getDongshi(name).size()+CoCorePersonModel.dao.getJianshi(name).size()
        		  +change.size()+lian.size()+shareholder.size()+invest.size()+brachs.size()+regist.size()+comtype.size());
	    setAttr("allsize", legalModels.size()+publicc.size()+execption.size()+job.size()+perosn.size()+dishonestModels.size());
		render("p2p/pages/04_01guanlianqiyeInfor.html");

	}

}
