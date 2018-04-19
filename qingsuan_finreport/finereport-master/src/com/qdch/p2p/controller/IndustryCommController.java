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
import com.qdch.xd.model.CoAnnounceModel;
import com.qdch.xd.model.CoBranchModel;
import com.qdch.xd.model.CoBusinessModel;
import com.qdch.xd.model.CoChangeLogModel;
import com.qdch.xd.model.CoCopyrightModel;
import com.qdch.xd.model.CoDishonestyModel;
import com.qdch.xd.model.CoEnterpriseModel;
import com.qdch.xd.model.CoExecutorModel;
import com.qdch.xd.model.CoJobModel;
import com.qdch.xd.model.CoJudgmentModel;
import com.qdch.xd.model.CoMainPersonModel;
import com.qdch.xd.model.CoPatentModel;
import com.qdch.xd.model.CoPenaltvModel;
import com.qdch.xd.model.CoReportModel;
import com.qdch.xd.model.CoShareHolderModel;
import com.qdch.xd.model.CoSoftcopyModel;
import com.qdch.xd.model.CoTradeMarkModel;
import com.qdch.xd.model.CoWebsiteModel;
import com.qdch.xd.model.CompanysInfoModel;
import com.qdch.xd.model.MarkNewsModel;
/**
 * p2p 工商静态内容 
 * @author lixiaoyi
 * @date 2018年4月12日上午11:34:41
 * @TODO
 */
public class IndustryCommController extends BaseController {
	
