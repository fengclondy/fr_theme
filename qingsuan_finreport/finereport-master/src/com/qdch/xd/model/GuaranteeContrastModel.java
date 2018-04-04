package com.qdch.xd.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * 
 * @todo   担保合同 hub_xd_cont_assu
 * @time   2018年4月2日14:59:19
 * @author wf
 */
public class GuaranteeContrastModel extends Model<GuaranteeContrastModel>{
	private static final long serialVersionUID = 1L;
	public static final GuaranteeContrastModel dao = new GuaranteeContrastModel();

	public Page<GuaranteeContrastModel> getPage(int num, int size){
		String sql = "select * ";
		StringBuffer sb = new StringBuffer();
		sb.append(" from hub_xd_cont_assu");
//		return dao.paginate();
		return dao.paginate(num,size," select * ",sb.toString());

	}


}
