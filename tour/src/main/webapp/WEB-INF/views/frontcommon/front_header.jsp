
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	table {
		text-align : center;
	}
</style>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container px-5">
		<a class="navbar-brand" href="/"><img
			src="https://www.hygn.go.kr/portal/sanglim/img/sub/03643.jpg"
			style="width: 150px; height: 60px; object-fit: cover;" /></a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link" href="/">홈</a></li>
				<li class="nav-item"><a class="nav-link" href="/front/tourList">관광지</a></li>
				<li class="nav-item"><a class="nav-link" href="/front/event">행사</a></li>
				<li class="nav-item"><a class="nav-link" href="/front/restaurant">먹거리</a></li>
				<li class="nav-item"><a class="nav-link" href="/front/notice/list">공지사항</a></li>
				<li class="nav-item"><a class="nav-link" href="/front/board">게시판</a></li>
				
				<c:if test="${empty sessionScope.MEMBER_ID}">

					<li class="nav-item"><a class="nav-link" href="#"
						onclick="location='/front/login'">로그인</a></li>
				</c:if>
				<c:if test="${not empty sessionScope.MEMBER_ID}">
					<!-- <li class="nav-item"><a class="nav-link" href="#" onclick="data_insert()">데이터 등록</a></li> -->
					<li class="nav-item" id="signOut"><a class="nav-link"
						href="/front/logOut" class="nav-link text-dark">로그아웃</a></li>
					<li class="nav-item"><a class="nav-link" href="/front/myPage">마이페이지</a></li> 
				</c:if>
			</ul>
		</div>
	</div>
</nav>