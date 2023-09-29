<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>현재 재고 조회</title>
</head>
<script>
function link0() {
	location.href='http://localhost:8080';
}
</script>
<body>
<button onclick="link0();">메인화면으로</button>
<br/>

<h1>등록된 품목</h1>
    <table border="1">
   	  	<colgroup>
		    <col width="5%" />
		    <col width="20%" >
		    <col width="20%"/>
		    <col width="20%"/>
		    <col width="20%"/>
	   </colgroup>
        <thead>
	        <tr>
	            <th>순번</th>
	            <th>대분류</th>
	            <th>중분류</th>
	            <th>소분류</th>
	            <th>수량</th>
	        </tr>
	    </thead>
        <tbody>
        <c:forEach items="${materialList }" var="ma">
            <tr>
            	<td>
            		${ma.num}
            	</td>
            	<td>
            		${ma.largeCat}
            	</td>
            	<td>
            		${ma.midCat}
            	</td>
            	<td>
            		${ma.smallCat}
            	</td>
            	<td>
            		${ma.qty}
            	</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</body>
</html>