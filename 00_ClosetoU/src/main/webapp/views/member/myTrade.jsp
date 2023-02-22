<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/header.jsp" />

<style>
	section#board-list-container{width:600px; margin:0 auto; text-align:center;}
	section#board-list-container h2{margin:10px 0;}
	table#tbl-board{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
	table#tbl-board th, table#tbl-board td {border:1px solid; padding: 5px 0; text-align:center;} 
	/*페이지바*/
	div#pageBar{margin-top:10px; text-align:center; background-color:rgba(255, 204, 153, 0.3);}
	#sidemenu {width: 20%; height: auto;}
	article {width: 75%; height: auto; box-sizing: border-box; float: left; padding-right:10px;}
	section {width: 1200px; height: 400px; margin: auto;}
	section > * {box-sizing: border-box; float: left;}
	#sub_menu_name_area:active {color:rgb(220, 179, 14);}
	#sub_menu_name_area > ul {list-style:none;}
 	#sub_menu_name_area > ul > li > a {text-decoration:none; color:black;}
</style>
<section>
	<div id="sidemenu">
		<div id="main_menu_name_area">
		    <!-- 헤더에 저장된 페이지 정보 중 어떤 메인메뉴를 클릭했나 가져옴 -->
		    <h1> <% request.getHeader("main_menu_name"); %> 마이 페이지</h1>
		</div>
		<div id="sub_menu_name_area">
	    	<ul>
		        <li><a href="${ path }/views/member/myPage.jsp"><h3> <% request.getHeaders("sub_menu_name"); %> 내 정보 수정</h3></a></li>
		        <li><a href="${ path }/views/member/myTrade.jsp"><h3> <% request.getHeaders("sub_menu_name"); %> 나의 거래 내역</h3></a></li>
		        <li><a href="${ path }/views/member/myArticle.jsp"><h3> <% request.getHeaders("sub_menu_name"); %> 나의 게시글</h3></a></li>
		        <li><a href="${ path }/views/member/myComment.jsp"><h3> <% request.getHeaders("sub_menu_name"); %> 나의 댓글</h3></a></li>
		        <li><a href="${ path }/views/member/myAsk.jsp"><h3> <% request.getHeaders("sub_menu_name"); %> 1:1 문의 내역</h3></a></li>
		    </ul>
		</div>
	</div>
	
	<article>
		<h2 align="center">나의 거래 내역 </h2>
		<div id="board-list-container">

		<table id="tbl-board">
			<tr>
				<th>번호</th>
				<th>상품 명</th>
				<th>상품 가격</th>
				<th>작성일자</th>
				<th>거래일자</th>
			</tr>
<!-- 게시글 목록 가져오기 각 게시글을 리스트에 담기 -->
			<!-- list 가 비어있으면 아래와 같이 화면을 그리겠다 url의 list?page=숫자에 14 이상의 값 넣어보기-->
			<c:if test="${ empty list }">
				<tr>
					<td colspan="6">
						조회된 거래 내역이 없습니다.
					</td>
				</tr>	
			</c:if>
			<c:if test="${ not empty list }">
					<c:forEach var="board" items="${ list }">
						<div class="item_box">
							<div>${ board.no }</div>
							<div>${ board.title }</div>
							<div>${ board.title }</div>
						</div>
					</c:forEach>
				</c:if>
			</table>
		<div id="pageBar">
			<!-- 맨 처음으로 -->
			<button onclick="location.href='${ path }/views/board/trade?page=1'">&lt;&lt;</button>

			<!-- 이전 페이지로 -->
			<button onclick="location.href='${ path }/views/board/trade?page=${ pageInfo.prevPage }'">&lt;</button>

			<!-- 10개 페이지 목록 -->
			<c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" varStatus="status">	
				<c:choose>
					<c:when test="${ status.current == pageInfo.currentPage }">
						<button disabled>${ status.current }</button>
					</c:when>
					<c:otherwise>
						<button onclick="location.href='${ path }/views/board/trade?page=${ status.current }'">${ status.current }</button>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<!-- 다음 페이지로 -->
			<button onclick="location.href='${ path }/views/board/trade?page=${ pageInfo.nextPage }'">&gt;</button>

			<!-- 맨 끝으로 -->
			<button onclick="location.href='${ path }/views/board/trade?page=${ pageInfo.maxPage }'">&gt;&gt;</button>
		</div>
	</div>
	</article>

</section>
<jsp:include page="/views/common/footer.jsp" />

</body>
</html>