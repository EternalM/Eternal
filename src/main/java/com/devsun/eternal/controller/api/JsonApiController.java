package com.devsun.eternal.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devsun.eternal.model.mxd2.Oxanswer;
import com.devsun.eternal.service.mxd2.IOxanswerService;

@Controller
@RequestMapping("/api/mxd2/oxanswer")
public class JsonApiController {
	
	@Autowired
	private IOxanswerService oxanswerService;
	
	@RequestMapping(value = "/all.json", method = RequestMethod.GET)
	@ResponseBody
	public String all(){
		List<Oxanswer> list = oxanswerService.getAll();
		StringBuffer js = new StringBuffer();
		js.append("var tdQuestions={'xlinfo':{");
		int index = 0;
		for(Oxanswer oxanswer : list){
			js.append("'").append(index).append("'").append(":{'question':'").append(oxanswer.getTitle()).append("','opt1':'").append(oxanswer.getIsTrue()==1?"O":"X").append("'},");
			index++;
		}
		js.append("}}");
		return js.toString();
	}

}
