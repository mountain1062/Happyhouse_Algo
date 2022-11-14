<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<div style="background-color: #b3b3b3; width: 900px;">
			<h1 style="color: #b30000">회원가입 완료</h1>
		</div>
		<div>
			<button type="button" class="btn btn-primary"
				onclick="javascript:location.href = '${root}/user.do?act=main';">확인</button>
		</div>
	</div>
</body>
</html>