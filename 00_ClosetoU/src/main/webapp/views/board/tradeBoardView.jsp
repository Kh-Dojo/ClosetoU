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
					 	사진 : ${ clothphoto.photoId }
					</c:when>
					<c:otherwise>
						<c:forEach var="clothpho" items="${ clothphotos }">
						<img src="${ clothimgpath }/${  clothpho.photoId }">
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			<div id="item_details">
				<div id="item_info">
					가격 : ${ trart.price } <br> 거래종료 : ${ trart.tradeEnd } <br>
					나눔여부 : ${ trart.free } <br> 거래방법 : ${ trart.tradeMethod } <br>
					지역 : ${ trart.location }
				</div>
				<div id="buttons_area">
					<button>채팅하기</button>
					<button>찜하기</button>
					<button>신고하기</button>
				</div>
			</div>
		</div>
		<div id="seller_content_area">
			<div id="title">제목 :${ article.title }</div>
			<div id="content">내용: ${ article.content }</div>
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
			<c:if
				test="${ not empty loginMember && loginMember.nickname == Article.userNickname }">
				<button type="button"
					onclick="location.href='${ path }/board/communityBoardUpdate?no=${ Article.no }'">수정</button>
				<button type="button" id="btnDelete">삭제</button>
			</c:if>
		</div>
		<div id="other_articles_area">

			<div id="other_item">다른 상품들이 출력될 자리입니다.</div>
			<button type="button"
				onclick="location.href='${ path }/views/board/trade'">목록으로</button>
		</div>
	</section>
</article>

<jsp:include page="/views/common/footer.jsp" />

