package com.fr.hailian.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.fs.base.entity.CustomRole;
import com.fr.fs.base.entity.User;
import com.fr.fs.control.UserControl;
import com.fr.fs.web.service.ServiceUtils;
import com.fr.hailian.action.HlLoadLoginAction;
import com.fr.hailian.core.QdchUser;
import com.fr.hailian.model.RoleMenuModel;
import com.fr.hailian.model.RoleModel;
import com.fr.hailian.service.UserDataFromRoleService;

/**
 * 
 * @className RoleUtil.java
 * @time   2017年8月8日 下午6:08:38
 * @author zuoqb
 * @todo   角色工具栏
 */
public class RoleUtil {
	/**
	 * 
	 * @time   2017年8月8日 下午6:10:36
	 * @author zuoqb
	 * @todo   判断是否包含辅助系统权限
	 * @param  @param user
	 * @param  @return
	 * @return_type   boolean
	 */
	public static boolean judgeAuxiliaryRole(User user){
		Set<CustomRole> roles;
		boolean hasRole=false;
		try {
			roles = UserControl.getInstance().getSRoles(user.getId());
			//根据用户id获取该所属的所有角色
			Iterator<CustomRole> it = roles.iterator();  
			while (it.hasNext()) {  
				CustomRole role = it.next();
				//System.out.println("role id:"+role.getId()+" roleName:"+role.getRolename()+" roleDisplayName:"+role.getDisplayName());
				if(role.getDisplayName().equals(com.fr.hailian.core.Constants.AUXILIARYROLE_NAME)){
					hasRole=true;
					break;
				}
//				if(role.getId()==com.fr.hailian.util.Constants.AUXILIARYROLE_ID){
//					hasRole=true;
//					break;
//				}
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hasRole;
	}
	public static void loginCMD(HttpServletRequest request,
			HttpServletResponse response){
		HlLoadLoginAction hl=new HlLoadLoginAction();
		try {
			hl.actionCMD(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @time   2017年8月10日 上午11:33:24
	 * @author zuoqb
	 * @todo   判断是否是管理员
	 * @param  @param user
	 * @param  @return
	 * @return_type   boolean
	 */
	public static boolean isSuperAdmin(User user){
		long superManagerID=UserControl.getInstance().getSuperManagerID();//超级管理员ID
		boolean isAdmin = superManagerID == user.getId(); //判断是否是管理员
		return isAdmin;
	}
	/**
	 * 
	 * @time   2017年8月11日 上午10:41:14
	 * @author zuoqb
	 * @todo  过去当前登录用户
	 * @param  @param request
	 * @param  @return
	 * @return_type   User
	 */
	public static User getCurrentUser(HttpServletRequest request){
		long userID = ServiceUtils.getCurrentUserID(request);
		User user=null;
		try {
			user = UserControl.getInstance().getUser(userID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//获取用户对象
		return user;
	}
	/**
	 * @todo   获取全部用户信息 包含基本信息 角色以及菜单
	 * @time   2018年4月8日 下午4:29:40
	 * @author zuoqb
	 * @return_type   QdchUser
	 */
	public static QdchUser authorityUser(HttpServletRequest request,String userName,String roleName){
		QdchUser user=new QdchUser();
       try {
    	   System.out.println("---authorityUser");
    	   Connection con = null;
    	   con = C3P0Utils.getInstance().getConnection();
           String sql = "select * from fr_t_user where username='"+userName+"'";
           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while (rs.next()) {
        	   user.setId(rs.getString("ID"));
        	   user.setUsername(rs.getString("USERNAME"));
        	   user.setMobile(rs.getString("MOBILE"));
        	   user.setRealname(rs.getString("REALNAME"));
        	   user.setEmail(rs.getString("EMAIL"));
           }
           rs.close();
           con.close();
           //查询角色 菜单
           List<RoleModel>  roles=getUserRoles(user.getId(), userName,roleName);
           user.setRoles(roles);
           //查询数据权限 交易所
           String jysStr=UserDataFromRoleService.getJysForP2pXd(userName);
           if(jysStr!=null&&!"".equals(jysStr)&&jysStr.length()>0){
        	   user.setJysList(Arrays.asList(jysStr.split(",")));
        	   user.setDataScope(jysStr);
           }
	} catch (Exception e) {
		e.printStackTrace();
	}
		return user;
	}
	/**
	 * 
	 * @todo   TODO
	 * @time   2018年4月8日 下午4:54:38
	 * @author 获取用户角色
	 * @return_type   List<RoleModel>
	 */
	public static List<RoleModel> getUserRoles(String userId,String userName,String roleName){
		Connection con = null;
		List<RoleModel> roles=new ArrayList<RoleModel>();
		System.out.println("getUserRoles");
       try {
    	   con = C3P0Utils.getInstance().getConnection();
           String sql = "select m.id, rolename, description, sortindex, issync from fr_t_customrole m left join fr_t_customrole_user t ";
           sql+=" on  m.id = t.customroleid left join fr_t_user u on u.id=t.userid where u.username='"+userName+"' ";
           if(!"".equals(roleName)&&roleName!=null){
           	sql+="  and m.rolename like '%"+roleName+"%' ";
           }
           //System.out.println(sql);
           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while (rs.next()) {
        	   RoleModel role=new RoleModel();
        	   role.setId(rs.getString("ID"));
        	   role.setRoleName(rs.getString("ROLENAME"));
        	   //菜单
        	   List<RoleMenuModel> menus=getMenuByRoleName(userId, role.getRoleName());
        	   role.setMenu(menus);
        	   roles.add(role);
           }
           rs.close();
           con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		return roles;
	}
	
	/**
	 * 根据自定义角色名称获取相应菜单信息
	 * zuoqb
	 * @param roleName:角色名称
	 * @return
	 * @throws SQLException
	 */
	public static List<RoleMenuModel> getMenuByRoleName(String  userId,String roleName){
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
        sql+=" and t.userid='"+userId+"'";
        System.out.println(sql);
        try {
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
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
}
