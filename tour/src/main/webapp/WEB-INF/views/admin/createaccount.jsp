<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../admincommon/admin_header_common.jsp"/>
</head>
<body class="d-flex flex-column">
        <main class="flex-shrink-0">
            <jsp:include page="../admincommon/admin_header.jsp"/>
	<form method="post">
		<p>이메일 : <input type="text" name="email">
		<p>패스워드 : <input type="password" name="password">
		<p>이름 : <input type="text" name="name">
		<p>전화번호 : <input type="text" name="phone_num">
		<p><input type="submit" value="등록">
	</form>
	 </main>
        <jsp:include page="../admincommon/admin_footer.jsp"/>
		<jsp:include page="../admincommon/admin_footer_common.jsp"/>
</body>
</html>