package com.qdch.xd.model;

import com.fr.stable.StringUtils;
import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * 
 * @todo   阈值 hub_fxsj_yuzhi
 * @time   2018年4月10日17:44:35
 * @author wf
 */
public class ThresholdValueModel extends Model<ThresholdValueModel>{
	private static final long serialVersionUID = 1L;
	public static final ThresholdValueModel dao = new ThresholdValueModel();


	/**
	 * 取得阈值信息
	 * @param type
	 * @return
	 */
	public List<ThresholdValueModel> getList(String type){
		String sql = "SELECT * from hub_fxsj_yuzhi WHERE jysfl='"+type+"'";

		return dao.find(sql);
	}






}
