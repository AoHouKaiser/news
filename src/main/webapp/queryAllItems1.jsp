<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	pageContext.setAttribute("path", request.getContextPath());
%>

<!DOCTYPE HTML>
<html>
<head>
<title>新闻概览</title>
<link href="${path}/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${path}/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${path}/js/bootstrap.min.js"></script>
<style type="text/css">
	a{text-decoration: none; color:black; font-size: 18px; font-family: 微软雅黑;}
a:hover{color:blue;font-size: 19px;}
</style>
</head>

<body>
<%
String ntid=request.getParameter("ntid");
 %>
	<div class="container">
	
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>NEWS SCAN</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
					<a class="btn btn-primary"href="home">返回首页</a>
			</div>
		</div>
		<!-- 表格  -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr>
					<th>编号</th>
						<th>作者</th>
						<th>标题</th>
					</tr>
					<c:forEach items="${pageInfo.list }" var="news">
						<tr>
							<td>${news.nid}</td>
							<td>${news.author}</td>
							<td><a a href="xinwenchushihua?flag=${news.nid}">${news.title }</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>

		<hr style="height:1px;border:none;border-top:1px solid #ccc;" />
		<!-- 分页导航栏 -->

		<!-- 分页信息 -->
		<div class="row">
			<!-- 分页文字信息，其中分页信息都封装在pageInfo中 -->
			<div class="col-md-6">
				当前第：${pageInfo.pageNum}页，总共：${pageInfo.pages}页，总共：${pageInfo.total}条记录
			</div>

			<!-- 分页条 -->
			<div class="col-md-6">
				<nav aria-label="Page navigation">
					<ul class="pagination">
						<li><a href="${path}/NewsInfo?pn=1&ntid='<%=ntid%>'">首页</a>
						</li>
						<c:if test="${pageInfo.hasPreviousPage }">
							<li><a
								href="${path}/NewsInfo?pn=${pageInfo.pageNum-1}&ntid='<%=ntid%>'"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:if>

						<c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
							<c:if test="${page_Num == pageInfo.pageNum }">
								<li class="active"><a href="#">${ page_Num}</a>
								</li>
							</c:if>
							<c:if test="${page_Num != pageInfo.pageNum }">
								<li><a href="${path}/NewsInfo?pn=${ page_Num}&ntid='<%=ntid%>'">${
										page_Num}</a>
								</li>
							</c:if>
						</c:forEach>
						<c:if test="${pageInfo.hasNextPage }">
							<li><a
								href="${path}/NewsInfo?pn=${pageInfo.pageNum+1}&ntid='<%=ntid%>'"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span> </a>
							</li>
						</c:if>
						<li><a
							href="${path}/NewsInfo?pn=${pageInfo.pages}&ntid='<%=ntid%>'">末页</a>
						</li>
					</ul>
				</nav>
			</div>
		</div>
	</div>

</body>
</html>