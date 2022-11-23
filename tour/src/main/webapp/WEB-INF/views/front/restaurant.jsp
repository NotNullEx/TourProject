<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
    <jsp:include page="../frontcommon/front_header_common.jsp"/>
    </head>
    <body class="d-flex flex-column">
        <main class="flex-shrink-0">
            <jsp:include page="../frontcommon/front_header.jsp"/>
            <!-- Page Content-->
            <section class="py-5">
                <div class="container px-5">
                    <h1 class="fw-bolder fs-5 mb-4">Company Blog</h1>
                    <div class="card border-0 shadow rounded-3 overflow-hidden">
                        <div class="card-body p-0">
                            <div class="row gx-0">
                                <div class="col-lg-6 col-xl-5 py-lg-5">
                                    <div class="p-4 p-md-5">
                                        <div class="badge bg-primary bg-gradient rounded-pill mb-2">#음식</div>
                                        <div class="h2 fw-bolder">당신의 성향, 취향 분석 완료! 마음에 쏙 들 음식점을 추천해 드릴게요!</div>
                                        <!-- <p></p> -->
                                        <%-- <c:out value="${data[0].res_name}" /> --%>
                                        <a class="stretched-link text-decoration-none" href="#!">
                                            Read more
                                            <i class="bi bi-arrow-right"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-lg-6 col-xl-7"><div class="bg-featured-blog" style="background-image: url('https://previews.123rf.com/images/macrovector/macrovector1604/macrovector160402844/54906048-%EB%8B%A4%EC%96%91%ED%95%9C-%EC%9D%8C%EC%8B%9D-%EB%B2%A1%ED%84%B0-%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8%EC%99%80-%ED%95%A8%EA%BB%98-%EB%8B%A4%EC%96%91%ED%95%9C-%ED%81%AC%EA%B8%B0%EC%9D%98-%EC%83%81%EB%8B%A8-%EB%B3%BC-%ED%94%8C%EB%A0%88%EC%9D%B4%ED%8A%B8-%EC%84%B8%ED%8A%B8.jpg')"></div></div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
<!--             <section class="py-5 bg-light">
                <div class="container px-5">
                    <div class="row gx-5">
                        <div class="col-xl-8">
                            <h2 class="fw-bolder fs-5 mb-4">News</h2>
                            News item
                            <div class="mb-4">
                                <div class="small text-muted">May 12, 2022</div>
                                <a class="link-dark" href="#!"><h3>Start Bootstrap releases Bootstrap 5 updates for templates and themes</h3></a>
                            </div>
                            News item
                            <div class="mb-5">
                                <div class="small text-muted">May 5, 2022</div>
                                <a class="link-dark" href="#!"><h3>Bootstrap 5 has officially landed</h3></a>
                            </div>
                            News item
                            <div class="mb-5">
                                <div class="small text-muted">Apr 21, 2022</div>
                                <a class="link-dark" href="#!"><h3>This is another news article headline, but this one is a little bit longer</h3></a>
                            </div>
                            <div class="text-end mb-5 mb-xl-0">
                                <a class="text-decoration-none" href="#!">
                                    More news
                                    <i class="bi bi-arrow-right"></i>
                                </a>
                            </div>
                        </div>
                        <div class="col-xl-4">
                            <div class="card border-0 h-100">
                                <div class="card-body p-4">
                                    <div class="d-flex h-100 align-items-center justify-content-center">
                                        <div class="text-center">
                                            <div class="h6 fw-bolder">Contact</div>
                                            <p class="text-muted mb-4">
                                                For press inquiries, email us at
                                                <br />
                                                <a href="#!">press@domain.com</a>
                                            </p>
                                            <div class="h6 fw-bolder">Follow us</div>
                                            <a class="fs-5 px-2 link-dark" href="#!"><i class="bi-twitter"></i></a>
                                            <a class="fs-5 px-2 link-dark" href="#!"><i class="bi-facebook"></i></a>
                                            <a class="fs-5 px-2 link-dark" href="#!"><i class="bi-linkedin"></i></a>
                                            <a class="fs-5 px-2 link-dark" href="#!"><i class="bi-youtube"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section> -->
            <!-- Blog preview section-->
            
            
            <section class="py-5">
                <div class="container px-5">
                    <h2 class="fw-bolder fs-5 mb-4">Featured Stories</h2>
                    <div class="row gx-5">
                        <div class="col-lg-4 mb-5">
                            <div class="card h-100 shadow border-0">
                                <img class="card-img-top" src="https://ukcooyocdlvo8099722.cdn.ntruss.com/public_data/menu_images/1813278_1638844743_menu.png" alt="..." />
                                <div class="card-body p-4">
                                    <div class="badge bg-primary bg-gradient rounded-pill mb-2"><c:out value="${data[0].res_name}" /></div>
                                    <a class="text-decoration-none link-dark stretched-link" href="#!"><div class="h5 card-title mb-3">서울특별시 관악구 신림동</div></a>
                                    <p class="card-text mb-0"></p>
                                </div>
                                <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
                                    <div class="d-flex align-items-end justify-content-between">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 mb-5">
                            <div class="card h-100 shadow border-0">
                                <img class="card-img-top" src="https://ukcooyocdlvo8099722.cdn.ntruss.com/public_data/menu_images/1417244_1638844842_menu.png" width:"100%"; alt="..." />
                                <div class="card-body p-4">
                                    <div class="badge bg-primary bg-gradient rounded-pill mb-2"><c:out value="${data[1].res_name}" /></div>
                                    <a class="text-decoration-none link-dark stretched-link" href="#!"><div class="h5 card-title mb-3">서울특별시 서대문구 신촌동</div></a>
                                    <p class="card-text mb-0"></p>
                                </div>
                                <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
                                    <div class="d-flex align-items-end justify-content-between">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 mb-5">
                            <div class="card h-100 shadow border-0">
                                <img class="card-img-top" src="https://ukcooyocdlvo8099722.cdn.ntruss.com/public_data/menu_images/2291640_1638845477_menu.jpg" alt="..." />
                                <div class="card-body p-4">
                                    <div class="badge bg-primary bg-gradient rounded-pill mb-2"><c:out value="${data[2].res_name}" /></div>
                                    <a class="text-decoration-none link-dark stretched-link" href="#!"><div class="h5 card-title mb-3">The last blog post title is a little bit longer than the others</div></a>
                                    <p class="card-text mb-0">Some more quick example text to build on the card title and make up the bulk of the card's content.</p>
                                </div>
                                <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
                                    <div class="d-flex align-items-end justify-content-between">
                                        <div class="d-flex align-items-center">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="text-end mb-5 mb-xl-0">
                        <a class="text-decoration-none" href="#!">
                            More stories
                            <i class="bi bi-arrow-right"></i>
                        </a>
                    </div>
                </div>
            </section>
        </main>
        <jsp:include page="../frontcommon/front_footer.jsp"/>
		<jsp:include page="../frontcommon/front_footer_common.jsp"/>
    </body>
</html>
