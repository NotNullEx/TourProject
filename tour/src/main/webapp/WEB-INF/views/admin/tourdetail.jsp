<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	.titleType1{
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
@media screen and (max-width: 1023px)
.detail_tab ul li a {
    line-height: 39px;
    font-size: 15px;
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
@media screen and (max-width: 1023px)
.photo_gallery .swiper-container {
    height: 193.5px;
}
.titleType1 .post_area {
    position: relative;
    top: 0;
    margin: 35px 0 18px;
}
.titleType1 .post_area > button {
    float: left;
}

.post_area .btn_good {
    padding: 0 10px 0 0;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../admincommon/admin_header_common.jsp"/>
</head>
<body>
	<jsp:include page="../admincommon/admin_header.jsp"/>
	<div class="titleType1">
		<div class="area_tag">
			<div class="brandingIcon"><ul class="food"></ul></div>
		</div>
		<h2 id="topTitle">관광지명</h2>
		<div class="area_address" id="topAddr">
			<span>구까지의 주소</span>
		</div>
		<div class="post_area">
			<button type="button" class="btn_good" onclick="setLike();">
				<span class="ico">좋아요</span><span class="num" id="conLike">0</span>
			</button>
			<span class="num_view"><em class="tit">조회수</em><span class="num" id="conRead">477</span></span> 
			<span class="rline">
			<button type="button" class="btn_bookmark" onclick="setFavoContentDetail();">
				<span class="ico">즐겨찾기</span>
			</button>
			</span>
		</div>
		<div class="db_cont_detail">
			<div class="detail_tab">
				<ul>
					<li class="select_tab on" id="photoTab">
						<a href="#" title="선택됨"> 
							<span>사진보기</span>
						</a>	
					</li>	
					<li class="select_tab" id="detailTab">
						<a href="javascript:tabChange('detailGo');">
							<span>상세정보</span>
						</a>
					</li>	
					<li class="select_tab" id="talkTab">
						<a href="javascript:tabChange('replyGo');">
							<span>여행톡</span>
						</a>
					</li>
				</ul>
			</div>
			<div id="galleryGo">
				<div class="user_reg">
					<button type="button" onclick="imgupload();">
						<span>사진 등록하기</span>
					</button>
				</div>
				<h3 class="blind">사진보기</h3>
				<div class="photo_gallery">
					<!-- 사진영역 -->
					<div class="swiper-container swiper-container-initialized swiper-container-horizontal">
						<div class="swiper-wrapper" id="pImgList" style="transition-duration: 0ms; transform: translate3d(-1856px, 0px, 0px);">
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>