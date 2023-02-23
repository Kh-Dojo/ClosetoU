<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />

<jsp:include page="/views/common/sub-header.jsp" />
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
<link rel="stylesheet" href="${ path }/resources/css/tradeArticle.css" />
<article>
	<div id="sidemenu"><jsp:include page="/views/common/sidemenu/tradeSideMenu.jsp" /></div>
	<section>
		<div id="item_area">
			<div id="item_photos">의류의 사진이 들어가는 영역입니다.</div>
			<div id="item_details">
				<div id="item_info">아이템의 설명이 들어가는 영역입니다.</div>
				<div id="buttons_area">
					<button>채팅하기</button>
					<button>찜하기</button>
					<button>신고하기</button>
				</div>
			</div>
			<div id="seller_content_area">판매자가 작성한 글이 출력되는 영역입니다.</div>
			<div id="seller_info_area">판매자 정보가 출력되는 영역입니다.</div>
		</div>
		<div id="comment_area">
			<div>댓글들이 표시될 영역입니다.</div>
			<div>댓글의 페이지 바가 표시될 영역입니다.</div>
		</div>
		<div id="other_articles_area">다른 상품들이 출력될 자리입니다.
			<div id="other_item"></div>		
		</div>
	</section>
</article>

<jsp:include page="/views/common/footer.jsp" />

