<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />

<jsp:include page="/views/common/header.jsp" />
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>

<link rel="stylesheet" href="${ path }/resources/css/communityBoardUpdate.css" />

<article>
	<section id="content">
		<div id='board-write-container'>
			<h2>게시판 수정</h2>
			<form action="${ path }/board/update" method="POST" enctype="multipart/form-data">
				<input type="hidden" name="no" value="${ board.no }">
				<table id='tbl-board'>
					<tr>
						<th>제목</th>
	<!-- 230214 7교시 게시글 수정 페이지에 원래 작성글 내용 들어가게 만들기 -->
						<td><input type="text" name="title" id="title"
								value="${ board.title }"></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><input type="text" name="writer" value="${ board.writerId }" readonly></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<input type="file" name="upfile"><br>
							<c:if test="${ not empty board.originalFileName }">
								<span>${ board.originalFileName }</span>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="content" cols="50" rows="15" >${ board.content }</textarea></td>
					</tr>
					<tr>
						<th colspan="2">
							<input type="submit" value="수정">
							<input type="button" onclick="location.replace('${path}/board/communityBoardList')" value="목록으로">
						</th>
					</tr>
				</table>
			</form>
		</div>
	</section>
<%-- 	<script src="${ path }/resources/js/communityBoardUpdate.js"></script> --%>
</article>

<jsp:include page="/views/common/footer.jsp" />

