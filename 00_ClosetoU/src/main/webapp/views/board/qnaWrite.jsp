<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>
<link rel="stylesheet" href="${ path }/resources/css/qnaWrite.css" />

<jsp:include page="/views/common/sub-header.jsp" />


<section id="qnaWrite-container">
	<div id="sidemenu"><jsp:include page="/views/common/sidemenu/myPageSideMenu.jsp" /></div>
	<div id="qnaWrite">
        <br>
		<h2 id="qnatTitle">온라인 문의</h2>
            <ul class="qnaList">
                <li>신속한 답변을 원하시면 대표번호 [1588-1234]으로 연락 주시길 바랍니다.<br>
                    (전화상담 가능시간 : 월 ~ 금 09:00 ~ 15:00, 점심시간 12:00~13:00)
                </li>
                <li>
                    문의하시기 전 <a href="${ path }/views/board/faq.jsp" id="faq-a"><b>'자주 묻는 질문'</b></a>을 먼저 확인해 보세요. 바로 궁금증을 해결할 수 있습니다.
                </li>
                </li>
                    원하는 내용이 없을 경우 '온라인 문의'를 이용해 주세요.
                </li>
            </ul>
            <br>

            <form name="qna-write" action="${ path }/article/qnawrite" method="POST">
                <table id="qna-table">
                    <tr class="tr">
                        <tr >
                        <th>
                            <label for="name">성함</label>
                        </th>
                        <td>
                            <input type="text" name="name" id="name" value="${ loginMember.name }" placeholder="성함을 입력해 주세요." required>
                        </td>
                    </tr>
                    <tr class="tr">
                        <th>
                            <label for="phone">연락처</label>
                        </th>
                        <td>
                            <input type="tel" name="phone" id="phone" value="${ loginMember.phone }" placeholder="연락처를 입력해 주세요." required>
                        </td>
                    </tr>
                    <tr  class="tr">
                        <tr>
                        <th>
                            <label for="title">문의제목</label>
                        </th>
                        <td>
                            <input type="text" name="title" id="title" placeholder="제목을 입력해주세요." required>
                        </td>
                    </tr>
                    <tr id="tr-content">
                        <th id="th-content">
                            <label for="content">문의내용</label>
                        </th>
                        <td id="td-content">
                            <input type="textarea" name="content" id="qna-cont-textarea" placeholder="문의내용을 입력해주세요." required>
                        </td>
                    </tr>
                </table><br>
			<input type="submit" id="qna-submit" value="문의하기">
		</form>
		<br><br><br>
    </div>
</section>
<jsp:include page="/views/common/footer.jsp" />
