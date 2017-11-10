package com.devsun.eternal.service.mxd2.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsun.eternal.model.mxd2.Oxanswer;
import com.devsun.eternal.service.mxd2.IOxanswerService;
import com.devsun.framework.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.entity.Example;

@Service
public class OxanswerServiceImpl extends BaseServiceImpl<Oxanswer> implements IOxanswerService {
	
	@Override
	public List<Oxanswer> getAll() {
		Example example = new Example(Oxanswer.class);
		return super.selectByExample(example);
	}

}
