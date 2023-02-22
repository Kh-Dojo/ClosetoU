
$(document).ready(() => {
 
  // 카테고리 옵션 열고 닫기
 
  $("#togglecategory_btn").on("click", () => {
  	
    if ($("#categoryoptions").css("display") == "block") {
      $("#categoryoptions").css("display", "none");
    } else {
      $("#categoryoptions").css("display", "block");
    }
    
  });
});
