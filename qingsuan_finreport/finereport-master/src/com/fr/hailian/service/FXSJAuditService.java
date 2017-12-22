package com.fr.hailian.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.fr.hailian.util.JDBCUtil;

/***
 * 风险事件审核
 * @author Tom
 *
 */
public class FXSJAuditService {
	/***
	 * 审核
	 * @param id
	 * @param fxsjId
	 * @param reportId
	 * @param dealId
	 * @param insertTime
	 * @param status
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void doAudit(String id ,String fxsjId,String reportId,String dealId,String insertTime ,String status) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		String sql = "insert into hub_fxsj_audit values('"+id+"','"+fxsjId+"','"+reportId+"','"+dealId+"','"+insertTime+"','"+status+"')";
		st.execute(sql);
		st.close();
		JDBCUtil.closeConnection(conn);
	}
	/***
	 * 更新风险事件表的时间状态
	 * @param fxsjId
	 * @param status
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void updateFXSJStatus(String fxsjId,String status) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		String sql = "UPDATE hub_fxsj SET clzt ='"+status+"' WHERE fxsj_id = '"+fxsjId+"'";
		st.executeUpdate(sql);
		st.close();
		JDBCUtil.closeConnection(conn);
	}
	/***
	 * 审核同时更新数据，即同时执行插入和更新
	 * @param id
	 * @param fxsjId
	 * @param reportId
	 * @param dealId
	 * @param insertTime
	 * @param status
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void doAuditInsertAndUpdate(String id ,String fxsjId,String reportId,String dealId,String insertTime ,String status) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		String insertSql = "insert into hub_fxsj_audit values('"+id+"','"+fxsjId+"','"+reportId+"','"+dealId+"','"+insertTime+"','"+status+"')";
		String updateSql = "UPDATE hub_fxsj SET clzt ='"+status+"' WHERE fxsj_id = '"+fxsjId+"'";
		st.execute(insertSql);
		st.executeUpdate(updateSql);
		st.close();
		JDBCUtil.closeConnection(conn);
	}

}
