<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<h2 align="center">1:1 문의 내역 </h2>
		<div id="board-list-container">

		<table id="tbl-board">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>문의 일자</th>
				<th>문의 내용</th>
				<th>처리 여부</th>
			</tr>

			<c:if test="${ empty list }">
				<tr>
					<td colspan="5">
						조회된 1:1 문의 내역이 없습니다.
					</td>
				</tr>	
			</c:if>

			<c:if test="${ not empty list }">
				<c:forEach var="article" items="${ list }">
					<tr>
						<td>${ article.rowNum }</td>		<!--순번 -->
						<td>${ article.title }</td>		<!-- 제목 -->
						<td>${ article.postDate }</td>	<!-- 문의일자 -->
						<td>${ article.content }</td>	<!-- 문의 내용 -->
						<td>${ article.visable }</td>	<!-- 처리 여부 -->
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<div id="pageBar">
			<!-- 맨 처음으로 -->
			<button onclick="location.href= '${ path }/myAsk?page=1'">&lt;&lt;</button>

			<!-- 이전 페이지로 -->
			<button onclick="location.href= '${ path }/myAsk?page=${ pageInfo.prevPage }'">&lt;</button>

			<c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" varStatus="status">	
				<c:choose>
					<c:when test="${ status.current == pageInfo.currentPage }">
						<button disabled>${ status.current }</button>
					</c:when>
					<c:otherwise>
						<button onclick="location.href= '${ path }/myAsk?page=${ status.current }'">${ status.current }</button>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<!-- 다음 페이지로 -->
			<button onclick="location.href= '${ path }/myAsk?page=${ pageInfo.nextPage }'">&gt;</button>

			<!-- 맨 끝으로 -->
			<button onclick="location.href= '${ path }/myAsk?page=${ pageInfo.maxPage }'">&gt;&gt;</button>
		</div>
	</div>
	</article>

</section>
<jsp:include page="/views/common/footer.jsp" />

</body>
</html>