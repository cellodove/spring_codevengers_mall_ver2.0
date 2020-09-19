<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h3>카테고리</h3>

<ul>
<!-- /shop/list 는 기본 경로이며, 뒤의 ?c=[번호1]&l=[번호2] 는 구분자입니다. -->
<li><a href="/shop/list?c=100&l=1">남성신발</a>
	<ul class="low">
		<li><a href="/shop/list?c=101&l=2">스니커즈</a></li>
		<li><a href="/shop/list?c=102&l=2">슈즈</a></li>
	</ul>
</li>
<li><a href="/shop/list?c=200&l=1">여성신발</a></li>
</ul>