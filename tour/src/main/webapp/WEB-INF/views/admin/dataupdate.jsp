<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../admincommon/admin_header_common.jsp" />
</head>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
function tour_update(){
	var formData = $("form[name=input]").serialize();
	$.ajax({
		type : "POST",
		url : "/admin/updateTourData",
		data : formData,
		success : function(data) {
			if (data == 1) {
				alert('정상적으로 수정 되었습니다.');
				location.href = "/admin";
			} else {
				alert('잠시후 다시 이용해주세요.');
			}
		}
	});
}

function back(){
	location.href  ="/admin";
}
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            }

            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}

</script>
<body class="d-flex flex-column">
<main class="flex-shrink-0">
<jsp:include page="../admincommon/admin_header.jsp" />
<section class="bg-light py-5">
	<div class="container px-5 my-5">
		<div class="text-center mb-5">
			<h1 class="fw-bolder">관광지 데이터 수정</h1>
		</div>
	</div>
	<div class="container px-0 py-5 text-center d-flex justify-content-center align-items-top h-100">
			<div class="bg-white col-sm-8">
				<div
					class="d-flex justify-content-center text-center shadow-lg py-5">
					<form name="input">
						<input type="hidden" id="tour_seq" name="tour_seq" value="${sb[0].tour_seq}">
						<div class="mb-3">
							<label class="float-start form-label">고유 번호</label>
							<input type="text" class="form-control" id="tour_post_sn" name="tour_post_sn" value="${sb[0].tour_post_sn}" disabled="disabled">
						</div>
						<div class="mb-3">
							<label for="id" class="form-label float-start">관광지 명</label> 
							<input type="text" class="form-control" id="tour_post_sj" name="tour_post_sj" value="${sb[0].tour_post_sj}" disabled="disabled">
						</div>
						<div class="mb-3">
							<label class="float-start form-label">fax 번호</label>
							<input type="text" class="form-control" id="tour_cmmn_fax" name="tour_cmmn_fax" value="${sb[0].tour_cmmn_fax}">
						</div>
						<div class="mb-3">
							<input type="text" id="sample4_postcode" name="sample4_postcode" class="form-control" placeholder="우편번호"> 
							<input type="button" onclick="sample4_execDaumPostcode()" class="form-control" value="우편번호 찾기"><br> 
							<input type="text" id="sample4_roadAddress" name="sample4_roadAddress" placeholder="도로명주소"> 
							<input type="text" id="sample4_jibunAddress" name="sample4_jibunAddress" placeholder="지번주소"> 
							<span  id="guide" style="color: #999; display: none"></span> 
							<input type="text" id="sample4_detailAddress" name="sample4_detailAddress" placeholder="상세주소"> 
							<input type="text" id="sample4_extraAddress" name="sample4_extraAddress" placeholder="참고항목">
						</div>
						<!-- <div class="mb-3">
							<label for="pwd" class="float-start form-label">신주소</label>
							<input type="password" class="form-control" id="password" name="password">
						</div> -->
						<div class="mb-3">
							<label class="form-label float-start">교통정보</label> 
							<input type="text" class="form-control" id="tour_subway_info" name="tour_subway_info" value="${sb[0].tour_subway_info}">
						</div>
						<div class="mb-3">
							<label class="float-start form-label">홈페이지 url</label>
							<input type="text" class="form-control" id="tour_cmmn_hmpg_url" name="tour_cmmn_hmpg_url" value="${sb[0].tour_cmmn_hmpg_url}">
						</div>
						<div class="mb-3">
							<label class="float-start form-label">기관 연락처</label>
							<input type="text" class="form-control" id="tour_cmmn_telno" name="tour_cmmn_telno" value="${sb[0].tour_cmmn_telno}">
						</div>
						<div class="mb-3">
							<label class="float-start form-label">운영요일</label>
							<input type="text" class="form-control" id="tour_cmmn_bsnde" name="tour_cmmn_bsnde" value="${sb[0].tour_cmmn_bsnde}">
						</div>
						<div class="mb-3">
							<label class="float-start form-label">장애인 편의시설</label>
							<input type="text" class="form-control" id="tour_bf_desc" name="tour_bf_desc" value="${sb[0].tour_bf_desc}">
						</div>
						<div class="mb-3">
							<label class="float-start form-label">휴무일</label>
							<input type="text" class="form-control" id="tour_cmmn_rstde" name="tour_cmmn_rstde" value="${sb[0].tour_cmmn_rstde}">
						</div>
						<div class="mb-3">
							<label class="float-start form-label">운영 시간</label>
							<input type="text" class="form-control" id="tour_cmmn_use_time" name="tour_cmmn_use_time" value="${sb[0].tour_cmmn_use_time}">
						</div>
						
						<button type="button" id="signIn" class="w-100 btn btn-primary mb-2" onclick="tour_update()">수정</button>
						<button type="button" class="w-100 btn btn-primary mb-2" onclick="history.back()">취소</button>
					</form>
				</div>
			</div>
		</div>
</section>

</main>
<jsp:include page="../admincommon/admin_footer.jsp"/>
<jsp:include page="../admincommon/admin_footer_common.jsp"/>
</body>
</html>