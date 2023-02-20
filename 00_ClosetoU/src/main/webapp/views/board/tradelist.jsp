<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />


<jsp:include page="/views/common/header.jsp" />
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>

<link rel="stylesheet" href="${ path }/resources/css/trade.css" />
<article>
	<div id="sidemenu"><jsp:include page="/views/common/sidemenu.jsp" /></div>
	<section>
		article 자리입니다.
		<div id="search_section">
			<form action="${ path }/itemsearch" method="POST">
				<input type="search" name="search" id="search_bar"
					placeholder="제목이나 키워드를 입력하세요." /> <input type="submit" value="검색">
				<button type="button" id="search_option_toggle_btn">검색옵션</button>
				<div id="search_option_box">
					<div id="option_view_area">
						<input type="text" id="show_options_textbox"
							placeholder="사용자가 체크한 옵션들이 여기에 표시됩니다." readonly />
					</div>
					<hr />
					<div>
						<input type="checkbox" class="catagory_checkbox"
							name="item_attribute" id="attr1" value="속성1" />속성1<br /> <input
							type="checkbox" class="catagory_checkbox" name="item_attribute"
							id="attr2" value="속성2" />속성2<br /> <input type="checkbox"
							class="catagory_checkbox" name="item_attribute" id="attr3"
							value="속성3" />속성3<br /> <input type="checkbox"
							class="catagory_checkbox" name="item_attribute" id="attr4"
							value="속성4" />속성4<br />
					</div>
					<hr />
				</div>
			</form>
		</div>
		<div id="item_area">

			<c:if test="${ empty trlist }">
				<div>검색 결과가 없습니다.</div>
			</c:if>
			<c:if test="${ not empty trlist }">
				trlist가 존재합니다.
				${ trlist[0].clothInfo }
				<%-- <c:forEach var="tradearticle" items="${ trList }">
					<div id="item_list">
						<div class="item_box">
							<div class="item_thumbnail">${ tradearticle.clothInfo }</div>
							<div class="item_info">
								<div>${ tradearticle.no }</div>
								<div>${ tradearticle.price } 원</div>
							</div>
						</div>
						<div class="item_box">
							<div class="item_thumbnail"></div>
						</div>
					</div>
				</c:forEach> --%>
				<div id="pagebar_area">페이지바 영역입니다</div>
			</c:if>
		</div>
	</section>
	<script src="${ path }/resources/js/trade.js"></script>
</article>

<jsp:include page="/views/common/footer.jsp" />

