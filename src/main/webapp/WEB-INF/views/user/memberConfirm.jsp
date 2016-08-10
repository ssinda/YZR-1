<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header.jsp"%>
<%@include file="./sidebar.jsp"%>
<c:if test="${ member_id == null }">
	<script>
		<%response.sendRedirect("/login");%>
	</script>
</c:if>
<script>
	function edit(){
		
		var f = document.getElementById("f");
		alert(f.user_id.value);
		f.submit();
	}
	function index(){
		location.href="/index";
	}
</script>

<style>

</style>

<div id="wrap_content" style="min-height:100%; position:relative;" align="center" >
	<div id="content" style="padding-bottom:150px; width:400px;">
		<h1>회원가입을 축하합니다</h1>
		<br>
		
		<table class="table">
			<tr>
				<th class="col-xs-6">아이디</th><td>${vo.getMember_id()}</td>
			</tr>
			<tr>
				<th>이름</th><td>${vo.getMember_name()}</td>
			</tr>
			<tr>
				<th>성별</th><td>${vo.getGender()}</td>
			</tr>
			<tr>
				<th>생년월일</th><td>${vo.getBirthday()}</td>
			</tr>
			<tr>
				<th>주소</th><td>${vo.getAddress()}</td>
			</tr>
			<tr>
				<th>이메일</th><td>${vo.getEmail()}</td>
			</tr>
			<tr>
				<th>연락처</th><td>${vo.getTel()}</td>
			</tr>
		</table>
		<form action="/user/member/edit" id="f" name="f" method="post">
			<input id="user_id" name="user_id" type="hidden" value="${vo.getMember_id()}" >
		</form>
		<input type="button" onclick="edit()" class="btn btn-danger" value="회원정보수정">
		<input type="button" onclick="index()" class="btn btn-danger" value="메인페이지로 이동">
	</div>	
</div>

<%@include file="../include/footer.jsp"%>