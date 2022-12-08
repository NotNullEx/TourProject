<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function add() {

		var board_title = document.getElementById('board_title').value;
		var board_contents = document.getElementById('board_contents').value;
		var kind = document.getElementById('kind').value;
		$.ajax({
			type : "POST",
			url : "/front/createBoardOK",
			data : {
				"board_title" : board_title,
				"board_contents" : board_contents,
				"kind" : kind
			},
			async : false,
			success : function(data) {
				if (data.result == 1) {
					alert("게시판이 등록 되었습니다.");
					window.location.assign("/front/board");
				} else {
					alert("등록 실패");
				}
			}

		});
	}
</script>
<jsp:include page="../frontcommon/front_header_common.jsp" />
<body class="d-flex flex-column">
	<main class="flex-shrink-0">
		<jsp:include page="../frontcommon/front_header.jsp" />
		<!-- Page content-->
		<section class="py-5">
			<div class="container px-5">
				<div class="text-center mb-5">
						<h1 class="fw-bolder">글쓰기</h1>
					</div>
				<div class="bg-light rounded-3 py-5 px-4 px-md-5 mb-5">
					
					<div class="row gx-5 justify-content-center">
						<div class="col-lg-8 col-xl-6">
							<form id="title" method="post">
								<!-- Name input-->
								<div class="form-floating mb-3">
									<div>
										<select id="kind" class="form-select">
											<option value="0">자유</option>
											<option value="1">질문 </option>
											<option value="2">답변 </option>
											<option value="3">숙박후기 </option>
											<option value="4">음식점후기 </option>
											<option value="5">축제후기 </option>
										</select>
									</div>
									
								</div>
								
								<div class="form-floating mb-3">
									<input class="form-control" name="title" id="board_title"
										type="text"> <label for="board_title">제목</label>
								</div>
								<div class="form-floating mb-3">
									<textarea class="form-control" style="height: 20rem" name="title" id="board_contents"></textarea><label for="board_title">내용</label>
								</div> 								
							</form>
							<div class="d-grid">
								<button type="button" id="reg"
									class="form-control btn btn-primary mb-2" onclick="add()">등록</button>
									<button type="button" id="reg"
									class="form-control btn btn-primary mb-2" onclick="history.back()">취소</button>
							</div>
						</div>
					</div>
				</div>
			</div>
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