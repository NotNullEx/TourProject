<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
   		<jsp:include page="../frontcommon/front_header_common.jsp"/>
        <title>공지사항</title>
    </head>
    
<style type="text/css">
.tag-square-red {
	border-style: solid;
	border-color: red;
	color: red;
	box-sizing: border-box;
	width: 80px;
	display: inline-block;
}	

ul{
   list-style:none;
   text-align: center;   
}

a {
  text-align: center;
  text-decoration: none !important; 
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  
}


.board-list-title,
.board-list-inner {
    display: table;
    table-layout: fixed;
    overflow: hidden;
    width: 100%;
}

.board-list-title {
    border-top: 2px solid #000;
    border-bottom: 1px solid #e0e0e0;
}

.board-list-title > li {
    display: table-cell;
    height: 52px;
    padding: 15px 7px;
    box-sizing: border-box;
    color: #424242;
    font-weight: 400;
    text-align: center;
}

.board-list-inner > li {
    display: table-cell;
    box-sizing: border-box;
    padding: 15px 7px;
}

.board-list-inner > li,
.board-list-inner > li span,
.board-list-inner > li p,
.board-list-inner > li a {
    font-size: 1.4rem;
    font-weight: 400;
    line-height: 2.4rem;
}

.board-list-title > li.board-title-con,
.board-list-inner > li.board-title-con {
    width: 700px;
}

.title-row2 {
    overflow: hidden;
    text-overflow: ellipsis;
    word-wrap: break-word;
    display: -ms-flexbox;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    width: 100%;
    /* height: 5rem; */
    margin-bottom: 8px;
    color: #000;
    font-size: 1.8rem;
    font-weight: 600;
    line-height: 2.5rem;
    height: 5rem;
}

.title-row2 > a:hover{
    text-decoration: underline;
}

.board-list-inner > li.board-title-con .title-row2 {
    display: block;
    -webkit-line-clamp: 1;
    height: 25px;
    margin-bottom: 0;
    box-sizing: border-box;
    white-space: nowrap;
    overflow: hidden;
}

.tag {
    display: inline-block;
    padding: 16px 0 8px;
    color: #e90000;
    font-size: 1.4rem;
    line-height: 2.4rem;
}

.date {
    padding-left: 8px;
    color: #9e9e9e;
    font-size: 1.4rem;
    font-weight: 400;
    line-height: 2.4rem;
}

.board-list-inner .tag {
    padding: 0;
}

.board-list-inner .date {
    font-weight: 300;
}

.board-list > li {
    height: 68px;
    box-sizing: border-box;
    border-bottom: 1px solid #e0e0e0;
    display: inline-block;
}

.board-list > li span {
    vertical-align: middle;
}

.board-list .tag-square {
    padding: 4px 15px;
    height: 28px;
    line-height: 1.9rem;
    font-size: 1.3rem;
}

.board-num {
    color: #9e9e9e;
}
// 태블릿
@media all and (min-width: 1025px) and (max-width: 1240px) {
	.tag-square-red {
		border-style: solid;
		border-color: red;
		color: red;
	}

    .board-list-title > li:first-child,
    .board-list-title > li:nth-child(4),
    .board-list-inner > li:first-child,
    .board-list-inner > li:nth-child(4) {
        width: 8%;
    }

    .board-list-title > li:nth-child(2),
    .board-list-title > li:last-child,
    .board-list-inner > li:nth-child(2),
    .board-list-inner > li:last-child {
        width: 12%;
    }

    .board-list-title > li.board-title-con,
    .board-list-inner > li.board-title-con {
        width: 60%;
    }
}

// 모바일
@media all and (max-width: 1024px) {
	.tag-square-red {
		border-style: solid;
		border-color: red;
		color: red;
		box-sizing: border-box;
	}
	.board-list-title {
        display: none;
    }
    .board-list {
        border-top: 1px solid #000;
    }
    .board-list > li {
        position: relative;
        height: initial;
        margin-top: 23px;
    }
    .board-list-inner {
        padding: 0 8px 23px;
    }
    .board-list-inner > li {
        display: block;
        float: left;
        padding: 0;
    }
    .board-list-inner > li:first-child,
    .board-list-inner > li.board-title-con {
        width: initial;
    }
    .board-list-inner > li.board-title-con {
        clear: both;
        float: none;
        width: 90%;
        padding-top: 8px;
    }

        position: absolute;
        right: 8px;
        top: 0;
    }

    .icon-clip {
        width: 18px;
        height: 18px;
    }
    .board-list-inner > li.board-num {
        line-height: 24px;
    }
    .board-list-inner .tag-square {
        height: 24px;
        padding: 4px 7px;
        font-size: 1.1rem;
        line-height: 13px;
    }
    .board-list-inner .tag {
        padding: 0;
        padding-left: 16px;
        line-height: 24px;
    }
    .board-list-inner > li,
    .board-list-inner > li span,
    .board-list-inner > li p,
    .board-list-inner > li a {
        font-size: 1.2rem;
        line-height: 1.8rem;
    }
    .board-list-inner > li.board-title-con .title-row2 {
        display: -webkit-box;
        white-space: break-spaces;
        -webkit-line-clamp: 2;
        height: 20%;
    }
}   

</style>

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
									<li class="board-num"><div class="tag-square-red">공지</div></li>
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
									<li class="board-num">공지</li>
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
											<a class="page-link" href='<c:url value="/front/notice/list?page=${pageNum}"/>'>${pageNum}</a>
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
