package com.zdhy.platform.knowledge.core;

import java.util.Comparator;
import java.util.List;
/**
 * 计算数据类型
 * @author kylin
 *
 */
public class Entry {
	private String label;
	private List<Float> value;
	private Double cosineV;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Float> getValue() {
		return value;
	}

	public void setValue(List<Float> value) {
		this.value = value;
	}

	public Double getCosineV() {
		return cosineV;
	}

	public void setCosineV(Double cosineV) {
		this.cosineV = cosineV;
	}

	public Entry() {
	}

	public Entry(String label, List<Float> value) {
		this.label = label;
		this.value = value;
	}

	public Entry(String label, List<Float> value, Double cosineV) {
		this.label = label;
		this.value = value;
		this.cosineV = cosineV;
	}

	public static void quickSort(Entry[] data, int start, int end) {
		if (start < end) {
			// 以第一个元素为分界值
			Entry middleNum = data[start];
			int i = start;
			int j = end + 1;
			while (true) {
				// 找到大于分界值的元素的索引或者已经到了end处
				while (i < end && data[++i].getCosineV() <= middleNum.getCosineV())
					;
				// 找到小于分界值的元素的索引或者已经到了start处
				while (j > start && data[--j].getCosineV() >= middleNum.getCosineV())
					;
				if (i < j) {
					// 交换
					Entry temp = data[i];
					data[i] = data[j];
					data[j] = temp;
				} else {
					break;
				}
			}
			Entry temp = data[start];
			data[start] = data[j];
			data[j] = temp;
			// 递归左子序列
			quickSort(data, start, j - 1);
			// 递归右子序列
			quickSort(data, j + 1, end);
		}
	}
	
	public static void quickSortDesc(Entry[] data){
		for (int start = 0, end = data.length - 1; start < end; start++, end--) {
			Entry temp = data[end];
	        data[end] = data[start];
	        data[start] = temp;
	    }
	}

	public static void quickSort(List<Entry> data) {
		Comparator<? super Entry> c = new Comparator<Entry>() {

			public int compare(Entry o1, Entry o2) {

				return o1.getCosineV() < o2.getCosineV() ? 0 : 1;
			}

		};
		data.sort(c);
	}

	@Override
	public String toString() {
		return "Entry [label=" + label + ", value=" + value + ", cosineV=" + cosineV + "]";
	}

}
