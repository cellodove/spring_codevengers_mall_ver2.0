<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
	<title>SHOP</title>
	
	
	<style>
	
		body { margin:0; padding:0; font-family:'맑은 고딕', verdana; }
		a { color:#05f; text-decoration:none; }
		a:hover { text-decoration:underline; }
		
		h1, h2, h3, h4, h5, h6 { margin:0; padding:0; }
		ul, lo, li { margin:0; padding:0; list-style:none; }
	
		/* ---------- */
		
		div#root { width:900px; margin:0 auto; }
		header#header {}
		nav#nav {}
		section#container { }
			section#content { float:right; width:700px; }
			aside#aside { float:left; width:180px; }
			section#container::after { content:""; display:block; clear:both; }	
		footer#footer { background:#eee; padding:20px; }
		
		/* ---------- */
		
		header#header div#header_box { text-align:center; padding:30px 0; }
		header#header div#header_box h1 { font-size:50px; }
		header#header div#header_box h1 a { color:#000; }
		
		nav#nav div#nav_box { font-size:14px; padding:10px; text-align:right; }
		nav#nav div#nav_box li { display:inline-block; margin:0 10px; }
		nav#nav div#nav_box li a { color:#333; }
		
		section#container { }
		
		aside#aside h3 { font-size:22px; margin-bottom:20px; text-align:center; }
		aside#aside li { font-size:16px; text-align:center; }
		aside#aside li a { color:#000; display:block; padding:10px 0; }
		aside#aside li a:hover { text-decoration:none; background:#eee; }
		
		aside#aside li { position:relative; }
		aside#aside li:hover { background:#eee; } 		
		aside#aside li > ul.low { display:none; position:absolute; top:0; left:180px;  }
		aside#aside li:hover > ul.low { display:block; }
		aside#aside li:hover > ul.low li a { background:#eee; border:1px solid #eee; }
		aside#aside li:hover > ul.low li a:hover { background:#fff;}
		aside#aside li > ul.low li { width:180px; }
		
		footer#footer { margin-top:100px; border-radius:50px 50px 0 0; }
		footer#footer div#footer_box { padding:0 20px; }
		
	</style>
	
	<style>
 div.goods div.goodsImg { float:left; width:350px; }
 div.goods div.goodsImg img { width:350px; height:auto; }
 
 div.goods div.goodsInfo { float:right; width:330px; font-size:22px; }
 div.goods div.goodsInfo p { margin:0 0 20px 0; }
 div.goods div.goodsInfo p span { display:inline-block; width:100px; margin-right:15px; }
 
 div.goods div.goodsInfo p.cartStock input { font-size:22px; width:50px; padding:5px; margin:0; border:1px solid #eee; }
 div.goods div.goodsInfo p.cartStock button { font-size:26px; border:none; background:none; }
 div.goods div.goodsInfo p.addToCart { text-align:right; }
 div.goods div.gdsDes { font-size:18px; clear:both; padding-top:30px; }
</style>

<style>
 section.replyForm { padding:30px 0; }
 section.replyForm div.input_area { margin:10px 0; }
 section.replyForm textarea { font-size:16px; font-family:'맑은 고딕', verdana; padding:10px; width:500px;; height:150px; }
 section.replyForm button { font-size:20px; padding:5px 10px; margin:10px 0; background:#fff; border:1px solid #ccc; }
 
 section.replyList { padding:30px 0; }
 section.replyList ol { padding:0; margin:0; }
 section.replyList ol li { padding:10px 0; border-bottom:2px solid #eee; }
 section.replyList div.userInfo { }
 section.replyList div.userInfo .username { font-size:24px; font-weight:bold; }
 section.replyList div.userInfo .date { color:#999; display:inline-block; margin-left:10px; }
 section.replyList div.replyContent { padding:10px; margin:20px 0; }
 
 section.replyList div.replyFooter button { font-size:14px; border: 1px solid #999; background:none; margin-right:10px; }
</style>

<style>
 div.replyModal { position:relative; z-index:1; display: none;}
 div.modalBackground { position:fixed; top:0; left:0; width:100%; height:100%; background:rgba(0, 0, 0, 0.8); z-index:-1; }
 div.modalContent { position:fixed; top:20%; left:calc(50% - 250px); width:500px; height:250px; padding:20px 10px; background:#fff; border:2px solid #666; }
 div.modalContent textarea { font-size:16px; font-family:'맑은 고딕', verdana; padding:10px; width:500px; height:200px; }
 div.modalContent button { font-size:20px; padding:5px 10px; margin:10px 0; background:#fff; border:1px solid #ccc; }
 div.modalContent button.modal_cancel { margin-left:20px; }

 .orderInfo { border:5px solid #eee; padding:20px; display: none;}
.orderInfo .inputArea { margin:10px 0; }
.orderInfo .inputArea label { display:inline-block; width:120px; margin-right:10px; }
.orderInfo .inputArea input { font-size:14px; padding:5px; }
#useraddr2, #useraddr3 { width:250px; }

.orderInfo .inputArea:last-child { margin-top:30px; }
.orderInfo .inputArea button { font-size:20px; border:2px solid #ccc; padding:5px 10px; background:#fff; margin-right:20px;}
 
 .orderInfo .inputArea #sample2_address { width:230px; }
.orderInfo .inputArea #sample2_detailAddress { width:280px; }
.orderInfo .inputArea #sample2_extraAddress { display:none; }
</style>

	<script src="/resources/jquery/jquery-1.11.2.min.js"></script>
	
					<script> 
				function replyList() {
 var gdsnum = ${review.gdsnum};
 $.getJSON("/shop/view/replyList" + "?n=" + gdsnum, function(data){
  var str = "";
  
  $(data).each(function(){
   
   console.log(data);
   
   var repdate = new Date(this.repdate);
   repdate = repdate.toLocaleDateString("ko-US")
   
   str += "<li data-repnum='" + this.repnum + "'>"
     + "<div class='userInfo'>"
     + "<span class='username'>" + this.username + "</span>"
     + "<span class='date'>" + repdate + "</span>"
     + "</div>"
     + "<div class='replyContent'>" + this.repcon + "</div>"
     
     + "<c:if test='${member != null}'>"
     
     + "<div class='replyFooter'>"
	 + "<button type='button' class='modify' data-repnum='" + this.repnum + "'>수정</button>"
	 + "<button type='button' class='delete' data-repnum='" + this.repnum + "'>삭제</button>"
	 + "</div>"
     
     + "</c:if>"
     
     + "</li>";           
  });
  
  $("section.replyList ol").html(str);
 });
 }
</script>
	
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
		<div id="container_box">
		
			<section id="content">
			<form role="form" method="post">
				<input type="hidden" name="gdsnum" value="${review.gdsnum}">
			</form>
			<div class="goods">
			<div class="goodsimg">
				<img src='<spring:url value="/resources/${review.gdsimg}"/>'>
			</div>
			<div class="goodsinfo">
			<p class="gdsname"><span>상품명</span>${review.gdsname}</p>
			</div>
			</div>
			
			
			<div id="reply">
			
			
			
			
			 <section class="replyForm">
			
				<form role="form" method="post" autocomplete="off">
				
				<input type="hidden" name="gdsnum" id="gdsnum" value="${review.gdsnum}">
				 <div class="input_area">
				<textarea name="repcon" id="repcon"></textarea>
				</div>
				<div class="input_area">
				<button type="button" id="reply_btn">소감 남기기</button>
				
				<script>
				$("#reply_btn").click(function() {
					var formObj = $(".replyForm form[role='form']");
					var gdsnum = $("#gdsnum").val();
					var repcon = $("#repcon").val();
					
					var data = {
						gdsnum : gdsnum,
						repcon : repcon
						};
					$.ajax({
						url : "/shop/view/registReply",
						type : "post",
						data : data,
						success : function(){
							replyList();
							$("#repcon").val("");
						}
					});
				});
				</script>
				
				</div>
				
				</form>
				
				</section> 
				
			
				
				
				<section class="replyList">
				<ol>
				<%-- <c:forEach items="${reply}" var="reply">
				<li>
				<div class="userInfo">
				<span class="username">${reply.username}</span>
				<span class="date"><fmt:formatDate value="${reply.repdate}" pattern="yyyy-MM-dd"/></span>
				</div>
				<div class="replyContent">${reply.repcon}</div>
				</li>
				</c:forEach> --%>
				</ol>
				
<script>
replyList();
</script>

<script>
$(document).on("click", ".modify", function() {
	
	$(".replyModal").fadeIn(200);
	var repnum = $(this).attr("data-repnum");
	var repcon = $(this).parent().parent().children(".replyContent").text();
	$(".modal_repcon").val(repcon);
	$(".modal_modify_btn").attr("data-repnum", repnum);
});

$(document).on("click", ".delete", function(){
	var deleteConfirm = confirm("정말로 삭제하시겠습니까?");
	if(deleteConfirm) {
	var data = { repnum : $(this).attr("data-repnum")};
	$.ajax({
		url : "/shop/view/deleteReply",
		type : "post",
		data : data,
		success : function(result){
  
		if(result == 1) {
			replyList();
		} else {
			alert("작성자 본인만 할 수 있습니다.");    
  		}
 		},
 		error : function(){
  		alert("로그인하셔야합니다.")
		}
		});
	}
});
</script>
				
				</section>
				
			</div>
			
			</section>
			
			<aside id="aside">
				<%@ include file="../include/aside.jsp" %>
			</aside>
			
		</div>
	</section>

	<footer id="footer">
		<div id="footer_box">
			<%@ include file="../include/footer.jsp" %>
		</div>		
	</footer>

</div>

<div class="replyModal">
 <div class="modalContent">
  <div>
   <textarea class="modal_repcon" name="modal_repcon"></textarea>
  </div>

  <div>
   <button type="button" class="modal_modify_btn">수정</button>
   <button type="button" class="modal_cancel">취소</button>
  </div>
  
 </div>
 <div class="modalBackground"></div>
</div>

<script>
$(".modal_modify_btn").click(function(){
 var modifyConfirm = confirm("정말로 수정하시겠습니까?");
 
 if(modifyConfirm) {
  var data = {
     repnum : $(this).attr("data-repnum"),
     repcon : $(".modal_repcon").val()
    }; 
  
  $.ajax({
   url : "/shop/view/modifyReply",
   type : "post",
   data : data,
   success : function(result){
    
    if(result == 1) {
     replyList();
     $(".replyModal").fadeOut(200);
    } else {
     alert("작성자 본인만 할 수 있습니다.");       
    }
   },
   error : function(){
    alert("로그인하셔야합니다.")
   }
  });
 }
 
});
</script>

</body>
</html>