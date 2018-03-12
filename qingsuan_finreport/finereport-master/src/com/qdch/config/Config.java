package com.qdch.config;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal3.JFinal3BeetlRenderFactory;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.qdch.controller.DemoController;
import com.qdch.model.DemoModel;
import com.qdch.util.TemplteLayoutTag;

public class Config extends JFinalConfig {
	public void configConstant(Constants me) {
		me.setDevMode(true);
		//添加beetl配置
		JFinal3BeetlRenderFactory rf = new JFinal3BeetlRenderFactory();
		rf.config();
		me.setRenderFactory(rf);
		GroupTemplate gt = rf.groupTemplate;
		gt.registerTag("layout", TemplteLayoutTag.class);
	}

	public void configRoute(Routes me) {
		//设置根页面路径
		me.setBaseViewPath("/WEB-INF/qss");
		me.add("jfinal/demo", DemoController.class,"/");
	}

	public void configEngine(Engine me) {
		me.setDevMode(true);
	}

	public void configPlugin(Plugins me) {
		PropKit.use("config.txt");
		String jdbc = PropKit.get("jdbc");
		String user = PropKit.get("user");
		String pwd = PropKit.get("pwd");
		DruidPlugin dp = new DruidPlugin(jdbc, user, pwd);
//		DruidPlugin dp = new DruidPlugin("jdbc:postgresql://172.16.6.61:5432/qdchedw", "hub", "hub@2017");
		me.add(dp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(com.qdch.core.Constants.QSS_GP_HUB,dp);
		me.add(arp);
		// 配置Postgresql方言
	    arp.setDialect(new PostgreSqlDialect());
		//arp.addMapping("user", User.class);
		arp.addMapping("hub_commerce_ref_jys", DemoModel.class);
	}

	public void configInterceptor(Interceptors me) {
	}

	public void configHandler(Handlers me) {
	}

	public static void main(String[] args){
		JFinal.start("WebRoot", 8080, "/", 5);
	}
}
