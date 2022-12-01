<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<meta name="referrer" content="no-referrer-when-downgrade" />
<head>
<jsp:include page="../admincommon/admin_header_common.jsp" />
</head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function go_modify(seq){
		window.location.assign("/admin/notificationUpdate?noti_seq="+seq);
	}
	function go_delete(seq,status){
		if(!confirm("게시판을 삭제 하시겠습니까?")){
			return false;
		}else{
				$.ajax({
					url : "/admin/setNotiHidden",
					data : {
						"noti_status" : status,
						"noti_seq" : seq
					},
					type : "POST",
					async : false,
	    			success:function(data){
	    				console.log(data);
	    				if (data.code == 200) {
	    					alert(data.message);
	    					window.location.assign("/admin"); 
	    				} else if (data.code == 403) {
	    					location.href = "/admin/login";
	    				} else {
	    					alert("게시판 삭제에 실패했습니다.");
	    				}
	    			},
	    			error:function(e){
	    				console.log(e);
	    				alert("게시판 삭제에 실패했습니다.");
	    			}
				});
		}
	}
</script>
<body class="d-flex flex-column h-100">
	<main class="flex-shrink-0">
		<jsp:include page="../admincommon/admin_header.jsp" />
		<!-- Header-->
		<header class="bg-dark py-5">
			<div class="container px-5">
				<div class="row gx-5 align-items-center justify-content-center">
					<div class="col-lg-8 col-xl-7 col-xxl-6">
						<div class="my-5 text-center text-xl-start">

							<h1 class="display-5 fw-bolder text-white mb-2">
								<a href="/admin/tourDetail?tour_seq=${lists[0].tour_seq}"
									class="text-decoration-none link-light">${lists[0].tour_post_sj}</a>
							</h1>
							<p class="lead fw-normal text-white-50 mb-4">${lists[0].tour_new_address}</p>
						</div>
					</div>
					<div class="col-xl-5 col-xxl-6 d-none d-xl-block text-center">
						<img class="img-fluid rounded-3 my-5" src="https://dummyimage.com/600x400/343a40/6c757d" alt="..." />
					</div>
				</div>
			</div>
		</header>

		<section class="py-5">
			<div class="container px-5 my-5">
				<div class="row gx-5 justify-content-center">
					<div class="col-lg-8 col-xl-6">
						<div class="text-center">
							<h2 class="fw-bolder">요즘 핫한 서울 볼거리!</h2>
							<p class="lead fw-normal text-muted mb-5">2030세대들이 가볼만한 이쁜곳!</p>
						</div>
					</div>
				</div>
				<div class="row gx-5">
					<!-- Call to action-->
					<aside class="bg-gradient rounded-3 p-4 p-sm-5 mt-5">
						<c:if test="${not empty sessionScope.ADMIN_ID}">
							<button type="button" id="addNoti" class="btn btn-primary" style="float: right; margin-bottom: 5px; margin-right: 5px;" onclick="location.href='/admin/createNotification'">공지사항 등록</button>
						</c:if>
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">No</th>
									<th scope="col">작성자</th>
									<th scope="col">제목</th>
									<th scope="col">작성일</th>
									<th scope="col">수정/삭제</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="num" value="${totalCount - ((curPage-1) * 10) }"/>
								<c:forEach var="list" items="${list}" >
									<tr>
										<th scope="row"><c:out value="${num}"/></th>
										<td><c:out value="${list.admin_name}"/></td>
										<td><a href="/admin/notificationDetail?noti_seq=${list.noti_seq}"><c:out value="${list.noti_title}"/></a></td>
										<td><c:out value="${list.noti_reg_date}"/></td>
										<td>
											<button type="button" id="modify" class="btn btn-primary" onclick="javascript: go_modify(${list.noti_seq})">수정</button>
											<button type="button" id="delete" class="btn btn-success" onclick="javascript: go_delete(${list.noti_seq},${list.noti_status})">삭제</button>
										</td>
									</tr>
									<c:set var = "num" value = "${num - 1 }" />
								</c:forEach>
							</tbody>
						</table>
						<ul class="pagination justify-content-center">
							<c:if test="${pageMaker.prev}">
								<li class="page-item"><a class="page-link" href='<c:url value="/admin?page=${pageMaker.startPage-1}"/>'>prev</a></li>
							</c:if>
							<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage }" var="pageNum" varStatus="status">
								<c:choose>
									<c:when test = "${pageMaker.cri.page == pageNum}">
										<li class="page-item active" aria-current="page">
											<a class="page-link" href='<c:url value="/admin?page=${pageNum}"/>'>${pageNum}</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item ">
											<a class="page-link" href='<c:url value="/admin?page=${pageNum}"/>'>${pageNum}</a>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${pageMaker.next && pageMaker.endPage >0}">
								<li class="page-item"><a class="page-link" href='<c:url value="/admin?page=${pageMaker.endPage+1}"/>'>next</a></li>
							</c:if>
						</ul>
					</aside>
				</div>
			</div>
		</section>

	</main>
	<jsp:include page="../admincommon/admin_footer.jsp" />
	<jsp:include page="../admincommon/admin_footer_common.jsp" />
</body>

</html>
