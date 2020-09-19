<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WALNUT ADMiN</title>

<style>
 body { font-family:'맑은 고딕', verdana; padding:0; margin:0; }
 ul { padding:0; margin:0; list-style:none;  }

 div#root { width:90%; margin:0 auto; }
 
 header#header { font-size:60px; padding:20px 0; }
 header#header h1 a { color:#000; font-weight:bold; }
 
 nav#nav { padding:10px; text-align:right; }
 nav#nav ul li { display:inline-block; margin-left:10px; }

   section#container { padding:20px 0; border-top:2px solid #eee; border-bottom:2px solid #eee; }
 section#container::after { content:""; display:block; clear:both; }
 aside { float:left; width:200px; }
 div#container_box { float:right; width:calc(100% - 200px - 20px); }
 
 aside ul li { text-align:center; margin-bottom:10px; }
 aside ul li a { display:block; width:100%; padding:10px 0;}
 aside ul li a:hover { background:#eee; }
 
 footer#footer { background:#f9f9f9; padding:20px; }
 footer#footer ul li { display:inline-block; margin-right:10px; }
</style>

<style>
.inputArea { margin:10px 0; }
select { width:100px; }
label { display:inline-block; width:70px; padding:5px; }
label[for='gdsDes'] { display:block; }
input { width:150px; }
textarea#gdsDes { width:400px; height:180px; }
</style>

<style>
#container_box table td { width:100px; }
</style>

<style>
/*
#container_box table { width:900px; }
#container_box table th { font-size:20px; font-weight:bold;
       text-align:center; padding:10px; border-bottom:2px solid #666; }
#container_box table tr:hover { background:#eee; }
#container_box table td { padding:10px; text-align:center; }
#container_box table img { width:150px; height:auto; }
*/
 div#container_box ul li { border:5px solid #eee; padding:10px 20px; margin-bottom:20px; }
 div#container_box .adminOrderList span { font-size:20px; font-weight:bold; display:inline-block; width:90px; margin-right:10px; }
</style>

<script src="/resources/jquery/jquery-1.11.2.min.js"></script>

<link rel="stylesheet" href="/resources/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="/resources/bootstrap/bootstrap-theme.min.css">
<script src="/resources/bootstrap/bootstrap.min.js"></script>
</head>
<body>

<div id="root">
<header id="header">
<div id="header_box">
<%@ include file="../include/header.jsp" %>
</div>
</header>

<nav id="nav">
<div id="nav_box">
<%@ include file="../include/nav.jsp" %>
</div>
</nav>

<section id="container">
<aside>
<%@ include file="../include/aside.jsp" %>
</aside>
<div id="container_box">
<section id="content">
<ul class="adminOrderList">
 <c:forEach items="${adminOrderList}" var="adminOrderList">
 <li>
 <div>
  <p><span>주문번호</span><a href="/admin/shop/adminOrderView?n=${adminOrderList.orderid}">${adminOrderList.orderid}</a></p>
  <p><span>주문자</span>${adminOrderList.mem_id}</p>
  <p><span>수령인</span>${adminOrderList.orderrec}</p>
  <p><span>주소</span>(${adminOrderList.useraddr1}) ${adminOrderList.useraddr2} ${adminOrderList.useraddr3}</p>
  <p><span>가격</span><fmt:formatNumber pattern="###,###,###" value="${adminOrderList.amount}" /> 원</p> 
  <p><span>주문상태</span>${adminOrderList.delivery}</p>
 </div>
 </li>
 </c:forEach>
</ul>
</section>
</div>
</section>

<footer id="footer">
<div id="footer_box">
<%@ include file="../include/footer.jsp" %>
</div>
</footer>
</div>
</body>
</html>