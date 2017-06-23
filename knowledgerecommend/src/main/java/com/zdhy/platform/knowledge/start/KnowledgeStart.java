package com.zdhy.platform.knowledge.start;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;

import com.zdhy.platform.knowledge.action.ModelAction;
import com.zdhy.platform.knowledge.core.DataSource;
import com.zdhy.platform.knowledge.core.Entry;
import com.zdhy.platform.knowledge.core.KnnModelKnowledge;
import com.zdhy.platform.knowledge.core.ParticipleIkCn;
import com.zdhy.platform.knowledge.core.TfIdfCalculate;

public class KnowledgeStart {
	private static Map<String,String> dataSet = new TreeMap<String,String>();

	public static void main(String[] args) {
		File file = new File("/Users/kylin/data/event.csv");
		List<String> lines = null;
		try {
			lines = FileUtils.readLines(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		List<DataSource> dss = new ArrayList<DataSource>();
		for (String line : lines) {
			String[] split = line.split(",");
			Integer idKey=Integer.parseInt(split [0]);
			String title=split [1].replaceAll("\\d+", "");
			dataSet.put(split[0],split [1]);
			DataSource source = new DataSource(idKey, title);
			dss.add(source);
		}
		
		//切词
		for (DataSource dataSource : dss) {
			String content = dataSource.getContent();
			List<String> analyzer = null;
			try {
				analyzer = ModelAction.analyzer(new ParticipleIkCn(), content);
			} catch (Exception e) {
				System.err.println(dataSource.getIdKey()+"====>"+content);
				e.printStackTrace();
			}
			Map<String, Long> wordCount = TfIdfCalculate.calcuateWord(analyzer);
			dataSource.setWordCount(wordCount);
		    dataSource.setCount(analyzer.size());
		}
		
		
		
		KnnModelKnowledge model = new KnnModelKnowledge(6, 0.5, dss);
		model.fit();
		
		List<String> arrayList = new ArrayList<String>();
		arrayList.add("野兽派画家莱克获威尼斯奖");
		arrayList.add("孟加拉 万人在风暴中生还");
		arrayList.add("男性首次从北坡登上珠穆朗玛峰");
		arrayList.add("国共合作修建花园口大堤");
		arrayList.add("孟加拉风暴");
		
		for (String query : arrayList) {
			List<Entry> queryEn = model.query(query);
			System.out.println("=========查询===========" + query);
			for (Entry entry2 : queryEn) {
				System.out.println("推荐结果 : "+dataSet.get(entry2.getLabel())+" 评分:"+entry2.getCosineV());
			}
		}
		
	}
	

}
