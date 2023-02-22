<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div id="main_menu_name_area">
	<%-- 헤더에 저장된 페이지 정보 중 어떤 메인메뉴를 클릭했나 가져옴 --%>
	<h1 id="mainmenu">메인메뉴 출력 ${ sideMenu.mainMenuName }</h1>
</div>
<div id="sub_menu_name_area">
	<c:forEach var="submenu" items = "${ sideMenu.subMenuName }">
		<div id="submenu">${ submenu }</div>	
	</c:forEach>

	
</div>