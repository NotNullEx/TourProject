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
	function revise(code) {
		var tour_ps = document.getElementById('tour_ps').value;
		var tour_address = document.getElementById('tour_address').value;
		var telnum = document.getElementById('telnum').value;
		var adress_area = $('#adress_area option:selected').val();
		var adress = document.getElementById('adress').value;
		var opentime = document.getElementById('opentime').value;
		var rest_day = document.getElementById('rest_day').value;
		var desc = document.getElementById('desc').value;
		var name = document.getElementById('name').value;
		var kind = $('input[name=kind]:checked').val();
		if (tour_address == "") {
			alert("tour_address를 입력해주세요.");
			return false;
		}
		if (telnum == "") {
			alert("telnum을 입력해주세요.");
			return false;
		}
		if (adress == "") {
			alert("adress를 입력해주세요.");
			return false;
		}
		if (opentime == "") {
			alert("opentime을 입력해주세요.");
			return false;
		}
		if (rest_day == "") {
			alert("rest_day를 입력해주세요.");
			return false;
		}
		if (desc == "") {
			alert("desc를 입력해주세요.");
			return false;
		}
		if (name == "") {
			alert("id을 입력해주세요.");
			return false;
		}
		$.ajax({
			type : "POST",
			url : "/admin/reviseAllOK",
			data : {
				"tour_address" : tour_address,
				"telnum" : telnum,
				"adress_area" : adress_area,
				"adress" : adress,
				"opentime" : opentime,
				"rest_day" : rest_day,
				"desc" : desc,
				"name" : name,
				"kind" : kind,
				"code" : code
			},
			async : false,
			success : function(data) {
				if (data.result == 1) {
					alert("수정 완료되었습니다.");
					window.location.assign("/admin/restaurant"); 
				} else {
					alert("수정이 안되었습니다.");
				}
			}
		});
	}
	function cancel(code) {
		alert("수정을 취소합니다.")
		window.location.assign("/admin/restaurantDetail?res_code="+code);
	}
</script>
<body>
	<jsp:include page="../admincommon/admin_header.jsp" />
	
	<div id="contactForm">
		<div class="form-floating mb-3">
             한식 <input name="kind" type="radio" value="0" checked="checked"> &nbsp;
             중식 <input name="kind" type="radio" value="1"> &nbsp;
             일식 <input name="kind" type="radio" value="2"> &nbsp;
             양식 <input name="kind" type="radio" value="3"> &nbsp;
        </div>
        <div class="form-floating mb-3">
		<h5>(구)</h5>
			<select id = "adress_area">
				<c:forEach var="area" items="${area}" varStatus="i">
					<c:choose>
						<c:when test="${i.index == 0 }">
							<option value = "${area.gu_name}" selected>${area.gu_name}</option>
						</c:when>
						<c:otherwise>
							<option value = "${area.gu_name}">${area.gu_name}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="tour_address" type="text" value="${data[0].tour_address}"> <label
				for="even_id">tour_address</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="telnum" type="text" value="${data[0].res_telnum}"> <label
				for="even_adress">전화번호</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="adress" type="text" value="${data[0].res_adress}"> <label
				for="even_id">전체 주소</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="opentime" type="text" value="${data[0].res_open_time}"> <label
				for="even_id">여는시간</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="rest_day" type="text" value="${data[0].res_rest_day}"> <label
				for="even_id">쉬는 날(null)</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="desc" type="text" value="${data[0].res_desc}"> <label
				for="even_id">설명</label>
		</div>
		<div class="form-floating mb-3">
			<input class="form-control" id="name" type="text" value="${data[0].res_name}"> <label
				for="even_id">이름</label>
		</div>
		
		<div class="d-grid">
			<button type="button" onclick="revise(${data[0].res_code})">음식점 데이터 수정</button>
			<button type="button" onclick="cancel(${data[0].res_code})">취소</button>
		</div>
	</div>
	
</body>
</html>