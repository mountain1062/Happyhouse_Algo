<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<div align="center" style="max-width:90%;margin:0 auto;background-color:#eeeeee;">
	
	<h3>아파트 정보</h3>
	<form action="${root}/house.do" method="post">
		<input type="hidden" value="listview" name="action"> <input type="submit" value="houseinfo 출력하기">
	</form>
	<div style="background-color:#bfbfbf">
	<form action="${root}/house.do" method="post">
	<input type="hidden" value="listsearch" name="action">
	
	<table class="table table-borderless">
		<tr>
		<td align="right">
			<select name="key" id="key">
				<option value="name" selected="selected">아파트 명</option>
				<option value="dong">동으로 검색</option>
			</select>
			<input type="text" name="word" />
			<input type="submit" value="housedeal 검색"/>
		</td>
		</tr>
		<tr>
		<td>
			<label><input type="checkbox" name="types" value="0" checked>아파트 매매 </label>
			<label><input type="checkbox" name="types" value="1" checked>아파트 전월세</label>
			<label><input type="checkbox" name="types" value="2" checked>연립,주택 매매</label>
			<label><input type="checkbox" name="types" value="3" checked>연립,주택 전월세</label>
		</td>
		</tr>
	</table>
	</form>
	</div>
	<table class="table table-active">
		<tbody>
			<tr class="table-info">
				<td>번호</td>
				<td>동</td>
				<td>아파트 이름</td>
				<td>코드</td>
				<td>건축년도</td>
				<td>지번</td>
				<td>이미지</td>
			</tr>
			<c:forEach var="article" items="${apartments}">
				<tr>
					<td>${article.no}</td>
					<td>${article.dong}</td>
					<td>${article.aptName}</td>
					<td>${article.code}</td>
					<td>${article.buildYear}</td>
					<td>${article.jibun}</td>
					<!-- https://pbs.twimg.com/profile_images/3198535065/f52ff338e1680c24307bcc778513b04d_400x400.jpeg -->
					<td><img  style="width:150px;" src="${root}/img/${article.img}" onerror="this.onerror=null; this.src='${root}/img/다세대주택.jpg';"></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>