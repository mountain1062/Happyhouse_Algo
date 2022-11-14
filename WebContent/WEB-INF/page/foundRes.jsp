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
	<div align="center" style="border: 3px solid black;">
		<div align="center">
			<c:if test="${ foundPW == null }">
				<h3>해당 회원 정보가 존재하지 않습니다.</h3>
			</c:if>
			<c:if test="${ foundPW != null }">
				<h3>찾으시는 비밀번호 : <strong>${ foundPW }</strong></h3>
			</c:if>
			
		</div>
		<div align="center">
			<button type="button" onclick="location.href = '${root}/user.do?act=main'">확인</button>
		</div>
		<br>
	</div>
</body>
</html>