<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="./include/header.jsp" %>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.0.js"></script>
	<style>
<!--
	
	.content1 {
		border-left:2px solid #999;
		border-right:2px solid #999;
		background-color: #F2F0E4;
		padding-top: 15px;
		float: left;
		height: 420px;
		overflow: scroll;
	}
	
	#menu1{
		width: 1140px;
		height: 40px;
	}
	
	#content1{
		width: 1140px;
		height: 420px;
		clear: both;
	}
	
	.menu1{
		border-left:2px solid #999;
		border-right:2px solid #999;
		background-color: #404040;
		color: rgba(255,255,255,0.75);
		text-align: center;
		font-size: 13pt;
		padding: 5px;
		float:left;
		height: 40px;
	}
	
	#menu1-1, #content1-1 {
		width: 27%;
	}
	
	#content1-1>div{
		
		clear:none;
		margin-left:20px;
		margin-top:15px;
	}
	
	#menu1-2, #content1-2 {
		width: 28%;
	}
	
	#menu1-3, #content1-3 {
		width: 19%;		
	}
	
	#menu1-4, #content1-4 {
		width: 26%;
	}
	
	#title2-1 {
		 color: #fff;
		 font-weight: bolder;
		 width: 400px;
		 padding-left: 20px;
	}
	
	#title2-2 {
		 color: rgba(230,230,230,0.75);
		 padding-left: 120px;
		 font-size: 14pt;
		 width: 400px;
	}
	
	#title2-3 {
		color: rgba(230,230,230,0.75);
		font-weight: bolder;
		text-align: right;
		padding-right:20px;
		width: 800px;
	}
	
	.movie_menu{
		float:left;
		padding-right:7px;
		padding-left:7px;
		text-align: center;
		cursor:pointer;
	}
	
	.movie_second_menu{
		float:left;
		padding-right:8px;
		padding-left:8px;
		border-bottom:2px solid #999;
		text-align: center;
		width: 80px;
		cursor:pointer;
	}
	
	#movie_second_menu1{
		border-bottom: 2px solid #000;
		cursor:pointer;
	}
	
	#theater_state{
		margin-top:15px;
		float:left;
		text-align: right;
		width:130px;
	}
	
	#time{
		cursor:pointer;
	}
	
	#theater_state_num{
		margin-top:15px;
		margin-left:-20px;
		float:left;
		text-align: left;
		width:20px;	
	}
	
	ul#theater_state {
		width: 50%;
	}
	
	ul#theater_state>li {
		
		width: 100%;
		margin-bottom: 3px;
		padding: 8px;
		
		cursor:pointer;
	}
	
	.theater_state {
		background-color: #E6E4D9;
	}
	
	.select_theater_state{
		background-color: #F2F0E4;
	}
	
	ul#theater_state_num>li>div {
		background-color: rgba(166,166,166,0.2);
		margin-bottom: 3px;
		padding: 8px;
		margin-left: -20px;
		width:35px;
		
	}
	
	.theater_city{
		margin-top:15px;
		width: 50%;
		float:left;
		text-align: left;
	}
	
	#theater_detail>div{
		margin-bottom: 3px;
		padding: 8px;
		width: 100%;
		
	}
	
	.timetable_time{
		border: 1px solid #999;
		float: left;
		margin-left: 15px;
		margin-right: 3px;
		margin-bottom: 5px;
	}
	
	.timetable_seat{
		color: #6B9900;
		margin-right: 10px;
		float: left;
	}
	
	.calender{
		height: 30px;
		width: 80%;
		padding-left: 30%;
		margin: 0px auto;
		text-align:center;
		cursor:pointer;
	}
	
	#movie_list{
		cursor:pointer;
	}
	
	#movie_list>div {
		width: 240px;
		margin: 0px auto;
		margin-top: 15px;
	 	float: none;
	}
	
	#theater_detail{
		width:100%;
		cursor:pointer;
	}
	
	#reset {
		float:left;
		padding-left:5px;
	}
	
	#timetableBtn {
		float:left;
		padding-left:5px;
	}
	
	.glyphicon{
		clear: both;
	}
	
	#favoriteCGV1 {
		float:left;
		border-top:2px solid #999;
		border-bottom:2px solid #999;
		width:120px;
		margin-left:45px;
		color: red; font-weight: bold;
	}
	
	#favoriteCGV2 {
		float:left;
		border-top:2px solid #999;
		border-bottom:2px solid #999;
		width:120px;
	}
	
	#info_box {
		background-color: #1D1D1C;
		height: 200px;
		clear:both;
	}
	
	#screen {
		width: 600px;
		height: 25px;
		background-color: #bbb;
		margin: 0px auto;
		text-align: center;
		font-size: 14pt;
		font-weight: bold;
		color: #555;
	}
	
	

	
	
	th{
		background-color: rgba(0,0,0,0.9);
		<!--border: 1px solid rgba(230,230,230,0.75);-->
		padding-top: 5px;
		height: 40px;
	}
	
	td{
		background-color: #FFFFE6;
		padding: 5px;
		border: 2px solid #999;
	}
	
	.seat_num{
		margin-bottom:3px;
		
	}
	
	.seat_number{
		float: left;
		margin-left: 5px;
	}
	
	.box{
		float: left;
		width: 18px;
		height: 18px;
		border: 1px solid #000;
	}
	
	#seat_table>table ,#seat{
		margin: 0px auto;
	}
	
	.seat_header{
		width: 25px;
		height: 25px;
		font-size: 2pt;
		text-align: center;
		background-color: #FFFFE6;
		border-top: 2px solid #ddd;
		border-bottom: 2px solid #ddd;
		border-spacing: 0px;
	}
	
	.seat_prime{
		width: 25px;
		height: 25px;
		font-size: 2pt;
		text-align: center;
		border: 2px solid #f00;
		border-spacing: 0px;
		cursor:pointer;
	}
	
	.seat_standard{
		width: 25px;
		height: 25px;
		font-size: 2pt;
		text-align: center;
		border: 2px solid #477500;
		cursor:pointer;
	}
	
	.seat_economy{
		width: 25px;
		height: 25px;
		font-size: 2pt;
		text-align: center;
		border: 2px solid #FF4500;
		cursor:pointer;
	}
	
	.seat_sweetbox{
		width: 25px;
		height: 25px;
		font-size: 2pt;
		text-align: center;
		border: 2px solid #FF4500;
		
		cursor:pointer;
	}
	
	.sweet_color {
		background-color: #FF007F;
	}
	
	.seat_handicapped{
		width: 25px;
		height: 25px;
		font-size: 2pt;
		text-align: center;
		border: 0px solid #FF4500;
		background-color: #597812;
		cursor:pointer;
	}
	
	.seat_reservation{
		width: 25px;
		height: 25px;
		font-size: 2pt;
		text-align: center;border: 2px0solid #FF4500;
		
		background-color: #C2C2C2;
		cursor:pointer;
	}
	
	.seat_clicked{
		width: 25px;
		height: 25px;
		font-size: 2pt;
		text-align: center;
		border: 2px solid #999;
		background-color: #980000;
	}
	
	.seat_over{
		width: 25px;
		height: 25px;
		font-size: 2pt;
		text-align: center;
		border: 2px solid #999;
		background-color: #980000;
	}
	
	.seat_empty{
		width: 25px;
		height: 25px;
		font-size: 2pt;
		text-align: center;
		border: 0px solid #FF4500;
		background-color: #FFFFE6;
		
	}
	
	.seat_count_nomal,.seat_count_youth,.seat_count_advantage{
		border: 1px solid #000;
		height: 20px;
		width: 20px;
		background-color: #333;
		color: white;
	}
	
	.seat_nomal,.seat_youth,.seat_advantage,.seat_title{
		margin-right: 5px;
		float:left;
		text-align: center;
	}
	.seat_nomal,.seat_youth,.seat_advantage{
		cursor: pointer;
		border: 1px solid #000;
		height: 20px;
		width: 20px;
	}
	
	.seat_title {
		width: 80px;
		height: 20px;
		margin-left:10px;
		text-align: left;
		float:none;
	}
	
	#prime {
		width: 18px;
		height: 18px;
		border: 2px solid #f00;
		float: left;
	}
	
	#standard {
		 width: 18px;
		 height: 18px;
		 border: 2px solid #477500;
		 float: left;
	}
	
	#economy {
		 width: 18px;
		 height: 18px;
		 border: 2px solid #FF4500;
		 float: left;
	}
	
	#handicapped {
		width: 18px;
		height: 18px;
		border: 0px solid #FF4500;
		background-color: #597812;
		float: left;
	}
	
	#sweetbox {
		width: 18px;
		height: 18px;
		border: 0px solid #999;
		background-color: #FF007F;
		float: left;
	}
	
	#reservationed {
		width: 18px;
		height: 18px;
		background-color: #C2C2C2;
		float: left;
	}
	
	#selected {
		width: 18px;
		height: 18px;
		border: 2px solid #999;
		background-color: #980000;
		float: left;
	}
	
	#backArea, #backArea2 {
		float: left;
	}
	
	#backBtn, #backBtn2 {
		 margin-top: 50px;
		 margin-left: 50px;
		 background-color: #999;
		 color: #fff;
	}
	
	#nextBtn1, #nextBtn2, #nextBtn3 {
	 	margin-top: 50px;
		margin-right: 50px;
		background-color: #999;
		color: #fff;
	 }
	
	#backBtn > span, #nextBtn1 > span, #nextBtn2 > span, #backBtn2 > span, #nextBtn3 > span {
		font-size: 35pt;
		color: #fff;
		font-weight: bolder;
	}
	
	#selected_movie {
		color: #fff;
		font-weight: bold;
		font-size: 30pt;
		margin-top: 80px;
		float: left;
		margin-right: 15px;
		padding-right: 20px;
		margin-left: 50px;
		border-right: 2px solid #fff;
	}
	
	#selected_theater	{
		 color: #fff;
		 font-weight: bold;
		 font-size: 30pt;
		 margin-top: 80px;
		 float: left;
		 margin-right: 15px;
		 padding-right: 20px;
		 border-right: 2px solid #fff;
	}
	
	#etc {
		 color: #fff;
		 font-weight: bold;
		 font-size: 30pt;
		 margin-top: 80px;
		 float: left;
	}
	
	#nextArea1, #nextArea2, #nextArea3 {
		float: right;
	}
	
	.discount_method, .payment {
		font-size: 30px;
		height: 50px;
		background-color: #333; 
		color: #E0E0E0; 
		font-weight: bold;
		float: left;
		padding-top: 5px;
	}
	
	#discount_method1, #payment_method1 {
		width: 30%;
		padding-left: 20px;
	}
	
	#discount_method2, #payment_method2 {
		width: 40%;
		text-align: center;
	}
	
	#discount_method3, #payment_method3 {
		width: 30%;
		text-align: right;
		padding-right: 20px;
	}
	
	#discount_title {
		background-color: #DEDED6;
		color: #333;
		clear: both;
		width: 100%;
		height: 70px;
		font-size: 30px;
		padding-top: 15px;
		padding-left: 15px;
	}
	
	#discount_content {
		background-color: #FEF0E4;
		height: 300px;
		width: 100%;
	}
	
	#payment_content {
		clear: both;
		padding-top: 30px;
		background-color: #FEF0E4;
		height: 750px;
		width: 100%;
	}
	
	#discount_coupon, #discount_point {
		width: 50%;
		height: 300px;
		float: left;
		margin-top: 10px;
		padding-left: 2.5%;
		
	}
	
	#discount_coupon>div>span, #discount_point>div>span {
		height: 30px;
		color: #02283D;
		font-size: 20px;
		font-weight: bold;
	}
	
	#discount_coupon>div>div {
		float: right;
		margin-right: 10%;
	}
	
	#discount_coupon>div>div>button {
		margin-left: 5px;
	}
	
	
	
	.coupon_title {
		border-bottom: 1px solid #DEDED6;
		float: left;
		margin-top: 20px;
	}
	
	#coupon_title1 {
		width: 60%;
		
	}
	
	#coupon_title2 {
		width: 30%;
	}
	
		
	#coupon_content {
		clear: both;
	}
	
	#point_ul>li {
		clear: both;
	}
	
	.select {
		background-color: #333;
		color: white;
	}
	
	.movie_section {
		margin-left : -15px;
		width : 100%;
		height : 30px;
	}
	
	.Saturday {
		color: blue;
	}
	
	.Sunday {
		color: red;
	}
	
	label {
		float:left;
		margin-right: 5px;
	}

