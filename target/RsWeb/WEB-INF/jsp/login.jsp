<%--
  Created by IntelliJ IDEA.
  User: Jason
  Date: 2017/8/29
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;

%>
<base href="<%=basePath%>">
<html>
<head>
    <title></title>

    <%--<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/plugins/easyui/themes/metro/easyui.css">--%>

    <%--<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/u8server.css">--%>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/login.css">


    <script type="text/javascript" src="<%=basePath%>/js/plugins/easyui/jquery.min.js"></script>
    <%--<script type="text/javascript" src="<%=basePath%>/js/plugins/easyui/jquery.easyui.min.js"></script>--%>
    <%--<script type="text/javascript" src="<%=basePath%>/js/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>--%>
    <script type="text/javascript" src="<%=basePath%>/js/jquery.md5.js"></script>

</head>
<style>


</style>

<body id=userlogin_body>
<%--<form style="margin-top: 1em" action="" method="post"--%>
<%--<div>--%>
<%--</div>--%>

<div id=user_login>
    <dl>
        <dd id=user_top>
            <ul>
                <li class=user_top_l></li>
                <li class=user_top_c></li>
                <li class=user_top_r></li>
            </ul>
        </dd>

        <dd id=user_main>
            <li class=user_main_l></li>
            <li class=user_main_c>
                <div class=user_main_box>
                    <ul>
                        <li class=user_main_text>
                            用户名：
                        </li>
                        <li class=user_main_input>
                            <input type='text' class=TxtUserNameCssClass id=loginName
                                   name=loginName maxlength="20" onfocus="ts_display()"
                                   onkeydown="return noNumbers(event)"/>
                        </li>
                        <li>
                            <label id="yonghu" style="color: red; font-size: 12px; display: none;">*必填</label>
                        </li>
                    </ul>
                    <ul>
                        <li class=user_main_text>
                            密&nbsp;&nbsp;&nbsp;&nbsp;码：
                        </li>
                        <li class=user_main_input>
                            <input type=password class=TxtPasswordCssClass id=loginPwd
                                   name=loginPwd onfocus="pwd_display()"
                                   onkeydown="return noNumbers(event)"/>
                        </li>
                        <li>
                            <label id="pwd" style="color: red; font-size: 12px; display: none;">*必填</label>
                        </li>
                    </ul>
                    <ul>
                        <li class=user_main_text></li>
                        <li class=user_main_input>
                            <label id="ts" style="color: red; font-size: 16px; display: none;">
                            </label>
                        </li>
                    </ul>
                </div>
            </li>
            <li class=user_main_r>
                <input class="IbtnEnterCssClass" style="border-style: none;"
                       type=image src="${basePath}/images/user_botton.gif" onclick="return play();"/>
            </li>
        </dd>
        <dd id=user_bottom>
            <ul>
                <li class=user_bottom_l></li>
                <li class=user_bottom_c></li>
                <li class=user_bottom_r></li>
            </ul>
        </dd>
    </dl>
</div>

<script type="text/javascript">

    function ts_display() {
        document.getElementById("ts").style.display = "none";
        document.getElementById("yonghu").style.display = "none";
    }

    function pwd_display() {
        document.getElementById("pwd").style.display = "none";
        document.getElementById("ts").style.display = "none";
    }

    function play() {
        document.getElementById("ts").style.display = "none";
        if (document.getElementById("loginName").value == "") {
            document.getElementById("yonghu").style.display = "block";
            return false;
        }
        if (document.getElementById("loginPwd").value == "") {
            document.getElementById("pwd").style.display = "block";
            return false;
        }
        var lname = $("#loginName").val();
        var lpwd = $("#loginPwd").val();
        lpwd = $.md5(lpwd);
        $.post('<%=basePath%>/doLogin', {username: lname, password: lpwd}, function (result) {
            if (result.state == 1) {
                location.href = "<%=basePath%>/index"
            } else {
                document.getElementById("ts").style.display = "block";
                document.getElementById("ts").textContent = "提示：" + result.msg;
            }
        }, 'json');

        <%--$.ajax({--%>
            <%--url:"<%=basePath%>/userLogin",--%>
            <%--data:{"userName":lname,"password":lpwd},--%>
            <%--type:"POST",--%>
            <%--dataType:"html",--%>
            <%--success: function(data,textstatus){--%>
<%--//                alert(textstatus);--%>
            <%--},--%>
            <%--error:function(jqxhr,textstatus,error){--%>
<%--//                alert(jqxhr);--%>
<%--//                alert(error);--%>
<%--//                alert(textstatus);--%>
            <%--}--%>
        <%--});--%>

        return true;
    }

    function noNumbers(e) {
        var keynum;
        var keychar;
        keynum = window.event ? e.keyCode : e.which;
        if(keynum == 13)
        {
            play();
        }
    }
</script>
</body>
</html>
