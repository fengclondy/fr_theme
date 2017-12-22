package com.fr.hailian.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import com.fr.hailian.util.JDBCUtil;
import com.fr.hailian.util.KeyUtil;

/***
 * 将全部未读消息变已读
 * @author Tom
 *
 */
public class ReadAllMsgService {
	/***
	 * 将所有未读都变成已读
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void setAllToRead(String key,String type) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		//获取所有消息id
		String sqlin = KeyUtil.getKeyValue(key);
		String sql = "select audit_id,clzt from hub_fxsj_audit_new WHERE clzt in ("+sqlin+") and jysfl='"+type+"'";
		ResultSet rs = st.executeQuery(sql);
		ArrayList<String> list = new ArrayList<String>();
		//存储状态
		ArrayList<String> status = new ArrayList<String>();
		while(rs.next()){
			list.add(rs.getString(1));
			status.add(rs.getString(2));
		}
		rs.close();
		//获取已读消息id
		String sql2 = "select audit_id from hub_fxsj_audit_new_read WHERE clzt in ("+sqlin+") and type ='"+type+"'";
		ResultSet rs2 = st.executeQuery(sql2);
		ArrayList<String> list2 = new ArrayList<String>();
		while(rs2.next()){
			list2.add(rs2.getString(1));
		}
		rs.close();
		//判断未读消息id
		ArrayList<String> unReadList = new ArrayList<String>();
		ArrayList<String> unReadStatus = new ArrayList<String>();
		for(int i = 0 ; i < list.size() ; i++){
			if(!list2.contains(list.get(i))){
				unReadList.add(list.get(i));
				unReadStatus.add(status.get(i));
				System.out.println("未读消息："+list.get(i)+"未读消息状态："+status.get(i));
			}
		}
		//生成插入的sql语句
		for(int i = 0 ; i < unReadList.size() ; i++){
			String id = UUID.randomUUID().toString().trim()
					.replaceAll("-", "");
			String insertSql = "insert into hub_fxsj_audit_new_read values('"+id+"','"+unReadList.get(i)+"','"+unReadStatus.get(i)+"','"+type+"')";
			st.addBatch(insertSql);
		}
		if(unReadList.size()>0){
			st.executeBatch();
			System.out.println("插入成功");
		}else{
			System.out.println("无插入数据");
		}
		st.close();
		JDBCUtil.closeConnection(conn);
	}

}
