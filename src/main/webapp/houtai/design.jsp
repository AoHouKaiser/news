﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <style>
.c {
	border-style: solid;
	width: 400px;
	height: 260px;
	margin: 4 23 0 23;
	border-radius: 5;
	display: block;
	background: #fff;
	margin: 10% auto;
}

.addMask {
	width: 50%;
	height: 50%;
	position: absolute;
	display: none;
	margin-top: -600px;
}
</style>
  <script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
function del(nid) {
		if (window.confirm("你确定要删除该新闻信息吗？")) {
			window.location.href="<%=request.getContextPath()%>/delnews?nid="+nid;
		}
	}
function delnn() {
	if (window.confirm("你确定要删除这些新闻信息吗？")) {
		myform.submit();
	}
}
</script>
	<script type="text/javascript" src="<%=basePath %>/js/jquery-1.12.4.min.js"></script>
	   <script type="text/javascript" src="<%=basePath %>/js/jquery-1.8.3.js"></script> 
<script type="text/javascript">
	//页面上添加按钮的提交验证,通过就提交
	function check1() {
		if ($("#addPwd").val() == null || $("#addPwd").val() == "") {
			alert("二级验证密码不能为空！");
			return false;
		}
		return true;
	}

	//页面上添加按钮的关闭按钮切换方法
	function showAddMask(flag) {
		if (flag== 'true') {
			$(".addMask").css("display", "block");//让.addMask元素显示
		} else {
			$(".addMask").css("display", "none");//让.addMask隐藏
		}
	}
	
</script>
   <%--  <base href="<%=basePath%>"> --%>
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css"/>
    <script src="SpryAssets/SpryValidationCheckbox.js" type="text/javascript"></script>
    <link href="SpryAssets/SpryValidationCheckbox.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
	if(session.getAttribute("newslist")==null){
	pageContext.forward("../xinwenguanli?flag=1");
	}%>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="index.html" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
         <li><a class="on" href="<%=path%>/houtai/index.jsp">首页</a>
					</li>
					<li><a href="<%=path%>/index.jsp" target="_blank">网站首页</a>
					</li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
           	<li><a  href="javascript:void(0)"  onclick="showAddMask('true');" >
								管理员</a>
					</li>

					<li><a href="#">退出</a>
					</li>
            </ul>
        </div>
    </div>
</div>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
         <li><a href="#"><i class="icon-font">&#xe003;</i>常用操作</a>
						<ul class="sub-menu">
							<li><a href="<%=request.getContextPath()%>/xinwenguanli"><i
									class="icon-font">&#xe052;</i>新闻管理</a>
							</li>
							<li><a
								href="<%=request.getContextPath()%>/tpchshihua?flag=1"><i
									class="icon-font">&#xe006;</i>分类管理</a>
							</li>
							<li><a href="<%=request.getContextPath()%>/UserInfo"><i
									class="icon-font">&#xe052;</i>用户管理</a>
							</li>
							<li><a  href="javascript:void(0)"  onclick="showAddMask('true');" ><i
									class="icon-font">&#xe006;</i>管理员管理</a>
							</li>
							
								 <li><a
								href="file.jsp"><i
									class="icon-font">&#xe052;</i>文件上传</a>
							</li> 
						
							 <li><a
								href="<%=request.getContextPath()%>/support/SupportInfo"><i
									class="icon-font">&#xe033;</i>广告管理</a>
							</li> 
							
						
							
							
							
						</ul></li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="index.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">新闻管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="<%=request.getContextPath()%>/searchNews" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="120">选择分类:</th>
                            <td>
                                <select name="search-sort" id="">
                                    <option value="0">全部</option>
                                    <c:forEach items="${sessionScope.newstypelist1 }" var="i" varStatus="suoyin1">
                                    <option value="${i.ntid }">${i.ntname}</option>
                                    </c:forEach>
                                </select>
                            </td>
                          <th width="70">关键字:</th>
                            <td><input class="common-text" placeholder="关键字" name="title" value="" id="" type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post" action="<%=request.getContextPath()%>/moredel">
                <div class="result-title">
                    <div class="result-list">
                        <a href="insert.jsp"><i class="icon-font"></i>新增作品</a>
                         <a id="batchDel" onclick="delnn()"><i class="icon-font"></i>批量删除</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th class="tc" width="5%"><input class="allChoose" name="" type="checkbox"></th>
                          <th width="4%">ID</th>
                          <th width="14%">标题</th>
                          <th width="11%">类别</th>
                          <th width="4%">热点</th>
                          <th width="9%">发布人</th>
                          <th width="23%">更新时间</th>
                          <th width="8%">可否评论</th>
                          <th width="13%">操作</th>
                      </tr>
                      <c:forEach items="${sessionScope.newslist }" var="i" varStatus="suoyin1">
                        <tr>
                            <td class="tc">
                            <input name="id${suoyin1.index+1 }" value="1" type="checkbox"></td>
                            <td>${i.nid }<input type="hidden" name="nid${suoyin1.index+1 }" value="${i.nid }"/></td>
                            <td title="${i.title }"><a target="_blank" href="<%=request.getContextPath()%>/xinwenchushihua?flag=${i.nid }" title="${i.title }">${i.title }</a>
                            </td>
                            <td>
                             <c:forEach items="${sessionScope.newstypelist2 }" var="pt" varStatus="suoyin2" >
                             <c:if  test="${pt.ntid ==i.ntid }">${pt.ntname}</c:if>
                            </c:forEach>
                            </td>
                            <td><input name="hot" type="checkbox" disabled id="hot" <c:if test="${i.hot==0}">checked</c:if>/></td>
                            <td>${i.author}</td>
                            <td><fmt:formatDate value="${i.ntime }" pattern="yyyy-MM-dd" />
                            
                            </td>
                            <td><span id="sprycheckbox1">
                              <input name="ping"  type="checkbox" disabled id="ping" <c:if test="${i.isreview==1}">checked</c:if>></span></td>
              <td>
                                <a href="../gainews?flag=${i.nid }">修改</a>
                                <a href="#" onclick="del(${i.nid})">删除</a>
                            </td>
                        </tr></c:forEach>
                    </table>
                    <div class="list-page"> </div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
<script type="text/javascript">
//<!--
//var sprycheckbox1 = new Spry.Widget.ValidationCheckbox("sprycheckbox1");
//-->
</script>
	<div class="addMask" align="center">
		<div class="c" align="center">

			<div
				style="background-color:#173e65;height:20px;color:#fff;font-size:12px;padding-left:7px;">

				二级密码验证<font style="float:right;padding-right:10px;"
					onclick="showAddMask('false');">X</font>
			</div>
			<br> <br> <br>
			<form id="addForm" action="<%=path %>/manager/check" method="post"
				onsubmit="return check1();">
				二级密码：<input type="text" id="addPwd" name="managerPassword"
					style="width:120px" /> <br /> <br> <input type="submit"
					value="验证"
					style="background-color:#173e65;color:#ffffff;width:70px;" />
			</form>

		</div>
	</div>
</body>
</html>