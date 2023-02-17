<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="path" value="${ pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="${ path }/resources/js/jquery-3.6.3"></script>


<title>거래 게시판 TEST</title>
<link rel="stylesheet" href="${ path }/resources/css/trade.css" />
</head>
<body>
	<jsp:include page="/views/common/header.jsp" />
	<section>
		<div id="sidemenu">사이드메뉴 자리입니다.</div>
		<article>
			article 자리입니다.
			<div id="search_section">
				<form action="${ path }/itemsearch">
					<input type="search" name="search" id="search_bar"
						placeholder="제목이나 키워드를 입력하세요." />
					<button id="search_button">검색</button>
					<button type="button" id="search_option_toggle_btn" >검색옵션</button>
					<div id="search_option_box">
						<div id="option_view_area">
							<input type="text" id="show_options_textbox" placeholder="사용자가 체크한 옵션들이 여기에 표시됩니다."
								readonly />
						</div>
						<hr />
						<div>
							<input type="checkbox" class="catagory_checkbox"
								name="item_attribute" id="attr1" value="속성1" />속성1<br /> <input
								type="checkbox" class="catagory_checkbox" name="item_attribute"
								id="attr2" value="속성2" />속성2<br /> <input type="checkbox"
								class="catagory_checkbox" name="item_attribute" id="attr3"
								value="속성3" />속성3<br /> <input type="checkbox"
								class="catagory_checkbox" name="item_attribute" id="attr4"
								value="속성4" />속성4<br />
						</div>
						<hr />
					</div>
				</form>
			</div>
			<div id="item_area">
				아이템들이 노출되는 공간입니다.
				<div id="item_list">
					<div class="item_box">
						<div class="item_thumbnail">아이템섬네일</div>
						<div class="item_info">
							<div>아이템 제목</div>
							<div>아이템 가격</div>
						</div>
					</div>
					<div class="item_box">
						<div class="item_thumbnail"></div>
					</div>
				</div>
				<div id="pagebar_area">페이지바 영역입니다</div>
			</div>
		</article>
		
	</section>
	<jsp:include page="/views/common/footer.jsp" />
	<script src="${ path }/resources/js/trade.js"></script>
</body>
</html>
