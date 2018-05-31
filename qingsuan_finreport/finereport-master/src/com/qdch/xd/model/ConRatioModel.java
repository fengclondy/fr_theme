package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

//@TableBind(tableName="hub_commerce_ref_jys")
/**
 * 
 * @author doush
 * @date 2018年3月26日
 * @TODO 获取信用风险的指数排名
 */
public class ConRatioModel extends Model<ConRatioModel>{

	private static final long serialVersionUID = 1L;
	
	public static final ConRatioModel dao = new ConRatioModel();
	
	public List<ConRatioModel> getConRatioByCustCount(String bigjys,String jyscode){
		String sql="select vday_ym as month,round(cust_mm::NUMERIC/cust_yy,2)  as value from insight_xd_cust_count where cust_yy !=0 ";
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and jysc in "+ bigjys;
		}
		if(StringUtils.isNotBlank(jyscode)){
			sql+=" and jysc = '"+jyscode+"'";
		}
		sql+=" group by vday_ym,jysc,cust_mm,cust_yy order by vday_ym";
		
		return dao.find(sql);
	}
	public List<ConRatioModel> getConRatioByTime(String bigjys,String jyscode){
		String sql="select vday_ym as month,round(fvalue::NUMERIC/fvalue1,2)  as value from insight_xd_loan_sjjzd where fvalue1 !=0 ";
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and jysc in "+ bigjys;
		}
		if(StringUtils.isNotBlank(jyscode)){
			sql+=" and jysc = '"+jyscode+"'";
		}
		sql+=" group by vday_ym,jysc,fvalue,fvalue1 order by vday_ym";
		
		return dao.find(sql);
	}
	public List<ConRatioModel> getConRatioByAge(String bigjys,String jyscode){
		String sql=" SELECT jyscmc,agestage,round(SUM(custnum)::NUMERIC/(SELECT SUM(custnum) FROM insight_xd_cust_tongji WHERE jyscmc=t1.jyscmc),2) AS nums "
				+ "FROM insight_xd_cust_tongji t1 where 1=1 ";
				
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and jysc in "+ bigjys;
		}
		if(StringUtils.isNotBlank(jyscode)){
			sql+=" and jysc = '"+jyscode+"'";
		}
		sql+="  group by t1.jyscmc,t1.agestage order by t1.agestage";
		
		return dao.find(sql);
	}
	public List<ConRatioModel> getConRatioByRegion(String bigjys,String jyscode){
		String sql=" SELECT jyscmc,provice,round(SUM(custnum)::NUMERIC/(SELECT SUM(custnum) FROM insight_xd_cust_tongji WHERE jyscmc=t1.jyscmc),2) AS nums "
				+ "FROM insight_xd_cust_tongji t1 where 1=1 ";
				
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and jysc in "+ bigjys;
		}
		if(StringUtils.isNotBlank(jyscode)){
			sql+=" and jysc = '"+jyscode+"'";
		}
		sql+="  group by t1.jyscmc,t1.provice order by t1.provice";
		
		return dao.find(sql);
	}
	public List<ConRatioModel> getConRatioByIndustry(String bigjys,String jyscode){
		String sql="SELECT jyscmc,waykind,round(SUM(custnum)::NUMERIC/(SELECT SUM(custnum) FROM insight_xd_cust_tongji WHERE jyscmc=t1.jyscmc),2) AS nums "
				+ "FROM insight_xd_cust_tongji t1 where 1=1 ";
				
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and jysc in "+ bigjys;
		}
		if(StringUtils.isNotBlank(jyscode)){
			sql+=" and jysc = '"+jyscode+"'";
		}
		sql+="  group by t1.jyscmc,t1.waykind order by t1.waykind";
		
		return dao.find(sql);
	}
	
	
	
	
}
