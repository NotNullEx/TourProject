<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../frontcommon/front_header_common.jsp" />
</head>
<button type="button" id="addNoti" class="btn btn-primary" onclick="location.href='/front/createBoard'">게시판 등록</button>
<body class="d-flex flex-column">
	<main class="flex-shrink-0">
		<jsp:include page="../frontcommon/front_header.jsp" />
		<!-- Page Content-->
		<section class="py-5">
			<div class="container px-5 my-5">
				<div class="row gx-5">
					<div class="col-lg-3">
						<div class="d-flex align-items-center mt-lg-5 mb-4">
							<img class="img-fluid rounded-circle"
								src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." />
							<div class="ms-3">
								<div class="fw-bold">Valerie Luna</div>
								<div class="text-muted">News, Business</div>
							</div>
						</div>
					</div>
					<div class="col-lg-9">
				
						<input type="hidden" value="${seq}">
 						<c:forEach begin="0" var="list" items="${list}">
							<li class="page-item"><a class="page-link"
								href="/front/blogPost_detail?board_seq=${list.board_seq}">${list.board_title}</a></li>
						</c:forEach> 
						<%-- 				<c:forEach var="list" items="${list}" begin="0">
						<article>
						
							<!-- Post header-->
							<header class="mb-4">
								<!-- Post title-->
								<h1 class="fw-bolder mb-1">${list.board_title}</h1>
								<!-- Post meta content-->
								<div class="text-muted fst-italic mb-2">${list.board_reg_date}</div>
								<!-- Post categories-->
								<a class="badge bg-secondary text-decoration-none link-light"
									href="#!">공지사항</a> <a
									class="badge bg-secondary text-decoration-none link-light"
									href="#!">이벤트</a>
							</header>
							<!-- Preview image figure-->
							<figure class="mb-4">
								<img class="img-fluid rounded"
									src="https://dummyimage.com/900x400/ced4da/6c757d.jpg"
									alt="..." />
							</figure>
							<!-- Post content-->
							<section class="mb-5">
								<p class="fs-5 mb-4">${list.board_contents}</p>
								<!-- <p class="fs-5 mb-4">The universe is large and old, and the ingredients for life as we know it are everywhere, so there's no reason to think that Earth would be unique in that regard. Whether of not the life became intelligent is a different question, and we'll see if we find that.</p>
                                    <p class="fs-5 mb-4">If you get asteroids about a kilometer in size, those are large enough and carry enough energy into our system to disrupt transportation, communication, the food chains, and that can be a really bad day on Earth.</p>
                                    <h2 class="fw-bolder mb-4 mt-5">I have odd cosmic thoughts every day</h2>
                                    <p class="fs-5 mb-4">For me, the most fascinating interface is Twitter. I have odd cosmic thoughts every day and I realized I could hold them to myself or share them with people who might be interested.</p>
                                    <p class="fs-5 mb-4">Venus has a runaway greenhouse effect. I kind of want to know what happened there because we're twirling knobs here on Earth without knowing the consequences of it. Mars once had running water. It's bone dry today. Something bad happened there as well.</p> -->
							</section>
						</article>
						</c:forEach> --%>
						<!-- Comments section-->
						<section>
							<div class="card bg-light">
								<div class="card-body">
									<!-- Comment form-->
									<form class="mb-4">
										<textarea class="form-control" rows="3"
											placeholder="Join the discussion and leave a comment!"></textarea>
									</form>
									<!-- Comment with nested comments-->
									<div class="d-flex mb-4">
										<!-- Parent comment-->
										<div class="flex-shrink-0">
											<img class="rounded-circle"
												src="https://dummyimage.com/50x50/ced4da/6c757d.jpg"
												alt="..." />
										</div>
										<div class="ms-3">
											<div class="fw-bold">Commenter Name</div>
											If you're going to lead a space frontier, it has to be
											government; it'll never be private enterprise. Because the
											space frontier is dangerous, and it's expensive, and it has
											unquantified risks.
											<!-- Child comment 1-->
											<div class="d-flex mt-4">
												<div class="flex-shrink-0">
													<img class="rounded-circle"
														src="https://dummyimage.com/50x50/ced4da/6c757d.jpg"
														alt="..." />
												</div>
												<div class="ms-3">
													<div class="fw-bold">Commenter Name</div>
													And under those conditions, you cannot establish a
													capital-market evaluation of that enterprise. You can't get
													investors.
												</div>
											</div>
											<!-- Child comment 2-->
											<div class="d-flex mt-4">
												<div class="flex-shrink-0">
													<img class="rounded-circle"
														src="https://dummyimage.com/50x50/ced4da/6c757d.jpg"
														alt="..." />
												</div>
												<div class="ms-3">
													<div class="fw-bold">Commenter Name</div>
													When you put money directly to a problem, it makes a good
													headline.
												</div>
											</div>
										</div>
									</div>
									<!-- Single comment-->
									<div class="d-flex">
										<div class="flex-shrink-0">
											<img class="rounded-circle"
												src="https://dummyimage.com/50x50/ced4da/6c757d.jpg"
												alt="..." />
										</div>
										<div class="ms-3">
											<div class="fw-bold">Commenter Name</div>
											When I look at the universe and all the ways the universe
											wants to kill us, I find it hard to reconcile that with
											statements of beneficence.
										</div>
									</div>
								</div>
							</div>
						</section>
					</div>
				</div>
			</div>
		</section>
	</main>
	<jsp:include page="../frontcommon/front_footer.jsp" />
	<jsp:include page="../frontcommon/front_footer_common.jsp" />
</body>
</html>