package com.zdhy.platform.knowledge.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * TFIDF计算
 * @author kylin
 *
 */
public class TfIdfCalculate {
	//文件总数
	private static Long docCount = 0l;
	//包含这个词组的文件数字典表
	private static Map<String, Long> incouldWordDocs = new HashMap<String, Long>();
	
	/**
	 * 计算单个文件中词频
	 * @param startValue
	 * @return
	 */
	public static Map<String,Long> calcuateWord(List<String> words){
		docCount++;
		Map<String, Long> wordCount = new HashMap<String,Long>();
		for(String word:words){
			if(wordCount.containsKey(word)){
				Long value=wordCount.get(word)+1l;
				wordCount.put(word, value);
			}else{
				wordCount.put(word, 1l);
			}
		}
		return wordCount;
	}
	/**
	 * 计算单个文件中词频的TF值
	 * @param wordCount
	 * @param wordAllCount
	 * @return
	 */
	public static Map<String,Float> calcuateWordTf(Map<String,Long> wordCount,Integer wordAllCount){
		Map<String, Float> map = new HashMap<String,Float>();
		for (Entry<String, Long> en : wordCount.entrySet()) {
			String key = en.getKey();
			Long value = en.getValue();
			map.put(key, (float)value/wordAllCount);
			//更新词组
			if(incouldWordDocs.containsKey(key)){
				incouldWordDocs.put(key, incouldWordDocs.get(key)+1);
			}else{
				incouldWordDocs.put(key, 1l);
			}
		}
		return map;
	}
	
	/**
	 * 计算IDF
	 * @param startValue
	 * @return
	 */
	public static Map<String,Float> calcuateWordTfIdf(Map<String,Float> docTf){ 
		Map<String, Float> map = new HashMap<String,Float>();
		for (Entry<String, Float> en : docTf.entrySet()) {
			String key = en.getKey();
			Float valueTf = en.getValue();
			Long wordDocNum = incouldWordDocs.get(key);
			double idfVal=Math.log10(docCount)-Math.log10(wordDocNum);
			map.put(key, (float)docCount/valueTf);
		}
		return map;
	}
	public static Long getDocCount() {
		return docCount;
	}
}
