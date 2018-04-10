package com.fr.hailian.model;

import java.io.Serializable;
import java.util.List;

/**
 * @todo   角色实体
 * @time   2018年4月8日 下午3:50:08
 * @author zuoqb
 */
public class RoleModel implements Serializable{
	private static final long serialVersionUID = 6201547131958870064L;
	private String id;
	private String roleName;//角色名称
	private List<RoleMenuModel> menu;//角色对应菜单
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<RoleMenuModel> getMenu() {
		return menu;
	}
	public void setMenu(List<RoleMenuModel> menu) {
		this.menu = menu;
	}
}
