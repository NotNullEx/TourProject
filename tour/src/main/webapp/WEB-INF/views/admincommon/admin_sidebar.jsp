<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
  /* 사이드바 래퍼 스타일 */
  
  #page-wrapper {
    padding-left: 250px;
  }
  
  #sidebar-wrapper {
    position: fixed;
    width: 250px;
    height: 100%;
    margin-left: -250px;
    background: #000;
    overflow-x: hidden;
    overflow-y: auto;
  }
  
  #page-content-wrapper {
    width: 100%;
    padding: 20px;
  }
  /* 사이드바 스타일 */
  
  .sidebar-nav {
    width: 250px;
    margin: 0;
    padding: 0;
    list-style: none;
  }
  
  .sidebar-nav li {
    text-indent: 1.5em;
    line-height: 2.8em;
  }
  
  .sidebar-nav li a {
    display: block;
    text-decoration: none;
    color: #999;
  }
  
  .sidebar-nav li a:hover {
    color: #fff;
    background: rgba(255, 255, 255, 0.2);
  }
  
  .sidebar-nav > .sidebar-brand {
    font-size: 1.3em;
    line-height: 3em;
  }

</style>
<body>
	<!-- 사이드바 -->
  <div id="sidebar-wrapper">
    <ul class="sidebar-nav">
      <li class="sidebar-brand">
        <a style="color: white">마이페이지</a>
      </li>
      <li><a href="/admin/myInfo">내 정보</a></li>
      <li><a href="/admin/myNotiInfo?admin_seq=${seq}">내가쓴 공지사항</a></li>
      <li><a href="#">메뉴 3</a></li>
      <li><a href="#">메뉴 4</a></li>
      <li><a href="#">메뉴 5</a></li>
      <li><a href="#">메뉴 6</a></li>
      <li><a href="#">메뉴 7</a></li>
      <li><a href="#">메뉴 8</a></li>
      <li><a href="#">메뉴 9</a></li>
    </ul>
  </div>
  <!-- /사이드바 -->
</body>
</html>