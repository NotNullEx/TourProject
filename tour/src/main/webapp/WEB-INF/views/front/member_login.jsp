<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../frontcommon/front_header_common.jsp" />
</head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function check_account() {
		var id = document.getElementById('id').value;
		var pw = document.getElementById('password').value;

		if (id == "") {
			alert("아이디를 입력해주세요.");
			return false;
		}
		if (pw == "") {
			alert("패스워드를 입력해주세요.");
			return false;
		}
		$.ajax({
			type : "POST",
			url : "/front/loginOk",
			data : {
				"id" : id,
				"password" : pw
			},
			async : false,
			success : function(data) {
				if (data.result == 1) {
					console.log(id);
					alert("정상적으로 로그인되었습니다.");
					window.location.assign("/front/blog");
				} else {
					alert("아이디, 패스워드를 확인해주세요.");
				}
			}
		});
	}
</script>
<body class="d-flex flex-column">
	<jsp:include page="../frontcommon/front_header.jsp" />
	<div class="bg-light rounded-	 py-5 px-4 px-md-5 mb-5">
		<div class="text-center mb-5">
			<div class="feature bg-primary bg-gradient text-white rounded-3 mb-3">
				<i class="bi bi-envelope"></i>
			</div>
			<h1 class="fw-bolder">Login Page</h1>
		</div>
		<div
			class="container px-0 py-5 text-center d-flex justify-content-center align-items-top h-100">
			<div class="bg-white col-sm-8">
				<div
					class="d-flex justify-content-center text-center shadow-lg py-5">
					<form class="">
						<div class="mb-3">
							<label for="id" class="form-label float-start">ID</label> <input
								type="text" class="form-control" id="id" name="id"
								aria-describedby="emailHelp">
						</div>
						<div class="mb-5">
							<label for="pwd" class="float-start form-label">Password</label>
							<input type="password" class="form-control" id="password"
								name="password">
						</div>
						<button type="button" id="signIn"
							class="w-100 btn btn-primary mb-2" onclick="check_account()">로그인</button>
						<button type="button" id="signIn"
							class="w-100 btn btn-primary mb-2" onclick="location.href='createMember'">회원가입</button>
					</form>
				</div>
			</div>

		</div>
	</div>
	<div class="row gx-5 row-cols-2 row-cols-lg-4 py-5">
		<div class="col">
			<div class="feature bg-primary bg-gradient text-white rounded-3 mb-3">
				<i class="bi bi-chat-dots"></i>
			</div>
			<div class="h5 mb-2">Chat with us</div>
			<p class="text-muted mb-0">Chat live with one of our support
				specialists.</p>
		</div>
		<div class="col">
			<div class="feature bg-primary bg-gradient text-white rounded-3 mb-3">
				<i class="bi bi-people"></i>
			</div>
			<div class="h5">Ask the community</div>
			<p class="text-muted mb-0">Explore our community forums and
				communicate with other users.</p>
		</div>
		<div class="col">
			<div class="feature bg-primary bg-gradient text-white rounded-3 mb-3">
				<i class="bi bi-question-circle"></i>
			</div>
			<div class="h5">Support center</div>
			<p class="text-muted mb-0">Browse FAQ's and support articles to
				find solutions.</p>
		</div>
		<div class="col">
			<div class="feature bg-primary bg-gradient text-white rounded-3 mb-3">
				<i class="bi bi-telephone"></i>
			</div>
			<div class="h5">Call us</div>
			<p class="text-muted mb-0">Call us during normal business hours
				at (555) 892-9403.</p>
		</div>
	</div>

	<jsp:include page="../frontcommon/front_footer.jsp" />
	<jsp:include page="../frontcommon/front_footer_common.jsp" />
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>


</html>