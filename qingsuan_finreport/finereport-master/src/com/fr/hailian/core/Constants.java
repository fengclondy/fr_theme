package com.fr.hailian.core;
/**
 * 
 * @className Constants.java
 * @time   2017年8月7日 下午4:46:36
 * @author zuoqb
 * @todo   系统常量
 */
public class Constants {
	public static final long AUXILIARYROLE_ID = 7;//辅助决策需要的角色ID
	public static final String AUXILIARYROLE_NAME = "XBY_BI";//辅助决策需要的角色名称
	public static final String PORTAL_FROM="NWH5ABF13158";//userValidate的参数FROM
	public static final String ENCODE_RULES="hailian88888888finereport201708";//对称加密 加密规则
	public static final String RTX_AUTH_SERVER_ID="Bi";//发送消息的系统 即授权ID
	/**
	 * 对外发布的webservice端口号 不能与finereport端口一致
	 * 测试是否发布成功
	 * http://ip:8888/Service/TaskWebService?wsdl
	 * 入参格式：{"Flag":"1","Page":1,"Pagesize":10,"Type":"1","Uid":"47"}
	 */
	public static final String WebService_Port = "8888";
	public static final String DEFAULT_PWD = "123456";//导入员工的默认密码
	public static final String DEFAULT_POST= "53";//导入员工的默认职务ID FR_T_POST
	/**
	 * 数据库配置
	 */
/*	public static final String driverclass = "com.fr.third.org.hsqldb.jdbcDriver";
	public static final String url = "emb:jdbc:hsqldb:file://f:FineReport_8.0/WebReport/WEB-INF/finedb/db";
	public static final String username = "sa";
	public static final String password = "";*/
	public static final String DB_NAME = "Hsql";//数据库名称 配置在服务器上
	
	//public static final String CTX_PATH = "";//帆软项目根目录
	//public static final String CTX_PORT = "80";//帆软项目端口
	
/*	public static final String CTX_DOMAIN="10.158.215.151";*/
	public static final String CTX_PATH = "/WebReport";//帆软项目根目录
	public static final String CTX_PORT = "8075";//帆软项目端口
	
	public static final String RTX_TITLE = "多级上报未处理信息提醒";//RTX发送标题
	public static final String RTX_CONTENT = "BI平台";//RTX发送内容
	//连接GP数据库信息
	public final static String JDBC_HUB = "jdbc:postgresql://172.16.6.61:5432/qdchedw";
	public final static String JDBC_USER_HUB = "hub";
	public final static String JDBC_USER_PWD_HUB = "hub@2017";
	
	public final static String FXSJ_AUDITING = "审批中";
	public final static String FXSJ_AUDIT_FINISH = "审批完成";
	public final static String FXSJ_AUDIT_REJECT = "审批驳回";
	
	public final static String MSG_USER = "qdqszx";
	
	public final static String MSG_USER_PWD = "b0ecbc94863ba01db82b67a3a4b01c7f";
	
	public final static String MSG_USER_SIGN = "监管预警平台";
	
	//测试的url地址
	//public final static String MSG_API_URL = "http://manager.wxtxsms.cn/smsport/feePost.aspx";
	
	//正式的url地址
	public final static String MSG_API_URL = "http://manager.wxtxsms.cn/smsport/sendPost.aspx";
	//访问网址
	public final static String WEB_URL = "http://144.123.36.214:10080/WebReport/QDCH/login.html ";
	
	public final static String[] MSG_MODEL = {
			"防控预警平台提示：风险预警事件（编号NUM）已提交，请登录"+WEB_URL+"查看处理。",
			"防控预警平台提示：您提交的风险预警事件（编号NUM）已驳回，请登录"+WEB_URL+"查看处理。",
			"防控预警平台提示：一条风险预警事件（编号NUM）已上报，请登录系统"+WEB_URL+"查看处理。" };
	//微信appid
	public final static String APP_ID = "wx74f14108b8d653fa";
	//微信appsecret
	public final static String APP_SECRET = "39658d1df85ba6b515981611578c7270";
	
	public  final static String FTP_USER = "mysftp";
	
	public  final static String FTP_PWD = "MYsftp@123";
	public static String ROLE_NAME="";
	
	}
