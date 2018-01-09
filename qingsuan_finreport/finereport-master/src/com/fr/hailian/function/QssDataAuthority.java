package com.fr.hailian.function;

import com.fr.hailian.service.UserDataFromRoleService;
import com.fr.script.AbstractFunction;

/**
 * 
 * @author zuoqb 2018年1月4日10:19:16
 *
 *清算所数据权限自定义公式 
 *目前的数据权限是根据机构部门来分配的
 *部门（数据管理）下面对应职位（职位看做交易所），职位对应人员
 *职位（交易所）又要区分大宗‘权益等类别，
 *所以职位名称命名规则为 类别（大宗、权益）+“_”+交易所名称+“_”+交易所代码（比如0007）
 *数据权限规则为：根据当前用户名称去finedb数据库查询用户当前大类（大宗、权益）职位，把职位名称
 *中交易所代码拼接；如果当前用户没有相应大类职位，则去gp数据库查询所有交易所
 */
public class QssDataAuthority extends AbstractFunction{
	private static final long serialVersionUID = -110961006132823292L;

	@Override
	public Object run(Object[] arg0) {
		String userName=arg0[0].toString();//参数一 用户名
		String jys="";
		try {
			jys=UserDataFromRoleService.getDepartMenByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "("+jys+")";
	}

}
