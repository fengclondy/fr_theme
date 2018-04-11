package com.qdch.xd.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

/**
 * 
 * @todo   风险类别
 * @time   2018年4月2日14:59:19
 * @author wf
 */
public class RiskTypeModel extends Model<RiskTypeModel>{
	private static final long serialVersionUID = 1L;
	public static final RiskTypeModel dao = new RiskTypeModel();



	/**
	 * 根据交易所信息来查询
	 * @param type
	 * @return
	 */
	public List<RiskTypeModel> getByType(String type){
	    String sql = "SELECT * FROM hub_fxlb WHERE jysfl='"+type+"'";
        return dao.find(sql);
    }

    public List<RiskTypeModel> getTypeKind(String type){
		String sql = "select fxlb from hub_fxlb where jysfl='"+type+"' GROUP BY fxlb";
		return dao.find(sql);
	}




}
