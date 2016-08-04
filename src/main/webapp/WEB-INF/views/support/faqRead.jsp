<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header.jsp"%>
<%@include file="./sidebar.jsp"%>
<div class="content" style="margin-left: 250px;">
	<div>
		<label style="font-size: 15pt;">자주찾는 질문</label><br>
		회원님들께서 가장 자주하시는 질문을 모았습니다.<br>
		궁금하신 내용에 대해 검색해보세요.<br><br><br>
	</div>
	<table class="table">
		<thead>
			<tr style="background-color: red; color: white;">
				<th>[${ vo.category }] ${ vo.title }</th>
				<th style="width: 8%; text-align: center;">등록일</th>
				<th style="width: 11%; text-align: center;">${ vo.reg_date }</th>
				<th style="width: 8%; text-align: center;">조회수</th>
				<th style="width: 6%; text-align: right;">${ vo.view_cnt }</th>
			</tr>
		</thead>
		<tbody>
			<tr style="background-color: ivory;">
				<td colspan="5" style="padding: 30px; padding-top: 50px; padding-bottom: 50px;">
					${ vo.content }
				</td>
			</tr>
		</tbody>
	</table>
	<hr>
	<input type="button" class="btn btn-danger" value="목록으로" style="margin-left: 90%;" onclick="history.back();">
</div>
<%@include file="../include/footer.jsp"%>