package com.fr.hailian.core;

import java.io.Serializable;
import java.util.List;

import com.fr.hailian.model.RoleMenuModel;
import com.fr.hailian.model.RoleModel;

/**
 * @todo   用户实体
 * @time   2018年3月24日 下午4:38:18
 * @author zuoqb
 */
public class QdchUser implements Serializable {
	/**
	* @todo   用户实体p2p、小贷专用
	* @time   2018年4月8日 下午3:57:09
	* @author zuoqb
	*/
	private static final long serialVersionUID = 5075829814985411312L;
	private String id;
	private String username;//用户名称
	private String realname;//真实姓名
	private String mobile;//电话
	private String email;//邮箱
	private List<RoleModel> roles;//用户相应角色 菜单
	private String dataScope;
	private List<String> jysList;//数据权限相关  交易所信息
	private List<RoleMenuModel> menus;//菜单 包含父子级关系
	private String activity;//审批流权限  处理/审核/决策
	private String type;//2-P2P 3-小贷
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<RoleMenuModel> getMenus() {
		return menus;
	}

	public void setMenus(List<RoleMenuModel> menus) {
		this.menus = menus;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleModel> roles) {
		this.roles = roles;
	}

	public List<String> getJysList() {
		return jysList;
	}

	public void setJysList(List<String> jysList) {
		this.jysList = jysList;
	}

}
