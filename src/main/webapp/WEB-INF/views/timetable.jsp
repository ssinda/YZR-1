<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%  %>
<%@include file="./include/header.jsp" %>
<div id="wrap_content" style="min-height: 100%; position: relative;">
   <div id="content" style="padding-bottom: 150px; float: left; width:100%">
      <div id="theater_table" style="border:2px solid gray; border-radius:20px; height:400px; background-color:#4B4B4B;">
         <span style="font-weight:bold; color:white; font-size:30px; margin-top:35px; margin-left:110px; float:left;">자주가는 CGV</span>
         <div id="like_th_place" style="float:left; margin-top:25px; margin-left:20px;">
            <c:forEach items="${like_th}" var="like_th" begin="0" end="4" varStatus="i">
               <div class="th_group" style="text-align:center; line-height:50px;">
                  <span class="th_name" style="color:white; font-weight:bold; font-size:20px;" value="${like_th.theater_id}">
                     ${like_th.theater_name}
                  </span>
               </div>
            </c:forEach>
            <c:if test="${fn:length(like_th) < 5}">
               <c:forEach begin="0" end="${4-fn:length(like_th)}">
                  <div class="th_group">
                     <span style="color:white; font-weight:bold; font-size:20px;">
                     </span>
                  </div>
               </c:forEach>
            </c:if>
         </div>
         
         <ul id="location" class="nav nav-tabs list-inline" style="clear:both; width:1140px; text-align:center; margin-top:20px; float:left;">
            <li class="lo_li" onclick="getTheater_list('서울')">서울</li>
            <li class="lo_li" onclick="getTheater_list('경기')">경기</li>
            <li class="lo_li" onclick="getTheater_list('인천')">인천</li>
            <li class="lo_li" onclick="getTheater_list('강원')">강원</li>
            <li class="lo_li" onclick="getTheater_list('대전/충청')">대전/충청</li>
            <li class="lo_li" onclick="getTheater_list('광주/전라')">광주/전라</li>
            <li class="lo_li" onclick="getTheater_list('대구/울산/부산')">대구/울산/부산</li>
            <li class="lo_li" onclick="getTheater_list('경상')">경상</li>
            <li class="lo_li" onclick="getTheater_list('제주')">제주</li>
         </ul>
         
         <ul id="theater" class="nav nav-tabs list-inline" style="clear:both; width:1140px; margin-top:20px; float:left;">
            <!-- 지역에 따른 -->
         </ul>
      </div>
      
      <span id="theater_name" style="font-weight:bold; font-size:36px; float:left; margin-top:30px;"></span>
      <span id="theater_id" style="display:none;"></span>
      <div style="border:2px solid #000; width:1140px; margin-top:20px; clear:both;"></div>
      
      <div id="play_date" style="clear:both; margin-top:20px; margin-left:260px;">
         <i id="cal" class="fa fa-calendar fa-3x" aria-hidden="true" style="float:left; margin-top:6px;"></i>
         <div id="date_group" style="float:left; width:600px; margin-left:5px;">
            <!-- setTheater에서 출력 -->
         </div>
      </div>
      
      <div style="border:1px solid #000; margin-top:25px; width:100%; float:left;"></div>
      
      <div style="margin-top:15px; margin-left:20px; float:left;">
         <i class="fa fa-sun-o fa-1x" aria-hidden="true" style="color:red"><span style="color:black; font-weight:bold;">조조</span></i> &nbsp
         <i class="fa fa-moon-o fa-1x" aria-hidden="true" style="color:blue"><span style="color:black; font-weight:bold;">심야</span></i> &nbsp
         <i class="fa fa-arrow-circle-o-right fa-1x" aria-hidden="true" style="color:green"><span style="color:black; font-weight:bold;">일반</span></i>
         <span style="margin-left:50px; color:#4B4B4B;">* 시간을 클릭하시면 빠른 예매를 하실 수 있습니다.</span>
      </div>
      
      <div id="movie_place">
         <!-- timetable ajax구간 -->
      </div>
      
      <div style="border:1px solid #000; margin-top:25px; clear:both; float:left; width:100%;"></div>
      
   </div>
</div>

