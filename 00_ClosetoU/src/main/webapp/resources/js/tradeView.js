/**
 * 
 */

$(document).ready(() => {

	$(btnDelete).on("click",
		function delcheck() {
			if (confirm('정말 게시글을 삭제하시겠습니까?')) {
				return true;

			} else {
				return false;
			}
		}

	);

});