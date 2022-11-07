<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../admincommon/admin_header_common.jsp" />
</head>
<style>
.bimg {
	max-height: 400px;
	max-width: 500px;
	background-repeat: no-repeat;
	background-position: center;
	object-fit: cover;
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
					<c:forEach var="data" items="${data}" begin="0" end="50" varStatus="vs">
						<tr>
						<th scope="row">${vs.index}</th>
						<td><img class="image bimg" alt="..." src="${data.even_main_img}">
							</td>
							<td>${data.even_title}</td>
							<td> 이용대상 : ${data.even_use_trgt} <br> 기관명 : 
								 <c:choose>
									<c:when
										test="${data.even_org_name == null or data.even_org_name eq ' '}">
										- <br>
									</c:when>
									<c:otherwise>
							        	${data.even_org_name} <br>
							        </c:otherwise>
								</c:choose>
								출연자 : 
								<c:choose>
									<c:when
										test="${data.even_player == null or data.even_player eq ' '}">
										- <br>
									</c:when>
									<c:otherwise>
							        	${data.even_player} <br>
							        </c:otherwise>
								</c:choose>
								프로그램 : 
								<c:choose>
									<c:when
										test="${data.even_program == null or data.even_program eq ' '}">
										- <br>
									</c:when>
									<c:otherwise>
							        	${data.even_program} <br>
							        </c:otherwise>
								</c:choose>
								기타 : 
								<c:choose>
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
								<c:when test="${data.even_use_fee == null or data.even_use_fee eq ' '}">
									-	<br>
								</c:when>
								<c:otherwise>
									${data.even_use_fee} <br>
								</c:otherwise>
								</c:choose></td>
							<td>
								${data.even_place} <br> ${data.even_date}
							</td>
							<td><a class="text-decoration-none link-dark" href="${data.even_org_link}" target="_blank">${data.even_org_link}</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>
	</main>
	<jsp:include page="../admincommon/admin_footer.jsp" />
	<jsp:include page="../admincommon/admin_footer_common.jsp" />
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>
