package com.qdch.xd.model;


import java.util.List;
import org.apache.commons.lang.StringUtils;
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
		String sql = "SELECT x.jyscmc,x.vday,x.fvalue,y.jysinfo,y.fxlb,y.nums "
				   + "from (select a.jyscmc,a.vday,a.fvalue "
				   		 + "from hub_xd_fxzs a,(select jyscmc,max(vday)max_vday from hub_xd_fxzs group by jyscmc) t "
				   		 + "where a.jyscmc=t.jyscmc and a.vday=t.max_vday) x,hub_xd_fxzsmx y "
				   + "where x.jyscmc = y.jyscmc and x.vday = y.vday order by x.fvalue asc";
		if(StringUtils.isNotBlank(dataSql)){
			sql+=" and jysc in"+ dataSql+" ";
		}
		return dao.find(sql);
	}
}
