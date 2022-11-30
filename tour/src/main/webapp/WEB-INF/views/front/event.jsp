<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../frontcommon/front_header_common.jsp" />
<style>

.box {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

.tableInfo td{
	text-align : left;
	vertical-align:middle;
}

.tablelay {
	table-layout: fixed;
}
</style>
</head>
<body class="d-flex flex-column">
	<main class="flex-shrink-0">
		<jsp:include page="../frontcommon/front_header.jsp"/>
		<section class="py-5">
			<h2 class="fw-bolder fs-5 mb-4">여러가지 재밌는 이벤트들을 놓치지 마세요!</h2>
			<table class="table table-striped tablelay">
				<thead>
					<tr>		
						<th style="width: 10%">이벤트 이름</th>
						<th style="width: 30%">정보</th>
						<th style="width: 10%">이용료</th>
						<th style="width: 10%">장소</th>
						<th style="width: 10%">날짜</th>
						<th style="width: 30%">홈페이지</th>
					</tr>
				</thead>
				<tbody class="tableInfo">
					<c:forEach var="data" items="${list}" varStatus="vs">
						<tr>
							<td class="box"><a class="text-decoration-none link-dark"
								href="/front/event/detail?even_code=${data.even_code}">${data.even_title}</a></td>
							<td class="box">이용대상 : ${data.even_use_trgt} <br>  
								<c:choose>
									<c:when test="${data.even_org_name == null or data.even_org_name eq ''}">
										
									</c:when>
									<c:otherwise>
							        	기관명 : ${data.even_org_name} <br>
									</c:otherwise>
								</c:choose>  
								<c:choose>
									<c:when test="${data.even_player == null or data.even_player eq ''}">
										
									</c:when>
									<c:otherwise>
							        	출연자 : ${data.even_player} <br>
									</c:otherwise>
								</c:choose> 
								<c:choose>
									<c:when test="${data.even_program == null or data.even_program eq ''}">
										
									</c:when>
									<c:otherwise>
							        	프로그램 : ${data.even_program} <br>
									</c:otherwise>
								</c:choose> 
								<c:choose>
									<c:when test="${data.even_etc_desc == null or data.even_etc_desc eq ''}">
										
									</c:when>
									<c:otherwise>
							        	기타 : ${data.even_etc_desc} <br>
									</c:otherwise>
								</c:choose>
							</td>
							<td class="box">
								<c:choose>
									<c:when test="${data.even_use_fee == null or data.even_use_fee eq ''}">
										무료<br>
									</c:when>
									<c:otherwise>
										${data.even_use_fee}
									</c:otherwise>
								</c:choose>
							</td>
							<td class="box">${data.even_place}</td>
							<td class="box">${data.even_date}</td>
							<td class="box"><a class="d-inline-block text-truncate link-dark" style="max-width: 150px;"
								href="${data.even_org_link}" target="_blank">${data.even_org_link}</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<ul class="pagination justify-content-center">
				<c:if test="${pageMaker.prev}">
					<li class="page-item"><a class="page-link"
						href='<c:url value="/front/event?page=${pageMaker.startPage-1}"/>'>prev</a></li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage}"
					end="${pageMaker.endPage }" var="pageNum" varStatus="status">
					<c:choose>
						<c:when test="${pageMaker.cri.page == pageNum}">
							<li class="page-item active" aria-current="page"><a
								class="page-link" href='<c:url value="/front/event?page=${pageNum}"/>'>${pageNum}</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item "><a class="page-link"
								href='<c:url value="/front/event?page=${pageNum}"/>'>${pageNum}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage >0}">
					<li class="page-item"><a class="page-link"
						href='<c:url value="/front/event?page=${pageMaker.endPage+1}"/>'>next</a></li>
				</c:if>
			</ul>
		</section>
	</main>
	<jsp:include page="../frontcommon/front_footer.jsp" />
	<jsp:include page="../frontcommon/front_footer_common.jsp" />
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>
