<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />

<jsp:include page="/views/common/header.jsp" />
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>

<link rel="stylesheet" href="${ path }/resources/css/tradeWrite.css" />
<section>
	<div id="sidemenu"><jsp:include
			page="/views/common/sidemenu/tradeSideMenu.jsp" /></div>
	<article id="content">
		<div id='board-write-container'>
			<div id = "submenuarea">
			<h2>거래 글 등록</h2>
			</div>
			<form action="${ path }/ariticle/communityWrite" method="POST"
				enctype="multipart/form-data">
				<div id="cloth_enroll">
					<input type="text" name="cloth_name">
					<button id="togglecategory_btn" type="button">카테고리 열기</button>
					<div id = "categoryoptions">
						<c:forEach var="category" items="${ categorylist }">
							<c:choose>
								<c:when test="${ category.clothCode == '00' }">
									<input type="checkbox" name="clothcategory"
										value="${ category.clothCategory }" checked>${ category.clothCategory }						
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="clothcategory"
										value="${ category.clothCategory }">${ category.clothCategory }
								
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>

				</div>

				<div id="content">
					<table id='tbl-board'>
						<tr>
							<th>제목</th>
							<td><input type="text" name="title" id="title"></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td><input type="text" name="writer"
								value="${ loginMember.id }" readonly></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea name="content" cols="50" rows="15"></textarea></td>
						</tr>
						<tr>
							<th colspan="2"><input type="submit" value="등록"> <input
								type="reset" value="취소"></th>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</article>
</section>
<script src="${ path }/resources/js/tradeWrite.js"></script>
<jsp:include page="/views/common/footer.jsp" />

