<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../admincommon/admin_header_common.jsp"/>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<script type="text/javascript">
	function account() {
		var id = document.getElementById('emails').value;
		var pass = document.getElementById('pass').value;
		var name = document.getElementById('names').value;
		var phone = document.getElementById('phone').value;
		
		if (id == "") {
			alert("아이디를 입력해주세요.");
			return false;
		}
		if (pass == "") {
			alert("패스워드를 입력해주세요.");
			return false;
		}
		if (name == "") {
			alert("이름을 입력해주세요.");
			return false;
		}
		if (phone == "") {
			alert("전화번호를 입력해주세요.");
			return false;
		}
		
		$.ajax({
			type : "POST",
			url : "/admin/createaccountOK",
			data : {
				"email" : id,
				"password" : pass,
				"name" : name,
				"phone_num" : phone
			},
			async : false,
			success : function(data) {
				if (data.result == 1) {
					console.log(id);
					alert(name+"님의 가입을 축하드립니다!");
					window.location.assign("/"); 
				} else {
					alert("가입불가 공란을 확인해주세요.");
				}
			}
		});
	}

</script>
<body class="d-flex flex-column">
        <main class="flex-shrink-0">
            <jsp:include page="../admincommon/admin_header.jsp"/>
	<form>
		<p>이메일 : <input type="text" name="email" id="emails">
		<p>패스워드 : <input type="password" name="password" id="pass">
		<p>이름 : <input type="text" name="name" id="names">
		<p>전화번호 : <input type="text" name="phone_num" id="phone">
		<p><button type="button" id="signIn" class="w-100 btn btn-primary mb-2" onclick="account()">가입</button>	
	</form>
	 </main>
        <jsp:include page="../admincommon/admin_footer.jsp"/>
		<jsp:include page="../admincommon/admin_footer_common.jsp"/>
</body>
</html>