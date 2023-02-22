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
			<div id="submenuarea">
				<h2>거래 글 등록</h2>
			</div>
			<form action="${ path }/ariticle/communityWrite" method="POST"
				enctype="multipart/form-data">
				<div id="cloth_enroll">
					<div id="photoarea">
						<input type="file" name="upfile">
					</div>
				</div>
				<div>
					<input type="text" name="cloth_name" placeholder="상품명을 입력해주세요.">
					<button id="togglecategory_btn" type="button">카테고리 등록</button>
				</div>
				<div id="categoryoptions">
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
				<div id="content">
					<div>
						<input type="text" name="title" id="title" placeholder="제목">
					</div>
					<div>
						<textarea name="content" cols="50" rows="15"></textarea>
					</div>
					<div>
						<input type="submit" value="등록"> <input type="reset"
							value="취소">
					</div>
				</div>
			</form>
		</div>
	</article>
</section>
<script src="${ path }/resources/js/tradeWrite.js"></script>
<jsp:include page="/views/common/footer.jsp" />

