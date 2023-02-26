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
<link rel="stylesheet" href="${ path }/resources/css/tradeUpdate.css" />
<article>
	<div id="sidemenu"><jsp:include
			page="/views/common/sidemenu/tradeSideMenu.jsp" /></div>
	<section>
		<form action="${ path }/trade/article/update" method="POST"
				enctype="multipart/form-data">
			<input type="hidden" name="articleno" value="${ article.no }">
			<input type="hidden" name="clothno" value="${ trart.clothNumber }">
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
						가격 : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
							type="number" name="price" placeholder="가격"
							value="${ trart.price }"> 원 <br> 나눔여부 : &nbsp; <input
							type="checkbox" id="free"> <br> 거래방법 : &nbsp; <select
							name="trademethod">
							<option value="직거래" selected>직거래</option>
							<option value="택배">택배</option>
						</select>
						<br> 지역 :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
							type="text" name="location" value="${ trart.location }"><br>
						<hr>
						카테코리 등록 :
						<div id="categoryoptions">
							<c:forEach var="category" items="${ categorylist }">
								<c:choose>
									<c:when test="${ category.clothCode == '00' }">
										<input type="checkbox" name="clothcategory"
											value="${ category.clothCode }" checked>${ category.clothCategory }						
								</c:when>
									<c:otherwise>
										<input type="checkbox" name="clothcategory"
											value="${ category.clothCode }">${ category.clothCategory }
								
								</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
						<hr>
						<button type="button" class="btn_small" style="width: 150px">거래종료</button>
					</div>
				</div>
			</div>
			<div id="seller_content_area">
				<div id="title">
					<input type="text" name="title" id="title" placeholder="제목"
						value="${ article.title }">
				</div>
				<div id="content">
					<textarea name="content" cols="50" rows="15">${ article.content }</textarea>
				</div>
			</div>
			<div id="seller_info_area">
				<div id="seller_info">판매자 정보</div>
				<div id="profile_photo">
					<img src="${ imgpath }/basic.jpg">
				</div>
				<div id="seller_info2">
					${ loginMember.nickname }<br> (${ loginMember.id })
					<div></div>
				</div>

			</div>
			<!-- 		<div id="other_articles_area"> -->
			<!-- 			<div id="other_item">다른 상품들이 출력될 자리입니다.</div> -->
			<!-- 		</div> -->
			<div id="manage_button_area">
				<button class="btn_small" type="submit">수정완료</button>
				<button class="btn_small" type="button"
					onclick="location.href='${ path }/views/board/trade'">목록으로</button>
			</div>
		</form>
	</section>
</article>

<jsp:include page="/views/common/footer.jsp" />

