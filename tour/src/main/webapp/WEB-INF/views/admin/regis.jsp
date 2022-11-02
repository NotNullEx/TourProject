<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../admincommon/admin_header_common.jsp"/>
<title>관광지 데이터 등록</title>
</head>
<script type="text/javascript">
	function resetRestaurant() {
		$.ajax({
			url : "/admin/loginOk",
			
		});
		
	}
</script>
<body>
<jsp:include page="../admincommon/admin_header.jsp"/>

<div class="col-lg-4 mb-5">
<form>
<button type="button" id="resetRes" class="w-100 btn btn-primary mb-2" onclick="resetRestaurant()">음식점 데이터 초기화</button>
<button type="button" id="addRes" class="w-100 btn btn-primary mb-2" onclick="location.href='/admin/addrestaurant'">음식점 데이터 추가</button>
</form>
</div>
</body>
</html>