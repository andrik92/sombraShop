
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="reDirection" value="${direction == 'ASC' ? 'DESC' : 'ASC' }" />
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
<div class="container-fluid">
	<br>
	<div class="row">
		<div class="col-md-2">
			<table class="table table-hover table-bordered">
				<thead>
					<tr style="background-color: #3276b1;">
						<th><spring:message code="categorySelect.subcategories" /></th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${language== 'ua'}">
							<c:forEach items="${subCategories}" var="category">
								<tr class="active">
									<td><a
										href="<spring:url value="/selectCategory/${category.categoryId}" />">
											${category.categoryNameUA}</a></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach items="${subCategories}" var="category">
								<tr class="active">
									<td><a
										href="<spring:url value="/selectCategory/${category.categoryId}" />">
											${category.categoryName}</a></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<div class="col-md-10">
			<div class="row-md">
				<!-- <div class="col-md-12"> -->
				<form:form class="navbar-form navbar-left" action="/filter"
					commandName="filterCategory" modelAttribute="filterCategory"
					method="post">
					<div class="col-md-1 col-md-offset-1">
						<form:select class="form-control" path="cityId">
							<option value="0"><spring:message
									code="categorySelect.allCities" /></option>
							<form:options items="${cities}" itemValue="cityId"
								itemLabel="cityName" />
						</form:select>
					</div>
					<div class="col-md-1 col-md-offset-2">
						<h5>
							<span id="ex2SliderValMin"><b>${minPriceSlider}</b></span>$
						</h5>
					</div>
					<form:input type="hidden" path="filterUse" value="1" />
					<form:input type="hidden" path="min" value="${minPrice}" />
					<form:input type="hidden" path="max" value="${maxPrice}" />
					<form:input type="hidden" path="sortBy" value="${sortBy}" />
					<form:input type="hidden" path="direction" value="${direction}" />
					<form:input type="hidden" path="pageSize" value="${pageSize}" />
					<form:input type="hidden" path="categoryId" value="${categoryId}" />
					<div class="col-md-2 col-md-offset-0 disabled">

						<div style="width: 200px">
							<c:choose>
								<c:when
									test="${minPriceSlider == maxPriceSlider && maxPriceSlider==0}">
									<form:input id="ex2" type="text" value=""
										data-slider-min="${minPrice}"
										data-slider-max="${maxPrice == 0 ? 100 : maxPrice}"
										data-slider-step="1" data-slider-value="[${0},${1}]"
										path="mas" />
								</c:when>
								<c:otherwise>
									<form:input id="ex2" type="text" value=""
										data-slider-min="${minPrice}" data-slider-max="${maxPrice}"
										data-slider-step="1"
										data-slider-value="[${minPriceSlider},${maxPriceSlider}]"
										path="mas" />
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="col-md-1 col-md-offset-1">
						<h5>
							<span id="ex2SliderValMax"><b>${maxPriceSlider}</b></span>$
						</h5>
					</div>
					<div class="col-md-1 col-md-offset-1">
						<button id="buttonId" type="submit" class="btn btn-default ">
							<spring:message code="categorySelect.filter" />
						</button>
					</div>
					<div class="col-md-1">
						<a
							href="/selectCategory/${categoryId}/1?filterUse=0&countOnPage=${pageSize}&sortBy=${sortBy}&direction=${direction}"
							type="btn btn-default btn-sm" class="btn btn-primary "><spring:message
								code="categorySelect.reset" /> </a>
					</div>
				</form:form>
			</div>
			<div>
				<table class="table">
					<thead>
						<tr>
							<th><spring:message code="my_lots.picture" /></th>
							<th><a
								href="<spring:url value="/selectCategory/${categoryId}/1?sortBy=lotName&countOnPage=${pageSize}&direction=${reDirection}" />"><spring:message
										code="my_lots.name" /> <c:if
										test="${sortBy == 'lotName' && direction=='ASC'}">&uArr;</c:if>
									<c:if test="${sortBy == 'lotName' && direction=='DESC'}">&dArr;</c:if></a></th>
							<th><a
								href="<spring:url value="/selectCategory/${categoryId}/1?sortBy=city&countOnPage=${pageSize}&direction=${reDirection}" />"><spring:message
										code="categorySelect.city" /> <c:if
										test="${sortBy == 'city' && direction=='ASC'}">&uArr;</c:if> <c:if
										test="${sortBy == 'city' && direction=='DESC'}">&dArr;</c:if></a></th>
							<th><a
								href="<spring:url value="/selectCategory/${categoryId}/1?sortBy=currentPrice&countOnPage=${pageSize}&direction=${reDirection}"/>"><spring:message
										code="my_lots.price" /> <c:if
										test="${sortBy == 'currentPrice' && direction=='ASC'}">&uArr;</c:if>
									<c:if test="${sortBy == 'currentPrice' && direction=='DESC'}">&dArr;</c:if></a></th>
						</tr>
					</thead>

					<tbody>

						<c:forEach items="${lotsList}" var="lot">
							<tr onmouseover="this.style.backgroundColor='#99BDDB';"
								onmouseout="this.style.backgroundColor='#E9E9E9'"
								onclick="window.location.replace('<spring:url value='http://localhost:8080/lots/${lot.lotId}'/>');">

								<td>
									<div class="imageSmall">
										<div class="trickSmall">
											<img src="/showLotImage/${lot.lotId}" class="img">
										</div>
									</div>
								</td>
								<td>${lot.lotName}</td>
								<td>${lot.city.cityName}</td>
								<td>${lot.price}</td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<br>
	<div class="pull-right">

		<form class="pagination pull-left">
			<select class="btn btn-default dropdown-toggle" id="mymenu"
				onchange="window.location=document.forms[2].mymenu.options[document.forms[2].mymenu.selectedIndex].value">
				<option <c:if test="${pageSize == 5}">selected</c:if>
					value="/selectCategory/${categoryId}/1?sortBy=${sortBy}&countOnPage=5&direction=${reDirection}">5</option>
				<option <c:if test="${pageSize == 10}">selected</c:if>
					value="/selectCategory/${categoryId}/1?sortBy=${sortBy}&countOnPage=10&direction=${reDirection}">10</option>
				<option <c:if test="${pageSize == 20}">selected</c:if>
					value="/selectCategory/${categoryId}/1?sortBy=${sortBy}&countOnPage=20&direction=${reDirection}">20</option>
				<option <c:if test="${pageSize == 50}">selected</c:if>
					value="/selectCategory/${categoryId}/1?sortBy=${sortBy}&countOnPage=50&direction=${reDirection}">50</option>
			</select>
		</form>

		<ul class="pagination">
			<c:choose>
				<c:when test="${currentIndex == 1 || currentIndex == 0}">
					<li class="disabled"><a href="#">&lt;&lt;</a></li>
					<li class="disabled"><a href="#">&lt;</a></li>
				</c:when>
				<c:otherwise>
					<li><a
						href="/selectCategory/${categoryId}/1?sortBy=${sortBy}&direction=${direction}&countOnPage=${pageSize}">&lt;&lt;</a></li>
					<li><a
						href="/selectCategory/${categoryId}/${currentIndex - 1}?sortBy=${sortBy}&direction=${direction}&countOnPage=${pageSize}">&lt;</a></li>
				</c:otherwise>
			</c:choose>
			<c:if test="${endIndex !=0}">
				<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
					<c:url var="pageUrl"
						value="/selectCategory/${categoryId}/${i}?sortBy=${sortBy}&direction=${direction}&countOnPage=${pageSize}" />
					<c:choose>
						<c:when test="${i == curentIndex}">
							<li class="active"><a href="${pageUrl}"><c:out
										value="${i}" /></a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
			<c:choose>
				<c:when test="${currentIndex == totalPages || totalPages == 0}">
					<li class="disabled"><a href="#">&gt;</a></li>
					<li class="disabled"><a href="#">&gt;&gt;</a></li>
				</c:when>
				<c:otherwise>
					<li><a
						href="/selectCategory/${categoryId}/${currentIndex + 1}?sortBy=${sortBy}&direction=${direction}&countOnPage=${pageSize}">&gt;</a></li>
					<li><a
						href="/selectCategory/${categoryId}/${totalPages}?sortBy=${sortBy}&direction=${direction}&countOnPage=${pageSize}">&gt;&gt;</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</div>
<script type=text/javascript>
	$("#ex2").slider({});
	$("#ex2").on('slide', function(slideEvt) {
		$("#ex2SliderVal").text(slideEvt.value);
		$("#ex2SliderValMin").text(slideEvt.value[0]);
		$("#ex2SliderValMax").text(slideEvt.value[1]);
	});
</script>