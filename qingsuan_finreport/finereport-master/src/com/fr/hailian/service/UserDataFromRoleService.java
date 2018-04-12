package com.fr.hailian.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.fr.hailian.core.Constants;
import com.fr.hailian.model.RoleMenuModel;
import com.fr.hailian.util.C3P0Utils;
import com.fr.hailian.util.JDBCUtil;
import com.fr.hailian.util.RoleUtil;

/***
 * 通过角色获取用户信息
 * @author Tom
 *
 */
public class UserDataFromRoleService {
	
	/***
	 * 通过角色名称获取roleId
	 * @param name
	 * @return
	 * @throws SQLException 
	 */
	public String getRoleId(String name) throws SQLException{
		Connection con = null;
        con = C3P0Utils.getInstance().getConnection();
        String sql = "SELECT ID FROM FR_T_CUSTOMROLE WHERE ROLENAME = '"+name+"'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        String id = "";
        while (rs.next()) {
        	System.out.println("获取到的角色名称："+name);
            System.out.println("获取到的角色id："+rs.getString("ID"));
            id = rs.getString("ID");
        }
        rs.close();
        con.close();
		return id;
	}
	/**
	 * 获取用户id
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<String> getUserIDByRoleId(String roleId) throws SQLException{
		Connection con = null;
        con = C3P0Utils.getInstance().getConnection();
        String sql = "SELECT USERID FROM FR_T_CUSTOMROLE_USER WHERE CUSTOMROLEID = '"+roleId+"'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<String> list = new ArrayList<String>();
        while (rs.next()) {
            System.out.println("获取到的用户id："+rs.getString("USERID"));
            list.add(rs.getString("USERID"));
        }
        rs.close();
        con.close();
		return list;
	}
	/***
	 * 通过id获取用电话号码
	 * @param list
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<String> getUserPhoneById(ArrayList<String> list) throws SQLException{
		String insql = "(";
		for(int i = 0 ; i < list.size() ; i++){
			if(i==0){
				insql +=("'"+list.get(i)+"'"); 
			}else{
				insql += (",'"+list.get(i)+"'");
			}
		}
		insql += ")";
		Connection con = null;
        con = C3P0Utils.getInstance().getConnection();
        String sql = "SELECT MOBILE FROM FR_T_USER WHERE ID IN" + insql;
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<String> phoneList = new ArrayList<String>();
        while (rs.next()) {
            System.out.println("获取到的用户mobile："+rs.getString("MOBILE"));
            phoneList.add(rs.getString("MOBILE"));
        }
        rs.close();
        con.close();
		return phoneList;
	}
	/***
	 * 通过角色名称获取用户phonenumber
	 * @param name
	 * @return
	 * @throws SQLException 
	 */
	public String getUserPhoneByRoleName(String name) throws SQLException{
		String roleId = getRoleId(name);
		ArrayList<String> phoneList = getUserPhoneById(getUserIDByRoleId(roleId));
		String phone = "";
		for(int i = 0 ; i < phoneList.size() ; i++){
			if(!(phoneList.get(i)==null||"".equals(phoneList.get(i)))){
				phone += (phoneList.get(i)+",");
			}
		}
		//把最后的一个号码的逗号去掉
		if(phone.length()>0){
			phone = phone.substring(0, phone.length()-1);
		}else{
			phone = "";
		}
		return phone;
	}
	/***
	 * 通过userID获取角色名
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public String getRoleNameByUserId(String userId) throws SQLException{
		Connection con = null;
        con = C3P0Utils.getInstance().getConnection();
        String sql = "SELECT ID, ROLENAME, DESCRIPTION, SORTINDEX, ISSYNC FROM FR_T_CUSTOMROLE M LEFT JOIN FR_T_CUSTOMROLE_USER T ON T.USERID='"+userId+"' WHERE M.ID = T.CUSTOMROLEID";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        String roleName = "";
        while (rs.next()) {
            roleName += rs.getString("ROLENAME");
        }
        //System.out.println("获取到的用户角色名："+roleName);
        rs.close();
        con.close();
		return roleName;
	}
	/**
	 * 根据自定义角色名称获取相应菜单信息
	 * zuoqb
	 * @param roleName:角色名称
	 * @return
	 * @throws SQLException
	 */
	public static List<RoleMenuModel> getMenuByRoleName(HttpServletRequest request,String roleName) throws SQLException{
		Connection con = null;
		List<RoleMenuModel> list=new ArrayList<RoleMenuModel>();
        con = C3P0Utils.getInstance().getConnection();
        String sql = "select p.name as pname,a.type||b.id as id,'0'||b.parent as pid,b.name,b.reportletpath ";
        sql+=" from fr_t_customroleentryprivilege a inner join fr_reportletentry b ";
        sql+=" on b.id=a.entryid left join fr_folderentry p on p.id=b.parent left join fr_t_customrole r on r.id=a.roleid ";
        sql+=" left join fr_t_customrole_user t on r.id = t.customroleid  where 1=1 ";
        if(!"".equals(roleName)&&roleName!=null){
        	sql+="  and r.rolename like '%"+roleName+"%' ";
        }
        long userId = RoleUtil.getCurrentUser(request).getId();
        sql+=" and t.userid='"+userId+"'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
        	RoleMenuModel m=new RoleMenuModel();
        	m.setId(rs.getString("id"));
        	m.setName(rs.getString("name"));
        	m.setPid(rs.getString("pid"));
        	m.setPname(rs.getString("pname"));
        	m.setReportletpath(rs.getString("reportletpath"));
        	list.add(m);
        }
        rs.close();
        con.close();
		return list;
	}
	/**
	 * 
	 * @param userName
	 * @return 数据权限 获取交易所信息
	 * @throws SQLException
	 */
	public static String getDepartMenByUserName(String userName){
		if(userName==null||StringUtils.isBlank(userName)){
			return null;
		}
		String jysIds="";
		Connection con = null;
        try {
        	con = C3P0Utils.getInstance().getConnection();
            String sql = "select p.postname,d.name from fr_t_post p left join fr_t_department_post_user du on du.postid=p.id ";
            sql+=" left join fr_t_department d on d.id=du.departmentid left join fr_t_user u on u.id=du.userid ";
            sql+=" where 1=1  ";
            if(!"".equals(userName)&&userName!=null){
            	sql+="  and u.username ='"+userName+"' ";
            }
            if(!"".equals(Constants.ROLE_NAME)&&Constants.ROLE_NAME!=null){
            	sql+=" and p.postname like '%"+Constants.ROLE_NAME+"%' ";
            }
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	jysIds=jysIds+"'"+rs.getString("postname").split("_")[2]+"',";
            }
            if(jysIds.length()>0){
            	jysIds=jysIds.substring(0,jysIds.length()-1);
            }
            if("".equals(jysIds)||jysIds==null){
            	//如果是空 查询所有
            	Connection conn = JDBCUtil.getConnection();
    			Statement st = conn.createStatement();
    			String gpSql = "select jys from hub_dd_tqs_jys";
    			ResultSet gprs=st.executeQuery(gpSql);
    			while (gprs.next()) {
    				jysIds=jysIds+"'"+gprs.getString("jys")+"',";
    			}
    			if(jysIds.length()>0){
    	        	jysIds=jysIds.substring(0,jysIds.length()-1);
    	        }
    			st.close();
    			JDBCUtil.closeConnection(conn);
            }
            rs.close();
            con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jysIds;
	}
	/**
	 * 
	 * @todo   获取菜单详细
	 * @time   2018年4月5日 下午3:15:57
	 * @author zuoqb
	 * @return_type   String
	 */
	public static RoleMenuModel getMenuDetail(String menuId){
		if(menuId==null||"".equals(menuId)){
			return null;
		}
		Connection con = null;
		RoleMenuModel m=new RoleMenuModel();
        try {
        	con = C3P0Utils.getInstance().getConnection();
            String sql = "select id,name,parent,reportletpath from fr_reportletentry where id='"+menuId.trim()+"'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	m.setId(rs.getString("id"));
            	m.setName(rs.getString("name"));
            	m.setPid(rs.getString("parent"));
            	m.setReportletpath(rs.getString("reportletpath"));
            }
            rs.close();
            con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return m;
	}
	
	/**
	 * 
	 * @param userName
	 * @return 数据权限 获取交易所信息
	 * @throws SQLException
	 */
	public static String getJysForP2pXd(String userName){
		if(userName==null||StringUtils.isBlank(userName)){
			return null;
		}
		String jysIds="";
		Connection con = null;
        try {
        	con = C3P0Utils.getInstance().getConnection();
            String sql = "select p.postname,d.name from fr_t_post p left join fr_t_department_post_user du on du.postid=p.id ";
            sql+=" left join fr_t_department d on d.id=du.departmentid left join fr_t_user u on u.id=du.userid ";
            sql+=" where 1=1  ";
            if(!"".equals(userName)&&userName!=null){
            	sql+="  and u.username ='"+userName+"' ";
            }
            System.out.println(sql);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	jysIds=jysIds+"'"+rs.getString("postname").split("_")[2]+"',";
            }
            if(jysIds.length()>0){
            	jysIds=jysIds.substring(0,jysIds.length()-1);
            }
            rs.close();
            con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jysIds;
	}
	
}
