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
<link href="${path}/resources/css/event_style.css" rel="stylesheet" type="text/css">
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
	
	
	document.cookie = "crossCookie=bar; SameSite=None; Secure"
</script>

<body>
<jsp:include page="../admincommon/admin_header.jsp"/>
<div class="even">
	<h1 class="even_title"><c:out value="${data[0].even_title}"/></h1>
	<input class="regdater" readonly="readonly" value="${data[0].even_rgst_date}">
	
	<div class="input_wrap_num">
		<label>이벤트 번호</label>
		<li><input name="evne_code" readonly="readonly" value='<c:out value="${data[0].even_code}"/>' ></li>
	</div>
	
	<div class="input_wrap">
		<img class="image bimg" name="even_main_img" alt="..." src="${data[0].even_main_img}">
	</div>
	
	<div class="even_info">
		<ul>
			<li><strong>이용대상</strong><span &nbsp;><c:out value="${data[0].even_use_trgt}"/></span></li>
			<li><strong>기관명</strong><span><c:out value="${data[0].even_org_name}"/></span></li>
			<li><strong>출연자</strong><span><c:out value="${data[0].even_player}"/></span></li>
			<li><strong>프로그램</strong><span><c:out value="${data[0].even_program}"/></span></li>
			<li><strong>기타</strong><span><c:out value="${data[0].even_etc_desc}"/></span></li>
			<li><strong>이용료</strong><span><c:out value="${data[0].even_use_fee}"/></span></li>
			<li><strong>장소</strong><span><c:out value="${data[0].even_place}"/></span></li>
			<li><strong>날짜</strong><span><c:out value="${data[0].even_date}"/></span></li>
		</ul>
	</div>
	
	<div class="btn_wrap">
		<button type="button" class="btn" onclick="location.href='/admin/event'">목록</button>
		<button type="button" class="btn" onclick="location.href='/admin/eventReviseAll?even_code=${data[0].even_code}'">수정</button>
		<button type="button" class="btn" onclick="deleteOne(${data[0].even_code})">삭제</button>
	</div>
	<form id="infoForm" action="/board/modify" method="get">
		<input type="hidden" id="even_code" name="even_code" value='<c:out value="${data[0].even_code}"/>'>
	</form>
</div>
<jsp:include page="../admincommon/admin_footer.jsp"/>
<jsp:include page="../admincommon/admin_footer_common.jsp"/>
</body>
</html>