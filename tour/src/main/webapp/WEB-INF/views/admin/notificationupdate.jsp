<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../admincommon/admin_header_common.jsp" />
<title>음식점 데이터 수정</title>
</head>
<script type="text/javascript">
	function revise(seq) {
		var noti_title = document.getElementById('noti_title').value;
		var noti_contents = document.getElementById('noti_contents').value;
		
		if (noti_title == "") {
			alert("noti_title을 입력해주세요.");
			return false;
		}
		if (noti_contents == "") {
			alert("noti_contents를 입력해주세요.");
			return false;
		}
		
		$.ajax({
			type : "POST",
			url : "/admin/notificationUpdateOK",
			data : {
				"noti_title" : noti_title,
				"noti_contents" : noti_contents,
				"noti_seq" : seq
			},
			async : false,
			success : function(data) {
				if (data.result == 1) {
					alert("수정 완료되었습니다.");
					window.location.assign("/admin"); 
				} else {
					alert("수정이 안되었습니다.");
				}
			},
			error : function() {
				alert("에러가 발생하였습니다.");
			}
		});
	}
	function cancel(seq) {
		alert("수정을 취소합니다.")
		window.location.assign("/admin/notificationDetail?noti_seq="+seq);
	}
</script>
<body>
	<jsp:include page="../admincommon/admin_header.jsp" />
	
	<div id="contactForm">
		<div class="form-floating mb-3">
			<input class="form-control" id="noti_seq" type="text" value="${data.noti_seq}" disabled="disabled"> <label
				for="noti_seq">noti_seq</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="noti_title" type="text" value="${data.noti_title}"> <label
				for="noti_title">제목</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="noti_contents" type="text" value="${data.noti_contents}"> <label
				for="noti_contents">내용</label>
		</div>
		<div class="d-grid">
			<button type="button" onclick="revise(${data.noti_seq})">게시판 수정</button>
			<button type="button" onclick="cancel(${data.noti_seq})">취소</button>
		</div>
	</div>
	
</body>
</html>