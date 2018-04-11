package com.qdch.config;
import com.qdch.xd.controller.*;
import com.qdch.xd.model.*;
import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal3.JFinal3BeetlRenderFactory;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.qdch.model.DemoModel;
import com.qdch.p2p.controller.BorrowerController;
import com.qdch.p2p.controller.BorrowerPhotoController;
import com.qdch.p2p.controller.CompanyFeelController;
import com.qdch.p2p.controller.DealplatformController;
import com.qdch.p2p.controller.PlatformAlertController;
import com.qdch.p2p.controller.PlatformController;
import com.qdch.p2p.controller.ProjectplatformController;
import com.qdch.p2p.controller.RiskController;
import com.qdch.p2p.controller.SuperviseController;
import com.qdch.p2p.model.BorrowerModel;
import com.qdch.p2p.model.PlatformModel;
import com.qdch.p2p.model.PpjyscModel;
import com.qdch.p2p.model.ProjectStructureModel;
import com.qdch.util.TemplteLayoutTag;

import com.qdch.xd.controller.AssetRiskController;
import com.qdch.xd.controller.BusinessOverviewController;
import com.qdch.xd.controller.ComplianceRiskController;
import com.qdch.xd.controller.CreditRiskController;
import com.qdch.xd.controller.DevelopmentCapacityController;
import com.qdch.xd.controller.EventAuditController;
import com.qdch.xd.controller.EventDecisionController;
import com.qdch.xd.controller.EventProcessingController;
import com.qdch.xd.controller.EventSeeDetailsController;
import com.qdch.xd.controller.EventViewController;
import com.qdch.xd.controller.ManagementRiskController;
import com.qdch.xd.controller.MonthlyReportController;
import com.qdch.xd.controller.OperationalCapabilityController;
import com.qdch.xd.controller.ProfitabilityController;
import com.qdch.xd.controller.ReputationRiskController;
import com.qdch.xd.controller.RiskOverviewController;
import com.qdch.xd.controller.XiaoDaiController;
import com.qdch.xd.model.ComparisonOfCompeModel;
import com.qdch.xd.model.CompetitiveRrendModel;
import com.qdch.xd.model.ConRatioModel;
import com.qdch.xd.model.CurrentComRankingModel;
import com.qdch.xd.model.CustomerInfoModel;
import com.qdch.xd.model.DefrateModel;
import com.qdch.xd.model.DetailsQueryModel;
import com.qdch.xd.model.DictModel;
import com.qdch.xd.model.ExchangeInfoModel;
import com.qdch.xd.model.GuaranteeContrastModel;
import com.qdch.xd.model.IncomeAndLossrateModel;
import com.qdch.xd.model.IndexRankingModel;
import com.qdch.xd.model.JyscModel;
import com.qdch.xd.model.KeyIndicatorsModel;
import com.qdch.xd.model.LimitQueryModel;
import com.qdch.xd.model.ManagementRiskListModel;
import com.qdch.xd.model.MigrationRateModel;
import com.qdch.xd.model.MonthlyReportListModel;
import com.qdch.xd.model.MonthlyReportModel;
import com.qdch.xd.model.PersonalCustomModel;
import com.qdch.xd.model.ProportionModel;
import com.qdch.xd.model.PublicCustomModel;
import com.qdch.xd.model.RiskCountModel;
import com.qdch.xd.model.RiskEventHistoryModel;
import com.qdch.xd.model.RiskEventModel;
import com.qdch.xd.model.RiskShowModel;
import com.qdch.xd.model.RiskTrendDetailedModel;
import com.qdch.xd.model.RiskTrendModel;
import com.qdch.xd.model.RiskTypeModel;
import com.qdch.xd.model.ScabilityModel;
import com.qdch.xd.model.defrateRankModel;
import com.qdch.xd.model.maxIntrateRankModel;



public class Config extends JFinalConfig {
	public void configConstant(Constants me) {
		me.setDevMode(true);
		//添加beetl配置
		JFinal3BeetlRenderFactory rf = new JFinal3BeetlRenderFactory();
		rf.config();
//		me.setViewType(ViewType.JSP);
		me.setRenderFactory(rf);
		GroupTemplate gt = rf.groupTemplate;
		gt.registerTag("layout", TemplteLayoutTag.class);
	}

