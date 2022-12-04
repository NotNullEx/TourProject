<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../admincommon/admin_header_common.jsp" />
</head>

<script type="text/javascript">
	function deleteOne(code) {
		if(confirm("정말 삭제하시겠습니까??")) {
			$.ajax({
				type : "POST",
				data : {
					"code" : code
				},
				url : "/admin/deleteOneRestaurant",
				async : false,
				success : function(data) {
					console.log(data);
					if(data > 0) {
						alert("삭제가 완료되었습니다.");
						location.reload();
					}else {
						alert("삭제에 실패했습니다.");
					}
				}
			});
		}
	}
</script>

<style>
.bimg {
	background-image: url('#');
	background-repeat: no-repeat;
	background-position: center;
	background-size: contain;
	object-fit: cover;
}
</style>



<body class="d-flex flex-column">
	<main class="flex-shrink-0">
		<jsp:include page="../admincommon/admin_header.jsp" />
		<!-- Page Content-->

		<section class="py-5">
			<div class="container px-5">
				<h1 class="fw-bolder fs-5 mb-4">인기있는 맛집</h1>
			</div>
		</section>

		<div>
			<ul>
				<c:forEach var="area" items="${area}">
					<button type="button" class="btn btn-warning" onclick="location='/admin/selectRestaurantBySection?res_adress_area=${area.gu_name}'">${area.gu_name}</button>
				</c:forEach>
			</ul>
		</div>

		<!-- Blog preview section-->
		<section class="py-5">
			<table class="table">
				<h2 class="fw-bolder fs-5 mb-4">여기도 한번 둘러보세요!</h2>
				<button type="button" id="addNoti" class="btn btn-primary" onclick="location.href='/admin/addrestaurant'">음식점 등록</button>
				<thead>
					<tr>
						<th scope="col">No.</th>
						<th scope="col">정보</th>
						<th scope="col">휴일</th>
						<th scope="col">전화번호</th>
						<th scope="col">수정/삭제</th>
					</tr>
				</thead>
				<tbody class="tableInfo">
					<c:set var="num" value="${totalCount - ((curPage-1) * 10) }"/>
					<c:forEach var="data" items="${data}" begin="0" varStatus="vs">
						<tr>
							<th scope="row">${num}</th>
							<td><a class="text-decoration-none link-dark"
								href="/admin/restaurantDetail?res_code=${data.res_code}">${data.res_name}</a>
								<br> ${data.res_adress}<br> <c:choose>
									<c:when
										test="${data.res_open_time == null or data.res_open_time eq ' '}">
										- <br>
									</c:when>
									<c:otherwise>
							        	${data.res_open_time}
							        </c:otherwise>
								</c:choose></td>
							<td><c:choose>
									<c:when
										test="${data.res_rest_day == null or data.res_rest_day eq ' '}">
							       	-	<br>
									</c:when>
									<c:otherwise>
									${data.res_rest_day}
								</c:otherwise>
								</c:choose></td>
							<td>${data.res_telnum}</td>
							<td>
								<button type="button" id="modi" class="btn btn-primary" onclick="location.href='/admin/reviseAll?res_code=${data.res_code}">수정</button>
								<button type="button" id="del" class="btn btn-success" onclick="javascript: deleteOne(${data.res_code})">삭제</button>
							</td>
						</tr>
						<c:set var = "num" value = "${num - 1 }" />
					</c:forEach>
				</tbody>
			</table>
			<ul class="pagination justify-content-center">
				<c:if test="${pageMaker.prev}">
					<li class="page-item"><a class="page-link"
						href='<c:url value="/admin/restaurant?page=${pageMaker.startPage-1}&res_adress_area=${address}"/>'>prev</a></li>	
				</c:if>
				<c:forEach begin="${pageMaker.startPage}"
					end="${pageMaker.endPage }" var="pageNum" varStatus="status">
					<c:choose>
						<c:when test="${pageMaker.cri.page == pageNum}">
							<li class="page-item active" aria-current="page"><a
								class="page-link"
								href='<c:url value="/admin/restaurant?page=${pageNum}&res_adress_area=${address}"/>'>${pageNum}</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item "><a class="page-link"
								href='<c:url value="/admin/restaurant?page=${pageNum}&res_adress_area=${address}"/>'>${pageNum}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage >0}">
					<li class="page-item"><a class="page-link"
						href='<c:url value="/admin/restaurant?page=${pageMaker.endPage+1}&res_adress_area=${address}"/>'>next</a></li>
				</c:if>
			</ul>
		</section>
	</main>
	<jsp:include page="../admincommon/admin_footer.jsp" />
	<jsp:include page="../admincommon/admin_footer_common.jsp" />
</body>
</html>