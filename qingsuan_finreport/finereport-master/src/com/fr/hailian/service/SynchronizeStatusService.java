package com.fr.hailian.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.fr.hailian.util.JDBCUtil;

/***
 * 同步状态
 * 把审核历史的表中的状态同步到风险事件表中
 * @author Tom
 *
 */
public class SynchronizeStatusService {
	
	/***
	 * 通过风险事件获取要当前事件的状态
	 * @param fxsjId
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public HashMap<String, String> getDataById(String fxsjId) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		String sql = "select clzt,report_id,deal_id,update_time,bz from hub_fxsj_audit_new where fxsj_id = '"+fxsjId+"' order by update_time desc";
		ResultSet rs = st.executeQuery(sql);
		//状态只获取最新的
		HashMap<String, String> map = new HashMap<String, String>();
		if(rs.next()){
			map.put("status", rs.getString(1));
			map.put("report", rs.getString(2));
			map.put("deal", rs.getString(3));
			map.put("updateTime", rs.getString(4));
			map.put("note", rs.getString(5));
			map.put("success", "1");
		}else{
			//若没有数据，置空
			map.put("status", "");
			map.put("report", "");
			map.put("deal", "");
			map.put("updateTime", "");
			map.put("note", "");
			map.put("success", "0");
		}
		rs.close();
		st.close();
		JDBCUtil.closeConnection(conn);
		return map;
	}
	
	/***
	 * 同步状态
	 * @param param
	 * @param fxsjId
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void synchronizeStatus(HashMap<String, String> param,String fxsjId) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		String deal_id = "";
		if("".equals(param.get("deal"))||null==param.get("deal")||"null".equals(param.get("deal"))){
			deal_id = "";
		}else{
			deal_id = param.get("deal");
		}
		String sql = "UPDATE hub_fxsj SET clzt='"+param.get("status")+"',report_id='"+param.get("report")+"', deal_id='"+deal_id+"',update_time='"+param.get("updateTime")+"',bz='"+param.get("note")+"' WHERE fxsj_id='"+fxsjId+"'";
		System.out.println("sql-----"+sql);
		st.executeUpdate(sql);
		st.close();
		JDBCUtil.closeConnection(conn);
	}

}
