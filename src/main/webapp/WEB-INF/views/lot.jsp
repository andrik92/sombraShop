<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<script src="/resources/js/lotHandler.js"></script>
<script type="text/javascript" src="/resources/js/imageHandlerScript.js"></script>
<link href="/resources/css/slides.css" rel="stylesheet" type="text/css" />
<!-- Spring:message -->
<spring:message code="lot.statusAvailable" var="msgstatusAvailable" />
<spring:message code="lot.seller" var="msgseller" />
<spring:message code="lot.condition" var="msgcondition" />
<spring:message code="lot.location" var="msglocation" />
<spring:message code="lot.price" var="msgprice" />
<spring:message code="lot.description" var="msgdescription" />
<spring:message code="lot.addToCart" var="msgaddToCart" />
<spring:message code="lot.deleteFromCart" var="msgdeleteFromCart" />

<!-- Categories path -->
<ol class="breadcrumb">
	<li class="active"><a
		href='<spring:url value="/selectCategory.html"/>'><spring:message
				code="header.allCategories" /></a></li>
	<c:choose>
		<c:when test="${language== 'ua'}">

			<c:forEach items="${treeOfCategory}" var="category">
				<li><a
					href='<spring:url value="/selectCategory/${category.categoryId}"/>'>${category.categoryNameUA}</a></li>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:forEach items="${treeOfCategory}" var="category">
				<li><a
					href='<spring:url value="/selectCategory/${category.categoryId}"/>'>${category.categoryName}</a></li>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</ol>

<!-- Modal -->
<div class="modal fade modal-fullscreen force-fullscreen"
	id="pictureModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-header"></div>
	<div class="modal-content">
		<div class="modal-body">
			<section class="block">
				<div id="myCarousel" class="carousel slide">
					<div id="thisCarousel" class="carousel-inner"></div>
					<a class="carousel-control left" href="#myCarousel"
						data-slide="prev">‹</a> <a class="carousel-control right"
						href="#myCarousel" data-slide="next">›</a>
				</div>
			</section>
			<div style="position: absolute; top: 4%; right: 4%;">
				<button class="close" data-dismiss="modal"
					data-target="#pictureModal">X</button>
			</div>
		</div>
	</div>
</div>
<!-- /.modal -->

<div class="row">
	<!-- Lot Images -->
	<div class="col-md-4"
		style="width: 320px; border: 1px; margin-left: 12px">
		<div class="row">
			<!--  -->
			<div id="outerPoint" class=" thumbnail col-md-10"
				style="width: 300px; border: 1px">
				<div style="position: absolute; bottom: 45%; left: 0%;">
					<span id="previous" name="previous"
						class="glyphicon glyphicon-chevron-left resizeG"></span>
				</div>
				<div style="position: absolute; bottom: 45%; right: 0%;">
					<span id="next" name="next"
						class="glyphicon glyphicon-chevron-right resizeG"></span>
				</div>
				<div id="iddd" class="imageLarge ">
					<div class="trick"></div>

					<img id="imageOne" name="imageOne" alt="" data-toggle="modal"
						data-target="#pictureModal" class="Centered">
				</div>
				<!--width: 296px; padding-left  -->
				<div id="outerVisbility"
					style="height: 100px; position: absolute; bottom: 0%; overflow: hidden;">
					<div
						style="width: 289px; height: 100px; position: relative; bottom: 0%; left: 289px; z-index: +2;">
					</div>
					<div
						style="width: 289px; height: 100px; position: absolute; bottom: 0%; left: -289px; z-index: +2;"></div>
					<div id="movedContainer"
						style="width: 289px; height: 100px; position: absolute; top: 0%; z-index: 0; left: 0px">
						<div id="mainMove" class="row"
							style="width: 1000000px; height: 50px;">
							<!-- <div id ="prevDiv" class=""> -->
						</div>
					</div>
				</div>
			</div>
		</div>
		<c:set var="status" scope="page" value="${lot.lotStatus}"></c:set>

	</div>

	<!-- 	Lot info -->
	<div class="col-md-5" style="border-left: 1px solid silver">
		<c:set var="status" scope="page" value="${lot.lotStatus}"></c:set>
		<c:if test="${status == 'AVAILABLE'}">
			<div class="bg-success" style="font-size: x-large;">${lot.lotName}
				<span class="label label-success pull-right" style="margin-top: 5px">${msgstatusAvailable}</span>
			</div>
		</c:if>

		<a href="#" id="userHref"><span class="glyphicon glyphicon-user"></span>${msgseller}
			${lot.seller.login}</a>
		<table class="table">
			<tr>
				<td width="500">${msgcondition}</td>
				<td style="font-style: italic;">new</td>
			</tr>
			<tr>
				<td width=>${msglocation}</td>
				<td style="font-style: italic;">${lot.city.cityName}</td>
			<tr>
				<td>${msgcurrentPrice}</td>
				<td style="font-style: italic;">${lot.price}</td>
		</table>

		<c:set var="isOwner"
			value="${lot.seller.login == userInSession.login}" />

		<c:choose>
			<c:when test="${isOwner == false}">
				<input type="hidden" name="lotId" value="${lot.lotId}">
			</c:when>

		</c:choose>

		<security:authorize
			access="!hasRole('ROLE_ADMIN') && isAuthenticated()">

			<div class="col-sm-5 col-md-offset-3">

				<c:choose>
					<c:when test="${isInWatchlist == true}">
						<c:set var="addBtnClass" value="hide" />
						<c:set var="deleteBtnClass" value="" />
					</c:when>
					<c:otherwise>
						<c:set var="addBtnClass" value="" />
						<c:set var="deleteBtnClass" value="hide" />
					</c:otherwise>
				</c:choose>

				<c:if test="${lot.seller.login != userInSession.login}">
					<spring:url value="/lots/${lotId}/addToCart" var="addHref" />
					<spring:url value="/lots/${lotId}/deleteFromCart" var="deleteHref" />
					<spring:message code="lot.adding" var="adding" />
					<spring:message code="lot.deleting" var="deleting" />
					<a href="${addHref}" type="btn btn-default btn-sm"
						data-loading-text="${adding}"
						class="btn btn-primary watchButton ${addBtnClass}">
						${msgaddToCart} </a>
					<a href="${deleteHref}" type="btn btn-default btn-sm"
						data-loading-text="${deleting}"
						class="btn btn-primary watchButton ${deleteBtnClass}">
						${msgdeleteFromCart} </a>
				</c:if>
			</div>
		</security:authorize>
	</div>
</div>
<br>

<!-- Description block -->
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">${msgdescription}</h3>
	</div>
	<div class="panel-body">${lot.description}</div>
</div>