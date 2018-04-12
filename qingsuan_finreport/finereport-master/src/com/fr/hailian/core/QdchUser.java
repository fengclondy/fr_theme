package com.fr.hailian.core;

import java.io.Serializable;
import java.util.List;

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
