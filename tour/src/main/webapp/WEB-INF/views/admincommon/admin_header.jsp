 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container px-5">
                    <a class="navbar-brand" href="/admin"><img src="https://www.hygn.go.kr/portal/sanglim/img/sub/03643.jpg" style="width: 150px; height: 60px; object-fit: cover;"/></a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                            <li class="nav-item"><a class="nav-link" href="/admin">Home</a></li>
                            <li class="nav-item"><a class="nav-link" href="/admin/about">About</a></li>
                            <li class="nav-item"><a class="nav-link" href="/admin/contact">Contact</a></li>
                            <li class="nav-item"><a class="nav-link" href="/admin/pricing">Pricing</a></li>
                            <li class="nav-item"><a class="nav-link" href="/admin/faq">FAQ</a></li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" id="navbarDropdownBlog" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Blog</a>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownBlog">
                                    <li><a class="dropdown-item" href="/admin/blog">Blog Home</a></li>
                                    <li><a class="dropdown-item" href="/admin/blogPost">Blog Post</a></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" id="navbarDropdownPortfolio" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Portfolio</a>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownPortfolio">
                                    <li><a class="dropdown-item" href="/admin/portfolioOverview">Portfolio Overview</a></li>
                                    <li><a class="dropdown-item" href="/admin/portfolioitem">Portfolio Item</a></li>
                                </ul>
                            </li>
                            <li class="nav-item"><a class="nav-link" href="/admin/regis">데이터 등록</a></li>
                            <c:if test="${empty sessionScope.ADMIN_ID}">            
					        	<li class="nav-item"><a class="nav-link" href="#" onclick="location='/admin/login'">로그인</a></li>
					       </c:if>
                            <c:if test="${not empty sessionScope.ADMIN_ID}">            
					        	<li class="nav-item" id="signOut"><a class="nav-link" href="/admin/logOut" class="nav-link text-dark">로그아웃</a></li>
					       </c:if>
                        </ul>
                    </div>
                </div>
            </nav>