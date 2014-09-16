<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="с"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link href="/resources/css/admpagecss.css" rel="stylesheet"
	type="text/css" />
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src="/resources/js/jquery.tablesorter.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<с:url var="firstUrl" value="/admEditUsers/1" />
<с:url var="lastUrl" value="/admEditUsers/${sellingLots.totalPages}" />
<с:url var="prevUrl" value="/admEditUsers/${currentIndex - 1}" />
<с:url var="nextUrl" value="/admEditUsers/${currentIndex + 1}" />
<с:set var="page" value="allSellingLots" />

<br>

<div id="dialog" title="<spring:message code="adminmenu.editUsers" />"
	style="display: none">
	<form:form action="editUserDeal" commandName="user">

		<br>
		<h5 style="margin-left: 10px;">
			<spring:message code="adminmenu.email" />
			:<input type='text' name=email value="${login.getEmail()}" />
		</h5>
		<h5 style="margin-left: 10px;">
			<spring:message code="adminmenu.login" />
			:<input type='text' name=login class='login'
				value="${login.getLogin()}" />
		</h5>
		<h5 style="margin-left: 10px;">
			<spring:message code="adminmenu.phonenumber" />
			:<input type='text' name=phoneNumber class='phoneNumber'
				value="${login.getPhoneNumber()}" />
		</h5>
		<h5 style="margin-left: 10px;">
			<spring:message code="adminmenu.firstname" />
			:<input type='text' name=firstName class='firstName'
				value="${login.getFirstName()}" />
		</h5>
		<h5 style="margin-left: 10px;">
			<spring:message code="adminmenu.lastname" />
			:<input type='text' name=lastName class='lastName'
				value="${login.getLastName()}" />
		</h5>

		<input type="hidden" name=userId value="${login.getUserId()}">
		<div class="form-group" align="center">
			<input type="submit"
				value=" <spring:message code="adminmenu.save" /> "
				class="btn btn-default"> <a href="/admEditUsers/1"><input
				type="button" value=" <spring:message code="adminmenu.cancel" /> "
				class="btn btn-default"></a>
		</div>
	</form:form>
</div>


