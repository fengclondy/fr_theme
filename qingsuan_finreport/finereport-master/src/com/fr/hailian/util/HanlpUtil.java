package com.fr.hailian.util;

import java.util.HashMap;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

/***
 * 新分词软件
 * @author Tom
 *
 */
public class HanlpUtil {
	
	public static HashMap<String, String> doSplitWords(String msg){
		HashMap<String, String> result = new HashMap<String, String>();
		List<String> keywordList = HanLP.extractKeyword(msg, 5);
		String str = "";
		for(int i = 0 ; i < keywordList.size() ; i ++){
			str += keywordList.get(i)+"#";
		}
		result.put("keyWords", str);
		List<Term> termList = StandardTokenizer.segment(msg);
		result.put("words", termList.toString());
		return result;
	}
	public static void main(String[] args){
		List<Term> termList = StandardTokenizer.segment("商品和服务");
		System.out.println(termList);
	}

}
