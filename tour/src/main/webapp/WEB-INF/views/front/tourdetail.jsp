<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../frontcommon/front_header_common.jsp" />
<style type="text/css">
#contents {
	width: 980px;
	margin: 0 auto;
	padding: 0 20px 27px 20px;
}

.titleType1 {
	text-align: center;
	position: relative;
}

.detail_tab ul li {
	float: left;
	width: 25%;
	text-align: center;
	border-top: 1px solid #e6e6e6;
	border-bottom: 1px solid #e6e6e6;
}

dl, ul, ol, menu, li {
	list-style: none;
}

.detail_tab ul li a {
	line-height: 39px;
	font-size: 15px;
}

element.style {
	
}

@media screen and (max-width: 1023px) .detail_tab ul li a {
	line-height
	:
	 
	39px
	;
	
    
	font-size
	:
	 
	15px
	;
	

}

.detail_tab ul li a {
	position: relative;
	display: block;
	line-height: 63px;
	font-size: 18px;
	color: #666;
	letter-spacing: -1px;
}

a:link, a:visited, a:hover, a:focus, a:active {
	text-decoration: none;
}

@media screen and (max-width: 1023px) .photo_gallery .swiper-container {
	height: 
	193
	.5px
	;
	

}

.titleType1 .post_area {
	position: relative;
	top: 0;
	margin: 35px 0 18px;
}

.titleType1 .post_area>button {
	float: left;
}

.post_area .btn_good {
	padding: 0 10px 0 0;
}
.post_area .num_view {
    float: left;
}
.post_area .btn_good .ico {
    width: 23px;
    height: 21px;
    background-image: url('/resources/img/free-icon-like-149217.png');
}
.post_area .btn_bookmark .ico {
    position: relative;
    width: 16px;
    height: 21px;
    background-image: url('/resources/img/favoritesstar_79753.png');
}
.post_area button .ico {
    display: inline-block;
    text-indent: -9999px;
}
.post_area .num {
    display: inline-block;
    margin-left: 8px;
    color: #000;
    font-size: 16px;
    vertical-align: top;
}
button {
    border: 0 none;
    background-color: transparent;
    cursor: pointer;
    outline: 0 none;
}
.wrap_contView {
    position: relative;
    width: 100%;
    margin-top: 24px;
}
div {
    box-sizing: border-box;
}
.wrap_contView .area_txtView {
    position: relative;
    padding-bottom: 0 !important;
}
.db_cont_detail .wrap_contView .area_txtView .inr_wrap .inr {
    overflow: hidden;
}
.db_cont_detail .wrap_contView .area_txtView ul li strong {
    float: none;
    margin-right: 0;
    position: relative;
    display: table-cell;
    width: 128px;
    padding: 0 0 0 12px;
    font-weight: 700;
    color: #333;
}
.db_cont_detail .wrap_contView .area_txtView ul li span.mo {
    display: none;
}

.db_cont_detail .wrap_contView .area_txtView ul li span {
    float: none;
    width: auto;
    display: table-cell;
    color: #666;
    padding-right: 20px;
    line-height: 1.4;
}
.db_cont_detail .wrap_contView .area_txtView ul li {
    float: left;
    width: 50%;
    padding: 0 0 9px 0;
    display: table;
    font-size: 15px;
    font-weight: 400;
    background: none !important;
</style>
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
			<h2 id="topTitle" class="select_tab on" >${sb[0].tour_post_sj}</h2>
			<input type="hidden" id="tour_name" value="${sb[0].tour_post_sj}">
			<div class="area_address" id="topAddr">
				<span>${address}</span>
			</div>
			<input type="hidden" id="tour_seq" value="${tour_seq}">
			<input type="hidden" id="mem_seq" value="${mem_seq}">
			<div class="post_area">
				<span class="ico">
				<button type="button" class="btn_good" onclick="setFavoContentDetail()">즐겨찾기</button>
				</span>
				<span class="num_view"><em class="tit">조회수</em><span
					class="num" id="conRead">477</span></span> <span class="rline">
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
									<img class="swiper-lazy swiper-lazy-loaded" style="width:100%;height:100%;object-fit: contain;" alt="ss" src="/resources/img/20221015_133042.jpg">
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
	<jsp:include page="../frontcommon/front_footer.jsp"/>
		<jsp:include page="../frontcommon/front_footer_common.jsp"/>
</body>
</html>