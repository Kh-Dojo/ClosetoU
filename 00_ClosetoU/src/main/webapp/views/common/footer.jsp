<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>
<link rel="stylesheet" href="${ path }/resources/css/footer.css">
	<footer id="footer">
        <div id="wrap">
            <div id="top">
                <a href="${ path }/views/intro/donation_Intro.jsp" class="aTag">이용안내</a> | <a id="FAQ" href="${ path }/views/board/faq.jsp" class="aTag">FAQ</a> | <a href="https://www.hometax.go.kr/" target="_blank" class="aTag">국세청 홈택스</a>
            	<a href="#">
            		<img id="top-img" src="${ path }/resources/img/forFooterTopImg.png" alt="${ path }/">
            	</a>
            </div>
            <hr>
            <div id="bottom-left">
                <img id="logo-img" src="${ path }/resources/img/forFooterLogo.png">
            </div>
            <div id="bottom-mid">
                <div>
                    주소 : 서울특별시 강남구 테헤란로14길 6 남도빌딩 <br>
                    사업자등록번호 : 123-45-67890 | 이사장 : 이정준
                </div>
                <br><br>
                <div>
                    &lt;Copyright 2023 <strong>CloseToU.</strong> All rights reserved.&gt;
                </div>  
            </div>
            <div id="bottom-right">
                <div id="text-qna">
                    의류기부 중고거래 관련 문의(평일 09:00 ~ 17:00)<br>
                    점심시간(평일 12:00 ~ 13:00)
                <br><br>
                <div id="tel"><img id="tel-img" src="${ path }/resources/img/forFooterTelImg.png">1588-1234</div>
                <div id="btn"><a id="btnQna" href="${ path }/views/board/qnaWrite.jsp">온라인 문의</a></div>
            </div>
        </div>
    </footer>
<script>
	$(document).ready(() =>{
		
		$('#btnQna').on('focus', () => {
			if(${ empty loginMember }) {
				alert('로그인 후 문의해 주세요.')				
				$('#userId').focus();
			} else {
				if(${ not empty loginMember }){
					location.replace('${ path }/views/board/qnaWrite.jsp');
/* 					location.href = '${ path }/views/board/qnaWrite.jsp';	 */				
				}
			}
		});
	});
</script>
</body>
</html>
