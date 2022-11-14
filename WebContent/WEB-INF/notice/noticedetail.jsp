<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>notice detail</h2>
	<a href="${root}/notice.do?action=list">뒤로가기</a>
	
		<table class="table table-active">
			<tbody>
				<tr class="table-info">
					<td>작성자 : ${article.writer}</td>
				</tr>
				<tr>
					<td colspan="2" class="table-danger"><strong>${article.no}. ${article.title} </strong>
					</td>
				</tr>
				<tr>
					<td colspan="2">내용: ${article.content}</td>
				</tr>
			</tbody>
		</table>
	<c:if test="${article.writer == userinfo.id}">
		<form action="${root}/notice.do">
			<input type="hidden" value="updatepage" name="action">
			<input type="hidden" value="${article.no}" name="no">
			<input type="submit" value="수정">
		</form>
		<form action="${root}/notice.do">
			<input type="hidden" value="remove" name="action">
			<input type="hidden" value="${article.no}" name="no">
			<input type="submit" value="삭제">
		</form>
	</c:if>
	
</body>
</html>