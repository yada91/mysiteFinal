<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
</head>
<body>
	<div id="navigation" class="div_class">
		<ul id=navi-ul style="top: 200px">
			<c:choose>
				<c:when test="${param.menu == 'main' }">
					<li class="selected"><a
						href="${pageContext.request.contextPath }/">메인</a></li>
					<li><a href="${pageContext.request.contextPath }/gb">방명록</a></li>
					<li><a href="${pageContext.request.contextPath }/gb/ajax">방명록(AJAX)</a></li>
					<li><a href="${pageContext.request.contextPath }/board">게시판</a></li>
					<li><a href="${pageContext.request.contextPath }/gallery">갤러리</a></li>
				</c:when>
				<c:when test="${param.menu == 'guestbook' }">
					<li><a href="${pageContext.request.contextPath }/">메인</a></li>
					<li class="selected"><a
						href="${pageContext.request.contextPath }/gb">방명록</a></li>
					<li><a href="${pageContext.request.contextPath }/gb/ajax">방명록(AJAX)</a></li>
					<li><a href="${pageContext.request.contextPath }/board">게시판</a></li>
					<li><a href="${pageContext.request.contextPath }/gallery">갤러리</a></li>
				</c:when>
				<c:when test="${param.menu == 'guestbook-ajax' }">
					<li><a href="${pageContext.request.contextPath }/">메인</a></li>
					<li><a href="${pageContext.request.contextPath }/gb">방명록</a></li>
					<li class="selected"><a
						href="${pageContext.request.contextPath }/gb/ajax">방명록(AJAX)</a></li>
					<li><a href="${pageContext.request.contextPath }/board">게시판</a></li>
					<li><a href="${pageContext.request.contextPath }/gallery">갤러리</a></li>
				</c:when>
				<c:when test="${param.menu == 'board' }">
					<li><a href="${pageContext.request.contextPath }/">메인</a></li>
					<li><a href="${pageContext.request.contextPath }/gb">방명록</a></li>
					<li><a href="${pageContext.request.contextPath }/gb/ajax">방명록(AJAX)</a></li>
					<li class="selected"><a
						href="${pageContext.request.contextPath }/board">게시판</a></li>
					<li><a href="${pageContext.request.contextPath }/gallery">갤러리</a></li>
				</c:when>
				<c:when test="${param.menu == 'gallery' }">
					<li><a href="${pageContext.request.contextPath }/">메인</a></li>
					<li><a href="${pageContext.request.contextPath }/gb">방명록</a></li>
					<li><a href="${pageContext.request.contextPath }/gb/ajax">방명록(AJAX)</a></li>
					<li><a href="${pageContext.request.contextPath }/board">게시판</a></li>
					<li class="selected"><a
						href="${pageContext.request.contextPath }/gallery">갤러리</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath }/">메인</a></li>
					<li><a href="${pageContext.request.contextPath }/gb">방명록</a></li>
					<li><a href="${pageContext.request.contextPath }/gb/ajax">방명록(AJAX)</a></li>
					<li><a href="${pageContext.request.contextPath }/board">게시판</a></li>
					<li><a href="${pageContext.request.contextPath }/gallery">갤러리</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</body>
</html>