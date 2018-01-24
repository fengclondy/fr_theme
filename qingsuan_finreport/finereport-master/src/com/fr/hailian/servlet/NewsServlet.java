package com.fr.hailian.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fr.hailian.core.BaseServlet;
import com.fr.hailian.core.Constants;
import com.fr.hailian.util.JDBCUtil;

/**
 * 
 * @author zuoqb
 *
 */
public class NewsServlet extends BaseServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getNewsByTitle(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getNewsByTitle(request, response);
	}
	
	private void getNewsByTitle(HttpServletRequest request, HttpServletResponse response) {
		HttpServletRequest hrequest = (HttpServletRequest) request;//web资源
		JSONObject r = new JSONObject();
		try {
			String title = java.net.URLDecoder.decode(hrequest.getParameter("title"), "UTF-8");
			String type = java.net.URLDecoder.decode(hrequest.getParameter("type"), "UTF-8");
			//System.out.println(title);
			Connection conn = JDBCUtil.getConnection();
			Statement st = conn.createStatement();
			if("0".equals(type)){
				//舆情信息
				String gpSql = "select a.* from (select";
				gpSql+=" title||'  '||t .publish_date||'  '||(select	jysinfo from	hub_commerce_ref_jys where enterprise_id = t .enterprise_id) as news,* ";
				gpSql+=" from 	hub_commerce_meiya_sentiment_news t where t.enterprise_id in (select ";
				gpSql+=" enterprise_id FROM	hub_commerce_ref_jys))a WHERE a.news='"+title+"' ";
				ResultSet gprs=st.executeQuery(gpSql);
				List<String> urls=new ArrayList<String>();
				while (gprs.next()) {
					urls.add(gprs.getString("url"));
				}
				st.close();
				JDBCUtil.closeConnection(conn);
				r.put("url",urls.get(0));
			}else if("1".equals(type)){
				//工商变更信息
				String gpSql = "select a.* from (select ";
				gpSql+=" case when char_length (new_log) > 20 then 	substr(new_log, 0, 20) || '...' ";
				gpSql+=" else new_log end ||'  '|| t .change_date ||'  '||( ";
				gpSql+=" select jysinfo from hub_commerce_ref_jys where ";
				gpSql+=" company_name = t .company_name ) news,t.* from 	hub_commerce_co_change_log t ";
				gpSql+=" where t.company_name  in (select 	company_name from ";
				gpSql+=" hub_commerce_ref_jys ))a where a.news='"+title+"' ";
				ResultSet gprs=st.executeQuery(gpSql);
				List<String> urls=new ArrayList<String>();
				while (gprs.next()) {
					urls.add(gprs.getString("company_id"));
				}
				Constants.COMPANY_ID=urls.get(0);
				r.put("company_id",urls.get(0));
				st.close();
				JDBCUtil.closeConnection(conn);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		responseOutWithJson(response, r);
	}

}
