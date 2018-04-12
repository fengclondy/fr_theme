package com.qdch.xd.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fr.hailian.util.DateUtil;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.qdch.model.DemoModel;

//@TableBind(tableName="hub_commerce_ref_jys")
/**
 * 
 * @author doush
 * @date 2018年3月26日
 * @TODO 获取交易市场
 */
public class JyscModel extends Model<JyscModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final JyscModel dao = new JyscModel();
	
	public List<JyscModel> getJysc(String dataSql){	
		String sql="select * from hub_xd_jysc where 1=1 ";
		
		if(StringUtils.isNotBlank(dataSql)){
			sql+=" jysc in"+ dataSql+"";
		}
		return dao.find(sql);
	}

	
	
	
}
