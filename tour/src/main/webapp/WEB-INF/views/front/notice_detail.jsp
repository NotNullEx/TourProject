<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../frontcommon/front_header_common.jsp" />
<title>게시판 수정</title>
</head>
<script type="text/javascript">
	
</script>
<body>
	<jsp:include page="../frontcommon/front_header.jsp" />
	
	<div id="contactForm">
		<div class="form-floating mb-3">
			<input class="form-control" id="board_title" type="text" value = "${noti.noti_title }" readonly="readonly"> <label
				for="even_codename">공지사항 제목</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="board_title" type="text" value = "${noti.noti_contents }" readonly="readonly"> <label
				for="even_guname">공지사항 내용</label>
		</div>
		
				<div class="form-floating mb-3">
			<input class="form-control" id="board_title" type="text" value = "${noti.noti_reg_date }" readonly="readonly"> <label
				for="even_guname">작성일자</label>
		</div>

		<div class="d-grid">
			<button type="button" onclick="javascript:history.back(-1);">돌아가기</button>
		</div>
	</div>
</body>
</html>