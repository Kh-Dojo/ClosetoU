<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />

jsp include로 연결된 header입니다.

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
</head>
<body>
	<header>
		<h1>CloseToU</h1>
		
		<br>
		
		<div class="login-container">
			<c:if test="${ empty loginMember }">
				<form id="loginFrm" action="${ path }/login" method="post">
					<table>
						<tr>
							<td>
								<input type="text" name="userId" id="userId" placeholder="아이디"
									required>
							</td>
							<td></td>
						</tr>
						<tr>
							<td>
								<input type="password" name="userPwd" id="userPwd" placeholder="비밀번호" required>
							</td>
							<td>
								<input type="submit" value="로그인">						
							</td>
						</tr>
					</table>
				</form>
			</c:if>
			
			<c:if test="${ not empty loginMember }">
				<table>
					<tr>
						<td colspan="2">
							${ loginMember.name }님 안녕하세요. 
						</td>
					</tr>
					<tr>
						<td>
							<button onclick="location.href='${ path }/member/myPage'">내 정보</button>
						</td>
						<td>
							<button onclick="location.replace('${ path }/logout')">로그아웃</button>
						</td>		
					</tr>	
				</table>
			</c:if>
		</div>
	</header>
</body>

<a href="${ path }/boardtrade">거래페이지</a>
