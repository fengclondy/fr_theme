package com.qdch.xd.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.qdch.util.FileUpLoad;

//@TableBind(tableName="hub_commerce_ref_jys")
/**
 * 获取监管月报
* @author doushuihai  
* @date 2018年4月3日下午10:09:12  
* @TODO
 */
public class MonthlyReportModel extends Model<MonthlyReportModel>{

	private static final long serialVersionUID = 1L;
	
	public static final MonthlyReportModel dao = new MonthlyReportModel();
	
	public List<MonthlyReportModel> getMonthlyReport(String bigjys,String jys,String reportType,String date){
		String sql="select t.order_number,t.title,t.upload_time,t.upload_user,t.jys,t.type,t.file_name,t.jysfl "
				+ "from insight_regulatory_report t LEFT JOIN hub_xd_jysc t2 on t.jys=t2.jyscmc where 1=1  ";
		if(StringUtils.isNotBlank(bigjys)){
			sql+=" and t2.jysc in "+ bigjys;
		}
		if(StringUtils.isNotBlank(jys)){
			sql+=" and t.jys = '"+ jys+"'";
		}
		if(StringUtils.isNotBlank(reportType)){
			sql+=" and t.type = '"+ reportType+"'";
		}
		if(StringUtils.isNotBlank(date)){
			sql+=" and t.upload_time = '"+ date+"'";
		}
		sql+=" order by order_number,title";
		
		return dao.find(sql);
	}
	//文件上传
/*	public List<MonthlyReportModel> getFileUpLoad(String datasql){
		String sql=" ";
		
		if(StringUtils.isNotBlank(datasql)){
			sql+=" and jysc in "+datasql;
		}
		if(StringUtils.isNotBlank(name)){
			sql+=" and jysc in "+datasql;
		}
		
		sql+=" and jysc in "+datasql;
	return null;
}
*/

	
	
	
	
}
