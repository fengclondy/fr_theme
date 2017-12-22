package com.fr.hailian.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.fr.hailian.util.JDBCUtil;

/***
 * 获取机构信息（交易所）
 * @author Tom
 *
 */
public class OrgInfoService {
	
	public HashMap<String, String> getOrgInfo(String jysName) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		String sql = "select t.jys ,t.jysmc ,t1.jysinfo, t2.fvalue usercount, t3.fvalue cdzj,jye,fxsjs from hub_dd_tqs_jysxx t "
				+ "left join hub_ref_jysinfo t1 on t.jys=t1.jys "
				+ "left join (select jys,sum(fvalue) fvalue from insight_user_qty group by jys) t2 on t.jys=t2.jys  "
				+ "left join (select jys,sum(fvalue) fvalue from insight_sendimentary_amount where vday=(select max(vday) from insight_user_qty)group by jys) t3 on t.jys=t3.jys "
				+ "left join (select jys,sum(rt.fvalue) jye from  insight_transaction_amount rt group by jys) t4 on t.jys=t4.jys "
				+ "left join (select jgdm,count(rt.fxsj_id) fxsjs from hub_fxsj rt group by jgdm)  t5 on t.jys=t5.jgdm "
				+ "WHERE t1.jysinfo='"+jysName+"' "
				+ "order by t.jys";
		System.out.println(sql);
		ResultSet rs = st.executeQuery(sql);
		HashMap<String, String> map = new HashMap<String, String>();
		if(rs.next()){
			map.put("userCount", rs.getString("usercount"));
			map.put("cdzj", rs.getString("cdzj"));
			map.put("jye", rs.getString("jye"));
			map.put("success", "1");//有数据为1
		}else{
			map.put("success", "0");//没有数据为0
		}
		rs.close();
		st.close();
		JDBCUtil.closeConnection(conn);
		return map;
	}

}
