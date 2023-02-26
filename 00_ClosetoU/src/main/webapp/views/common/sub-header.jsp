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
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
<link rel="stylesheet" href="${ path }/resources/css/sub-header.css">

<title>CloseToU 메인페이지</title>
</head>
<body>
	<header>
		<div id="header-wrap">
			<div class="logo">
					<img src="${ path }/resources/img/logo1.png">
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
								<td><input type="submit" value="로그인" class="btn_small"></td>
							</tr>
							<tr>
							<td colspan="2">
								<label><input type="checkbox" name="saveId"
											${ empty cookie.saveId ? "" : "checked" }>아이디 저장</label>
								<input type="button" class="btn_small" value="회원가입" onclick="location.href = '${ path }/views/member/enroll.jsp';"> 
							</td>
						</tr>
						</table>
					</form>
				</c:if>

				<c:if test="${ not empty loginMember }">
					<table>
						<tr>
							<th rowspan="2"><c:if
									test="${ not empty loginMember && loginMember.role == 'ROLE_USER'}"><img style="border-radius:70%;" width="70ox" height="70px" class="main-profile-img" src="${ path }/resources/img/basic.jpg"></c:if></th>
							<th class="main_login_msg" colspan="3">${ loginMember.nickname }님 안녕하세요.<br></th>
						</tr>
						<tr>
							<td><c:if
									test="${ not empty loginMember && loginMember.role == 'ROLE_USER'}">
									<button id="btn_userInfo" class="btn_small"
										onclick="location.href='${ path }/member/myPage'">마이 페이지</button>
								</c:if></td>
							<td><c:if
									test="${ not empty loginMember && loginMember.role == 'ROLE_ADMIN'}">
									<button id="btn_admin" class="btn_small"
										onclick="location.href='${ path }/admin/members'">관리자 페이지</button>
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
			<ul id="nav">
				<li><a href="${ path }/views/intro/donation_Intro.jsp">사이트 이용 안내</a></li>
				<li><a href="${ path }/views/donation/donation_Form">의류 기부 신청</a></li>
				<li><a href="${ path }/views/board/trade">중고거래 및 나눔</a></li>
				<li><a href="${ path }/board/communityBoardList">자유로운 수다방</a></li>
			</ul>
		</nav>
	</header>