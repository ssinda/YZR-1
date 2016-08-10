<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header.jsp"%>
<title>pwSearch</title>
<script type="text/javascript">
	function check(){
		isIdCheck = true;
		alert($("#member_id").val());
		var member_id = $("#member_id").val();
		var answer = $("#answer").val();
		var question = $("#question").val();
		
		if($("#member_id").val() == null || $("#member_id").val() == ""){
			alert("아이디를 입력하세요.");
		}else if( $("#question").val() == null || $("#question").val() == ""){
			alert("질문을 선택하세요.");
		}else if($("#answer").val() == null || $("#answer").val() == ""){
			alert("질문에 대한 답변을 작성하세요.");
		}else{
			$.ajax({
				type : 'post',
				url : '/user/pwFind',
				headers : {"Content-Type" : "application/json"},
				data : {"member_id" : member_id, "question" : question, "answer" : answer},
				dataType : 'json',
				success : function(result){
					if(result == true){
						alert("success");
						
					}else{
						alert("fail");
					}
				}
			});
		}
	}
</script>
</head>

<div align="center" >
	<h1>비밀번호 찾기</h1>
	<form id="pwSearch" name="pwSearch" method="post">
		<table class="table" style="width:400px;" >
			<tr>
				<th>아이디</th>
				<td><input type="text" id="member_id" class="form-control" name="member_id" placeholder="찾은 아이디 보여주기"></td>
			</tr>
			<tr>
				<th>비밀번호찾기 질문</th>
				<td>
					<select id="question" name="question" class="form-control" >
						<option value="">질문을 선택하세요</option>
						<option value="첫번째로 사용했던 핸드폰 배터리 용량은?">첫번째로 사용했던 핸드폰 배터리 용량은?</option>
						<option value="내가 다닌 컴퓨터학원 데스크 직원 이름은?">내가 다닌 컴퓨터학원 데스크 직원 이름은?</option>
						<option value="가장 감명깊게 읽은 책의 총페이지수는">가장 감명깊게 읽은 책의 총페이지수는?</option>
						<option value="다녔던 고등학교의 교가 작곡가는?">다녔던 고등학교의 교가 작곡가는?</option>
						<option value="가장 친한친구의 아버지 또는 어머니의 발사이즈는?">가장 친한친구의 아버지 또는 어머니의 발사이즈는?</option>
					</select>
				</td>
				<td id="checkQuestion"></td>
			</tr>
			<tr>
				<th>비밀번호찾기 답변</th>
				<td><input type="text" id="answer" name="answer" class="form-control" ></td>
				<td id="checkAnswer"></td>
			</tr>
		</table>
		<input type="button" onclick="check()" class="btn btn-danger" value="확인" >
	</form>
</div>
<hr>

<%@include file="../include/footer.jsp"%>