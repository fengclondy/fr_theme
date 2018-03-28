package com.qdch.xd.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fr.hailian.util.DateUtil;
import com.jfinal.plugin.activerecord.Model;
/**
 * 
 * @todo   小贷风险指数
 * @time   2018年3月24日 下午3:44:32
 * @author zuoqb
 */
public class RiskCountModel extends Model<RiskCountModel>{
	private static final long serialVersionUID = 1L;
	public static final RiskCountModel dao = new RiskCountModel();
	/**
	 * 
	 * @todo   获取风险排名
	 * @time   2018年3月24日 下午4:16:09
	 * @author zuoqb
	 */
	public List<RiskCountModel> getRiskRanking(String dataSql){
		String sql="select vday_ym,jysc,jyscmc,nums,vday from insight_xd_fxsj_count where 1=1 ";
		if(StringUtils.isNotBlank(dataSql)){
			sql+=" and jysc in"+ dataSql+" ";
		}
		sql+=" and vday='"+DateUtil.format(new Date(-1), "yyyyMMdd")+"' order by nums";
		return dao.find(sql);
	}
}
