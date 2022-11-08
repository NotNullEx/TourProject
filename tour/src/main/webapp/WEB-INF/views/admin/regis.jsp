<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../admincommon/admin_header_common.jsp"/>
<title>데이터 등록</title>
</head>
<script type="text/javascript">
	function resetRestaurant() {
		if(confirm("정말 삭제하시겠습니까??")) {
			$.ajax({
				url : "/admin/deleteRestaurant",
				success : function(data) {
					console.log(data);
					if(data > 0) {
						alert("DB 삭제가 완료되었습니다."); 
					}else {
						alert("DB 삭제에 실패했습니다.");
					}
				}
			});
		}
	}
	
	function eventDataInsert(code) {
		if(code != null) {
			alert("이미 데이터가 존재합니다.")
		}else {
			$.ajax({
				url : "/admin/eventDataInsert",
				success : function(data) {
					console.log(data);
					if(data != null || data == ' ') {
						alert("이벤트 api 등록이 완료되었습니다."); 
					}else {
						alert("이벤트 api 등록에 실패했습니다.");
					}
				}
			});
		}
	}
</script>
<body>
<jsp:include page="../admincommon/admin_header.jsp"/>
<section class="py-5">

	<div class="row gx-5">
		<div class="col-lg-4 mb-5">
			 <div class="card h-100 shadow border-0">
					<button type="button" id="resetRes" class="w-100 btn btn-primary mb-2" onclick="resetRestaurant()">음식점 데이터 초기화</button>
					<button type="button" id="addRes" class="w-100 btn btn-primary mb-2" onclick="location.href='/admin/addrestaurant'">음식점 Admin 데이터 추가</button>
			</div>
		</div>
		
		<div class="col-lg-4 mb-5">
			<div class="card h-100 shadow border-0">
				<button type="button" id="addRes" class="w-100 btn btn-primary mb-2" onclick="location.href='/admin/adminDataInsert'">Tour 데이터 추가</button>
			</div>
		</div>
		
		<div class="col-lg-4 mb-5">
			<div class="card h-100 shadow border-0">
				<button type="button" id="resetRes" class="w-100 btn btn-primary mb-2" onclick="#">Event 데이터 초기화</button>
				<button type="button" id="addRes" class="w-100 btn btn-primary mb-2" onclick="eventDataInsert(${eventData[3001].even_code})">Event API 데이터 추가</button>
				<button type="button" id="addRes" class="w-100 btn btn-primary mb-2" onclick="#">Event Admin 데이터 추가</button>
			</div>
		</div>
		
	</div>
</section>
</body>
</html>