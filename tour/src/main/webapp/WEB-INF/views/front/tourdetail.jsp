<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../frontcommon/front_header_common.jsp" />
<link href="../resources/css/front_tourdetail.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function setFavoContentDetail() {
		var tour_seq = document.getElementById('tour_seq').value;
		var mem_seq = document.getElementById('mem_seq').value;
		if(confirm("즐겨찾기에 추가하시겠습니까?")) {
			$.ajax({
				type : "POST",
				url : "/front/tourFavorites",
				data : {
					"tour_seq" : tour_seq,
					"mem_seq" : mem_seq
				},
				async : false,
				success : function(data) {
						if(data != null){
							alert(tour_name + "이(가) 즐겨찾기 되었습니다.");
						}else{
							alert("즐겨찾기 실패");
						}
					}, error: function(data){
						alert("즐겨찾기 실패");
					}
			});
		}else {
			return false;
		}
	}

</script>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<jsp:include page="../frontcommon/front_header.jsp" />
	<div id="contents">
		<div class="titleType1">
			<div class="area_tag">
				<div class="brandingIcon">
					<ul class="food"></ul>
				</div>
			</div>
			<h2 id="topTitle" class="select_tab on" >${lists[0].tour_post_sj}</h2>
			<input type="hidden" id="tour_name" value="${lists[0].tour_post_sj}">
			<div class="area_address" id="topAddr">
				<c:if test="${not empty lists[0].tour_gu_name}">
					<span>${lists[0].tour_gu_name}</span>
				</c:if>
			</div>
			<input type="hidden" id="tour_seq" value="${tour_seq}">
			<input type="hidden" id="mem_seq" value="${mem_seq}">
			<div class="post_area">
				<span class="ico">
				<button type="button" class="tour_btn" onclick="setFavoContentDetail()">즐겨찾기</button>
				<button type="button" class="tour_btn" onclick="history.back()">목록</button>
				</span>
				<span class="rline">
					<button type="button" class="btn_bookmark"
						onclick="setFavoContentDetail();">
						<span class="ico">좋아요</span>
					</button>
				</span>
			</div>
			<div class="db_cont_detail">
				<div class="detail_tab">
					
				</div>
				<div id="galleryGo">
					
					<div class="photo_gallery">
						<!-- 사진영역 -->
						<div
							class="swiper-container swiper-container-initialized swiper-container-horizontal">
							<div class="swiper-wrapper" id="pImgList">
								<div class="swiper-slide swiper-slide-active">
									<img class="swiper-lazy swiper-lazy-loaded" style="width:100%;height:100%;object-fit: contain;" alt="ss" src="${lists[0].image_url}">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="wrap_contView" id="detailinfoview">
					<!-- 세부 정보 -->
					<div class="area_txtView bottom" style="padding-bottom: 0px;">
						<div class="inr_wrap" style="overflow: visible; height: auto;">
							<div class="inr">
								<ul>
									<li><strong>문의 및 안내</strong><span class="mo"><a href="tel:${lists[0].tour_cmmn_telno }">${lists[0].tour_cmmn_telno }</a></span><span class="pc">${sb[0].tour_cmmn_telno }</span></li>
									<li><strong>주소</strong><span>${lists[0].tour_new_address }</span></li>
									<li><strong>이용시간</strong><span>${lists[0].tour_cmmn_use_time }</span></li>
									<li><strong>휴일</strong><span>${lists[0].tour_cmmn_rstde }</span></li>
									<li><strong>장애인 안내</strong><span>${lists[0].tour_bf_desc }</span></li>
									<li><strong>운영 요일</strong><span>${lists[0].tour_cmmn_bsnde } </span></li>
									<li><strong>홈페이지</strong><span><a href="${lists[0].tour_cmmn_hmpg_url }" target="_blank">${lists[0].tour_cmmn_hmpg_url }</a></span></li>
								</ul>
							</div>
						</div>
						<div class="cont_more" style="display: none;">
							<button type="button" class="btn_more" title="내용더보기">
								<span>더보기</span>
							</button>
						</div>
					</div>
					<!-- //세부 정보 -->
				</div>
			</div>

		</div>
	</div>
	<jsp:include page="../frontcommon/front_footer.jsp"/>
		<jsp:include page="../frontcommon/front_footer_common.jsp"/>
</body>
</html>