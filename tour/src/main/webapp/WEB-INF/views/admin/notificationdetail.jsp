<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../admincommon/admin_header_common.jsp" />
<link href="../resources/css/tourdetail.css" rel="stylesheet" type="text/css">
</head>
<script type="text/javascript">
	/* function go_modify(){
		var tour_seq = $("#tour_seq").val();
		window.location.assign("/admin/dataUpdate?tour_seq="+tour_seq);
	}
	function go_delete(){
		var tour_seq = $("#tour_seq").val();
		if(!confirm("삭제 하시겠습니까?")){
			return false;
		}else{
			$.ajax({
				url : "/admin/dataDelete?tour_seq=" + tour_seq,
    			success:function(data){
    				alert("정상적으로 삭제되었습니다.");
    				window.location.assign("/admin/tourList"); 
    			},
    			error:function(data){
    				alert("데이터 삭제에 실패했습니다.");
    			}
			});
		}
	} */
</script>
<body>
	<jsp:include page="../admincommon/admin_header.jsp" />
	<div id="contents">
		<section class="py-5">
                <div class="container px-5">
                    <!-- Contact form-->
                    <div class="bg-light rounded-3 py-5 px-4 px-md-5 mb-5">
                        <div class="text-center mb-5">
                            <h1 class="fw-bolder">공지사항 상세</h1>
                            <p class="lead fw-normal text-muted mb-0"><c:out value="${data.noti_title}"/></p>
                        </div>
                        <div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-6">
                                <div id="contactForm">
                                	<input type="hidden" name="noti_seq" value="${data.noti_seq}"/>
                                    <div class="form-floating mb-3">
                                        <textarea class="form-control" name="title" disabled="disabled"><c:out value="${data.noti_contents}"/></textarea>
                                        <label for="even_name">내용</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" name="adress" type="text" value="${data.noti_reg_date}" disabled="disabled">
                                        <label for="even_adress">등록일</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                     	<c:choose>
                                     		<c:when test="${data.noti_status == 0}">
		                                        <input class="form-control" name="status" type="text" value="비표시" disabled="disabled">
		                                        <label for=noti_status>표시여부</label>
	                                        </c:when>
	                                        <c:otherwise>
		                                        <input class="form-control" name="status" type="text" value="표시" disabled="disabled">
		                                        <label for="noti_status">표시여부</label>
	                                        </c:otherwise>
                                      	</c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <button type="button" onclick="go_modify()">수정</button>
			<button type="button" onclick="go_delete()">삭제</button>
			<button type="button" onclick="history.back()">홈</button>
	</div>
	<jsp:include page="../admincommon/admin_footer.jsp"/>
		<jsp:include page="../admincommon/admin_footer_common.jsp"/>
</body>
</html>