<div class="breadcrumb">
	<div class="container">
		<div>
			<ul class="nav nav-pills ">
				<li class="active"><a href="/admEditUsers/1"><spring:message
							code="adminmenu.editUsers" /></a></li>
				<li><a href="/admEditCategory"><spring:message
							code="adminmenu.editCategories" /></a></li>
				<li><a href="/admEditCities"><spring:message
							code="adminmenu.city" /></a></li>
				<li><a href="/createLot"><spring:message
							code="adminmenu.addLot" /></a></li>

				<li>
					<div style="margin-left: 392px;" class="nav navbar-nav">
						<form:form class="navbar-form navbar-left" action="/searchUsers"
							commandName="user" method="post">
							<div class="row">
								<!-- column for search panel -->
								<div class="col-md-4  col-md-offset-2">
									<form:input type="text" style="width: 144px;"
										class="form-control"
										placeholder="${language=='ua'?'Пошук':'Search'}"
										path="firstName" />
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<form:select path="userId" class="form-control">
											<option value="1">by first name</option>
											<option value="2">by last name</option>
											<option value="3">by Login</option>
										</form:select>
									</div>
								</div>
								<div class="col-md-1" style="margin-left: 24px;">
									<button type="submit" class="btn btn-default">
										<spring:message code="header.go" />
									</button>
								</div>
							</div>
						</form:form>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<br>
	<div class="container">
		<div class="row">
			<table id="myTable" class="tablesorter table">
				<thead>
					<tr>
						<th><spring:message code="adminmenu.email" /></th>
						<th><spring:message code="adminmenu.login" /></th>
						<th><spring:message code="adminmenu.phonenumber" /></th>
						<th><spring:message code="adminmenu.firstname" /></th>
						<th><spring:message code="adminmenu.lastname" /></th>
						<th><spring:message code="adminmenu.status" /></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<с:forEach items="${users}" var="p">
						<tr>
							<td>${p.email}</td>
							<td>${p.login}</td>
							<td>${p.phoneNumber}</td>
							<td>${p.firstName}</td>
							<td>${p.lastName}</td>
							<td align="center"><c:choose>
									<c:when test="${p.enabled == false}">
										<c:set var="deactivateBtnClass" value="hide" />
										<c:set var="activateBtnClass" value="" />
									</c:when>
									<c:otherwise>
										<c:set var="deactivateBtnClass" value="" />
										<c:set var="activateBtnClass" value="hide" />
									</c:otherwise>
								</c:choose> <spring:url value="/admDeactivate/${p.userId}"
									var="deactivateHref" /> <spring:url
									value="/admActivate/${p.userId}" var="activateHref" /> <a
								href="${deactivateHref}"
								class=" watchButton ${deactivateBtnClass}"> <span
									class="glyphicon glyphicon-ok"></span></a> <a
								href="${activateHref}" class=" watchButton ${activateBtnClass}">
									<span class="glyphicon glyphicon-remove"></span>
							</a></td>
							<td><a
								href="${pageContext.request.contextPath}/edit/${p.login}.html"><button
										class="btn btn-default">
										<spring:message code="adminmenu.edit" />
									</button></a></td>
						</tr>
						<div id="dialog"
							title="<spring:message code="adminmenu.editUsers" />"
							style="display: none">
							<form:form action="edituserdial" commandName="user">
								<br>
								<h5 style="margin-left: 10px;">
									<spring:message code="adminmenu.email" />
									:<input type='text' name=email value="${login.getEmail()}" />
								</h5>
								<h5 style="margin-left: 10px;">
									<spring:message code="adminmenu.login" />
									:<input type='text' name=login class='login'
										value="${login.getLogin()}" />
								</h5>
								<h5 style="margin-left: 10px;">
									<spring:message code="adminmenu.phonenumber" />
									:<input type='text' name=phoneNumber class='phoneNumber'
										value="${login.getPhoneNumber()}" />
								</h5>
								<h5 style="margin-left: 10px;">
									<spring:message code="adminmenu.firstname" />
									:<input type='text' name=firstName class='firstName'
										value="${login.getFirstName()}" />
								</h5>
								<h5 style="margin-left: 10px;">
									<spring:message code="adminmenu.lastname" />
									:<input type='text' name=lastName class='lastName'
										value="${login.getLastName()}" />
								</h5>
								<input type="hidden" name=userId value="${login.getUserId()}">
								<div class="form-group" align="center">
									<input type="submit"
										value=" <spring:message code="adminmenu.save" /> "
										class="btn btn-default"> <a href="/admEditUsers/1"><input
										type="button"
										value=" <spring:message code="adminmenu.cancel" /> "
										class="btn btn-default"></a>
								</div>
							</form:form>
						</div>
					</с:forEach>
				</tbody>
			</table>
			<с:choose>
				<c:when test="${paging == true}">
				</c:when>
				<c:otherwise>
					<div class="pull-right">
						<ul class="pagination">
							<с:choose>
								<с:when test="${currentIndex == 1}">
									<li class="disabled"><a href="#">&lt;&lt;</a></li>
									<li class="disabled"><a href="#">&lt;</a></li>
								</с:when>
								<с:otherwise>
									<li><a href="${firstUrl}">&lt;&lt;</a></li>
									<li><a href="${prevUrl}">&lt;</a></li>
								</с:otherwise>
							</с:choose>
							<с:forEach var="i" begin="${beginIndex}" end="${endIndex}">
								<с:url var="pageUrl" value="/admEditUsers/${i}" />
								<с:choose>
									<с:when test="${i == curentIndex}">
										<li class="active"><a href="${pageUrl}"><с:out
													value="${i}" /></a></li>
									</с:when>
									<с:otherwise>
										<li><a href="${pageUrl}"><с:out value="${i}" /></a></li>
									</с:otherwise>
								</с:choose>
							</с:forEach>
							<с:choose>
								<с:when test="${currentIndex == pageusers.totalPages}">
									<li class="hide"><a href="#">&gt;</a></li>
									<li class="hide"><a href="#">&gt;&gt;</a></li>
								</с:when>
								<с:otherwise>
									<li><a href="${nextUrl}">&gt;</a></li>
									<li><a href="${lastUrl}">&gt;&gt;</a></li>
								</с:otherwise>
							</с:choose>
						</ul>
					</div>
				</c:otherwise>
			</с:choose>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$("#myTable").tablesorter({
			sortList : [ [ 0, 0 ], [ 1, 0 ] ]
		});
	});

	window.onload = function() {
		var hash = (window.location.hash);
		if (hash == '#5') {

			$("#dialog").dialog({
				dialogClass : 'no-close',
				modal : true,
				width : '21%'
			});
		}
		;
	};

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