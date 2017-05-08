package com.module.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8464733130622569517L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	/**
	 * 名字
	 */
	@Column
	private String name;

	/**
	 * 性别
	 */
	@Column
	private Sex sex;

	/**
	 * 账号
	 */
	@Column
	private String account;

	/**
	 * 归属组织
	 */
	@Column
	private String company;

	/**
	 * 归属组织
	 */
	@Column
	private String moblieNumber;

	/**
	 * 办公电话
	 */
	@Column
	private String officeNumber;

	/**
	 * 归属组织
	 */
	@Column
	private String address;

	/**
	 * 邮件
	 */
	@Column
	private String mail;

	/**
	 * 职位
	 */
	@Column
	private String role;

	/**
	 * 备注
	 */
	@Column
	private String descrition;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getMoblieNumber() {
		return moblieNumber;
	}

	public void setMoblieNumber(String moblieNumber) {
		this.moblieNumber = moblieNumber;
	}

	public String getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescrition() {
		return descrition;
	}

	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}

}
