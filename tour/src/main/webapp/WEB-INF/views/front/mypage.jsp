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
					<h1>간단한 사이드바</h1>
					<p>메뉴가 많아서 한 페이지를 넘으면 세로 스크롤바 생김</p>
				</div>
			</div>
		</div>
	</main>
</body>
</html>