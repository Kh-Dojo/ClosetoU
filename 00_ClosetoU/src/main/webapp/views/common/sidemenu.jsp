<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

jsp로 연결된 sidemenu 입니다


<div id="main_menu_name_area">
    <!-- 헤더에 저장된 페이지 정보 중 어떤 메인메뉴를 클릭했나 가져옴 -->
    <h1> <% request.getHeader("main_menu_name"); %> 메인메뉴</h1>
</div>
<div id="sub_menu_name_area">
    <ul>

        <li><h3> <% request.getHeaders("sub_menu_name"); %> 서브메뉴</h3></li>
        <li><h3> <% request.getHeaders("sub_menu_name"); %> </h3></li>
        <li><h3> <% request.getHeaders("sub_menu_name"); %> </h3></li>
        <li><h3> <% request.getHeaders("sub_menu_name"); %> </h3></li>
    </ul>
</div>