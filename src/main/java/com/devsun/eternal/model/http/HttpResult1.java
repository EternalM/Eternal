package com.devsun.eternal.model.http;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.devsun.framework.entity.ObjectTransient;

@Table(name="tb_eternal_e_http_result1")
public class HttpResult1 extends ObjectTransient implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
	private String id;
	
	private String uuid;
	
	private String name;
	
	private String image;//本地upload地址
	
	private String pUrl;//目标页面地址
	
	private Date date;
	
	private Date insertDate;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getpUrl() {
		return pUrl;
	}

	public void setpUrl(String pUrl) {
		this.pUrl = pUrl;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	
}
