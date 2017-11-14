package com.devsun.eternal.test.mxd2;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.devsun.eternal.model.mxd2.Oxanswer;
import com.devsun.eternal.service.mxd2.IOxanswerService;
import com.devsun.eternal.test.base.BaseJunitTest;
import com.devsun.tool.base.FileUtil;

/**
 * 用于OX答题统计
 * @author eternal
 *
 */
public class OxanswerTest extends BaseJunitTest {
	
	//输出路径
	private static final String PATH = "/Volumes/HDD/Users/Eternal/Downloads/mxd2_oxjs.txt";	
	
	@Autowired
	private IOxanswerService oxanswerService;
	
	@Test
	public void run(){
		List<Oxanswer> list = oxanswerService.getAll();
		StringBuffer js = new StringBuffer();
		js.append("var tdQuestions={'xlinfo':{");
		int index = 0;
		for(Oxanswer oxanswer : list){
			js.append("'").append(index).append("'").append(":{'question':'").append(oxanswer.getTitle()).append("','opt1':'").append(oxanswer.getIsTrue()==1?"O":"X").append("'},");
			index++;
		}
		js.append("}}");
		FileUtil.writeFile(PATH, js.toString(), false);
	}

}
