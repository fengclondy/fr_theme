package com.qdch.xd.model;

import com.fr.hailian.util.DateUtil;
import com.jfinal.plugin.activerecord.Model;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 
 * @todo   风险事件
 * @time   2018年4月2日14:59:19
 * @author wf
 */
public class RiskEventModel extends Model<RiskEventModel>{
	private static final long serialVersionUID = 1L;
	public static final RiskEventModel dao = new RiskEventModel();


	public List<RiskEventModel> getRiskEvent(String dataSql){
//		String sql="select vday_ym,jysc,jyscmc,nums,vday from insight_xd_fxsj_count where 1=1 ";
//		if(StringUtils.isNotBlank(dataSql)){
//			sql+=" and jysc in"+ dataSql+" ";
//		}
//		sql+=" and vday='"+DateUtil.format(new Date(-1), "yyyyMMdd")+"' order by nums";
//		return dao.find(sql);
		String sql = "select * from hub_fxsj a limit 10";
		return dao.find("");

	}

}
