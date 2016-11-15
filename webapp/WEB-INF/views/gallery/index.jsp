<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/lightbox.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	var dialog;
	$(function() {
		dialog = $("#dialog-form").dialog({
			autoOpen : false,
			height : 300,
			width : 250,
			modal : true,
			buttons : {
				"upload" : function() {
					$("#dialog-form form").submit();
				},
				Cancel : function() {
					dialog.dialog("close");
				}
			},
			close : function() {
			}
		});
		$("#upload-image").click(function(event) {
			//event.preeventDefault();
			dialog.dialog("open");
		});
		$("#upload-image1").click(function(event) {
			//event.preeventDefault();
			window.location.href ="${pageContext.request.contextPath }/user/loginform";
		});
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="gallery">

				<div>
					<h1>갤러리</h1>
					<c:choose>
						<c:when test="${authUser eq null}">
							<span id="upload-image1">이미지 올리기</span>
						</c:when>
						<c:otherwise>
							<span id="upload-image">이미지 올리기</span>
						</c:otherwise>
					</c:choose>

				</div>

				<ul>
					<c:forEach items="${list }" var="vo">
						<li><a class="image"
							href="${pageContext.request.contextPath }/gallery/assets/${vo.saveFileName}"
							data-lightbox="roadtrip" data-title="${vo.comments }"
							style="background-image:url('${pageContext.request.contextPath }/gallery/assets/${vo.saveFileName}')">${vo.saveFileName}</a>
							<c:if test="${authUser.no == vo.userNo}">
								<a
									href="${pageContext.request.contextPath }/gallery/delete?no=${vo.no}"
									class="del-button" title="삭제">삭제</a>
							</c:if></li>

					</c:forEach>
				</ul>
			</div>
		</div>
		<div id="dialog-form" title="upload form" style="display: none">
			<form method="post"
				action="${pageContext.request.contextPath }/gallery/upload"
				enctype="multipart/form-data">
				<input type="file" name="file"> <br> comments:<input
					type="text" name="comments">
			</form>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="gallery" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
	<script
		src="${pageContext.request.contextPath }/assets/js/lightbox/lightbox.js"></script>
</body>
</html>