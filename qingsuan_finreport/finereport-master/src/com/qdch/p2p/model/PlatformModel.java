

package com.qdch.p2p.model;

import java.util.List;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement.Else;
import com.jfinal.plugin.activerecord.Model;

import org.apache.commons.lang.StringUtils;



/**
 * @author hanpengda
 * @date 2018年4月9日
 * @TODO 平台总览表
 */
public class PlatformModel extends Model<PlatformModel>{

	private static final long serialVersionUID = 1L;
	public static final PlatformModel dao = new PlatformModel();

	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月10日
	 * @TODO 获取平台总览信息
	 */
	public List<PlatformModel> findOverviewInfo(String jysIds,String ptmc,String cgyh,String zczb,String db,String ptsl){
			
			StringBuffer sql = new StringBuffer("select * from insight_pp_overview where 1=1");
			if(StringUtils.isNotBlank(jysIds)) sql.append(" and jysc in "+jysIds+"");
			if(StringUtils.isNotBlank(ptmc)) sql.append(" and jyscmc = '"+ptmc+"'");		
			//根据银行存管查询
			//0：没有银行存管 1:有银行存管
			if(StringUtils.isNotBlank(cgyh)){
				if (Integer.valueOf(cgyh) == 0) {
					sql.append(" and rtrim(cgyh) = '无存管'");
				}else{
					sql.append(" and rtrim(cgyh) != '无存管'");
				}
			}
			//根据注册资本查询
			//0：1000万以下 1：1000万~5000万 2：5000万以上
			if (StringUtils.isNotBlank(zczb)) {
				if (Integer.valueOf(zczb) == 0) {
					sql.append(" and zczb::numeric < 1000");
				}else if(Integer.valueOf(zczb) == 1){
					sql.append(" and zczb::numeric between 1000 and 5000");
				}else{
					sql.append(" and zczb::numeric > 5000");
				}
			}
			//根据等保查询
			if (StringUtils.isNotBlank(db)) {
				if (Integer.valueOf(db) == 0) {
					sql.append(" and db = '×'");
				}else{
					sql.append(" and db != '×'");
				}
			}
			
			//根据平台实力查询
			if (StringUtils.isNotBlank(ptsl)) {
				sql.append(" and ptsl like '%"+ptsl+"%'");
			}
			sql.append(" order by xxpl desc");
			return dao.find(sql.toString());
		} 
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月10日
	 * @TODO 获取平台总览信息条数
	 */
	public List<PlatformModel> countOverviewInfo(String jysIds,String ptmc,String cgyh,String zczb,String db,String ptsl){
			
			StringBuffer sql = new StringBuffer("select count(0) from insight_pp_overview where 1=1");
			if(StringUtils.isNotBlank(jysIds)) sql.append(" and jysc in "+jysIds+"");
			if(StringUtils.isNotBlank(ptmc)) sql.append(" and jyscmc = '"+ptmc+"'");		
			//根据银行存管查询
			//0：没有银行存管 1:有银行存管
			if(StringUtils.isNotBlank(cgyh)){
				if (Integer.valueOf(cgyh) == 0) {
					sql.append(" and rtrim(cgyh) = '无存管'");
				}else{
					sql.append(" and rtrim(cgyh) != '无存管'");
				}
			}
			//根据注册资本查询
			//0：1000万以下 1：1000万~5000万 2：5000万以上
			if (StringUtils.isNotBlank(zczb)) {
				if (Integer.valueOf(zczb) == 0) {
					sql.append(" and zczb::numeric < 1000");
				}else if(Integer.valueOf(zczb) == 1){
					sql.append(" and zczb::numeric between 1000 and 5000");
				}else{
					sql.append(" and zczb::numeric > 5000");
				}
			}
			//根据等保查询
			if (StringUtils.isNotBlank(db)) {
				if (Integer.valueOf(db) == 0) {
					sql.append(" and db = '×'");
				}else{
					sql.append(" and db != '×'");
				}
			}
			
			//根据平台实力查询
			if (StringUtils.isNotBlank(ptsl)) {
				sql.append(" and ptsl like '%"+ptsl+"%'");
			}
			
			return dao.find(sql.toString());
		}
	
	/**
	 * 
	 * @author hanpengda
	 * @date 2018年4月11日
	 * @TODO 根据平台获取总览信息
	 */
	public List<PlatformModel> getPlatfromByJysc(String jysIds, String jysc){
		String sql = "select * from insight_pp_overview where 1=1";
		if (StringUtils.isNotBlank(jysIds)) {
			sql += " and jysc in "+jysIds+"";
		}
		if (StringUtils.isNotBlank(jysc)) {
			sql += " and jysc= '"+jysc+"'";
		}
		return dao.find(sql);
	}
}

