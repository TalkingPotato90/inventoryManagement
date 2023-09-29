<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>출고 등록</title>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <script>
        function link0() {
            location.href='http://localhost:8080';
        }

        $(function() {
            //input을 datepicker로 선언
            $("#datepicker").datepicker({
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
            $('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)            
        });
        
        function doRegist() {
        	var lc = document.getElementById("lcDropdown").value;
        	var mc = document.getElementById("mcDropdown").value;
        	var qty = document.getElementById("ioqty").value;
        	var outname = document.getElementById("outname").value;
        	
        	if(lc == null || lc == "" || lc == undefined){
        		alert("대분류에 입력된 값이 없습니다.");
        		return;
        	}
        	
        	if(mc == null || mc == "" || mc == undefined){
        		alert("중분류에 입력된 값이 없습니다.");
        		return;
        	}
        	if(qty == null || qty == "" || qty == undefined){
        		alert("수량에 입력된 값이 없습니다.");
        		return;
        	}
        	if(outname == null || outname == "" || outname == undefined){
        		alert("불출자에 입력된 값이 없습니다.");
        		return;
        	}
        	
        	document.getElementById("form").submit();
            alert("등록되었습니다.");
        }

    </script>
</head>
<body>
    <button onclick="link0();">메인화면으로</button>
    <br/>
    <form id="form" action="/material/unstoring" method="post">
    <input type="hidden" name="ioTyp" value = "출고">
    날짜 : <input type="text" id="datepicker" name="ioDate">
		<br>
        <label for="lcDropdown">대분류:</label>
		<select id="lcDropdown" name="largeCat">
   		 <option value="">선택</option>
   		 <c:forEach var="material" items="${materialSelectlist}">
   		     <option value="${material.largeCat}">${material.largeCat}</option>
	  	 </c:forEach>
		</select>
       
        <br>
        <label for="mcDropdown">중분류:</label>
        <select id="mcDropdown" name="midCat">
            <option value="">선택</option>
            <c:forEach var="material" items="${materialSelectlist}">
                <option value="${material.midCat}">${material.midCat}</option>
            </c:forEach>
        </select>

        <br>
        <label for="scDropdown">소분류:</label>
        <select id="scDropdown" name="smallCat">
            <option value="">선택</option>
            <c:forEach var="material" items="${materialSelectlist}">
                <option value="${material.smallCat}">${material.smallCat}</option>
            </c:forEach>
        </select>

        <br>
		수량 : <input type="text" id="ioqty" name="ioQty"/>
		<br/>
		불출자 : <input type="text" id="outname" name="outName"/>
		<br/>		
        <input type="button" value="등록" onclick="doRegist()">
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
