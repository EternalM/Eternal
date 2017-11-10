package com.devsun.eternal.model.mxd2;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.devsun.framework.entity.ObjectTransient;

@Table(name="tb_eternal_e_mxd2_oxanswer")
public class Oxanswer extends ObjectTransient implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	private String title;
	
	private Integer isTrue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(Integer isTrue) {
		this.isTrue = isTrue;
	}
	
}
