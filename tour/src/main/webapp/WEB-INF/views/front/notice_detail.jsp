<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../frontcommon/front_header_common.jsp" />
<title>공지사항</title>
</head>
<style type="text/css">
	.btn_list {
		margin: 2%;
		width: 10%;
		background: #EBEDF3;
		border-radius: 12px;
	}
	.item {
		margin : 2% 10%;
		margin-bottom: 0;
		height : 90%;
		background-color: #EBEDF3;
	}
	#board_title {
		text-align: center;
		padding-top: 1%;
	}
	
	#board_reg_date {
		text-align: right;
		padding-right: 2%;
	}
	
	#board_contents {
		padding-left: 1%;
	}
	
</style>
<script type="text/javascript"></script>
<body>
	<jsp:include page="../frontcommon/front_header.jsp" />
	
		<article class="item">
			<header class="mb-4">
				<h1 class="fw-bolder mb-1" id="board_title" type="text" readonly="readonly">
				<label for="even_codename">${noti.noti_title }</label>
				</h1>	
				<div class="text-muted fst-italic mb-2" id="board_reg_date" type="text" readonly="readonly"> 
				<label for="even_guname">${noti.noti_reg_date }</label></div>
				<hr>
			</header>
			
			<section class="mb-5">
				<p class="fs-5 mb-4" id="board_contents" type="text" readonly="readonly"> 
				<label for="even_guname">${noti.noti_contents }</label></p>
			</section>
			
		</article>
		
		<div class="d-grid">
			<button type="button" class="btn_list" onclick="javascript:history.back(-1);"> 목록으로 </button>
		</div>
		
	<jsp:include page="../frontcommon/front_footer.jsp"/>
	<jsp:include page="../frontcommon/front_footer_common.jsp"/>
</body>
</html>