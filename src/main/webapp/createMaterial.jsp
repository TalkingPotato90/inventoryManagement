<%@page import="com.snf.domain.MaterialData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>신규 품목 개설</title>
    <script>
        function link0() {
            location.href = 'http://localhost:8080';
        }
        
        function doRegist(){
        	var lc = document.getElementById("lc").value;
        	var mc = document.getElementById("mc").value;
        	var sc = document.getElementById("sc").value;
        	
        	if(lc == null || lc == "" || lc == undefined){
        		alert("대분류에 입력된 값이 없습니다.");
        		return;
        	}
        	
        	if(mc == null || mc == "" || mc == undefined){
        		alert("중분류에 입력된 값이 없습니다.");
        		return;
        	}
        	
        	document.getElementById("form").submit();
        	alert("등록되었습니다.");
        }
        
        function confirmUpdate(rowId) {
            var confirmed = confirm("수정하시겠습니까?"); // 수정 확인 창 띄우기
            if (confirmed) {
                updateRow(rowId); // 사용자가 확인을 누른 경우에만 수정 함수 호출
            }
        }
        
        function updateRow(rowId){
        	document.getElementById("updRowId").value = rowId;
        	document.getElementById("updRowLc").value = document.getElementById("lc"+rowId).value;
        	document.getElementById("updRowMc").value = document.getElementById("mc"+rowId).value;
        	document.getElementById("updRowSc").value = document.getElementById("sc"+rowId).value;
        	document.getElementById("updateform").submit();
        }
        
        function confirmDelete(rowId) {
            var confirmed = confirm("삭제하시겠습니까?"); // 삭제 확인 창 띄우기
            if (confirmed) {
                deleteRow(rowId); // 사용자가 확인을 누른 경우에만 삭제 함수 호출
            }
        }
        
        function deleteRow(rowId){
        	document.getElementById("delRowId").value = rowId;
        	document.getElementById("deleteform").submit();
        }
    </script>
</head>
<body>
        <button type="button" onclick="link0();">메인화면으로</button>
    <form id="form" method="post" action="/material/insert">
        <br/>
        대분류: <input type="text" id="lc" name="largeCat"/>
        <br/>
        중분류: <input type="text" id="mc" name="midCat"/>
        <br/>
        소분류: <input type="text" id="sc" name="smallCat"/>
        <br/>
        <button type="button" onclick="doRegist();">등록</button>
    </form>
    <form id="deleteform" method="post" action="/material/delete"> 
        <input type="hidden" id="delRowId" name="id"/>
    </form>
    <form id="updateform" method="post" action="/material/update"> 
        <input type="hidden" id="updRowId" name="id"/>
        <input type="hidden" id="updRowLc" name="lc"/>
        <input type="hidden" id="updRowMc" name="mc"/>
        <input type="hidden" id="updRowSc" name="sc"/>
    </form>
    <h1>등록된 품목</h1>
    <table border="1">
        <thead>
	        <tr>
	            <th>순번</th>
	            <th>대분류</th>
	            <th>중분류</th>
	            <th>소분류</th>
	            <th>수정</th>
	            <th>삭제</th>
	        </tr>
	    </thead>
        <tbody>
        <c:forEach items="${materialList }" var="ma">
            <tr>
            	<td>
            		${ma.num}
            	</td>
            	<td>
            		<input type="text" id="lc${ma.id}" name="largeCat" value="${ma.largeCat}" />
            	</td>
            	<td>
            		<input type="text" id="mc${ma.id}" name="midCat" value="${ma.midCat}" />
            	</td>
            	<td>
            		<input type="text" id="sc${ma.id}" name="smallCat" value="${ma.smallCat}" />
            	</td>
            	<td>
            		<input type="button" value="수정" onclick="confirmUpdate('${ma.id}');" />
				</td>
            	<td>
            		<input type="button" value="삭제" onclick="confirmDelete('${ma.id}');" />
            	</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
