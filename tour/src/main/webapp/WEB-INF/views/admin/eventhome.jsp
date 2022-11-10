<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../admincommon/admin_header_common.jsp" />
</head>
<script type="text/javascript">
	
</script>

<style>
.bimg {
	max-height: 400px;
	max-width: 500px;
	background-repeat: no-repeat;
	background-position: center;
	object-fit: cover;
}

.pageInfo {
	list-style: none;
	display: inline-block;
	margin: 50px 0 0 100px;
}

.pageInfo li {
	float: left;
	font-size: 20px;
	margin-left: 18px;
	padding: 7px;
	font-weight: 500;
}

a:link {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}

a:hover {
	color: black;
	text-decoration: underline;
}

.active {
	background-color: #cdd5ec;
}

}
</style>
<body class="d-flex flex-column">
	<main class="flex-shrink-0">
		<jsp:include page="../admincommon/admin_header.jsp" />
		<section class="py-5">
			<h2 class="fw-bolder fs-5 mb-4">여러가지 재밌는 이벤트들을 놓치지 마세요!</h2>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">No.</th>
						<th scope="col">이벤트 이미지</th>
						<th scope="col">이벤트 이름</th>
						<th scope="col">정보</th>
						<th scope="col">이용료</th>
						<th scope="col">장소 및 날짜</th>
						<th scope="col">홈페이지</th>
					</tr>
				</thead>
				<tbody class="tableInfo">
					<c:forEach var="data" items="${data}" begin="${strt}" end="${end}"
						varStatus="vs">
						<tr>
							<th scope="row">${vs.index}</th>
							<td><img class="image bimg" alt="..."
								src="${data.even_main_img}"></td>
							<td><a class="text-decoration-none link-dark"
								href="/admin/eventDetail?even_code=${data.even_code}">${data.even_title}</a></td>
							<td>이용대상 : ${data.even_use_trgt} <br> 기관명 : <c:choose>
									<c:when
										test="${data.even_org_name == null or data.even_org_name eq ' '}">
										- <br>
									</c:when>
									<c:otherwise>
							        	${data.even_org_name} <br>
									</c:otherwise>
								</c:choose> 출연자 : <c:choose>
									<c:when
										test="${data.even_player == null or data.even_player eq ' '}">
										- <br>
									</c:when>
									<c:otherwise>
							        	${data.even_player} <br>
									</c:otherwise>
								</c:choose> 프로그램 : <c:choose>
									<c:when
										test="${data.even_program == null or data.even_program eq ' '}">
										- <br>
									</c:when>
									<c:otherwise>
							        	${data.even_program} <br>
									</c:otherwise>
								</c:choose> 기타 : <c:choose>
									<c:when
										test="${data.even_etc_desc == null or data.even_etc_desc eq ' '}">
										- <br>
									</c:when>
									<c:otherwise>
							        	${data.even_etc_desc} <br>
									</c:otherwise>
								</c:choose>
							</td>
							<td><c:choose>
									<c:when
										test="${data.even_use_fee == null or data.even_use_fee eq ' '}">
									-	<br>
									</c:when>
									<c:otherwise>
									${data.even_use_fee} <br>
									</c:otherwise>
								</c:choose></td>
							<td>${data.even_place}<br> ${data.even_date}
							</td>
							<td><a class="text-decoration-none link-dark"
								href="${data.even_org_link}" target="_blank">${data.even_org_link}</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div class="pageInfo_wrap">
				<div class="pageInfo_area">
					<ul id="pageInfo" class="pageInfo">
						<!-- 이전페이지 버튼 -->
						<c:if test="${page.prev}">
							<li class="pageInfo_btn previous"><a
								href="/admin/event?num=${page.startPage-1}">Previous</a></li>
						</c:if>

						<!-- 각 번호 페이지 버튼 -->
						<c:set var="index" value="${index}"/>
						<c:forEach var="num" begin="${page.startPage}"
							end="${page.endPage}">
							<c:choose>
								<c:when test="${num == index}">
									<li class="pageInfo_btn active"><a
										href="/admin/event?num=${num}">${num}</a></li>
								</c:when>
								<c:otherwise>
									<li class="pageInfo_btn"><a
										href="/admin/event?num=${num}">${num}</a></li>
								</c:otherwise>
							</c:choose>
							
						</c:forEach>
						
						<!-- 다음페이지 버튼 -->
						<c:if test="${page.next}">
							<li class="pageInfo_btn next"><a href="/admin/event?num=${page.endPage + 1}">Next</a></li>
						</c:if>
					</ul>
				</div>
			</div>

			<form id="moveForm" method="get">
				<input type="hidden" name="pageNum" value="${page.cri.pageNum}">
				<input type="hidden" name="amount" value="${page.cri.amount}">
			</form>
		</section>
	</main>
	<jsp:include page="../admincommon/admin_footer.jsp" />
	<jsp:include page="../admincommon/admin_footer_common.jsp" />
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>
