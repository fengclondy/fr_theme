package com.qdch.xd.model;

import com.fr.hailian.util.DateUtil;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 
 * @todo   风险事件
 * @time   2018年4月2日14:59:19
 * @author wf
 */
public class RiskEventModel extends Model<RiskEventModel>{
	private static final long serialVersionUID = 1L;
	public static final RiskEventModel dao = new RiskEventModel();


	/**
	 * 风险事件列表
	 * @param num
	 * @param size
	 * @return
	 */
	public Page<RiskEventModel> getRiskEvent(String datascope,int num, int size, HttpServletRequest request){
		StringBuffer sb = new StringBuffer();
		sb.append(" from hub_fxsj a where 1=1 ");
		sb.append(" and jysfl='3' ");//交易所分类 3--小贷 4--p2p
//		String sql = " ";khmc
		if(StringUtils.isNotBlank(request.getParameter("fxsj_id"))){ //风险事件id
			sb.append(" and fxsj_id  like '%").append(request.getParameter("fxsj_id")).append("%'");
		}
		if(StringUtils.isNotBlank(request.getParameter("cust_id"))){ //客户号
			sb.append(" and cust_id  like '%").append(request.getParameter("cust_id")).append("%'");
		}
		if(StringUtils.isNotBlank(request.getParameter("clzt"))){ //处理状态
			sb.append(" and clzt  like '%").append(request.getParameter("clzt")).append("%'");
		}
		if(StringUtils.isNotBlank(request.getParameter("fxlb"))){ //风险类别
			sb.append(" and fxlb  like '%").append(request.getParameter("fxlb")).append("%'");
		}
		if(StringUtils.isNotBlank(request.getParameter("fxzb"))){ //风险指标
			sb.append(" and fxzb  like '%").append(request.getParameter("fxzb")).append("%'");
		}
		if(StringUtils.isNotBlank(request.getParameter("ywbm"))){ //客户号
			sb.append(" and ywbm  like '%").append(request.getParameter("ywbm")).append("%'");
		}
		if(StringUtils.isNotBlank(request.getParameter("alarmTimeStart"))){ //报警时间起
			sb.append(" and ywbm  like '%").append(request.getParameter("ywbm")).append("%'");
		}
		if(StringUtils.isNotBlank(request.getParameter("khmc"))){ //客户名称
			sb.append(" and khmc  like '%").append(request.getParameter("khmc")).append("%'");
		}
		if(StringUtils.isNotBlank(request.getParameter("jgmc"))){ //机构名称/市场名称
			sb.append(" and jgmc  like '%").append(request.getParameter("jgmc")).append("%'");
		}
		if(StringUtils.isNotBlank(request.getParameter("ywbm"))){ //业务编码
			sb.append(" and ywbm  like '%").append(request.getParameter("ywbm")).append("%'");
		}


		return dao.paginate(num,size," select * ",sb.toString());

	}

}
