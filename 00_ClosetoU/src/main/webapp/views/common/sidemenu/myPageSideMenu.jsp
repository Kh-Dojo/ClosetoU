<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />

<div id="main_menu_name_area">
	<%-- 헤더에 저장된 페이지 정보 중 어떤 메인메뉴를 클릭했나 가져옴 --%>
	<h1 id="mainmenu">마이페이지</h1>
</div>
<div id="sub_menu_name_area">
	<h3 class="submenu">
		<a href="${ path }/views/member/myPage.jsp">내 정보 수정</a>
	</h3>
	<h3 class="submenu">
		<a href="${ path }/views/member/myTrade.jsp">나의 거래 내역</a>
	</h3>
	<h3 class="submenu">
		<a href="${ path }/views/member/myArticle.jsp">나의 게시글</a>
	</h3>
	<h3 class="submenu">
		<a href="${ path }/views/member/myComment.jsp">나의 댓글</a>
	</h3>
	<h3 class="submenu">
		<a href="${ path }/views/member/myAsk.jsp">1:1 문의 내역</a>
	</h3>
</div>