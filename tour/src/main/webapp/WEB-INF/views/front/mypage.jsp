<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<jsp:include page="../frontcommon/front_header_common.jsp" />
<body>
	<main class="flex-shrink-0">
		<jsp:include page="../frontcommon/front_header.jsp" />
		<div id="page-wrapper">
			<jsp:include page="../frontcommon/front_sidebar.jsp" />
			<!-- 본문 -->
			<div id="page-content-wrapper">
				<div class="container-fluid">
					<h1>My Page</h1>
					<p>내 정보 페이지 입니다. 개인정보 유출에 주의해주세요.</p>
				</div>
			</div>
		</div>
	</main>
</body>
</html>