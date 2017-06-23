package com.zdhy.platform.knowledge.action;

import java.util.List;

import com.zdhy.platform.knowledge.core.Participle;
/**
 * 模型执行
 * @author kylin
 */
public class ModelAction {
	/**
	 * 驱动不同的模型去执行
	 * @param obj
	 * @param line
	 * @return
	 * @throws Exception
	 */
	public static List<String> analyzer(Participle obj, String line) throws Exception {
		return obj.analyzer(line);
	}
}
