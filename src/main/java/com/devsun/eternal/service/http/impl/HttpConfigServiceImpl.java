package com.devsun.eternal.service.http.impl;

import org.springframework.stereotype.Service;

import com.devsun.eternal.common.Constants;
import com.devsun.eternal.model.http.HttpConfig;
import com.devsun.eternal.service.http.IHttpConfigService;
import com.devsun.framework.service.impl.BaseServiceImpl;

@Service
public class HttpConfigServiceImpl extends BaseServiceImpl<HttpConfig> implements IHttpConfigService {
	
	@Override
	public HttpConfig getResult1Config() {
		return super.selectById(Constants.HTTP_CONFIG_ID_RESULT1);
	}
	
}
