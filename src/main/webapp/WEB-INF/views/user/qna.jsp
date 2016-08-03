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
<div class="content" style="margin-left: 250px;">
	<h3>나의 문의내역</h3>
	<div>
		<b>1:1문의</b>
	</div>
	<div style="margin-top:4px;">
		<b style="float: left;margin-top: 4px;">문의조회</b>
		<div class="col-xs-4" style="float:left">
			<input type="text" class="form-control" style="margin-left: 5px;">
		</div>		
		<button class="btn btn-danger" style="height: 30px;">검색하기</button>
	</div>
	
	<table class="table" style="margin-top: 30px;">
			<tr style="background-color: red">
				<th style="text-align: center;">번호</th>
				<th style="text-align: center;">문의cgv</th>
				<th width="50%" style="text-align: center;">제목</th>
				<th style="text-align: center;">등록일</th>
			</tr>	
		</table>
	
	
	
	
</div>
<%@include file="../include/footer.jsp"%>