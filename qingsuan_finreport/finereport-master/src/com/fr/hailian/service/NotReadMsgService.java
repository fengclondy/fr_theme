package com.fr.hailian.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fr.hailian.util.JDBCUtil;

/***
 * 处理未读消息
 * @author Tom
 *
 */
public class NotReadMsgService {
	/***
	 * 获取总信息数
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public long getAllMsgCount() throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		String sql = "select count(0) from hub_fxsj";
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		long result = rs.getLong(1);
		rs.close();
		st.close();
		JDBCUtil.closeConnection(conn);
		return result;
	}
	/***
	 * 获取已读的条数
	 * @param userid
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public long getReadMsgCount(String userid) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		String sql = "select count(0) from hub_fxsj_read where userid="+userid;
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		long result = rs.getLong(1);
		rs.close();
		st.close();
		JDBCUtil.closeConnection(conn);
		return result;
	}
	/***
	 * 获取未读的条数
	 * 
	 * 2017-12-28 17:40:56 变更为只根据状态查询风险事件数
	 * @param userid
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public long getUnReadMsgCount(String userid,String type,String sqlin) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		String sql = "select count(0) from hub_fxsj where clzt='未处理' and jysfl = '"+type+"' and jgdm in "+sqlin;
		//String sql2 = "select count(0) from hub_fxsj_read where userid='"+userid+"' and jysfl='"+type+"'";
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		long all = rs.getLong(1);
		rs.close();
		/*ResultSet rs2 = st.executeQuery(sql2);
		rs2.next();
		long read = rs2.getLong(1);
		rs2.close();
		long unRead = all - read;*/
		st.close();
		JDBCUtil.closeConnection(conn);
		//return (unRead<0)?0L:unRead;
		return all;
	}
	

}
