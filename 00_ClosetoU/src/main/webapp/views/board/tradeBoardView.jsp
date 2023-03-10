<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />
<c:set var="imgpath"
	value="${ pageContext.request.contextPath }/resources/img" />
<c:set var="clothimgpath"
	value="${ pageContext.request.contextPath }/resources/clothImages" />

<jsp:include page="/views/common/sub-header.jsp" />
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
<link rel="stylesheet" href="${ path }/resources/css/tradeArticle.css" />
<article>
	<div id="sidemenu"><jsp:include
			page="/views/common/sidemenu/tradeSideMenu.jsp" /></div>
	<section>
		<div id="item_area">
			<div id="item_photos">
				<c:choose>
					<c:when test="${ empty clothphotos }">
						<img src="${ clothimgpath }/${  clothphoto.photoId }">
					</c:when>
					<c:otherwise>
						<%-- 						<c:forEach var="clothpho" items="${ clothphotos }"> --%>
						<%-- 						<img src="${ clothimgpath }/${  clothpho.photoId }"> --%>
						<%-- 						</c:forEach> --%>
						<img src="${ clothimgpath }/${  clothphotos[0].photoId }">
					</c:otherwise>
				</c:choose>
			</div>
			<div id="item_details">
				<div id="item_info">
					<div id="cloth_name">${ cloth.name }</div>
					<br>
					<div id="cloth_price">${ trart.price }원</div>
					<br> <br> 거래방법 : ${ trart.tradeMethod } <br> <br>
					지역 : ${ trart.location }
				</div>
				<div id="buttons_area">
					<button class="btn_small" style="font-size: 12px;">채팅하기</button>
					<button class="btn_small" style="font-size: 12px;">찜하기</button>
					<button class="btn_small" style="font-size: 12px;"
						disabled="disabled">신고하기</button>
				</div>
			</div>
		</div>
		<div id="seller_content_area">
			<div id="title">${ article.title }</div>
			<div id="content">${ article.content }</div>
		</div>
		<div id="seller_info_area">
			<div id="seller_info">판매자 정보</div>
			<div id="profile_photo">
				<img src="${ imgpath }/basic.jpg">
			</div>
			<div id="seller_info2">
				${ member.nickname }<br> (${ member.id })
				<div></div>
			</div>

		</div>
		<!-- 		<div id="other_articles_area"> -->
		<!-- 			<div id="other_item">다른 상품들이 출력될 자리입니다.</div> -->
		<!-- 		</div> -->
		<div id="manage_button_area">
			<button class="btn_small" type="button"
				onclick="location.href='${ path }/trade/article/update?no=${ article.no }'">수정</button>
			<button class="btn_small" type="button" id="btnDelete" name="btnDelete"
				onclick="location.href='${ path }/trade/article/delete?no=${ article.no }&&user=${ member.nickname }'">삭제</button>
			<button class="btn_small" type="button" style="width: 12%;"
				onclick="location.href='${ path }/views/board/trade'">목록으로</button>
		</div>
	</section>
</article>
<script src="${ path }/resources/js/tradeView.js"></script>
<jsp:include page="/views/common/footer.jsp" />

