<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../admincommon/admin_header_common.jsp" />
<link href="../resources/css/tourdetail.css" rel="stylesheet" type="text/css">
</head>
<script type="text/javascript">
	function go_modify(){
		var tour_seq = $("#tour_seq").val();
		window.location.assign("/admin/dataUpdate?tour_seq="+tour_seq);
	}
	function go_delete(){
		var tour_seq = $("#tour_seq").val();
		if(!confirm("삭제 하시겠습니까?")){
			return false;
		}else{
			$.ajax({
				url : "/admin/dataDelete?tour_seq=" + tour_seq,
    			success:function(data){
    				alert("정상적으로 삭제되었습니다.");
    				window.location.assign("/admin/tourList"); 
    			},
    			error:function(data){
    				alert("데이터 삭제에 실패했습니다.");
    			}
			});
		}
	}
</script>
<body>
	<jsp:include page="../admincommon/admin_header.jsp" />
	<div id="contents">
		<button type="button" onclick="go_modify()">수정</button>
		<button type="button" onclick="go_delete()">삭제</button>
		<button type="button" onclick="history.back()">목록</button>
		<input type="hidden" id="tour_seq" value="${sb[0].tour_seq}">
		<div class="titleType1">
			<div class="area_tag">
				<div class="brandingIcon">
					<ul class="food"></ul>
				</div>
			</div>
			<h2 id="topTitle" class="select_tab on">${sb[0].tour_post_sj}</h2>
			
			<div class="area_address" id="topAddr">
				<span>${address}</span>
			</div>
			<div class="post_area">
				<button type="button" class="btn_good" onclick="setLike();">
					<span class="ico">좋아요</span><span class="num" id="conLike">0</span>
				</button>
				<span class="num_view"><em class="tit">조회수</em><span
					class="num" id="conRead">477</span></span> <span class="rline">
					<button type="button" class="btn_bookmark"
						onclick="setFavoContentDetail();">
						<span class="ico">즐겨찾기</span>
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
									<img class="swiper-lazy swiper-lazy-loaded" style="width:100%;height:100%;object-fit: contain;" alt="ss" src="${sb[0].image_url}">
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
									<li><strong>문의 및 안내</strong><span class="mo"><a href="tel:${sb[0].tour_cmmn_telno }">${sb[0].tour_cmmn_telno }</a></span><span class="pc">${sb[0].tour_cmmn_telno }</span></li>
									<li><strong>주소</strong><span>${sb[0].tour_new_address }</span></li>
									<li><strong>이용시간</strong><span>${sb[0].tour_cmmn_use_time }</span></li>
									<li><strong>휴일</strong><span>${sb[0].tour_cmmn_rstde }</span></li>
									<li><strong>장애인 안내</strong><span>${sb[0].tour_bf_desc }</span></li>
									<li><strong>운영 요일</strong><span>${sb[0].tour_cmmn_bsnde } </span></li>
									<li><strong>홈페이지</strong><span><a href="${sb[0].tour_cmmn_hmpg_url }" target="_blank">${sb[0].tour_cmmn_hmpg_url }</a></span></li>
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
	<jsp:include page="../admincommon/admin_footer.jsp"/>
		<jsp:include page="../admincommon/admin_footer_common.jsp"/>
</body>
</html>