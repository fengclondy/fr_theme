package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * 
 * @author lixiaoyi
 * @date 2018年4月2日
 * @TODO 获取管理风险占比
 */
public class ProportionModel extends Model<ProportionModel>{
	
	private static final long serialVersionUID = 1L;
	
	public static final ProportionModel dao =new ProportionModel();
			
	/**		
	 * 
	 * @author lixiaoyi
	 * @date 2018年4月2日
	 * @TODO 获取风险管理年限占比
	 */
   public List<ProportionModel> getSeniorProportion(String datasql,String jys){
	   String sql="SELECT t1.jysc,t1.jyscmc,t1.cynx,SUM(ggnum) AS fvalue "
	   		+ "FROM insight_xd_jysc_info t1 WHERE t1.vday=(SELECT MAX(vday) FROM insight_xd_jysc_info )";
	   	
		
		if(StringUtils.isNotBlank(jys)){
			sql+="and t1.jyscmc='"+ jys+"'  ";
			
		}
		if(StringUtils.isNotBlank(datasql)){
			sql+="and t1.jysc in"+ datasql+" ";
			
		}
		sql+="GROUP BY t1.vday,t1.jysc,t1.jyscmc,t1.cynx";
		return dao.find(sql);
	
		
	}
	/**
	 * 
	 * @author lixiaoyi
	 * @date 2018年4月2日
	 * @TODO 获取管理风险 学历占比
	 */
   public List<ProportionModel> getEducationProportion(String datasql,String jys){
	   String sql="SELECT t1.jysc,t1.jyscmc,t1.xuel,SUM(ygnum) AS fvalue "
	   		+ "FROM insight_xd_jysc_info t1 WHERE t1.vday=(SELECT MAX(vday) FROM insight_xd_jysc_info )";		
	   	if(StringUtils.isNotBlank(jys)){
			sql+=" and t1.jyscmc='"+ jys+"' ";
		}
		if(StringUtils.isNotBlank(datasql)){
			sql+="and t1.jysc in"+ datasql+" ";
			
		}
		sql+="GROUP BY t1.vday,t1.jysc,t1.jyscmc,t1.xuel";
		return dao.find(sql);
	
		
	}

}
