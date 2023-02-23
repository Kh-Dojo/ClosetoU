<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />


<jsp:include page="/views/common/sub-header.jsp" />
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>

<link rel="stylesheet" href="${ path }/resources/css/trade.css" />
<section>
	<div id="sidemenu"><jsp:include
			page="/views/common/sidemenu/tradeSideMenu.jsp" /></div>
	<article>
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
			<table id="tbl-board">
				<c:if test="${ empty trlist }">
					<tr>
						<th colspan="6">조회된 게시글이 없습니다.</th>
					</tr>
				</c:if>
				<c:if test="${ not empty trlist }">
					<c:forEach var="trboard" items="${ trlist }">
						<div class="item_box">
							<div>${ trboard.no }</div>
						</div>
					</c:forEach>
				</c:if>
			</table>

			<div id="pagebar_area">
				<div></div>
				<div>
					<!-- 맨 처음으로 -->
					<button
						onclick="location.href='${ path }/views/board/trade?page=1'">&lt;&lt;</button>

					<!-- 이전 페이지로 -->
					<button
						onclick="location.href='${ path }/views/board/trade?page=${ pageInfo.prevPage }'">&lt;</button>

					<!--  10개 페이지 목록 -->
					<c:forEach begin="${ pageInfo.startPage }"
						end="${ pageInfo.endPage }" varStatus="status">
						<c:choose>
							<c:when test="${ status.current == pageInfo.currentPage}">
								<button disabled>${ status.current }</button>
							</c:when>
							<c:otherwise>
								<button
									onclick="location.href='${ path }/views/board/trade?page=${ status.current }'">${ status.current }</button>
							</c:otherwise>
						</c:choose>
					</c:forEach>


					<!-- 다음 페이지로 -->
					<button
						onclick="location.href='${ path }/views/board/trade?page=${ pageInfo.nextPage }'">&gt;</button>

					<!-- 맨 끝으로 -->
					<button
						onclick="location.href='${ path }/views/board/trade?page=${ pageInfo.maxPage }'">&gt;&gt;</button>
				</div>
				<div></div>
			</div>
		</div>
	</article>
	<script src="${ path }/resources/js/trade.js"></script>
</section>

<jsp:include page="/views/common/footer.jsp" />

