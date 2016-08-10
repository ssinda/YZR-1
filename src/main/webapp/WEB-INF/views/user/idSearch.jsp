<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header.jsp"%>
<title>idSearch</title>
<script type="text/javascript">
	function check(){
		var form = document.getElementById("idSearch");
		submit();
	}
</script>
</head>

<div align="center" >
	<h1>아이디 찾기</h1>
	<form id="idSearch" name="idSearch" action="/user/idSearch" method="post">
		<table class="table" style="width:400px;" >
			<tr>
				<th>이름</th>
				<td><input type="text" id="member_name" class="form-control" name="member_name" ></td>
				<td rowspan="2"><input type="button" class="btn btn-danger" onclick="check()" value="확인"></td>
			</tr>
			<tr>
				<th>이메일</th><td><input type="text" id="email" class="form-control" name="email" ></td>
			</tr>
		</table>
	</form>
</div>
<hr>
<script type="text/javascript">
	function check(){
		isIdCheck = true;
		$.ajax({
			type : 'get',
			url : 'user/member/idSearch',
			headers : {"Content-Type" : "application/json"},
			data : {"member_id" : $("#member_id").val(), "email" : $("#email").val()},
			dataType : 'json',
			success : function(result){
				if(result == true){
					$("#checkMsg").html("이미 등록된 아이디입니다.");
				}else{
					$("#checkMsg").html("사용하실 수 있는 아이디입니다");
				}
			}
		});
	}
</script>

<%@include file="../include/footer.jsp"%>