<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../frontcommon/front_header_common.jsp" />
</head>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
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
					if (list > 0) {
						alert("삭제가 완료되었습니다.");
						window.location.assign("/front/board");
					} else {
						alert("삭제에 실패했습니다.");
					}
				}, error : function (e) {
					alert("오류가 발생하였습니다.");
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
	
	function reviseComents(seq) {
		var content = document.getElementById('coment_contents').value;
		if (confirm("수정하시겠습니까?")) {
			var contents = prompt('수정할 내용',content);
			if(contents == "" || contents == null) {
				alert("내용을 입력해 주세요.");
				return false;
			}
			$.ajax({
				type : "POST",
				data : {
					"seq" : seq,
					"contents" : contents
				},
				url : "/front/reviseComents",
				async : false,
				success : function(data) {
					if(data.result != 0) {
						alert("수정이 완료되었습니다.");
						location.reload();
					}else {
						alert("수정에 실패했습니다.")
					}
				}
			});
		}
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

						<article>

							<!-- Post header-->
							<header class="mb-4">
								<!-- Post title-->
								<h1 class="fw-bolder mb-1">${list[0].board_title}</h1>
								<!-- Post meta content-->
								<div class="text-muted fst-italic mb-2">${list[0].board_reg_date}</div>
								<!-- Post categories-->
								<c:choose>
									<c:when test="${list[0].board_status == 0}">
										<div class="badge bg-secondary text-decoration-none link-light">자유</div>
									</c:when>
									<c:when test="${list[0].board_status == 1}">
										<div class="badge bg-secondary text-decoration-none link-light">질문</div>
									</c:when>
									<c:when test="${list[0].board_status == 2}">
										<div class="badge bg-secondary text-decoration-none link-light">답변</div>
									</c:when>
									<c:when test="${list[0].board_status == 3}">
										<div class="badge bg-secondary text-decoration-none link-light">숙박후기</div>
									</c:when>
									<c:when test="${list[0].board_status == 4}">
										<div class="badge bg-secondary text-decoration-none link-light">음식점후기</div>
									</c:when>
									<c:otherwise>
										<div class="badge bg-secondary text-decoration-none link-light">축제후기</div>
									</c:otherwise>
								</c:choose>
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
							</section>
						</article>
						<%-- </c:forEach> --%>

						<!-- Comments section-->
						<section>
							<div class="card bg-light">
								<div class="card-body">
									<!-- Comment form-->
									<div class="mb-4">
										<textarea class="form-control" rows="3"
											placeholder="댓글기능은 로그인 후 이용 가능합니다!"
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
													<input type="hidden" id="coment_contents" value="${data.coment_contents}">
													<c:out value="${data.coment_contents}"></c:out>
												</div>
										</div>
										<c:choose>
											<c:when test="${mem_seq == data.mem_seq}">
												<button type="button" class="btn-primary"
													onclick="javascript: reviseComents(${data.coment_seq})">수정</button>
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
				<c:choose>
					<c:when test="${mem_seq == list[0].mem_seq}">
						<button type="button" class="btn btn-primary mb-2"
							onclick="location.href='/front/editBoard?board_seq=${list[0].board_seq}'">수정하기</button>
						<button type="button" id="addRes"
							class="btn btn-primary mb-2"
							onclick="deleteOne(${list[0].board_seq})">삭제하기</button>
					</c:when>
				</c:choose>
				<button type="button" class="btn btn-primary mb-2"
									onclick="location.href='/front/board'">목록</button>
			</div>
		</section>
	</main>
	<jsp:include page="../frontcommon/front_footer.jsp" />
	<jsp:include page="../frontcommon/front_footer_common.jsp" />
</body>
</html>