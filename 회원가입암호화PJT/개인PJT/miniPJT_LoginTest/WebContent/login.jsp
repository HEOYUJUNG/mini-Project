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
	<form method ="post" action ="main">
		<input type = "hidden" name = "act" value = "login">
		아이디 : <input type = "text" name = "id" placeholder = "아이디를 입력하세요."><br>
		비밀번호 : <input type = "password" name ="password" placeholder = "비밀번호를 입력하세요.">
		<input type = "submit">
	</form>
	<h4>암호 : </h4>
	<hr>
	
</body>
</html>