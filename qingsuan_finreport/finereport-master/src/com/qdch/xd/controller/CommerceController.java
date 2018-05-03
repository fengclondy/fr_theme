package com.qdch.xd.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ObjectUtils.Null;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.qdch.core.BaseController;
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
import com.qdch.xd.model.CoPatentModel;
import com.qdch.xd.model.CoPenaltvModel;
import com.qdch.xd.model.CoReportModel;
import com.qdch.xd.model.CoScabilityModel;
import com.qdch.xd.model.CoShareHolderModel;
import com.qdch.xd.model.CoSoftcopyModel;
import com.qdch.xd.model.CoStockchangeModel;
import com.qdch.xd.model.CoTradeMarkModel;
import com.qdch.xd.model.CoWebsiteModel;
import com.qdch.xd.model.CompanysInfoModel;
import com.qdch.xd.model.MarkNewsModel;
import com.qdch.xd.model.RiskTrendDetailedModel;
import com.qdch.xd.model.RiskTrendModel;
/**
 * 工商模块获取数据
 * @author lixiaoyi
 * @date 2018年4月10日
 * @TODO
 */
public class CommerceController extends BaseController{

	/**
	 * 首页获取所有信息 并返回公司列表
	 * @author lixiaoyi
	 * @date 2018年4月10日 下午2:45:09
	 * @TODO
	 */
	public void index(){
		String name=getPara("name");
		if (name==null||"".equals(name)) {
			name="重庆海尔小额贷款有限公司";
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
	   
	  List<MarkNewsModel> mark =  MarkNewsModel.dao.getNews2(cc.get("id")+"", "", "", "");
	  List<CompanysInfoModel> infoModels=new ArrayList<CompanysInfoModel>();
	  setAttr("marknews", mark);
	  setAttr("marksize", mark.size());
	  if(basic!=null&&basic.size()>0){
	    	infoModels=CompanysInfoModel.dao.getCompanybyName(basic.get(0).get("legal_person")+"");
	   }
	   setAttr("comInfo", infoModels);
	   setAttr("all", CoShareHolderModel.dao.getMainSize(name).size()+CoShareHolderModel.dao.getManagerSize(name).size()
				  +CoShareHolderModel.dao.getDirectSize(name).size()+CoShareHolderModel.dao.getSuperSize(name).size()+stackInfo.size()+
				  invest.size()+changeLog.size()+branch.size()+liaison.size()+ register.size()+companyType.size());
	   setAttr("allsize", judgment.size()+announce.size()+exector.size()+businesExeption.size()+penaltv.size()+job.size()); 
	   RiskTrendDetailedModel risk = RiskTrendDetailedModel.dao.getScore(getDataScopeByUserName(), name);
	   setAttr("risk", risk);
	   RiskTrendModel valueList=RiskTrendModel.dao.getFxzs(getDataScopeByUserName(), name);
	  setAttr("value", valueList);
	  render("xd/pages/08_01shichanghuaxiang.html");
	}
	/**
	 * 获取企业基本信息 工商信息 
	 * @author lixiaoyi
	 * @date 2018年4月10日 下午2:47:38
	 * @TODO
	 */
	public void getBasicInfo(){
		 String name =getPara("name");
		 //查询企业基本信息及工商信息
	    List<CompanysInfoModel> info = CompanysInfoModel.dao.getBasicinfo(getDataScopeByUserName(),name);
	    mRenderJson(info);
         
	}
	/**
	 * 获取股权结构
	 * @author lixiaoyi
	 * @date 2018年4月10日 下午3:12:33
	 * @TODO
	 */
	public void gainStock(){
		 String name = getPara("name");
	    List<CoShareHolderModel> stock = CoShareHolderModel.dao.getStock(name);
	    mRenderJson(stock);
        
	}
	/**
	 * 获取核心人员--自然股东
	 * @author lixiaoyi
	 * @date 2018年4月11日 上午10:05:42
	 * @TODO
	 */
	public void gainMainperson(){
		 String name = getPara("name");
	    List<CoShareHolderModel> main = CoShareHolderModel.dao.getMainperson(name);
	    mRenderJson(main);
       
	}
	/**
	 * 获取核心人员-总经理
	 * @author lixiaoyi
	 * @date 2018年4月11日 上午10:13:32
	 * @TODO
	 */
	public void gainManager(){
	   String name = getPara("name");
	    List<CoShareHolderModel> manager = CoShareHolderModel.dao.getManager(name);
	    mRenderJson(manager);
	} 
	/**
	 * 获取核心人员-董事
	 * @author lixiaoyi
	 * @date 2018年4月11日 上午10:14:35
	 * @TODO
	 */
	public void gainDirector(){
	   String name = getPara("name");
	    List<CoShareHolderModel> dierctor = CoShareHolderModel.dao.getDong(name);
	    mRenderJson(dierctor);
	} 
	/**
	 * 获取核心人员-监事
	 * @author lixiaoyi
	 * @date 2018年4月11日 上午10:25:48
	 * @TODO
	 */
	public void gainSupervisor(){
		   String name = getPara("name");
		  List<CoShareHolderModel> supervisor = CoShareHolderModel.dao.getJian(name);
		   mRenderJson(supervisor);
		} 
	/**
	 * 股东信息
	 * @author lixiaoyi
	 * @date 2018年4月11日 上午10:39:22
	 * @TODO
	 */
	public void gainStockInfo(){
		   String name = getPara("name");
		  List<CoShareHolderModel> stackInfo = CoShareHolderModel.dao.getStackInfo(name);
		   mRenderJson(stackInfo);
		} 
	/**
	 * 对外投资
	 * @author lixiaoyi
	 * @date 2018年4月11日 上午11:28:47
	 * @TODO
	 */
	public void gainInvest(){
		   String name = getPara("name");
		  List<CoShareHolderModel> invest = CoShareHolderModel.dao.getInvest(name);
		   mRenderJson(invest);
		} 
	/**
	 * 变更记录
	 * @author lixiaoyi
	 * @date 2018年4月11日 上午11:51:37
	 * @TODO
	 */
	public void gainChangelog(){
		String name = getPara("name");
		List<CoChangeLogModel> changeLog = CoChangeLogModel.dao.getChange(name);
		mRenderJson(changeLog);
	}
	/**
	 * 企业年报
	 * @author lixiaoyi
	 * @date 2018年4月11日 下午1:02:17
	 * @TODO
	 */
	public void gainReport(){
		String name = getPara("name");
		     List<CoReportModel> report = CoReportModel.dao.getReport(name);
		mRenderJson(report);
	}
	/**
	 * 分支机构
	 * @author lixiaoyi
	 * @date 2018年4月11日 下午1:20:28
	 * @TODO
	 */
	public void gainBranch(){
		String name = getPara("name");
		List<CoBranchModel> branch = CoBranchModel.dao.getBranch(name);
		mRenderJson(branch);
		     
	} 
	/**
	 * 法律诉讼列表
	 * @author lixiaoyi
	 * @date 2018年4月11日 下午1:37:58
	 * @TODO
	 */
	public void gainJudgment(){
		String name = getPara("name");
	      List<CoJudgmentModel> judgment = CoJudgmentModel.dao.getJudgment(name);
		mRenderJson(judgment);
		     
	} 
	/**
	 * 法律公告
	 * @author lixiaoyi
	 * @date 2018年4月11日 下午1:45:04
	 * @TODO
	 */
	public void gainPublic(){
		String name = getPara("name");
	List<CoAnnounceModel> announce = CoAnnounceModel.dao.getAnnounce(name);
	mRenderJson(announce);
		   
	}
	/**
	 * 失信人信息
	 * @author lixiaoyi
	 * @date 2018年4月11日 下午1:51:35
	 * @TODO
	 */
	public void gainDishonesty(){
		String name = getPara("name");
	   CoDishonestyModel dishonesty = CoDishonestyModel.dao.getDishonesty(name);
	    mRenderJson(dishonesty);
	}
	/**
	 * 被执行人
	 * @author lixiaoyi
	 * @date 2018年4月11日 下午2:08:09
	 * @TODO
	 */
	public void gainExecutor(){
		String name = getPara("name");
		List<CoExecutorModel> exector = CoExecutorModel.dao.getExecutor(name);
		mRenderJson(exector);
	}
	/**
	 * 经营异常
	 * @author lixiaoyi
	 * @date 2018年4月11日 下午2:22:31
	 * @TODO
	 */
	public void gainBusiness(){
		String name = getPara("name");
		 List<CoBusinessModel> business = CoBusinessModel.dao.getBusiness(name);
	     mRenderJson(business); 
	}
	/**
	 * 行政处罚
	 * @author lixiaoyi
	 * @date 2018年4月11日 下午2:34:52
	 * @TODO
	 */
	public void gainPenaltv(){
		String name = getPara("name");
	   List<CoPenaltvModel> penaltv = CoPenaltvModel.dao.getPenalt(name);
	   mRenderJson(penaltv);
	}
	/**
	 * 招聘工作
	 * @author lixiaoyi
	 * @date 2018年4月11日 下午2:38:49
	 * @TODO
	 */
	public void gainJob(){
		String name = getPara("name");
		List<CoJobModel> job = CoJobModel.dao.getJob(name);
		mRenderJson(job);
	}
	/**
	 * 商标信息
	 * @author lixiaoyi
	 * @date 2018年4月11日 下午2:51:54
	 * @TODO
	 */
	public void gainTrademark(){
		String name = getPara("name");
		List<CoTradeMarkModel> tradeMark =  CoTradeMarkModel.dao.getMark(name);
		mRenderJson(tradeMark);
	}
	
	/**
	 * 专利信息
	 * @author lixiaoyi
	 * @date 2018年4月11日 下午2:55:13
	 * @TODO
	 */
	public void gainPanten(){
		String name = getPara("name");
		List<CoPatentModel> patent = CoPatentModel.dao.getPatent(name);
		mRenderJson(patent);
	}
	/**
	 * 软件著作权
	 * @author lixiaoyi
	 * @date 2018年4月11日 下午2:58:09
	 * @TODO
	 */
	public void gainSoft(){
		String name = getPara("name");
		List<CoSoftcopyModel> soft = CoSoftcopyModel.dao.getSoft(name); 
	    mRenderJson(soft);
	}
	/**
	 * 工商变更
	 * @author lixiaoyi
	 * @date 2018年4月11日 下午3:15:16
	 * @TODO
	 */
	public void gainCommchange(){
		String name = getPara("name");
		List<CoChangeLogModel> commerce = CoChangeLogModel.dao.getCommerce(name);
	    mRenderJson(commerce);
	}
	
	/**
	 * 著作权
	 * @author lixiaoyi
	 * @date 2018年4月11日 下午3:30:00
	 * @TODO
	 */
	public  void gainCopy(){
		String name = getPara("name");
		List<CoCopyrightModel> copy = CoCopyrightModel.dao.getCopy(name);
		mRenderJson(copy);
	}
	/**
	 * 网站备案
	 * @author lixiaoyi
	 * @date 2018年4月11日 下午3:32:24
	 * @TODO
	 */
	public void gainWebsite(){
		String name = getPara("name");
		List<CoWebsiteModel> website = CoWebsiteModel.dao.getWebsite(name);
	    mRenderJson(website);
	}
	/**
	 * 舆情详情
	 * @author lixiaoyi
	 * @date 2018年4月17日 下午3:05:46
	 * @TODO
	 */
	public void gainNews(){
	String name =	getPara("name");
	String keyword = getPara("keyword");
	String begin= getPara("begin");
	String end = getPara("end");
	List<MarkNewsModel> mark =null;
	if (name!=null||"".equals(name)) {
		 CoEnterpriseModel cc=CoEnterpriseModel.dao.getid(name);
		  mark  =  MarkNewsModel.dao.getNews(cc.get("id")+"", keyword, begin,end);

	}else {
	 mark =  MarkNewsModel.dao.getNews("", keyword, begin,end);

	}
		mRenderJson(mark);
	}
	/**
	 * 平均综合得分
	 * @author lixiaoyi
	 * @date 2018年4月18日 上午9:34:39
	 * @TODO
	 */
	public void gainScore(){
	String name=getPara("name");
	List<CoScabilityModel>	  scability= CoScabilityModel.dao.getScore(name, getDataScopeByUserName());
	  if (scability.size()==0) {
		  mRenderJson("kong");
		
	}else {
		mRenderJson(scability.get(0));
	}    	
	}
	/**
	 * 风险指数
	 * @author lixiaoyi
	 * @date 2018年4月18日 上午11:20:23
	 * @TODO
	 */
	public void gainRiskscore(){
		String name=getPara("name");
		RiskTrendDetailedModel risk = RiskTrendDetailedModel.dao.getScore(getDataScopeByUserName(), name);
	    mRenderJson(risk);
	}
	/**
	 * 关系图谱
	 * @author lixiaoyi
	 * @date 2018年5月2日 下午1:42:24
	 * 
	 * @TODO
	 */
	public void gainRelationMap(){
        String name	=	getPara("name");
	    List<CompanysInfoModel>  map = CompanysInfoModel.dao.getRelationMap(name);
	    mRenderJson(map);
	}
}
