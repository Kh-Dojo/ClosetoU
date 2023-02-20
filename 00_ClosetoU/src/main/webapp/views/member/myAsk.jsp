<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>
<link rel="stylesheet" href="${ path }/resources/css/myPage.css">
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>

<jsp:include page="/views/common/header.jsp" />

<style>
	section #view-container {
		text-align:center;
	}
	
	section #view-container input {
		margin:3px;
	}
	
	section #view-container table {
		margin:0 auto;
	}
	
	section #view-container table th {
		padding:0 10px; 
		text-align:right;
	}
	
	section #view-container table td {
		padding:0 10px; 
		text-align:left;
	}
</style>
<section id="content">
	<h2 align="center">1:1 문의 내역</h2>
	<div id="view-container">
 	</div>
 	
 	jsp로 연결된 sidemenu 입니다

	<div id="main_menu_name_area">
	    <!-- 헤더에 저장된 페이지 정보 중 어떤 메인메뉴를 클릭했나 가져옴 -->
	    <h1> <% request.getHeader("main_menu_name"); %> 마이 페이지</h1>
	</div>
	<div id="sub_menu_name_area">
    	<ul>
	        <li><a href="${ path }/views/member/myPage.jsp"><h3> <% request.getHeaders("sub_menu_name"); %> 내 정보 수정</h3></a></li>
	        <li><a href="${ path }/views/member/myTrade.jsp"><h3> <% request.getHeaders("sub_menu_name"); %> 나의 거래 내역</h3></a></li>
	        <li><a href="${ path }/views/member/myArticle.jsp"><h3> <% request.getHeaders("sub_menu_name"); %> 나의 게시글</h3></a></li>
	        <li><a href="${ path }/views/member/myComment.jsp"><h3> <% request.getHeaders("sub_menu_name"); %> 나의 댓글</h3></a></li>
	        <li><a href="${ path }/views/member/myAsk.jsp"><h3> <% request.getHeaders("sub_menu_name"); %> 1:1 문의 내역</h3></a></li>
	    </ul>
	</div>

<jsp:include page="/views/common/footer.jsp" /> 