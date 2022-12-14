<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../admincommon/admin_header_common.jsp" />
<title>이벤트 데이터 수정</title>
</head>
<script type="text/javascript">
	function revise(code) {
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
		
		$.ajax({
			type : "POST",
			url : "/admin/eventReviseAllOK",
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
				"code" : code
			},
			async : false,
			success : function(data) {
				if (data.result == 1) {
					alert("수정 완료되었습니다.");
					window.location.assign("/admin/event"); 
				} else {
					alert("수정이 안되었습니다.");
				}
			}
		});
	}
	function cancel(code) {
		alert("수정을 취소합니다.")
		window.location.assign("/admin/eventDetail?even_code="+code);
	}
</script>
<body>
	<jsp:include page="../admincommon/admin_header.jsp" />
	
	<div id="contactForm">
		<div class="form-floating mb-3">
			<input class="form-control" id="even_codename" type="text" value="${data[0].even_codename}"> <label
				for="even_codename">이벤트 분류</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_guname" type="text" value="${data[0].even_guname}"> <label
				for="even_guname">이벤트 자치구</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_title" type="text" value="${data[0].even_title}"> <label
				for="even_title">이벤트 공연/행사명</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_date" type="text" value="${data[0].even_date}"> <label
				for="even_date">이벤트 날짜/시간</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_place" type="text" value="${data[0].even_place}"> <label
				for="even_place">이벤트 장소</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_org_name" type="text" value="${data[0].even_org_name}"> <label
				for="even_org_name">이벤트 기관명</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_use_trgt" type="text" value="${data[0].even_use_trgt}"> <label
				for="even_use_trgt">이벤트 이용대상</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_use_fee" type="text" value="${data[0].even_use_fee}"> <label
				for="even_use_fee">이벤트 이용요금</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_player" type="text" value="${data[0].even_player}"> <label
				for="even_player">이벤트 출연자정보</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_program" type="text" value="${data[0].even_program}"> <label
				for="even_program">이벤트 프로그램소개</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_etc_desc" type="text" value="${data[0].even_etc_desc}"> <label
				for="even_etc_desc">이벤트 기타내용</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_org_link" type="text" value="${data[0].even_org_link}"> <label
				for="even_org_link">이벤트 홈페이지 주소</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_rgst_date" type="text" value="${data[0].even_rgst_date}"> <label
				for="even_rgst_date">이벤트 신청일</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_ticket" type="text" value="${data[0].even_ticket}"> <label
				for="even_ticket">이벤트 시민/기관</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_strt_date" type="text" value="${data[0].even_strt_date}"> <label
				for="even_strt_date">이벤트 시작일</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_end_date" type="text" value="${data[0].even_end_date}"> <label
				for="even_end_date">이벤트 종료일</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="even_theme_code" type="text" value="${data[0].even_theme_code}"> <label
				for="even_theme_code">이벤트 테마분류</label>
		</div>
		<div class="d-grid">
			<button type="button" onclick="revise(${data[0].even_code})">이벤트 데이터 수정</button>
			<button type="button" onclick="cancel(${data[0].even_code})">취소</button>
		</div>
	</div>
</body>
</html>