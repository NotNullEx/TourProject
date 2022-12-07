<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
   		<jsp:include page="../frontcommon/front_header_common.jsp"/>
   		<link href="/resources/css/notice_style.css" rel="stylesheet" type="text/css">
        <title>공지사항</title>
    </head>

    <body class="d-flex flex-column h-100">
        <main class="flex-shrink-0">
            <jsp:include page="../frontcommon/front_header.jsp"/>
            <!-- Page Content-->
            <br>
                    <div class="text-center mb-5">
                        <h1 class="fw-bolder">공지사항</h1>
                    </div>
                    <ul class="board-list-title">
					    <li class="txt-14">번호</li>
					    <li class="board-title-con txt-14">제목</li>
					    <li class="txt-14">작성자</li>
					    <li class="txt-14">작성일</li>
					</ul>
					<ul class="board-list">
						<c:set var="num" value="${totalCount - ((curPage-1) * 10) }" />
						<c:forEach var="list" items="${list}" begin="0" end="1">
							<li>
								<ul class="board-list-inner">
								<c:choose>
									<c:when test="${pageMaker.cri.page == 1}">
										<li class="board-num"><div class="tag-square-red">공지</div></li>
									</c:when>
									<c:otherwise>
										<li class="board-num"><div class="tag-square">공지</div></li>
									</c:otherwise>
								</c:choose>
									<li class="board-title-con txt-l">
										<a href="/front/notice/detail?noti_seq=${list.noti_seq}" style="color: inherit;">${list.noti_title}</a>
									</li>
									<li>
										<p class="date">${list.admin_name}</p>
									</li>
									<li>
										<p class="date">${list.noti_reg_date}</p>
									</li>
								</ul>
							</li>
						</c:forEach>
						<c:forEach var="list" items="${list}"  begin="2" >
							<li>
								<ul class="board-list-inner">
									<li class="board-num"><div class="tag-square">공지</div></li>
									<li class="board-title-con txt-l">
										<a href="/front/notice/detail?noti_seq=${list.noti_seq}" style="color: inherit;">${list.noti_title}</a>
									</li>
									<li>
										<p class="date">${list.admin_name}</p>
									</li>
									<li>
										<p class="date">${list.noti_reg_date}</p>
									</li>
								</ul>
							</li>
						</c:forEach>
					</ul>
					
						<ul class="pagination justify-content-center">
							<c:if test="${pageMaker.prev}">
								<li class="page-item"><a class="page-link" href='<c:url value="/front/notice/list?page=${pageMaker.startPage-1}"/>'>prev</a></li>
							</c:if>
							<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage }" var="pageNum" varStatus="status">
								<c:choose>
									<c:when test = "${pageMaker.cri.page == pageNum}">
										<li class="page-item active" aria-current="page">
											<a class="page-link noti_a" href='<c:url value="/front/notice/list?page=${pageNum}"/>'>${pageNum}</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item ">
											<a class="page-link" href='<c:url value="/front/notice/list?page=${pageNum}"/>'>${pageNum}</a>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${pageMaker.next && pageMaker.endPage >0}">
								<li class="page-item"><a class="page-link" href='<c:url value="/front/notice/list?page=${pageMaker.endPage+1}"/>'>next</a></li>
							</c:if>
						</ul>
        </main>
        <jsp:include page="../frontcommon/front_footer.jsp"/>
		<jsp:include page="../frontcommon/front_footer_common.jsp"/>
    </body>
</html>
