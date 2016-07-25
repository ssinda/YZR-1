<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="./include/header.jsp"%>
<script src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
<script>
	function initialize() {
		var Y_point = 영화관.xpoint // Y 좌표
		var X_point = 영화관.ypoint // X 좌표
		var zoomLevel = 16; // 지도의 확대 레벨 : 숫자가 클수록 확대정도가 큼
		var markerTitle = 영화관.name // 현재 위치 마커에 마우스를 오버했을때 나타나는 정보
		var markerMaxWidth = 300;
		// 말풍선 내용
		var contentString = 영화관.address;
		var myLatlng = new google.maps.LatLng(Y_point, X_point);
		var mapOptions = {
			zoom : zoomLevel,
			center : myLatlng,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		}
		var map = new google.maps.Map(document.getElementById('map'), mapOptions);
		var marker = new google.maps.Marker({
			position : myLatlng,
			map : map,
			title : markerTitle
		});
		var infowindow = new google.maps.InfoWindow({
			content : contentString,
			maxWidth : markerMaxWidth
		});
		google.maps.event.addListener(marker, 'click', function() {
			infowindow.open(map, marker);
		});
	}
	google.maps.event.addDomListener(window, "load", initialize);
</script>
<div class="content">
	<div id="map" style="width: 500px; height: 300px;"></div>
</div>
<%@include file="./include/footer.jsp"%>