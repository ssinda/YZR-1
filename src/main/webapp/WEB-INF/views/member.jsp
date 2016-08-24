<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="./include/header.jsp"%>
<style>
	th{background-color:#bf4040; color:white;}	
</style>

<script>
	var isIdCheck = false;
	
	// 취소버튼
	function home(){
		location.href="/index";
	}
	
	// 회원가입버튼
	function welcome(){
		
		var f = document.getElementById("f");
			
			if(
				   $("#member_id").val()==""
				|| $('#member_pw').val()==""
				|| $('#member_name').val()==""
				|| $('#gender').val()==""
				|| $('#birthday').val()==""
				|| $('#address').val()==""
				|| $('#email').val()==""
				|| $('#tel').val()==""	
			){
				alert('입력하실 내용이 남아있습니다!!');
				if($("#member_id").val()==null || $("#member_id").val()==""){
					$("#checkMsg").html("아이디를 입력하세요.");
				}
				if($('#member_pw').val()==null || $('#member_pw').val()==""){
					$('#checkPw').html("비밀번호를 입력하세요.");
				}
				if($('#member_pw2').val()==null || $('#member_pw2').val()==""){
					$('#checkPw2').html("비밀번호확인을 입력하세요.");
				}
				if($('#member_name').val()==null || $('#member_name').val()==""){
					$("#checkName").html("이름을 입력하세요.");
				}
				if($('input[name="gender"]:checked').length == 0){
					$('#checkGender').html("성별을 선택하세요.");
				}
				if($('#birthday').val()==null || $('#birthday').val()==""){
					$('#checkBirthday').html('생년월일을 입력하세요.');
				}
				if($('#address').val()==null || $('#address').val()==""){
					$('#checkAddress').html("주소를 입력하세요.");
				}
				if($('#email').val()==null || $('#email').val()==""){
					$('#checkEmail').html("이메일을 입력하세요.");
				}
				if($('#tel').val()==null || $('#tel').val()==""){
					$('#checkTel').html("연락처를 입력하세요.");
				}
				if($('#question').val()==null || $('#question').val()==""){
					$('#checkQuestion').html("질문을 선택하세요.");
				}
				if($('#answer').val()==null || $('#answer').val()==""){
					$('#checkAnswer').html("답변을 입력하세요.");
				}
			}else if($('#member_pw').val() != $('#member_pw2').val()){
				$('#checkPw2').html("비밀번호가 일치하지 않습니다");
			}else if(!isIdCheck){
				alert("아이디가 중복되었는지 체크하세요.");
				
			}else{
				alert("회원가입을 축하");
				f.submit();
			}
		
	}
	function check(){
		isIdCheck = true;
		$.ajax({
			type : 'get',
			url : '/member/check',
			headers : {"Content-Type" : "application/json"},
			data : {"member_id" : $("#member_id").val()},
			dataType : 'json',
			success : function(result){
				if(result == true){
					$("#checkMsg").html("이미 등록된 아이디입니다.");
				}else{
					$("#checkMsg").html("사용하실 수 있는 아이디입니다.");
				}
			}
		});
	}
	
	function email(){
		document.getElementById("email2").value = document.getElementById("emailSelect").value;
	}
	
</script>

<div id="wrap_content" style="min-height:100%; position:relative;">
	<div id="content" style="padding-bottom:50px; width:860px; margin:0 auto; ">
		<form action="/member" id="f" name="f" method="post" class="form-inline">
		<br>
		<h1 align="center" >회원가입</h1>
		<br>
		<br>
			<table class="table">
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" id="member_id" name="member_id" class="form-control" >
						<input type="button" class="btn btn-danger" onclick="check()" value="중복체크" >
					</td>
					<td id="checkMsg"></td>
				</tr>
				<tr>
					<th>비밀번호</th><td><input type="password" id="member_pw" name="member_pw" class="form-control" ></td><td id="checkPw"></td>
				</tr>
				<tr>
					<th>비밀번호확인</th><td><input type="password" id="member_pw2" name="member_pw2" class="form-control" ></td><td id="checkPw2"></td>
				</tr>	
				<tr>
					<th>이름</th><td><input type="text" id="member_name" name="member_name" class="form-control" ></td><td id="checkName"></td>
				</tr>	
				<tr>
					<th>성별</th>
					<td>
					    <label class="radio-inline">
				        	<input type="radio" id="gender" name="gender" value="남성">남성
					    </label>
					    <label class="radio-inline">
					    	<input type="radio" id="gender" name="gender" value="여성">여성
					    </label>
					</td>
					
					<td id="checkGender"></td>
				</tr>	
				<tr>
					<th>생년월일</th><td><input type="text" id="birthday" name="birthday" class="form-control" ></td><td id="checkBirthday"></td>
				</tr>	
				<tr>
					<th >집주소</th><td><input type="text" id="address" name="address" class="form-control" style="width:480px;"></td><td id="checkAddress"></td>
				</tr>	
				<tr>
					<th>이메일</th>
					<td>
						<input class="form-control" id="email1" name="email1" type="text" style="width: 120px; float: left; margin-right:4px;">@
					    <input class="form-control" id="email2" name="email2" type="text" style="width: 120px;">
					    <select class="form-control" id="emailSelect" name="emailSelect" onchange="email()" >
							<option value="">직접입력</option>
							<option value="gmail.com">gmail.com</option>
							<option value="naver.com">naver.com</option>
							<option value="hanmail.net">hanmail.net</option>
							<option value="nate.com">nate.com</option>
							<option value="yahoo.co.kr">yahoo.co.kr</option>
						</select>
					</td>
					<td id="checkEmail">
						
					</td>
				</tr>	
				<tr>
					<th>연락처</th><td><input type="text" id="tel" name="tel" class="form-control" placeholder="'ㅡ' 없이 작성하세요." ></td><td id="checkTel"></td>
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
			<br/>
			<div style="text-align:center;" >
				<input type="button" onclick="welcome()" class="btn btn-danger" value="회원가입">
				<input type="button" onclick="home()" class="btn btn-danger" value="취소">
			</div>
			
		</form>
	</div>	
</div>

<%@include file="./include/footer.jsp"%>