-->
	</style>
	<div id="reservation1">
		<button class="btn btn-warning btn-sm"><span class="glyphicon glyphicon-time" /><div id="timetableBtn">상영시간표</div></button>
		<button class="btn btn-warning btn-sm"><span class="glyphicon glyphicon-repeat" /><div id="reset">예매다시하기</div></button>
		
		<br>
		<br>
		<div id="menu1">
			<div id="menu1-1" class="menu1">영화</div>
			<div id="menu1-2" class="menu1">극장</div>
			<div id="menu1-3" class="menu1">날짜</div>
			<div id="menu1-4" class="menu1">시간</div>
		</div>
		
		<div id="content1">
			<div id="content1-1" class="content1">
				<div>
					<div id="movie_second_menu1" class="movie_second_menu" onclick="order_reservation()">예매율순</div>
					<div id="movie_second_menu2" class="movie_second_menu" onclick="order_alphabet()">가나다순</div>
					<div id="movie_second_menu3" class="movie_second_menu">&nbsp;</div>
				</div>
				<br>
				<div id="movie_list"></div>
			</div>
			
			<div id="content1-2" class="content1">
				<div>
					<div id="favoriteCGV1">
						자주가는 CGV<br>
						<br>
						<br>
					</div>
					<div id="favoriteCGV2">
						CGV1<br>
						CGV2<br>
						CGV3
					</div>
				</div>
				<br>
				<div style="height: 300px">
					<ul id="theater_state">
						<c:forEach items="${ localList }" var="local_list">
							<c:choose>
								<c:when test="${local_list.localName=='서울'}">
									<li class="localName theater_state select_theater_state" style="float: none; display: inline-block; list-style: none;"  onclick="theater_local('${local_list.localName}')" >
										<div>${local_list.localName} (${local_list.localCount})</div>
									</li>
								</c:when>
	
								<c:otherwise>
									<li class="localName theater_state" style="float: none; display: inline-block; list-style: none;"  onclick="theater_local('${local_list.localName}')" >
										<div>${local_list.localName} (${local_list.localCount})</div>
									</li>
								</c:otherwise>
								
							</c:choose>
						</c:forEach>
						
					</ul>
					<div class="theater_city">
						<div id = "theater_detail"></div>
					</div>
				</div>
			</div>
			<div id="content1-3" class="content1">
				<div style="text-align: center; font-size: 15pt;" id = "year"></div><br>
				<div style="margin-top: -20px; text-align: center; font-size: 30pt;" id = "month"></div><br>
				<div id="calender_date"></div>
				
			</div>
			<div id="content1-4" class="content1">
				<div id="timetable">
					
				</div>
			</div>
		</div>
	</div>
	
	<div id="reservation2">
		<table>
			<tr>
				<th id="title2-1">☞ 관람 할인 안내</th>
				<th id="title2-2">인원/좌석</th>
				<th id="title2-3">다시하기<span class="glyphicon glyphicon-repeat"></span></th>
			</tr>
			<tr>
				<td style="width: 500px;">
					<div class="seat_num">
						<div class="seat_title" style="float: left;">일반</div>
						<div id="nomal_defalut" class="seat_nomal">0</div>
						<div class="seat_nomal">1</div>
						<div class="seat_nomal">2</div>
						<div class="seat_nomal">3</div>
						<div class="seat_nomal">4</div>
						<div class="seat_nomal">5</div>
						<div class="seat_nomal">6</div>
						<div class="seat_nomal">7</div>
						<div class="seat_nomal">8</div>
					</div>
					<div class="seat_num">
						<div class="seat_title">청소년</div>
						<div id="youth_defalut" class="seat_youth" style="margin-left:95px;">0</div>
						<div class="seat_youth">1</div>
						<div class="seat_youth">2</div>
						<div class="seat_youth">3</div>
						<div class="seat_youth">4</div>
						<div class="seat_youth">5</div>
						<div class="seat_youth">6</div>
						<div class="seat_youth">7</div>
						<div class="seat_youth">8</div>
					</div>
					<div class="seat_num">
						<div class="seat_title">우대</div>
						<div id="advantage_defalut" class="seat_advantage" style="margin-left:95px;">0</div>
						<div class="seat_advantage">1</div>
						<div class="seat_advantage">2</div>
						<div class="seat_advantage">3</div>
						<div class="seat_advantage">4</div>
						<div class="seat_advantage">5</div>
						<div class="seat_advantage">6</div>
						<div class="seat_advantage">7</div>
						<div class="seat_advantage">8</div>
					</div>
				</td>
				<td style="width: 200px; text-align: center;">
					<div>좌석붙임설정</div>
					<div style="margin-left: 30px; height: 25px;">
						<label onclick="seatFormClick(1);">
							<input class ="seat_number" name="seat_num" type="radio" value="1">
							<div class="box"></div>
						</label>

						<label onclick="seatFormClick(2);">
							<input class ="seat_number" name="seat_num" type="radio" value="2">
							<div class="box"></div><div class="box"></div>
						</label>
						
						
						<label onclick="seatFormClick(3);">
							<input class ="seat_number" name="seat_num" type="radio" value="3">
							<div class="box"></div><div class="box"></div><div class="box"></div>
						</label>
						
						<label onclick="seatFormClick(4);">
							<input class ="seat_number" name="seat_num" type="radio" value="4">
							<div class="box"></div><div class="box"></div><div class="box"></div><div class="box"></div>
						</label>
					</div>
				</td>
				<td>
					<div>선택하신 상영관/시간</div>
					<div style="margin-top: 10px;">
						<span id="plex" style="font-size: 12pt; font-weight: bolder;"></span>
						<span id="seat_totcnt">(총 172석)</span>
						<span style="font-size: 12pt;">21:30 - 23:38</span>
						(잔여 132석)
					</div>
					<button style="float: left;" class="btn btn-warning">상영시간 변경하기</button>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="border-right: 0px solid #999;">
					<div id="screen">screen</div>
					<div style="height:50px"><span></span></div>
					<div id="seat_table"></div>
				</td>
				<td style="border-left: 0px solid #999;">
					<div id="selected"></div><div>선택</div><br>
					<div id="reservationed"></div><div>예매완료</div><br>
					
					<div id="prime"></div><div>Prime Zone</div><br>
					<div id="standard"></div><div>Standard Zone</div><br>
					<div id="economy"></div><div>Economy Zone</div><br>
					<div id="handicapped"></div><div>장애인석</div><br>
					<div id="sweetbox"></div><div>SweetBox</div><br>
				</td>
			</tr>
		</table>
	</div>
	<div id="reservation3">
		<div id="discount_method1" class="discount_method">STEP 1</div>
		<div id="discount_method2" class="discount_method">할인수단 선택</div>
		<div id="discount_method3" class="discount_method">다시하기</div>
		<div id="discount_title" class="discount_title">CGV 할인쿠폰 <span>/</span> CJ ONE 포인트</div>
		<div id="discount_content">
			<div id="discount_coupon">
				<div><span>●CGV 할인쿠폰</span><div><button>등록하기</button><button>전체 쿠폰</button></div></div>
					<div id="coupon_title1" class="coupon_title">사용가능 쿠폰</div><div id="coupon_title2" class="coupon_title">유효기간</div>
				<div id="coupon_content">사용 가능한 CGV할인쿠폰이 없습니다.</div>
			</div>
			<div id="discount_point">
				<div>
					<span>●CJ ONE 포인트</span>
					<div>
						<ul id="point_ul">
							<li>현재 보유 포인트: <span>0p</span></li>
							<li><span>사용할 포인트:</span><span><input type="text" />p</span><span>모두사용<input type="checkbox"/></span></li>
							<li>CJ ONE 포인트는 <span>1,000P</span> 이상부터 <span>10P</span>단위로 사용 가능합니다.</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		
		<div id="payment_method1" class="payment">STEP 2</div>
		<div id="payment_method2" class="payment">결제정보 입력</div>
		<div id="payment_method3" class="payment"></div>
		<div id="payment_content">
			
		
		
			<div id="demo" class="col-md-8 col-md-offset-1 col-xs-11">
				<form name="frm_payment" id="frm_payment" class="form-horizontal">
					<div class="form-group">
						<label for="payMethod" class="col-md-4 col-xs-4">결제수단</label>
						<select name="payMethod" id="payMethod" class="col-md-8 col-xs-8">
							<option value="card">신용카드</option>
							<option value="trans">실시간 계좌이체</option>
							<option value="vbank">가상계좌</option>
							<option value="phone">휴대폰 소액결제</option>
						</select>
	                </div>
					
					<div class="form-group">
						<label for="reservationCode" class="col-md-4 col-xs-4">예매번호</label>
						<input type="text" name="reservationCode" id="reservationCode" value="R001" class="col-md-8 col-xs-8"/>
					</div>
					<div class="form-group">
						<label for="movieName" class="col-md-4 col-xs-4">영화명</label>
						<input type="text" name="movieName" id="movieName" value="" class="col-md-8 col-xs-8"/>
					</div>
					<div class="form-group">
						<label for="pay" class="col-md-4 col-xs-4">금액</label>
						<input type="text" name="pay" id="pay" value="1000" class="col-md-8 col-xs-8"/>
					</div>
					<div class="form-group">
						<label for="email" class="col-md-4 col-xs-4">이메일주소</label>
						<input type="text" name="email" id="email" value="abcd@naver.com" class="col-md-8 col-xs-8"/>
					</div>
					<div class="form-group">
						<label for="buyerName" class="col-md-4 col-xs-4">성함</label>
						<input type="text" name="buyerName" id="buyerName" value="이상태로 뿌리기" class="col-md-8 col-xs-8"/>
					</div>
					<div class="form-group">
						<label for="tel" class="col-md-4 col-xs-4">전화번호</label>
						<input type="text" name="tel" id="tel" value="010-1234-5678" class="col-md-8 col-xs-8"/>
					</div>
					<div class="form-group">
						<label for="address" class="col-md-4 col-xs-4">주소</label>
						<input type="text" name="address" id="address" value="서울시 송파구 " class="col-md-8 col-xs-8"/>
					</div>
					<div class="form-group">
						<label for="post" class="col-md-4 col-xs-4">우편번호</label>
						<input type="text" name="post" id="post" value="123-456" class="col-md-8 col-xs-8"/>
					</div>
					<div class="form-group">
						<label for="vbank_due" class="col-md-4 col-xs-4">가상계좌 입금일자<br>(YYYYMMDD)</label>
						<input type="text" name="vbank_due" id="vbank_due" value="" class="col-md-8 col-xs-8"/>
					</div>
					
					
				</form>
				<div id="responser"></div>
			</div>
		</div>
	</div>

	<div id="info_box">
		<div id="backArea"><button id="backBtn" onclick="backStep()"><span><</span><br/>영화선택</button></div>
		<div id="backArea2"><button id="backBtn2" onclick="backStep2()"><span><</span><br/>좌석선택</button></div>
		<div id="selected_movie">
			영화선택
		</div>
		<div id="selected_theater">
			극장선택
		</div>
		<div id="etc">
			> 좌석선택 > 결제
		</div>
		<div id="nextArea1"><button id="nextBtn1" onclick="nextStep();"><span>></span><br/>좌석선택</button></div>
		<div id="nextArea2"><button id="nextBtn2" onclick="lastStep()"><span>></span><br/>결제선택</button></div>
		<div id="nextArea3"><button id="nextBtn3" onclick="payment()"><span>></span><br/>결제하기</button></div>
	</div>
	
	<form id="frm" name="frm" method="post" action="/reservation">
		<input type="text" id="movie" name="movie" size="50" maxlength="100" style="display:none;">
		<input type="text" id="theater" name="theater" size="50" maxlength="100" style="display:none;">
		<input type="text" id="years" name="year" size="50" maxlength="100" style="display:none;">
		<input type="text" id="months" name="month" size="50" maxlength="100" style="display:none;">
		<input type="text" id="dates" name="date" size="50" maxlength="100" style="display:none;">
		<input type="text" id="start_time" name="start_time" size="50" maxlength="100" style="display:none;">
		<input type="text" id="plex" name="plex" size="50" maxlength="100" style="display:none;">
		<input type="text" id="seat_cnt" name="seat_cnt" size="50" maxlength="100" style="display:none;">
		<input type="text" id="seat1" name="seat1" size="50" maxlength="100" style="display:none;">
		<input type="text" id="seat2" name="seat2" size="50" maxlength="100" style="display:none;">
		<input type="text" id="seat3" name="seat3" size="50" maxlength="100" style="display:none;">
		<input type="text" id="seat4" name="seat4" size="50" maxlength="100" style="display:none;">
		<input type="text" id="seat5" name="seat5" size="50" maxlength="100" style="display:none;">
		<input type="text" id="seat6" name="seat6" size="50" maxlength="100" style="display:none;">
		<input type="text" id="seat7" name="seat7" size="50" maxlength="100" style="display:none;">
		<input type="text" id="seat8" name="seat8" size="50" maxlength="100" style="display:none;">
	</form>
	
	<script>
		var checkMovie = false;
		var checkTheater = false;
		var checkDate = false;
		var currentMoviePage = "reservation_rate";
		var currentTheaterPage = "서울";
		var movieId = ""
		var theaterId = ""
		var reservationCode = "";
		var plexNum = "";
		var seat_count = 0;
		var totalPrice = 0;
		
		function backStep(){
			$("#reservation1").show();
			$("#reservation2").hide();
			$("#backArea").hide();
			$("#backArea2").hide();
			$("#nextArea3").hide();
			$("#nextArea2").hide();
			$("#nextArea1").show();
		}
		
		function backStep2() {
			$("#reservation1").hide();
			$("#reservation2").show();
			$("#reservation3").hide();
			$("#backArea").show();
			$("#backArea2").hide();
			$("#nextArea3").hide();
			$("#nextArea2").hide();
			$("#nextArea1").show();
		}
		
		function payment(){
			
			var IMP = window.IMP;
			IMP.init('iamport');
			
			var payMethod = $("#payMethod").val();
			var reservationCode = $("#reservationCode").val();
			var movieName = $("#movieName").val();
			var pay = parseInt($("#pay").val());
			var email = $("#email").val();
			var buyerName = $("#buyerName").val();
			var tel = $("#tel").val();
			var addr = $("#address").val();
			var post = $("#post").val();
			alert(payMethod);
			
			IMP.request_pay({
				//이니시스만 됨
				pg : 'inicis', // version 1.1.0부터 지원.
				/*
					'kakao':카카오페이,
					'inicis':이니시스, 'html5_inicis':이니시스(웹표준결제),
					'nice':나이스,
					'jtnet':jtnet,
					'uplus':LG유플러스
				*/
				
				pay_method : payMethod, // 'card' : 신용카드 | 'trans' : 실시간계좌이체 | 'vbank' : 가상계좌 | 'phone' : 휴대폰소액결제
				
				merchant_uid : reservationCode,
				name : movieName,
				amount : pay,
				buyer_email : email,
				buyer_name : buyerName,
				buyer_tel : tel,
				buyer_addr : addr,
				buyer_postcode : post,
				app_scheme : 'iamporttest' //in app browser결제에서만 사용 
			}, function(rsp) {
				
				if ( rsp.success ) {
					
					var msg = '결제가 완료되었습니다.';
					msg += '상점 거래ID : ' + rsp.reservationCode;
					msg += '결제 금액 : ' + rsp.pay;
					
				} else {
					
					var msg = '결제에 실패하였습니다.';
					msg += '에러내용 : ' + rsp.error_msg;
					
				}
				$('#responser').text(msg);
				
			});
			
		};
		
		function nextStep(){
			
			if(frm.months.value < 10 && frm.months.value.length != 2){
				frm.months.value = "0" + frm.months.value;
			}
			
			if(frm.plex.value == "" || frm.plex.value == null) {
				alert("시간을 선택해 주세요");	
			} else {

				$("#reservation1").hide();
				$("#reservation2").show();
				$("#backArea").show();
				$("#nextArea1").hide();
				$("#nextArea2").show();
				$("#nextBtn1").hide;
				$("#plex").text(frm.plex.value + "관");
				$("#seat_totcnt").text(" (총  " + frm.seat_cnt.value + " 석 )");
				reservationCode = $("#years").val() + $("#months").val() 
								+ $("#dates").val() + movieId + theaterId + frm.plex.value;

			}
		}
		
		function lastStep() {
			if(selectFlag == 1){
				
				plexNum = frm.plex.value;
				seat1 = frm.seat1.value;
				seat2 = frm.seat2.value;
				seat3 = frm.seat3.value;
				seat4 = frm.seat4.value;
				seat5 = frm.seat5.value;
				seat6 = frm.seat6.value;
				seat7 = frm.seat7.value;
				seat8 = frm.seat8.value;
				
				reservationCodeGenerater();
				payGenerater();
				
				$("#reservation1").hide();
				$("#reservation2").hide();
				$("#reservation3").show();
				$("#backArea").hide();
				$("#backArea2").show();
				$("#nextArea2").hide();
				$("#nextArea3").show();
			} else if(selectFlag == 0){
				alert("선택한 좌석수가 총 좌석수와 맞지 않습니다");
			}
			
		}
		
		function reservationCodeGenerater() {
			var codeYear = frm.years.value;
			var codeMonth = frm.months.value;
			var codeDate = frm.dates.value;
			
			if(frm.months.value < 10 && frm.months.value.length < 2 ){
				codeMonth = '0' + frm.months.value;
			}
			
			if(frm.dates.value < 10 && frm.dates.value.length < 2){
				codeDate = '0' + frm.dates.value;
			}
			
			var codeHour = frm.start_time.value.substr(0,2);
			var codeMinute = frm.start_time.value.substr(3,2);

			var seat = frm.seat1.value;
			
			reservationCode = codeYear + codeMonth + codeDate + codeHour + codeMinute + movieId + theaterId + plexNum + seat;
			
			$('#reservationCode').val(reservationCode);

		}
		
		function payGenerater() {
			
			var economy = 0;
			var standard = 0;
			var prime = 0;
			var handicapped = 0;
			var sweetbox = 0;
			
		
		
			for(var i = 1; i <= 8; i++){
				
				//alert('#'+($('#seat'+i).val()));
				
				if($('#seat'+i).val() != ""){
					if($('#'+($('#seat'+i).val())).hasClass("seat_economy")){
						economy++;
					} else if($('#'+($('#seat'+i).val())).hasClass('seat_standard')){
						standard++;
					} else if($('#'+($('#seat'+i).val())).hasClass('seat_prime')){
						prime++;
					} else if($('#'+($('#seat'+i).val())).hasClass('seat_handicapped')){
						handicapped++;
					} else if($('#'+($('#seat'+i).val())).hasClass('seat_sweetbox')){
						sweetbox++;
					}
				}
			}
			
			economy *= 8000;
			standard *= 9000;
			prime *= 10000;
			handicapped *= 8000;
			sweetbox *= 11000;
			
			totalPrice = economy + standard + prime + handicapped + sweetbox - 1000*seatYouthCnt;
			
			$('#pay').val(totalPrice);
			alert(totalPrice);
			
		}
		
		function reservationSeat() {

			if(frm.months.value < 10 && frm.months.value.length < 2 ){
				frm.months.value = '0' + frm.months.value;
			}
			
			if(frm.dates.value < 10 && frm.dates.value.length < 2){
				frm.dates.value = '0' + frm.dates.value;
			}
			
			var startTime = frm.years.value +'-' + frm.months.value + '-' + frm.dates.value + ' ' + frm.start_time.value;
			
			$.ajax({
				type:'get',
				url:'/ticket/' + theaterId +'/' + plexNum + '/seat/' + startTime,
				headers: {
					"Content-Type" : "application/json",
				},
				dataType:'json',
				data : {"seat1":seat1, "seat2":seat2, "seat3":seat3, "seat4":seat4, "seat5":seat5, "seat6":seat6, "seat7":seat7, "seat8":seat8},
				success : function(result){
					alert(result);
					alert(result.l);
					alert(result.i);
					setSeat(result.l, result.i);
				}
			});
		}
		
		$(document).ready(function() {
			$("#movie_second_menu1").click(function(){
				$("#movie_second_menu1").css("border-bottom", "2px solid #000")
				$("#movie_second_menu2").css("border-bottom", "2px solid #999")
			});
		});
		
		$(document).ready(function() {
			$("#movie_second_menu2").click(function(){
				$("#movie_second_menu1").css("border-bottom", "2px solid #999")
				$("#movie_second_menu2").css("border-bottom", "2px solid #000")
			});
		});		
		
		function today() {
			var now = new Date();
			var year = now.getFullYear();
			var month = now.getMonth()+1;
			var date = now.getDate();
			var day = now.getDay();
			
			var week = new Array("일", "월", "화", "수", "목", "금", "토");
			
			if(date < 10)
				date = "0" + date;
			
			document.getElementById("year").innerHTML = year;
			document.getElementById("year").value = year;
			document.getElementById("month").innerHTML = month;
			document.getElementById("month").value = month;
			
			
			var result = "";
			
			for(i=0;i<31;i++){
				result += "<div class='calender' onclick='dateSelect("+ i +")'>" 
						+ "<span style='float: left; font-size: 13pt; margin-right: 5px; font-weight: bold' id='day" + i 
						+ "'></span>" + "<span style='float: left; font-size: 13pt; font-weight: bold;' id='date" + i 
						+ "'></span>" + "</div>";
			}
			result += "";
			document.getElementById("calender_date").innerHTML = result;
			
			for(i=0;i<31;i++){
				date = now.getDate();
				day = now.getDay();
				month = now.getMonth()+1;

				document.getElementById("date"+i).innerHTML = date;
				document.getElementById("date"+i).value = date;
				document.getElementById("day"+i).innerHTML = week[now.getDay()];
				document.getElementById("day"+i).value = week[now.getDay()];
				now.setDate(now.getDate()+1);
				
				if($("#day"+i).val() == '토'){
					$("#day"+i).addClass("Saturday");
					$("#date"+i).addClass("Saturday");
				} else if($("#day"+i).val() == '일'){
					$("#day"+i).addClass("Sunday");
					$("#date"+i).addClass("Sunday");
				}
			}

		}
		
		function interval(){
			var interval = setInterval(function(){
				if(checkMovie && checkTheater && checkDate){
					clearInterval(interval);
					var movie = $("#movie").val();
					var month = $("#month").val();
					
					if(month < 10){
						month = "0" + month;
					}
					
					var days = $("#dates").val();
					
					if(days < 10){
						days = "0" + days;
					}
					
					var theater = $("#theater").val();
					var date = $("#year").val() + '-' + month + '-';
					date += days;
					
					getTimetable(movie, theater, date)
				}	
			}, 50)
		}
		
		$(document).ready(function() {
			today();
			$("#backArea").hide();
			$("#backArea2").hide();
			$("#nextArea2").hide();
			$("#nextArea3").hide();
			interval();
			
		});
		
		function dateSelect(i){
			checkDate = false;
			
			if(event.target.nodeName == 'SPAN'){
				
				$(event.target).parent().parent().children().removeClass("select");
				
				//alert(event.target.nodeName);
				$(event.target).parent().addClass("select");
			} else if(event.target.nodeName == 'DIV') {
				$(event.target).parent().children().removeClass("select");
				
				//alert(event.target.nodeName);
				$(event.target).addClass("select");
			}
			
			var date = document.getElementById("date"+i).value;
			frm.years.value = $('#year').val();
			frm.months.value = $('#month').val();
			frm.dates.value = date;
			checkDate = true;
			interval();
		}

		function order_reservation() {
			$('#order').val("reservation_rate");
			getMovieList("reservation_rate");
		}
		
		function order_alphabet() {
			$('#order').val("alphabet");
			getMovieList("alphabet");
		}
		
		function theater_local(value) {			
			if(event.target.nodeName == 'DIV'){
				$(event.target).parent().parent().children().removeClass("select_theater_state");				
				$(event.target).parent().addClass("select_theater_state");				
				
			} else if(event.target.nodeName == 'LI'){
				$(event.target).parent().children().removeClass("select_theater_state");				
				$(event.target).addClass("select_theater_state");			
			}			
			getTheaterList(value);
		}
		
		function movie_select(title, movie_id) {
			checkMovie = false;
			document.getElementById("selected_movie").innerHTML = title;
			movieId = movie_id
			frm.movie.value = title;
			$('#movieName').val(title);
			
			if(event.target.nodeName == 'SPAN'){
				
				$(event.target).parent().parent().children().removeClass("select");
				
				//alert(event.target.nodeName);
				$(event.target).parent().addClass("select");
			} else if(event.target.nodeName == 'DIV') {
				$(event.target).parent().children().removeClass("select");
				
				//alert(event.target.nodeName);
				$(event.target).addClass("select");
			}

			checkMovie = true;
			interval();
		}
		
		function theater_select(theater_name, theater_id) {
			
			if(event.target.nodeName == 'LI'){
				
				$(event.target).parent().parent().children().removeClass("select");
				
				//alert(event.target.nodeName);
				$(event.target).parent().addClass("select");
			} else if(event.target.nodeName == 'DIV'){
				
				$(event.target).parent().children().removeClass("select");
				
				//alert(event.target.nodeName);
				$(event.target).addClass("select");
			}
			
			
			
			
			checkTheater = false;
			document.getElementById("selected_theater").innerHTML = theater_name;
			theaterId = theater_id;
			frm.theater.value = theater_name;
			checkTheater = true;
			interval();
		}
		
		function getMovieList(page) {
			
			if(page == null){
				page = currentMoviePage;
			}
						
			$.ajax({
				type:'get',
				url:'/ticket/movie/' + page,
				headers: {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "GET",
				},
				dataType:'json',
				data : '',
				success : function(result){
					setMovieList(result.l);
				}
			});
		}
		
		function setMovieList(data) {
			var result = "<div>";
			
			$(data).each(function() {
				result += "<div class='movie_section' onclick='movie_select(" 
					+ '"' + this.title + '", ' + '"' + this.movie_id + '"' + ")'><div style='width: 35px; float:left;'>"
					+ this.rating + "</div><span>" + this.title + "</span></div>";
			})
			
			result += "</div>";

			document.getElementById("movie_list").innerHTML = result;
		}
		
