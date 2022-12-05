<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../frontcommon/front_header_common.jsp" />
	<script type="text/javascript">
	
		var selectedRestaurantGu = function(page) {
			
			var optVal = $("#guselect option:selected").val();
			
			if (optVal == "ALL") {
				location.href = "/front/restaurant";
			}
			
			$.ajax({
				type : "GET",
				url : "/front/selected/restaurant",
				data : {
					"optVal" : optVal,
					"page" : page
				},
				async : false,
				success : function(data) {
					if (data.code == 200) {
						var list = data.obj.data;
						var html = "";
						$("#restaurantText").text(optVal+"음식점 리스트");
						$("#restaurant").empty();
						$(".pagination").empty();
						for (var item of list) {
							html += "<div class=\"col-lg-4 mb-5\">";
							html += "	<div class=\"card h-100 shadow border-0\">";
							if (!stringjs.isnull(item.res_image)) {
								html += "	<img class=\"card-img-top\" src=\""+item.res_image+"\" alt=\"...\">";
							} else {
								html += "	<img class=\"card-img-top\" src=\"/resources/img/noimage.png;\" alt=\"...\">";
							}
							
							html += "		<div class=\"card-body p-4\">";
							html += "			<div class=\"badge bg-primary bg-gradient rounded-pill mb-2\">";
							html += item.res_name + "</div>";
							html += "	<div class=\"h5 card-title mb-3\">";
							html += "		<a class=\"text-decoration-none link-dark stretched-link\">"+item.res_adress+"</a>";
							html += "	</div>";
							html += "	<p class=\"card-text mb-0\"></p>";
							html += "</div>";
							html += "<div class=\"card-footer p-4 pt-0 bg-transparent border-top-0\">";
							html += "	<div class=\"d-flex align-items-end justify-content-between\">";
							html += "	</div>";
							html += "</div>";
							html += "</div>";
							html += "</div>";
						}
						$("#restaurant").html(html);
						
						pagejs.setPaging(data.obj,"selectedRestaurantGu");
					}
				}
			});
		}
	</script>
</head>
<body class="d-flex flex-column">
	<main class="flex-shrink-0">
		<jsp:include page="../frontcommon/front_header.jsp" />

		<section class="py-5">
			<div class="container px-5">
				<h2 class="fw-bolder fs-5 mb-4" id = "restaurantText">음식점 전체 리스트</h2>
				<select id = "guselect" onchange="javascript:selectedRestaurantGu(1)">
					<option value = "ALL" selected>ALL</option>
					<c:forEach var = "item" items="${area }" varStatus="i">
						<option value = "${item.gu_name }">${item.gu_name }</option>
					</c:forEach>
				</select>
				<div class="row gx-5" id = "restaurant">
					<c:forEach var="item" items="${data}">
						<div class="col-lg-4 mb-5">
							<div class="card h-100 shadow border-0">
								<img class="card-img-top" src="${item.res_image }" alt="..." onerror="this.src='/resources/img/noimage.png';" />
								<div class="card-body p-4">
									<div class="badge bg-primary bg-gradient rounded-pill mb-2">
										<c:out value="${item.res_name}" />
									</div>
									<div class="h5 card-title mb-3">
										<a class="text-decoration-none link-dark stretched-link"
											href="#!">${item.res_adress }</a>
									</div>
									<p class="card-text mb-0"></p>
								</div>
								<div class="card-footer p-4 pt-0 bg-transparent border-top-0">
									<div class="d-flex align-items-end justify-content-between">
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<ul class="pagination justify-content-center">
				<c:if test="${pageMaker.prev }">
					<li class="page-item"><a class="page-link"
						href='<c:url value="/front/restaurant?page=${pageMaker.startPage-1 }"/>'>prev</a></li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage }"
					end="${pageMaker.endPage }" var="pageNum" varStatus="status">
					<c:choose>
						<c:when test="${pageMaker.cri.page == pageNum}">
							<li class="page-item active" aria-current="page"><a
								class="page-link"
								href='<c:url value="/front/restaurant?page=${pageNum }"/>'>${pageNum }</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item "><a class="page-link"
								href='<c:url value="/front/restaurant?page=${pageNum }"/>'>${pageNum }</a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage >0 }">
					<li class="page-item"><a class="page-link"
						href='<c:url value="/front/restaurant?page=${pageMaker.endPage+1 }"/>'>next</a></li>
				</c:if>
			</ul>
		</section>
	</main>
	<jsp:include page="../frontcommon/front_footer.jsp" />
	<jsp:include page="../frontcommon/front_footer_common.jsp" />
</body>
</html>