<script>
   var date = new Date();
   
   var month = date.getMonth() + 1;
   var day = date.getDate();
   
   var dayOfWeek = date.getDay();
   
   function getTheater_list(location){
      if(location == null || location == ""){
         location = "서울";
      }
      $.ajax({
         type : 'get',
         url : '/timetable/theater',
         headers : {
         "Content-Type" : "application/json",
         },
         dataType : 'json',
         data : {"location" : location},
         success : function(theater_list){
            setTheater_list(theater_list);
         }
      });
   }
   
   getTheater_list();
   
   function setTheater_list(theater_list){
      var result = "";
      for(var i=0; i<theater_list.length; i++){
         result += '<li id="th_tt'+i+'" class="th_li" onclick="setTheater_name('+"'"+theater_list[i].theater_name+"'"+","+"'"+theater_list[i].theater_id+"'"+')">'
            + theater_list[i].theater_name
            + '</li>';
      }
      
      document.getElementById("theater").innerHTML = result;
   }
   
   function setTheater_name(theater_name, theater_id){
      document.getElementById("theater_name").innerHTML = theater_name;
      document.getElementById("theater_id").value = theater_id;
      
      viewDate();
      $(".date_div").css("color", "black");
      $("#0d").css("color", "red");
      getTimetable(theater_id, $("#month0").val()+"-"+$("#day0").val());
   }
   
   setTheater_name("CGV강남","T001");
   
   function setViewDate(){
      var cal_date = "";
      
      for(var cd=0; cd<5; cd++){
         cal_date += '<div id="'+cd+'d" class="date_div">'
            + '<div style="float:left;">'
            + '<span id="month'+cd+'" style="margin-top:8px; float:left;">'
            + '</span>'
            + '<span style="margin-top:8px; float:left;">'
            + '월'
            + '</span>'
            + '<br/>'
            + '<span id="dayoftheweek'+cd+'" style="margin-left:6px;">'
            + '</span>'
            + '</div>'
            + '<span id="day'+cd+'" style="float:left; font-size:40px;">' 
            + '</span>'
            + '</div>';
      }
      
      document.getElementById("date_group").innerHTML = cal_date;
      $("#0d").css("color", "red");
   }
   setViewDate();
   
   $(document).ready(function(){
      
      for(var a=0; a<5; a++){
         day = date.getDate();
         month = date.getMonth() + 1;
         dayOfWeek = date.getDay(dayOfWeek);
         if(month < 10){
            month = "0" + month;
         }
         $("#month"+a).html(month);
         $("#month"+a).val(month);
         if(day < 10){
            day = "0" + day;
         }
         $("#dayoftheweek"+a).html(getDay(dayOfWeek));
         $("#dayoftheweek"+a).val(getDay(dayOfWeek));
         
         $("#day"+a).html(day);
         $("#day"+a).val(day);
         
         day++;
         dayOfWeek++;
         date.setDate(date.getDate()+1);
      }
   })
   
   function viewDate(){
      
      var nday = "";
      var ndayoftheweek = "";
      var nmonth = "";
      
      for(var a=0; a<5; a++){
         nday = $("#day"+a).val();
         ndayoftheweek = $("#dayoftheweek"+a).val();
         nmonth = $("#month"+a).val();
         
         $("#day"+a).html(nday);
         $("#day"+a).val(nday);
         $("#dayoftheweek"+a).html(ndayoftheweek);
         $("#dayoftheweek"+a).val(ndayoftheweek);
         $("#month"+a).html(nmonth);
         $("#month"+a).val(nmonth);
      }
   }
   
   function getDay(dayOfWeek){
      var date = "";
      
      if(dayOfWeek > 6){
         dayOfWeek = dayOfWeek - 7 ;
      }
      
      switch(dayOfWeek){
         case 0 : date = "일"; break;
         case 1 : date = "월"; break;
         case 2 : date = "화"; break;
         case 3 : date = "수"; break;
         case 4 : date = "목"; break;
         case 5 : date = "금"; break;
         case 6 : date = "토"; break;
      }
      
      return date;
   }
   
   
   $(".date_div").click(function(){
      var cday = "";
      var ti = document.getElementById("theater_id").value;
      
      $(".date_div").css("color","black");
      $(this).css("color","red");
      var temp = $(this).attr('id').substring(0, 1);
         cday = month + "-" + $("#day" + temp).val();
         getTimetable(ti, cday);
   })
   
   $(".th_name").click(function(){
      var ti = $(this).attr("value");
      var tn = $(this).text();
      $(".th_name").css("color","white");
      $(this).css("color","red");
      setTheater_name(tn, ti);
   })
   
   if(day<10){
      getTimetable("T001", month+"-0"+day);
   }else if(day>=10){
      getTimetable("T001", month+"-"+day);
   }   
   
   function getTimetable(theater_id, cday){
      $.ajax({
         type : 'get',
         url : '/timetable/tt',
         headers : {
         "Content-Type" : "application/json",
         },
         dataType : 'json',
         data : {
            "theater_id" : theater_id,
            "day" : cday   
         },
         success : function(tt){
            setTimetable(tt.tt, tt.total);
            getMovieInfo(theater_id, cday);
            getMovieGenre(theater_id, cday);
            getPlexInfo(theater_id, cday);
         }
      });
   }
   
   
   function setTimetable(tt, total){
      var timetable = "";
      $("#movie_place").html("");
      var plex = "";
      
      for(var a=0; a<total; a++){
         plex += '<div class="movie_time" style="float:left; margin-top:15px;">'
            + '<div style="border-top:1px solid #000; margin-top:10px; width:1140px;">'
            + '</div>'
            + '<div id="movie" style="float:left; margin-top:15px; margin-left:20px;">'
            + '<div id="grade_circle'+a+'" style="margin-top:3px; float:left; border:1px solid white; border-radius:20px; width:25px; height:25px; text-align:center; padding-top:1px; color:white;">'
            + '</div>'
            + '<span id="movie_title'+a+'" style="margin-left:20px; font-size:20px;">'
            + '</span>'
            + '<span id="movie_status'+a+'" style="margin-left:10px; border:2px solid #6799FF; border-radius:3px; color:#6799FF; font-weight:bold; font-size:13px;">'
            + '</span>'
            + '<span>'
            + '&nbsp <span id="genre'+a+'"></span> / <span id="runtime'+a+'"></span> / <span id="open_date'+a+'"></span> 개봉'
            + '</span>'
            + '<br/>'
            + '<i class="fa fa-caret-right" aria-hidden="true" style="margin-top:10px;">'
            + '<span>'
            + '&nbsp <span id="plex_type'+a+'"></span> > '
            + tt[a][0].plex_number
            + '관 > <span id="seat_cnt'+a+'"></span>'
            + '</span>'
            + '</i>'
            + '<br/>'
            + '<div id="time_table'+a+'">'
            + '</div>'
            + '</div>'
            + '</div>';
         $("#movie_place").html(plex);
         
               
         
      }
      
      var mor = '<i class="fa fa-sun-o fa-1x" aria-hidden="true" style="color:red"><span style="color:black; font-weight:bold;">조조</span></i>';
      var night = '<i class="fa fa-moon-o fa-1x" aria-hidden="true" style="color:blue"><span style="color:black; font-weight:bold;">심야</span></i>';
      var nor = '<i class="fa fa-arrow-circle-o-right fa-1x" aria-hidden="true" style="color:green"><span style="color:black; font-weight:bold;">일반</span></i>';
      
      for(var a=0; a<total; a++){
         timetable = "";   
         for(var b=0; b < tt[a].length; b++){
            timetable += '<li class="time_li">'
               + '<a href="#">'
               + '<span style="font-size:15px; display:block;">'
               + tt[a][b].start_time.substring(11,16)
               + '</span>'
               + '<span id="pt'+a+b+'">'
               + '</span>'
               + '</a>'
               + '</li>';
         }
         $("#time_table"+a).html(timetable);
         for(var c=0; c < tt[a].length; c++){
            var tempHour = tt[a][c].start_time.substring(11,13); 
            
            if(tempHour > 06 && tempHour < 10){
               $("#pt"+a+c).html(mor);
            }else if(tempHour >= 10 && tempHour < 24){
               $("#pt"+a+c).html(nor);
            }else if(tempHour == 24 || (tempHour >= 01 && tempHour <= 06)){
               $("#pt"+a+c).html(night);
            }
         }
      }
   }
   
   function getMovieInfo(theater_id, cday){
      $.ajax({
         type : 'get',
         url : '/timetable/tt/movieInfo',
         headers : {
         "Content-Type" : "application/json",
         },
         dataType : 'json',
         data : {
            "theater_id" : theater_id,
            "day" : cday   
         },
         success : function(movie_info){
            setMovieInfo(movie_info);
         }
      });
   }
   
   function setMovieInfo(movie_info){
      for(var i=0; i<movie_info.length; i++){
         $("#movie_title"+i).html(movie_info[i].title);
         $("#grade_circle"+i).html(movie_info[i].rating);
         if(movie_info[i].rating == "18"){
            $("#grade_circle"+i).css("background-color","red");
         }else if(movie_info[i].rating == "15"){
            $("#grade_circle"+i).css("background-color","orange");
         }else if(movie_info[i].rating == "12"){
            $("#grade_circle"+i).css("background-color","green");
         }
         if(movie_info[i].status == "play"){
            $("#movie_status"+i).html("현재상영중");
         }else if(movie_info[i].status == "schedule"){
            $("#movie_status"+i).html("상영예정")
         }
         $("#runtime"+i).html(movie_info[i].runtime + "분");
         $("#open_date"+i).html(movie_info[i].open_date);
      }
   }
   
   function getMovieGenre(theater_id, day){
      $.ajax({
         type : 'get',
         url : '/timetable/tt/movieGenre',
         headers : {
         "Content-Type" : "application/json",
         },
         dataType : 'json',
         data : {
            "theater_id" : theater_id,
            "day" : day   
         },
         success : function(genre){
            setMovieGenre(genre);
         }
      });
   }
   
   function setMovieGenre(genre){
      for(var i=0; i<genre.length; i++){
         $("#genre"+i).html(genre[i].movie_genre);
      }
   }
      
   function getPlexInfo(theater_id, day){
      $.ajax({
         type : 'get',
         url : '/timetable/tt/plexInfo',
         headers : {
         "Content-Type" : "application/json",
         },
         dataType : 'json',
         data : {
            "theater_id" : theater_id,
            "day" : day   
         },
         success : function(plex){
            setPlexInfo(plex);
         }
      });
   }
   
   function setPlexInfo(plex){
      for(var i=0; i<plex.length; i++){
         $("#plex_type"+i).html(plex[i].plex_type);
         $("#seat_cnt"+i).html("총 "+plex[i].plex_seat_cnt+"석");
      }
   }
