<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>
<link rel="stylesheet" href="${ path }/resources/css/myPage.css">
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>

<jsp:include page="/views/common/header.jsp" />

<style>
	section {
		position: relative;
	}

	section #view-container {
		text-align:center;
		height: auto;
		box-sizing: border-box;
	}
	
	section #view-container input {
		margin:3px;
		padding:3px;
	}
	
	.btn_myPage {
		padding-left:500px;
	}
	
	section #view-container table {
		margin:0 auto;
		padding-left:250px;
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
	<div id="sidemenu"><jsp:include page="/views/common/sidemenu/myPageSideMenu.jsp" /></div>
	
	<h2>내 정보 수정</h2>
	<div id="view-container">
		<form id="memberFrm" enctype="multipart/form=data" action="${ path }/member/update" method="POST">
			<table>
				<tr>
	                <th>프로필 등록</th>
					<td class="btn-file">
						👉클릭하여 이미지 업로드<input type="file" name="userProfile">
					</td>
	            </tr>
				<tr>
	                <th>아이디</th>
					<td>
						<input type="text" name="userId" id="newId" 
							value="${ loginMember.id }" readonly required >
					</td> 	
	            </tr>
	            <tr>
	                <th>이름</th>
					<td>
						<input type="text" name="userName" id="userName" 
							value="${ loginMember.name }" required>				
					</td> 	
	            </tr>
	             <tr>
	                <th>닉네임</th>
					<td>
						<input type="text" name="nickName" id="nickName" 
							value="${ loginMember.nickname }">				
					</td> 	
	            </tr>
      	        <tr>
	                <th>연락처</th>
	                <td>
	                    <input type="tel" name="phone" value="${ loginMember.phone }"
	                    	id="phone" maxlength="13">
	                </td>
	            </tr>
	            <tr>
	                <th>이메일</th>
					<td>
						<input type="email" name="email" id="email" value="${ loginMember.email }">												
					</td> 	
	            </tr>
	            <tr>
	                <th>주소</th>
					<td>
						<input type="text" name="address" id="address"
							value="${ loginMember.address }">
					</td> 	
	            </tr>
	            <tr>
	                <th>상세 주소</th>
					<td>
						<input type="text" name="address_detail" id="address_detail"
							value="${ loginMember.address_detail }">
					</td> 	
	            </tr>
	        </table>
	        <br>
	        <div class="btn_myPage">
		        <button style="height:30px;" type="button" id="btnUpdatePwd">비밀번호 변경</button>
		        <input style="height:30px;" type="submit" value="정보 수정">
		        <input style="height:30px;" type="button" id="btnDelete" value="회원 탈퇴">
	        </div>
	 	</form>
 	</div>
</section>
<jsp:include page="/views/common/footer.jsp" />

<script>
$(document).ready(() => {
	$('#btnUpdatePwd').on('click', () => {
		let url = '${ path }/member/updatePwd';
		let status = 'left=2500px,top=200px,width=500px,height=195px'; 
	
		open(url, 'updatePwd', status);
	});
	
	$('#btnDelete').on('click', () => {
		if(confirm('정말로 탈퇴하시겠습니까?')) {
			location.replace('${ path }/member/delete');
		}
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