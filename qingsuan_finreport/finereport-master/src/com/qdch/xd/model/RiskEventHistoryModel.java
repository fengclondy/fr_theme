package com.qdch.xd.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

/**
 * 
 * @todo   风险事件
 * @time   2018年4月2日14:59:19
 * @author wf
 */
public class RiskEventHistoryModel extends Model<RiskEventHistoryModel>{
	private static final long serialVersionUID = 1L;
	public static final RiskEventHistoryModel dao = new RiskEventHistoryModel();


	/**
     * 风险事件列表
	 * @param num
     * @param size
     * @return
     */
	public Page<RiskEventHistoryModel> getRiskEvent(int num, int size){
//		String sql="select vday_ym,jysc,jyscmc,nums,vday from insight_xd_fxsj_count where 1=1 ";
//		if(StringUtils.isNotBlank(dataSql)){
//			sql+=" and jysc in"+ dataSql+" ";
//		}
//		sql+=" and vday='"+DateUtil.format(new Date(-1), "yyyyMMdd")+"' order by nums";
//		return dao.find(sql);
		String sql = "select * from hub_fxsj a ";
		List<RiskEventHistoryModel> ss = dao.find(sql);
		return dao.paginate(num,size,sql,"");

	}

	public List<RiskEventHistoryModel> getByRiskEvent(String id){
		String sql = "select report_id checker,clzt checkstatus,update_time checktime,bz remarks from hub_fxsj_audit_new WHERE fxsj_id="+id+" ORDER BY update_time asc";
		return dao.find(sql);
	}

//	public Rs


}
