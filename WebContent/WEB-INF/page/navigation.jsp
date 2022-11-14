<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<div align="center" style="background-color:#aaaaaa;">
	<h2>Nav Bar</h2>
	<ul>
		<li><a href="${root}/notice.do?action=list">공지사항</a></li>
		<li>오늘의 뉴스</li>
		<li><a href="${root}/house.do">메인화면</a></li>
	</ul>
	
</div>