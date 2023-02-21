<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
<link rel="stylesheet" href="${ path }/resources/css/enroll.css">

<jsp:include page="/views/common/header.jsp" />

<style>
	section #enroll-container {
		text-align:center;
	}
	
	section #enroll-container input {
		margin:3px;
	}
	
	section #enroll-container table {
		margin:0 auto;
	}
	
	section #enroll-container table th {
		padding:0 10px; 
		text-align:right;
	}
	
	section #enroll-container table td {
		padding:0 10px; 
		text-align:left;
	}
</style>

<section id="content">
	<h2 align="center">📌회원 가입 정보📌</h2>
	<div id="enroll-container">	 	
	 	<form name="memberEnrollFrm" action="${ path }/member/enroll" method="POST">
	 		<table>
	 			<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="userId" id="newId" size="26" placeholder="아이디를 입력해주세요" required>
						<input type="button" id="checkIdDuplicate" value="중복 검사" >
					</td> 			
	 			</tr>
	 			<tr>
					<th>패스워드</th>
					<td>
						<input type="password" name="userPwd" id="pass1" size="26" placeholder="사용할 비밀번호를 입력해주세요." required>
					</td> 			
	 			</tr>
	 			<tr>
					<th>패스워드확인</th>
					<td>
						<input type="password" id="pass2" size="26">
					</td> 			
	 			</tr>
	 			<tr>
					<th>이름</th>
					<td>
						<input type="text" name="userName" id="userName" size="26" required>				
					</td>
				<tr>	
					<th>닉네임</th>
					<td>
						<input type="text" name="userName" id="userName" size="26" required>
					</td>
	 			</tr>
	 			<tr>
					<th>연락처</th>
					<td>
						<input type="tel" name="phone" id="phone" maxlength="13" size="26" required>								
					</td> 			
	 			</tr>
	 			<tr>
					<th>이메일</th>
					<td>
						<input type="email" placeholder="abc@abc.com" name="email" id="email" size="26">												
					</td> 			
	 			</tr>
	 			<tr>
					<th>주소</th>
					<td>
						<input type="text" name="address" id="address" size="26">
					</td> 			
	 			</tr>
	 			<tr>
					<th>상세 주소</th>
					<td>
						<input type="text" name="address_detail" id="address_detail" size="26">
					</td> 			
	 			</tr>
	 		</table> 
	 		<input type="submit" id="enrollSubmit" value="가입">	
	 		<input type="reset" value="취소">	
	 	</form>
 	</div>
</section>
<script>
	// 아이디 중복 확인
	$(document).ready(() => {
		$('#checkIdDuplicate').on('click', () => {
			let userId = $('#newId').val().trim();
			
			$.ajax({
				type: 'POST',
				url: '${ path }/member/check',
				dataType: 'json',
				data: {
					userId
				},
				success: (obj) => {
					console.log(obj);
					
					if(obj.duplicate) {
						alert("이미 사용중인 아이디 입니다.");
					} else {
						alert("사용 가능한 아이디 입니다.");
					}
				},
				error: (error) => {
					console.log(error);	
				}
			});
		});
		
	});
</script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	window.onload = function(){
    document.getElementById("address").addEventListener("click", function(){ //주소입력칸을 클릭하면
        //카카오 지도 발생
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
                document.getElementById("address").value = data.address; // 주소 넣기
                document.querySelector("input[name=address_detail]").focus(); //상세입력 포커싱
            }
        }).open();
    });
}
</script>

<jsp:include page="/views/common/footer.jsp" /> 