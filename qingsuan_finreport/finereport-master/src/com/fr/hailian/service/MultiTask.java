package com.fr.hailian.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fr.hailian.util.HanlpUtil;
import com.fr.hailian.util.JDBCUtil;

/***
 * 新使用多线程进行更新
 * @author Tom
 *
 */
public class MultiTask implements Runnable{
	
	private String type;

	public MultiTask(String type){
		this.type = type;
		System.out.println("Task"+type+"初始化");
	}
	@Override
	public void run() {
		try {
			//如果有未分词的词条，就继续分词
			long splitNum = getUnSplitNum(type);
			//long splitNum = 1L;
			//long count = 1;
			while(splitNum > 0){
				ArrayList<HashMap<String, String>> list = getUnSplitMsg(type);
				List<HashMap<String, String>> sqls = new ArrayList<HashMap<String,String>>();
				for(int i = 0 ; i < list.size() ; i ++){
					HashMap<String, String> map = new HashMap<String, String>();
					//判断是否为空
					if(list.get(i).get("msg")==null||list.get(i).get("msg")=="null"||list.get(i).get("msg")==null){
						System.out.println("NoData");
						map = HanlpUtil.doSplitWords("");
					}else{
						map = HanlpUtil.doSplitWords(list.get(i).get("msg"));
					}
					String words = map.get("keyWords");
					System.out.println(i+"---"+list.get(i).get("id")+"---"+words);
					map.put("id", list.get(i).get("id"));
					sqls.add(map);
					//更新
					//updateSplitMsg(list.get(i).get("id"),map.get("words"),words);
					//System.out.println("第"+(count++)+"条,"+list.get(i).get("id")+"更新成功");
				}
				updateSplitMsg(sqls);
				System.out.println("Task"+type+"100条更新成功");
				//System.out.println("100条分词成功");
				splitNum = getUnSplitNum(type);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/***
	 * 根据类型获取未分词的条数
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public long getUnSplitNum(String type) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		String sql = "SELECT count(0) FROM hub_commerce_meiya_sentiment_news WHERE split_status='0' and to_number(info_id,'99999999999')%10="+type;
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		long result = rs.getLong(1);
		rs.close();
		st.close();
		JDBCUtil.closeConnection(conn);
		return result;
	}
	/***
	 * 根据类型获取获取要分词的数据
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<HashMap<String, String>> getUnSplitMsg(String type) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		//分词以1000条为单位进行
		String sql = "SELECT info_id,content FROM hub_commerce_meiya_sentiment_news WHERE split_status='0' and to_number(info_id,'99999999999')%10="+type+" order by info_id limit 100";
		//String sql = "SELECT info_id,summary FROM hub_commerce_meiya_sentiment_news WHERE split_status='1' and create_time='20180115' order by info_id";
		ResultSet rs = st.executeQuery(sql);
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		while(rs.next()){
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("id", rs.getString("info_id"));
			map.put("msg", rs.getString("content"));
			list.add(map);
		}
		rs.close();
		st.close();
		JDBCUtil.closeConnection(conn);
		return list;
	}
	/***
	 * 更新分词数据
	 * @param infoId
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int updateSplitMsg(String infoId,String words,String keyWords) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		//更新分词，状态置为1
		String sql = "UPDATE hub_commerce_meiya_sentiment_news set split_key_words = '"+keyWords+"' , split_status='1',split_words='"+words+"' WHERE info_id='"+infoId+"'";
		int result = st.executeUpdate(sql);
		st.close();
		JDBCUtil.closeConnection(conn);
		return result;
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
