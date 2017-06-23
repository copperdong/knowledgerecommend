package com.zdhy.platform.knowledge.core;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class ParticipleIkCn extends Participle {

	public List<String> analyzer(String msg)  {
		List<String> list = new ArrayList<String>();
		try {
			StringReader sr=new StringReader(msg);
			//true代表不是细颗粒的分词
			IKSegmenter ik=new IKSegmenter(sr, true);
			Lexeme lex=null;
			while((lex=ik.next())!=null){
			    list.add(lex.getLexemeText());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
