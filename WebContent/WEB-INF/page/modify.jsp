<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
	var dupl = 0;
	function modify() {
		if(document.getElementById("name").value==""){
			alert("이름를 입력하세요");
			return;
		} else if(document.getElementById("address").value==""){
			alert("주소를 입력하세요");
			return;
		}else if(document.getElementById("phone").value==""){
			alert("전화번호를 입력하세요");
			return;
		} else{
			document.getElementById("signupform").action = "${root}/user.do?act=modify";
			document.getElementById("signupform").submit();
		}
		
	}

	
</script>
</head>
<body>

<div class="container" align="center">
	<div class="col-lg-6" align="center">
		<br><br><br>
		<h2>회원 정보 수정</h2>
		<form id="signupform" method="post" action="">
			<div class="form-group" align="left">
				<label for="id">아이디</label>
				<input type="text" class="form-control" id="id" name="id" value = "${ userinfo.id }" readonly>
			</div>
			<div class="form-group" align="left">
				<label for="pw">비밀번호</label>
				<input type="text" class="form-control" id="pw" name="pw" value = "${ userinfo.pw }" readonly>
			</div>

			<div class="form-group" align="left">
				<label for="name">이름</label>
				<input type="text" class="form-control" id="name" name="name" value = "${ userinfo.name }">
			</div>
			<div class="form-group" align="left">
				<label for="address">주소</label>
				<input type="text" class="form-control" id="address" name="address" value = "${ userinfo.address }">
			</div>
			<div class="form-group" align="left">
				<label for="phone">전화번호</label>
				<input type="text" class="form-control" id="phone" name="phone" value = "${ userinfo.phone }">
			</div>
			
			<button type="button" class="btn btn-primary" onclick="javascript:modify();">수정</button>
			<button type="reset" class="btn btn-warning">초기화</button>
			<button type="button" class="btn btn-primary" onclick="javascript:location.href = '${root}/user.do?act=main';">메인화면</button>
		</form>
	</div>
</div>


</body>
</html>