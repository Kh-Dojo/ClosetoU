<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
				<div>
					<a href="${ path }/trade/article/view?no=90">거래글보기 임시 링크</a>
				</div>
				<div id="search_option_box">
					<div id="option_view_area">
						<input type="text" id="show_options_textbox"
							placeholder="사용자가 체크한 옵션들이 여기에 표시됩니다." readonly />
					</div>
					<hr />
					<div>
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
						<hr />
					</div>
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
					<c:forEach var="trboard" items="${ trlist }" varStatus="loop">
						<div class="item_box">
							<c:forEach var="cloth" items="${ cllist }">
								<c:if test="${ trboard.clothNumber == cloth.no }">
									<div>
										<img class="item_thumbnail"
											src="${ path }/resources/clothImages/${ cloth.photoId }">
									</div>
								</c:if>
							</c:forEach>
							<c:forEach var="arboard" items="${ list }">
								<c:if test="${ trboard.no == arboard.no }">
									<div>${ arboard.title }</div>
								</c:if>
							</c:forEach>
							<div>${ trboard.price }</div>
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

