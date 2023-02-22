<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />

<script src="${ path }/resources/js/jquery-3.6.3.js"></script>

<jsp:include page="/views/common/header.jsp" />

<style>
	section#board-list-container{width:600px; margin:0 auto; text-align:center;}
	section#board-list-container h2{margin:10px 0;}
	table#tbl-board{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
	table#tbl-board th, table#tbl-board td {border:1px solid; padding: 5px 0; text-align:center;} 
	/*페이지바*/
	div#pageBar{margin-top:10px; text-align:center; background-color:rgba(255, 204, 153, 0.3);}
	#sidemenu {width: 20%; height: auto;}
	article {width: 80%; height: auto; box-sizing: border-box; float: left; padding-right:10px;}
	section {width: 1200px; height: 400px; margin: auto;}
	section > * {box-sizing: border-box; float: left;}
	#sub_menu_name_area:active {color:rgb(220, 179, 14);}
	#sub_menu_name_area > ul {list-style:none;}
 	#sub_menu_name_area > ul > li > a {text-decoration:none; color:black;}
</style>
<section id="content">
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
		<h2 align="center">나의 게시글 </h2>
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
					<td colspan="5">
						조회된 게시글이 없습니다.
					</td>
				</tr>	
			</c:if>
			<!-- list 가 비어있지 않으면 아래와 같이 화면을 그리겠다. 조회된 게시글 갯수만큼 그려줌 -->
			<c:if test="${ not empty list }">
				<c:forEach var="article" items="${ path }/views/board/communityBoardList.jsp }">
					<tr>
						<td>${ article.rowNum }</td>		<!--순번 -->
						<td>
<!-- 230214 2교시 게시판에서 제목 클릭시 게시글 상세페이지 나타나게 링크 걸기 -->
							<c:if test="${ article.type eq '공지' }">
								<a href="${ path }/communityBoardView?no=${ article.no }">
									<span>[${ article.type }]</span> ${ article.title }
								</a>
							</c:if>
							<c:if test="${ article.type eq '자유' }">
								<a href="${ path }/communityBoardView?no=${ article.no }">
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
<!-- 230213 8교시 게시판 페이지 설정 맨 처음으로 ~ 맨 끝으로 onclick= 설정으로 url에 페이지값을 넘길 수 있음-->
			<!-- 맨 처음으로 -->
			<button onclick="location.href= '${ path }/views/board/communityBoardList?page=1'">&lt;&lt;</button>

			<!-- 이전 페이지로 -->
			<button onclick="location.href= '${ path }/views/board/communityBoardList?page=${ pageInfo.prevPage }'">&lt;</button>

			<!--  10개 페이지 목록 8교시 페이지 버튼 누르면 버튼 disabled상태 되고 해당 페이지로 이동-->
			<c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" varStatus="status">	
						<!--  PageInfo.java의 getStartPage()에서부터 getEndPage()까지 반복
								게터(getStartPage()...)를 통해 가져옴.
								네이밍 규칙만 맞으면 마치 필드에 접근하는 것처럼 필드를 접근해서 가지고 오는 것처럼 만들 수 있다.-->	
				<c:choose>
					<c:when test="${ status.current == pageInfo.currentPage }">
						<button disabled>${ status.current }</button>
					</c:when>
					<c:otherwise>
						<button onclick="location.href= '${ path }/views/board/communityBoardList?page=${ status.current }'">${ status.current }</button>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<!-- 다음 페이지로 -->
			<button onclick="location.href= '${ path }/views/board/communityBoardList?page=${ pageInfo.nextPage }'">&gt;</button>

			<!-- 맨 끝으로 -->
			<button onclick="location.href= '${ path }/views/board/communityBoardList?page=${ pageInfo.maxPage }'">&gt;&gt;</button>
		</div>
	</div>

<%-- 	<script src="${ path }/resources/js/communityBoardList.js"></script> --%>
</article>
</section>

<jsp:include page="/views/common/footer.jsp" />

</body>
</html>