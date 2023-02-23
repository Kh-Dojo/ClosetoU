<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
<link rel="stylesheet" href="${ path }/resources/css/enroll.css">

<jsp:include page="/views/common/sub-header.jsp" />

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
		text-align:center;
		height:60px;
		font-size:25pt;
	}
	
	section #enroll-container table td {
		padding:0 10px; 
		width:150px;
		height:25px;
		text-align:center;
		color: rgb(220, 179, 14);
		font-weight: bold;
	}
</style>

<section id="content">
	<div id="enroll-container">	 	
	 	<form name="memberEnrollFrm" action="${ path }/member/enroll" method="POST">
	 		<table class="table table-bordered table-hover" style="text-align:center;">
	 			<thead>
	 				<tr>
	 					<th colspan="3">📌회원 가입 정보📌</th>
	 				</tr>
	 			</thead>
	 			<tbody>
	 			<br>
		 			<tr>
						<td style="width:110px">아이디</td>
						<td>
							<input type="text" class="form-control" name="userId" id="newId" placeholder="아이디를 입력해주세요" required>
						</td>
						<td style="width:110px">
							<input type="button" id="checkDuplicate" value="중복 검사" >
						</td>
		 			</tr>
		 			<tr>
						<td style="width:110px">비밀번호</td>
						<td>
							<input type="password" class="form-control" name="userPwd" id="pass1" placeholder="비밀번호를 입력하세요." required>
						</td> 			
		 			</tr>
		 			<tr>
						<td style="width:110px">비밀번호 확인</td>
						<td>
							<input type="password" id="pass2">
						</td> 			
		 			</tr>
		 			<tr>
						<td style="width:110px">이름</td>
						<td>
							<input type="text" name="userName" id="userName" required>				
						</td>
					<tr>	
						<td style="width:110px">닉네임</td>
						<td>
							<input type="text" name="userNickname" id="userNickname" required>
						</td>
		 			</tr>
		 			<tr>
						<td style="width:110px">연락처</td>
						<td>
							<input type="tel" name="phone" id="phone" maxlength="13" required>								
						</td> 			
		 			</tr>
		 			<tr>
						<td style="width:110px">이메일</td>
						<td>
							<input type="email" placeholder="abc@abc.com" name="email" id="email">												
						</td> 			
		 			</tr>
		 			<tr>
						<td style="width:110px">주소</td>
						<td>
							<input type="text" name="address" id="address">
						</td> 			
		 			</tr>
		 			<tr>
						<td style="width:110px">상세 주소</td>
						<td>
							<input type="text" name="address_detail" id="address_detail">
						</td> 			
		 			</tr>
		 		</tbody>
	 		</table> 
	 		<input type="submit" id="enrollSubmit" value="가입">	
	 		<input type="reset" value="취소">	
	 	</form>
 	</div>
</section>
<script>
	// 아이디 중복 확인
	$(document).ready(() => {
		$('#checkDuplicate').on('click', () => {
			let userId = $('#newId').val().trim();
			
			$.ajax({
				type: 'POST',
				url: '${ path }/member/idCheck',
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