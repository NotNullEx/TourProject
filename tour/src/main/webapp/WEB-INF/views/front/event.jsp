<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../frontcommon/front_header_common.jsp" />
<style>

.box {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

.tableInfo td{
	text-align : left;
	vertical-align:middle;
}

.tablelay {
	table-layout: fixed;
}
</style>
</head>
<body class="d-flex flex-column">
	<main class="flex-shrink-0">
		<jsp:include page="../frontcommon/front_header.jsp"/>
		<section class="py-5">
			<h2 class="fw-bolder fs-5 mb-4"></h2>
			<section class="py-5">
                <div class="container px-5 my-5">
                    <div class="row gx-5 justify-content-center">
                        <div class="col-lg-8 col-xl-6">
                            <div class="text-center">
                                <h2 class="fw-bolder">여러가지 이벤트들을 놓치지 마세요!</h2>
                                <p class="lead fw-normal text-muted mb-5">진행중인이벤트</p>
                            </div>
                        </div>
                    </div>
                    <div class="row gx-5">
                         <c:forEach var="data" items="${list}">
	                    	<div class="col-lg-4 mb-5">
	                            <div class="card h-100 shadow border-0">
	                                <img class="card-img-top" src="${data.even_main_img }" alt="..." width = "300px" height = "300px" />
	                                <div class="card-body p-4">
	                                    <div class="badge bg-primary bg-gradient rounded-pill mb-2 d-inline-block text-truncate" style="max-width: 150px;">
	                                    	상세: <a class="text-decoration-none link-light" href="${data.even_org_link}" target="_blank">${data.even_org_link}</a>  
	                                    </div>
	                                    <h5 class="card-title mb-3"><a href="/front/eventDetail?even_code=${data.even_code}" class="text-decoration-none link-dark">${data.even_title}</a> </h5>
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
						href='<c:url value="/front/event?page=${pageMaker.startPage-1}"/>'>prev</a></li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage}"
					end="${pageMaker.endPage }" var="pageNum" varStatus="status">
					<c:choose>
						<c:when test="${pageMaker.cri.page == pageNum}">
							<li class="page-item active" aria-current="page"><a
								class="page-link" href='<c:url value="/front/event?page=${pageNum}"/>'>${pageNum}</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item "><a class="page-link"
								href='<c:url value="/front/event?page=${pageNum}"/>'>${pageNum}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage >0}">
					<li class="page-item"><a class="page-link"
						href='<c:url value="/front/event?page=${pageMaker.endPage+1}"/>'>next</a></li>
				</c:if>
			</ul>
		</section>
	</main>
	<jsp:include page="../frontcommon/front_footer.jsp" />
	<jsp:include page="../frontcommon/front_footer_common.jsp" />
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>
