package com.zdhy.platform.knowledge.core;

import java.util.List;
import java.util.Map;

/**
 * 余弦值计算
 * @author kylin
 *
 */
public class CosineCalculate {
	/**
	 * 余弦值主计算
	 * @param a
	 * @param b
	 * @return
	 */
	public static double calculateCosine(List<Float> a, List<Float> b) {
		// 点乘
		Float tmpNum = 0.0f;
		for (int i = 0; i < a.size(); i++) {
			tmpNum += a.get(i) * b.get(i);
		}
		// 求平方
		double res = tmpNum / (Math.sqrt(squares(a)) * Math.sqrt(squares(b)));
		return res;
	}

	/**
	 * 求平方和
	 * @param a
	 * @return
	 */
	private static Float squares(List<Float> a) {
		Float res = 0f;
		for (Float ai : a) {
			res += ai * ai;
		}
		return res;
	}
	
	/**
	 * 求核心点最近的点
	 * @param map
	 * @return
	 */
	public static Entry minCoreValue(Map<Entry,Double> map){
		Entry en = null;
		Double min = 0.0;
		for (java.util.Map.Entry<Entry,Double> et : map.entrySet()) {
			if(en==null&&min==0.0){
				en = et.getKey();
				min = et.getValue();
			}else{
				if(et.getValue()<min){
					en = et.getKey();
				}
			}
		}
		return en;
	}

}
