<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function insUrl(){
		var url = $("#url").val();
		var tour_seq = $("#tour_seq").val();
		$.ajax({
			type : "POST",
			url : "/admin/insUrl",
			data : {
				"url" : url,
				"tour_seq" : tour_seq
			},
			success:function(data){
				if(data ==1){
					alert("이미지가 정상적으로 등록되었습니다.");
					window.location.assign("/admin/tourList"); 
				}else {
					alert("이미지가 이미 존재합니다");
					window.location.assign("/admin/tourList");
				}
				
			},
			error:function(data){
				alert("이미지 등록에 실패했습니다.");
			}
		});
	}
</script>
<jsp:include page="../admincommon/admin_header_common.jsp" />
<body>
	<jsp:include page="../admincommon/admin_header.jsp" />
	<section class="py-5">
		
                <div class="container px-5">
                    <!-- Contact form-->
                    <div class="bg-light rounded-3 py-5 px-4 px-md-5 mb-5">
                        <div class="text-center mb-5">
                            <h1 class="fw-bolder">이미지 URL 등록</h1>
                        </div>
                        <div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-6">
                                <div id="contactForm">
                                	<input type="hidden" id="tour_seq" value="${tour_seq}"/>
                                    <div class="form-floating mb-3">
		                            	<input class="form-control" name="status" type="text" value="${tour_post_sj}" disabled="disabled" >
		                                <label for=noti_status>관광지 명</label>
                                    </div>
                                    <div class="form-floating mb-3">
		                            	<input class="form-control" id="url" type="text">
		                                <label for=noti_status>URL을 입력해주세요 ../resources/img/</label>
                                    </div>
                                    <div >
		                            	<button type="button" id="regis" onclick="insUrl()">등록</button>
		                            	<button type="button" id="regis" onclick="history.back()">취소</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
</body>
</html>