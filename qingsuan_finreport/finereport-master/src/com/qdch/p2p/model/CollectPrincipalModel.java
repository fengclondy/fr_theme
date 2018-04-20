package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

public class CollectPrincipalModel  extends Model<CollectPrincipalModel>{
	private static final long serialVersionUID = 1L;
	public static final CollectPrincipalModel dao = new CollectPrincipalModel();
	
	/**
	 * @todo   平台画像，代收本金
	 * @time   2018年4月13日 
	 * @author ljm   
	 * 
	 */
	public List<CollectPrincipalModel> getCollectPrincipal(String datasql,String jysinfo){
		String sql = "select vday_ym,round(avg(je),2) As bz "
				+ "from insight_pp_collect_principal  "
				+ "where jyscfl='4' and je>0 ";
	
		
		if(StringUtils.isNotBlank(datasql)){
			sql+=" and jysc in "+datasql;
		}
		
		if(StringUtils.isNotBlank(jysinfo)){
			sql+=" and jysinfo = '"+jysinfo+"'";
		}
		sql+=" group by vday_ym order by vday_ym ";
		
		return dao.find(sql);
	}
}
