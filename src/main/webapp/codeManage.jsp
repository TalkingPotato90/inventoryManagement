<%@page import="com.snf.domain.MaterialData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>코드 관리</title>
    <script>
        function link0() {
            location.href = 'http://localhost:8080';
        }
        
        function doRegist(){
        	var codeGroup = document.getElementById("codeGroup").value;
        	var codeGroupName = document.getElementById("codeGroupName").value;
        	
        	if(codeGroup == null || codeGroup == "" || codeGroup == undefined){
        		alert("코드 그룹에 입력된 값이 없습니다.");
        		return;
        	}
        	
        	if(codeGroupName == null || codeGroupName == "" || codeGroupName == undefined){
        		alert("코드 그룹명에 입력된 값이 없습니다.");
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
    <form id="form" method="post" action="/common/code/insert">
        <br/>
        코드 그룹 : <input type="text" id="codeGroup" name="codeGroup"/>
        <br/>
        코드 그룹명 : <input type="text" id="codeGroupName" name="codeGroupName"/>
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
    <h1>코드 그룹 목록</h1>
    <table border="1">
        <thead>
	        <tr>
	            <th>순번</th>
	            <th>코드 그룹</th>
	            <th>코드 그룹명</th>
	            <th>등록 일시</th>
	            <th>수정</th>
	            <th>삭제</th>
	        </tr>
	    </thead>
        <tbody>
        <c:forEach items="${codeList}" var="code">
            <tr>
            	<td>
            		${code.num}
            	</td>
            	<td>
            		<input type="text" id="lc${code.id}" name="largeCat" value="${code.codeGroup}" />
            	</td>
            	<td>
            		<input type="text" id="mc${code.id}" name="midCat" value="${code.codeGroupName}" />
            	</td>
            	<td>
            		<input type="text" id="mc${code.id}" name="midCat" value="${code.createdDate}" />
            	</td>
            	<td>
            		<input type="button" value="수정" onclick="confirmUpdate('${code.id}');" />
				</td>
            	<td>
            		<input type="button" value="삭제" onclick="confirmDelete('${code.id}');" />
            	</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <hr>
    <br>
        <form id="form" method="post" action="/common/code/insert">
        <br/>
        코드 그룹 : <input type="text" id="codeGroup" name="codeGroup"/>
        
        
<%--         <br>
        <label for="cgDropdown">코드 그룹 : </label>
        <select id="cgDropdown" name="codeGroup">
            <option value="">선택</option>
            <c:forEach var="code" items="${materialSelectlist}">
                <option value="${material.midCat}">${material.midCat}</option>
            </c:forEach>
        </select> --%>
        
        
        
        
        <br/>
        코드 : <input type="text" id="codeGroup" name="codeGroup"/>
        <br/>
        코드명 : <input type="text" id="codeGroup" name="codeGroup"/>
        <br/>
        부모 그룹 : <input type="text" id="codeGroup" name="codeGroup"/>
        <br/>
        부모 코드 : <input type="text" id="codeGroupName" name="codeGroupName"/>
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
    <h1>코드 그룹 목록</h1>
    <table border="1">
        <thead>
	        <tr>
	            <th>순번</th>
	            <th>코드 그룹</th>
	            <th>코드 그룹명</th>
	            <th>등록 일시</th>
	            <th>수정</th>
	            <th>삭제</th>
	        </tr>
	    </thead>
        <tbody>
        <c:forEach items="${codeList}" var="code">
            <tr>
            	<td>
            		${code.num}
            	</td>
            	<td>
            		<input type="text" id="lc${code.id}" name="largeCat" value="${code.codeGroup}" />
            	</td>
            	<td>
            		<input type="text" id="mc${code.id}" name="midCat" value="${code.codeGroupName}" />
            	</td>
            	<td>
            		<input type="text" id="mc${code.id}" name="midCat" value="${code.createdDate}" />
            	</td>
            	<td>
            		<input type="button" value="수정" onclick="confirmUpdate('${code.id}');" />
				</td>
            	<td>
            		<input type="button" value="삭제" onclick="confirmDelete('${code.id}');" />
            	</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
