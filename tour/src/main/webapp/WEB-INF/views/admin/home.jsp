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
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">


		google.charts.load('current', {'packages':['corechart']});
		google.charts.setOnLoadCallback(memberChart);
		google.charts.setOnLoadCallback(contentsChart);
		google.charts.setOnLoadCallback(boardChart);
		
		function memberChart() {
		var signin = parseInt($('#signin').val());
		var signout = parseInt($('#signout').val());
		console.log(signin);
		var data = google.visualization.arrayToDataTable([
			['회원', '수'],
		    ['가입자 수',signin],
		    ['탈퇴자 수',signout]
		  ]);
		
		  var options = {
		    title: '회원수'
		  };
		
		  var chart = new google.visualization.PieChart(document.getElementById('memberChart'));
		
		  chart.draw(data, options);
		}
		
		function contentsChart() {
			var tour = parseInt($('#tour').val());
			var event = parseInt($('#event').val());
			var res = parseInt($('#res').val());
			  var data = google.visualization.arrayToDataTable([
			    ['Task', 'Hours per Day'],
			    ['관광지 수',tour],
			    ['음식점 수',res],
			    ['행사 수',event]
			  ]);
			
			  var options = {
			    title: '컨텐츠 수'
			  };
			
			  var chart = new google.visualization.PieChart(document.getElementById('contentsChart'));
			
			  chart.draw(data, options);
			}
		
		function boardChart() {
			var board = parseInt($('#board').val());
			var coments = parseInt($('#coments').val());
			  var data = google.visualization.arrayToDataTable([
			    ['Task', 'Hours per Day'],
			    ['게시글 수',board],
			    ['댓글 수',coments],
			  ]);
			
			  var options = {
			    title: '게시글 수'
			  };
			
			  var chart = new google.visualization.PieChart(document.getElementById('boardChart'));
			
			  chart.draw(data, options);
			}
	
	
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
		<header >
			<div class="row gx-5 justify-content-center">
					<div class="col-lg-8 col-xl-6">
						<div class="text-center">
							<h2 class="fw-bolder">사이트 현황</h2>
							
						</div>
					</div>
				</div>
			<table>
				<tbody>
					<tr>
						<td>
							<div id="memberChart" style="width: 600px; height: 300px;">
								<input type="hidden" id="signin" value="${signin}">
								<input type="hidden" id="signout" value="${signout}">
							</div>
						</td>
						<td>
							<div id="contentsChart" style="width: 600px; height: 300px;">
								<input type="hidden" id="tour" value="${tour}">
								<input type="hidden" id="event" value="${event}">
								<input type="hidden" id="res" value="${res}">
							</div>
						</td>
						<td>
							<div id="boardChart" style="width: 600px; height: 300px;">
								<input type="hidden" id="board" value="${board}">
								<input type="hidden" id="coments" value="${coments}">
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			
		</header>

		<section class="py-5">
			<div class="container px-5 my-5">
				
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
