<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/sub-header.jsp" />

<link rel="stylesheet" href="${ path }/resources/css/qnaWrite.css" />
<section>
	<div id="sidemenu"><jsp:include page="/views/common/sidemenu/myPageSideMenu.jsp" /></div>
	<article id="qnaWrite">
		article1 자리입니다.
		<h2 class="title">문의하기</h2>
		<div id="qna-container" class="layout">
        <form name="qna-write" action="${ path }/article/qnawrite" method="POST">
            <table>
            <tr>
                <th>
                    <label for="name">성함</label>
                </th>
            </tr>
            <tr>
                <td>
                    <input type="text" name="name" id="name" value="${ loginMember.name }" placeholder="성함을 입력해 주세요." required>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="phone">연락처</label>
                </th>
            </tr>
            <tr>
                <td>
                    <input type="tel" name="phone" id="phone" value="${ loginMember.phone }" placeholder="연락처를 입력해 주세요." required>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="title">문의제목</label>
                </th>
            </tr>
            <tr>
                <td>
                    <input type="text" name="title" id="title" placeholder="제목을 입력해주세요." required>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="content">문의내용</label>
                </th>
            </tr>
            <tr>
                <td>
                    <input type="text" name="content" id="content" placeholder="문의내용을 입력해주세요." required>
                </td>
            </tr>
			<tr>
				<td>
            		<input type="submit" id="submit" value="문의하기">
            	</td>
            </tr>
            </table>
        </form>
    </div>
    </article>
    <article id="faq">
    	article2 자리입니다.
    	<h2 class="title">자주 묻는 질문</h2>
    	<div id="faq-container" class="layout">
        <ul class="faq">
            <li>
                <input type="checkbox" id="faq-1">
                <label for="faq-1">기부 가능 품목을 알려주세요.</label>
                <div>
                    <p>사계절/남녀노소 모든 의류 가능, 신발(힐, 구두 등 모든 신발), 가방(어린이집 가방 포함), 모자, 벨트, 속옷류 등의 의복 관련 품목을 기부받고 있습니다.</p>
                </div>
            </li>
            <li>
                <input type="checkbox" id="faq-2">
                <label for="faq-2">기부금 영수증 금액은 어떻게 산출되나요?</label>
                <div>
                    <p>기부금 영수증은 운송비 기부금과 보내주신 물품의 가액을 합산하여 발행해드리고 있습니다. 물품의 가액은 현지에서의 가치를 판단하여 발행해드리고 있습니다.</p>
                </div>
            </li>
            <li>
                <input type="checkbox" id="faq-3">
                <label for="faq-3">의류 기부로도 봉사점수를 받을 수 있나요?</label>
                <div>
                    <p>단순히 물품을 기부한다고 해서 봉사점수를 부여해 드리진 않습니다. 국내·외 어려운 이웃에 지원하기 위해 선별/분류/포장의 과정까지 완료한 봉사자에게만 기준에 맞게 봉사점수가 부여됩니다.</p>
                </div>
            </li>
            <li>
                <input type="checkbox" id="faq-4">
                <label for="faq-4">거래 금지 물품이 있나요?</label>
                <div>
                    <p>가품/이미테이션(상표권, 저작권 침해 물품, 특정 브랜드의 스타일을 모방한 물품) 이외 법률을 위반하는 모든 물품은 금지하고 있습니다.</p>
                </div>
            </li>
            <li>
                <input type="checkbox" id="faq-5">
                <label for="faq-5">프로필 사진과 닉네임을 변경하고 싶어요</label>
                <div>
                    <p>[마이페이지 > 내 정보 수정] 에서 개인 정보를 수정할 수 있습니다.</p>
                </div>
            </li>
        </ul>
    </div>
    </article>
</section>


<jsp:include page="/views/common/footer.jsp" />

</body>
</html>