package com.fr.hailian.util;

//import code.NlpirTest.CLibrary;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.fr.hailian.library.CLibrary;
/***
 * 分词的工具类
 * @author Tom
 *
 */
public class NLPIRUtil {
	
	public static String transString(String aidString, String ori_encoding,
			String new_encoding) {
		try {
			return new String(aidString.getBytes(ori_encoding), new_encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	/***
	 * 分词程序
	 * @param words
	 * @return
	 */
	public static HashMap<String, String> splitWords(String words){
		//获取library下的Data目录
		String path = CLibrary.class.getResource("").getPath().replace("%5c", "");
		path = path.substring(1,path.length());
		String argu = path;
		// String system_charset = "GBK";//GBK----0
		//String system_charset = "UTF-8";
		int charset_type = 1;
		
		int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");
		String nativeBytes = null;

		if (0 == init_flag) {
			nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
			System.err.println("初始化失败！fail reason is "+nativeBytes);
			return null;
		}

		String sInput = "↑点击上方“福建交易市场登记结算中心”关注我们 信息来源���澎湃新闻 发布时间：2017年10月12日 “全国金融工作会议之后，我们一直在研究要不要加挂金融监督管理局的牌子，”一位地方金融办人士对澎湃新闻表示。10月10日，深圳召开全市金融工作会议，决定在市金融...在四川之前，北京、天津和宁夏分别于2009年、2015年、2015年设置金融工作局，并将以前金融办的职能并入其中。";
		
		sInput = words;
		//String nativeBytes = null;
		HashMap<String, String> result = new HashMap<String, String>();
		try {
			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);

			//System.out.println("分词结果为： " + nativeBytes);
			result.put("words", nativeBytes.replace("'", ""));
			
			//CLibrary.Instance.NLPIR_AddUserWord("要求美方加强对输 n");
			//CLibrary.Instance.NLPIR_AddUserWord("华玉米的产地来源 n");
			//nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
			//System.out.println("增加用户词典后分词结果为： " + nativeBytes);
			
			//CLibrary.Instance.NLPIR_DelUsrWord("要求美方加强对输");
			//nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
			//System.out.println("删除用户词典后分词结果为： " + nativeBytes);
			//int nCountKey = 0;
			String nativeByte = CLibrary.Instance.NLPIR_GetKeyWords(sInput, 10,false);
			//System.out.print("关键词提取结果是：" + nativeByte);
			result.put("keyWords", nativeByte);
			CLibrary.Instance.NLPIR_Exit();

		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return result;
		
	}
	
	public static void main(String[] args) throws Exception {
		//获取library下的Data目录
		String path = CLibrary.class.getResource("").getPath().replace("%5c", "");
		path = path.substring(1,path.length());
		String argu = path;
		// String system_charset = "GBK";//GBK----0
		//String system_charset = "UTF-8";
		int charset_type = 1;
		
		int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");
		String nativeBytes = null;

		if (0 == init_flag) {
			nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
			System.err.println("初始化失败！fail reason is "+nativeBytes);
			return;
		}

		String sInput = "11月再见，12月你好!2017-12-01 美文共享* #切尔西体育杂志# *Melody Fair-Bee Gees-专辑《The 60's Collection》 * 2017年最后一个月。告别秋凉和炎热...信心能够帮你重获新生，一定不要怀疑自己，不要忘了为自己喝彩。* 圆融通达，懂方与圆的生存智慧 伸出你的友爱之手，帮助他人就是帮助自己。用宽容之心打动别人，照亮别人，也照亮了自己。";

		//String nativeBytes = null;
		try {
			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);

			System.out.println("分词结果为： " + nativeBytes);
			
			//CLibrary.Instance.NLPIR_AddUserWord("要求美方加强对输 n");
			//CLibrary.Instance.NLPIR_AddUserWord("华玉米的产地来源 n");
			//nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
			//System.out.println("增加用户词典后分词结果为： " + nativeBytes);
			
			//CLibrary.Instance.NLPIR_DelUsrWord("要求美方加强对输");
			//nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
			//System.out.println("删除用户词典后分词结果为： " + nativeBytes);
			
			
			//int nCountKey = 0;
			String nativeByte = CLibrary.Instance.NLPIR_GetKeyWords(sInput, 10,false);

			System.out.print("关键词提取结果是：" + nativeByte);

			//nativeByte = CLibrary.Instance.NLPIR_GetFileKeyWords("D:\\NLPIR\\feedback\\huawei\\5341\\5341\\产经广场\\2012\\5\\16766.txt", 10,false);

			//System.out.print("关键词提取结果是：" + nativeByte);

			

			CLibrary.Instance.NLPIR_Exit();

		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

	}

}
