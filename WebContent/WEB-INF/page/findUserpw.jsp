<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${ pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>


<script type="text/javascript">
	function find() {
		if (document.getElementById("name").value == "") {
			alert("이름을 입력하세요");
			return;
		} else if (document.getElementById("id").value == "") {
			alert("아이디를 입력하세요");
			return;
		} else {
			document.getElementById("finduserpw").action = "${ root }/user.do?act=findpw";
			document.getElementById("finduserpw").submit();
		}
	}
</script>
</head>
<body>

	<div class="container" align="center">
		<div class="col-lg-6" align="center">
			<br><br><br>
			<h2>비밀번호 찾기</h2>
			<form id="finduserpw" method="post" action="">

				<div class="form-group" align="left">
					<label for="id">아이디</label> <input type="text"
						class="form-control" id="id" name="id" placeholder=""
						value="">
				</div>
				
				<div class="form-group" align="left">
					<label for="name">이름</label> <input type="text" class="form-control"
						id="name" name="name" placeholder="" value="">
				</div>

				<div class="form-group" align="center">
					<button type="button" class="btn btn-primary"
						onclick="javascript:find();">찾기</button>
					<button type="button" class="btn btn-warning"
						onclick="javascript:location.href = '${root}/index.jsp';">메인으로</button>
				</div>

			</form>
		</div>
	</div>
</body>
</html>