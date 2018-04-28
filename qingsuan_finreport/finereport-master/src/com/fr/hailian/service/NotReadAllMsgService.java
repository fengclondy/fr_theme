package com.fr.hailian.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fr.hailian.util.JDBCUtil;
import com.fr.hailian.util.KeyUtil;

/***
 * 获取总未读信息数
 * @author Tom
 *
 */
public class NotReadAllMsgService {
	
	/***
	 * 获取总未读的条数
	 * 2017-12-28 17:55:39 更改为只获取对应角色人需要处理的信息数
	 * @param key
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public long getUnReadAllMsgCount(String key,String type,String sqls) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		String sqlin = KeyUtil.getKeyValue(key);
		String sql = "select count(0) from hub_fxsj WHERE clzt in ("+sqlin+") and jysfl = '"+type+"'  ";
		if(!"()".equals(sqls)){
			sql+=" and jgdm in "+sqls;
		}
		//String sql2 = "select count(0) from hub_fxsj_audit_new_read WHERE clzt in ("+sqlin+") and type = '"+type+"'";
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
