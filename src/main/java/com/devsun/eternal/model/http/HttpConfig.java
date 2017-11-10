package com.devsun.eternal.model.http;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.devsun.framework.entity.ObjectTransient;

@Table(name="tb_eternal_e_http_config")
public class HttpConfig extends ObjectTransient implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
	private String id;
	
	private String name;
	
	private Integer type;//1:result1
	
	private String host;
	
	private Integer status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
