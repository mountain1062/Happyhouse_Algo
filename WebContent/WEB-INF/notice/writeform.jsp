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
	<h2>WriteForm</h2>
	<form action="${root}/notice.do" method="post">
		<input type="hidden" value="write" name="action">
		<input type="hidden" value="${article.no}" name="no">
		<div>
			<label for="title">제목:</label> <input type="text" id="title" name="title" value="${article.title}">
		</div>
		<div>
			<label for="content">내용:</label>
			<textarea class="form-control" rows="15" id="content" name="content">${article.content}</textarea>
		</div>
		<c:if test="${not empty article.no}">
			<input type="submit" value="수정" style="float:right">
		</c:if>
		<c:if test="${empty article.no}">
			<input type="submit" value="작성" style="float:right">
		</c:if>
	</form>
</body>
</html>