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
import com.qdch.util.TemplteLayoutTag;

import com.qdch.xd.model.ComparisonOfCompeModel;
import com.qdch.xd.model.CompetitiveRrendModel;
import com.qdch.xd.model.ConRatioModel;
import com.qdch.xd.model.CurrentComRankingModel;
import com.qdch.xd.model.DefrateModel;
import com.qdch.xd.model.IncomeAndLossrateModel;
import com.qdch.xd.model.IndexRankingModel;
import com.qdch.xd.model.JyscModel;
import com.qdch.xd.model.KeyIndicatorsModel;
import com.qdch.xd.model.MigrationRateModel;
import com.qdch.xd.model.ProportionModel;
import com.qdch.xd.model.RiskCountModel;
import com.qdch.xd.model.ScabilityModel;


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
		me.add(arp);
		// 配置Postgresql方言
	    arp.setDialect(new PostgreSqlDialect());
		//arp.addMapping("user", User.class);
		arp.addMapping("hub_commerce_ref_jys", DemoModel.class);
		arp.addMapping("hub_xd_jysc", JyscModel.class);
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
		me.add(insight_arp);
		// 配置Postgresql方言
		insight_arp.setDialect(new PostgreSqlDialect());
		
		
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
		insight_arp.addMapping("insight_regulatory_report", MonthlyReportModel.class);//监管月报
		/***doushuiahi Model START***/
		
		
		/***小贷zuoqb insight层 Model START***/
		
		/*insight_arp.addMapping("hub_xd_fxzs", RiskCountModel.class);//小贷风险指数*/		
		insight_arp.addMapping("insight_xd_scability", ScabilityModel.class);//小贷-业务总览-市场竞争力趋势
		
		/***小贷zuoqb insight层 Model START***/
		
		
		
		
		
		/*** 小贷 王风 insight层 Model START ***/
		insight_arp.addMapping("hub_fxsj", RiskEventModel.class);//风险事件
		insight_arp.addMapping("hub_fxsj_audit_new", RiskEventHistoryModel.class);//风险事件历史信息
		insight_arp.addMapping("hub_commerce_ref_jys", ExchangeInfoModel.class);//交易所信息
		insight_arp.addMapping("hub_fxlb", RiskTypeModel.class);//风险类别
		insight_arp.addMapping("hub_xd_cont_assu", GuaranteeContrastModel.class);//担保合同
//
		arp.addMapping("hub_xd_cust_corp", PublicCustomModel.class);//对公客户

		arp.addMapping("hub_xd_cust_pers", PersonalCustomModel.class);//个人客户

//		insight_arp.addMapping("hub_xd_loan_ledeger", DetailsQueryModel.class);//明细查询

		arp.addMapping("hub_xd_cred_indus_info", LimitQueryModel.class);//额度查询



		/***小贷 王风 insight层 Model START***/
		
		
		
		
		
		
		
		/***小贷 连纪明 insight层 Model START ***/
		
		insight_arp.addMapping("hub_xd_fxzs", RiskCountModel.class);//小贷风险指数
		
		
		/***小贷 连纪明 insight层 Model START***/
		
		
		
		
		
		
		/***小贷 李晓依 insight层 Model START ***/
		insight_arp.addMapping("insight_xd_jysc_info", ProportionModel.class);//小贷-管理风险-占比
		
		
		
		/***小贷 王风 insight层 Model START***/
		
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
