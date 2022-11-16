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
<body>
	<main>
		<jsp:include page="../admincommon/admin_header.jsp" />
		<div id="page-wrapper">
			<jsp:include page="../admincommon/admin_sidebar.jsp" />
			<div class="container px-0 py-5 text-center d-flex justify-content-center align-items-top h-100" id="body-content">
				<div class="bg-white col-sm-8">
					<div class="d-flex justify-content-center text-center shadow-lg py-5">
						<form name="input">
							<div class="mb-3">
								<label class="float-start form-label">이름</label> <input
									type="text" class="form-control" id="name" name="name"
									value="${name}" disabled="disabled">
							</div>
							<div class="mb-3">
								<label for="id" class="form-label float-start">전화번호</label> <input
									type="text" class="form-control" id="phon" name="phon"
									value="${phon}" disabled="disabled">
							</div>
							<div class="mb-3">
								<label class="float-start form-label">이메일</label> <input
									type="text" class="form-control" id="email" name="email"
									value="${email}" disabled="disabled">
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
							<button type="button" id="signIn"
								class="w-100 btn btn-primary mb-2" onclick="tour_update()">수정</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		
	</main>
	<footer>
		<%@ include file="../admincommon/admin_footer.jsp" %>
		<%@ include file="../admincommon/admin_footer_common.jsp" %>
		<%-- <jsp:include page="../admincommon/admin_footer.jsp" />
		<jsp:include page="../admincommon/admin_footer_common.jsp" /> --%>
	</footer>
</body>
</html>