<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- taglib 지시자를 이용해서 사용하고자 하는 기능이 있는 라이브러리 사용 (여기서는 core 라이브러리 사용) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>miniPJT 회원가입&로그인 암호화</title>
</head>

<body>
	<h1 align = "center">유댕둥당 회원가입&로그인 PJT</h1>
	<h2>회원가입</h2>
	<hr>
	<br>
	<form method ="post" action ="main">
		<input type = "hidden" name = "act" value = "regist">
		아이디 : <input type = "text" name = "id" placeholder = "아이디를 입력하세요."><br>
		비밀번호 : <input type = "password" name ="password" placeholder = "비밀번호를 입력하세요.">
		<input type = "submit">
	</form>
	<br>
	<hr>
	
	<h2>로그인</h2>
	<%-- session에서 loginUser를 가져와서 존재 여부에 따라 로그인 폼 또는 사용자 정보를 출력한다. --%>
	<%-- loginUser 존재하지 않는 경우 --%>
	<c:if test="${empty loginUser}">
		<form method ="post" action ="main">
			<input type = "hidden" name = "act" value = "login">
			아이디 : <input type = "text" name = "id" placeholder = "아이디를 입력하세요."><br>
			비밀번호 : <input type = "password" name ="password" placeholder = "비밀번호를 입력하세요.">
			<input type = "submit">
		</form>
	</c:if>
	<%-- loginUser 존재하는 경우 --%>
	<c:if test="${!empty loginUser}">
		<div>
			${loginUser.id}님 반갑습니다.  
			<a href = "main?act=logout">로그아웃</a>
		</div>
		<h4>암호 : ${loginUser.password}</h4>
	</c:if>

	<hr>
	
</body>

<script>
	// req 영역에 msg라는 이름의 attribute가 있다면 화면에 alert으로 출력한다.
	let msg = "${msg}";
	if (msg) {
		alert(msg) 
	}
</script>

</html>