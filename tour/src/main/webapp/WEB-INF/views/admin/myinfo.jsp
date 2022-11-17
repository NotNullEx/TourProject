<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="../admincommon/admin_header_common.jsp" />

<!-- <link href="../resources/css/footer.css" rel="stylesheet"/>  -->
<script type="text/javascript">
	function tour_update(){
		
		var name = $("#names").val();
		var phone = $("#phones").val();
		var seq = $("#seq").val();
		var pass = $("#pass").val();
		var repass = $("#repass").val();
		
		if(confirm("정말 수정하시겠습니까?")){
			if(pass != repass){
				alert("비밀번호가 맞지않습니다.");
				return false;
			}
			$.ajax({
				type: "POST",
				url: "/admin/myInfoUpdate",
				data:{
					"name" : name,
					"phone" : phone,
					"seq" : seq,
					"pass" : pass
				},
				success : function(data){
					if(data == 1){
						alert("회원 정보가 정상적으로 수정되었습니다.");
						window.location.assign("/admin/myInfo?="+email); 
					}
				}, error : function(data){
					alert("데이터 수정을 할 수 없습니다. 정보를 다시 입력해주세요.");
				}
			});
		}
	}
</script>
<body>
	<main>
		<jsp:include page="../admincommon/admin_header.jsp" />
		<div id="page-wrapper">
			<jsp:include page="../admincommon/admin_sidebar.jsp" />
			
			<div class="container px-0 py-5 text-center d-flex justify-content-center align-items-top h-100" id="body-content">
				<h2>내 정보</h2>
			</div>
			
			<div class="container px-0 py-5 text-center d-flex justify-content-center align-items-top h-100" id="body-content">
				<div class="bg-white col-sm-8">
					<div class="d-flex justify-content-center text-center shadow-lg py-5">
						
						<form name="input">
							<div class="mb-3">
								<label class="float-start form-label">이름</label> <input
									type="text" class="form-control" id="names" name="name"
									value="${name}" >
							</div>
							<div class="mb-3">
								<label for="id" class="form-label float-start">전화번호</label> <input
									type="text" class="form-control" id="phones" name="phon"
									value="${phon}" >
							</div>
							<div class="mb-3">
								<label class="float-start form-label">이메일</label> <input
									type="text" class="form-control" id="emails" name="email"
									value="${email}" >
							</div>
							<div class="mb-3">
								<label class="form-label float-start">가입날짜</label> <input
									type="text" class="form-control" id="regist_day"
									name="regist_day" value="${regist_day}" disabled="disabled">
							</div>
							<div class="mb-3">
								<label class="float-start form-label">고유번호</label> <input
									type="text" class="form-control" id="seq" name="seq"
									value="${seq}" disabled="disabled">
							</div>
							<div class="mb-3">
								<label class="float-start form-label">비밀번호</label> <input
									type="text" class="form-control" id="pass" name="pass">
							</div>
							<div class="mb-3">
								<label class="float-start form-label">비밀번호 확인</label> <input
									type="text" class="form-control" id="repass" name="repass">
							</div>
							<button type="button" id="signIn"
								class="w-100 btn btn-primary mb-2" onclick="tour_update()">수정</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		
	</main>
</body>
</html>