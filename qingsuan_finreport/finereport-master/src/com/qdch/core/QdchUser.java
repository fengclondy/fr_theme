package com.qdch.core;

import java.util.List;
/**
 * 
 * @todo   用户实体
 * @time   2018年3月24日 下午4:38:18
 * @author zuoqb
 */
public class QdchUser {
	private String name;
	private String tel;
	private List<String> jysList;//交易所数据信息
	//扩展其他属性
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public List<String> getJysList() {
		return jysList;
	}
	public void setJysList(List<String> jysList) {
		this.jysList = jysList;
	}
	
}
