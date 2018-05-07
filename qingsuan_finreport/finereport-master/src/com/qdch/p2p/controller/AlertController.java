package com.qdch.p2p.controller;

import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.p2p.model.AlertCompanyRegisterModel;
import com.qdch.p2p.model.AlertCompanyTimeModel;
import com.qdch.p2p.model.AlertPersonAgeModel;
import com.qdch.p2p.model.AlertPersonCompanyModel;
import com.qdch.p2p.model.AlertPersonIncomeModel;
import com.qdch.p2p.model.AlertPersonSexModel;
import com.qdch.p2p.model.AlertProvinceModel;
import com.qdch.p2p.model.AlertTradePersonnumModel;

public class AlertController  extends BaseController{

	public void index (){
	  String name= getPara("name");
	  setAttr("name", name);
		render("p2p/pages/04_06iiekuanrenhuaxiangalert.html");
	}
	/**
	 * 省份分布
	 * @author lixiaoyi
	 * @date 2018年5月7日 上午11:48:39
	 * @TODO
	 */
	public void getProvince(){
	     String name=	getPara("name");
	   List<AlertProvinceModel> provice=     AlertProvinceModel.dao.gainProvice(name);
	  mRenderError(provice);
	}
	/**
	 * 行业人数分布
	 * @author lixiaoyi
	 * @date 2018年5月7日 上午11:55:15
	 * @TODO
	 */
	public void getTradenum(){
	     String name=	getPara("name");
	   List<AlertTradePersonnumModel> trade=  AlertTradePersonnumModel.dao.gainTrdae(name);
	  mRenderError(trade);
	}
	/**
	 * 个人公司占比
	 * @author lixiaoyi
	 * @date 2018年5月7日 下午1:19:59
	 * @TODO
	 */
	public void getPersonCompany(){
	     String name=	getPara("name");
	     List<AlertPersonCompanyModel> person=  AlertPersonCompanyModel.dao.gainPersonCompany(name);
	  mRenderError(person);
	}
	/**
	 * 个人年龄占比
	 * @author lixiaoyi
	 * @date 2018年5月7日 下午1:33:39
	 * @TODO
	 */
	public void getPersonage(){
	     String name=	getPara("name");
	     List<AlertPersonAgeModel> age = AlertPersonAgeModel.dao.gainAge(name);
	  mRenderError(age);
	}
	/**
	 * 个人性别占比
	 * @author lixiaoyi
	 * @date 2018年5月7日 下午1:39:15
	 * @TODO
	 */
	public void getPersonsex(){
	     String name=	getPara("name");
	  List<AlertPersonSexModel> sex= AlertPersonSexModel.dao.gainSex(name);
	  mRenderError(sex);
	}
	/**
	 * 个人收入占比
	 * @author lixiaoyi
	 * @date 2018年5月7日 下午1:39:24
	 * @TODO
	 */
	public void getPersonincome(){
	     String name=	getPara("name");
	    List<AlertPersonIncomeModel> income  =  AlertPersonIncomeModel.dao.gainIncome(name);
	  mRenderError(income);
	}
	
	
	
	/**
	 * 企业注册时间占比
	 * @author lixiaoyi
	 * @date 2018年5月7日 下午1:34:48
	 * @TODO
	 */
	public void getCompanytime(){
	     String name=	getPara("name");
	  List<AlertCompanyTimeModel> time = AlertCompanyTimeModel.dao.gainTime(name);
	  mRenderError(time);
	}
	/**
	 * 企业注册资本占比
	 * @author lixiaoyi
	 * @date 2018年5月7日 下午1:36:48
	 * @TODO
	 */
	public void getCompanyregister(){
	     String name=	getPara("name");
	     List<AlertCompanyRegisterModel>  register=   AlertCompanyRegisterModel.dao.gainRegister(name);
	  mRenderError(register);
	}
	
	
}
