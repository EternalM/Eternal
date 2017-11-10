package com.devsun.eternal.service.mxd2;

import java.util.List;

import com.devsun.eternal.model.mxd2.Oxanswer;
import com.devsun.framework.service.IBaseService;

public interface IOxanswerService extends IBaseService<Oxanswer> {
	
	public List<Oxanswer> getAll();
	
}
