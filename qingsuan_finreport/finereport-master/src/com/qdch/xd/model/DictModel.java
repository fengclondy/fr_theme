package com.qdch.xd.model;

import com.fr.stable.StringUtils;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

/**
 * 
 * @todo   字典表 hub_comm_param
 * @time   2018年4月2日14:59:19
 * @author wf
 */
public class DictModel extends Model<DictModel>{
	private static final long serialVersionUID = 1L;
	public static final DictModel dao = new DictModel();


	public List<DictModel> getLabel(String note){
		StringBuffer sb = new StringBuffer();
		sb.append("select fvalue from  hub_comm_param where 1=1 ");
		if(StringUtils.isNotBlank(note)){
			sb.append(note);
		}
		sb.append("ORDER BY param_type,fkey");
		return dao.find(sb.toString());
	}






}
