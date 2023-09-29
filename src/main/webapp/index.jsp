<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>SNF 재고 관리 시스템</title>
<script>
function link0() {
	document.getElementById("pageName").value="createMaterial";
	doMovePage();
}
function link1() {
	document.getElementById("pageName").value="storing";
	doMovePage();
}
function link2() {
	document.getElementById("pageName").value="unstoring";
	doMovePage();
}
function link3() {
	document.getElementById("pageName").value="inventoryCheck";
	doMovePage();
}
function link4() {
	document.getElementById("pageName").value="historyCheck";
	doMovePage();
}
function link5() {
	document.getElementById("pageName").value="codeManage";
	doMovePage();
}
function doMovePage(){
	document.getElementById("form").submit();
}
</script>
</head>
<body>
 <form id="form" method="post" action="/view/viewPage">
 	<input type="hidden" id="pageName" name="pageName" /> 
	<button onclick="link0();">신규 자재 추가</button>
	<br/>
	<button onclick="link1();">입고 등록</button>
	<br/>
	<button onclick="link2();">출고 등록</button>
	<br/>
	<button onclick="link3();">현재 재고 조회</button>
	<br/>
	<button onclick="link4();">이력 조회</button>
	<br/>
	<button onclick="link5();">코드 관리</button>
</form>
</body>
</html>