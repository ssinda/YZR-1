<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header.jsp"%>
<%@include file="./sidebar.jsp"%>
<style>
th, td{
	text-align: center;
}
th{
	width: 100px;
	text-align: left;
	font-weight: bold;
}
</style>
<div class="content" style="margin-left: 250px;">
	<div>
		<label style="font-size: 15pt;">자주찾는 질문</label><br>
		회원님들께서 가장 자주하시는 질문을 모았습니다.<br>
		궁금하신 내용에 대해 검색해보세요.<br><br><br>
	</div>
	<div class="form-inline">
		<input type="text" class="form-control" id="search" name="search" size="40" placeholder="검색어를 입력해주세요.">
		<input type="submit" class="btn btn-danger" value="검색" onclick="getNoticeList(1);">
	</div><br>
	<div>
		<ul id="searchCategory" class="nav nav-tabs searchCategory" style="font-size: 15px; font-weight: bold; height: 43px; width: 650px;">
			<li class="active"><a id="searchAll" href="javascript:getSearchList()" style="background-color: red; color: white">전체</a></li>
			<li><a id="searchMovie" href="javascript:getMovieList(1)">예매/관람권</a></li>
			<li><a id="searchActor" href="javascript:getActorList(1)">할인혜택</a></li>
			<li><a id="searchDirector" href="javascript:getDirectorList(1)">영화관이용</a></li>
			<li><a id="searchMovie" href="javascript:getMovieList(1)">특별관</a></li>
			<li><a id="searchActor" href="javascript:getActorList(1)">기프트샵</a></li>
			<li><a id="searchDirector" href="javascript:getDirectorList(1)">홈페이지</a></li>
		</ul>
	</div><br>
	<div>총 ${ count }건이 검색 되었습니다.</div><br>
	<table class="table">
		<thead>
			<tr style="background-color: red;">
				<th style="width: 10%;">번호</th>
				<th style="width: 20%;">구분</th>
				<th>제목</th>
				<th style="width: 10%;">조회수</th>
			</tr>
		</thead>
		<tbody id="faq_list">
		</tbody>
	</table>
	<div id="faq_page" style="text-align: center;"></div>
</div>
<script>
	$('#searchCategory a').click(function (e) {
	    $(this).tab('show');
	    $('#searchCategory a').css("background-color", "white");
	    $('#searchCategory a').css("color", "black");
	    $(this).css("background-color", "red");
	    $(this).css("color", "white");
	});
	var currentPage = 1;
	var startPage = 1;
	var endPage = 1;
	var totalPage;
	var search="";
	
	function setNoticeList(data){
		var result;
		$("#faq_list").html("");
		$(data).each(function(){
			result += "<tr>"
			+ "<td>["
			+ this.no
			+ "]</td>"
			+ "<td>"
			+ "<a href='/support/faq/read/" + this.no + "'>"
			+ this.category
			+ "</a>"
			+ "</td>"
			+ "<td>"
			+ this.title
			+ "</td>"
			+ "<td>"
			+ this.view_cnt
			+ "</td>"
			+ "</tr>";
		});		
		$("#faq_list").html(result);
	}
	function getNoticeList(page){
		if(page == null){
			page = currentPage;
		}
		currentPage = page;
		$.ajax({
			type : 'get',
			url : '/support/faq/' + page,
			headers : {
				"Content-Type" : "application/json",
			},
			data : {"search" : $("#search").val()},
			dataType : 'json',
			success : function(result){

				endPage = result.paging.endPage; 
	            startPage = result.paging.startPage;
	            if(totalPage<result.paging.totalPage){
	            	getNoticeList(result.paging.totalPage);
	            }
	            totalPage = result.paging.totalPage;
	            
	            setNoticeList(result.faq_list);
	            setPagePrint(result.paging);
			}
		});
	}
	getNoticeList(currentPage);
	
	function setPagePrint(pm){
		var str = "<ul class='pagination'>";
		if(currentPage > pm.endPage && currentPage > 1){
			getNoticeList(currentPage - 1);
		}
		if(pm.prev){
			str += "<li> <a onclick='getNoticeList("+(pm.startPage-1)+")'>&lt;</a> </li>"
		}
		for(var i = pm.startPage; i <= pm.endPage ; i++){
			if(i == pm.criteria.page){
				str += "<li class='active'><a href='#'>" + i + "</a></li>"
			}else{
				str += "<li><a onclick='getNoticeList("+i+")' style='cursor:pointer'>" + i + "</a></li>"
			}
		}
		if(pm.next){
			str += "<li> <a onclick='getNoticeList("+(pm.endPage+1)+")'>&gt;</a> </li>"
		}
		str += "</ul>"
		document.getElementById("faq_page").innerHTML = str;
	}
</script>
<%@include file="../include/footer.jsp"%>