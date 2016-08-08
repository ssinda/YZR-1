<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header.jsp"%>
<%@include file="./sidebar.jsp"%>
<div class="content" style="margin-left: 120px; width: 890px;">
	<h2>공지/뉴스</h2><br>
	<table class="table">
		<thead>
			<tr style="background-color: red; color: white;">
				<th>[${ vo.notice_category }] ${ vo.notice_title }</th>
				<th style="width: 80px; text-align: center;">등록일</th>
				<th style="width: 120px; text-align: center;">${ vo.notice_date }</th>
				<th style="width: 80px; text-align: center;">조회수</th>
				<th style="width: 60px; text-align: right;">${ vo.notice_view_cnt }</th>
			</tr>
		</thead>
		<tbody>
			<tr style="background-color: ivory;">
				<td colspan="5" style="padding: 30px; padding-top: 50px; padding-bottom: 50px;">
					<c:if test="${ vo.notice_image != null }">
						<div><img src="/resources/images/notice/${ vo.notice_image }" style="width: 830px;"></div><br>
					</c:if>
					<div>${ vo.notice_content }</div>
				</td>
			</tr>
		</tbody>
	</table>
	<hr>
	<a href="/support/notice"><input type="button" class="btn btn-danger" value="목록으로" style="margin-left: 90%;"></a>
</div>
<%@include file="../include/footer.jsp"%>