<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="с"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<с:set var="current"
	value="${requestScope['javax.servlet.forward.request_uri']}" />
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<br>

<div class="breadcrumb">
	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div>
				<ul class="nav nav-pills ">
					<li><a href="/admEditUsers/1"><spring:message
								code="adminmenu.editUsers" /></a></li>
					<li><a href="/admEditCategory"><spring:message
								code="adminmenu.editCategories" /></a></li>
					<li class="active"><a href="/admEditCities"><spring:message
								code="adminmenu.city" /></a></li>
					<li><a href="/createLot"><spring:message
								code="adminmenu.addLot" /></a></li>
				</ul>
			</div>
		</div>
		<br>
		<form:form class="form-inline" action="saveCity" commandName="city">
			<div class="form-group">
				<label>Add new city:</label>
				<form:input path="cityName" class="form-control"
					placeholder="cityName" />
			</div>
			<div class="form-group">
				<input type="submit" value="Add" class="btn btn-default">
			</div>
		</form:form>
		<br>
		<table class="table" style="width: 250px;">
			<thead>
				<tr>
					<th>City Name</th>
					<th>Active Status</th>
				</tr>
			</thead>
			<tbody>
				<с:forEach items="${cities}" var="city">
					<tr>
						<td>${city.cityName}</td>
						<td align="center"><c:choose>
								<c:when test="${city.enabledCity == false}">
									<c:set var="addBtnClass" value="hide" />
									<c:set var="deleteBtnClass" value="" />
								</c:when>
								<c:otherwise>
									<c:set var="addBtnClass" value="" />
									<c:set var="deleteBtnClass" value="hide" />
								</c:otherwise>
							</c:choose> <spring:url value="/deactivate/${city.cityId}" var="addHref" />
							<spring:url value="/activate/${city.cityId}" var="deleteHref" />
							<a href="${addHref}" class="watchButton ${addBtnClass}"> <span
								class="glyphicon glyphicon-ok"></span></a> <a href="${deleteHref}"
							class="watchButton ${deleteBtnClass}"> <span
								class="glyphicon glyphicon-remove"></span></a></td>
					</tr>
				</с:forEach>
		</table>
	</div>
</div>

<script>
	$(document).ready(function() {
		$(".watchButton").click(function(event) {
			event.preventDefault();
			var btn = $(this);
			var url = $(this).attr("href");
			$.get(url, function(response) {
				btn.parent().find(".watchButton").toggleClass("hide");
			});
		});
	});
</script>