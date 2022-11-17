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
	function revise(seq) {
		var board_title = document.getElementById('board_title').value;
		var board_contents = document.getElementById('board_contents').value;

		
		$.ajax({
			type : "POST",
			url : "/front/editBoardOK",
			data : {
				"board_title" : board_title,
				"board_contents" : board_contents,
				"seq" :seq

			},
			async : false,
			success : function(data) {
				if (data.result == 1) {
					alert("수정 완료되었습니다.");
					window.location.assign("/front/blogPost"); 
				} else {
					alert("수정이 안되었습니다.");
				}
			}
		});
	}
	function cancel(seq) {
		alert("수정을 취소합니다.")
		window.location.assign("/front/blogPost_detail?board_seq="+seq);
	}
</script>
<body>
	<jsp:include page="../frontcommon/front_header.jsp" />
	
	<div id="contactForm">
		<div class="form-floating mb-3">
			<input class="form-control" id="board_title" type="text" value="${list[0].board_title}"> <label
				for="even_codename">이벤트 분류</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="board_contents" type="text" value="${list[0].board_contents}"> <label
				for="even_guname">이벤트 자치구</label>
		</div>

		<div class="d-grid">
			<button type="button" onclick="revise(${list[0].board_seq})">게시판 수정</button>
			<button type="button" onclick="cancel(${list[0].board_seq})">취소</button>
		</div>
	</div>
</body>
</html>