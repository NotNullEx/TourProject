<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<head>
  <jsp:include page="../admincommon/admin_header_common.jsp"/>
</head>
<title>관광지 데이터 등록</title>
</head>
<body>
<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img class="d-block w-100" src="https://dummyimage.com/600x400/343a40/6c757d" alt="First slide">
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="https://dummyimage.com/600x400/343a40/6c757d" alt="Second slide">
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="https://dummyimage.com/600x400/343a40/6c757d" alt="Third slide">
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
	<c:forEach var="tas" items="${sb}" begin="0" end="2">
                <div class="carousel-item">
				  <img src="https://dummyimage.com/600x400/343a40/6c757d" alt="...">
				  <div class="carousel-caption d-none d-md-block">
				    <h5>${tas.tour_post_sj}</h5>
				    <p>${tas.tour_new_address}</p>
				  </div>
				</div>
			</c:forEach>
</body>
</html>