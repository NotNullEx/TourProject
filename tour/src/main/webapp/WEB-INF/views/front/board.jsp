<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../frontcommon/front_header_common.jsp" />
</head>
<body class="d-flex flex-column">
	<main class="flex-shrink-0">
		<jsp:include page="../frontcommon/front_header.jsp" />
		<!-- Page Content-->
		<section class="py-5">
			<div class="container px-5 my-5">
				<div class="row gx-5">
					<div class="col-lg-9">
				
						<input type="hidden" value="${seq}">
 						<c:forEach begin="0" var="list" items="${list}">
							<li class="page-item"><a class="page-link"
								href="/front/board_detail?board_seq=${list.board_seq}">${list.board_title}</a></li>
						</c:forEach> 
					</div>
				</div>
				<button type="button" id="addNoti" class="btn btn-primary" onclick="location.href='/front/createBoard'">게시판 등록</button>
			</div>
		</section>
	</main>
	<jsp:include page="../frontcommon/front_footer.jsp" />
	<jsp:include page="../frontcommon/front_footer_common.jsp" />
</body>
</html>