<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>관리자 페이지</title>
	<style>
		button {
			background: linear-gradient(to bottom, #78b4c7 0%, #8db6c7 50%, #c0cfe8 100%); 
			border: none;
			border-radius: 8px; 
			box-shadow: 0px 1px 3px rgba(0,0,0,0.5); 
			color: white;
			font-family: Arial, sans-serif; 
			font-size: 12px; 
			padding: 15px 30px;
			text-align: center;
			text-decoration: none;
			display: inline-block;
			margin: 4px 2px;
			cursor: pointer;
			transition: all 0.3s ease-in-out; 
		}
		button:hover {
			transform: translateY(-2px); 
			box-shadow: 0px 3px 6px rgba(0,0,0,0.5);
		}
		#admin-page {
			text-align: center; 
			font-size: 24px; 
			margin-top: 20px; 
		}
		.button-num {
			font-size: 20px; 
		}
		.button-container {
			text-align: center; 
		}
		#btn-4 {
			margin-top: 8px; 
		}
	</style>
</head>
<body>

	<h1 id="admin-page">관리자 페이지</h1>
    <div class="button-container">
        <button class="button-num" style="white-space: normal; width: 250px;" onclick="location.href='userManage.html';">회원 관리</button>
        <button class="button-num" style="white-space: normal; width: 250px;" onclick="location.href='noticeManage.html';">공지사항 관리</button>
        <button class="button-num" style="white-space: normal; width: 250px;" onclick="location.href='postManage.html';">게시글 // 댓글 관리</button>
        <button class="button-num" id="btn-4" style="white-space: normal; width: 250px;">미구현</button>
    </div>
    

</body>
</html>
