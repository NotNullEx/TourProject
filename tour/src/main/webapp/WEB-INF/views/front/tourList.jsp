<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../frontcommon/front_header_common.jsp" />
<style type="text/css">
	.table.table{
		width: 80%;
		margin-left:auto; 
    	margin-right:auto;
	}
	.tableInfo td{
		vertical-align:middle;
		}
	.image{
		width: 350;
	}
	.col-lg-6 {
		width : 100% !important;
	}
	.img-fluid {
		width : 100%;
		height : 700px !important;
	}
</style>
<script type="text/javascript">

	$(function() {
		$('.recommend').slick({
		      slide: 'div',        //슬라이드 되어야 할 태그
		      infinite : true,     //무한 반복 옵션     
		      slidesToShow : 1,        // 한 화면에 보여질 컨텐츠 개수
		      slidesToScroll : 1,        //스크롤 한번에 움직일 컨텐츠 개수
		      speed : 500,     // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
		      arrows : false,         // 옆으로 이동하는 화살표 표시 여부
		      dots : false,         // 스크롤바 아래 점으로 페이지네이션 여부
		      autoplay : true,            // 자동 스크롤 사용 여부
		      autoplaySpeed : 2000,         // 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
		      pauseOnHover : true,        // 슬라이드 이동    시 마우스 호버하면 슬라이더 멈추게 설정
		      vertical : false,        // 세로 방향 슬라이드 옵션
		      prevArrow : "<button type='button' class='slick-prev'>Previous</button>",
		      nextArrow : "<button type='button' class='slick-next'>Next</button>",
		      draggable : true,     //드래그 가능 여부 

		});
	});

	function go_url(){
		var url = $("#url").val();
		location.href(url);
	}
	
	
	var addFavorite = function(tour_seq) {
		if(${not empty sessionScope.MEMBER_ID}){
			if(confirm("즐겨찾기에 추가하시겠습니까?")) {
				
				var mem_seq = '${sessionScope.FRONT_US_SEQ}';
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
							alert("즐겨찾기 되었습니다.");
							location.reload();
						}else{
							alert("즐겨찾기 실패");
						}
					}, error: function(data){
						alert("즐겨찾기 실패");
					}
				});
			}
		} else {
			alert('로그인 해 주세요');
			location.href="/front/login";
		}
	}

</script>
</head>
<body class="d-flex flex-column">
	<main class="flex-shrink-0">
		<!-- 헤더 -->
		<jsp:include page="../frontcommon/front_header.jsp" />
		<!-- Header-->
		<header class="py-5">
			<div class="container px-5">
				<div class="row justify-content-center">
					<div class="col-lg-8 col-xxl-6">
						<div class="text-center my-5">
							<h1 class="fw-bolder mb-3">서울 관광명소 리스트</h1>
							<p class="lead fw-normal text-muted mb-4">서울의 관광지를 한눈에!</p>
						</div>
					</div>
				</div>
			</div>
		</header>
		<!-- About section one-->
		<section class="py-5 bg-light" id="scroll-target">
			<h2 style="text-align : center;">인기 관광지</h2>
			<div class="container px-5 my-5 recommend">
				<c:forEach var="list" items="${tourRecommendList}">
					<div class="row gx-5 align-items-center">
						<div class="col-lg-6">
							<img class="img-fluid rounded mb-5 mb-lg-0"
								src="${list.image_url }" alt="..." />
						</div>
						<div class="col-lg-6">
							<h2 class="fw-bolder">${list.tour_post_sj }</h2>
						</div>
					</div>
				</c:forEach>
			</div>
		</section>
		<!-- About section two-->
		<section class="py-5">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">No.</th>
						<th scope="col">관광지 이미지</th>
						<th scope="col">정보</th>
						<th scope="col">홈페이지 바로가기</th>
						<th scope="col">즐겨찾기 추가</th>
					</tr>
				</thead>
				<tbody class="tableInfo">
					<c:set var="num" value="${totalCount - ((curPage-1) * 10) }"/>
					<c:forEach var="item" items="${list}" varStatus="vs">
						<tr>
							<th scope="row"><c:out value="${num}"/></th>
							<td><a href = '/front/tourDetail?tour_seq=${item.tour_seq }'><img class="image" alt="tt" src="../resources/img/001.jpg"></a></td>
							<td><a class="text-decoration-none link-dark" href="/front/tourDetail?tour_seq=${item.tour_seq}" >${item.tour_post_sj}</a> <br>
								${item.tour_new_address }<br>
								<c:choose>
									<c:when test = "${item.tour_cmmn_fax == null or item.tour_cmmn_fax eq ''}">
										- <br>
									</c:when>
									<c:otherwise>
							            ${item.tour_cmmn_fax }
							        </c:otherwise>
								</c:choose>
							</td>
							<td><a class="text-decoration-none link-dark" href="${item.tour_cmmn_hmpg_url}" target="_blank">${item.tour_cmmn_hmpg_url}</a></td>
							<td>
								<c:choose>
									<c:when test="${item.wishflag eq 'Y' }">
										<button disabled="disabled">추가완료</button>
									</c:when>
									<c:otherwise>
										<img src = "/resources/img/like.png" width="50" height="50" onclick="javascript:addFavorite(${item.tour_seq})">
									</c:otherwise>
								</c:choose>
								
							</td>
						</tr>
						<c:set var = "num" value = "${num - 1 }" />
					</c:forEach>
				</tbody>
			</table>
			<ul class="pagination justify-content-center">
				<c:if test="${pageMaker.prev }">
					<li class="page-item"><a class="page-link" href='<c:url value="/front/tourList?page=${pageMaker.startPage-1 }"/>'>prev</a></li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum" varStatus="status">
					<c:choose>
						<c:when test = "${pageMaker.cri.page == pageNum}">
							<li class="page-item active" aria-current="page">
								<a class="page-link" href='<c:url value="/front/tourList?page=${pageNum }"/>'>${pageNum }</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item ">
								<a class="page-link" href='<c:url value="/front/tourList?page=${pageNum }"/>'>${pageNum }</a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage >0 }">
					<li class="page-item"><a class="page-link" href='<c:url value="/front/tourList?page=${pageMaker.endPage+1 }"/>'>next</a></li>
				</c:if>
			</ul>
		</section>
	</main>
	<jsp:include page="../frontcommon/front_footer.jsp" />
	<jsp:include page="../frontcommon/front_footer_common.jsp" />
</body>
</html>
