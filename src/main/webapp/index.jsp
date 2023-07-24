<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>SNF 재고 관리 시스템</title>
<script>
function link0() {
	location.href='http://localhost:8080/createMaterial.jsp';
}
function link1() {
	location.href='http://localhost:8080/storing.jsp';
}
function link2() {
	location.href='http://localhost:8080/unstoring.jsp';
}
function link3() {
	location.href='http://localhost:8080/inventoryCheck.jsp';
}
function link4() {
	location.href='http://localhost:8080/historyCheck.jsp';
}
</script>
</head>
<body>
<button onclick="link0();">신규 자재 추가</button>
<br/>
<button onclick="link1();">입고 등록</button>
<br/>
<button onclick="link2();">출고 등록</button>
<br/>
<button onclick="link3();">현재 재고 조회</button>
<br/>
<button onclick="link4();">이력 조회</button>
</body>
</html>