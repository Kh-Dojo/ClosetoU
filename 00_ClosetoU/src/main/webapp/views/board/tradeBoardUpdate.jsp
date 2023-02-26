<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="path" value="${ pageContext.request.contextPath }" />
<c:set var="imgpath"
	value="${ pageContext.request.contextPath }/resources/img" />
<c:set var="clothimgpath"
	value="${ pageContext.request.contextPath }/resources/clothImages" />

<jsp:include page="/views/common/sub-header.jsp" />
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
<link rel="stylesheet" href="${ path }/resources/css/tradeUpdate.css" />
<article>
	<div id="sidemenu"><jsp:include
			page="/views/common/sidemenu/tradeSideMenu.jsp" /></div>
	<section>
		<form action="${ path }/trade/article/update" method="POST"
			enctype="multipart/form-data">
			<input type="hidden" name="articleno" value="${ article.no }">
			<input type="hidden" name="clothno" value="${ trart.clothNumber }">
			<div id="item_area">
				<div id="item_photos">
					<c:choose>
						<c:when test="${ empty clothphotos }">
							<img src="${ clothimgpath }/${  clothphoto.photoId }">
						</c:when>
						<c:otherwise>
							<%-- 						<c:forEach var="clothpho" items="${ clothphotos }"> --%>
							<%-- 						<img src="${ clothimgpath }/${  clothpho.photoId }"> --%>
							<%-- 						</c:forEach> --%>
							<img src="${ clothimgpath }/${  clothphotos[0].photoId }">
						</c:otherwise>
					</c:choose>
				</div>
				<div id="item_details">
					<div id="item_info">
						가격 : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
							type="number" name="price" step="100" id="price" placeholder="가격"
							value="${ trart.price }" required> 원 <br> 나눔여부 :
						&nbsp; <input type="checkbox" id="free_checkbox"> <br> 거래방법 :
						&nbsp; <select name="trademethod" required>
							<option value="직거래" selected>직거래</option>
							<option value="택배">택배</option>
						</select> <br> 지역 : <input type="button" onclick="getlocation()"
							value="도로명 주소 찾기" style="margin-bottom: 5px; margin-top: 5px;"><br>
						<input type="text" name="location" id="roadAddress"
							value="${ trart.location }" style="width: 300px; height: 35px;"
							required><br>
						<hr>
						카테코리 등록 :
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
						<hr>
						<button type="button" class="complete_trade"
							style="width: 150px; height: 35px;">거래종료</button>
					</div>
				</div>
			</div>
			<div id="seller_content_area">
				<div id="title">
					<input type="text" name="title" id="title" placeholder="제목"
						value="${ article.title }">
				</div>
				<div id="content">
					<textarea name="content" cols="50" rows="15">${ article.content }</textarea>
				</div>
			</div>
			<div id="seller_info_area">
				<div id="seller_info">판매자 정보</div>
				<div id="profile_photo">
					<img src="${ imgpath }/basic.jpg">
				</div>
				<div id="seller_info2">
					${ loginMember.nickname }<br> (${ loginMember.id })
					<div></div>
				</div>

			</div>
			<!-- 		<div id="other_articles_area"> -->
			<!-- 			<div id="other_item">다른 상품들이 출력될 자리입니다.</div> -->
			<!-- 		</div> -->
			<div id="manage_button_area">
				<button class="btn_small" type="submit">수정완료</button>
				<button class="btn_small" type="button"
					onclick="location.href='${ path }/views/board/trade'">목록으로</button>
			</div>
		</form>
	</section>
	<script src="${ path }/resources/js/tradeUpdate.js"></script>
	<script>
		function getlocation() {
			new daum.Postcode({
				oncomplete : function(data) {
					// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

					// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
					// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
					var roadAddr = data.roadAddress; // 도로명 주소 변수
					var extraRoadAddr = ''; // 참고 항목 변수

					// 법정동명이 있을 경우 추가한다. (법정리는 제외)
					// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
					if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
						extraRoadAddr += data.bname;
					}
					// 건물명이 있고, 공동주택일 경우 추가한다.
					if (data.buildingName !== '' && data.apartment === 'Y') {
						extraRoadAddr += (extraRoadAddr !== '' ? ', '
								+ data.buildingName : data.buildingName);
					}
					// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
					if (extraRoadAddr !== '') {
						extraRoadAddr = ' (' + extraRoadAddr + ')';
					}

					// 우편번호와 주소 정보를 해당 필드에 넣는다.
					document.getElementById("roadAddress").value = roadAddr;

					var guideTextBox = document.getElementById("guide");
					// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
					if (data.autoRoadAddress) {
						var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
						guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr
								+ ')';
						guideTextBox.style.display = 'block';

					} else if (data.autoJibunAddress) {
						var expJibunAddr = data.autoJibunAddress;
						guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr
								+ ')';
						guideTextBox.style.display = 'block';
					} else {
						guideTextBox.innerHTML = '';
						guideTextBox.style.display = 'none';
					}
				}
			}).open();
		}
	</script>
</article>

<jsp:include page="/views/common/footer.jsp" />

