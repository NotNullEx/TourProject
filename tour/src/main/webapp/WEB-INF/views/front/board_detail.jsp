<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../frontcommon/front_header_common.jsp" />
</head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function deleteOne(seq) {
		if (confirm("정말 삭제하시겠습니까??")) {
			$.ajax({
				type : "POST",
				data : {
					"seq" : seq
				},
				url : "/front/deleteOneBoard",
				async : false,
				success : function(list) {
					console.log(list);
					if (list > 0) {
						alert("삭제가 완료되었습니다.");
						window.location.assign("/front/board");
					} else {
						alert("삭제에 실패했습니다.");
					}
				}
			});
		}
	}
	
	function createComents(board_seq) {
			var userCheck = document.getElementById('mem_seq').value;
			var coment_contents = document.getElementById('coments').value;
			
			if(coment_contents == ""){
				alert("댓글을 확인해주세요");
				return false;
			}
			
			if(userCheck == "" || userCheck == null) {
				alert("댓글 등록은 로그인을 해야 가능합니다.");
				return false;
			}else {
			$.ajax({
				type : "POST",
				data : {
					"board_seq" : board_seq,
					"coment_contents" : coment_contents
				},
				url : "/front/createComents",
				async : false,
				success : function(data){
					if(data != null){
						alert("등록 완료");
						location.reload();
					}else{
						alert("등록 실패");
					}
				}
			});
		}
	}
	
	function deleteComents(seq) {
		if (confirm("정말 삭제하시겠습니까??")) {
			$.ajax({
				type : "POST",
				data : {
					"seq" : seq
				},
				url : "/front/deleteComents",
				async : false,
				success : function(list) {
					console.log(list);
					if (list != null) {
						alert("삭제가 완료되었습니다.");
						location.reload();
					} else {
						alert("삭제에 실패했습니다.");
					}
				}
			});
		}
	}
	
	function editComents(seq) {
		
	}
</script>
<body class="d-flex flex-column">
	<main class="flex-shrink-0">
		<jsp:include page="../frontcommon/front_header.jsp" />
		<!-- Page Content-->
		<input type="hidden" id="mem_seq" value="${mem_seq}">
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



						<%-- <c:forEach var="list" items="${list}" begin="0"> --%>
						<article>

							<!-- Post header-->
							<header class="mb-4">
								<!-- Post title-->
								<h1 class="fw-bolder mb-1">${list[0].board_title}</h1>
								<!-- Post meta content-->
								<div class="text-muted fst-italic mb-2">${list[0].board_reg_date}</div>
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
								<p class="fs-5 mb-4">${list[0].board_contents}</p>
								<!-- <p class="fs-5 mb-4">The universe is large and old, and the ingredients for life as we know it are everywhere, so there's no reason to think that Earth would be unique in that regard. Whether of not the life became intelligent is a different question, and we'll see if we find that.</p>
                                    <p class="fs-5 mb-4">If you get asteroids about a kilometer in size, those are large enough and carry enough energy into our system to disrupt transportation, communication, the food chains, and that can be a really bad day on Earth.</p>
                                    <h2 class="fw-bolder mb-4 mt-5">I have odd cosmic thoughts every day</h2>
                                    <p class="fs-5 mb-4">For me, the most fascinating interface is Twitter. I have odd cosmic thoughts every day and I realized I could hold them to myself or share them with people who might be interested.</p>
                                    <p class="fs-5 mb-4">Venus has a runaway greenhouse effect. I kind of want to know what happened there because we're twirling knobs here on Earth without knowing the consequences of it. Mars once had running water. It's bone dry today. Something bad happened there as well.</p> -->
							</section>
						</article>
						<div class="col-lg-4 mb-5">
							<div class="card h-100 shadow border-0">
								<button type="button" class="w-100 btn btn-primary mb-2"
									onclick="location.href='/front/editBoard?board_seq=${list[0].board_seq}'">수정하기</button>


								<button type="button" id="addRes"
									class="w-100 btn btn-primary mb-2"
									onclick="deleteOne(${list[0].board_seq})">삭제하기</button>
								<button type="button" class="w-100 btn btn-primary mb-2"
									onclick="location.href='/front/board'">목록</button>
							</div>
						</div>
						<%-- </c:forEach> --%>

						<!-- Comments section-->
						<section>
							<div class="card bg-light">
								<div class="card-body">
									<!-- Comment form-->
									<div class="mb-4">
										<textarea class="form-control" rows="3"
											placeholder="Join the discussion and leave a comment!"
											id="coments"></textarea>
										<button type="button" class="w-100 btn btn-primary mb-2"
											onclick="createComents(${list[0].board_seq})">등록</button>

									</div>
									<!-- Comment with nested comments-->
									<c:forEach begin="0" var="data" items="${data}">
										<div class="d-flex mb-4">
											<!-- Parent comment-->
												<div class="ms-3">
													<div class="fw-bold">${data.mem_name} 
														<h6><c:out value="${data.coment_upd_date}"/></h6>
													</div>
													<c:out value="${data.coment_contents}"></c:out>
												</div>
										</div>
										<c:choose>
											<c:when test="${mem_seq == data.mem_seq}">
												<button type="button" class="btn-primary"
													onclick="editComents(${data.coment_seq})">수정</button>
												<button type="button" class="btn-success"
													onclick="deleteComents(${data.coment_seq})">삭제</button>
											</c:when>
										</c:choose>
									</c:forEach>
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