</script>

<style>
   #th1{
      margin-left:0px;
   }
   .th_group{
      border:5px double white; 
      width:130px; 
      height:60px; 
      float:left; 
      margin-left:5px;
      cursor:pointer;
   }
   .time_li:FIRST-CHILD{
      border-left:1px solid #BDBDBD;
   }
   .time_li{
      margin-top:10px;
      float:left;
      border-top:1px solid #BDBDBD;
      border-right:1px solid #BDBDBD;
      border-bottom:1px solid #BDBDBD;
      width:70px;
      height:42px;
      text-align:center;
   }
   .time_li > a{
      text-decoration:none;
   }
   .time_li > a:hover{
      color:red;
   }
   .lo_li{
      color:white;
      font-weight:bold;
      font-size:17px;
      margin-left:32px;
      margin-right:32px;
      cursor:pointer;
   }
   .lo_li:HOVER{
      color:red;
   }
   .th_li{
      color:white;
      font-size:14px;
      margin-left:60px;
      margin-right:25px;
      width:200px;
      margin-top:10px;
   }
   .th_li:HOVER{
      color:red;
      cursor:pointer;
   }
   .nav-tabs{
      border:0px;
   }
   .date_div{
      float:left;
      width:80px;
      margin-left:20px;
      margin-right:20px;
   }
   .date_div:HOVER{
      cursor:pointer;
      color:red;
   }
</style>
<% session.setAttribute("menu", null);%>
<%@include file="./include/footer.jsp" %>