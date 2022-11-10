<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../admincommon/admin_header_common.jsp"/>
<meta charset="UTF-8">
<title>이벤트 상세정보</title>
</head>
<script type="text/javascript">
	function deleteOne(code) {
		if(confirm("정말 삭제하시겠습니까??")) {
			$.ajax({
				type : "POST",
				data : {
					"code" : code
				},
				url : "/admin/deleteOneEvent",
				async : false,
				success : function(data) {
					console.log(data);
					if(data > 0) {
						alert("삭제가 완료되었습니다.");
						window.location.assign("/admin/event"); 
					}else {
						alert("삭제에 실패했습니다.");
					}
				}
			});
		}
	}
</script>
<style>
<style type="text/css">
.input_wrap{
	padding: 5px 20px;
}
label{
    display: block;
    margin: 10px 0;
    font-size: 20px;	
}
input{
	padding: 5px;
    font-size: 17px;
}
textarea{
	width: 800px;
    height: 200px;
    font-size: 15px;
    padding: 10px;
}
.btn{
  	display: inline-block;
    font-size: 22px;
    padding: 6px 12px;
    background-color: #fff;
    border: 1px solid #ddd;
    font-weight: 600;
    width: 140px;
    height: 41px;
    line-height: 39px;
    text-align : center;
    margin-left : 30px;
    cursor : pointer;
}
.btn_wrap{
	padding-left : 80px;
	margin-top : 50px;
}
.bimg {
	max-height: 400px;
	max-width: 500px;
	background-repeat: no-repeat;
	background-position: center;
	object-fit: cover;
}
</style>
<body>
<jsp:include page="../admincommon/admin_header.jsp"/>

<h1>조회 페이지</h1>
	<div class="input_wrap">
		<label>이벤트 번호</label>
		<li><input name="evne_code" readonly="readonly" value='<c:out value="${data[0].even_code}"/>' ></li>
	</div>
	<div class="input_wrap">
		<label>이벤트 이미지</label>
		<img class="image bimg" name="even_main_img" alt="..." src="${data[0].even_main_img}">
	</div>
	<div class="input_wrap">
		<label>이벤트 제목</label>
		<li><input name="even_title" readonly="readonly" value='<c:out value="${data[0].even_title}"/>' ></li>
	</div>
	<div class="input_wrap">
		<label>이벤트 정보</label>
			<li><input name="even_use_trgt" readonly="readonly" value='이용대상 : <c:out value="${data[0].even_use_trgt}"/>' ></li>
			<li><input name="even_org_name" readonly="readonly" value='기관명 : <c:out value="${data[0].even_org_name}"/>' ></li>
			<li><input name="even_player" readonly="readonly" value='출연자 : <c:out value="${data[0].even_player}"/>' ></li>
			<li><input name="even_program" readonly="readonly" value='프로그램 : <c:out value="${data[0].even_program}"/>' ></li>
			<li><input name="even_etc_desc" readonly="readonly" value='기타 : <c:out value="${data[0].even_etc_desc}"/>' ></li>
	</div>
	<div class="input_wrap">
		<label>이용료</label>
		<li><input name="writer" readonly="readonly" value='<c:out value="${data[0].even_use_fee}"/>' ></li>
	</div>
	<div class="input_wrap">
		<label>이벤트 장소 및 날짜</label>
		<li><input name="even_place" readonly="readonly" value="${data[0].even_place}"></li>
		<li><input name="even_date" readonly="readonly" value="${data[0].even_date}"></li>
	</div>	
	<div class="input_wrap">
		<label>게시판 등록일</label>
		<li><input name="regdater" readonly="readonly" value="${data[0].even_rgst_date}"></li>
	</div>
	<div class="btn_wrap">
		<button type="button" class="btn" onclick="location.href='/admin/event'">목록</button>
		<button type="button" class="btn" onclick="location.href='/admin/eventReviseAll?even_code=${data[0].even_code}'">수정하기</button>
		<button type="button" class="btn" onclick="deleteOne(${data[0].even_code})">삭제하기</button>
	</div>
	<form id="infoForm" action="/board/modify" method="get">
		<input type="hidden" id="even_code" name="even_code" value='<c:out value="${data[0].even_code}"/>'>
	</form>
	
<jsp:include page="../admincommon/admin_footer.jsp"/>
<jsp:include page="../admincommon/admin_footer_common.jsp"/>
</body>
</html>