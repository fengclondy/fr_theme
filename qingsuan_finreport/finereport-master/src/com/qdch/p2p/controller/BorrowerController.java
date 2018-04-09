package com.qdch.p2p.controller;

import java.util.List;

import com.qdch.core.BaseController;
import com.qdch.p2p.model.BorrowerModel;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月8日
 * @TODO 借款人总览
 */
public class BorrowerController extends BaseController {
	/**
	 * 
	 * @author lixiaoyi
	 * @date 2018年4月8日
	 * @TODO 借款人总览首页
	 */
	public void index(){
		render("p2p/pages/04_05jiekuanrenhuaxiang.html");
	}

}
