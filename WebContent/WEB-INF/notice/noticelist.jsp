<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<%@include file="/WEB-INF/page/navigation.jsp" %>

<div align="center" style="width:70%;margin:0 auto;">
	<h2>notice List2</h2>
	<a href="${root}/notice.do?action=writeform">글 작성하기</a>
	<c:forEach var="list" items="${noticelist}">
		<table class="table table-active">
			<tbody>
				<tr class="table-info">
					<td colspan="3">작성자 : ${list.writer}</td>
				</tr>
				<tr>
					<td style="width:100px;"><strong>${list.no}</strong></td>
					<td class="table-success"><a href="${root}/notice.do?action=detail&no=${list.no}">${list.title} </strong></a></td>
				</tr>
			</tbody>
		</table>
	</c:forEach>
</div>
</body>
</html>