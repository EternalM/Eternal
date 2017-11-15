package com.devsun.eternal.controller.api.mxd2;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devsun.eternal.controller.BaseController;
import com.devsun.eternal.model.mxd2.Oxanswer;
import com.devsun.eternal.service.mxd2.IOxanswerService;

@Controller
@RequestMapping("/api/mxd2")
public class JsonApiOxanswerController extends BaseController {
	
	@Autowired
	private IOxanswerService oxanswerService;
	
	@RequestMapping(value = "/oxanswer/all.json", method = RequestMethod.GET)
	@ResponseBody
	public void oxanswerAll(HttpServletResponse response){
		List<Oxanswer> list = oxanswerService.getAll();
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("var tdQuestions={'xlinfo':{");
		int index = 0;
		for(Oxanswer oxanswer : list){
			if(index!=0){
				stringBuffer.append(",");
			}
			stringBuffer.append("'").append(index).append("'").append(":{'question':'").append(oxanswer.getTitle()).append("','opt1':'").append(oxanswer.getIsTrue()==1?"O":"X").append("'}");
			index++;
		}
		stringBuffer.append("}}");
		super.printText(response, stringBuffer.toString());
	}

}
