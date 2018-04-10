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
	/**
	 * @todo   平台运营监控 (环形图)
	 * @time   2018年4月4日 
	 * @author ljm 
	 */
	//获取平台总数，无报警平台数量，有报警平台数量
	public List<RiskCountModel> getAllPlatform(String dataSql){
		String sql = "select  zs,yc,zs-yc As zc from (select count(*) zs from hub_xd_jysc) zs,(select count(DISTINCT jgdm) yc from hub_fxsj where jysfl='3') yc";
		if(StringUtils.isNotBlank(dataSql)){
			sql+=" and jysc in"+ dataSql+" ";
		}
		return dao.find(sql);
	}
	/**
	 * @todo   平台运营监控 (风险种类，数量)
	 * @time   2018年4月4日 
	 * @author ljm
	 */
	public List<RiskCountModel> getRiskCount(String dataSql){
		String sql = "select count(fxlb),fxlb from hub_fxsj where jysfl='3' group by fxlb";
		if(StringUtils.isNotBlank(dataSql)){
			sql+=" and jgdm in"+ dataSql+" ";
		}
		return dao.find(sql);
	}

	
}
