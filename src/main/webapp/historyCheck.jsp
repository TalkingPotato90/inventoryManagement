<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>이력 조회</title>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <script>
        function link0() {
            location.href='http://localhost:8080';
        }

        $(function() {
            //input을 datepicker로 선언
            $("#datepicker1, #datepicker2").datepicker({
                dateFormat: 'yy-mm-dd', //달력 날짜 형태
                showOtherMonths: true, //빈 공간에 현재월의 앞뒤월의 날짜를 표시
                showMonthAfterYear: true, // 월- 년 순서가아닌 년도 - 월 순서
                changeYear: true, //option값 년 선택 가능
                changeMonth: true, //option값  월 선택 가능                
                showOn: "both", //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
                buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif", //버튼 이미지 경로
                buttonImageOnly: true, //버튼 이미지만 깔끔하게 보이게함
                buttonText: "선택", //버튼 호버 텍스트              
                yearSuffix: "년", //달력의 년도 부분 뒤 텍스트
                monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], //달력의 월 부분 텍스트
                monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], //달력의 월 부분 Tooltip
                dayNamesMin: ['일','월','화','수','목','금','토'], //달력의 요일 텍스트
                dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'], //달력의 요일 Tooltip
                minDate: "-5Y", //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
                maxDate: "+5y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)  
            });                    
            
            //초기값을 오늘 날짜로 설정해줘야 합니다.
//             $('#datepicker1').datepicker('setDate', 'today -1M'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, +1M:한달후, +1Y:일년후)            
//             $('#datepicker2').datepicker('setDate', 'today');
        });
        
        function doRegist() {
        	document.getElementById("form").submit();
            alert("조회되었습니다.");
        }

    </script>
</head>
<body>
    <button onclick="link0();">메인화면으로</button>
    <br/>
    <form id="form" action="/material/historycheck" method="post">
    	<p>조회기간 
	        <input type="text" id="datepicker1" name="fromDate"> 부터
	        <input type="text" id="datepicker2" name="toDate"> 까지
    	</p>
<input type="button" value="조회" onclick="doRegist()">
    </form>

<h1>등록된 품목</h1>
    <table border="1">
        <thead>
	        <tr>
	            <th>순번</th>
	            <th>대분류</th>
	            <th>중분류</th>
	            <th>소분류</th>
	            <th>입출고구분</th>
	            <th>수량</th>
	            <th>날짜</th>
	            <th>불출자</th>
	        </tr>
	    </thead>
        <tbody>
        <c:forEach items="${materialList }" var="ma">
            <tr>
            	<td>${ma.num}</td>
            	<td>${ma.largeCat}</td>
            	<td>${ma.midCat}</td>
            	<td>${ma.smallCat}</td>
            	<td>${ma.ioTyp}</td>
            	<td>${ma.ioQty}</td>
            	<td>${ma.ioDate}</td>
            	<td>${ma.outName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</body>
</html>
