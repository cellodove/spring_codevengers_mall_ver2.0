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
 section#content ul li { display:inline-block; margin:10px; }
 section#content div.goodsThumb img { width:200px; height:200px; }
 section#content div.goodsName { padding:10px 0; text-align:center; }
 section#content div.goodsName a { color:#000; }
*/
 .orderInfo { border:5px solid #eee; padding:10px 20px; margin:20px 0;}
 .orderInfo span { font-size:20px; font-weight:bold; display:inline-block; width:90px; }
 
 .orderView li { margin-bottom:20px; padding-bottom:20px; border-bottom:1px solid #999; }
 .orderView li::after { content:""; display:block; clear:both; }
 
 .thumb { float:left; width:200px; }
 .thumb img { width:200px; height:200px; }
 .gdsInfo { float:right; width:calc(100% - 220px); line-height:2; }
 .gdsInfo span { font-size:20px; font-weight:bold; display:inline-block; width:100px; margin-right:10px; }
 .deliveryChange { text-align:right; }
.delivery1_btn,
.delivery2_btn { font-size:16px; background:#fff; border:1px solid #999; margin-left:10px; }

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
<div class="orderInfo">
  <c:forEach items="${adminOrderView}" var="adminOrderView" varStatus="status">
  
  <c:if test="${status.first}">
   <p><span>주문자</span>${adminOrderView.mem_id}</p>
   <p><span>수령인</span>${adminOrderView.orderrec}</p>
   <p><span>주소</span>(${adminOrderView.useraddr1}) ${adminOrderView.useraddr2} ${adminOrderView.useraddr3}</p>
   <p><span>가격</span><fmt:formatNumber pattern="###,###,###" value="${adminOrderView.amount}" /> 원</p>
   <p><span>주문상태</span>${adminOrderView.delivery}</p>
   <div class="deliveryChange">
 <form role="form" method="post" class="deliveryForm">
 
  <input type="hidden" name="orderid" value="${adminOrderView.orderid}" />
  <input type="hidden" name="delivery" class="delivery" value="" />
  
  <button type="button" class="delivery1_btn">배송중</button>
  <button type="button" class="delivery2_btn">배송완료</button>
  
  <script>
   $(".delivery1_btn").click(function(){
    $(".delivery").val("배송중");
    run();
   });
   
   $(".delivery2_btn").click(function(){
    $(".delivery").val("배송완료");
    run();
    
   });
   
   function run(){
    $(".deliveryForm").submit();
   }
  
  </script>
 </form>
</div>
  </c:if>
  
 </c:forEach>
</div>

<ul class="adminOrderView">
 <c:forEach items="${adminOrderView}" var="adminOrderView">     
 <li>
  <div class="thumb">
   <img src="${adminOrderView.gdsthumbimg}" />
  </div>
  <div class="gdsInfo">
   <p>
    <span>상품명</span>${adminOrderView.gdsname}<br />
    <span>개당 가격</span><fmt:formatNumber pattern="###,###,###" value="${adminOrderView.gdsprice}" /> 원<br />
    <span>구입 수량</span>${adminOrderView.cartstock} 개<br />
    <span>최종 가격</span><fmt:formatNumber pattern="###,###,###" value="${adminOrderView.gdsprice * adminOrderView.cartstock}" /> 원                  
   </p>
  </div>
 </li>     
 </c:forEach>
</ul>
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