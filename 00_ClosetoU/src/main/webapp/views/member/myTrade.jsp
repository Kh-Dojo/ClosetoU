<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/sub-header.jsp" />

<style>
	section#board-list-container{width:600px; margin:0 auto; text-align:center;}
	section#board-list-container h2{margin:10px 0;}
	table#tbl-board{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
	table#tbl-board th, table#tbl-board td {border:1px solid; padding: 5px 0; text-align:center;} 
	/*페이지바*/
	div#pageBar{margin-top:10px; text-align:center; background-color:rgba(255, 204, 153, 0.3);}
	#sidemenu {width: 20%; height: auto;}
	article {width: 80%; height: auto; box-sizing: border-box; float: left; padding-right:10px;}
	section {width: 1200px; height: 500px; margin: auto;}
	section > * {box-sizing: border-box; float: left;}
	section th {background-color:  #FFF0CA;}
	#sub_menu_name_area:active {color:rgb(220, 179, 14);}
	#sub_menu_name_area > ul {list-style:none;}
 	#sub_menu_name_area > ul > li > a {text-decoration:none; color:black;}
	.btn_small {
	box-shadow:inset 0px 1px 0px 0px #fce2c1;
	background-color:#ffc477;
	border-radius:6px;
	border:1px solid #eeb44f;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:12px;
	font-weight:bold;
	padding:2.5px 10px;
	text-decoration:none;
	text-shadow:0px 1px 0px #cc9f52;
}
.btn_small:hover {
	background-color:#fb9e25;
}
.btn_small:active {
	position:relative;
	top:1px;
}
</style>
<section>
	<div id="sidemenu"><jsp:include page="/views/common/sidemenu/myPageSideMenu.jsp" /></div>
	
	<article>
		<h2 align="center">나의 거래 내역 </h2>
		<div id="board-list-container">

		<table id="tbl-board">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
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
				<c:forEach var="article" items="${ list }">
					<tr>
						<td>${ article.rowNum }</td>
						<td>
							<c:if test="${ article.type eq '거래' }">
								<a href="${ path }/trade/article/view?no=${ article.no }">
									${ article.title }
								</a>
							</c:if>
						</td>		<!-- 제목 -->
						<td>${ article.userNickname }</td>	<!-- 작성자 -->
						<td>${ article.postDate }</td>	<!-- 작성일 -->
						<td>${ article.readCount }</td>	<!-- 조회수 --> 
					</tr>
				</c:forEach>
			</c:if>
			</table>
		<div id="pageBar">
			<!-- 맨 처음으로 -->
			<button onclick="location.href='${ path }/myTrade?page=1'">&lt;&lt;</button>

			<!-- 이전 페이지로 -->
			<button onclick="location.href='${ path }/myTrade?page=${ pageInfo.prevPage }'">&lt;</button>

			<!-- 10개 페이지 목록 -->
			<c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" varStatus="status">	
				<c:choose>
					<c:when test="${ status.current == pageInfo.currentPage }">
						<button disabled>${ status.current }</button>
					</c:when>
					<c:otherwise>
						<button onclick="location.href='${ path }/myTrade?page=${ status.current }'">${ status.current }</button>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<!-- 다음 페이지로 -->
			<button onclick="location.href='${ path }/myTrade?page=${ pageInfo.nextPage }'">&gt;</button>

			<!-- 맨 끝으로 -->
			<button onclick="location.href='${ path }/myTrade?page=${ pageInfo.maxPage }'">&gt;&gt;</button>
		</div>
	</div>
	</article>

</section>
<jsp:include page="/views/common/footer.jsp" />

</body>
</html>