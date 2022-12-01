<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../admincommon/admin_header_common.jsp" />
<style type="text/css">
	.table{
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
</style>
<script type="text/javascript">
	function go_url(){
		var url = $("#url").val();
		location.href(url);
	}
	function go_modify(tour_seq){
		var t_seq = tour_seq;
		window.location.assign("/admin/dataUpdate?tour_seq="+t_seq);
	}
	function go_delete(tour_seq){
		var t_seq = tour_seq;
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
	function data_insert(){
		$.ajax({
			url : "/admin/dataInsert",
			success:function(data){
				alert("정상적으로 등록되었습니다.");
				window.location.assign("/admin/tourList"); 
			},
			error:function(data){
				alert("데이터 등록에 실패했습니다.");
			}
		});
		
	}
	function go_img(seq,tour_post_sj){
		window.location.assign("/admin/addImage?tour_seq="+seq +"&tour_post_sj="+tour_post_sj);
	}

</script>
</head>
<body class="d-flex flex-column">
	<main class="flex-shrink-0">
		<!-- 헤더 -->
		<jsp:include page="../admincommon/admin_header.jsp" />
		<!-- Header-->
		<header class="py-5">
			<div class="container px-5">
				<div class="row justify-content-center">
					<div class="col-lg-8 col-xxl-6">
						<div class="text-center my-5">
							<h1 class="fw-bolder mb-3">서울 관광명소 리스트</h1>
							
							<p class="lead fw-normal text-muted mb-4">서울의 관광지를 한눈에!</p>
							<!-- <a class="btn btn-primary btn-lg" href="#scroll-target">Read
								our story</a> -->
						</div>
					</div>
				</div>
			</div>
		</header>
		<!-- About section one-->
		<section class="py-5 bg-light" id="scroll-target">
			<div><strong><a href="https://t1.daumcdn.net/cfile/tistory/27494B4E51BA8D6B08"></a> 추천!</strong></div>
			<div class="container px-5 my-5">
				<div class="row gx-5 align-items-center">
					<div class="col-lg-6">
						<img class="img-fluid rounded mb-5 mb-lg-0"
							src="${list[0].image_url}" alt="..." />
					</div>
					<div class="col-lg-6">
						<h2 class="fw-bolder">${list[0].tour_post_sj}</h2>
						<p class="lead fw-normal text-muted mb-0">${list[0].tour_new_address }</p>
					</div>
				</div>
			</div>
		</section>
		<!-- About section two-->
		<section class="py-5">
		<div style="float: right; margin-bottom: 5px; margin-right: 5px;">
				<c:if test="${not empty sessionScope.ADMIN_ID}">
					<button type="button" id="addRes" class="btn btn-primary" onclick="location.href='/admin/adminDataInsert'">관광지 추가</button>
					<button type="button" id="addRes" class="btn btn-primary" onclick="data_insert()">api 관광지 추가</button>
				</c:if>
		</div>
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">No.</th>
						<th scope="col">관광지 이미지</th>
						<th scope="col">정보</th>
						<th style="width: 30%">홈페이지 바로가기</th>
						<th scope="col">이미지 추가</th>
						<th scope="col">수정/삭제</th>
					</tr>
				</thead>
				<tbody class="tableInfo">
					<c:set var="num" value="${totalCount - ((curPage-1) * 10) }"/>
					<c:forEach var="item" items="${list}" varStatus="vs">
						<tr>
							<th scope="row"><c:out value="${num}"/></th>
							<td><img class="image" alt="tt" src="${item.image_url}" width="293px;" height="172px;"> </td>
							<td><a class="text-decoration-none link-dark" href="/admin/tourDetail?tour_seq=${item.tour_seq}">${item.tour_post_sj}
							</a> <br>
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
								<button type="button" id="img" class="btn btn-primary" onclick="javascript: go_img(${item.tour_seq},'${item.tour_post_sj}')">이미지 추가</button>
							</td>
							<td>
								<button type="button" id="modi" class="btn btn-primary" onclick="javascript: go_modify(${item.tour_seq})">수정</button>
								<button type="button" id="del" class="btn btn-success" onclick="javascript: go_delete(${item.tour_seq})">삭제</button>
							</td>
						</tr>
						<c:set var = "num" value = "${num - 1 }" />
					</c:forEach>
				</tbody>
			</table>
			<ul class="pagination justify-content-center">
				<c:if test="${pageMaker.prev }">
					<li class="page-item"><a class="page-link" href='<c:url value="/admin/tourList?page=${pageMaker.startPage-1 }"/>'>prev</a></li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum" varStatus="status">
					<c:choose>
						<c:when test = "${pageMaker.cri.page == pageNum}">
							<li class="page-item active" aria-current="page">
								<a class="page-link" href='<c:url value="/admin/tourList?page=${pageNum }"/>'>${pageNum }</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item ">
								<a class="page-link" href='<c:url value="/admin/tourList?page=${pageNum }"/>'>${pageNum }</a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage >0 }">
					<li class="page-item"><a class="page-link" href='<c:url value="/admin/tourList?page=${pageMaker.endPage+1 }"/>'>next</a></li>
				</c:if>
			</ul>
		</section>
	</main>
	<jsp:include page="../admincommon/admin_footer.jsp" />
	<jsp:include page="../admincommon/admin_footer_common.jsp" />
</body>
</html>
