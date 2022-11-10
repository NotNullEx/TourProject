<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function account() {
		var id = document.getElementById('emails').value;
		var pass = document.getElementById('pass').value;
		var passChk = document.getElementById('passChk').value;
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
		if(pass != passChk){
			alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			return false;
		}
		if(passChk == ""){
			alert("비밀번호 확인을 해주세요.");
			return false;
		}
		
		$.ajax({
			type : "POST",
			url : "/front/createMemberOK",
			data : {
				"email" : id,
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
	
	function overlap() {
		var id = document.getElementById('emails').value;
		
		$.ajax({
			type : "POST",
			url : "/front/overlapOK",
			data : {
				"email" : id,
			},
			async : false,
			success : function(data) {
				if (data.result != 1) {
					console.log(id);
					alert("사용 가능한 이메일 입니다.");
				} else {
					alert("사용중인 이메일 입니다.");
				}
			}
		});
	}

</script>
   <body class="d-flex flex-column">
        <main class="flex-shrink-0">
            <jsp:include page="../frontcommon/front_header.jsp"/>
            <!-- Page content-->
            <section class="py-5">
                <div class="container px-5">
                    <!-- Contact form-->
                    <div class="bg-light rounded-3 py-5 px-4 px-md-5 mb-5">
                        <div class="text-center mb-5">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-envelope"></i></div>
                            <h1 class="fw-bolder">회원가입</h1>
                            <!-- <p class="lead fw-normal text-muted mb-0">We'd love to hear from you</p> -->
                        </div>
                        <div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-6">
                                <!-- * * * * * * * * * * * * * * *-->
                                <!-- * * SB Forms Contact Form * *-->
                                <!-- * * * * * * * * * * * * * * *-->
                                <!-- This form is pre-integrated with SB Forms.-->
                                <!-- To make this form functional, sign up at-->
                                <!-- https://startbootstrap.com/solution/contact-forms-->
                                <!-- to get an API token!-->
                                <form id="contactForm" data-sb-form-api-token="API_TOKEN" method="post">
                                    <!-- Name input-->
                                    <div class="form-floating mb-3">
                                    
                                        <input class="form-control" id="names" type="text" placeholder="Enter your name..." data-sb-validations="required" />
                                        <label for="name">이름</label>
                                        <!-- <div class="invalid-feedback" data-sb-feedback="name:required">이름을 입력해 주세요.</div> -->
                                    </div>
                                    <!-- Email address input-->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="emails" type="email" placeholder="name@example.com" data-sb-validations="required,email" />
                                        <label for="email">이메일</label>
                                        <input type="button" value="중복" onclick="overlap()">
                                        <!-- <div class="invalid-feedback" data-sb-feedback="email:required">이메일을 입력해 주세요.</div> -->
                                        <!-- <div class="invalid-feedback" data-sb-feedback="email:email">Email is not valid.</div> -->
                                    </div>
                                    <!-- Phone number input-->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="phone" type="tel" placeholder="(123) 456-7890" data-sb-validations="required" />
                                        <label for="phone">전화번호</label>
                                        <!-- <div class="invalid-feedback" data-sb-feedback="phone:required">전화번호를 입력해 주세요.</div> -->
                                    </div>
                                    <!-- Message input-->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="pass" type="password" data-sb-validations="required" />
                                        <label for="password">비밀번호</label>
                                        <!-- <div class="invalid-feedback" data-sb-feedback="message:required">A message is required.</div> -->
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="passChk" type="password" data-sb-validations="required" />
                                        <label for="password">비밀번호 확인</label>
                                        <!-- <div class="invalid-feedback" data-sb-feedback="message:required">A message is required.</div> -->
                                    </div>
                                    <!-- Submit success message-->
                                    <!---->
                                    <!-- This is what your users will see when the form-->
                                    <!-- has successfully submitted-->
                                    <div class="d-none" id="submitSuccessMessage">
                                        <div class="text-center mb-3">
                                            <div class="fw-bolder">Form submission successful!</div>
                                            To activate this form, sign up at
                                            <br />
                                            <a href="https://startbootstrap.com/solution/contact-forms">https://startbootstrap.com/solution/contact-forms</a>
                                        </div>
                                    </div>
                                    <!-- Submit error message-->
                                    <!---->
                                    <!-- This is what your users will see when there is-->
                                    <!-- an error submitting the form-->
                                    <div class="d-none" id="submitErrorMessage"><div class="text-center text-danger mb-3">Error sending message!</div></div>
                                    <!-- Submit Button-->
                                    <div class="d-grid"><button type="button" id="signIn" class="btn btn-primary btn-lg enabled" onclick="account()">가입</button></div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- Contact cards-->
                    <div class="row gx-5 row-cols-2 row-cols-lg-4 py-5">
                        <div class="col">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-chat-dots"></i></div>
                            <div class="h5 mb-2">Chat with us</div>
                            <p class="text-muted mb-0">Chat live with one of our support specialists.</p>
                        </div>
                        <div class="col">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-people"></i></div>
                            <div class="h5">Ask the community</div>
                            <p class="text-muted mb-0">Explore our community forums and communicate with other users.</p>
                        </div>
                        <div class="col">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-question-circle"></i></div>
                            <div class="h5">Support center</div>
                            <p class="text-muted mb-0">Browse FAQ's and support articles to find solutions.</p>
                        </div>
                        <div class="col">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-telephone"></i></div>
                            <div class="h5">Call us</div>
                            <p class="text-muted mb-0">Call us during normal business hours at (555) 892-9403.</p>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <jsp:include page="../frontcommon/front_footer.jsp"/>
		<jsp:include page="../frontcommon/front_footer_common.jsp"/>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>
