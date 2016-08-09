<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../include/header.jsp"%>
<%@include file="./sidebar.jsp"%>
<style>
	.reservationInfo{
		height:20px; 
		width: 56px; 
		display: block; 
		margin-left: 10px; 
		margin-bottom: 5px; 
		font-weight: bold; 
		float: left;
	}
</style>
<fmt:formatDate value="${today}" pattern="yyyy-MM-dd HH:mm" var="today"/>
<div class="content" style="margin-left: 120px; width: 890px;">
	<div id="myInfo" style="border: 1px solid #aaa; height: 150px; width: 100%; margin-bottom: 10px; border-radius: 15px;">
		<div style="height: 46px; margin: 10px; line-height: 46px;">
			<h3 style="margin-top: 10px; display: inline-block;">${userInfo.member_name}님 등급은 <p style="display: inline; color: red;">${userInfo.grade}</p>입니다</h3>
		</div>
		<div id="coupon" style="border-top: 1px solid #aaa; width: 444px; height:82px; float: left; padding-left: 20px;">
			<span style="display: block; width:110px; margin: 10px; margin-left: 0; border: 2px dashed red; border-radius: 15px; text-align: center;"><h4 style="margin: 5px;">COUPON</h4></span>
			<span>사용 가능 쿠폰 : ${couponTotal}장</span>
		</div>
		<div id="point" style="border-top: 1px solid #aaa; border-left: 1px solid #aaa; width: 444px; height:82px; float: right; padding-left: 20px;">
			<span style="display: block; width:110px; margin: 10px; margin-left: 0; border: 2px dashed red; border-radius: 15px; text-align: center;"><h4 style="margin: 5px;">POINT</h4></span>
			<span>멤버십 포인트 : ${userInfo.point}점</span>
		</div>
	</div>


	<div style="height: 34px; line-height: 34px;">
		<span style="font-size: 20px; font-weight: bold;">예매내역</span><a style="margin-left: 5px;" href="/user/reservation"><span id="reservationTotal">${reservationTotal}건</span><span class="glyphicon glyphicon-plus-sign"></span></a>
	</div>
	<div id="reservationList" style="border: 1px solid #aaa; border-radius: 15px;">
		<div style="width: 888px; height:50px; line-height: 50px; border-bottom: 1px solid #aaa;">
			<div style="width: 200px; text-align: center; font-size: 15px; border-right: 1px solid #aaa; float: left; font-weight: bold;">예매번호</div>
			<div style="width: 688px; text-align: center; font-size: 15px; margin-left: 200px; font-weight: bold;">예매정보</div>
		</div>
		<c:forEach items="${reservation.reservationList}" var="r" begin="0" end="2" step="1">
			<div style="height: 130px; border-top: 1px solid #aaa;">
				<span style="width: 200px; display: block; line-height: 130px; float: left; text-align: center; border-right: 1px solid #aaa;">${r.reservation_code}</span>
				<c:forEach items="${reservation.reservationMovie}" var="rm">
					<c:if test="${r.movie_id == rm.movie_id }">
						<div style="height: 130px; width: 400px; float: left;">
							<span style="height: 128px; width: 105px; display: block; margin: 0; float: left;"><a href="/movie/${r.movie_id}"><img style="height: 100%; width: 100%;" src="/resources/images/poster/${rm.poster}"></a></span>
							<span style="height:30px; display: block; margin-left: 115px; margin-bottom: 5px; padding-top:10px; font-weight: bold;"><a href="/movie/${r.movie_id}">${rm.title}</a></span>
							<span class="reservationInfo">관람극장</span>
							<span style="height:20px; display: block; margin-left: 181px; margin-bottom: 5px;">
							<c:forEach items="${reservation.reservationTheater}" var="rt">
								<c:if test="${rt.theater_id eq r.theater_id}">
									${rt.theater_name}
								</c:if>
							</c:forEach>
							&nbsp${r.plex_number}관&nbsp/&nbsp${r.ticket_cnt}명</span>
							<span class="reservationInfo">관람좌석</span>
							<span style="height:20px; display: block; margin-left: 181px; margin-bottom: 5px;">${r.view_seat}</span>
							<span class="reservationInfo">관람일시</span>
							<span style="height:20px; display: block; margin-left: 181px; margin-bottom: 5px;">${r.start_time}</span>
							<span class="reservationInfo">결제</span>
							<span style="height:20px; display: block; margin-left: 181px;">${r.pay_method} &nbsp/&nbsp ${r.pay}원</span>
						</div>
						<div style="height: 130px; line-height: 130px; text-align: center; float: left; width: 288px;">
							<c:if test="${r.start_time gt today}">
								<span style="display: inline-block; margin-top: 48px; margin-left: 20px;"><button class="btn btn-danger" onclick="deleteReservation('${r.reservation_code}')">예매취소</button></span>
							</c:if>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</c:forEach>
	</div>
