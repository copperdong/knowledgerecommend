package com.zdhy.platform.knowledge.core;

import java.util.List;
import java.util.Map;

/**
 * 数据模型类
 * @author kylin
 *
 */
public class DataSource {
	private Integer idKey;
	private String content;
	private Map<String, Long> wordCount;
	private Map<String, Float> words;
	private Integer count;
	private List<Float> tfidfValue;

	public Integer getIdKey() {
		return idKey;
	}

	public void setIdKey(Integer idKey) {
		this.idKey = idKey;
	}

	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, Float> getWords() {
		return words;
	}

	public void setWords(Map<String, Float> words) {
		this.words = words;
	}

	public List<Float> getTfidfValue() {
		return tfidfValue;
	}

	public void setTfidfValue(List<Float> tfidfValue) {
		this.tfidfValue = tfidfValue;
	}

	public DataSource() {
	}

	public DataSource(Integer idKey, String content) {
		this.idKey = idKey;
		this.content = content;
	}

	public Map<String, Long> getWordCount() {
		return wordCount;
	}

	public void setWordCount(Map<String, Long> wordCount) {
		this.wordCount = wordCount;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "DataSource [idKey=" + idKey + ", content=" + content + ", wordCount=" + wordCount + ", words=" + words
				+ ", count=" + count + ", tfidfValue=" + tfidfValue + "]";
	}
}
