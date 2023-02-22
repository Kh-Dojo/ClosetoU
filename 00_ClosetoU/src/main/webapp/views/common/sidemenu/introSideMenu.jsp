<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />

<div id="main_menu_name_area">
	<%-- 헤더에 저장된 페이지 정보 중 어떤 메인메뉴를 클릭했나 가져옴 --%>
	<h1 id="mainmenu">자유로운 수다방</h1>
</div>
<div id="sub_menu_name_area">
	<h3 class="submenu"><a href="#">서브메뉴링크</a></h3>	
</div>