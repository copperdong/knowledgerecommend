package com.zdhy.platform.knowledge.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zdhy.platform.knowledge.action.ModelAction;

/**
 * KNN 知识推荐模型
 * @author kylin
 *
 */
public class KnnModelKnowledge {
	// 分类，簇
	private static Integer core;
	// 分片
	private static Integer calcuatePair;
	// 相似度
	private static Double cosineVal;
	// 数据来源
	private static List<DataSource> dss;
	// 数据转化
	private static List<Entry> sourceEn = new ArrayList<Entry>();

	@SuppressWarnings("unused")
	private KnnModelKnowledge() {
	}

	public KnnModelKnowledge(Integer core, Double cosineVal, List<DataSource> dss) {
		this.core = core;
		this.cosineVal = cosineVal;
		this.dss = dss;
		this.calcuatePair = 1;
	}

	public KnnModelKnowledge(Integer core, Double cosineVal, List<DataSource> dss, Integer calcuatePair) {
		this.core = core;
		this.cosineVal = cosineVal;
		this.dss = dss;
		this.calcuatePair = calcuatePair;
	}

	/**
	 * 数据初始化
	 */
	public static void fit() {
		for (DataSource dataSource : dss) {
			Map<String, Float> calcuateWordTf = TfIdfCalculate.calcuateWordTf(dataSource.getWordCount(),
					dataSource.getCount());
			dataSource.setWords(TfIdfCalculate.calcuateWordTfIdf(calcuateWordTf));
		}
	}

	/**
	 * 求近邻
	 * @param content 查询语句
	 * @return
	 */
	public static List<Entry> query(String content) {
		List<String> analyzer = null;
		try {
			//分词
			analyzer = ModelAction.analyzer(new ParticipleIkCn(), content.replaceAll("\\d+", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//词频计算
		Map<String, Long> calcuateWord = TfIdfCalculate.calcuateWord(analyzer);
		//初始化矩阵模型
		Set<String> set = calcuateWord.keySet();
		//计算查询的词频TF
		Map<String, Float> calcuateWordTf = TfIdfCalculate.calcuateWordTf(calcuateWord, analyzer.size());
		//计算查询的词频的IDF
		Map<String, Float> tfIdf = TfIdfCalculate.calcuateWordTfIdf(calcuateWordTf);
		//查询内容矩阵初始化
		List<Float> values = new ArrayList<Float>();
		for (String s : set) {
			values.add(tfIdf.get(s));
		}
		//查询模型
		Entry entryQuery = new Entry("content", values);
        //初始化结果容器
		List<Entry> res = new ArrayList<Entry>();
		//原始数据查询矩阵化
		List<Entry> tmpRes = new ArrayList<Entry>();
		for (DataSource dataSource : dss) {
			Map<String, Float> words = dataSource.getWords();
			List<Float> arrayList = new ArrayList<Float>();
			for (String s : set) {
				if (words.containsKey(s)) {
					arrayList.add(words.get(s));
				}else{
					arrayList.add(0.0f);
				}
			}
			tmpRes.add(new Entry(dataSource.getIdKey().toString(), arrayList));
		}
        //查询计算
		for (Entry en : tmpRes) {
			double value = CosineCalculate.calculateCosine(entryQuery.getValue(), en.getValue());
			if (value > cosineVal) {
				res.add(new Entry(en.getLabel(), en.getValue(), value));
			}
		}
        //集合数组化
		Entry[] array = res.toArray(new Entry[] {});
		//快速排序
		Entry.quickSort(array, 0, array.length - 1);
	    //倒序
		Entry.quickSortDesc(array);
		List<Entry> arrayList = new ArrayList<Entry>();
        //推荐个数计算
		int size = core < array.length ? core : array.length;
		for (int i = 0; i < size; i++) {
			arrayList.add(array[i]);
		}
		return arrayList;
	}

}
