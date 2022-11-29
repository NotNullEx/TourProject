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
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">No</th>
									<th scope="col">아이디</th>
									<th scope="col">제목</th>
									<th scope="col">날짜</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="num" value="${totalCount - ((curPage-1) * 10) }"/>
								<c:forEach var="data" items="${list}" varStatus="status">
									<tr>
									<th scope="row"><c:out value="${num}"/></th>
									<td>${data.mem_email}</td>
									<td><a href="/front/board_detail?board_seq=${data.board_seq}">${data.board_title}</a></td>
									<td>${data.board_reg_date}</td>
								</tr>
								<c:set var = "num" value = "${num - 1 }" />
								</c:forEach>
							</tbody>
						</table>
						<ul class="pagination justify-content-center">
							<c:if test="${pageMaker.prev }">
								<li class="page-item"><a class="page-link" href='<c:url value="/front/board?page=${pageMaker.startPage-1}"/>'>prev</a></li>
							</c:if>
							<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum" varStatus="status">
								<c:choose>
									<c:when test = "${pageMaker.cri.page == pageNum}">
										<li class="page-item active" aria-current="page">
											<a class="page-link" href='<c:url value="/front/board?page=${pageNum}"/>'>${pageNum }</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item ">
											<a class="page-link" href='<c:url value="/front/board?page=${pageNum}"/>'>${pageNum }</a>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${pageMaker.next && pageMaker.endPage >0 }">
								<li class="page-item"><a class="page-link" href='<c:url value="/front/board?page=${pageMaker.endPage+1 }"/>'>next</a></li>
							</c:if>
						</ul>
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