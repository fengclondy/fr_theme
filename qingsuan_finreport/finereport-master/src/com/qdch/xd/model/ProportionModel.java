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
   public List<ProportionModel> getSeniorProportion(String datasql){
	   String sql="select * from insight_xd_jysc_info where 1=1 ";
		
		if(StringUtils.isNotBlank(datasql)){
			sql+=" jysc in"+ datasql+"";
		}
		return dao.find(sql);
	
		
	}
	/**
	 * 
	 * @author lixiaoyi
	 * @date 2018年4月2日
	 * @TODO 获取管理风险 学历占比
	 */
   public List<ProportionModel> getEducationProportion(String datasql){
	   String sql="select * from insight_xd_jysc_info where 1=1 ";
		
		if(StringUtils.isNotBlank(datasql)){
			sql+=" jysc in"+ datasql+"";
		}
		return dao.find(sql);
	
		
	}

}
