<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../admincommon/admin_header_common.jsp" />
<style type="text/css">
	.table{
		width: 80%;
		margin-left:auto; 
    	margin-right:auto;
	}
	.tableInfo td{
		vertical-align:middle;
		}
	.image{
		width: 350;
	}
</style>
<script type="text/javascript">
	function go_url(){
		var url = $("#url").val();
		location.href(url);
	}

</script>
</head>
<body class="d-flex flex-column">
	<main class="flex-shrink-0">
		<!-- 헤더 -->
		<jsp:include page="../admincommon/admin_header.jsp" />
		<!-- Header-->
		<header class="py-5">
			<div class="container px-5">
				<div class="row justify-content-center">
					<div class="col-lg-8 col-xxl-6">
						<div class="text-center my-5">
							<h1 class="fw-bolder mb-3">서울 관광명소 리스트</h1>
							
							<p class="lead fw-normal text-muted mb-4">서울의 관광지를 한눈에!</p>
							<!-- <a class="btn btn-primary btn-lg" href="#scroll-target">Read
								our story</a> -->
						</div>
					</div>
				</div>
			</div>
		</header>
		<!-- About section one-->
		<section class="py-5 bg-light" id="scroll-target">
			<div><strong><a href="https://t1.daumcdn.net/cfile/tistory/27494B4E51BA8D6B08"></a> 추천!</strong></div>
			<div class="container px-5 my-5">
				<div class="row gx-5 align-items-center">
					<div class="col-lg-6">
						<img class="img-fluid rounded mb-5 mb-lg-0"
							src="https://dummyimage.com/600x400/343a40/6c757d" alt="..." />
					</div>
					<div class="col-lg-6">
						<h2 class="fw-bolder">${sb[0].tour_post_sj }</h2>
						<p class="lead fw-normal text-muted mb-0">test</p>
					</div>
				</div>
			</div>
		</section>
		<!-- About section two-->
		<section class="py-5">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">No.</th>
						<th scope="col">관광지 이미지</th>
						<th scope="col">정보</th>
						<th scope="col">홈페이지 바로가기</th>
					</tr>
				</thead>
				<tbody class="tableInfo">
					<c:forEach var="item" items="${sb}" varStatus="vs">
						<tr>
							<th scope="row">${vs.index }</th>
							<td><img class="image" alt="tt" src="../resources/img/001.jpg"> </td>
							<td><a class="text-decoration-none link-dark" href="/admin/tourDetail?tour_seq=${item.tour_seq}" >${item.tour_post_sj}</a> <br>
								${item.tour_new_address }<br>
								<c:choose>
									<c:when test = "${item.tour_cmmn_fax == null or item.tour_cmmn_fax eq ' '}">
										- <br>
									</c:when>
									<c:otherwise>
							            ${item.tour_cmmn_fax }
							        </c:otherwise>
								</c:choose>
							</td>
							<td><a class="text-decoration-none link-dark" href="${item.tour_cmmn_hmpg_url}" target="_blank">${item.tour_cmmn_hmpg_url}</a></td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>
		<!-- Team members section-->
		<section class="py-5 bg-light">
			<div class="container px-5 my-5">
				<div class="text-center">
					<h2 class="fw-bolder">Our team</h2>
					<p class="lead fw-normal text-muted mb-5">Dedicated to quality
						and your success</p>
				</div>
				<div
					class="row gx-5 row-cols-1 row-cols-sm-2 row-cols-xl-4 justify-content-center">
					<div class="col mb-5 mb-5 mb-xl-0">
						<div class="text-center">
							<img class="img-fluid rounded-circle mb-4 px-4"
								src="https://dummyimage.com/150x150/ced4da/6c757d" alt="..." />
							<h5 class="fw-bolder">Ibbie Eckart</h5>
							<div class="fst-italic text-muted">Founder &amp; CEO</div>
						</div>
					</div>
					<div class="col mb-5 mb-5 mb-xl-0">
						<div class="text-center">
							<img class="img-fluid rounded-circle mb-4 px-4"
								src="https://dummyimage.com/150x150/ced4da/6c757d" alt="..." />
							<h5 class="fw-bolder">Arden Vasek</h5>
							<div class="fst-italic text-muted">CFO</div>
						</div>
					</div>
					<div class="col mb-5 mb-5 mb-sm-0">
						<div class="text-center">
							<img class="img-fluid rounded-circle mb-4 px-4"
								src="https://dummyimage.com/150x150/ced4da/6c757d" alt="..." />
							<h5 class="fw-bolder">Toribio Nerthus</h5>
							<div class="fst-italic text-muted">Operations Manager</div>
						</div>
					</div>
					<div class="col mb-5">
						<div class="text-center">
							<img class="img-fluid rounded-circle mb-4 px-4"
								src="https://dummyimage.com/150x150/ced4da/6c757d" alt="..." />
							<h5 class="fw-bolder">Malvina Cilla</h5>
							<div class="fst-italic text-muted">CTO</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</main>
	<jsp:include page="../admincommon/admin_footer.jsp" />
	<jsp:include page="../admincommon/admin_footer_common.jsp" />
</body>
</html>
