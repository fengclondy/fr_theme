package com.qdch.xd.model;

import com.fr.hailian.util.DateUtil;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

/**
 * 
 * @todo   风险事件 hub_fxsj
 * @time   2018年4月2日14:59:19
 * @author wf
 */
public class RiskEventModel extends Model<RiskEventModel>{
	private static final long serialVersionUID = 1L;
	public static final RiskEventModel dao = new RiskEventModel();


	/**
	 * 风险事件列表
	 * @param num  第几页
	 * @param size 一页多少条
	 * @return
	 */
	public Page<RiskEventModel> getRiskEvent(String datascope,int num, int size, HttpServletRequest request){
		StringBuffer sb = new StringBuffer();
		sb.append(" from hub_fxsj a where 1=1 ");
		sb.append(" and jysfl='3' ");//交易所分类 3--小贷 4--p2p
//		String sql = " ";khmc
		try {
			if(StringUtils.isNotBlank(request.getParameter("fxsj_id"))){ //风险事件id
				sb.append(" and fxsj_id  like '%").append(request.getParameter("fxsj_id")).append("%'");
			}
			if(StringUtils.isNotBlank(request.getParameter("cust_id"))){ //客户号
				sb.append(" and cust_id  like '%").append(request.getParameter("cust_id")).append("%'");
			}
			if(StringUtils.isNotBlank(request.getParameter("clzt"))){ //处理状态
				sb.append(" and clzt  like '%").append(decode(request.getParameter("clzt"))).append("%'");
			}
			if(StringUtils.isNotBlank(request.getParameter("fxlb"))){ //风险类别
				sb.append(" and fxlb  like '%").append(decode(request.getParameter("fxlb"))).append("%'");
			}
			if(StringUtils.isNotBlank(request.getParameter("fxzb"))){ //风险指标
				sb.append(" and fxzb  like '%").append(decode(request.getParameter("fxzb"))).append("%'");
			}
			if(StringUtils.isNotBlank(request.getParameter("ywbm"))){ //客户号
				sb.append(" and ywbm  like '%").append(request.getParameter("ywbm")).append("%'");
			}
			if(StringUtils.isNotBlank(request.getParameter("alarmTimeStart"))
					&&StringUtils.isNotBlank(request.getParameter("alarmTimeEnd"))){ //报警时间
				sb.append(" and bjsj  BETWEEN").append(request.getParameter("alarmTimeStart"))
						.append(" and ").append(request.getParameter("alarmTimeEnd"));
			}
			if(StringUtils.isNotBlank(request.getParameter("khmc"))){ //客户名称
				sb.append(" and khmc  like '%").append(decode(request.getParameter("khmc"))).append("%'");
			}
			if(StringUtils.isNotBlank(request.getParameter("jgmc"))){ //机构名称/市场名称
				sb.append(" and jgmc  like '%").append(decode(request.getParameter("jgmc"))).append("%'");
			}
			if(StringUtils.isNotBlank(request.getParameter("ywbm"))){ //业务编码
				sb.append(" and ywbm  like '%").append(request.getParameter("ywbm")).append("%'");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}


		return dao.paginate(num,size," select * ",sb.toString());
	}


	public List<RiskEventModel> getRiskEventList(HttpServletRequest request){
		StringBuffer sb = new StringBuffer();
		sb.append(" from hub_fxsj a where 1=1 ");
		sb.append(" and jysfl='3' ");//交易所分类 3--小贷 4--p2p
//		String sql = " ";khmc
		try {
			if(StringUtils.isNotBlank(request.getParameter("fxsj_id"))){ //风险事件id
				sb.append(" and fxsj_id  like '%").append(request.getParameter("fxsj_id")).append("%'");
			}
			if(StringUtils.isNotBlank(request.getParameter("cust_id"))){ //客户号
				sb.append(" and cust_id  like '%").append(request.getParameter("cust_id")).append("%'");
			}
			if(StringUtils.isNotBlank(request.getParameter("clzt"))){ //处理状态
				sb.append(" and clzt  like '%").append(decode(request.getParameter("clzt"))).append("%'");
			}
			if(StringUtils.isNotBlank(request.getParameter("fxlb"))){ //风险类别
				sb.append(" and fxlb  like '%").append(decode(request.getParameter("fxlb"))).append("%'");
			}
			if(StringUtils.isNotBlank(request.getParameter("fxzb"))){ //风险指标
				sb.append(" and fxzb  like '%").append(decode(request.getParameter("fxzb"))).append("%'");
			}
			if(StringUtils.isNotBlank(request.getParameter("ywbm"))){ //客户号
				sb.append(" and ywbm  like '%").append(request.getParameter("ywbm")).append("%'");
			}
			if(StringUtils.isNotBlank(request.getParameter("alarmTimeStart"))
					&&StringUtils.isNotBlank(request.getParameter("alarmTimeEnd"))){ //报警时间
				sb.append(" and bjsj  BETWEEN").append(request.getParameter("alarmTimeStart"))
						.append(" and ").append(request.getParameter("alarmTimeEnd"));
			}
			if(StringUtils.isNotBlank(request.getParameter("khmc"))){ //客户名称
				sb.append(" and khmc  like '%").append(decode(request.getParameter("khmc"))).append("%'");
			}
			if(StringUtils.isNotBlank(request.getParameter("jgmc"))){ //机构名称/市场名称
				sb.append(" and jgmc  like '%").append(decode(request.getParameter("jgmc"))).append("%'");
			}
			if(StringUtils.isNotBlank(request.getParameter("ywbm"))){ //业务编码
				sb.append(" and ywbm  like '%").append(request.getParameter("ywbm")).append("%'");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}


		return dao.find(" select * "+sb.toString());
	}

	private String decode(String str) throws UnsupportedEncodingException {
		return URLDecoder.decode(str,"UTF-8");
	}

	@Override
	public RiskEventModel findById(Object idValue) {
		String sql = "select * from hub_fxsj where fxsj_id="+idValue;
		return dao.findFirst(sql);
	}
}
