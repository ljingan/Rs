<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<title>经销商支持系统管理</title>
		<%@ include file="/common/meta.jsp"%>
		<style>
.TxtUserNameCssClass {
	BORDER-TOP-WIDTH: 0px;
	PADDING-LEFT: -25px;
	BORDER-LEFT-WIDTH: 0px;
	BACKGROUND: url(assets/user_login_name.gif) no-repeat;
	BORDER-BOTTOM-WIDTH: 0px;
	WIDTH: 165px;
	LINE-HEIGHT: 20px;
	HEIGHT: 21px;
	BORDER-RIGHT-WIDTH: 0px
}

.TxtPasswordCssClass {
	BORDER-TOP-WIDTH: 0px;
	PADDING-LEFT: -25px;
	BORDER-LEFT-WIDTH: 0px;
	BACKGROUND: url(assets/user_login_password.gif) no-repeat;
	BORDER-BOTTOM-WIDTH: 0px;
	WIDTH: 165px;
	LINE-HEIGHT: 20px;
	HEIGHT: 21px;
	BORDER-RIGHT-WIDTH: 0px
}

.loginClass {
	BORDER-TOP-WIDTH: 0px;
	PADDING-LEFT: 25px;
	BORDER-LEFT-WIDTH: 0px;
	BACKGROUND: url(assets/user_botton.gif) no-repeat;
	BORDER-BOTTOM-WIDTH: 0px;
	WIDTH: 111px;
	LINE-HEIGHT: 122px;
	HEIGHT: 122px;
	BORDER-RIGHT-WIDTH: 0px
}

body {
	margin-left: 0px;
	margin-top: 100px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
	</head>
	<body>
		<table align="center" background="assets/login_bg.jpg" width="589"
			height="342">
			<tr height=90></tr>
			<tr align="center" height="25">
				<td>
					用户名：
					<input type="text" name="name" class=TxtUserNameCssClass />
				</td>
			</tr>
			<tr align="center" height="25">
				<td>
					密&nbsp;&nbsp;码：
					<input type="text" name="password" class="TxtPasswordCssClass">
				</td>
			</tr>
			<tr >
				<td align="center">
					<input type="submit" value="登录" class="loginClass">
				</td>
			</tr>
		</table>

		<span id=ValrUserName style="DISPLAY: none; COLOR: red"></span>
		<span id=ValrPassword style="DISPLAY: none; COLOR: red"></span>
		<span id=ValrValidateCode style="DISPLAY: none; COLOR: red"></span>
		<div id=ValidationSummary1 style="DISPLAY: none; COLOR: red"></div>

		<div></div>
	</body>
</html>
