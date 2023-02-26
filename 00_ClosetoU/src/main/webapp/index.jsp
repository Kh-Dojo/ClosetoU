<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<link rel="stylesheet" href="${ path }/resources/css/index.css">
	<jsp:include page="/views/common/main-header.jsp" />

	<div class="wrap">
            <div id="content">
                <div id="content1">
					<img src="${ path }/resources/img/main.gif">
				</div>
                <div id="content2">
                	<br><br><br><br>
                    <div id="content2_1">
                        <form style="width: 650px;	margin-left : 30px;	margin-right : 30px;">

						<p>공지사항</p>
                        <table class=table1">
                            <tr>
                                <th class="th" id="td_cate1">분류</th>
                                <th class="th" id="td_title1">제목</th>
                                <th class="th" id="td_date1">날짜</th>
                            </tr>
                            <tbody id="noticelist">								
                            
                            </tbody>           	
                            
                        </table>

                    </form>                    
                    </div>
					<br><br><br>
                    <div id="content2_2">
                        <form style="width: 650px;	margin-left : 30px;	margin-right : 30px;">
                         <p style="font-size:15px">커뮤니티 인기 게시글</p>
                        <table class="table1">
                            <tr>
                                <th class="th" id="td_cate2">분류</th>                                
                                <th class="th" id="td_title2">제목</th>
                                <th class="th" id="count">조회수</th>                                
                                <th class="th" id="td_date2">날짜</th>
                            </tr>
                            <tbody id="poplist">
                            
                            </tbody>
                        </table>
                    
                    </form>
                    <br><br><br>
                    </div>
                    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;지역별 기부 현황</p>
					<div id="chartview" style="height: 300px; width: 610px; margin-left: 42px;">
						<canvas id="myChart" style="box-sizing: border-box"></canvas>
					</div>            
                </div>
                
            </div>

        </div>
		</div>
	</article>
		<script>	
		
		
		$(document).ready(function(){		
// 			var theForm = document.frmSubmit;
// 			  theForm.action = "${ path }/board/communityBoardUpdate";

			
			 $.ajax({
               url : '${ path }/noticelist',
                 type : 'GET',
                dataType : 'json',
                 success: function(json){
                	console.log(json);
                		
                		noticeAdd = '<TR>';
                			$.each(json, function(i){
                				noticeAdd += '<TD class="td">' +json[i].td1 + '</TD><TD class="td"><a style="text-decoration-line: none; color:black;" href="${ path }/communityArticleView?no=' + json[i].no + '">' +json[i].td2 + '</a></TD><TD class="td">' + json[i].td3 + '</TD>'; 
                				noticeAdd += '</TR>'
                			});                		
                		
                		$("#noticelist").append(noticeAdd);
                		}
                 	}); 
			$.ajax({
				url : '${ path }/poplist',
				type : 'GET',
				dataType : 'json',
				success: function(data){
					console.log(data);
					
					popAdd = '<TR>';
        			$.each(data, function(i){
        				popAdd += '<TD class="td">' +data[i].td2_1 + '</TD><TD class="td"><a style="text-decoration-line: none; color:black;" href="${ path }/communityArticleView?no='+data[i].no+ ' ">' +data[i].td2_2 + '</a></TD><TD class="td">' + data[i].td2_3 + '</TD><TD class="td">' + data[i].td2_4 + '</TD>'; 
        				popAdd += '</TR>'
        			}); 
        			$('#poplist').append(popAdd);
				}						
			});
			
			console.log("123");
			
			$.ajax({
				url : '${ path }/donationboard',
				type : 'GET',
				dataType : 'json',
				success: function(data){
					console.log(data);				
					const ctx = document.getElementById('myChart').getContext('2d');

					var labels = [] ;
					var donationCount = [] ;

					for(var i = 0 ; i < data.length ; i++){
						console.log("====>", data[i]);
						labels[i] = data[i].sido;
						donationCount[i] = data[i].donationCount;
					}
						
					// 차트를 그릴 영역을 dom 요소로 가져옴
					const myChart = new Chart(ctx, {
						// 차트 생성
						type: 'bar',
						// 차트의 종류
						data: {
						    // 차트 data
						    labels:labels,
						    // 값을 입력
						    // x축
						    datasets: [{
						    	label: '기부 현황 차트', 
						        data: donationCount,
								// 선 두께
						        borderWidth: 0.5
						  	}]
						},
						 options: {
						    // 차트 설정
						    scales: {
						        // y축에 관한 설정
						        yAxes: [{
						            ticks:{
						            	stepSize:5,
						            	suggestedMin: 0,
						            	suggestedMax: 20,
						            }	
								}]
						     }
						 }
					});	
				}						
			});
		
			//createDonation();
		});
		


			
	</script>
	<jsp:include page="/views/common/footer.jsp" />
