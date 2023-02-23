<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />

	<jsp:include page="/views/common/main-header.jsp" />
	<article>
		<div id="welcome">
			<img src="${ path }/resources/img/main.gif">
		</div>
		<div></div>
		<div></div>
	</article> 
	<jsp:include page="/views/common/footer.jsp" />