	public void configRoute(Routes me) {
		//设置根页面路径
		me.setBaseViewPath("/WEB-INF/qss");
		

	
	
		me.add("qdch/xiaodai", XiaoDaiController.class,"/");
		
		/***小贷 doushuihai Controller START***/
		
		me.add("qdch/creditrisk", CreditRiskController.class,"/");	//信用风险
		me.add("qdch/managementrisk", ManagementRiskController.class,"/");	//管理风险
		me.add("qdch/assetrisk", AssetRiskController.class,"/");	//资产风险
		me.add("qdch/compliancerisk", ComplianceRiskController.class,"/");	//合规风险
		me.add("qdch/reputationrisk", ReputationRiskController.class,"/");	//声誉风险
		me.add("qdch/operationalcapability", OperationalCapabilityController.class,"/");	//经营能力
		me.add("qdch/profitability", ProfitabilityController.class,"/");	//盈利能力
		me.add("qdch/developmentcapacity", DevelopmentCapacityController.class,"/");	//发展能力
		me.add("qdch/eventprocessing", EventProcessingController.class,"/");	//风险事件处理
		me.add("qdch/eventview", EventViewController.class,"/");	//风险事件查看
		me.add("qdch/eventaudit", EventAuditController.class,"/");	//风险事件审核
		me.add("qdch/eventdecision", EventDecisionController.class,"/");	//风险事件决策
		me.add("qdch/monthlyrepor", MonthlyReportController.class,"/");	//监管月报
		/***小贷doushuihai Controller START***/
		
		/***小贷zuoqb Controller START***/
		
		
		me.add("qdch/riskOverview", RiskOverviewController.class,"/");//小贷-风险总览
		me.add("qdch/businessOverview", BusinessOverviewController.class,"/");//小贷-业务总览
		
		/***小贷zuoqb Controller END***/

		me.add("qdch/eventSeeDetails", EventSeeDetailsController.class,"/");	//监管月报
		me.add("qdch/eventinput", EventInputController.class,"/");	//风险事件填报
		
		/***p2p lixiaoyi Controller START ***/
		me.add("qdch/borrower",BorrowerController.class,"/");    //p2p-借款人总览
		me.add("qdch/platform",PlatformController.class,"/");    //p2p-平台总览
		me.add("qdch/risk",RiskController.class,"/");  // p2p-风险总览
		me.add("qdch/project",ProjectplatformController.class,"/"); //p2p-平台项目
		me.add("qdch/deal",DealplatformController.class,"/"); //p2p-平台交易
		me.add("qdch/borrowerphoto",BorrowerPhotoController.class,"/"); //p2p-借款人画像
		me.add("qdch/companyfeel",CompanyFeelController.class,"/");  //p2p-企业舆情
		me.add("qdch/supervise",SuperviseController.class,"/");  //p2p-监管月报
		me.add("qdch/platformalert",PlatformAlertController.class,"/"); //p2p-平台总览弹出
	}

	public void configEngine(Engine me) {
		me.setDevMode(true);
	}

