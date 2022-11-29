<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="../frontcommon/front_header_common.jsp" />
<style>
.center {text-align: center;}
</style>
<script type="text/javascript">
	function cancelFavorites(tour_seq) {
		if(confirm("즐겨찾기를 취소하시겠습니까?")) {
			$.ajax({
				url : "/front/cancelFavorites",
				data : {
					"tour_seq" : tour_seq
				},
				type : "POST",
				async : false,
    			success:function(data){
    				if (data.result == 1) {
    					alert("즐겨찾기를 취소했습니다.");
    					location.reload();
    				} else {
    					alert("즐겨찾기를 취소하는 과정에 문제가 발생했습니다.");
    				}
    			},
    			error:function(e){
    				alert("즐겨찾기를 취소하는 과정에 문제가 발생했습니다.");
    			}
			});
		}else {
			return false;
		}
	}
</script>
<body>
	<main>
		
		<jsp:include page="../frontcommon/front_header.jsp" />
		<div id="page-wrapper">
			<jsp:include page="../frontcommon/front_sidebar.jsp" />
			<h1 class="fw-bolder fs-5 mb-4 center">내가 즐겨찾는 관광지</h1>
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">No.</th>
						<th scope="col">상호명</th>
						<th scope="col">전화번호</th>
						<th scope="col">웹사이트</th>
						<th scope="col">즐겨찾기 취소</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="num" value="${totalCount - ((curPage-1) * 10) }"/>
					<c:forEach var="data" items="${list}" varStatus="status">
						<tr>
						<th scope="row"><c:out value="${num}"/></th>
						<td>${data.tour_post_sj}</td>
						<td>${data.tour_cmmn_telno}</td>
						<td><a href="${data.tour_cmmn_hmpg_url }" target="_blank">${data.tour_cmmn_hmpg_url }</a></td>
						<td><button type="button" id="modify" class="btn btn-primary" onclick="javascript: cancelFavorites(${data.tour_seq})">취소</button></td>
					</tr>
					<c:set var = "num" value = "${num - 1 }" />
					</c:forEach>
				</tbody>
			</table>
			<ul class="pagination justify-content-center">
				<c:if test="${pageMaker.prev }">
					<li class="page-item"><a class="page-link" href='<c:url value="/front/myBoardInfo?page=${pageMaker.startPage-1}"/>'>prev</a></li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum" varStatus="status">
					<c:choose>
						<c:when test = "${pageMaker.cri.page == pageNum}">
							<li class="page-item active" aria-current="page">
								<a class="page-link" href='<c:url value="/front/myBoardInfo?page=${pageNum}"/>'>${pageNum }</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item ">
								<a class="page-link" href='<c:url value="/front/myBoardInfo?page=${pageNum}"/>'>${pageNum }</a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage >0 }">
					<li class="page-item"><a class="page-link" href='<c:url value="/front/myBoardInfo?page=${pageMaker.endPage+1 }"/>'>next</a></li>
				</c:if>
			</ul>
		</div>
	</main>
</body>
</html>