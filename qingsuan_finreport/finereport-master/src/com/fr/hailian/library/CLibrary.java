package com.fr.hailian.library;

import com.sun.jna.Library;
import com.sun.jna.Native;

/***
 * 分词使用的library 定义接口CLibrary，继承自com.sun.jna.Library
 * 
 * @author Tom
 *
 */
public interface CLibrary extends Library {
	// 定义并初始化接口的静态变量
	CLibrary Instance = (CLibrary) Native.loadLibrary(
	// "D:\\NLPIR\\bin\\ICTCLAS2013\\x64\\NLPIR", CLibrary.class);
			CLibrary.class
					.getResource("")
					.getPath()
					.replace("%5c", "")
					.substring(
							1,
							CLibrary.class.getResource("").getPath()
									.replace("%5c", "").length())
					+ "NLPIR", CLibrary.class);

	public int NLPIR_Init(String sDataPath, int encoding, String sLicenceCode);

	public String NLPIR_ParagraphProcess(String sSrc, int bPOSTagged);

	public String NLPIR_GetKeyWords(String sLine, int nMaxKeyLimit,
			boolean bWeightOut);

	public String NLPIR_GetFileKeyWords(String sLine, int nMaxKeyLimit,
			boolean bWeightOut);

	public int NLPIR_AddUserWord(String sWord);// add by qp 2008.11.10

	public int NLPIR_DelUsrWord(String sWord);// add by qp 2008.11.10

	public String NLPIR_GetLastErrorMsg();

	public void NLPIR_Exit();
}
