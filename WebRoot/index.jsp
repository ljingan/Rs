<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<title>经销商支持系统管理</title>
		<%@ include file="/common/meta.jsp"%>
		<link href="${ctx}/css/xtree.css" type=text/css rel=stylesheet></link>
		<link href="${ctx}/css/login.css" type=text/css rel=stylesheet></link>
		<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	</head>

	<body>
		<form action="register!login" method="post" style="margin-top: 1em"
			width="165" height="160" id="leftTable" border="0" cellpadding="0"
			cellspacing="0">
			<table align="center">
				<caption>
					<h3>
						用户登录
					</h3>
				</caption>
				<tr>
					<td>
						用户名：
						<input type="text" name="name" />
					</td>
				</tr>
				<tr>
					<td>
						密&nbsp;&nbsp;&nbsp;码：
						<input type="text" name="password" />
					</td>
				</tr>
				<tr align="center">
					<td colspan="2">
						<input type="submit" value="登录" />
						<input type="reset" value="重填" />
					</td>
				</tr>
			</table>

			<span id=ValrUserName style="DISPLAY: none; COLOR: red"></span>
			<span id=ValrPassword style="DISPLAY: none; COLOR: red"></span>
			<span id=ValrValidateCode style="DISPLAY: none; COLOR: red"></span>
			<div id=ValidationSummary1 style="DISPLAY: none; COLOR: red"></div>

			<div></div>
		</form>
	</body>
</html>
