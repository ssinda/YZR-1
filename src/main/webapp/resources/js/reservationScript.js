/**
 * 
 */

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
		var discountPrice = 0;
		var startTime = "";
		var veiwDay = "";
		var reservationDay = "";
		var reservationFlag = true;
		var couponCode = "";
		var couponApply = false;
		var timeStatus = 0;
		var disCountPrice = 0;
		var disCountAmount = 0;
		var price = 0;
		var discountMoney = 0;
		
		$('#resetBtn').click(function() {
			location.href="/ticket";
		});
		
		function resetFirst() {
			location.href="/ticket";
		}
		
		function discountApply() {
		
			var couponValue = $("input[name=coupon]:checked").val();
			var couopnInfo = couponValue.split('/');
			couponCode = couopnInfo[0];
			var couponAmount = couopnInfo[1];

			
			
			disCountAmount = price * (couponAmount/100);
			
			disCountAmount += discountMoney;
			
			disCountPrice = totalPrice - disCountAmount;

			
			couponApply = true;
			$('#discountPay').text(disCountAmount);
			$('#pay').val(disCountPrice);

		}
		
		function discountCancel() {
			couponApply = false;
			$('#discountPay').text(discountMoney);
			$('#pay').val(price);
		}
		
		function couponUsed() {

			var couponNo = couponCode.split('-')[1];
			
			$.ajax({
				type:'get',
				url:'/ticket/coupon/'+ couponNo,
				headers: {
					"Content-Type" : "application/json",
				},
				dataType:'json',
				data : '',
				success : function(result){
					
				}
			});
		}
		
		
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
		
		function goLastpage() {
			
			finalFrm.submit();
			
		}
		
		function payment(){
						
			if(frm.months.value < 10 && frm.months.value.length < 2 ){
				frm.months.value = '0' + frm.months.value;
			}
			
			if(frm.dates.value < 10 && frm.dates.value.length < 2){
				frm.dates.value = '0' + frm.dates.value;
			}
			
			startTime = frm.years.value +'-' + frm.months.value + '-' + frm.dates.value + ' ' + frm.start_time.value;
				
			$.ajax({
				type:'get',
				url:'/ticket/seatIdentify/' + theaterId +'/' + plexNum + '/' + startTime,
				headers: {
					"Content-Type" : "application/json",
				},
				dataType:'json',
				data : {"seat1":seat1, "seat2":seat2, "seat3":seat3, "seat4":seat4, "seat5":seat5, "seat6":seat6, "seat7":seat7, "seat8":seat8},
				success : function(result){
					paymentAction(result.l);
				}
			});

			
		}
		
		function paymentAction(reservationExist) {
			
			$(reservationExist).each(function() {
	
				if(this == 1) {
					reservationFlag = false
				}

			});

			
			if(reservationFlag == true) {
				
				reservationSeat();
				
				var memberId = '${member_id}';
				var payMethod = $("#payMethod").val();
				var reservationCode = $("#reservationCode").text();
				var movieName = $("#movieName").text();
				var pay = parseInt($("#pay").val());
				var email = $("#email").val();
				var buyerName = $("#buyerName").val();
				var tel = $("#tel").val();
				var addr = $("#address").val();
				var post = $("#post").val();
				var seatAll = "";
				for(var i = 1; i <= 8; i++){
					if($("#seat"+i).val() != ""){
						seatAll += $("#seat"+i).val()
						if($("#seat"+(i+1)).val() != "")
						seatAll	+= ', ';
					}
				}			

				var IMP = window.IMP;
				IMP.init('iamport');
				
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
					 // 'card' : 신용카드 | 'trans' : 실시간계좌이체 | 'vbank' : 가상계좌 | 'phone' : 휴대폰소액결제
					
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
						reservationCodeInput();
						
						if(couponApply){
							couponUsed();
						}
						goLastpage();
						
					} else {
						
						var msg = '결제에 실패하였습니다.';
						msg += '에러내용 : ' + rsp.error_msg;
						reservationCancel();
						
					}
					$('#responser').text(msg);
					
				});
			}
			
			
		}
		
		function reservationCodeInput() {
			
			var theaterName = frm.theater.value;
			
			var viewYear = frm.years.value;
			var viewMonth = frm.months.value;
			var viewDate = frm.dates.value;
	
			if(frm.months.value < 10 && frm.months.value.length < 2 ){
				viewMonth = '0' + frm.months.value;
			}
			
			if(frm.dates.value < 10 && frm.dates.value.length < 2){
				viewDate = '0' + frm.dates.value;
			}
			
			var viewHour = frm.start_time.value.substr(0,2);
			var viewMinute = frm.start_time.value.substr(3,2);
			
			veiwDay = viewYear + '-' + viewMonth + '-' + viewDate + ' ' + viewHour + ":" + viewMinute;

			var reservationNow = new Date();
			var reservationYear = reservationNow.getFullYear();
			var reservationMonth = reservationNow.getMonth()+1;
			var reservationDate = reservationNow.getDate();
			var reservationHour = reservationNow.getHours();
			var reservationMinute = reservationNow.getMinutes();
			
			if(reservationMonth < 10){
				reservationMonth = '0' + reservationMonth;
			}
			
			if(reservationDate < 10){
				reservationDate = '0' + reservationDate;
			}
			
			reservationDay = reservationYear + '-' + reservationMonth + '-' + reservationDate + ' ' + reservationHour + ":" + reservationMinute;
			
			var memberId = '${member_id}';
			var payMethod = $("#payMethod").val();
			var reservationCode = $("#reservationCode").text();
			var movieName = $("#movieName").text();
			var pay = parseInt($("#pay").val());
			var email = $("#email").val();
			var buyerName = $("#buyerName").val();
			var tel = $("#tel").val();
			var addr = $("#address").val();
			var post = $("#post").val();
			var seatAll = "";
			var hour = "";
			for(var i = 1; i <= 8; i++){
				if($("#seat"+i).val() != ""){
					seatAll += $("#seat"+i).val()
					if($("#seat"+(i+1)).val() != "")
					seatAll	+= ', ';
				}
			}			

			//여기
			$.ajax({
				type:'get',
				url:'/ticket/reservation/' + reservationCode,
				headers: {
					"Content-Type" : "application/json",
				},
				dataType:'json',
				data : {"memberId":memberId, "movieId":movieId, "theaterId":theaterId, "plexNumber":plexNum, "startTime":veiwDay,
						"ticketCnt":totalSeat, "seat":seatAll, "pay":pay, "payMethod":payMethod, "reservationDate":reservationDay},
				success : function(result){
					alert("결제가 완료되었습니다.");
					//location.href = "/ticket/" + theaterName
				}
			});		
		}
		
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
				reservationCode = $("#years").val() + $("#months").val() 
								+ $("#dates").val() + movieId + theaterId + frm.plex.value;

			}
		}
		
		function lastStep() {
			var start_time = frm.start_time.value;
			hour = start_time.split(':')[0];
			
			
			if(7 <= hour && hour <= 9 ) {
				timeStatus = 1;
			} else if (0 <= hour && hour <= 6) {
				timeStatus = 2;
			}
			
			var intHour = parseInt(hour);
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
				
				reservationCodeGenerator();
				payGenerator();
				
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
		
		function reservationCodeGenerator() {
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
			
			$('#reservationCode').text(reservationCode);
			$('#reservationcode').val(reservationCode);
			$('#theaterid').val(theaterId);
			
			
		}
		
		function payGenerator() {
			
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
			
			if(seatYouthCnt > 0) {
				discountMoney += 1000*seatYouthCnt
			}
			
			if(timeStatus == 1) {
				discountMoney += 2000*totalSeat;
			} else if(timeStatus == 2) {
				discountMoney += 1000*totalSeat;
			}
			
			totalPrice = economy + standard + prime + handicapped + sweetbox;
			price = economy + standard + prime + handicapped + sweetbox - discountMoney
			
			$('#pay').val(price);
			$('#originPay').text(totalPrice);
			$('#discountPay').text(discountMoney);
		}
		
		function reservationSeat() {

			if(frm.months.value < 10 && frm.months.value.length < 2 ){
				frm.months.value = '0' + frm.months.value;
			}
			
			if(frm.dates.value < 10 && frm.dates.value.length < 2){
				frm.dates.value = '0' + frm.dates.value;
			}
			
			startTime = frm.years.value +'-' + frm.months.value + '-' + frm.dates.value + ' ' + frm.start_time.value;
			
			$.ajax({
				type:'get',
				url:'/ticket/' + theaterId +'/' + plexNum + '/seat/' + startTime,
				headers: {
					"Content-Type" : "application/json",
				},
				dataType:'json',
				data : {"seat1":seat1, "seat2":seat2, "seat3":seat3, "seat4":seat4, "seat5":seat5, "seat6":seat6, "seat7":seat7, "seat8":seat8},
				success : function(result){
					setSeat(result.l, result.i);
				}
			});
		}
		
		function reservationCancel() {

			if(frm.months.value < 10 && frm.months.value.length < 2 ){
				frm.months.value = '0' + frm.months.value;
			}
			
			if(frm.dates.value < 10 && frm.dates.value.length < 2){
				frm.dates.value = '0' + frm.dates.value;
			}
			
			startTime = frm.years.value +'-' + frm.months.value + '-' + frm.dates.value + ' ' + frm.start_time.value;
			
			
			$.ajax({
				type:'get',
				url:'/ticket/cancel/' + theaterId +'/' + plexNum + '/' + startTime,
				headers: {
					"Content-Type" : "application/json",
				},
				dataType:'json',
				data : {"seat1":seat1, "seat2":seat2, "seat3":seat3, "seat4":seat4, "seat5":seat5, "seat6":seat6, "seat7":seat7, "seat8":seat8},
				success : function(result){
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
			$("#wrapper").css("margin","0px auto");
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
			$('#movieName').text(title);
			
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
					+ '"' + this.title + '", ' + '"' + this.movie_id + '"' + ")'><div class='grade grade" + this.rating +"'>"
					+ this.rating + "</div><span>" + this.title + "</span></div>";
			});
			
			result += "</div>";

			document.getElementById("movie_list").innerHTML = result;
		}
		
//		$( "body" ).click(function( event ) {
//			alert(event.target.nodeName);
//		});
		
		function setTimetableList(time, plex, extraSeatNum) {
			
			var timetableTime = "";
			var result = "";
			var number = 1;
			if(time == "" || plex == ""){
				result += '<div></div>';
				document.getElementById("timetable").innerHTML = result;
			}else {
				$(plex).each(function() {
					
					result += '<div style="margin-left: 20px; margin-bottom: 5px; float:none; clear:both;">' 
						+ '<span style="color: #993800; font-weight: bold; float:left">'
						+ this.plex_type + '&nbsp;</span> <span style="font-weight: bold; float:left;">' + this.plex_number + '관 &nbsp;</span><span style="float:left;">'
						+'(총</span><div style="float:left;">' + this.plex_seat_cnt + '</div>석)</div>';
					
					var totalSeatNum = this.plex_seat_cnt;
						$(time).each(function() {
							
							if(this.plex_number == number){
								
								
								result += '<div id="time" onclick="timetable(' + "'" + this.start_time.substring(11,16) + "',  " 
										+ "'" + this.plex_number + "'," + "'" + totalSeatNum + "'" +');"><span class="timetable_time">' 
										+ this.start_time.substring(11,16) + '</span><div class="timetable_seat">';
										
										timetableTime = this.start_time;
										$(extraSeatNum).each(function() {
											if(timetableTime == this.startTime && number == this.plexNumber){
												result += this.extraSeatCount +'석</div></div>';
											} else if(timetableTime != this.startTime && number != this.plexNumber) {
												result += '0석</div></div>';
											}
										});
										
										
							}
							
							document.getElementById("timetable").innerHTML = result;
						});
					number++;
				});
			}

		}
		
		
		function timetable(time, plex ,totalSeatNum){
			
			var remainSeatNum = $(event.target).parent().children('DIV').text();
			
			$("#remainSeat").text(remainSeatNum);
			$("#seat_totcnt").text(totalSeatNum);
			
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
			
			var startDay = frm.years.value +'-' + frm.months.value + '-' + frm.dates.value + ' ' + frm.start_time.value;

			$.ajax({
				type:'get',
				url:'/ticket/plex/' + plexNum + '/' + startDay,
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
					setTimetableList(result.l,result.t,result.n);
					
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

		
