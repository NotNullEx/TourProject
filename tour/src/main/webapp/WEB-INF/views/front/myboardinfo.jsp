<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="../frontcommon/front_header_common.jsp" />
<style>
.center {text-align: center;}
</style>
<body>
	<main>
		
		<jsp:include page="../frontcommon/front_header.jsp" />
		<div id="page-wrapper">
			<jsp:include page="../frontcommon/front_sidebar.jsp" />
			<h1 class="fw-bolder fs-5 mb-4 center">내가 쓴 게시물</h1>
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">No</th>
						<th scope="col">이름</th>
						<th scope="col">게시글 제목</th>
						<th scope="col">날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="num" value="${totalCount - ((curPage-1) * 10) }"/>
					<c:forEach var="data" items="${list}" varStatus="status">
						<tr>
						<th scope="row"><c:out value="${num}"/></th>
						<td>${data.mem_name}</td>
						<td>${data.board_title}</td>
						<td>${data.board_reg_date}</td>
					</tr>
					<c:set var = "num" value = "${num - 1 }" />
					</c:forEach>
				</tbody>
			</table>
			<ul class="pagination justify-content-center">
				<c:if test="${pageMaker.prev }">
					<li class="page-item"><a class="page-link" href='<c:url value="/front/myBoardInfo?page=${pageMaker.startPage-1}"/>'>prev</a></li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum" varStatus="status">
					<c:choose>
						<c:when test = "${pageMaker.cri.page == pageNum}">
							<li class="page-item active" aria-current="page">
								<a class="page-link" href='<c:url value="/front/myBoardInfo?page=${pageNum}"/>'>${pageNum }</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item ">
								<a class="page-link" href='<c:url value="/front/myBoardInfo?page=${pageNum}"/>'>${pageNum }</a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage >0 }">
					<li class="page-item"><a class="page-link" href='<c:url value="/front/myBoardInfo?page=${pageMaker.endPage+1 }"/>'>next</a></li>
				</c:if>
			</ul>
		</div>
	</main>
</body>
</html>