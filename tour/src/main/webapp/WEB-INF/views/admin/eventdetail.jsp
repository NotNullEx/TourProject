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
<link href="/resources/css/event_style.css" rel="stylesheet" type="text/css">
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

<body>
<jsp:include page="../admincommon/admin_header.jsp"/>
<div class="even">
	<h1 class="even_title"><c:out value="${data[0].even_title}"/></h1>
	<p class="regdater">${data[0].even_rgst_date}</p>
	
	<div class="input_wrap_num">
		<label>이벤트 번호</label>
		<li><input name="evne_code" readonly="readonly" value='<c:out value="${data[0].even_code}"/>' ></li>
	</div>
	
	<div class="input_wrap">
		<img class="bimg" name="even_main_img" alt="..." src="${data[0].even_main_img}">
	</div>
	
	
	<div class="wrap_contView" id="detailinfoview">			
		<div class="area_txtView bottom" style="padding-bottom: 0px;">
			<div class="inr_wrap" style="overflow: visible; height: auto;">
				<div class="inr">
					<ul>
						<c:choose>
							<c:when test="${data[0].even_org_name != null and data[0].even_org_name != ''}">
								<li><strong>기관명</strong><span>&emsp;<c:out value="${data[0].even_org_name}"/></span></li>
							</c:when>
							<c:otherwise>
								<li><strong>기관명</strong><span>&emsp;<c:out value="-"/></span></li>
							</c:otherwise>
						</c:choose>	
						<c:choose>
							<c:when test="${data[0].even_player != null and data[0].even_player != ''}">
								<li><strong>출연자</strong><span>&emsp;<c:out value="${data[0].even_player}"/></span></li>
							</c:when>
							<c:otherwise>
								<li><strong>출연자</strong><span>&emsp;<c:out value="-"/></span></li>
							</c:otherwise>	
						</c:choose>
						<c:choose>
							<c:when test="${data[0].even_program != null and data[0].even_program != ''}">
								<li><strong>프로그램</strong><span>&emsp;<c:out value="${data[0].even_program}"/></span></li>
							</c:when>
							<c:otherwise>
								<li><strong>프로그램</strong><span>&emsp;<c:out value="-"/></span></li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${data[0].even_etc_desc != null and data[0].even_etc_desc != ''}">		
								<li><strong>기타</strong><span>&emsp;<c:out value="${data[0].even_etc_desc}"/></span></li>
							</c:when>
							<c:otherwise>
								<li><strong>기타</strong><span>&emsp;<c:out value="-"/></span></li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${data[0].even_use_trgt != null and data[0].even_use_trgt != ''}">		
								<li><strong>이용대상</strong><span>&emsp;<c:out value="${data[0].even_use_trgt}"/></span></li>
							</c:when>
							<c:otherwise>
								<li><strong>이용대상</strong><span>&emsp;<c:out value="-"/></span></li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${data[0].even_use_fee != null and data[0].even_use_fee != ''}">		
								<li><strong>이용료</strong><span>&emsp;<c:out value="${data[0].even_use_fee}"/></span></li>
							</c:when>
							<c:otherwise>
								<li><strong>이용료</strong><span>&emsp;<c:out value="-"/></span></li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${data[0].even_place != null and data[0].even_place != ''}">		
								<li><strong>장소</strong><span>&emsp;<c:out value="${data[0].even_place}"/></span></li>
							</c:when>
							<c:otherwise>
								<li><strong>장소</strong><span>&emsp;<c:out value="-"/></span></li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${data[0].even_date != null and data[0].even_date != ''}">		
								<li><strong>날짜</strong><span>&emsp;<c:out value="${data[0].even_date}"/></span></li>
							</c:when>
							<c:otherwise>
								<li><strong>날짜</strong><span>&emsp;<c:out value="-"/></span></li>
							</c:otherwise>
						</c:choose>
								
							
					</ul>
				</div>
			</div>	
		</div>
	</div>
	
	<div class="btn_wrap">
		<button type="button" class="even_btn" onclick="location.href='/admin/event'">목록</button>
		<button type="button" class="even_btn" onclick="location.href='/admin/eventReviseAll?even_code=${data[0].even_code}'">수정</button>
		<button type="button" class="even_btn" onclick="deleteOne(${data[0].even_code})">삭제</button>
	</div>
	<form id="infoForm" action="/board/modify" method="get">
		<input type="hidden" id="even_code" name="even_code" value='<c:out value="${data[0].even_code}"/>'>
	</form>
</div>
<jsp:include page="../admincommon/admin_footer.jsp"/>
<jsp:include page="../admincommon/admin_footer_common.jsp"/>
</body>
</html>