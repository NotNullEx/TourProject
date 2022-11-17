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




				<div id="carouselExampleControls" class="carousel slide"
					data-bs-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<div class="card border-0 shadow rounded-3 overflow-hidden">
								<div class="card-body p-0">
									<div class="row gx-0">
										<div class="col-lg-6 col-xl-5 py-lg-5">
											<div class="p-4 p-md-5">
												<div class="badge bg-primary bg-gradient rounded-pill mb-2">
													<c:out value="${data[0].res_name}" />
												</div>
												<div class="h2 fw-bolder">
													<c:out value="${data[0].res_adress}" />
												</div>
												<p>
													<c:out value="${data[0].res_desc}" />
												</p>
											</div>
										</div>
										<div class="col-lg-6 col-xl-7">

											<div class="bg-featured-blog bimg"
												style="background-image: url(https://upload.wikimedia.org/wikipedia/commons/1/1e/Tom%27s_Restaurant%2C_NYC.jpg)"></div>
										</div>
										<!-- <i class="bi bi-arrow-right"></i> -->
									</div>
								</div>
							</div>
						</div>
						<div class="carousel-item">
							<div class="card border-0 shadow rounded-3 overflow-hidden">
								<div class="card-body p-0">
									<div class="row gx-0">
										<div class="col-lg-6 col-xl-5 py-lg-5">
											<div class="p-4 p-md-5">
												<div class="badge bg-primary bg-gradient rounded-pill mb-2">
													<c:out value="${data[1].res_name}" />
												</div>
												<div class="h2 fw-bolder">
													<c:out value="${data[1].res_adress}" />
												</div>
												<p>
													<c:out value="${data[1].res_desc}" />
												</p>
											</div>
										</div>
										<!-- <i class="bi bi-arrow-right"></i> -->
										<div class="col-lg-6 col-xl-7">
											<div class="bg-featured-blog bimg"
												style="background-image: url(/resources/img/${data[1].res_image})"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="carousel-item">
							<div class="card border-0 shadow rounded-3 overflow-hidden">
								<div class="card-body p-0">
									<div class="row gx-0">
										<div class="col-lg-6 col-xl-5 py-lg-5">
											<div class="p-4 p-md-5">
												<div class="badge bg-primary bg-gradient rounded-pill mb-2">
													<c:out value="${data[2].res_name}" />
												</div>
												<div class="h2 fw-bolder">
													<c:out value="${data[2].res_adress}" />
												</div>
												<p>
													<c:out value="${data[2].res_desc}" />
												</p>
											</div>
										</div>
										<!-- <i class="bi bi-arrow-right"></i> -->
										<div class="col-lg-6 col-xl-7">
											<div class="bg-featured-blog bimg"
												style="background-image: url(/resources/img/${data[2].res_image})"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>



					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselExampleControls" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"
							style="visibility: hidden;"></span> <span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselExampleControls" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"
							style="visibility: hidden;"></span> <span class="visually-hidden">Next</span>
					</button>
				</div>

			</div>

		</section>

		<div>
			<ul>
				<c:forEach var="area" items="${area}">
					<li><button type="button"
							onclick="location='/admin/selectRestaurantBySection?res_adress_area=${area}'">${area}</button></li>
				</c:forEach>
			</ul>
		</div>

		<!-- Blog preview section-->
		<section class="py-5">
			<table class="table">
				<h2 class="fw-bolder fs-5 mb-4">여기도 한번 둘러보세요!</h2>
				<thead>
					<tr>
						<th scope="col">No.</th>
						<th scope="col">음식점 이미지</th>
						<th scope="col">정보</th>
						<th scope="col">휴일</th>
						<th scope="col">전화번호</th>
					</tr>
				</thead>
				<tbody class="tableInfo">
					<c:forEach var="data" items="${data}" begin="0" varStatus="vs">
						<tr>
							<th scope="row">${vs.index}</th>
							<td><img class="image" alt="..." src="${data.res_image}">
							</td>
							<td><a class="text-decoration-none link-dark"
								href="/admin/restaurantDetail?res_seq=${data.res_seq}">${data.res_name}</a>
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
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>
	</main>
	<jsp:include page="../admincommon/admin_footer.jsp" />
	<jsp:include page="../admincommon/admin_footer_common.jsp" />
</body>
</html>