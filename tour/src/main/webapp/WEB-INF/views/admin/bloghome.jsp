<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../admincommon/admin_header_common.jsp" />
</head>



<style>



	.page_banner{
 width:1280px;
 margin:0 auto;
 margin-bottom: 25px;
 background-color: #e6e6e6;
 overflow: hidden;
 position: relative;
}
.mask{
 width:1400px;
}
.banner_cont{
 border:1px solid #d7d7d7;
 color: #383838;
 font-size: 20px;
 padding:10px 14px;
 background-color: #ebebeb;
 display: table-cell;
 vertical-align: middle;
    margin-right: 10px;
    margin-bottom: 10px;

}
.banner_btn{
 position: absolute;
 text-align: center;
 font-size:0;
 height:50px;
 max-height:50px;
 max-width:50px;
 display: flex;
 justify-content: center;
 vertical-align:middle;
 margin-top:120px;
}
.banner_btn:after{display:inline-block; height:100%; content:""; vertical-align:middle;}
.banner_btn img{vertical-align:middle; object-fit: cover;}
.banner_btn1{
 left: 0;
 vertical-align:middle;
}
.banner_btn2{
 right: 0;
 vertical-align:middle;
}
	



</style>



<script type="text/javascript">



var divWidth  = "100"; //배너 1개 가로크기
//이전
/* function prev()
{
 $("#content").stop(true,true);
 var moveX   = parseInt($("#content").css("margin-left"));
 if( moveX < 0 )
 {
  $("#content").animate({"margin-left":"+=" + divWidth + "px"},500);
 }
 else
 {
 return;
 }
}
//다음
function next()
{
 $("#content").stop(true,true);
 var hiddenWidth  = ($("#content dd").length-1)*(-divWidth);
 var moveX   = parseInt($("#content").css("margin-left")) ;
 console.log(moveX);
 var lastWidth  = ( moveX - divWidth );

 if( hiddenWidth < moveX )
 {
  $("#content").animate({"margin-left":"-=" + divWidth + "px"},500);
 }
 else
 {
 return;
 }
} */


</script>





            
            
            
            













<body class="d-flex flex-column">
	<main class="flex-shrink-0">
		<jsp:include page="../admincommon/admin_header.jsp" />
		<!-- Page Content-->
		
		<section class="py-5">
			<div class="container px-5">
				<h1 class="fw-bolder fs-5 mb-4">Company Blog</h1>
				<div class="card border-0 shadow rounded-3 overflow-hidden">
					<div class="card-body p-0">
					
    <div class=" banner_btn banner_btn1" id="prevbtn" onClick="prev()"><input type="image" src="/resources/img/banner_arrow_l.png"></div>
     <div id="content">
      <div class="f_left">		
						<div class="row gx-0">
							<div class="col-lg-6 col-xl-5 py-lg-5">
								<div class="p-4 p-md-5">
									<div class="badge bg-primary bg-gradient rounded-pill mb-2">
										<c:out value="${data[0].res_name}" />
									</div>
									<div class="h2 fw-bolder">
										<c:out value="${data[0].res_adress}" />
									</div>
									<p>
										<c:out value="${data[0].res_desc}" />
									</p>
								</div>
							</div>
									<!-- <i class="bi bi-arrow-right"></i> -->
									<div class="col-lg-6 col-xl-7">
										<div class="bg-featured-blog"
											style="background-image: url(/resources/img/${data[0].res_image})"></div>
									</div>
								</div>
      </div>
     </div>
    </div>
    <div class=" banner_btn banner_btn2" id="nextbtn" onClick="next()"><input type="image" src="/resources/img/banner_arrow_r.png"></div>
								
								
							</div>
						</div>
			
		</section>
		<!-- Blog preview section-->
		<section class="py-5">
			<div class="container px-5">
				<h2 class="fw-bolder fs-5 mb-4">Featured Stories</h2>
				<div class="row gx-5">
					<c:forEach var="data" items="${data}" begin="1" end="3">
						<div class="col-lg-4 mb-5">
							<div class="card h-100 shadow border-0">
								<img class="card-img-top" src="/resources/img/${data.res_image}" alt="..." />
								<div class="card-body p-4">
									<div class="badge bg-primary bg-gradient rounded-pill mb-2">
										<c:out value="${data.res_name}" />
									</div>
									<a class="text-decoration-none link-dark stretched-link"
										href="#!"><div class="h5 card-title mb-3">
											<c:out value="${data.res_adress}" />
										</div></a>
									<p class="card-text mb-0">
										<c:out value="${data.res_desc}" />
									</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="text-end mb-5 mb-xl-0">
				<a class="text-decoration-none" href="#!"> More stories <i
					class="bi bi-arrow-right"></i>
				</a>
			</div>
		</section>
	</main>
	<jsp:include page="../admincommon/admin_footer.jsp" />
	<jsp:include page="../admincommon/admin_footer_common.jsp" />
</body>
</html>