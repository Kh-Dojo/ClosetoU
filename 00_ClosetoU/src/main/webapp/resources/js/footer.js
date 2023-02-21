window.onload = function(){
		
 		function loginCheck() {
			
			var id = "<c:out value='${param.userId}'/>";
			
	        if (id == null) {
	            alert("로그인 후 문의할 수 있습니다.");
	            location.href = "${ path }/index.jsp";
	            //return false;
	        } else {
	            location.href = '${ path }/views/board/qnaWrite.jsp';
	        }
	    };
	};