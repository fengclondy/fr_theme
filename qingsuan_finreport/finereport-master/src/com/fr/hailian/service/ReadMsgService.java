package com.fr.hailian.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fr.hailian.util.JDBCUtil;

/***
 * 将未读消息变为已读
 * @author Tom
 *
 */
public class ReadMsgService {
	/***
	 * 将所有未读都变成已读
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void setAllToRead(String userid,String type) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		//获取所有消息id
		String sql = "SELECT fxsj_id FROM hub_fxsj where jysfl = '"+type+"'";
		ResultSet rs = st.executeQuery(sql);
		ArrayList<String> list = new ArrayList<String>();
		while(rs.next()){
			list.add(rs.getString(1));
		}
		rs.close();
		//获取已读消息id
		String sql2 = "SELECT fxsj_id FROM hub_fxsj_read where userid='"+userid+"' and jysfl = '"+type+"'";
		ResultSet rs2 = st.executeQuery(sql2);
		ArrayList<String> list2 = new ArrayList<String>();
		while(rs2.next()){
			list2.add(rs2.getString(1));
		}
		rs.close();
		//判断未读消息id
		ArrayList<String> unReadList = new ArrayList<String>();
		for(int i = 0 ; i < list.size() ; i++){
			if(!list2.contains(list.get(i))){
				unReadList.add(list.get(i));
				System.out.println("未读消息："+list.get(i));
			}
		}
		//生成插入的sql语句
		for(int i = 0 ; i < unReadList.size() ; i++){
			String insertSql = "insert into hub_fxsj_read values('"+userid+"','"+unReadList.get(i)+"','"+type+"')";
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
	
	public static void main(String[] args){
//		try {
//			setAllToRead("tom");
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
