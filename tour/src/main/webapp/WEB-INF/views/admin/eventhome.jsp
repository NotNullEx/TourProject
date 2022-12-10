<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../admincommon/admin_header_common.jsp" />
</head>
<script type="text/javascript">
	
</script>

<style>
.bimg {
	max-height: 400px;
	max-width: 500px;
	background-repeat: no-repeat;
	background-position: center;
	object-fit: cover;
}

.pageInfo {
	list-style: none;
	display: inline-block;
	margin: 50px 0 0 100px;
}

.pageInfo li {
	float: left;
	font-size: 20px;
	margin-left: 18px;
	padding: 7px;
	font-weight: 500;
}

a:link {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}

a:hover {
	color: black;
	text-decoration: underline;
}

.active {
	background-color: #cdd5ec;
}

.box {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

.tableInfo td {
	vertical-align: middle;
}

.tablelay {
	table-layout: fixed;
}
</style>
<script type="text/javascript">
	function eventDataInsert(code) {
		if(code != null) {
			alert("이미 데이터가 존재합니다.")
		}else {
			$.ajax({
				url : "/admin/data/eventDataInsert",
				success : function(data) {
					console.log(data);
					if(data != null || data == ' ') {
						alert("이벤트 api 등록이 완료되었습니다."); 
					}else {
						alert("이벤트 api 등록에 실패했습니다.");
					}
				}
			});
		}
	}
	
	function resetEvent() {
		if(confirm("정말 event를 삭제하시겠습니까??")) {
			$.ajax({
				url : "/admin/data/deleteEvent",
				success : function(data) {
					console.log(data);
					if(data > 0) {
						alert("event DB 삭제가 완료되었습니다."); 
					}else {
						alert("event DB 삭제에 실패했습니다.");
					}
				}
			});
		}
	}
</script>
<body class="d-flex flex-column h-100">
	<main class="flex-shrink-0">
		<jsp:include page="../admincommon/admin_header.jsp" />
		<section class="py-5">
			
			<section class="py-5">
                <div class="container px-5 my-5">
                    <div class="row gx-5 justify-content-center">
                        <div class="col-lg-8 col-xl-6">
                            <div class="text-center">
                                <h2 class="fw-bolder">요즘 핫한 서울 행사!</h2>
                                <p class="lead fw-normal text-muted mb-5">여러가지 행사에 참가해보세요!</p>
                            </div>
                        </div>
                    </div>
                    
	                    
                    
                   
                    <div class="row gx-5">
                    	<div style="float: right; margin-bottom: 10px; margin-right: 5px;">
                    		<button type="button" id="addRes" style="float: right; margin-left: 5px;" class="btn btn-primary" onclick="location.href='/excel/download/eventList'">Event 엑셀 다운로드</button>
                    		<button type="button" id="addRes" style="float: right; margin-left: 5px;" class="btn btn-primary" onclick="eventDataInsert(${eventData[0].even_code})">Event API 데이터 추가</button>
							<button type="button" id="addRes" style="float: right;" class="btn btn-primary" onclick="location.href='/admin/addEvent'">Event 데이터 추가</button>
                    	</div>
                         <c:forEach var="data" items="${data}">
	                    	<div class="col-lg-4 mb-5">
	                            <div class="card h-100 shadow border-0">
	                                <img class="card-img-top" src="${data.even_main_img }" alt="..." width="316px;" height="316px;"/>
	                                <div class="card-body p-4">
	                                    <div class="badge bg-primary bg-gradient rounded-pill mb-2 d-inline-block text-truncate" style="max-width: 150px;">
	                                    	<a class="text-decoration-none link-light" href="${data.even_org_link}" target="_blank">${data.even_org_link}</a>  
	                                    </div>
	                                    <h5 class="card-title mb-3"><a href="/admin/eventDetail?even_code=${data.even_code}" class="text-decoration-none link-dark">${data.even_title}</a> </h5>
	                                    <p class="card-text mb-0">${data.even_place}</p>
	                                </div>
	                            </div>
	                        </div>
                    	</c:forEach>
                	</div>
                </div>
            </section>
			<ul class="pagination justify-content-center">
				<c:if test="${pageMaker.prev}">
					<li class="page-item"><a class="page-link"
						href='<c:url value="/admin/event?page=${pageMaker.startPage-1}"/>'>prev</a></li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage}"
					end="${pageMaker.endPage }" var="pageNum" varStatus="status">
					<c:choose>
						<c:when test="${pageMaker.cri.page == pageNum}">
							<li class="page-item active" aria-current="page"><a
								class="page-link"
								href='<c:url value="/admin/event?page=${pageNum}"/>'>${pageNum}</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item "><a class="page-link"
								href='<c:url value="/admin/event?page=${pageNum}"/>'>${pageNum}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage >0}">
					<li class="page-item"><a class="page-link"
						href='<c:url value="/admin/event?page=${pageMaker.endPage+1}"/>'>next</a></li>
				</c:if>
			</ul>
		</section>
	</main>
	<jsp:include page="../admincommon/admin_footer.jsp" />
	<jsp:include page="../admincommon/admin_footer_common.jsp" />
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>
