package com.qdch.xd.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class MonthlyReportListModel extends Model<MonthlyReportListModel> {
	private static final long serialVersionUID = 1L;
	public static final MonthlyReportListModel dao = new MonthlyReportListModel();
	public static Page<MonthlyReportListModel> getMonthlyReportList(
			String dataScopeByUserName, int pageNum, int pageSize,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append(" from insight_regulatory_report t LEFT JOIN hub_xd_jysc t2 on t.jys=t2.jyscmc where 1=1 ");
		try {
				if(StringUtils.isNotBlank(dataScopeByUserName)){ //用户权限
					String sql=" and t2.jysc in "+ dataScopeByUserName;
				sb.append(sql);
			}
			if(StringUtils.isNotBlank(request.getParameter("jys"))){ //市场名称
				String sql=" and t.jys = '"+ request.getParameter("jys")+"'";
				sb.append(sql);
			}
			if(StringUtils.isNotBlank(request.getParameter("reportType"))){ //报告类型
				String sql=" and t.type = '"+ request.getParameter("reportType")+"'";
				sb.append(sql);
			}
			if(StringUtils.isNotBlank(request.getParameter("upload_time"))){ //上传时间
				String sql=" and t.upload_time = '"+ request.getParameter("upload_time")+"'";
				sb.append(sql);
			}
			



			sb.append(" and t.jysfl='3' order by upload_time desc");


		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.paginate(pageNum,pageSize," select t.order_number,t.title,t.upload_time,t.upload_user,t.jys,t.type,t.file_name,t.jysfl ",sb.toString());
	}

}
