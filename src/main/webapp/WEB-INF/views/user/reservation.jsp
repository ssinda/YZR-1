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
		margin-bottom: 10px; 
		font-weight: bold; 
		float: left;
	}
</style>
<c:if test="${ member_id == null }">
	<script>
		<%response.sendRedirect("/login");%>
	</script>
</c:if>
<fmt:formatDate value="${today}" pattern="yyyy-MM-dd HH:mm" var="today"/>
<div class="content" style="margin-left: 250px;">
	<div style="height: 34px; line-height: 34px;">
		<span style="font-size: 20px;">예매내역</span><span id="reservationTotal" style="margin-left: 5px;">${reservationTotal}건</span>
	</div>
	<div id="reservationList" style="border: 1px solid #aaa; border-radius: 15px;">
		
	</div>
	
	<!-- 페이징 -->
	<div id="page" style="height: auto; width: 100%; display: inline-block; margin: 0 auto;"></div>

</div>

<script>
	var currentPage = 1;
	var startPage = 1;
	var endPage = 1;
	var totalPage;
	
	function setReservationList(r,rm){
		var a = $("#reservationList");
		var result = "";
		var date = null;
		a.html();
		a.show();
		$("#page").show();
		
		result += '<div style="width: 888px; height:50px; line-height: 50px; border-bottom: 1px solid #aaa;">'
				+ '<div style="width: 200px; text-align: center; font-size: 15px; border-right: 1px solid #aaa; float: left;">예매번호</div>'
				+ '<div style="width: 688px; text-align: center; font-size: 15px; margin-left: 200px;">예매정보</div>'
				+ '</div>';
		
		
		$(r).each(function(){
		
			result += '<div style="height: 130px; border-top: 1px solid #aaa;">'
					+ '<div style="width: 200px; line-height: 130px; float: left; text-align: center; border-right: 1px solid #aaa;">'
					+ '<span>' + this.reservation_code + '</span>'
					+ '</div>';
					
			for(var j=0; j<rm.length; j++){
				if(this.movie_id == rm[j].movie_id){
					
					result += '<div style="height: 130px; width: 400px; float: left;">'
							+ '<span style="height: 128px; width: 105px; display: block; margin: 0; float: left;"><a href="/movie/${r.movie_id}"><img style="height: 100%; width: 100%; vertical-align: baseline;" src="/resources/images/poster/'+rm[j].poster+'"></a></span>'
							+ '<span style="height:30px; display: block; margin-left: 115px; margin-bottom: 10px; padding-top:10px; font-weight: bold;"><a href="/movie/' + this.movie_id + '">' + rm[j].title + '</a></span>'
							+ '<span class="reservationInfo">관람극장</span>'
							+ '<span style="height:20px; display: block; margin-left: 181px; margin-bottom: 10px;">' + this.theater_name + this.plex_number + '관&nbsp/&nbsp' + this.ticket_cnt + '명</span>'
							+ '<span class="reservationInfo">관람일시</span>'
							+ '<span style="height:20px; display: block; margin-left: 181px; margin-bottom: 10px;">' + this.start_time + '</span>'
							+ '<span class="reservationInfo">결제</span>'
							+ '<span style="height:20px; display: block; margin-left: 181px;">'+ this.pay_method + '&nbsp/&nbsp' + this.pay + '원</span>'
							+ '</div>'
							+ '<div style="height: 130px; line-height: 130px; text-align: center; float: left;">'
							+ '<span style="display: inline-block; margin-top: 48px;"><button class="btn btn-danger">예매확인</button></span>';
					
					if(this.start_time > "${today}"){
						result += '<span style="display: inline-block; margin-top: 48px; margin-left: 20px;"><button class="btn btn-danger" onclick="">예매취소</button></span>';
					}
					
					result += '</div>';
				}
				
			}
			
			result += '</div>';
			
		});

		a.html(result);
	}
	
	function setPagePrint(pm){
		var str = "";
		
		str += "<ul class='pagination'>";
		
		if(pm.prev){
			str += "<li><a href='javascript:getReservationList("+(pm.startPage-1)+")'>&lt;</a> </li>";
		}
		
		for(var i = pm.startPage; i<=pm.endPage; i++){
			if(i == pm.criteria.page){
				str+="<li class='active'><a href='#'>" + i + "</a></li>";
			}else{
				str+="<li><a href='javascript:getReservationList("+i+")'>" + i + "</a></li>";
			}
			
		}
		
		
		if(pm.next){
			str += "<li><a href='javascript:getReservationList("+(pm.endPage+1)+")'>&gt;</a> </li>";
		}
		str += "</ul>";
		document.getElementById("page").innerHTML = str;
		
	}
	
	function getReservationList(page){
		
		if(page==null){
			page = currentPage; 
		}
		currentPage = page;
		
		$.ajax({
			type : 'get',
			url : '/user/reservation/'+page,
			headers : {
				"Content-Type" : "application/json"
	//			"X-HTTP-Method-Override" : "GET",
			},
			dataType : 'json',
			data : '',
			success : function(result){
				endPage = result.p.endPage; 
				startPage = result.p.startPage;
				if(totalPage<result.p.totalPage){
					getReservationList(result.p.totalPage);
				}
				totalPage = result.p.totalPage;
				
				setReservationList(result.l.reservationList, result.l.reservationMovie);
				setPagePrint(result.p);
				$("#reservationTotal").val(result.total+"건");
				
			}
		});
		
	}
	
	getReservationList();
	
	function deleteReservation(reservation_code){
		
		if(page==null){
			page = currentPage; 
		}
		currentPage = page;
		
		$.ajax({
			type : 'get',
			url : '/search/movie/'+page,
			headers : {
				"Content-Type" : "application/json"
	//			"X-HTTP-Method-Override" : "GET",
			},
			dataType : 'json',
			data : '',
			success : function(result){
				endPage = result.p.endPage; 
				startPage = result.p.startPage;
				if(totalPage<result.p.totalPage){
					getMovieList(result.p.totalPage);
				}
				totalPage = result.p.totalPage;
				
				setMovieList(result.l,result.am);
				setPagePrint(result.p);
				
			}
		});
		
	}
	
	Date.prototype.format = function(f) {
	    if (!this.valueOf()) return " ";
	 
	    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
	    var d = this;
	     
	    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
	        switch ($1) {
	            case "yyyy": return d.getFullYear();
	            case "yy": return (d.getFullYear() % 1000).zf(2);
	            case "MM": return (d.getMonth() + 1).zf(2);
	            case "dd": return d.getDate().zf(2);
	            case "E": return weekName[d.getDay()];
	            case "HH": return d.getHours().zf(2);
	            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
	            case "mm": return d.getMinutes().zf(2);
	            case "ss": return d.getSeconds().zf(2);
	            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
	            default: return $1;
	        }
	    });
	};
	 
	String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
	String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
	Number.prototype.zf = function(len){return this.toString().zf(len);};

</script>
<%@include file="../include/footer.jsp"%>