</div>
<script>
	function setReservationList(r,rm,rt){
		var a = $("#reservationList");
		var result = "";
		var date = null;
		a.html("");
		
		result += '<div style="width: 888px; height:50px; line-height: 50px; border-bottom: 1px solid #aaa;">'
				+ '<div style="width: 200px; text-align: center; font-size: 15px; border-right: 1px solid #aaa; float: left; font-weight: bold;">예매번호</div>'
				+ '<div style="width: 688px; text-align: center; font-size: 15px; margin-left: 200px; font-weight: bold;">예매정보</div>'
				+ '</div>';
		
		
		$(r).each(function(i){
			if(i<3){
				result += '<div style="height: 130px; border-top: 1px solid #aaa;">'
						+ '<div style="width: 200px; line-height: 130px; float: left; text-align: center; border-right: 1px solid #aaa;">'
						+ '<span>' + this.reservation_code + '</span>'
						+ '</div>';
						
				for(var j=0; j<rm.length; j++){
					if(this.movie_id == rm[j].movie_id){
						
						result += '<div style="height: 130px; width: 400px; float: left;">'
								+ '<span style="height: 128px; width: 105px; display: block; margin: 0; float: left;"><a href="/movie/${r.movie_id}"><img style="height: 100%; width: 100%; vertical-align: baseline;" src="/resources/images/poster/'+rm[j].poster+'"></a></span>'
								+ '<span style="height:30px; display: block; margin-left: 115px; margin-bottom: 5px; padding-top:10px; font-weight: bold;"><a href="/movie/' + this.movie_id + '">' + rm[j].title + '</a></span>'
								+ '<span class="reservationInfo">관람극장</span>'
								+ '<span style="height:20px; display: block; margin-left: 181px; margin-bottom: 5px;">';
						for(var k=0; k<rt.length; k++){
							if(rt[k].theater_id == this.theater_id){
								result += rt[k].theater_name;
							}
						}
						result += '&nbsp' + this.plex_number + '관&nbsp/&nbsp' + this.ticket_cnt + '명</span>'
								+ '<span class="reservationInfo">관람좌석</span>'
								+ '<span style="height:20px; display: block; margin-left: 181px; margin-bottom: 5px;">' + this.view_seat + '</span>'
								+ '<span class="reservationInfo">관람일시</span>'
								+ '<span style="height:20px; display: block; margin-left: 181px; margin-bottom: 5px;">' + this.start_time + '</span>'
								+ '<span class="reservationInfo">결제</span>'
								+ '<span style="height:20px; display: block; margin-left: 181px;">'+ this.pay_method + '&nbsp/&nbsp' + this.pay + '원</span>'
								+ '</div>'
								+ '<div style="height: 130px; line-height: 130px; width: 288px; text-align: center; float: left;">';
						
						if(this.start_time > "${today}"){
							result += '<span style="display: inline-block; margin-top: 48px; margin-left: 20px;"><button class="btn btn-danger" onclick="deleteReservation(' + this.reservation_code+ ')">예매취소</button></span>';
						}
						
						result += '</div>';
					}
					
				}
				
					result += '</div>';
			}
		});
	
		a.html(result);
	}
	
	function getReservationList(){
	
		$.ajax({
			type : 'get',
			url : '/user/reservation/1',
			headers : {
				"Content-Type" : "application/json"
	//			"X-HTTP-Method-Override" : "GET",
			},
			dataType : 'json',
			data : '',
			success : function(result){
				setReservationList(result.l.reservationList, result.l.reservationMovie, result.l.reservationTheater);
				$("#reservationTotal").html("");
				$("#reservationTotal").html(result.total+"건");
			}
		});
		
	}
	
	function deleteReservation(reservation_code){
		
		$.ajax({
			type : 'delete',
			url : '/user/reservation/'+reservation_code,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
			},
			success : function(result){
				if(result == "SUCCESS"){
					getReservationList();
				}
			}
		});
		
	}
</script>
<% session.setAttribute("menu", null);%>
<%@include file="../include/footer.jsp"%>