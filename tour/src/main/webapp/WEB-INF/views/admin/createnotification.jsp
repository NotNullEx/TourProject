<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../admincommon/admin_header_common.jsp" />
<title>게시판 등록</title>
</head>
<script type="text/javascript">
	function add() {
		var noti_title = document.getElementById('noti_title').value;
		var noti_contents = document.getElementById('noti_contents').value;
		if (noti_title == "") {
			alert("noti_title를 입력해주세요.");
			return false;
		}
		if (noti_contents == "") {
			alert("noti_contents를 입력해주세요.");
			return false;
		}
		$.ajax({
			type : "POST",
			url : "/admin/createNotificationOK",
			data : {
				"noti_title" : noti_title,
				"noti_contents" : noti_contents
			},
			async : false,
			success : function(data) {
				if (data.result == 1) {
					alert("등록 완료되었습니다.");
					window.location.assign("/admin");
				} else {
					alert("등록이 안되었습니다.");
				}
			},
			error : function(e) {
				alert("에러가 발생하였습니다.");
			}
		});
	}
	function cancel() {
		alert("등록을 취소합니다.")
		history.back(-1);
	}
</script>
<body>
	<jsp:include page="../admincommon/admin_header.jsp" />

	<form id="contactForm">
		<div class="form-floating mb-3">
			<input class="form-control" id="noti_title" type="text"> 
			<label for="noti_title">noti_title</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="noti_contents" type="text"> 
			<label for="noti_contents">noti_contents</label>
		</div>
		<div class="d-grid">
			<button type="button" onclick="add()">게시판 등록</button>
			<button type="button" onclick="cancel()">취소</button>
		</div>
	</form>
</body>
</html>