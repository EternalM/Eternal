package com.devsun.eternal.controller.api;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devsun.eternal.common.Constants;
import com.devsun.eternal.model.http.HttpConfig;
import com.devsun.eternal.model.http.HttpResult1;
import com.devsun.eternal.service.http.IHttpConfigService;
import com.devsun.eternal.service.http.IHttpResult1Service;
import com.devsun.eternal.util.HttpDownUtil;
import com.devsun.tool.base.DateUtil;
import com.devsun.tool.base.FileUtil;
import com.devsun.tool.base.HttpUtil;
import com.devsun.tool.base.StringUtil;

@Controller
@RequestMapping("/api")
public class JsonApiController {
	
	@Autowired
	private IHttpConfigService httpConfigService;
	
	@Autowired
	private IHttpResult1Service httpResult1Service;

	@RequestMapping(value = "/result1.json", method = RequestMethod.GET)
	@ResponseBody
	public String result1(){
		HttpConfig httpConfig = httpConfigService.getResult1Config();
		if(httpConfig==null){
			return "config not found";
		}
		//临时变量
		String content,url,imageName,imagePath;
		String[] contentArray;
		int index;
		Map<String, String> parameters;
		HttpResult1 httpResult1;
		int pages = 0;
		//获取总页数
		try {
			parameters = new HashMap<String, String>();
			content = httpGet(httpConfig, Constants.HTTP_RESULT1_HOME + "/", parameters);
			contentArray = content.split("\n");
			index = 0;
			for(String tmp : contentArray){
				if(tmp.indexOf("<div class=\"pages\">")>=0){
					break;
				}
				index++;
			}
			String totalPageStr = contentArray[index+1];
			totalPageStr = totalPageStr.substring(totalPageStr.indexOf("页次:1/")+"页次:1/".length());
			totalPageStr = totalPageStr.substring(0, totalPageStr.indexOf("页"));
			pages = Integer.parseInt(totalPageStr);
		} catch (Exception e) {
			e.printStackTrace();
			return "get pages error";
		}
		//获取每页内容
		boolean has = false;
		for(int i=1;i<=pages;i++){
			try {
				if(i==1){
					url = "/index.shtml";
				}
				else{
					url = "/index"+i+".shtml";
				}
				content = httpGet(httpConfig, Constants.HTTP_RESULT1_HOME + url, parameters);
				contentArray = content.split("\n");
				for(String tmp : contentArray){
					if(tmp.indexOf("<li><div class=\"cover\">")<0){
						continue;
					}
					//pUrl
					String pUrl = tmp.substring(tmp.indexOf("href=\"")+"href=\"".length());
					pUrl = pUrl.substring(0, pUrl.indexOf("\""));
					//uuid
					String uuid = pUrl.substring(Constants.HTTP_RESULT1_HOME.length()+1,pUrl.length()-1);
					//验证uuid是否重复
					if(httpResult1Service.selectByUuid(uuid)!=null) {
						has = true;
						break;
					}
					//image
					String image = tmp.substring(tmp.indexOf("src=\"")+"src=\"".length());
					image = image.substring(0, image.indexOf("\""));
					image = image.replaceAll("\\\\", "/");
					imagePath = image.substring(0, image.lastIndexOf("/"));
					imageName = image.substring(image.lastIndexOf("/")+1);
					//保存image
					String uploadPath = JsonApiController.class.getResource("/").getPath();
					uploadPath = uploadPath.substring(1,uploadPath.indexOf("WEB-INF"));
					uploadPath += "upload/";
					if(!new File(uploadPath+imagePath).exists()) {
						FileUtil.createDirs(uploadPath+imagePath);
					}
					HttpDownUtil.downLoadFromUrl("http://"+httpConfig.getHost()+image, imageName, uploadPath+imagePath);
					//name
					String name = tmp.substring(tmp.indexOf("alt=\"")+"alt=\"".length());
					name = name.substring(0, name.indexOf("\""));
					//date
					String dateStr = tmp.substring(tmp.indexOf("<em>更新：")+"<em>更新：".length());
					dateStr = dateStr.substring(0, dateStr.indexOf("</em>"));
					//执行保存
					httpResult1 = new HttpResult1();
					httpResult1.setId(StringUtil.getUUID());
					httpResult1.setUuid(uuid);
					httpResult1.setName(name);
					httpResult1.setImage(imagePath+"/"+imageName);
					httpResult1.setpUrl(pUrl);
					httpResult1.setDate(DateUtil.getDate(dateStr, "yyyy-MM-dd"));
					httpResult1Service.insert(httpResult1);
				}
				System.out.println("page " + i + " success");
				//已经补充到最新版则跳出
				if(has) {
					break;
				}
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(i+" page error");
				break;
			}
		}
		return "1";
	}
	
	@RequestMapping(value = "/result2.json", method = RequestMethod.GET)
	@ResponseBody
	public String result2(){
//		HttpConfig httpConfig = httpConfigService.getResult1Config();
//		if(httpConfig==null){
//			return "config not found";
//		}
		HttpConfig httpConfig = new HttpConfig();
		//1
		httpConfig.setHost("www.75tvtv.com/list/index57.html");
		//2
		httpConfig.setHost("www.75tvtv.com/view/index29382.html");
		//3
		httpConfig.setHost("www.75tvtv.com/player/index29382.html?29382-0-0");
		//临时变量
		String content,url,imageName,imagePath;
		String[] contentArray;
		int index;
		Map<String, String> parameters;
		HttpResult1 httpResult1;
		int pages = 0;
		parameters = new HashMap<String, String>();
		content = httpGet(httpConfig, "", parameters);
		System.out.println(content);
		//4
		HttpDownUtil.downLoadFromUrl("https://2017mp4.54popo.com/e/20171116/17/1/xml/91_f96d5f4531d542bbba02a2786c8e0519.mp4", "91_f96d5f4531d542bbba02a2786c8e0519.mp4", "/Volumes/HDD/Users/Eternal/Downloads/");
		return "1";
	}
	
	private String httpGet(HttpConfig httpConfig, String url, Map<String, String> parameters){
		String reqUrl = "http://"+httpConfig.getHost()+url;
		return HttpUtil.getInstance().doGet(reqUrl, parameters, "gbk");
	}
}
