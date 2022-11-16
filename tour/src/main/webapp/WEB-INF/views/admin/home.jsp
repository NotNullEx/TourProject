<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<meta name="referrer" content="no-referrer-when-downgrade" />
<head>
<jsp:include page="../admincommon/admin_header_common.jsp" />
</head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
    	
    	
</script>
<body class="d-flex flex-column h-100">
	<main class="flex-shrink-0">
		<jsp:include page="../admincommon/admin_header.jsp" />
		<!-- Header-->
		<header class="bg-dark py-5">
			<div class="container px-5">
				<div class="row gx-5 align-items-center justify-content-center">
					<div class="col-lg-8 col-xl-7 col-xxl-6">
						<div class="my-5 text-center text-xl-start">

							<h1 class="display-5 fw-bolder text-white mb-2">
								<a href="/admin/tourDetail?tour_seq=${sb[0].tour_seq}"
									class="text-decoration-none link-light">${sb[0].tour_post_sj}</a>
							</h1>
							<p class="lead fw-normal text-white-50 mb-4">${sb[0].tour_new_address}</p>
						</div>
					</div>
					<div class="col-xl-5 col-xxl-6 d-none d-xl-block text-center">
						<img class="img-fluid rounded-3 my-5" src="https://dummyimage.com/600x400/343a40/6c757d" alt="..." />
					</div>
				</div>
			</div>
		</header>

		<section class="py-5">
			<div class="container px-5 my-5">
				<div class="row gx-5 justify-content-center">
					<div class="col-lg-8 col-xl-6">
						<div class="text-center">
							<h2 class="fw-bolder">요즘 핫한 서울 볼거리!</h2>
							<p class="lead fw-normal text-muted mb-5">2030세대들이 가볼만한 이쁜곳!</p>
						</div>
					</div>
				</div>
				<div class="row gx-5">
					<%-- <c:forEach var="item" items="${sb}">
                    	<div class="col-lg-4 mb-5">
                            <div class="card h-100 shadow border-0">
                                <img class="card-img-top" src="https://dummyimage.com/600x350/ced4da/6c757d" alt="..." />
                                <div class="card-body p-4">
                                    <div class="badge bg-primary bg-gradient rounded-pill mb-2 d-inline-block text-truncate" style="max-width: 150px;">
                                    	<a class="text-decoration-none link-light" href="${item.tour_cmmn_hmpg_url}" target="_blank">${item.tour_cmmn_hmpg_url}</a>  
                                    </div>
                                    <h5 class="card-title mb-3"><a href="/admin/tourDetail?tour_seq=${item.tour_seq}" class="text-decoration-none link-dark">${item.tour_post_sj}</a> </h5>
                                    <p class="card-text mb-0">${item.tour_new_address}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach> --%>
					<div id="carouselExampleCaptions" class="carousel slide"
						data-bs-ride="false">
						<div class="carousel-indicators">
							<button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
							<button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
							<button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
						</div>
						<div class="carousel-inner">
								<div class="carousel-item active">
									<a href="/admin/tourDetail?tour_seq=${sb[0].tour_seq}"> <img src="../resources/img/001.jpg" class="d-block w-100" alt="...">
									</a>
									<div class="carousel-caption d-none d-md-block">
										<h5>${sb[0].tour_post_sj}</h5>
										<p>${sb[0].tour_new_address}</p>
									</div>
								</div>
								<div class="carousel-item">
									<a href="/admin/tourDetail?tour_seq=${sb[1].tour_seq}">
										<img src="https://dummyimage.com/600x350/ced4da/6c757d" class="d-block w-100" alt="...">
									</a>
									<div class="carousel-caption d-none d-md-block">
										<h5>${sb[1].tour_post_sj}</h5>
										<p>${sb[1].tour_new_address}</p>
									</div>
								</div>
								<div class="carousel-item">
									<a href="/admin/tourDetail?tour_seq=${sb[2].tour_seq}">
										<img src="https://dummyimage.com/600x350/ced4da/6c757d" class="d-block w-100" alt="...">
									</a>
									<div class="carousel-caption d-none d-md-block">
										<h5>${sb[2].tour_post_sj}</h5>
										<p>${sb[2].tour_new_address}</p>
									</div>
								</div>
						</div>
						<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Previous</span>
						</button>
						<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Next</span>
						</button>
					</div>
					<div class="py-5 bg-light">
						<div class="container px-5 my-5">
							<div class="row gx-5 justify-content-center">
								<div class="col-lg-10 col-xl-7">
									<div class="text-center">

										<div class="d-flex align-items-center justify-content-center">
											<div class="fw-bold">
												<h2>
													<strong>공지사항</strong>
												</h2>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- Call to action-->
					<aside class="bg-gradient rounded-3 p-4 p-sm-5 mt-5">
						<table class="table">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">First</th>
									<th scope="col">Last</th>
									<th scope="col">Handle</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row">1</th>
									<td>Mark</td>
									<td>Otto</td>
									<td>@mdo</td>
								</tr>
								<tr>
									<th scope="row">2</th>
									<td>Jacob</td>
									<td>Thornton</td>
									<td>@fat</td>
								</tr>
								<tr>
									<th scope="row">3</th>
									<td colspan="2">Larry the Bird</td>
									<td>@twitter</td>
								</tr>
							</tbody>
						</table>
					</aside>
				</div>
			</div>
		</section>

	</main>
	<jsp:include page="../admincommon/admin_footer.jsp" />
	<jsp:include page="../admincommon/admin_footer_common.jsp" />
</body>

</html>
