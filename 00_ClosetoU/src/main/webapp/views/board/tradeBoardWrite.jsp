<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />

<jsp:include page="/views/common/sub-header.jsp" />
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
			<form action="${ path }/article/tradeWrite" method="POST"
				enctype="multipart/form-data">
				<div id="cloth_enroll">
					<div id="photoarea">
						<input type="file" name="cloth_upfile">
					</div>
					<div id="cloth_detail"
						style="text-align: left; padding-left: 30px; width: 350px;">
						<input type="text" name="cloth_name" placeholder="상품명을 입력해주세요.">
						<button id="togglecategory_btn" type="button">카테고리 등록</button>
						<br> <br> <input type="number" name="price"
							placeholder="가격"> <input type="checkbox" id="free">나눔
						<br> <br> 희망거래방법 &nbsp;&nbsp;&nbsp;<select
							name="trademethod">
							<option value="직거래">직거래</option>
							<option value="택배">택배</option>
						</select> <br> <br> 거래지역
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
							name="location"><br> <br>

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
					</div>
				</div>
				<div id="content">
					<br>
					<br>
					<div id="title_area">
						<input type="text" name="title" id="title" placeholder="제목">
					</div>
					<br> <br>
					<div>
						<textarea style="width: 660px; height: 311px;" name="content"
							cols="50" rows="15"></textarea>
					</div>
					<div id="content_btn_area">
						<input class="btn_small" type="submit" value="등록"> <input
							class="btn_small" type="reset" value="초기화">
					</div>
				</div>
			</form>
		</div>
	</article>
</section>
<script src="${ path }/resources/js/tradeWrite.js"></script>
<jsp:include page="/views/common/footer.jsp" />

