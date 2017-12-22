package com.fr.hailian.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fr.hailian.model.RoleMenuModel;
import com.fr.hailian.util.C3P0Utils;

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
            System.out.println("获取到的用户角色名："+rs.getString("ROLENAME"));
            roleName = rs.getString("ROLENAME");
        }
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
	public static List<RoleMenuModel> getMenuByRoleName(String roleName) throws SQLException{
		Connection con = null;
		List<RoleMenuModel> list=new ArrayList<RoleMenuModel>();
        con = C3P0Utils.getInstance().getConnection();
        String sql = "select p.name as pname,a.type||b.id as id,'0'||b.parent as pid,b.name,b.reportletpath ";
        sql+=" from fr_t_customroleentryprivilege a inner join fr_reportletentry b ";
        sql+=" on b.id=a.entryid left join fr_folderentry p on p.id=b.parent left join fr_t_customrole r on r.id=a.roleid ";
        if(!"".equals(roleName)&&roleName!=null){
        	sql+=" where r.rolename like '%"+roleName+"%' ";
        }
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

}
