<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
function check_account() {
	var id = document.getElementById('id').value;
	var pw = document.getElementById('password').value;
	if (id == "") {
		alert("아이디를 입력해주세요.");
		return false;
	}
	if (pw == "") {
		alert("패스워드를 입력해주세요.");
		return false;
	}
	$.ajax({
		type : "POST",
		url : "/admin/loginOk",
		data : {
			"id" : id,
			"password" : pw
		},
		async : false,
		success : function(data) {
			if (data.result == 1) {
				console.log(id);
				alert("정상적으로 로그인되었습니다.");
				window.location.assign("/front/blog"); 
			} else {
				alert("아이디, 패스워드를 확인해주세요.");
			}
		}
	});
}

</script>
<body>
<div class="container px-0 py-5 text-center d-flex justify-content-center align-items-top h-100">
    <div class="bg-white col-sm-8 pt-5">
        <div class="d-flex justify-content-center text-center shadow-lg py-5">
			<form class="">
			  <div class="mb-3">
			    <label for="id" class="form-label float-start">ID</label>
			    <input type="text" class="form-control" id="id" name="id"aria-describedby="emailHelp">
			  </div>
			  <div class="mb-5">
			    <label for="pwd" class="float-start form-label">Password</label>
			    <input type="password" class="form-control" id="password" name="password">
			  </div>
			  <button type="button" id="signIn" class="w-100 btn btn-primary mb-2" onclick="check_account()">로그인</button>		  
			</form>
        </div>
    </div>
</div>
</body>


</html>