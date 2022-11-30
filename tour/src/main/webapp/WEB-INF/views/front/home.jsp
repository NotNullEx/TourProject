<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="../frontcommon/front_header_common.jsp"/>
        <style>
        	#regist,#login {
        		border : 1px solid #BDBDBD;
        	}
        
        </style>
    </head>
    <script type="text/javascript">
    	function goLogin() {
    		location.href= "/front/login";
    	}
    	
    	function goRegist() {
    		location.href="/front/createMember";
    	}
    </script>
    <body class="d-flex flex-column h-100">
        <main class="flex-shrink-0">
           <jsp:include page="../frontcommon/front_header.jsp"/>
            <!-- Header-->
            <header class="bg-dark py-5">
                <div class="container px-5">
                    <div class="row gx-5 align-items-center justify-content-center">
                        <div class="col-lg-8 col-xl-7 col-xxl-6">
                            <div class="my-5 text-center text-xl-start">
                            
                                <h1 class="display-5 fw-bolder text-white mb-2">
									<a href="/front/tourDetail?tour_seq=${tourInfo.tour_seq}" class="text-decoration-none link-light">${tourInfo.tour_post_sj}</a>
								</h1>
                                <p class="lead fw-normal text-white-50 mb-4">${tourInfo.tour_new_address}</p>
                            
                            </div>
                        </div>
                        <div class="col-xl-5 col-xxl-6 d-none d-xl-block text-center"><img class="img-fluid rounded-3 my-5" src="${tourInfo.image_url }" alt="..." /></div>
                    </div>
                </div>
            </header>
            <div class="py-5 bg-light">
                <div class="container px-5 my-5">
                    <div class="row gx-5 justify-content-center">
                        <div class="col-lg-10 col-xl-7">
                            <div class="text-center">
                                <div class="fs-4 mb-4 fst-italic">      </div>
                                <div class="d-flex align-items-center justify-content-center">
                                    <img class="rounded-circle me-3" src="/resources/img/member.jpg" alt="..." width = "70px" height = "70px" />
                                    <div class="fw-bold">
                                    	<c:choose>
                                    		<c:when test="${not empty memberInfo }">
                                    			<h1>${memberInfo.mem_name}
                                    			<span class="fw-bold text-primary mx-1"></span>
		                                        님 환영합니다.</h1>
                                    		</c:when>
                                    		<c:otherwise>
                                    			<button id = "regist" onclick="javascript:goRegist(); return false;">회원가입</button>
                                    			<button id = "login" onclick="javascript:goLogin(); return false;">로그인</button>
                                    		</c:otherwise>
                                    	</c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
                         <c:forEach var="item" items="${list}">
	                    	<div class="col-lg-4 mb-5">
	                            <div class="card h-100 shadow border-0">
	                                <img class="card-img-top" src="${item.image_url }" alt="..." />
	                                <div class="card-body p-4">
	                                    <div class="badge bg-primary bg-gradient rounded-pill mb-2 d-inline-block text-truncate" style="max-width: 150px;">
	                                    	<a class="text-decoration-none link-light" href="${item.tour_cmmn_hmpg_url}" target="_blank">${item.tour_cmmn_hmpg_url}</a>  
	                                    </div>
	                                    <h5 class="card-title mb-3"><a href="/front/tourDetail?tour_seq=${item.tour_seq}" class="text-decoration-none link-dark">${item.tour_post_sj}</a> </h5>
	                                    <p class="card-text mb-0">${item.tour_new_address}</p>
	                                </div>
	                            </div>
	                        </div>
                    	</c:forEach>
                	</div>
                </div>
            </section>
            
        </main>
        <jsp:include page="../frontcommon/front_footer.jsp"/>
		<jsp:include page="../frontcommon/front_footer_common.jsp"/>
    </body>
</html>
