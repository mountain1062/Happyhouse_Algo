<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="root" value="${pageContext.request.contextPath}" />
<c:set var="userinfo" value="${ userinfo }" />

<script type="text/javascript">
	function login() {
		if (document.getElementById("id").value == "") {
			alert("아이디를 입력하세요");
			return;
		} else if (document.getElementById("pw").value == "") {
			alert("비밀번호 입력를 입력하세요");
			return;
		} else {
			document.getElementById("loginform").action = "${root}/user.do?act=login";
			document.getElementById("loginform").submit();
		}

	}

	function moveSignUp() {
		document.location.href = "${root}/user.do?act=mvsignup";
	}

	function movefindPW() {
		document.location.href = "${root}/user.do?act=mvfindpw";
	}
	function moveView() {
		document.location.href = "${root}/user.do?act=userview";
	}
</script>
</head>
<body>
	<div align="center">

		<c:if test="${ userinfo == null }">
			<font style="color:red;">${ msg }</font>
			<div class="container" align="center">
				<div class="" align="center">
					<form id="loginform" method="post" action="">
						<input type="hidden" name="act" value="login">
						<div class="form-group" align="center">
							아이디 <input type="text" class="" id="id" name="id" placeholder="" > 비밀번호 <input type="password" class="" id="pw" name="pw" placeholder="" onkeydown="javascript:if(event.keyCode == 13) {login();}">
						</div>
						<div class="form-group" align="center">
							<button type="button" onclick="javascript:login();">로그인</button>
							<button type="button" onclick="javascript:moveSignUp();">회원가입</button>
							<button type="button" onclick="javascript:movefindPW();">비밀번호 찾기</button>
						</div>
					</form>
				</div>
			</div>
		</c:if>

		<c:if test="${ userinfo != null }">
			<div>
				<strong>${ userinfo.name }(${ userinfo.id })</strong>님 환영합니다.
				<button type="button" onclick="location.href = '${ root }/user.do?act=logout'">로그아웃</button>
				<button type="button" onclick="location.href = '${root}/user.do?act=userview'">회원 정보</button>
			</div>
			<!--------------------------------------------------------------- 로그인시 보여줄 추가 버튼 --------------------------------------------------------->


			<!--------------------------------------------------------------- 로그인시 보여줄 추가 버튼 --------------------------------------------------------->
		</c:if>
	</div>