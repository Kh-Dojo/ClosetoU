$(document).ready(() => {

	// 카테고리 옵션 열고 닫기

	$("#togglecategory_btn").on("click", () => {

		if ($("#categoryoptions").css("display") == "block") {
			$("#categoryoptions").css("display", "none");
		} else {
			$("#categoryoptions").css("display", "block");
		}

	});



	$("#free_checkbox").change(function() {
		if ($("#free_checkbox").is(":checked")) {
			alert('나눔에 체크하셨습니다!^^');
			$("#price").val(0);
			$("#price").attr('readonly', true);
		} else {
			$("#price").attr('readonly', false);
			alert('나눔 안 하시게요?ㅜㅜ');
		}
	});

	$("#price").change(function() {
		if ($("#price").val() < 0) {
			alert('0원 이하로는 설정할 수 없어요~');
			$("#price").val(0);
		}
	});


});
