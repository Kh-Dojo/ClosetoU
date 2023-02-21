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
		width: 75%;
		height: auto;
		box-sizing: border-box;
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

	<div id="sidemenu">
		<div id="main_menu_name_area">
		    <!-- 헤더에 저장된 페이지 정보 중 어떤 메인메뉴를 클릭했나 가져옴 -->
		    <h1> <% request.getHeader("main_menu_name"); %> 마이 페이지</h1>
		</div>
		<div id="sub_menu_name_area">
	    	<ul>
		        <li><a href="${ path }/views/member/myPage.jsp" class="sub_menu_name"><h3> <% request.getHeaders("sub_menu_name"); %> 내 정보 수정</h3></a></li>
		        <li><a href="${ path }/views/member/myTrade.jsp" class="sub_menu_name"><h3> <% request.getHeaders("sub_menu_name"); %> 나의 거래 내역</h3></a></li>
		        <li><a href="${ path }/views/member/myArticle.jsp" class="sub_menu_name"><h3> <% request.getHeaders("sub_menu_name"); %> 나의 게시글</h3></a></li>
		        <li><a href="${ path }/views/member/myComment.jsp" class="sub_menu_name"><h3> <% request.getHeaders("sub_menu_name"); %> 나의 댓글</h3></a></li>
		        <li><a href="${ path }/views/member/myAsk.jsp" class="sub_menu_name"><h3> <% request.getHeaders("sub_menu_name"); %> 1:1 문의 내역</h3></a></li>
		    </ul>
		</div>
	</div>
	
	<h2>내 정보 수정</h2>
	<div id="view-container">
		<form id="memberFrm" action="${ path }/member/update" method="POST">
			<table>
				<tr>
	                <th>프로필 이미지</th>
					<td>
						<input type="file">
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
	        <button type="button" id="btnUpdatePwd">비밀번호 변경</button>
	        <input type="submit" value="정보 수정">
	        <input type="button" id="btnDelete" value="회원 탈퇴">
	 	</form>
 	</div>
</section>
<jsp:include page="/views/common/footer.jsp" />

<script>
$(document).ready(() => {
	$('#btnUpdatePwd').on('click', () => {
		let url = '${ path }/member/updatePwd';
		let status = 'left=2500px,top=200px,width=500px,height=250px'; 
	
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