//		$( "body" ).click(function( event ) {
//			alert(event.target.nodeName);
//		});
		
		function setTimetableList(time, plex) {
			var result = "";
			var number = 1;
			if(time == "" || plex == ""){
				result += '<div></div>';
				document.getElementById("timetable").innerHTML = result;
			}else {
				$(plex).each(function() {
					
					result += '<div style="margin-left: 20px; margin-bottom: 5px; float:none; clear:both;">' 
						+ '<span style="color: #993800; font-weight: bold">'
						+ this.plex_type + '</span> <span style="font-weight: bold;">' + this.plex_number + '관 </span> (총 '
						+ this.plex_seat_cnt + '석)</div>';
					seat_tot_num(this.plex_seat_cnt);
					
						$(time).each(function() {
							if(this.plex_number == number){
								result += '<div id="time" onclick="timetable(' + "'" + this.start_time.substring(11,16) + "',  " 
										+ "'" + this.plex_number + "'" +');"><span class="timetable_time">' 
										+ this.start_time.substring(11,16) + '</span><div class="timetable_seat">50석</div></div>';
							}
							document.getElementById("timetable").innerHTML = result;
						});
					number++;
				});
			}

		}
		
		function seat_tot_num(seat_num) {
			frm.seat_cnt.value = seat_num;
		}
		
		function timetable(time, plex){
			
			if(event.target.nodeName == 'SPAN'){
				
				$(event.target).parent().parent().children().find('SPAN').removeClass("select");
				
				//alert(event.target.nodeName);
				$(event.target).addClass("select");
			} else if(event.target.nodeName == 'DIV') {
				$(event.target).parent().parent().children().find('SPAN').removeClass("select");
				
				//alert(event.target.nodeName);
				$(event.target).parent().find('SPAN').addClass("select");
			}
				
			frm.start_time.value = time;
			frm.plex.value = plex;

			getSeat(plex);
			
		}
		
		function getSeat(plexNum) {
			
			if(frm.months.value < 10 && frm.months.value.length < 2 ){
				frm.months.value = '0' + frm.months.value;
			}
			
			if(frm.dates.value < 10 && frm.dates.value.length < 2){
				frm.dates.value = '0' + frm.dates.value;
			}
			
			var startTime = frm.years.value +'-' + frm.months.value + '-' + frm.dates.value + ' ' + frm.start_time.value;

			$.ajax({
				type:'get',
				url:'/ticket/plex/' + plexNum + '/' + startTime,
				headers: {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "GET",
				},
				dataType:'json',
				data : '',
				success : function(result){
					setSeat(result.l, result.i);
				}
			});
		}
		
		function setSeat(seat, seatIndex) {
			var result = "<table>";
			var header_index = "";
			var seat_index = "";
			

			$(seatIndex).each(function() {
			
				result += "<tr>" + "<th class='seat_header'>" + this + "</th>";
								
				header_index = this;
				
				$(seat).each(function() {
					seat_index = this.seat_index;
					
					if(header_index == seat_index) {
						
						if (this.seat_type == 'empty'){
							result += "<td id='" + this.seat_index + this.seat_number + "' class='seat_" + this.seat_type + " seat_nonselect'>" + this.seat_number+ "</td>";
						} else if(this.reservation_exist == '1') {
							result += "<td id='" + this.seat_index + this.seat_number + "' class='seat_reservation seat_nonselect'>" + this.seat_number+ "</td>";
						} else if(this.seat_type == 'sweetbox') {
							result += "<td id='" + this.seat_index + this.seat_number + "' class='seat_" + this.seat_type + " sweet_color'>" + this.seat_number+ "</td>";
						} else if(this.reservation_exist == '0') {
							result += "<td id='" + this.seat_index + this.seat_number + "' class='seat_" + this.seat_type + "'>" + this.seat_number+ "</td>";
						}
					}
				});
				result += "</tr>"
		
			});
			result += "</table>";
			document.getElementById("seat_table").innerHTML = result;
			
			$(".seat_empty").text("");
			
			// 좌석 선택
			seatClick();
			
			//좌석 선택영역 표시
			mouseover();		
			mouseout();
			
		}

		function getTheaterList(page) {
			
			if(page == null){
				page = currentTheaterPage;
			}
			
			$.ajax({
				type:'get',
				url:'/ticket/theater/' + page,
				headers: {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "GET",
				},
				dataType:'json',
				data : '',
				success : function(result){
					setTheaterList(result.l);
				}
			});
		}
		
		function setTheaterList(data) {
			var result = "";
			
			$(data).each(function() {
				result += "<div onclick='theater_select(" + '"' + this.theater_name + '", ' + '"' + this.theater_id + '"' 
						+ ")'><li style='float: none; display: inline-block;'>" + this.theater_name + "</li></div>";
			});
			
			result += "";

			document.getElementById("theater_detail").innerHTML = result;
		}
		getTheaterList("서울");
		getMovieList("reservation_rate");
		
		function getTimetable(movie, theater, date) {
			$.ajax({
				type:'get',
				url:'/ticket/timetable/' + movie + '/' + theater + '/' + date,
				headers: {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "GET",
				},
				dataType:'json',
				data : '',
				success : function(result){
					setTimetableList(result.l,result.t);
				}
			});
		}
		
		var nomalCount = 0;
		var youthCount = 0;
		var advantageCount = 0;
		var countSum = 0;
		var totalSeat = 0;
		var currentCheckedValue = 0;
		var radioCheked = 0;
		var endTime = "";
		var selectFlag = 0;
		var seat_row = "";
		var seat_col = "";
		var seat1 = "";
		var seat2 = "";
		var seat3 = "";
		var seat4 = "";
		var seat5 = "";
		var seat6 = "";
		var seat7 = "";
		var seat8 = "";
		var seat = "";
		var seatType1 = "";
		var seatType2 = "";
		var seatType3 = "";
		var seatType4 = "";
		var seatType5 = "";
		var seatType6 = "";
		var seatType7 = "";
		var seatType8 = "";
		var seatNomalCnt = "";
		var seatYouthCnt = "";
		var seatAdvantageCnt = "";
		
		
		$(document).ready(function() {
			$("#nomal_defalut").addClass("seat_count_nomal");
			$("#youth_defalut").addClass("seat_count_youth");
			$("#advantage_defalut").addClass("seat_count_advantage");
		});
		
		$(document).ready(function() {
			
			//일반 좌석 수
			nomal_count;
			
			//청소년 좌석 수
			youth_count;
			
			//우대 좌석 수
			advantage_count;
			
			//좌석형태 함수 ㅁ, ㅁㅁ, ㅁㅁㅁ, ㅁㅁㅁㅁ
			seatNum;
			
			setInterval(function(){
				totalSeatCnt();
				checkSweetBox();
			},500);
			
			// 여기
			//$("#reservation1").hide();
			$("#reservation2").hide();
			$("#reservation3").hide();
		});
		
		function totalSeatCnt() {
			//alert("11");
			seatNomalCnt = $(".seat_count_nomal").text();
			seatYouthCnt = $(".seat_count_youth").text();
			seatAdvantageCnt = $(".seat_count_advantage").text();
			
			totalSeat = Number(seatNomalCnt) + Number(seatYouthCnt) + Number(seatAdvantageCnt);
			
		}

		function seatClick() {
			
			$(".seat_standard, .seat_prime, .seat_economy, .seat_handicapped, .seat_sweetbox").click(function() {
				if(selectFlag == 1 && countSum == 0){
					alert("다시 좌석을 선택합니다!");
					$(".seat_clicked").removeClass("seat_over");
					$(".seat_clicked").removeClass("seat_nonselect");
					$(".seat_clicked").removeClass("seat_clicked");
					selectFlag = 0;
					
					for(var i = 1; i <=8; i++){
						$('#seat' + i).val("");
					}
					
					countSum = totalSeat;
				} else if($(this).hasClass("seat_nonselect") && $(this).hasClass("seat_clicked") && selectFlag == 0) {

					if(currentCheckedValue == 1){
						
						$(this).removeClass("seat_clicked");
						$(this).removeClass("seat_nonselect");
						

						seat_row = $(this).parent().children("th").text();
						seat_col = $(this).text();
						seat = seat_row + seat_col;
						
						for(var i = 1; i <=8; i++){
							if($('#seat' + i).val() == seat){
								$('#seat' + i).val("");
							}
						}
						countSum += currentCheckedValue;
						seat_count -= 1;
						
					} else if(currentCheckedValue == 2) {
						
						if($(this).hasClass("seat_nonselect") && $(this).hasClass("seat_clicked") &&
						   $(this).next().hasClass("seat_nonselect") && $(this).next().hasClass("seat_clicked"))
						{
							$(this).removeClass("seat_clicked");
							$(this).removeClass("seat_nonselect");
							$(this).next().removeClass("seat_clicked");
							$(this).next().removeClass("seat_nonselect");
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).next().text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
						} else if($(this).hasClass("seat_nonselect") && $(this).hasClass("seat_clicked") &&
								  $(this).prev().hasClass("seat_nonselect") && $(this).prev().hasClass("seat_clicked"))
						{
							$(this).removeClass("seat_clicked");
							$(this).removeClass("seat_nonselect");
							$(this).prev().removeClass("seat_clicked");
							$(this).prev().removeClass("seat_nonselect");
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).prev().text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
						}
						
						countSum += currentCheckedValue;
						seat_count -= 2;
						
					} else if(currentCheckedValue == 3) {
						if($(this).hasClass("seat_nonselect") && $(this).hasClass("seat_clicked") &&
						   $(this).next().hasClass("seat_nonselect") && $(this).next().hasClass("seat_clicked") &&
						   $(this).next().next().hasClass("seat_nonselect") && $(this).next().next().hasClass("seat_clicked"))
						{
							$(this).removeClass("seat_clicked");
							$(this).removeClass("seat_nonselect");
							$(this).next().removeClass("seat_clicked");
							$(this).next().removeClass("seat_nonselect");
							$(this).next().next().removeClass("seat_clicked");
							$(this).next().next().removeClass("seat_nonselect");
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).next().text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).next().next().text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
						} else if($(this).hasClass("seat_nonselect") && $(this).hasClass("seat_clicked") &&
								  $(this).next().hasClass("seat_nonselect") && $(this).next().hasClass("seat_clicked") &&
								  $(this).prev().hasClass("seat_nonselect") && $(this).prev().hasClass("seat_clicked")) 
						{
							$(this).removeClass("seat_clicked");
							$(this).removeClass("seat_nonselect");
							$(this).next().removeClass("seat_clicked");
							$(this).next().removeClass("seat_nonselect");
							$(this).prev().removeClass("seat_clicked");
							$(this).prev().removeClass("seat_nonselect");
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).next().text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).prev().text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
						} else if($(this).hasClass("seat_nonselect") && $(this).hasClass("seat_clicked") &&
								  $(this).prev().hasClass("seat_nonselect") && $(this).prev().hasClass("seat_clicked") &&
								  $(this).prev().prev().hasClass("seat_nonselect") && $(this).prev().prev().hasClass("seat_clicked"))
						{
							$(this).removeClass("seat_clicked");
							$(this).removeClass("seat_nonselect");
							$(this).prev().removeClass("seat_clicked");
							$(this).prev().removeClass("seat_nonselect");
							$(this).prev().prev().removeClass("seat_clicked");
							$(this).prev().prev().removeClass("seat_nonselect");
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).prev().text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).prev().prev().text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
						}
						
						countSum += currentCheckedValue;
						seat_count -= 3;
						
					} else if(currentCheckedValue == 4) {
						if($(this).hasClass("seat_nonselect") && $(this).hasClass("seat_clicked") &&
						   $(this).next().hasClass("seat_nonselect") && $(this).next().hasClass("seat_clicked") &&
						   $(this).next().next().hasClass("seat_nonselect") && $(this).next().next().hasClass("seat_clicked") && 
						   $(this).next().next().next().hasClass("seat_nonselect") && $(this).next().next().next().hasClass("seat_clicked"))
						{
							$(this).removeClass("seat_clicked");
							$(this).removeClass("seat_nonselect");
							$(this).next().removeClass("seat_clicked");
							$(this).next().removeClass("seat_nonselect");
							$(this).next().next().removeClass("seat_clicked");
							$(this).next().next().removeClass("seat_nonselect");
							$(this).next().next().next().removeClass("seat_clicked");
							$(this).next().next().next().removeClass("seat_nonselect");
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).next().text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).next().next().text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).next().next().next().text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
						} else if($(this).hasClass("seat_nonselect") && $(this).hasClass("seat_clicked") &&
								  $(this).next().hasClass("seat_nonselect") && $(this).next().hasClass("seat_clicked") &&
								  $(this).next().next().hasClass("seat_nonselect") && $(this).next().next().hasClass("seat_clicked") && 
								  $(this).prev().hasClass("seat_nonselect") && $(this).prev().hasClass("seat_clicked")) 
						{
							$(this).removeClass("seat_clicked");
							$(this).removeClass("seat_nonselect");
							$(this).next().removeClass("seat_clicked");
							$(this).next().removeClass("seat_nonselect");
							$(this).next().next().removeClass("seat_clicked");
							$(this).next().next().removeClass("seat_nonselect");
							$(this).prev().removeClass("seat_clicked");
							$(this).prev().removeClass("seat_nonselect");
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).next().text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).next().next().text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).prev().text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
						} else if($(this).hasClass("seat_nonselect") && $(this).hasClass("seat_clicked") &&
								  $(this).next().hasClass("seat_nonselect") && $(this).next().hasClass("seat_clicked") &&
								  $(this).prev().hasClass("seat_nonselect") && $(this).prev().hasClass("seat_clicked") && 
								  $(this).prev().prev().hasClass("seat_nonselect") && $(this).prev().prev().hasClass("seat_clicked")) 
						{
							$(this).removeClass("seat_clicked");
							$(this).removeClass("seat_nonselect");
							$(this).next().removeClass("seat_clicked");
							$(this).next().removeClass("seat_nonselect");
							$(this).prev().removeClass("seat_clicked");
							$(this).prev().removeClass("seat_nonselect");
							$(this).prev().prev().removeClass("seat_clicked");
							$(this).prev().prev().removeClass("seat_nonselect");
							
						} else if($(this).hasClass("seat_nonselect") && $(this).hasClass("seat_clicked") &&
								  $(this).prev().hasClass("seat_nonselect") && $(this).prev().hasClass("seat_clicked") && 
								  $(this).prev().prev().hasClass("seat_nonselect") && $(this).prev().prev().hasClass("seat_clicked") &&
								  $(this).prev().prev().prev().hasClass("seat_nonselect") && $(this).prev().prev().prev().hasClass("seat_clicked")) 
						{
							$(this).removeClass("seat_clicked");
							$(this).removeClass("seat_nonselect");
							$(this).prev().removeClass("seat_clicked");
							$(this).prev().removeClass("seat_nonselect");
							$(this).prev().prev().removeClass("seat_clicked");
							$(this).prev().prev().removeClass("seat_nonselect");
							$(this).prev().prev().prev().removeClass("seat_clicked");
							$(this).prev().prev().prev().removeClass("seat_nonselect");
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).prev().text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).prev().prev().text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
							seat_row = $(this).parent().children("th").text();
							seat_col = $(this).prev().prev().prev().text();
							seat = seat_row + seat_col;
							
							for(var i = 1; i <=8; i++){
								if($('#seat' + i).val() == seat){
									$('#seat' + i).val("");
								}
							}
							
						}
						countSum += currentCheckedValue;
						seat_count -= 4;
					}
				} else if(!$(this).hasClass("seat_nonselect")) {
					
					if(currentCheckedValue == 1){
						
						$(this).addClass("seat_clicked");
						$(this).addClass("seat_nonselect");

						for(var i=1; i <=8; i++ ){
							if($('#seat' + i).val() == ""){
								
								seat_row = $(this).parent().children("th").text();
								seat_col = $(this).text();
								$('#seat' + i).val(seat_row + seat_col);
								//.seat_standard, .seat_prime, .seat_economy, .seat_handicapped
								if($(this).hasClass("seat_standard")) {
									$('#seatType' + i).val('standard');
								} else if($(this).hasClass("seat_prime")){
									$('#seatType' + i).val('prime');
								} else if($(this).hasClass("seat_economy")){
									$('#seatType' + i).val('economy');
								} else if($(this).hasClass("seat_handicapped")){
									$('#seatType' + i).val('handicapped');
								}
								break;
							}
						}
						
						countSum -= currentCheckedValue;
						
						seatSelect(1);
						
					} else if(currentCheckedValue == 2) {
						
						if($(this).next().hasClass("seat_nonselect") &&
						   !($(this).prev().hasClass("seat_nonselect"))) 
						{
							$(this).addClass("seat_clicked");
							$(this).prev().addClass("seat_clicked");
							$(this).addClass("seat_nonselect");
							$(this).prev().addClass("seat_nonselect");
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).prev().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							
						} else if(!($(this).next().hasClass("seat_nonselect")) && 
								  $(this).prev().hasClass("seat_nonselect"))
						{
							$(this).addClass("seat_clicked");
							$(this).next().addClass("seat_clicked");
							$(this).addClass("seat_nonselect");
							$(this).next().addClass("seat_nonselect");
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).next().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}

						} else if(!($(this).next().hasClass("seat_nonselect"))){
							$(this).addClass("seat_clicked");
							$(this).next().addClass("seat_clicked");
							$(this).addClass("seat_nonselect");
							$(this).next().addClass("seat_nonselect");
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).next().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
						}
						
						countSum -= currentCheckedValue;
						
						seatSelect(2);
	
					} else if(currentCheckedValue == 3){
						
						if($(this).next().hasClass("seat_nonselect") && 
						   !($(this).prev().hasClass("seat_nonselect")) && 
						   !($(this).prev().prev().hasClass("seat_nonselect")))
						{
							$(this).addClass("seat_clicked");
							$(this).prev().addClass("seat_clicked");
							$(this).prev().prev().addClass("seat_clicked");
							$(this).addClass("seat_nonselect");
							$(this).prev().addClass("seat_nonselect");
							$(this).prev().prev().addClass("seat_nonselect");
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).prev().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).prev().prev().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
						} else if($(this).next().next().hasClass("seat_nonselect") && 
								  !($(this).next().hasClass("seat_nonselect")) && 
								  !($(this).prev().hasClass("seat_nonselect")))
						{
							$(this).addClass("seat_clicked");
							$(this).prev().addClass("seat_clicked");
							$(this).next().addClass("seat_clicked");
							$(this).addClass("seat_nonselect");
							$(this).prev().addClass("seat_nonselect");
							$(this).next().addClass("seat_nonselect");
							

							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).next().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).prev().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
						} else if(!($(this).next().hasClass("seat_nonselect")) && 
								  !($(this).next().next().hasClass("seat_nonselect")))
						{
							$(this).addClass("seat_clicked");
							$(this).next().addClass("seat_clicked");
							$(this).next().next().addClass("seat_clicked");
							$(this).addClass("seat_nonselect");
							$(this).next().addClass("seat_nonselect");
							$(this).next().next().addClass("seat_nonselect");
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).next().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).next().next().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}

						}
						
						countSum -= currentCheckedValue;
						
						seatSelect(3);
						
					} else if(currentCheckedValue == 4){
						
						if($(this).next().hasClass("seat_nonselect") && 
						   !($(this).prev().hasClass("seat_nonselect")) && 
						   !($(this).prev().prev().hasClass("seat_nonselect")) && 
						   !($(this).prev().prev().prev().hasClass("seat_nonselect")))
						{
							$(this).addClass("seat_clicked");
							$(this).prev().addClass("seat_clicked");
							$(this).prev().prev().addClass("seat_clicked");
							$(this).prev().prev().prev().addClass("seat_clicked");
							$(this).addClass("seat_nonselect");
							$(this).prev().addClass("seat_nonselect");
							$(this).prev().prev().addClass("seat_nonselect");
							$(this).prev().prev().prev().addClass("seat_nonselect");
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).prev().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).prev().prev().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).prev().prev().prev().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
						} else if($(this).next().next().hasClass("seat_nonselect") && 
								  !($(this).next().hasClass("seat_nonselect")) && 
								  !($(this).prev().hasClass("seat_nonselect")) && 
								  !($(this).prev().prev().hasClass("seat_nonselect")))
						{
							$(this).addClass("seat_clicked");
							$(this).next().addClass("seat_clicked");
							$(this).prev().addClass("seat_clicked");
							$(this).prev().prev().addClass("seat_clicked");
							$(this).addClass("seat_nonselect");
							$(this).next().addClass("seat_nonselect");
							$(this).prev().addClass("seat_nonselect");
							$(this).prev().prev().addClass("seat_nonselect");
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									sseat_row = $(this).parent().children("th").text();
									seat_col = $(this).prev().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).prev().prev().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).next().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
						} else if($(this).next().next().next().hasClass("seat_nonselect") && 
								  !($(this).next().hasClass("seat_nonselect")) && 
								  !($(this).next().next().hasClass("seat_nonselect")) && 
								  !($(this).prev().hasClass("seat_nonselect"))) 
						{
							$(this).addClass("seat_clicked");
							$(this).next().addClass("seat_clicked");
							$(this).next().next().addClass("seat_clicked");
							$(this).prev().addClass("seat_clicked");
							$(this).addClass("seat_nonselect");
							$(this).next().addClass("seat_nonselect");
							$(this).next().next().addClass("seat_nonselect");
							$(this).prev().addClass("seat_nonselect");
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).next().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).next().next().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).prev().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
						} else if(!($(this).next().hasClass("seat_nonselect")) && 
								  !($(this).next().next().hasClass("seat_nonselect")) && 
								  !($(this).next().next().next().hasClass("seat_nonselect")))
						{
							$(this).addClass("seat_clicked");
							$(this).next().addClass("seat_clicked");
							$(this).next().next().addClass("seat_clicked");
							$(this).next().next().next().addClass("seat_clicked");
							$(this).addClass("seat_nonselect");
							$(this).next().addClass("seat_nonselect");
							$(this).next().next().addClass("seat_nonselect");
							$(this).next().next().next().addClass("seat_nonselect");
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).next().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).next().next().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}
							
							for(var i=1; i <=8; i++ ){
								if($('#seat' + i).val() == ""){
									
									seat_row = $(this).parent().children("th").text();
									seat_col = $(this).next().next().next().text();
									$('#seat' + i).val(seat_row + seat_col);
									break;
								}
							}

						}
						
						countSum -= currentCheckedValue;
						
						seatSelect(4);
					}
				}
			});
		}

		function mouseover() {
			$(".seat_standard, .seat_prime, .seat_economy, .seat_handicapped, .seat_sweetbox").mouseover(function() {
			
				if(!($(this).hasClass("seat_nonselect"))) {
					
					if(currentCheckedValue == 1){
						
						$(this).addClass("seat_over");
						
					} else if(currentCheckedValue == 2){
						
						if($(this).next().hasClass("seat_nonselect") &&
						   !($(this).prev().hasClass("seat_nonselect"))) 
						{
							$(this).addClass("seat_over");
							$(this).prev().addClass("seat_over");
							
						} else if(!($(this).next().hasClass("seat_nonselect")) && 
								  $(this).prev().hasClass("seat_nonselect"))
						{
							$(this).addClass("seat_over");
							$(this).next().addClass("seat_over");
							
						} else if(!($(this).next().hasClass("seat_nonselect"))){
							$(this).addClass("seat_over");
							$(this).next().addClass("seat_over");
						}
	
					} else if(currentCheckedValue == 3){
						
						if($(this).next().hasClass("seat_nonselect") && 
						   !($(this).prev().hasClass("seat_nonselect")) && 
						   !($(this).prev().prev().hasClass("seat_nonselect")))
						{
							$(this).addClass("seat_over");
							$(this).prev().addClass("seat_over");
							$(this).prev().prev().addClass("seat_over");
							
						} else if($(this).next().next().hasClass("seat_nonselect") && 
								  !($(this).next().hasClass("seat_nonselect")) && 
								  !($(this).prev().hasClass("seat_nonselect")))
						{
							$(this).addClass("seat_over");
							$(this).prev().addClass("seat_over");
							$(this).next().addClass("seat_over");
							
						} else if(!($(this).next().hasClass("seat_nonselect")) && 
								  !($(this).next().next().hasClass("seat_nonselect")))
						{
							$(this).addClass("seat_over");
							$(this).next().addClass("seat_over");
							$(this).next().next().addClass("seat_over");
							
						}
						
					} else if(currentCheckedValue == 4){
						
						if($(this).next().hasClass("seat_nonselect") && 
						   !($(this).prev().hasClass("seat_nonselect")) && 
						   !($(this).prev().prev().hasClass("seat_nonselect")) && 
						   !($(this).prev().prev().prev().hasClass("seat_nonselect")))
						{
							$(this).addClass("seat_over");
							$(this).prev().addClass("seat_over");
							$(this).prev().prev().addClass("seat_over");
							$(this).prev().prev().prev().addClass("seat_over");
							
						} else if($(this).next().next().hasClass("seat_nonselect") && 
								  !($(this).next().hasClass("seat_nonselect")) && 
								  !($(this).prev().hasClass("seat_nonselect")) && 
								  !($(this).prev().prev().hasClass("seat_nonselect")))
						{
							$(this).addClass("seat_over");
							$(this).next().addClass("seat_over");
							$(this).prev().addClass("seat_over");
							$(this).prev().prev().addClass("seat_over");
							
						} else if($(this).next().next().next().hasClass("seat_nonselect") && 
								  !($(this).next().hasClass("seat_nonselect")) && 
								  !($(this).next().next().hasClass("seat_nonselect")) && 
								  !($(this).prev().hasClass("seat_nonselect"))) 
						{
							$(this).addClass("seat_over");
							$(this).next().addClass("seat_over");
							$(this).next().next().addClass("seat_over");
							$(this).prev().addClass("seat_over");
							
						} else if(!($(this).next().hasClass("seat_nonselect")) && 
								  !($(this).next().next().hasClass("seat_nonselect")) && 
								  !($(this).next().next().next().hasClass("seat_nonselect")))
						{
							$(this).addClass("seat_over");
							$(this).next().addClass("seat_over");
							$(this).next().next().addClass("seat_over");
							$(this).next().next().next().addClass("seat_over");
							
						}	
					}
				} 
			});
		}
		
		function mouseout() {
			$(".seat_standard, .seat_prime, .seat_economy, .seat_handicapped, .seat_sweetbox").mouseout(function() {
		
				if(currentCheckedValue == 1){
					
					$(this).removeClass("seat_over");
					
				} else if(currentCheckedValue == 2){
					
					$(this).removeClass("seat_over");
					
					if($(this).next().hasClass("seat_nonselect")) {
						$(this).prev().removeClass("seat_over");
					} else {
						$(this).next().removeClass("seat_over");
					}
					
				} else if(currentCheckedValue == 3){
					
					$(this).removeClass("seat_over");
					
					if($(this).next().hasClass("seat_nonselect")) {
						$(this).prev().removeClass("seat_over");
						$(this).prev().prev().removeClass("seat_over");
						
					} else if($(this).next().next().hasClass("seat_nonselect")) {
						$(this).next().removeClass("seat_over");
						$(this).prev().removeClass("seat_over");
					} else {
						$(this).next().removeClass("seat_over");
						$(this).next().next().removeClass("seat_over");
					}
					
				} else if(currentCheckedValue == 4){
					$(this).removeClass("seat_over");
					
					if($(this).next().hasClass("seat_nonselect")) {
						$(this).prev().removeClass("seat_over");
						$(this).prev().prev().removeClass("seat_over");
						$(this).prev().prev().prev().removeClass("seat_over");	
					} else if($(this).next().next().hasClass("seat_nonselect")) {
						$(this).next().removeClass("seat_over");
						$(this).prev().removeClass("seat_over");
						$(this).prev().prev().removeClass("seat_over");
					} else if($(this).next().next().next().hasClass("seat_nonselect")) {
						$(this).next().removeClass("seat_over");
						$(this).next().next().removeClass("seat_over");
						$(this).prev().removeClass("seat_over");
					} else {
						$(this).next().removeClass("seat_over");
						$(this).next().next().removeClass("seat_over");
						$(this).next().next().next().removeClass("seat_over");
					}
				}
			});
		}
		
		
		var nomal_count = $(".seat_nomal").click(function() {
			
			radioCheked = $('input[name="seat_num"]:checked').length;
			
			if(countSum != 0) {
				countSum -=  nomalCount;
			}
			
			nomalCount = $(this).text();
			
			if(selectFlag == '0') {
				countSum = Number(nomalCount) + Number(youthCount) + Number(advantageCount);
			} else if(selectFlag == '1' && countSum != totalSeat) {
				alert("11");
				countSum += Number(nomalCount);
				selectFlag = 0;
			}
			
			
			if(countSum <= 8){
				reset("nomal");
				$(this).toggleClass("seat_count_nomal");
				if(countSum == 0 && radioCheked == 1){
					$(this).prop('checked',false);
					$('input[name="seat_num"]:radio[value="'+ currentCheckedValue +'"]').prop('checked',false);
					currentCheckedValue = 0;
				} else if(radioCheked == 1 && countSum < currentCheckedValue){
					currentCheckedValue = countSum;
					$('input[name="seat_num"]:radio[value="'+ currentCheckedValue +'"]').prop('checked',true);
				}
			} else{
				alert("최대 예매 가능한 인원수는 8명 까지 입니다. 단체관람의 경우 단체/대관문의를 이용해주세요.");
			}
			
		});
		
		function checkSweetBox() {
			var value = $('input[name="seat_num"]:checked').val();
			
			if(value == 2 || value == 4) {
				
				$(".seat_sweetbox").removeClass("seat_nonselect");
				$(".seat_sweetbox").removeClass("seat_reservation");
				$(".seat_sweetbox").addClass("sweet_color");
				
			} else {
				$(".seat_sweetbox").removeClass("sweet_color");
				$(".seat_sweetbox").addClass("seat_nonselect");
				$(".seat_sweetbox").addClass("seat_reservation");
				
			}
		}
		
		var youth_count = $(".seat_youth").click(function() {
			
			radioCheked = $('input[name="seat_num"]:checked').length;
			
			if(countSum != 0) {
				countSum -=  youthCount;
			}
			
			youthCount = $(this).text();
			
			if(selectFlag == '0'){
				countSum = Number(nomalCount) + Number(youthCount) + Number(advantageCount);
			} else if(selectFlag == '1' && countSum != totalSeat) {
				countSum += Number(youthCount);
				selectFlag = 0;
			}
			
			if(countSum <= 8){
				reset("youth");
				$(this).toggleClass("seat_count_youth");
				
				if(countSum == 0 && radioCheked == 1){
					$(this).prop('checked',false);
					$('input[name="seat_num"]:radio[value="'+ currentCheckedValue +'"]').prop('checked',false);
					currentCheckedValue = 0;
				} else if(radioCheked == 1 && countSum < currentCheckedValue){
					currentCheckedValue = countSum;
					$('input[name="seat_num"]:radio[value="'+ currentCheckedValue +'"]').prop('checked',true);
				}
			} else{
				alert("최대 예매 가능한 인원수는 8명 까지 입니다. 단체관람의 경우 단체/대관문의를 이용해주세요.");
			}
			
		});
		
		var advantage_count = $(".seat_advantage").click(function() {
			
			radioCheked = $('input[name="seat_num"]:checked').length;
			
			if(countSum != 0) {
				countSum -=  advantageCount;
			}
			
			advantageCount = $(this).text();
			
			if(selectFlag == '0'){
				countSum = Number(nomalCount) + Number(youthCount) + Number(advantageCount);
			} else if(selectFlag == '1' && countSum != totalSeat) {
				countSum += Number(advantageCount);
				selectFlag = 0;
			}
			
			if(countSum <= 8){
				reset("advantage");
				$(this).toggleClass("seat_count_advantage");
				if(countSum == 0 && radioCheked == 1){
					$(this).prop('checked',false);
					$('input[name="seat_num"]:radio[value="'+ currentCheckedValue +'"]').prop('checked',false);
					currentCheckedValue = 0;
				} else if(radioCheked == 1 && countSum < currentCheckedValue){
					currentCheckedValue = countSum;
					$('input[name="seat_num"]:radio[value="'+ currentCheckedValue +'"]').prop('checked',true);
				}
			} else{
				alert("최대 예매 가능한 인원수는 8명 까지 입니다. 단체관람의 경우 단체/대관문의를 이용해주세요.");
			}
			
		});
		
		function reset(name) {
			$(".seat_count_" + name).removeClass("seat_count_" + name);
		}
			
		var seatNum = $("input[name=seat_num]").click(function() {
			var value = $(this).val();
			
			if(selectFlag == 0){
				
				if(countSum < value){
					
					$(this).prop('checked',false);
					$('input[name="seat_num"]:radio[value="'+ currentCheckedValue +'"]').prop('checked',true);
					
				} else {
					
					$(this).prop('checked',true);
					currentCheckedValue = $(this).val();				
				}
			} else {
				
				$('input[name="seat_num"]').removeAttr('checked');
			}
			
			
		});
		
		function seatSelect(value){
			if(countSum < value){
				currentCheckedValue = countSum;
				
				if(currentCheckedValue == 0){
					selectFlag = 1;
				}
				$('input[name="seat_num"]:radio[value="'+ value +'"]').prop('checked',false);
				$('input[name="seat_num"]:radio[value="'+ currentCheckedValue +'"]').prop('checked',true);
				$('input[name="seat_num"]:radio[value="'+ value +'"]').parent().children('div').removeClass("select");
				$('input[name="seat_num"]:radio[value="'+ currentCheckedValue +'"]').parent().children('div').addClass("select");
				seatFormClick(currentCheckedValue);
			}

		}
		
		function seatFormClick(value) {
			
			if(countSum >= value  && selectFlag == 0) {
				$('input[name="seat_num"]:radio[value="'+ value +'"]').parent().parent().children().children('div').removeClass("select");
				$('input[name="seat_num"]:radio[value="'+ value +'"]').parent().children('div').addClass("select");
			}
		}
		
		function endTimeGenerate(startTime, runtime){
			var divideTime = startTime.split(":");
			alert( divideTime[0]+"시");
			alert(divideTime[1]+"분");
			var minute = 0;
			var hour = 0;
			
			minute = Number(divideTime[1]) + runtime;
			hour = Number(divideTime[0]);
			if(minute >= 60){
				hour += minute / 60;
				hour = Math.floor(hour);
				minute = minute % 60;
				if(minute < 10){
					minute = "0" + minute;
				}
			}
			
			alert(hour+"시");
			alert(minute+"분");
			
			endTime = hour + ":" + minute;
			alert("런타임 : " + runtime + "분");
			alert("시작 시간 : " + startTime);
			alert("종료 시간 : " + endTime);
		}

		
	</script>
<%@include file="./include/footer.jsp" %>