package com.fr.hailian.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fr.fs.control.UserControl;
import com.fr.hailian.util.JDBCUtil;
import com.fr.hailian.util.KeyUtil;

/***
 * 更新数据库的驳回的状态时添加处理人
 * @author Tom
 *
 */
public class UpdateRejectStatusService {

	/***
	 * 更新状态
	 * 返回值为手机号码
	 * @throws Exception 
	 */
	public String doUpdateStatus(String fxsjId) throws Exception{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		//获取最大时间
		/*String sql = "select max(update_time) from hub_fxsj_audit_new where status = '"+KeyUtil.getKeyValue("REJECT")+"'";
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		String maxTime = rs.getString(1);*/
		//获取处理人id
		String sql = "select report_id from hub_fxsj_audit_new where fxsj_id = '"+fxsjId+"' and clzt = "+KeyUtil.getKeyValue("AUDIT")+" order by update_time desc";
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		String reporter = rs.getString(1);
		rs.close();
		String phoneNum = "";
		try{
			phoneNum = UserControl.getInstance().getByUserName(reporter).getMobile();
		}catch(Exception e){
			e.printStackTrace();
			phoneNum="";
		}
		//更新状态
		String updateSql = "UPDATE hub_fxsj_audit_new SET deal_id = '"+reporter+"' WHERE clzt = '"+KeyUtil.getKeyValue("REJECT")+"' and fxsj_id = '"+fxsjId+"'";
		System.out.println("-----"+updateSql);
		st.execute(updateSql);
		st.close();
		JDBCUtil.closeConnection(conn);
		return phoneNum;
	}
	/***
	 * 根据风险事件id获取状态
	 * @param fxsjId
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public String  getStatusById(String fxsjId) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		//获取最大时间
		/*String sql = "select max(update_time) from hub_fxsj_audit_new where status = '"+KeyUtil.getKeyValue("REJECT")+"'";
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		String maxTime = rs.getString(1);*/
		//获取处理人id
		String sql = "select clzt from hub_fxsj_audit_new where fxsj_id = '"+fxsjId+"' order by update_time desc";
		ResultSet rs = st.executeQuery(sql);
		//状态只获取最新的
		String status = "";
		if(rs.next()){
			status = rs.getString(1);
		}else{
			//若没有数据，置0
			status = "0";
		}
		rs.close();
		st.close();
		JDBCUtil.closeConnection(conn);
		return status;
	}
	/***
	 * 根据风险事件id获取类别（大宗和权益）
	 * @param fxsjId
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public String  getTypeById(String fxsjId) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		//获取最大时间
		/*String sql = "select max(update_time) from hub_fxsj_audit_new where status = '"+KeyUtil.getKeyValue("REJECT")+"'";
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		String maxTime = rs.getString(1);*/
		//获取处理人id
		String sql = "select jysfl from hub_fxsj_audit_new where fxsj_id = '"+fxsjId+"' order by update_time desc";
		ResultSet rs = st.executeQuery(sql);
		//状态只获取最新的
		String type = "";
		if(rs.next()){
			type = rs.getString(1);
		}else{
			//若没有数据，置0
			type = "0";
		}
		rs.close();
		st.close();
		JDBCUtil.closeConnection(conn);
		return type;
	}
}
