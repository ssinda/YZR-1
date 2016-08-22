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
	
	function index(){
		location.href="/index";
	}
	function pwEdit(){
		location.href="/user/member/memberPwEdit";
	}
	
	function userUpdate(){
		var frm = document.createElement("form");
		frm.setAttribute("action","/user/member/edit");
		frm.setAttribute("method","post");
		
		var member_name = document.createElement("input");
		member_name.setAttribute("type","text");
		member_name.setAttribute("id","member_name");
		member_name.setAttribute("name","member_name");
		member_name.setAttribute("value", $("#member_name").val());
		frm.appendChild(member_name);
		
		var address = document.createElement("input");
		address.setAttribute("type","text");
		address.setAttribute("id","address");
		address.setAttribute("name","address");
		address.setAttribute("value", $("#address").val());
		frm.appendChild(address);
		
		var email = document.createElement("input");
		email.setAttribute("type","text");
		email.setAttribute("id","email");
		email.setAttribute("name","email");
		email.setAttribute("value", $("#email").val());
		frm.appendChild(email);
		
		var tel = document.createElement("input");
		tel.setAttribute("type","text");
		tel.setAttribute("id","tel");
		tel.setAttribute("name","tel");
		tel.setAttribute("value", $("#tel").val());
		frm.appendChild(tel);
		alert("success");
		frm.submit();
	}
</script>

<style>
	th{background-color:#bf4040; color:white; width:100px;}
	h2{text-align:center;}
</style>

<div id="wrap_content" align="center">
	<div id="content" style="width:480px;" >
		<h2>회원정보 수정페이지</h2>
		<br>
		
		<table class="table" style="width:400px;">
				<tr>
					<th>아이디</th>
					<td>${vo.getMember_id()}</td>
				</tr>
					
				<tr>
					<th>성별</th><td>${vo.getGender()}</td>
				</tr>	
				<tr>
					<th>생년월일</th><td>${vo.getBirthday()}</td>
				</tr>
				<tr>
					<th>닉네임</th><td><input type="text" id="member_name" name="member_name" class="form-control" value="${vo.getMember_name()}"  ></td>
				</tr>	
				<tr>
					<th >집주소</th><td><input type="text" id="address" name="address" class="form-control" value="${vo.getAddress()}"  ></td>
				</tr>	
				<tr>
					<th>이메일</th><td><input type="text" id="email" name="email" class="form-control" value="${vo.getEmail()}"  ></td>
				</tr>	
				<tr>
					<th>연락처</th><td><input type="text" id="tel" name="tel" class="form-control" value="${vo.getTel()}"  ></td>
				</tr>
			</table>
		
		
		<input type="button" onclick="userUpdate()" class="btn btn-danger" value="수정완료">
		<input type="button" onclick="index()" class="btn btn-danger" value="취소">
	</div>	
</div>

<%@include file="../include/footer.jsp"%>