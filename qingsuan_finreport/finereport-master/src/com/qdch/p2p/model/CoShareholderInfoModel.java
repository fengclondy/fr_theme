package com.qdch.p2p.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
/**
 * 
 * @author lixiaoyi
 * @date 2018年4月12日下午3:21:45
 * @TODO
 */
public class CoShareholderInfoModel extends Model<CoShareholderInfoModel> {
	private static final long serialVersionUID = 1L;
	public static final CoShareholderInfoModel dao=new CoShareholderInfoModel();
 /**
  * 股东信息
  * @author lixiaoyi
  * @date 2018年4月12日 下午3:39:10
  * @TODO
  */
    public List<CoShareholderInfoModel> getShareholder(String name){
		String sql ="select * from hub_static_shareholder_info ";
		if(StringUtils.isNotBlank(name)){
			sql+=" where company_name='"+name+"'";
		} 
		return dao.find(sql);
	}

    /**
     * 股权结构
     * @author lixiaoyi
     * @date 2018年4月20日 下午4:27:54
     * @TODO
     */
    public CoShareholderInfoModel getHolder(String name){
    	String sql="SELECT distinct t.investor,t.company_name from hub_static_shareholder_info t"; 
    	if(StringUtils.isNotBlank(name)){
			sql+=" WHERE t.company_name='"+name+"'";
		} 
          sql+="limit 1";
    	return dao.findFirst(sql);
    }
    /**
     * 对外投资
     * @author lixiaoyi
     * @date 2018年4月21日 上午11:39:09
     * @TODO
     */
    public  List<CoShareholderInfoModel> getInvers(String name){
    	String sql="SELECT T.company_name,m.legal_person,m.register_money,T.subcribe_money,T.subcribe_type,"
                  +"T.invest_rate,m.establish_date,m.company_status FROM hub_static_shareholder_info T"
             +" LEFT JOIN hub_static_company_info m ON m.company_name = T.company_name ";
         
          if(StringUtils.isNotBlank(name)){
  			sql+="  WHERE T.investor ='"+name+"'";
  		} 
          return dao.find(sql);
    }
}
