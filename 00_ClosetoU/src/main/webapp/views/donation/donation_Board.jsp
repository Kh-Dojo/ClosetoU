<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
<jsp:include page="/views/common/header.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<title>기부 단체 현황</title>
</head>
<body>
<div style="height: 500px; width: 500px;">
<canvas id="myChart" ></canvas>
</div>

<script>
var graphDataList ='${graphDataList}';
graphDataList= JSON.parse(graphDataList) ;
	$(document).ready(function() {
		
		createDonation();
	});
	function createDonation() {
		const ctx = document.getElementById('myChart').getContext('2d');


		var labels = [] ;
		var data = [] ;



		for(var i = 0 ; i < graphDataList.length ; i++){
			labels[i] = graphDataList[i].sido;
			data[i] = graphDataList[i].donationCount;
			
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
		        // y축
		            label: '기부 현황 수',
		            data: data,
		            
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
		            		stepSize:10,
		            		suggestedMin: 0,
		            		suggestedMax: 25,
		            	}	
					}]
		        }
		    }
		});
	}

</script>
</body>
</html>