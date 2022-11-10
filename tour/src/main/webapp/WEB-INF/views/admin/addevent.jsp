<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../admincommon/admin_header_common.jsp" />
<title>이벤트 데이터 등록</title>
</head>
<script type="text/javascript">
	function add() {
		var even_codename = document.getElementById('even_codename').value;
		var even_guname = document.getElementById('even_guname').value;
		var even_title = document.getElementById('even_title').value;
		var even_date = document.getElementById('even_date').value;
		var even_place = document.getElementById('even_place').value;
		var even_org_name = document.getElementById('even_org_name').value;
		var even_use_trgt = document.getElementById('even_use_trgt').value;
		var even_use_fee = document.getElementById('even_use_fee').value;
		var even_player = document.getElementById('even_player').value;
		var even_program = document.getElementById('even_program').value;
		var even_etc_desc = document.getElementById('even_etc_desc').value;
		var even_org_link = document.getElementById('even_org_link').value;
		var even_ticket = document.getElementById('even_ticket').value;
		var even_strt_date = document.getElementById('even_strt_date').value;
		var even_end_date = document.getElementById('even_end_date').value;
		var even_theme_code = document.getElementById('even_theme_code').value;
		var even_rgst_date = document.getElementById('even_rgst_date').value;
		
		if (even_codename == "") {
			alert("even_codename를 확인해 주세요.");
			return false;
		}
		if (even_guname == "") {
			alert("even_guname를 확인해 주세요.");
			return false;
		}
		if (even_title == "") {
			alert("even_title를 확인해 주세요.");
			return false;
		}
		if (even_date == "") {
			alert("even_date를 확인해 주세요.");
			return false;
		}
		if (even_place == "") {
			alert("even_place를 확인해 주세요.");
			return false;
		}
		if (even_use_trgt == "") {
			alert("even_use_trgt를 확인해 주세요.");
			return false;
		}
		if (even_ticket == "") {
			alert("even_ticket를 확인해 주세요.");
			return false;
		}
		if (even_strt_date == "") {
			alert("even_strt_date를 확인해 주세요.");
			return false;
		}
		if (even_end_date == "") {
			alert("even_end_date를 확인해 주세요.");
			return false;
		}
		if (even_rgst_date == "") {
			alert("even_rgst_date를 확인해 주세요.");
			return false;
		}
		
		$.ajax({
			type : "POST",
			url : "/admin/addEventOK",
			data : {
				"even_codename" : even_codename,
				"even_guname" : even_guname,
				"even_title" : even_title,
				"even_date" : even_date,
				"even_place" : even_place,
				"even_org_name" : even_org_name,
				"even_use_trgt" : even_use_trgt,
				"even_use_fee" : even_use_fee,
				"even_player" : even_player,
				"even_program" : even_program,
				"even_etc_desc" : even_etc_desc,
				"even_org_link" : even_org_link,
				"even_ticket" : even_ticket,
				"even_strt_date" : even_strt_date,
				"even_end_date" : even_end_date,
				"even_theme_code" : even_theme_code,
				"even_rgst_date" : even_rgst_date,
			},
			async : false,
			success : function(data) {
				if (data.result == 1) {
					alert("등록 완료되었습니다.");
					window.location.assign("/admin/regis"); 
				} else {
					alert("등록이 안되었습니다.");
				}
			}
		});
	}
	function cancel() {
		alert("등록을 취소합니다.")
		window.location.assign("/admin/regis");
	}
</script>
<body>
	<jsp:include page="../admincommon/admin_header.jsp" />
	
	<form id="contactForm">
		<div class="form-floating mb-3">
			<input class="form-control" id="even_codename" type="text"> <label
				for="even_codename">이벤트 분류</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_guname" type="text"> <label
				for="even_guname">이벤트 자치구</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_title" type="text"> <label
				for="even_title">이벤트 공연/행사명</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_date" type="text"> <label
				for="even_date">이벤트 날짜/시간</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_place" type="text"> <label
				for="even_place">이벤트 장소</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_org_name" type="text"> <label
				for="even_org_name">이벤트 기관명</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_use_trgt" type="text"> <label
				for="even_use_trgt">이벤트 이용대상</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_use_fee" type="text"> <label
				for="even_use_fee">이벤트 이용요금</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_player" type="text"> <label
				for="even_player">이벤트 출연자정보</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_program" type="text"> <label
				for="even_program">이벤트 프로그램소개</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_etc_desc" type="text"> <label
				for="even_etc_desc">이벤트 기타내용</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_org_link" type="text"> <label
				for="even_org_link">이벤트 홈페이지 주소</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_rgst_date" type="text"> <label
				for="even_rgst_date">이벤트 신청일</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_ticket" type="text"> <label
				for="even_ticket">이벤트 시민/기관</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_strt_date" type="text"> <label
				for="even_strt_date">이벤트 시작일</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_end_date" type="text"> <label
				for="even_end_date">이벤트 종료일</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_theme_code" type="text"> <label
				for="even_theme_code">이벤트 테마분류</label>
		</div>
		<div class="d-grid">
			<button type="button" onclick="add()">이벤트 DB 등록</button>
			<button type="button" onclick="cancel()">취소</button>
		</div>
	</form>
</body>
</html>