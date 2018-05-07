package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * @author gaozhao
 * @date 2018年5月7日
 * @TODO 交易量
 */
public class TradeCountModel extends Model<TradeCountModel>{
	private static final long serialVersionUID = 1L;
	public static final TradeCountModel dao = new TradeCountModel();
	/**
	 * 
	 * @author gaozhao
	 * @date 2018年5月7日
	 * @TODO 获取交易量信息
	 */
	public List<TradeCountModel> getTrade(String bigjys,String jysIds){
		String sql = "select * from insight_pp_tran_number where 1=1";
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and jysc in "+bigjys;
		}
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and jysc in "+jysIds+"";
		}
		sql += " order by jysc";
		return dao.find(sql);
	}
}
