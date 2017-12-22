package com.fr.hailian.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Properties;

/***
 * 通过配置文件获取字段（大宗，权益）
 * @author Tom
 *
 */
public class KeyUtil {
	/***
	 * 根据key值的角色名
	 * @return
	 */
	public static String getKeyValue(String key){
		Properties prop = new Properties();
		try {
			// 读取属性文件config.properties
			InputStream in = new BufferedInputStream (KeyUtil.class.getResourceAsStream("config.properties"));
			prop.load(in); // /加载属性列表
			String value = prop.getProperty(key);
			in.close();
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args){
		System.out.println(getKeyValue("DZ"));
		System.out.println(getKeyValue("QY"));
		System.out.println(getKeyValue("SH"));
		System.out.println(getKeyValue("JC"));
	}
}
