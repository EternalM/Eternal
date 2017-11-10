package com.devsun.eternal.model.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.devsun.framework.entity.ObjectTransient;

@Table(name="tb_e_user")
public class User extends ObjectTransient implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String uuid;
	
	private String username;			// 用户名
	
	private String realname;			// 姓名
	
	private String password;			// 密码
	
	private String email;				// 邮箱
	
	private Integer emailStatus;			// 邮箱验证状态 0:未验证 1:已验证
	
	private Integer status;					// 账号状态 0:禁用 1:使用
	
	private Integer type;					// 账号类型 0：普通用户 1，管理员
	
	private Date createDate;			// 创建时间
	
	private Integer delFlag;			//0.未删除  1.已经删除
	
	private Integer sex;                //性别 0男 1女 2保密
	
	private Date brithday;				//生日
	
	private String position;			//职位
	
	private String photo;				//用户头像
	
	public User clean(){
		this.password = null;
		this.delFlag = null;
		return this;
	}
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(Integer emailStatus) {
		this.emailStatus = emailStatus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBrithday() {
		return brithday;
	}

	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
