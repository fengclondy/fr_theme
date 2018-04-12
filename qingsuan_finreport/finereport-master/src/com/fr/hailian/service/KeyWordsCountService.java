package com.fr.hailian.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.fr.hailian.util.JDBCUtil;

/***
 * 舆情信息关键字统计
 * @author Tom
 *
 */
public class KeyWordsCountService {
	
	/**
	 *匹配字符出现的次数(存在重复字段的问题)
	 * @param a
	 *            被匹配的长字符串
	 * @param b
	 *            匹配的短字符串
	 * @return 匹配次数
	 */
	public int hit(String a, String b) {
		if (a.length() < b.length()) {
			return 0;
		}
		char[] a_t = a.toCharArray();
		int count = 0;

		for (int i = 0; i < a.length() - b.length(); i++) {
			StringBuffer buffer = new StringBuffer();
			for (int j = 0; j < b.length(); j++) {
				buffer.append(a_t[i + j]);
			}
			if (buffer.toString().equals(b)) {
				count++;
			}
		}

		return count;
	}
	/***
	 * 新的统计词频的方法
	 * @return
	 */
	public int hitNew(String str,ArrayList<String> list){
		int count = 0;
		for(int i = 0 ; i < list.size() ; i ++){
			if(str.equals(list.get(i))){
				count++;
			}
		}
		return count;
	}
	/***
	 * 从list中删除已经统计过的关键词
	 * @return
	 */
	public ArrayList<String> slimList(String str,ArrayList<String> list){
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 0 ; i < list.size() ; i ++){
			if(!str.equals(list.get(i))){
				result.add(list.get(i));
			}
		}
		return result;
	}
	/***
	 * 获取关键词的数据的数据
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public String getSplitKeywords(String id,String createTime) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		String sql = "SELECT split_key_words FROM hub_commerce_meiya_sentiment_news WHERE enterprise_id='"+id+"' and create_time='"+createTime+"'";
		ResultSet rs = st.executeQuery(sql);
		String result = "";
		while(rs.next()){
			result += rs.getString("split_key_words");
		}
		rs.close();
		st.close();
		JDBCUtil.closeConnection(conn);
		return result;
	}
	/***
	 * 获取关键词的数据的数据不根据日期，查询全部
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public String getSplitKeywordsNodate(String id) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		String sql = "SELECT split_key_words FROM hub_commerce_meiya_sentiment_news WHERE enterprise_id='"+id+"'";
		ResultSet rs = st.executeQuery(sql);
		String result = "";
		while(rs.next()){
			result += rs.getString("split_key_words");
		}
		rs.close();
		st.close();
		JDBCUtil.closeConnection(conn);
		return result;
	}
	/***
	 * 获取需要统计词频的交易所信息
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<HashMap<String, String>> getJYSInfo() throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		//针对单个交易所统计词频
		//String sql = "SELECT * FROM hub_commerce_ref_jys where enterprise_id = '2679'";
		String sql = "SELECT * FROM hub_commerce_ref_jys";
		ResultSet rs = st.executeQuery(sql);
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String,String>>();
		while(rs.next()){
			HashMap<String, String> tmp = new HashMap<String, String>();
			tmp.put("jys", rs.getString("jys"));
			tmp.put("jysinfo", rs.getString("jysinfo"));
			tmp.put("jysmc", rs.getString("jysmc"));
			tmp.put("company_name", rs.getString("company_name"));
			tmp.put("enterprise_id", rs.getString("enterprise_id"));
			result.add(tmp);
		}
		rs.close();
		st.close();
		JDBCUtil.closeConnection(conn);
		return result;
	}
	/***
	 * 按交易所，时间删除词频统计信息
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int deleteKeywordsInfo(String id,String date) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		String sql = "DELETE FROM hub_commerce_meiya_sentiment_keywords WHERE enterprise_id='"+id+"' AND modifydate='"+date+"'";
		int result = st.executeUpdate(sql);
		st.close();
		JDBCUtil.closeConnection(conn);
		return result;
	}
	/***
	 * 按交易所删除词频统计信息
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int deleteKeywordsInfoNoDate(String id) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		String sql = "DELETE FROM hub_commerce_meiya_sentiment_keywords_new WHERE enterprise_id='"+id+"'";
		int result = st.executeUpdate(sql);
		st.close();
		JDBCUtil.closeConnection(conn);
		return result;
	}
	/***
	 * 批量插入词频信息
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void insertKeywords(ArrayList<String> sqls) throws ClassNotFoundException, SQLException{
		Connection conn = JDBCUtil.getConnection();
		Statement st = conn.createStatement();
		for(int i = 0 ; i < sqls.size() ; i ++){
			st.addBatch(sqls.get(i));
		}
		st.executeBatch();
		st.close();
		JDBCUtil.closeConnection(conn);
	}
	/***
	 * 统计关键词词频
	 * @return
	 */
	public ArrayList<HashMap<String, String>> countKeywords(String keywords){
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		//将数组转换成List
		String[] arr = keywords.split("#");
		ArrayList<String> keys = new ArrayList<String>();
		for(int i = 0 ; i < arr.length ; i ++){
			keys.add(arr[i]);
		}
		//根据字符串替换存在问题
		/*while (keywords.length() > 0) {
			HashMap<String, String> result = new HashMap<String, String>();
			String tmp = keywords.split("#")[0];
			String count = hit(keywords, tmp)+"";
			//System.out.println(tmp + "----" + count);
			result.put("keyword", tmp);
			result.put("count", count);
			list.add(result);
			keywords = keywords.replace(tmp + "#", "");
		}*/
		//使用list匹配
		while (keys.size() > 0) {
			HashMap<String, String> result = new HashMap<String, String>();
			String count = hitNew(keys.get(0), keys)+"";
			//System.out.println(tmp + "----" + count);
			result.put("keyword", keys.get(0));
			result.put("count", count);
			list.add(result);
			keys = slimList(keys.get(0), keys);
		}
		return list;
	}
	/***
	 * 统计方法
	 * @return
	 */
	public String doCount(){
		//获取当天日期
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String date = dateFormat.format(now); 
		//String date = "20180110"; 
		
		try {
			System.out.println(date+"统计开始");
			//1.先获取需要获取词频的交易所
			ArrayList<HashMap<String, String>> list = getJYSInfo();
			//2.然后遍历交易所
			for(int i = 0 ; i < list.size() ; i++){
				String id = list.get(i).get("enterprise_id");
				//2.1先删除该交易所当天日期的所有词频信息
				System.out.println("------------"+i+"------------");
				System.out.println("删除交易所---"+list.get(i).get("jysinfo")+"---开始");
				deleteKeywordsInfo(id,date);
				System.out.println("删除交易所---"+list.get(i).get("jysinfo")+"---结束");
				//2.2根据id和日期获取关键词
				String keywords = getSplitKeywords(id,date);
				System.out.println(list.get(i).get("jysinfo"));
				System.out.println(keywords);
				//2.3对关键词统计词频
				ArrayList<HashMap<String, String>> result = countKeywords(keywords);
				//2.4生成sql语句
				ArrayList<String> sqls = new ArrayList<String>();
				for (int j = 0; j < result.size(); j++) {
					//判断关键字是否为空,为空则不插入
					if(result.get(j).get("keyword").replaceAll(" ", "").equals("")||result.get(j).get("keyword").replaceAll(" ", "").equals(null)){
					//if(Pattern.compile("\\s*|\t|\r|\n").matcher(result.get(j).get("keyword")).replaceAll("").replaceAll("\\s+", "")==""||Pattern.compile("\\s*|\t|\r|\n").matcher(result.get(j).get("keyword")).replaceAll("").replaceAll("\\s+", "")==null){
						System.out.println("关键字为空");
						break;
					}
					String sql = "INSERT INTO hub_commerce_meiya_sentiment_keywords VALUES('"
							+ list.get(i).get("jys")
							+ "','"
							+ list.get(i).get("jysinfo")
							+ "','"
							+ list.get(i).get("jysmc")
							+ "','"
							+ list.get(i).get("company_name")
							+ "','"
							+ list.get(i).get("enterprise_id")
							+ "','"
							+ date
							+ "','"
							+ result.get(j).get("keyword")
							+ "','"
							+ result.get(j).get("count") + "')";
					System.out.println(sql);
					sqls.add(sql);
				}
				//2.5提交insert语句
				insertKeywords(sqls);
				System.out.println("------------"+i+"------------");
			}
			System.out.println(date+"统计结束");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/***
	 * 新统计方法,对全表的舆情数据进行统计
	 * @return
	 */
	public String doCountNew(){
		//获取当天日期
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = dateFormat.format(now); 
		
		try {
			System.out.println("全局统计开始");
			//1.先获取需要获取词频的交易所
			ArrayList<HashMap<String, String>> list = getJYSInfo();
			//2.然后遍历交易所
			for(int i = 0 ; i < list.size() ; i++){
				String id = list.get(i).get("enterprise_id");
				//2.1先删除该交易所有词频信息
				System.out.println("------------"+i+"------------");
				System.out.println("删除交易所---"+list.get(i).get("jysinfo")+"---开始");
				deleteKeywordsInfoNoDate(id);
				System.out.println("删除交易所---"+list.get(i).get("jysinfo")+"---结束");
				//2.2根据id和日期获取关键词
				String keywords = getSplitKeywordsNodate(id);
				System.out.println(list.get(i).get("jysinfo"));
				System.out.println(keywords);
				//2.3对关键词统计词频
				ArrayList<HashMap<String, String>> result = countKeywords(keywords);
				//2.4生成sql语句
				ArrayList<String> sqls = new ArrayList<String>();
				for (int j = 0; j < result.size(); j++) {
					//判断关键字是否为空,为空则不插入
					if(result.get(j).get("keyword").replaceAll(" ", "").equals("")||result.get(j).get("keyword").replaceAll(" ", "").equals(null)){
					//if(Pattern.compile("\\s*|\t|\r|\n").matcher(result.get(j).get("keyword")).replaceAll("").replaceAll("\\s+", "")==""||Pattern.compile("\\s*|\t|\r|\n").matcher(result.get(j).get("keyword")).replaceAll("").replaceAll("\\s+", "")==null){
						System.out.println("关键字为空");
						break;
					}
					String sql = "INSERT INTO hub_commerce_meiya_sentiment_keywords_new VALUES('"
							+ list.get(i).get("jys")
							+ "','"
							+ list.get(i).get("jysinfo")
							+ "','"
							+ list.get(i).get("jysmc")
							+ "','"
							+ list.get(i).get("company_name")
							+ "','"
							+ list.get(i).get("enterprise_id")
							+ "','"
							+ date
							+ "','"
							+ result.get(j).get("keyword")
							+ "','"
							+ result.get(j).get("count") + "')";
					System.out.println(sql);
					sqls.add(sql);
				}
				//2.5提交insert语句
				insertKeywords(sqls);
				System.out.println("------------"+i+"------------");
			}
			System.out.println("全局统计结束");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
   
   public static void main(String[] args){
//	   String a="11#22#11#11#";
//	   String b="11";
//	   System.out.println(hit(a,b)+"");
//	   while(a.length()>0){
//		   String tmp = a.split("#")[0];
//		   System.out.println(tmp+"----"+hit(a,tmp));
//		   a=a.replace(tmp+"#","");
//	   }
//		try {
//			System.out.println(deleteKeywordsInfo("1","1"));
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	   
   }

}
