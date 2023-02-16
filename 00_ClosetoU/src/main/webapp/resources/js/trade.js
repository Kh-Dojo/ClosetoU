
$(document).ready(() => {
  // 체크박스가 체크될 때마다 체크된 속성들을 모두 모아 #tb_itemoptions에 출력하는 기능
  $(".catagory_checkbox").on("click", () => {
    // check_box의 길이계산
    var len = $(".catagory_checkbox").length;
    // 체크된 box들의 value값을 담을 변수 선언
    checkedValues = "";

    // check된 박스들의 value를 누적하여 변수에 더함
    for (i = 0; i <= len; i++) {
      if ($(`#attr${i}`).is(":checked")) {
        checkedValues += $(`#attr${i}`).val() + " ";
      }
    }
    // 해당 위치에 변수를 출력
    $("#show_options_textbox").val(checkedValues);
  });

  // 검색 옵션 열고 닫기
  
  $("#search_option_toggle_btn").on("click", () => {
  	
    if ($("#search_option_box").css("display") == "block") {
      $("#search_option_box").css("display", "none");
    } else {
      $("#search_option_box").css("display", "block");
    }
    
  });
});
