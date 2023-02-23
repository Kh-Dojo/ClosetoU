<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />

<jsp:include page="/views/common/sub-header.jsp" />
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
<link rel="stylesheet" href="${ path }/resources/css/intro.css">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section id="content">
	<div id="sidemenu"><jsp:include page="/views/common/sidemenu/introSideMenu.jsp" /></div>
	<div id="view-container">
		<img src="${ path }/resources/img/donationIntro.png">
	</div>
</section>
</body>
</html>