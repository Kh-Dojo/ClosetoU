<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="path" value="${ pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ClosetoU 메인페이지</title>
</head>
<body>
	<jsp:include page="/views/common/header.jsp" />
	<article>article 자리입니다.</article>
	<jsp:include page="/views/common/footer.jsp" />
</body>
</html>