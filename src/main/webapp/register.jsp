<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%-- <base href="<%=basePath%>"> --%>
    
    <title>注册界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	.img1{
		margin-top: 20px;
		width:150px;
		
	}
	a:HOVER {
	cursor: hand;
}
	</style>
<link rel="stylesheet" href="css/style.css">
  <script type="text/javascript">
var index = 0;
function changeCode(){
var iamgeCode = document.getElementById("codeId");
iamgeCode.src = "AuthImage?name="+index;
index++;
}
</script>
  </head>
<body>
<%
String mesg1=(String)session.getAttribute("mesg1");
if(mesg1==null || "".equals(mesg1)){}
else {
 %>
 

<div class="register-container">
<h1>${mesg1 }</h1><br>
<%} %>
	<h1>注册</h1>
	
	<div class="connect">
		<p>Link the world. Share to world.</p>
	</div>
	
	<form action="register"  method="post" id="loginForm">
		<div>
			<input type="text" name="username" class="username" placeholder="您的用户名" autocomplete="off"/>
		</div>
		<div>
			<input type="password" name="password" class="password" placeholder="输入密码" oncontextmenu="return false" onpaste="return false" />
		</div>
		<div>
			<input type="password" name="confirm_password" class="confirm_password" placeholder="再次输入密码" oncontextmenu="return false" onpaste="return false" />
		</div>
		<div>
			<input type="text" name="phone_number" class="phone_number" placeholder="输入手机号码" autocomplete="off" id="number"/>
		</div>
		<div>
			<input type="email" name="email" class="email" placeholder="输入邮箱地址" oncontextmenu="return false" onpaste="return false" />
		</div>

			<a href="tiaokuan.html">默认同意服务条款</a><br>

 <div>
  <b> &nbsp;&nbsp; 验证码：</b>
&nbsp;&nbsp;<input type="text"  name="code" style="width:100px " />&nbsp;<img src="AuthImage" onclick="changeCode()" id="codeId" class="img1"/></div>

<br>
<div ><a id="img1" onclick="changeCode()">看不清？换一张</a>  </div>

	<button  type="submit">注 册</button>
	</form>
	<a href="login.jsp">
		<button type="button" class="register-tis">已经有账号？</button>
	

</div>

</body>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<!--背景图片自动更换-->
<script src="<%=basePath%>js/supersized.3.2.7.min.js"></script>
<script src="<%=basePath%>js/supersized-init.js"></script>
<!--表单验证-->
<script src="<%=basePath%>js/jquery.validate.min.js?var1.14.0"></script>
</html>
