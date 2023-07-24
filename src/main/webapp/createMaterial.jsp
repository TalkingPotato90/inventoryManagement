<%@page import="com.snf.domain.MaterialData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        	document.getElementById("form").submit();
        	alert("등록되었습니다.");
        }
    </script>
</head>
<body>
        <button type="button" onclick="link0();">메인화면으로</button>
    <form id="form" method="post" action="/material/insert">
        <br/>
        대분류: <input type="text" name="largeCat"/>
        <br/>
        중분류: <input type="text" name="midCat"/>
        <br/>
        소분류: <input type="text" name="smallCat"/>
        <br/>
        <button type="button" onclick="doRegist();">등록</button>
    </form>
    
    <h1>등록된 품목</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>대분류</th>
            <th>중분류</th>
            <th>소분류</th>
        </tr>
        <c:forEach items="${materialList }" var="ma">
            <tr>
            	<td>${ma.id}</td>
            	<td>${ma.largeCat}</td>
            	<td>${ma.midCat}</td>
            	<td>${ma.smallCat}</td>
            </tr>
        </c:forEach>
        
        
    </table>
    
</body>
</html>
