<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="./include/header.jsp"%>
<div id="wrap_content" style="min-height: 100%; position: relative;">
	<div id="content" style="padding-bottom: 50px; float:left;">
		<div id="notice" style="border-bottom:2px solid #000; margin-top:10px;" class="carousel up" data-ride="carousel" data-interval="3000">
			<c:forEach begin="0" end="4" varStatus="n">
				<ol class="carousel-indicators">
					  <li data-target="#myCarousel" data-slide-to="${n.index}"></li>
				</ol>
			</c:forEach>
			<div class="carousel-inner" role="listbox">
				<c:forEach items="${notice}" var="notice" varStatus="noticeN">
					<c:choose>
						<c:when test="${noticeN.first}">
							<div class="item active">
								<i class="fa fa-bullhorn" aria-hidden="true" style="font-weight:bold; font-size:15px;"> &nbsp공지사항 </i>
								&nbsp [${notice.getNotice_category()}]
								&nbsp <a href=""> ${notice.getNotice_title()} </a>
								<span style="float:right;"> ${notice.getNotice_date()} </span>
							</div>
						</c:when>
						<c:otherwise>
							<div class="item">
								<i class="fa fa-bullhorn" aria-hidden="true" style="font-weight:bold; font-size:15px;"> &nbsp공지사항 </i>
								&nbsp [${notice.getNotice_category()}]
								&nbsp <a href=""> ${notice.getNotice_title()} </a>
								<span style="float:right;"> ${notice.getNotice_date()} </span>
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>		
		
		<form class="form-inline" role="form" style="text-align:left; margin-top:20px;">
			<input type="text" id="search" name="search" class="form-control" placeholder="검색어를 입력하세요" style="width:930px;"/>
			<button type="submit" id="btn_seacrch" name="btn_seacrch" class="btn btn-danger" style="width:204px;">검색</button>
		</form>
		
		<span style="font-weight:bold; font-size:36px; float:left; margin-top:10px;">추천영화</span>
		<div style="border:1px solid #000; margin-top:60px;"></div>
		
		<c:choose>
			<c:when test="${ member_id != null }">
				<c:set value="${ rec_movie }" var="chart"></c:set>
			</c:when>
			<c:otherwise>
				<c:set value="${ rec_basic }" var="chart"></c:set>
			</c:otherwise>
		</c:choose>
		
		<c:if test="${chart == null }">
			<span style="font-weight:bold; font-size:15px;"> ${basic_title} </span>
			<div id="rec_movie" style="width:250px; height:380px; text-align:left; margin-top:5px;">
				<a href=""><img src="/resources/poster/${chart.poster}" style="width:249px; height:300px;"/></a>
				<span style="font-weight:bold; font-size:15px;"> ${basic_title}  </span><br/>
				관람등급 : <br/>
				예매율 : <br/>
				개봉날짜 : <br/>
				<input type="button" id="res_btn" name="res_btn" class="btn btn-danger btn-sm" onclick="" value="예매" style="width:250px; display:inline-block; margin-top:7px;"/>
			</div>
		</c:if>
		
		<c:forEach items="${chart}" var="chart" begin="0" end="2" varStatus="i">
			<div style="margin-top:20px; float:left; margin-left:92px; margin-right:92spx; ">
				<div id="m1" style="margin-top:20px;">
					<div id="rec_movie_title" style="border:2px solid #000; width:250px; height:28px; padding-top:2px; background-color:#ff3636; text-align:center;">
						<c:choose>
							<c:when test="${member_id !=null}">
								<span style="font-weight:bold; font-size:15px;">
							</c:when>
							<c:otherwise>
								<span style="font-weight:bold; font-size:15px;">
							</c:otherwise>
						</c:choose>
							<c:choose>
								<c:when test="${ member_id != null }">
									<c:set value="${ rec_title }" var="chart_title"></c:set>
								</c:when>
								<c:otherwise>
									<c:set value="${ basic_title }" var="chart_title"></c:set>
								</c:otherwise>
							</c:choose>
							${chart_title[i.index]}
						</span>
					</div>
					<div id="rec_movie" style="width:250px; height:380px; text-align:left; margin-top:5px;">
						<a href=""><img src="/resources/images/poster/${chart.poster}" style="width:249px; height:300px;"/></a>
						<span style="font-weight:bold; font-size:15px;"> ${chart.title} </span><br/>
						관람등급 : ${chart.rating} <br/>
						예매율 : ${chart.reservation_rate}%<br/>
						개봉날짜 : ${chart.open_date} <br/>
						<input type="button" id="res_btn" name="res_btn" class="btn btn-danger btn-sm" onclick="" value="예매" style="width:250px; display:inline-block; margin-top:7px;"/>
					</div>
				</div>
			</div>
		</c:forEach>
		
		<h2 id="event_line" style="margin-top:550px;">
			<div style="border-top:1px solid #f00; border-bottom:1px solid #f00; width:503px; height:5px; float:left; margin-top:19px;"></div>
				<span style="float:left; color:red; font-size:40px;"><a id="event_li" href="/event">EVENT</a></span>
			<div style="border-top:1px solid #f00; border-bottom:1px solid #f00; width:503px; height:5px; float:left; margin-top:19px;"></div>
		</h2>
		
		<div id="hold_img1" style="margin-top:25px; float:left;">
			<div style="float:left; margin-right:6px;">
				<a href=""><img style="width:280px; height:280px;" src="http://img.cgv.co.kr/Event/Event/2016/0222/1318_web_listbanner_.jpg"></a>
			</div>
			<div style="float:left; margin-right:6px;">
				<a href=""><img style="width:280px; height:280px;" src="http://img.cgv.co.kr/Event/Event/2016/0309/web_special_day_listbanner.jpg"></a>
			</div>
			<div style="float:left; margin-right:6px;">
				<a href=""><img style="width:280px; height:280px;" src="http://img.cgv.co.kr/Event/Event/2016/0224/ARTHOUSECLUB_web_listbanner.jpg"></a>
			</div>
			<div style="float:left;">
				<a href=""><img style="width:280px; height:280px;" src="http://img.cgv.co.kr/Event/Event/2016/0224/ARTHOUSECLUB_web_listbanner.jpg"></a>
			</div>
		</div>
		
		<div style="clear:both; float:left; margin-top:25px; width:100%; border:1px solid #000;"></div>
		
		<div id="hold_img2" style="margin-top:25px; float:left;">
			<div style="float:left; margin-right:6px;">
				<a href=""><img style="width:280px; height:280px;" src="http://img.cgv.co.kr/Event/Event/2016/0222/1318_web_listbanner_.jpg"></a>
			</div>
			<div style="float:left; margin-right:6px;">
				<a href=""><img style="width:280px; height:280px;" src="http://img.cgv.co.kr/Event/Event/2016/0309/web_special_day_listbanner.jpg"></a>
			</div>
			<div style="float:left; margin-right:6px;">
				<a href=""><img style="width:280px; height:280px;" src="http://img.cgv.co.kr/Event/Event/2016/0224/ARTHOUSECLUB_web_listbanner.jpg"></a>
			</div>
			<div style="float:left;">
				<a href=""><img style="width:280px; height:280px;" src="http://img.cgv.co.kr/Event/Event/2016/0224/ARTHOUSECLUB_web_listbanner.jpg"></a>
			</div>
		</div>
	</div>	
</div>

<script type="text/javascript">

    $(document).ready(function() {
        $('#notice').carousel('cycle');
    });

</script>
			
<style>
	#event_li{
		color:red;
		text-decoration: none;
	}
	
	.nav-tabs{ border:0px }
	
	.nav-tabs > li > a:hover,
	.nav-tabs > li > a:focus{
	background-color:#fff;
	border:0px
	}
	
	.carousel-inner > .item > a,
	.carousel-inner > .item > a:hover{
	text-decoration: none;
	color: black;
	}
</style>
	
<%@include file="./include/footer.jsp"%>