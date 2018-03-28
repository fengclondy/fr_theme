package com.qdch.xd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jfinal.kit.JsonKit;
import com.qdch.core.BaseController;
import com.qdch.p2p.model.ConRatioModel;
import com.qdch.p2p.model.IndexRankingModel;
/**
 * 
 * @todo   监管全景-业务总览
 * @time   2018年3月24日 下午2:38:49
 * @author zuoqb
 */
public class BusinessOverviewController extends BaseController {
	/**
	 * 
	 * @todo   风险总览页面
	 * @time   2018年3月24日 下午2:40:42
	 * @author zuoqb
	 */
	public void index() {
		 render("xd/pages/01_02yewuzonglan.html");
	}



}
