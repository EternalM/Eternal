package com.devsun.eternal.controller.admin.result1;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devsun.eternal.model.http.HttpConfig;
import com.devsun.eternal.model.http.HttpResult1;
import com.devsun.eternal.service.http.IHttpConfigService;
import com.devsun.eternal.service.http.IHttpResult1Service;
import com.devsun.framework.entity.PageResult;

@Controller
@RequestMapping("/admin/result1")
public class JsonAdminResult1Controller {
	
	@Autowired
	private IHttpResult1Service httpResult1Service;
	@Autowired
	private IHttpConfigService httpConfigService;
	
	public static int PAGESIZE = 10;			//限定每页行数

	@RequestMapping(value = "/list.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(Integer pageIndex,String name,String image, String pUrl){
		Map<String, Object> map = new HashMap<String,Object>();
		HttpResult1 query = new HttpResult1();
		query.setName(name);
		query.setImage(image);
		query.setpUrl(pUrl);
		query.setOrderByClause("date desc");
		PageResult<HttpResult1> pageResult = httpResult1Service.select(query, pageIndex, PAGESIZE);
		map.put("list", pageResult);
		HttpConfig httpConfig = httpConfigService.selectById("result1");
		map.put("imageHome", httpConfig.getHost());
		return map;
	}
	
}
