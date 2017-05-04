package com.ynwi.ssh.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ynwi.ssh.beans.UserForm;
import com.ynwi.ssh.service.UserManager;
import com.ynwi.ssh.serviceImpl.UserManagerImpl;

public class RegisterTestAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private UserForm user;

	private UserManager userManager;

	public UserForm getUser() {
		return user;
	}

	public void setUser(UserForm user) {
		this.user = user;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	/**
	 * 用户登录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		// 调用业务逻辑组件的valid方法来
		// 验证用户输入的用户名和密码是否正确
		return SUCCESS;
	}

	public String execute() {
		try {
			this.setUserManager(new UserManagerImpl());
			userManager.regUser(user);
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

}