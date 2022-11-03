<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../admincommon/admin_header_common.jsp" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../admincommon/admin_header.jsp" />
	<div class="inr">
		<ul>
			<li><strong>tour_post_sn</strong><span>  ${data[0].tour_post_sn}</span></li>
			<li><strong>tour_address</strong><span>  ${data[0].tour_address}</span></li>
			<li><strong>res_telnum</strong><span>  ${data[0].res_telnum}</span></li>
			<li><strong>res_adress_area</strong><span>  ${data[0].res_adress_area}</span></li>
			<li><strong>res_adress</strong><span>  ${data[0].res_adress}</span></li>
			<li><strong>res_open_time</strong><span>  ${data[0].res_open_time}</span></li>
			<li><strong>res_rest_day</strong><span>  ${data[0].res_rest_day}</span></li>
			<li><strong>res_image</strong><span>  ${data[0].res_image}</span></li>
			<li><strong>res_kind</strong><span>  ${data[0].res_kind}</span></li>
			<li><strong>res_desc</strong><span>  ${data[0].res_desc}</span></li>
			<li><strong>res_name</strong><span>  ${data[0].res_name}</span></li>
			<li><strong>res_upd_date</strong><span>  ${data[0].res_upd_date}</span></li>
		</ul>
	</div>
	<div class="col-lg-4 mb-5">
			 <div class="card h-100 shadow border-0">
					<button type="button" id="resetRes" class="w-100 btn btn-primary mb-2" onclick="#">수정하기</button>
					<button type="button" id="addRes" class="w-100 btn btn-primary mb-2" onclick="#">삭제하기</button>
			</div>
		</div>
</body>
</html>