<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../admincommon/admin_header_common.jsp" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function deleteOne(code) {
		if(confirm("정말 삭제하시겠습니까??")) {
			$.ajax({
				type : "POST",
				data : {
					"code" : code
				},
				url : "/admin/deleteOneRestaurant",
				async : false,
				success : function(data) {
					console.log(data);
					if(data > 0) {
						alert("삭제가 완료되었습니다.");
						window.location.assign("/admin/restaurant"); 
					}else {
						alert("삭제에 실패했습니다.");
					}
				}
			});
		}
	}
</script>
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
			<li><strong>res_kind</strong><span>  ${data[0].res_kind}</span></li>
			<li><strong>res_desc</strong><span>  ${data[0].res_desc}</span></li>
			<li><strong>res_name</strong><span>  ${data[0].res_name}</span></li>
			<li><strong>res_upd_date</strong><span>  ${data[0].res_upd_date}</span></li>
		</ul>
	</div>
	<div class="col-lg-4 mb-5">
			 <div class="card card-body">
					<button type="button" id="resetRes" class="btn btn-primary mb-2" onclick="location.href='/admin/reviseAll?res_code=${data[0].res_code}'">수정하기</button>
					<button type="button" id="addRes" class="btn btn-primary mb-2" onclick="deleteOne(${data[0].res_code})">삭제하기</button>
					<button type="button" id="addRes" class="btn btn-primary mb-2" onclick="location.href='/admin/restaurant'">목록</button>
			</div>
		</div>
</body>
</html>