<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="с"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<br>
<div class="container">
	<br>
	<div class="row">
		<c:url var="firstUrl"
			value="/cart/1?sorterItem=${sorterItem}&countItems=${countItems}&direction=${reDirection}" />
		<c:url var="lastUrl"
			value="/cart/${content.totalPages}?sorterItem=${sorterItem}&countItems=${countItems}&direction=${reDirection}" />
		<c:url var="prevUrl"
			value="/cart/${currentIndex - 1}?sorterItem=${sorterItem}&countItems=${countItems}&direction=${reDirection}" />
		<c:url var="nextUrl"
			value="/cart/${currentIndex + 1}?sorterItem=${sorterItem}&countItems=${countItems}&direction=${reDirection}" />
		<div class="col-md-12">
			<table class="table">
				<thead>
					<tr>
						<th><a
							href="<spring:url value="/cart/1?sorterItem=lotId&countItems=${countItems}&direction=${direction}" />">№
								<c:if test="${sorterItem == 'lotId' && direction=='ASC'}">&uArr;</c:if>
								<c:if test="${sorterItem == 'lotId' && direction=='DESC'}">&dArr;</c:if>
						</a></th>
						<th><spring:message code="cart.picture" /></th>
						<th><a
							href="<spring:url value="/cart/1?sorterItem=lotName&countItems=${countItems}&direction=${direction}" />"><spring:message
									code="cart.name" /> <c:if
									test="${sorterItem == 'lotName' && direction=='ASC'}">&uArr;</c:if>
								<c:if test="${sorterItem == 'lotName' && direction=='DESC'}">&dArr;</c:if></a></th>
						<th><a
							href="<spring:url value="/cart/1?sorterItem=currentPrice&countItems=${countItems}&direction=${direction}"/>"><spring:message
									code="cart.price" /> <c:if
									test="${sorterItem == 'currentPrice' && direction=='ASC'}">&uArr;</c:if>
								<c:if
									test="${sorterItem == 'currentPrice' && direction=='DESC'}">&dArr;</c:if></a></th>
					</tr>
				</thead>
				<tbody>
					<с:forEach items="${content.content}" var="lot">
						<tr onmouseover="this.style.backgroundColor='#99BDDB';"
							onmouseout="this.style.backgroundColor='#E9E9E9'"
							onclick="window.location.replace('<spring:url value='http://localhost:8080/lots/${lot.lotId}'/>');">
							<td><br> <br>&#10022;</td>
							<td>
								<div class="imageSmall">
									<div class="trickSmall"></div>
									<label>jasfa</label> <img src="/showLotImage/${lot.lotId}"
										class="img">
								</div>
							</td>
							<td><h5>
									<br> <br>${lot.lotName}</h5></td>
							<td><h5>
									<br> <br>${lot.price}</h5></td>
							<td onmouseover="this.style.backgroundColor='#E9E9E9';"><a
								class="text-center"
								href="<spring:url value="/deal?lotId=${lot.lotId}"/>">
									<h3>
										<span class="glyphicon glyphicon-ok"></span>
									</h3>
							</a></td>
							<td onmouseover="this.style.backgroundColor='#E9E9E9';"><a
								class="text-center"
								href="<spring:url value="/cart?lotId=${lot.lotId}"/>">
									<h3>
										<span class="glyphicon glyphicon-remove"></span>
									</h3>
							</a></td>
						</tr>
					</с:forEach>
				</tbody>
			</table>
		</div>
		<div>
			<ul class="pagination pull-right">
				<c:choose>
					<c:when test="${currentIndex == 1}">
						<li class="disabled"><a href="#">&lt;&lt;</a></li>
						<li class="disabled"><a href="#">&lt;</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${firstUrl}">&lt;&lt;</a></li>
						<li><a href="${prevUrl}">&lt;</a></li>
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
					<c:url var="pageUrl"
						value="/cart/${i}?sorterItem=${sorterItem}&countItems=${countItems}&direction=${reDirection}" />

					<c:choose>
						<c:when test="${i == currentIndex}">
							<li class="active"><a href="${pageUrl}"><c:out
										value="${i}" /></a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${currentIndex == content.totalPages}">
						<li class="disabled"><a href="#">&gt;</a></li>
						<li class="disabled"><a href="#">&gt;&gt;</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${nextUrl}">&gt;</a></li>
						<li><a href="${lastUrl}">&gt;&gt;</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
			<form class="pagination pull-right" id="myform">
				<select class="btn btn-default dropdown-toggle" id="mymenu"
					onchange="window.location=document.forms[1].mymenu.options[document.forms[1].mymenu.selectedIndex].value">
					<option <c:if test="${countItems == 5}">selected</c:if>
						value="/cart/1?sorterItem=${sorterItem}&countItems=5&direction=${reDirection}">5</option>
					<option <c:if test="${countItems == 10}">selected</c:if>
						value="/cart/1?sorterItem=${sorterItem}&countItems=10&direction=${reDirection}">10</option>
					<option <c:if test="${countItems == 20}">selected</c:if>
						value="/cart/1?sorterItem=${sorterItem}&countItems=20&direction=${reDirection}">20</option>
					<option <c:if test="${countItems == 50}">selected</c:if>
						value="/cart/1?sorterItem=${sorterItem}&countItems=50&direction=${reDirection}">50</option>
				</select>
			</form>
		</div>
	</div>
</div>