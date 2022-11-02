<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../admincommon/admin_header_common.jsp"/>
<title>음식점 데이터 추가</title>
</head>
<body>
<jsp:include page="../admincommon/admin_header.jsp"/>

								<form id="contactForm" method="post">
                                    <div class="form-floating mb-3">
                                        <input class="form-control" name="tour_ps" type="text">
                                        <label for="even_code">tour_post_sn</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" name="tour_address" type="text">
                                        <label for="even_name">tour_address</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" name="telnum" type="text">
                                        <label for="even_adress">전화번호</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" name="adress_area" type="text">
                                        <label for="even_desc">주소(구)</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" name="adress" type="text">
                                        <label for="even_name">전체 주소</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" name="opentime" type="text">
                                        <label for="even_name">여는시간</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" name="rest_day" type="text">
                                        <label for="even_name">쉬는 날(null)</label>
                                    </div>
                                    <!-- <div class="form-floating mb-3">
                                        <input class="form-control" name="image" type="text">
                                    </div> -->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" name="desc" type="text">
                                        <label for="even_name">설명</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" name="name" type="text">
                                        <label for="even_name">이름</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        한식 <input name="kind" type="radio" value="0" checked="checked">
                                        중식 <input name="kind" type="radio" value="1">
                                        일식 <input name="kind" type="radio" value="2">
                                        양식 <input name="kind" type="radio" value="3">
                                    </div>
                                    <div class="d-grid"><input type="submit" value="음식점 DB 추가"></div>
                                </form>
</body>
</html>