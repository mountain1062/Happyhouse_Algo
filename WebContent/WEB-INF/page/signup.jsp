<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
	var dupl = 0;
	function signup() {
		if(document.getElementById("id").value==""){
			alert("아이디를 입력하세요");
			return;
		} else if(document.getElementById("pw").value==""){
			alert("비밀번호를 입력하세요");
			return;
		} else if(document.getElementById("name").value==""){
			alert("이름를 입력하세요");
			return;
		} else if(document.getElementById("address").value==""){
			alert("주소를 입력하세요");
			return;
		}else if(document.getElementById("phone").value==""){
			alert("전화번호를 입력하세요");
			return;
		} else{
			document.getElementById("signupform").action = "${root}/user.do?act=signup";
			document.getElementById("signupform").submit();
		}
		
	}
	function dupl() {
		if(document.getElementById("id").value==""){
			alert("아이디를 입력하세요");
			return;
		}else{
			document.getElementById("duplform").action = "${root}/user.do?act=duplchk";
			document.getElementById("duplform").submit();
			dupl=1;
		}
	}
	
</script>
</head>
<body>

<div class="container" align="center">
	<div class="col-lg-6" align="center">
		<br><br><br>
		<h2>회원가입</h2>
		<form id="signupform" method="post" action="">
			<div class="form-group" align="left">
				<label for="id">아이디</label>
				<input type="text" class="form-control" id="id" name="id">
				<!-- <button type="button" onclick="javascript:dupl();">중복확인</button> 
				<c:if test="${duplres == false }">${ msg }</c:if>
				 -->
			</div>
			<div class="form-group" align="left">
				<label for="pw">비밀번호</label>
				<input type="text" class="form-control" id="pw" name="pw">
			</div>
			<div class="form-group" align="left">
				<label for="name">이름</label>
				<input type="text" class="form-control" id="name" name="name">
			</div>
			<div class="form-group" align="left">
				<label for="address">주소</label>
				<input type="text" class="form-control" id="address" name="address">
			</div>
			<div class="form-group" align="left">
				<label for="phone">전화번호</label>
				<input type="text" class="form-control" id="phone" name="phone" value="ex)000-0000-0000">
			</div>
			
			<button type="button" class="btn btn-primary" onclick="javascript:signup();">회원가입</button>
			<button type="reset" class="btn btn-warning">초기화</button>
			<button type="button" class="btn btn-primary" onclick="javascript:location.href = '${root}/index.jsp';">메인화면</button>
		</form>
	</div>
</div>


</body>
</html>