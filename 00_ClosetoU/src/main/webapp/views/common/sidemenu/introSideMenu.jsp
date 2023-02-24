<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />
<style>
	.submenu a {
		text-decoration: none;
		color : black;
	}
	
	.submenu > a:hover {
		background-color:#FFB960;
		color : white;
	}
</style>

<div id="main_menu_name_area">
	<%-- 헤더에 저장된 페이지 정보 중 어떤 메인메뉴를 클릭했나 가져옴 --%>
	<h1 id="mainmenu">사이트 이용 안내</h1>
</div>
<div id="sub_menu_name_area">
	<h3 class="submenu">
		<a href="${ path }/views/intro/donation_Intro.jsp">기부 방법 안내</a>
	</h3>
	<h3 class="submenu">
		<a href="${ path }/views/intro/use_Intro.jsp">사이트 이용 안내</a>
	</h3>
</div>