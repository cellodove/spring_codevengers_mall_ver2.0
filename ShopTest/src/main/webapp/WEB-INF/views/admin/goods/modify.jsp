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

.select_img img{width: 500px; margin: 20px 0;}
</style>

<script src="/resources/jquery/jquery-1.11.2.min.js"></script>

<link rel="stylesheet" href="/resources/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="/resources/bootstrap/bootstrap-theme.min.css">
<script src="/resources/bootstrap/bootstrap.min.js"></script>

<script src="/resources/ckeditor/ckeditor.js"></script>
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
<H2>상품 수정</H2>
<form role="form" method="post" autocomplete="off" enctype="multipart/form-data">
<input type="hidden" name="gdsnum" value="${goods.gdsnum}"/>
<div class="inputArea"> 
 <label>1차 분류</label>
 <select class="category1">
  <option value="">전체</option>
 </select>

 <label>2차 분류</label>
 <select class="category2" name="catecode">
  <option value="">전체</option>
 </select>
</div>

<div class="inputArea">
 <label for="gdsname">상품명</label>
 <input type="text" id="gdsname" name="gdsname" value="${goods.gdsname}"/>
</div>

<div class="inputArea">
 <label for="gdsprice">상품가격</label>
 <input type="text" id="gdsprice" name="gdsprice" value="${goods.gdsprice}"/>
</div>

<div class="inputArea">
 <label for="gdsstock">상품수량</label>
 <input type="text" id="gdsstock" name="gdsstock" value="${goods.gdsstock}"/>
</div>

<div class="inputArea">
 <label for="gdsdes">상품소개</label>
 <textarea rows="5" cols="50" id="gdsdes" name="gdsdes">${goods.gdsdes}</textarea>

<script>
 var ckeditor_config = {
   resize_enaleb : false,
   enterMode : CKEDITOR.ENTER_BR,
   shiftEnterMode : CKEDITOR.ENTER_P,
   filebrowserUploadUrl : "/admin/goods/ckUpload"
 };
 
 CKEDITOR.replace("gdsdes", ckeditor_config);
</script>

</div>

<div class="inputArea">
 <label for="gdsimg">이미지</label>
 <input type="file" id="gdsimg" name="file" />
 <div class="select_img">
 <img src='<spring:url value="/resources/${goods.gdsimg}"/>'>
 <input type="hidden" name="gdsimg" value="${goods.gdsimg}"/>
 <input type="hidden" name="gdsthumbimg" value="${goods.gdsthumbimg}"/>
 </div>
 
 <script>
  $("#gdsimg").change(function(){
   if(this.files && this.files[0]) {
    var reader = new FileReader;
    reader.onload = function(data) {
     $(".select_img img").attr("src", data.target.result).width(500);        
    }
    reader.readAsDataURL(this.files[0]);
   }
  });
 </script>
 
 <%=request.getRealPath("/") %>
 
</div>

<div class="inputArea">
 <button type="submit" id="update_Btn" class="btn btn-primary">완료</button>
 <button type="button" id="back_Btn" class="btn btn-warning">취소</button>
 <script>
 $("#back_Btn").click(function(){
  //history.back();
  location.href = "/admin/goods/view?n=" + ${goods.gdsnum};
 });   
</script>
</div>
</form>

</div>
</section>

<footer id="footer">
<div id="footer_box">
<%@ include file="../include/footer.jsp" %>
</div>
</footer>
</div>

<script>
// 컨트롤러에서 데이터 받기

var jsonData = JSON.parse('${category}');
console.log(jsonData);

var cate1Arr = new Array();
var cate1Obj = new Object();

// 1차 분류 셀렉트 박스에 삽입할 데이터 준비
for(var i = 0; i < jsonData.length; i++) {
 
 if(jsonData[i].level == "1") {
  cate1Obj = new Object();  //초기화
  cate1Obj.catecode = jsonData[i].catecode;
  cate1Obj.catename = jsonData[i].catename;
  cate1Arr.push(cate1Obj);
 }
}

// 1차 분류 셀렉트 박스에 데이터 삽입
var cate1Select = $("select.category1")

for(var i = 0; i < cate1Arr.length; i++) {
 cate1Select.append("<option value='" + cate1Arr[i].catecode + "'>"
      + cate1Arr[i].catename + "</option>"); 
}

$(document).on("change", "select.category1", function(){

	 var cate2Arr = new Array();
	 var cate2Obj = new Object();
	 
	 // 2차 분류 셀렉트 박스에 삽입할 데이터 준비
	 for(var i = 0; i < jsonData.length; i++) {
	  
	  if(jsonData[i].level == "2") {
	   cate2Obj = new Object();  //초기화
	   cate2Obj.catecode = jsonData[i].catecode;
	   cate2Obj.catename = jsonData[i].catename;
	   cate2Obj.catecoderef = jsonData[i].catecoderef;
	   
	   cate2Arr.push(cate2Obj);
	  }
	 }
	 
	 var cate2Select = $("select.category2");
	 
	 /*
	 for(var i = 0; i < cate2Arr.length; i++) {
	   cate2Select.append("<option value='" + cate2Arr[i].cateCode + "'>"
	        + cate2Arr[i].cateName + "</option>");
	 }
	 */
	 
	 cate2Select.children().remove();

	 $("option:selected", this).each(function(){
	  
	  var selectVal = $(this).val();  
	  cate2Select.append("<option value='" + selectVal + "'>전체</option>");
	  
	  for(var i = 0; i < cate2Arr.length; i++) {
	   if(selectVal == cate2Arr[i].catecoderef) {
	    cate2Select.append("<option value='" + cate2Arr[i].catecode + "'>"
	         + cate2Arr[i].catename + "</option>");
	   }
	  }
	  
	 });
	 
	});
	
var select_catecode = '${goods.catecode}';
var select_catecoderef = '${goods.catecoderef}';
var select_catename = '${goods.catename}';

if(select_catecoderef != null && select_catecoderef != '') {
 $(".category1").val(select_catecoderef);
 $(".category2").val(select_catecode);
 $(".category2").children().remove();
 $(".category2").append("<option value='"
       + select_catecode + "'>" + select_catename + "</option>");
} else {
 $(".category1").val(select_catecode);
 //$(".category2").val(select_catecode);
 // select_cateCod가 부여되지 않는 현상이 있어서 아래 코드로 대체
 //$(".category2").append("<option value="'
//		+ select_catecode + '" selected='selected'>전체</option>");
 $(".category2").append("<option value='"
	       + select_catecode + "'>" + select_catename + "</option>");
}
</script>
<script>
//상품가격과 상품수량에 숫자가 아닌 문자가 들어가면 바로즉시 삭제
var regExp = /[^0-9]/gi;

$("#gdsprice").keyup(function(){ numCheck($(this)); });
$("#gdsstock").keyup(function(){ numCheck($(this)); });

function numCheck(selector) {
 var tempVal = selector.val();
 selector.val(tempVal.replace(regExp, ""));
}
</script>
</body>
</html>