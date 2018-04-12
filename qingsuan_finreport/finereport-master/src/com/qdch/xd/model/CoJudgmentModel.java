package com.qdch.xd.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fr.function.CODE;
import com.jfinal.plugin.activerecord.Model;
/**
 * 法院判决文书
 * @author lixiaoyi
 * @date 2018年4月10日
 * @TODO
 */
public class CoJudgmentModel extends Model<CoJudgmentModel> {
	private static final long serialVersionUID = 1L;
	public static final CoJudgmentModel dao = new CoJudgmentModel();
	/**
	 * 法律诉讼
	 * @author lixiaoyi
	 * @date 2018年4月10日 下午5:13:35
	 * @TODO
	 */
	public List<CoJudgmentModel> getJudgment(String name){
		String sql="SELECT T.trial_date,T.title,T.case_type,T.doc_type,T.case_no "
				+ "FROM hub_commerce_judgment T LEFT JOIN hub_commerce_enterprise n ";
		if(StringUtils.isNotBlank(name)){
			sql+="ON  n. NAME = '"+ name+"' WHERE T.TITLE like '%"+name+"%' or T.CLERK_DOC like '%"+name+"%'";
		}
				
		return dao.find(sql);
	}
}
