<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header.jsp"%>
<%@include file="./sidebar.jsp"%>
<c:if test="${ member_id == null }">
	<script>
		<%response.sendRedirect("/login");%>
	</script>
</c:if>
<div class="content" style="margin-left: 250px; width: 890px;">
	<div id="myInfo" style="border: 1px solid #000; height: 150px; width: 100%; margin-bottom: 10px;">
		<div style="height: 46px; margin: 10px; line-height: 46px;">
			<h3 style="margin-top: 10px; display: inline;">${userInfo.member_name}님 등급은 <p style="display: inline; color: red;">${userInfo.grade}</p>입니다</h3>
		</div>
		<div id="coupon" style="border-top: 1px solid #000; width: 444px; height:82px; float: left; padding-left: 20px;">
			<h4 style="margin: 10px;">COUPON</h4>
			<span>사용 가능 쿠폰 : ${couponTotal}장</span>
		</div>
		<div id="point" style="border-top: 1px solid #000; border-left: 1px solid #000; width: 444px; height:82px; float: right; padding-left: 20px;">
			<h4 style="margin: 10px;">POINT</h4>
			<span>사용 가능 포인트 : ${userInfo.point}점</span>
		</div>
	</div>


	<div style="height: 34px; line-height: 34px;">
		<span style="font-size: 20px;">예매내역</span><a style="margin-left: 5px;" href="/user/reservation">0건<span class="glyphicon glyphicon-plus-sign"></span></a>
	</div>
	<div id="reservationList" style="height: 200px; border: 1px solid #000;">
		<c:forEach items="${reservationList}" var="r">
			<div>
				<span style="">${r.reservation_code}</span>
			</div>
		</c:forEach>
	</div>
</div>

<%@include file="../include/footer.jsp"%>