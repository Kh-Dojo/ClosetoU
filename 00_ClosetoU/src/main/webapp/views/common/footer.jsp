<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>


	<footer style="background-color: beige" align="center">
	jsp include로 연결된 footer입니다. <br><br>
		<a href="${ path }/board/communityBoardList">FAQ</a> | <a href="${ path }/views/board/qnaWrite.jsp" onclick="loginCheck();">1:1 문의</a> | <a href="https://www.hometax.go.kr/" target="_blank">국세청 홈택스</a> 
		<hr>
		<p>
		의류기부 중고거래 관련 문의(평일 09:00 ~ 17:00)<br>
		점심시간(평일 12:00 ~ 13:00)
		</p>
		<p style="font-size: 25px">1588-1234</p>
		<p style="color: gray;">
			&lt;Copyright 2023 <strong>ClosetToU</strong>. All rights reserved.&gt;
		</p>
	</footer>
<!-- <script>
	window.onload = function(){
	    function loginCheck() {
	        var id = '${userId}';
	        if (id == null) {
	            alert("로그인 후 문의할 수 있습니다.");
	            location.href = "${ path }/index.jsp";
	            return false;
	        } else {
	            location.href = '${ path }/views/board/qnaWrite.jsp';
	        }
	    }
	}
</script>
 -->
<%-- <script>
	window.onload = function(){
	    function loginCheck(){ 
	         var id = '<%=(String)request.getAttribute("userId")%>';
		//	var id = "<c:out value='${param.userId}'/>";
			
	          if(id=="null"){
	             alert("로그인이 필요한 항목입니다. 로그인 후 문의해 주세요."); 
	             location.href = "${ path }/index.jsp";
	          }
	          else{
	        	  location.href = '${ path }/views/board/qnaWrite.jsp';
	          }
		}
    }    --%>
</script>
</body>
</html>
