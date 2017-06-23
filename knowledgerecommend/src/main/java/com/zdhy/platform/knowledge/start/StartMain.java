package com.zdhy.platform.knowledge.start;

import java.io.StringReader;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class StartMain {
	public static void main(String[] args) {
		
		// List<Float> a=new ArrayList<Float>();
		// a.add(1f);
		// a.add(1f);
		// a.add(2f);
		// a.add(1f);
		// a.add(1f);
		// a.add(1f);
		// a.add(0f);
		// a.add(0f);
		// a.add(0f);
		// List<Float> b=new ArrayList<Float>();
		// b.add(1f);
		// b.add(1f);
		// b.add(1f);
		// b.add(0f);
		// b.add(1f);
		// b.add(1f);
		// b.add(1f);
		// b.add(1f);
		// b.add(1f);
		//
		// double res = CosineCalculate.calculateCosine(a, b);
		// System.out.println(res);
		
//		List<Entry> array = new ArrayList<Entry>();
//		array.add(new Entry("1", null, 0.0));
//		array.add(new Entry("1", null, 0.3));
//		array.add(new Entry("1", null, 0.3));
//		array.add(new Entry("1", null, 0.1));
//		array.add(new Entry("1", null, 0.2));
//		array.add(new Entry("1", null, 0.8));
//		array.add(new Entry("1", null, 0.6));
//		for (Entry entry : array) {
//			System.out.println(entry);
//		}
//		System.out.println("++++++++++++++++");
//		Entry[] array2 = array.toArray(new Entry[]{});
//		Entry.quickSort(array2,0,array2.length-1);
//		List<Entry> arrayList = new ArrayList<Entry>();
//		Entry.quickSortDesc(array2);
//		for (Entry entry : array2) {
//			System.out.println(entry);
//		}
		
		try {
			cutWordTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

	public static void cutWordTest() throws Exception {
//		List<String> res = ModelAction.analyzer(new ParticipleIkCn(), "基于java语言开发的轻量级的中文分词工具包，java语言是一种开发语言");
//		List<String> res1 = ModelAction.analyzer(new ParticipleIkCn(), "邓小平宣布我国政府裁军100万");
//		List<String> enRes = ModelAction.analyzer(new ParticipleIkCn(),
//				"Apache Spark is a fast and general-purpose cluster computing system. It provides high-level APIs in Java, Scala, Python and R, and an optimized engine that supports general execution graphs.");
//		for (String e : enRes) {
//			System.err.println(e);
//		}
		
		String text="基于java语言开发的轻量级的中文分词工具包万";
        StringReader sr=new StringReader(text);
        //true代表不是细颗粒的分词
        IKSegmenter ik=new IKSegmenter(sr, true);
        Lexeme lex=null;
        while((lex=ik.next())!=null){
            System.out.print(lex.getLexemeText()+"|");
        }
	}
}
