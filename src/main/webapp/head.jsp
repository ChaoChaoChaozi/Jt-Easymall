<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<link rel="stylesheet" href="${app }/css/head.css"/>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<script type="text/javascript">
function search(a) {
    var b = "http://www.jt.com/search.html?q=" + encodeURIComponent(document.getElementById(a).value);
    return window.location.href = b;
}
</script>
<div id="common_head">
	<div id="line1">
	<div id="content">
	<c:if test="${empty sessionScope.userName}" var="eptun" scope="page">
	<a href="${app }/page/login">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="${app}/page/regist">注册</a>
	<%-- <a href="${app }/login.jsp">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="${app}/regist.jsp">注册</a> --%>
	</c:if>
	<c:if test="${!eptun}">
	欢迎${sessionScope.userName}&nbsp;&nbsp;${sessionScope.userId}<a id="logout_a" href="${app}/user_ajax/logout">注销</a>
	</c:if>
	</div>
	</div>
	<div id="line2">
		<img id="logo" src="${app }/img/head/logo.jpg"/>
		<input type="text" value="${q }" accesskey="s" id="key" autocomplete="off" onkeydown="javascript:if(event.keyCode==13) search('key');"/>
		<input type="button" value="搜 索" onclick="search('key');return false;"/>
		<%-- <input type="text" value="${q }" class="text" accesskey="s" id="key" autocomplete="off" onkeydown="javascript:if(event.keyCode==13) search('key');">
		 --%><!-- <input type="button" value="搜索" class="button" onclick="search('key');return false;" clstag="homepage|keycount|home2013|03a">
		 --><span id="goto">
			<a id="goto_order" href="${app}/order/list">我的订单</a>
			<a id="goto_cart" href="${app}/cart/mycart">我的购物车</a>
		</span>
		<img id="erwm" src="${app }/img/head/qr.jpg"/>
	</div>
	<div id="line3">
		<div id="content">
			<ul>
				<li><a href="#">首页</a></li>
				<li><a href="${app}/product/page/?currentPage=1&rows=5">全部商品</a></li>
				<li><a href="#">手机数码</a></li>
				<li><a href="#">电脑平板</a></li>
				<li><a href="#">家用电器</a></li>
				<li><a href="#">汽车用品</a></li>
				<li><a href="#">食品饮料</a></li>
				<li><a href="#">图书杂志</a></li>
				<li><a href="#">服装服饰</a></li>
				<li><a href="#">理财产品</a></li>
			</ul>
		</div>
	</div>
</div>