	 public void index(){
			String name=getPara("name");
			if (name==null||"".equals(name)) {
				name="青岛国际版权交易中心有限公司";
			}
			setAttr("company", name);
		    List<CompanysInfoModel> info = CompanysInfoModel.dao.getInfo(getDataScopeByUserName(),name);
		    setAttr("companyName", info);
		    List<CompanysInfoModel> basic = CompanysInfoModel.dao.getBasicinfo(getDataScopeByUserName(),name);
		     setAttr("basic", basic);
		    List<CoShareHolderModel> main = CoShareHolderModel.dao.getMainperson(name);
			 setAttr("main", main);
		    List<CoShareHolderModel> manager = CoShareHolderModel.dao.getManager(name);
		      setAttr("manager", manager);
			List<CoShareHolderModel> dierctor = CoShareHolderModel.dao.getDong(name);
			  setAttr("director", dierctor);
			List<CoShareHolderModel> supervisor = CoShareHolderModel.dao.getJian(name);
			  setAttr("supervisor", supervisor); 
			  setAttr("core",CoShareHolderModel.dao.getMainSize(name).size()+CoShareHolderModel.dao.getManagerSize(name).size()
					  +CoShareHolderModel.dao.getDirectSize(name).size()+CoShareHolderModel.dao.getSuperSize(name).size());
			List<CoShareHolderModel> stackInfo = CoShareHolderModel.dao.getStackInfo(name);
			  setAttr("stackInfo", stackInfo);
			  setAttr("stacksize", stackInfo.size());
			List<CoShareHolderModel> invest = CoShareHolderModel.dao.getInvest(name);
			  setAttr("invest", invest);  
			  setAttr("investSize", invest.size());
			List<CoChangeLogModel> changeLog = CoChangeLogModel.dao.getChange(name);
			  setAttr("change", changeLog);
			  setAttr("changeLog", changeLog.size()+"");
			List<CoReportModel> report = CoReportModel.dao.getReport(name); 
			  setAttr("report", report);
			List<CoBranchModel> branch = CoBranchModel.dao.getBranch(name);  
			  setAttr("brach", branch);
			  setAttr("brachsize", branch.size());
			List<CoChangeLogModel> nameChange = CoChangeLogModel.dao.getNamechange(name);
			  setAttr("nameChange", nameChange);
			List<CoChangeLogModel> investChange =  CoChangeLogModel.dao.getInvestchange(name);  
			  setAttr("investChange", investChange);
			List<CoChangeLogModel> liaison = CoChangeLogModel.dao.getLiaisonchange(name);
			  setAttr("liaison", liaison);
			  setAttr("liaiSize", liaison.size());
			List<CoChangeLogModel> business = CoChangeLogModel.dao.getBuinesschange(name);
	          setAttr("business", business);
	        List<CoChangeLogModel> companyType = CoChangeLogModel.dao.getComtypechange(name);
	          setAttr("com", companyType);
	          setAttr("typesize", companyType.size());
	        List<CoChangeLogModel> register  = CoChangeLogModel.dao.getRegisterchange(name);
	          setAttr("register", register);
	          setAttr("registSize", register.size());
	        List<CoJudgmentModel> judgment = CoJudgmentModel.dao.getJudgment(name);
	          setAttr("judgment", judgment);  
	          setAttr("judSize", judgment.size());
	        List<CoAnnounceModel> announce = CoAnnounceModel.dao.getAnnounce(name);
	          setAttr("announce", announce);
	          setAttr("annSize", announce.size());
	        CoDishonestyModel dishonesty = CoDishonestyModel.dao.getDishonesty(name); 
			   setAttr("dishonesty", dishonesty);
			List<CoExecutorModel> exector = CoExecutorModel.dao.getExecutor(name);
			   setAttr("exector", exector);
			   setAttr("exeSize", exector.size());
			List<CoBusinessModel> businesExeption = CoBusinessModel.dao.getBusiness(name);
			   setAttr("buExecption", businesExeption);
			   setAttr("businesize", businesExeption.size());
			List<CoPenaltvModel> penaltv = CoPenaltvModel.dao.getPenalt(name); 
	           setAttr("penalt", penaltv);
	           setAttr("penaltvSize", penaltv.size());
	        List<CoJobModel> job = CoJobModel.dao.getJob(name);  
	           setAttr("job", job);
	           setAttr("jobSize", job.size());
	        List<CoTradeMarkModel> tradeMark =  CoTradeMarkModel.dao.getMark(name);  
	           setAttr("trade", tradeMark);
	        List<CoPatentModel> patent = CoPatentModel.dao.getPatent(name);   
			   setAttr("patent", patent);
		    List<CoSoftcopyModel> soft = CoSoftcopyModel.dao.getSoft(name);   
		       setAttr("soft", soft);
		    List<CoCopyrightModel> copy = CoCopyrightModel.dao.getCopy(name);  
		       setAttr("copy", copy);
		    List<CoWebsiteModel> website = CoWebsiteModel.dao.getWebsite(name);   
	           setAttr("website", website);
	        CoShareHolderModel count = CoShareHolderModel.dao.getCount(name);
		       setAttr("count", count);
		    List<CoShareHolderModel> stockC = CoShareHolderModel.dao.getStockc(name);
		        setAttr("stockc", stockC);
		     CoEnterpriseModel cc=CoEnterpriseModel.dao.getid(name);
		   
		  List<MarkNewsModel> mark =  MarkNewsModel.dao.getNews(cc.get("id")+"", "", "", "");
		     setAttr("marknews", mark);
		  List<CompanysInfoModel> infoModels= CompanysInfoModel.dao.getCompanybyName(basic.get(0).get("legal_person")+"");
		      setAttr("comInfo", infoModels);
		      
		      setAttr("all", CoShareHolderModel.dao.getMainSize(name).size()+CoShareHolderModel.dao.getManagerSize(name).size()
					  +CoShareHolderModel.dao.getDirectSize(name).size()+CoShareHolderModel.dao.getSuperSize(name).size()+stackInfo.size()+
					  invest.size()+changeLog.size()+branch.size()+liaison.size()+ register.size()+companyType.size());
		     setAttr("allsize", judgment.size()+announce.size()+exector.size()+businesExeption.size()+penaltv.size()+job.size()); 
		      List<CoMainPersonModel> senior= CoMainPersonModel.dao.getRelact(basic.get(0).get("legal_person"));
		     setAttr("senior", senior);
		     render("p2p/pages/04_01pingtaigongshang.html");
	 }

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
