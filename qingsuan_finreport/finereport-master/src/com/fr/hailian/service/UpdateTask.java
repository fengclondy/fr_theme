package com.fr.hailian.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fr.hailian.util.JDBCUtil;
import com.fr.hailian.util.NLPIRUtil;

/***
 * 使用多线程进行更新
 * @author Tom
 *
 */
public class UpdateTask implements Runnable{
	
	ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();

	public UpdateTask(ArrayList<HashMap<String, String>> list){
		this.list = list;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.err.println(list.size());
		//NLPIRUtil split = new NLPIRUtil();
		for(int i = 0 ; i < list.size() ; i ++){
			HashMap<String, String> map = NLPIRUtil.splitWords(list.get(i).get("msg"));
			map.put("id", list.get(i).get("id"));
			list.add(map);
			System.out.println("add"+list.get(i).get("id"));
			//System.out.println("msg"+list.get(i).get("msg"));
		}
		try {
			updateSplitMsg(list);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 更新分词数据
	 * @param list2
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int updateSplitMsg(List<HashMap<String, String>> list2) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		//更新分词，状态置为1
		for(int i = 0 ; i < list2.size() ; i++){
			String sql = "UPDATE hub_commerce_meiya_sentiment_news set split_key_words = '"+list2.get(i).get("keyWords")+"' , split_status='1',split_words='"+list2.get(i).get("words")+"' WHERE info_id='"+list2.get(i).get("id")+"'";
			st.addBatch(sql);
		}
		st.executeBatch();
		st.close();
		JDBCUtil.closeConnection(conn);
		return 0;
	}

}
