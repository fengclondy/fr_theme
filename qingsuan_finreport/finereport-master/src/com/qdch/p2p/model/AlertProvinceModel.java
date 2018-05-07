package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 借款人总览弹出--省份借款方数量排名
 * @author lixiaoyi
 * @date 2018年5月7日上午9:58:10
 * @TODO
 */
public class AlertProvinceModel  extends Model<AlertProvinceModel>{
	private static final long serialVersionUID = 1L;
	public static final AlertProvinceModel dao =new AlertProvinceModel();
	
	public  List<AlertProvinceModel> gainProvice(String name){
		
		String sql="select jysc,jysinfo,provice,sum(coalesce(nums::numeric,0)) as nums from insight_pp_jkf_count  where 1=1";
		if (StringUtils.isNotBlank(name)) {
			sql+=" and jysinfo='"+name+"'";
		}
		sql+=" GROUP BY jysc,jysinfo,provice";
		return dao.find(sql);
		
	}

}
