package com.fr.hailian.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.fr.hailian.util.HanlpUtil;
import com.fr.hailian.util.JDBCUtil;

/***
 * 分词
 * @author Tom
 *
 */
public class SplitWordsService {
	/***
	 * 获取获取要分词的数据
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<HashMap<String, String>> getUnSplitMsg() throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		//分词以1000条为单位进行
		String sql = "SELECT info_id,content FROM hub_commerce_meiya_sentiment_news_bak WHERE split_status='0' order by info_id limit 10";
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
	 * 获取全部要分词的数据
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<HashMap<String, String>> getAllUnSplitMsg() throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		//分词以100条为单位进行
		String sql = "SELECT info_id,summary FROM hub_commerce_meiya_sentiment_news WHERE split_status='0'";
		ResultSet rs = st.executeQuery(sql);
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		while(rs.next()){
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("id", rs.getString("info_id"));
			map.put("msg", rs.getString("summary"));
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
		String sql = "UPDATE hub_commerce_meiya_sentiment_news_bak set split_key_words = '"+keyWords+"' , split_status='1',split_words='"+words+"' WHERE info_id='"+infoId+"'";
		int result = st.executeUpdate(sql);
		st.close();
		JDBCUtil.closeConnection(conn);
		return result;
	}
	/***
	 * 获取未分词的条数
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public long getUnSplitNum() throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		String sql = "SELECT count(0) FROM hub_commerce_meiya_sentiment_news_bak WHERE split_status='0'";
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		long result = rs.getLong(1);
		rs.close();
		st.close();
		JDBCUtil.closeConnection(conn);
		return result;
	}
	/***
	 * 进行分词
	 * @return
	 */
	public String doSplitWords(){
		try {
			//如果有未分词的词条，就继续分词
			long splitNum = getUnSplitNum();
			//long splitNum = 1L;
			long count = 1;
			while(splitNum > 0){
				ArrayList<HashMap<String, String>> list = getUnSplitMsg();
				for(int i = 0 ; i < list.size() ; i ++){
					//分词
					//System.out.println(list.get(i).get("msg"));
					//HashMap<String, String> map = NLPIRUtil.splitWords(msgWords);
					HashMap<String, String> map = HanlpUtil.doSplitWords(list.get(i).get("msg"));
					//过滤乱码字符
					String words = map.get("keyWords");
					words = words.replaceAll("�", "");
					//去掉空的字符
					words = words.replaceAll(" ", "");
					words = words.replaceAll("##", "#");
					//去除第一个#
					if(!words.isEmpty()){
						if(words.substring(0, 1).equals("#")){
							words=words.substring(1, words.length());
						}
					}
					System.out.println(words);
					//更新
					updateSplitMsg(list.get(i).get("id"),map.get("words"),words);
					System.out.println("第"+(count++)+"条,"+list.get(i).get("id")+"更新成功");
				}
				//System.out.println("100条分词成功");
				splitNum = getUnSplitNum();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/***
	 * 通过多任务更新
	 * @return
	 */
	public String doSplitWordsByMultitask(){
		try {
			ArrayList<HashMap<String, String>> list = getAllUnSplitMsg();
			//每页个数
			int size = 1000;
			//页数
			int page = list.size()/size;			
			for(int i= 1; i <= page ; i++){
				//Thread rthread = new Thread(new UpdateTask(list.subList((page-1)*size, page*size)));
				Thread rthread = new Thread(new UpdateTask(splitList(list,i,size)));
				rthread.start();
				//必须休眠 不然线程太多会报错
				Thread.sleep(2000);
			}
			//分页如果是整数，则不执行，否则执行其他的
			/*if(list.size()%size!=0){
				Thread rthread = new Thread(new UpdateTask(list.subList(page*size, list.size())));
				rthread.start();
				Thread.sleep(2000);
			}*/
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/***
	 * 通过多任务更新
	 * @return
	 */
	public String doSplitWordsByMultitaskNew(){
		try {		
			for(int i= 0; i < 10 ; i++){
				//Thread rthread = new Thread(new UpdateTask(list.subList((page-1)*size, page*size)));
				Thread rthread = new Thread(new MultiTask(i+""));
				rthread.start();
				//必须休眠 不然线程太多会报错
				Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/***
	 * 分配list
	 * @param list
	 * @param page
	 * @param size
	 * @return
	 */
	public ArrayList<HashMap<String, String>> splitList(ArrayList<HashMap<String, String>> list ,int page ,int size){
		ArrayList<HashMap<String, String>> tmp = new ArrayList<HashMap<String,String>>();
		for(int i = (page-1)*size ; i < page*size ; i ++){
			tmp.add(list.get(i));
		}
		return tmp;
	}
}