	public void configPlugin(Plugins me) {
		PropKit.use("config.txt");

		//----qdchedw hub用户连接方式 start----
		String jdbc = PropKit.get("jdbc");
		String user = PropKit.get("user");
		String pwd = PropKit.get("pwd");
		DruidPlugin dp = new DruidPlugin(jdbc, user, pwd);
		//DruidPlugin dp = new DruidPlugin("jdbc:postgresql://172.16.6.61:5432/qdchedw", "hub", "hub@2017");
		me.add(dp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(com.qdch.core.Constants.QSS_GP_HUB,dp);
		arp.setBaseSqlTemplatePath(PathKit.getRootClassPath());
		arp.addSqlTemplate("all.sql");
		arp.setShowSql(true);
		arp.setDialect(new PostgreSqlDialect());
		me.add(arp);
		// 配置Postgresql方言

		//arp.addMapping("user", User.class);
		arp.addMapping("hub_commerce_ref_jys", DemoModel.class);
		arp.addMapping("hub_xd_jysc", JyscModel.class);
		arp.addMapping("hub_fxsj", RiskShowModel.class);
		//----qdchedw hub用户连接方式 end----
		
		
		//----qdchedw insight用户连接方式 start----
		String insight_jdbc = PropKit.get("insight_jdbc");
		String insight_user = PropKit.get("insight_user");
		String insight_pwd = PropKit.get("insight_pwd");
		DruidPlugin insight_dp = new DruidPlugin(insight_jdbc, insight_user, insight_pwd);
		me.add(insight_dp);
		ActiveRecordPlugin insight_arp = new ActiveRecordPlugin(com.qdch.core.Constants.QSS_GP_INSIGHT,insight_dp);
		insight_arp.setBaseSqlTemplatePath(PathKit.getRootClassPath());
		insight_arp.addSqlTemplate("all.sql");
		insight_arp.setShowSql(true);
		insight_arp.setDialect(new PostgreSqlDialect());
		me.add(insight_arp);
		// 配置Postgresql方言

		
		
		/**
		 * 以下表实体映射配置按开发人员名字分块，不要随便写
		 */
		
		/***doushuiahi Model START***/
		insight_arp.addMapping("insight_xd_loan_count", IndexRankingModel.class);//指标排名		
		insight_arp.addMapping("insight_xd_defrate", DefrateModel.class);//不良率
		insight_arp.addMapping("insight_xd_mobiratio", MigrationRateModel.class);//迁徙率
		insight_arp.addMapping("insight_xd_cust_count", ConRatioModel.class);//开户人数集中度
		insight_arp.addMapping("insight_xd_income", IncomeAndLossrateModel.class);
		insight_arp.addMapping("insight_xd_lossrate", IncomeAndLossrateModel.class);
		insight_arp.addMapping("insight_xd_yeamount", KeyIndicatorsModel.class);//业务总览的关键指标排名-贷款余额
		insight_arp.addMapping("insight_xd_fkamount", KeyIndicatorsModel.class);//业务总览的关键指标排名-放款额和日均放款额
		insight_arp.addMapping("insight_xd_scability", CurrentComRankingModel.class);//业务总览的当前竞争力排名
		insight_arp.addMapping("insight_xd_scability", ComparisonOfCompeModel.class);//业务总览的竞争力对比
		insight_arp.addMapping("insight_xd_scability", CompetitiveRrendModel.class);//业务总览的竞争力对比
		insight_arp.addMapping("insight_regulatory_report", MonthlyReportModel.class);//监管月报
		insight_arp.addMapping("insight_regulatory_report", MonthlyReportListModel.class);//监管月报
		
		arp.addMapping("hub_fxsj", ManagementRiskListModel.class);//字典信用风险的管理风险列表
		/***doushuiahi Model START p2p***/
		arp.addMapping("hub_pp_jysc", PpjyscModel.class);
		insight_arp.addMapping("insight_pp_iterm_count", ProjectStructureModel.class);//平台画像的项目结构
		/***doushuiahi Model START p2p***/
		
		/***p2p 高照  insight层Model SART***/
		insight_arp.addMapping("insight_pp_score_info", BorrowerModel.class);//根据得分降序查找平台简称和得分
		
		
		
		/***小贷zuoqb insight层 Model START***/
		
		/*insight_arp.addMapping("hub_xd_fxzs", RiskCountModel.class);//小贷风险指数*/		
		insight_arp.addMapping("insight_xd_scability", ScabilityModel.class);//小贷-业务总览-市场竞争力趋势
		
		/***小贷zuoqb insight层 Model START***/
		
		
		
		
		
		/*** 小贷 王风 insight层 Model START ***/
		insight_arp.addMapping("hub_fxsj","fxsj_id", RiskEventModel.class);//风险事件
		insight_arp.addMapping("hub_fxsj_audit_new", RiskEventHistoryModel.class);//风险事件历史信息
		insight_arp.addMapping("hub_commerce_ref_jys", ExchangeInfoModel.class);//交易所信息
		insight_arp.addMapping("hub_fxlb", RiskTypeModel.class);//风险类别
		insight_arp.addMapping("hub_xd_cont_assu", GuaranteeContrastModel.class);//担保合同

		arp.addMapping("hub_comm_param", DictModel.class);//字典表

//		arp.addMapping("hub_xd_report_cont", CustomerInfoModel.class);//合同信息-基本信息
		arp.addMapping("hub_xd_cont_info", CustomerInfoModel.class);//合同信息-基本信息

		arp.addMapping("hub_xd_cust_corp", PublicCustomModel.class);//对公客户

		arp.addMapping("hub_xd_cust_pers", PersonalCustomModel.class);//个人客户

		arp.addMapping("hub_xd_loan_ledger", DetailsQueryModel.class);//明细查询

		arp.addMapping("hub_xd_cred_indus_info", LimitQueryModel.class);//额度查询
		arp.addMapping("hub_fxsj_yuzhi", ThresholdValueModel.class);//阈值信息



		/***小贷 王风 insight层 Model START***/
		
		
		
		
		
		
		
		/***小贷 连纪明 insight层 Model START ***/
		
			insight_arp.addMapping("hub_xd_fxzs",RiskCountModel.class);//小贷风险指数
			insight_arp.addMapping("hub_xd_fxzs",RiskTrendModel.class);//风险趋势
			insight_arp.addMapping("hub_xd_fxzsmx",RiskTrendDetailedModel.class);//风险趋势明细
			insight_arp.addMapping("insight_xd_defrate",defrateRankModel.class);//不良率
			insight_arp.addMapping("insight_xd_intrate",maxIntrateRankModel.class);//最高利率
			
		/***小贷 连纪明 insight层 Model END***/
		
		
		
		
		
		
		/***小贷 李晓依 insight层 Model START ***/
		insight_arp.addMapping("insight_xd_jysc_info", ProportionModel.class);//小贷-管理风险-占比
		
		
		
		/***小贷 王风 insight层 Model START***/
		
		
		
		/***p2p 韩朋达 insight层 Model START***/
		insight_arp.addMapping("insight_pp_overview", PlatformModel.class); //p2p 平台总览表
	}

	public void configInterceptor(Interceptors me) {
	}

	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("contextPath"));
	}

	public static void main(String[] args){ 
		JFinal.start("WebRoot", 8080, "/", 5);
	}
}
