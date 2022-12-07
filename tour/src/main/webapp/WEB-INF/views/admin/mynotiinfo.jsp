<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="../admincommon/admin_header_common.jsp" />
<body>
	<main>
		
		<jsp:include page="../admincommon/admin_header.jsp" />
		<div id="page-wrapper">
			<jsp:include page="../admincommon/admin_sidebar.jsp" />
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">게시번호</th>
						<th scope="col">아이디</th>
						<th scope="col">제목</th>
						<th scope="col">날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="num" value="${totalCount - ((curPage-1) * 10) }"/>
					<c:forEach var="data" items="${list}">
					<tr>
						<th scope="row"><c:out value="${num}"/></th>
						<td><c:out value="${data.admin_name}"/></td>
						<td><a href="/admin/notificationDetail?noti_seq=${data.noti_seq}"><c:out value="${data.noti_title}"/></a></td>
						<td><c:out value="${data.noti_reg_date}"/></td>
					</tr>
					<c:set var = "num" value = "${num - 1 }" />
					</c:forEach>
				</tbody>
			</table>
			<ul class="pagination justify-content-center">
				<c:if test="${pageMaker.prev }">
					<li class="page-item"><a class="page-link" href='<c:url value="/admin/myNotiInfo?page=${pageMaker.startPage-1 }&admin_seq=${seq}"/>'>prev</a></li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum" varStatus="status">
					<c:choose>
						<c:when test = "${pageMaker.cri.page == pageNum}">
							<li class="page-item active" aria-current="page">
								<a class="page-link" href='<c:url value="/admin/myNotiInfo?page=${pageNum }&admin_seq=${seq}"/>'>${pageNum }</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item ">
								<a class="page-link" href='<c:url value="/admin/myNotiInfo?page=${pageNum }&admin_seq=${seq}"/>'>${pageNum }</a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage >0 }">
					<li class="page-item"><a class="page-link" href='<c:url value="/admin/myNotiInfo?page=${pageMaker.endPage+1 }&admin_seq=${seq}"/>'>next</a></li>
				</c:if>
			</ul>
		</div>
	</main>
</body>
</html>