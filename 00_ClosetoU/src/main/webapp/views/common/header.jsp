<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=<device-width>, initial-scale=1.0">
<script src="${ path }/resources/js/jquery-3.6.3"></script>
<link rel="stylesheet" href="${ path }/resources/css/header.css">

<title>ClosetoU 메인페이지</title>
</head>
<body>
	<header>
		<div id="header-wrap">
			<div class="logo">
				<img src="${ path }/resources/img/logo_ver1.PNG">
			</div>

			<div class="title">
				<h1>
					<a href="${ path }/">CloseToU</a>
				</h1>
			</div>

			<div class="login-container">
				<c:if test="${ empty loginMember }">
					<form id="loginFrm" action="${ path }/login" method="post">
						<table>
							<tr>
								<td><input type="text" name="userId" id="userId"
									placeholder="아이디"
									<%-- value="${ empty cookie.saveId ? '' : cookie.saveId.value }" --%> required>
								</td>
								<td></td>
							</tr>
							<tr>
								<td><input type="password" name="userPwd" id="userPwd"
									placeholder="비밀번호" required></td>
								<td><input type="submit" value="로그인"></td>
							</tr>
						</table>
					</form>
				</c:if>

				<c:if test="${ not empty loginMember }">
					<table>
						<tr>
							<td class="main_login_msg" colspan="3">${ loginMember.name }님
								안녕하세요. <br> <br>
							</td>
							<td></td>
						</tr>
						<tr>
							<td><c:if
									test="${ not empty loginMember && loginMember.role == 'ROLE_USER'}">
									<button id="btn_userInfo" class="btn_small"
										onclick="location.href='${ path }/member/myPage'">마이
										페이지</button>
								</c:if></td>
							<td><c:if
									test="${ not empty loginMember && loginMember.role == 'ROLE_ADMIN'}">
									<button id="btn_admin" class="btn_small">
										<a href="${ path }/admin/members"
											style="text-decoration: none; color: black;">관리자 페이지</a>
									</button>
								</c:if></td>
							<td>
								<button id="btn_logout" class="btn_small"
									onclick="location.replace('${ path }/logout')">로그아웃</button>
							</td>
						</tr>
					</table>
				</c:if>
			</div>
		</div>
		<nav>
			<div id="nav-wrap">
				<ul class="gnb">
					<li><a href="#">사이트 이용 안내</a></li>
					<li><a href="#">의류 기부 신청</a></li>
					<li><a href="${ path }/boardtrade">중고거래 및 나눔</a></li>
					<li><a href="#">자유로운 수다방</a></li>
				</ul>
			</div>
		</nav>
	</header>