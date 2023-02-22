<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />

<jsp:include page="/views/common/header.jsp" />
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>

<link rel="stylesheet" href="${ path }/resources/css/communityBoardWrite.css" />

<section>
<div id="sidemenu"><jsp:include page="/views/common/sidemenu.jsp" /></div>
<article id="content">
	<div id='board-write-container'>
		<h2>게시판 작성</h2>
<!-- 230214 4교시 글 작성할 때 첨부파일 넣으면 서버로 전달
				enctype="multipart/form-data"을 지정해야 파일을 파일 그대로 보냄. 파일명만 보내는 것이 아니라
				인코딩 타입 변경 속성 폼태그의 인코딩 타입이 multipart/form-data 으로 변경
				http://www.servlets.com/의 COS File Upload Library 다운받아 등록
					(알아서 풀기 후 lib폴더 안의 cos.jar를 이클립스 웹프로젝트 WEB-INF lib폴더에 복붙)
				파일 중복 이름 처리, 인코딩 방식을 지정해 쉽게 처리해주는 라이브러리
				-->
		<form action="${ path }/article/communityWrite" method="POST"  enctype="multipart/form-data">
			<table id='tbl-board'>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" id="title"></td>
				</tr>
				<tr>
					<th><label for="">말머리 : </label></th>
					<td>
				        <select name="type" id="type">    
				                <option value="공지">공지</option>
				                <option value="자유">자유</option>
				        </select>
        			</td>
				</tr>
				<tr>
					<th>작성자</th>
<!-- 230214 4교시 첨부파일 다운로드하기 글 작성할 때 첨부파일 넣으면 서버로 전달 -->
					<td><input type="text" name="writer" value="${ loginMember.nickname }" readonly></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="upfile"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="content" cols="50" rows="15" ></textarea></td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="submit" value="등록">
						<input type="reset" value="취소">
					</th>
				</tr>
			</table>
		</form>
	</div>
</article>
<%-- <script src="${ path }/resources/js/communityBoardWrite.js"></script> --%>
</section>

<jsp:include page="/views/common/footer.jsp" />

