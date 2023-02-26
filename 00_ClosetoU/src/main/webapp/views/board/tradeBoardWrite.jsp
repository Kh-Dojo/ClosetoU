<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
						<input type="file" name="cloth_upfile" required>
					</div>
					<div id="cloth_detail"
						style="text-align: left; padding-left: 30px; width: 350px;">
						<input type="text" name="cloth_name" placeholder="상품명을 입력해주세요."
							required>
						<button id="togglecategory_btn" type="button">카테고리 등록</button>
						<br> <br> <input type="number" step="100" name="price" id="price"
							placeholder="가격" required> <label> <input
							type="checkbox" id="free_checkbox">나눔
						</label> <br> <br> 희망거래방법 &nbsp;&nbsp;&nbsp;<select
							name="trademethod">
							<option value="직거래" selected>직거래</option>
							<option value="택배">택배</option>
						</select> <br> <br> 거래지역
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button"
							onclick="getlocation()" value="도로명 주소 찾기"
							style="margin-bottom: 5px; margin-top: 10px;"> <input
							type="text" name="location" id="roadAddress"
							value="${ trart.location }" style="width: 300px; height: 35px;"
							required><br>

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
					<br> <br>
					<div id="title_area">
						<input type="text" name="title" id="title" placeholder="제목"
							required>
					</div>
					<br> <br>
					<div>
						<textarea style="width: 660px; height: 311px;" name="content"
							cols="50" rows="15" required></textarea>
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
<jsp:include page="/views/common/footer.jsp" />

