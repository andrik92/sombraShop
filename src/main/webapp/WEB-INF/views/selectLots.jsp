
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="reDirection" value="${direction == 'ASC' ? 'DESC' : 'ASC' }" />

<form:form class="navbar-form" action="/searchLotsFilter"
	commandName="filter" modelAttribute="filter" method="post">
			<div class="container-fluid">
				<br>
				<div class="row">
					<div class="col-md-2">
						<table class="table table-hover table-bordered">
							<thead>
								<tr style="background-color: #3276b1;">
									<th><spring:message code="selectLots.categories" /></th>
									<th><c:if test="${endIndex !=0}">
											<input type="checkbox" id="selectall" value="true" checked />
										</c:if></th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${endIndex !=0}">
									<c:choose>

										<c:when test="${language== 'ua'}">

											<c:forEach var="i" begin="0" end="${categoriesLots.size()-1}">
												<tr class="active">
													<td>${categoriesLots[i].categoryNameUA}</td>
													<td><form:checkbox class="td" path="idCategories"
															value="${categoriesLots[i].categoryId}" checked="checked" /></td>

												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach var="i" begin="0" end="${categoriesLots.size()-1}">
												<tr class="active">
													<td>${categoriesLots[i].categoryName}</td>
													<td><form:checkbox class="td" path="idCategories"
															value="${categoriesLots[i].categoryId}" checked="checked" /></td>
												</tr>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</c:if>
							</tbody>
						</table>
					</div>
					<div class="col-md-10">
						<!-- block for filters -->
						<div class="row-md">
							<div class="col-md-12">
								<form:form class="navbar-form navbar-left"
									action="/searchLotsFilter" commandName="filter"
									modelAttribute="filter" method="post">
									<div class="row-md">
										<form class="navbar-form">
											<div class="col-md-2 col-md-offset-1">
												<form:select class="form-control" path="cityId">
													<option value="0"><spring:message
															code="categorySelect.allCities" /></option>
													<form:options items="${cities}" itemValue="cityId"
														itemLabel="cityName" />
												</form:select>
											</div>
											<div class="col-md-1 col-md-offset-1">
												<c:choose>
													<c:when test="${minPriceSlider==maxPriceSlider}">
														<h5>
															<b>${minPriceSlider}</b>$
														</h5>
													</c:when>
													<c:otherwise>
														<h5>
															<span id="ex2SliderValMin"><b>${minPriceSlider}</b></span>$
														</h5>
													</c:otherwise>
												</c:choose>

											</div>
											<form:input type="hidden" path="filterUse" value="1" />

											<form:input type="hidden" path="min"
												value="${minPriceSlider}" />
											<form:input type="hidden" path="max"
												value="${maxPriceSlider}" />
											<form:input type="hidden" path="sortBy" value="${sortBy}" />
											<form:input type="hidden" path="direction"
												value="${direction}" />
											<form:input type="hidden" path="pageSize" value="${pageSize}" />
											<form:input type="hidden" path="subName" value="${subName}" />
											<form:input type="hidden" path="categoryId"
												value="${categoryId}" />
											<div class="col-md-3 col-md-offset-0 disabled">

												<div style="width: 200px">
													<c:choose>
														<c:when test="${minPriceSlider == maxPriceSlider}">
															<form:input type="hidden" path="mas" />
															<div class="slider slider-horizontal"
																style="width: 155px;">
																<div class="slider-track">
																	<div class="slider-selection"
																		style="left: 0%; width: 100%;"></div>
																</div>
															</div>
														</c:when>
														<c:otherwise>
															<form:input id="ex2" type="text" value=""
																data-slider-min="${minPriceSlider}"
																data-slider-max="${maxPriceSlider}" data-slider-step="1"
																data-slider-value="[${minPriceSlider},${maxPriceSlider}]"
																path="mas" />
														</c:otherwise>
													</c:choose>
												</div>
											</div>
											<div class="col-md-1">

												<c:choose>
													<c:when test="${minPriceSlider==maxPriceSider}">
														<h5>
															<b>${maxPriceSlider}</b>$
														</h5>
													</c:when>
													<c:otherwise>
														<h5>
															<span id="ex2SliderValMax"><b>${maxPriceSlider}</b></span>$
														</h5>
													</c:otherwise>
												</c:choose>

											</div>
											<div class="col-md-1 ">
												<c:if test="${endIndex !=0}">
													<button id="buttonId" type="submit"
														class="btn btn-default ">
														<spring:message code="categorySelect.filter" />
													</button>
												</c:if>
											</div>
											<div class="col-md-2">
												<a
													href="/searchLots/1?filterUse=0&countOnPage=${pageSize}&sortBy=${sortBy}&direction=${direction}"
													type="btn btn-default btn-sm" class="btn btn-primary "><spring:message
														code="categorySelect.reset" /> </a>
											</div>
										</form>
									</div>

								</form:form>
							</div>

						</div>
						<!-- end block of filter -->
						<div>
							<table class="table">
								<thead>
									<tr>
										<th><spring:message code="my_lots.picture" /></th>
										<th><a
											href="<spring:url value="/searchLots/1?sortBy=lotName&countOnPage=${pageSize}&direction=${reDirection}" />"><spring:message
													code="my_lots.name" /> <c:if
													test="${sortBy == 'lotName' && direction=='ASC'}">&uArr;</c:if>
												<c:if test="${sortBy == 'lotName' && direction=='DESC'}">&dArr;</c:if></a></th>
										<th><a
											href="<spring:url value="/searchLots/1?sortBy=city&countOnPage=${pageSize}&direction=${reDirection}" />"><spring:message
													code="categorySelect.city" /> <c:if
													test="${sortBy == 'city' && direction=='ASC'}">&uArr;</c:if>
												<c:if test="${sortBy == 'city' && direction=='DESC'}">&dArr;</c:if></a></th>
										<th><a
											href="<spring:url value="/searchLots/1?sortBy=currentPrice&countOnPage=${pageSize}&direction=${reDirection}"/>"><spring:message
													code="my_lots.price" /> <c:if
													test="${sortBy == 'currentPrice' && direction=='ASC'}">&uArr;</c:if>
												<c:if
													test="${sortBy == 'currentPrice' && direction=='DESC'}">&dArr;</c:if></a></th>
									</tr>
								</thead>

								<tbody>
									<%-- <c:if test="${endIndex !=0}"> --%>
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
									<%-- </c:if> --%>
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
								value="/searchLots/1?sortBy=${sortBy}&countOnPage=5&direction=${reDirection}">5</option>
							<option <c:if test="${pageSize == 10}">selected</c:if>
								value="/searchLots/1?sortBy=${sortBy}&countOnPage=10&direction=${reDirection}">10</option>
							<option <c:if test="${pageSize == 20}">selected</c:if>
								value="/searchLots/1?sortBy=${sortBy}&countOnPage=20&direction=${reDirection}">20</option>
							<option <c:if test="${pageSize == 50}">selected</c:if>
								value="/searchLots/1?sortBy=${sortBy}&countOnPage=50&direction=${reDirection}">50</option>
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
									href="/searchLots/1?sortBy=${sortBy}&direction=${direction}&countOnPage=${pageSize}">&lt;&lt;</a></li>
								<li><a
									href="/searchLots/${currentIndex - 1}?sortBy=${sortBy}&direction=${direction}&countOnPage=${pageSize}">&lt;</a></li>
							</c:otherwise>
						</c:choose>
						<c:if test="${endIndex !=0}">
							<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
								<c:url var="pageUrl"
									value="/searchLots/${i}?sortBy=${sortBy}&direction=${direction}&countOnPage=${pageSize}" />
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
									href="/searchLots/${currentIndex + 1}?sortBy=${sortBy}&direction=${direction}&countOnPage=${pageSize}">&gt;</a></li>
								<li><a
									href="/searchLots/${totalPages}?sortBy=${sortBy}&direction=${direction}&countOnPage=${pageSize}">&gt;&gt;</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
</form:form>
<script type=text/javascript>
	$("#ex2").slider({});
	$("#ex2").on('slide', function(slideEvt) {
		$("#ex2SliderVal").text(slideEvt.value);
		$("#ex2SliderValMin").text(slideEvt.value[0]);
		$("#ex2SliderValMax").text(slideEvt.value[1]);
	});
</script>

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
<script type="text/javascript">
	$('document').ready(function() {

		$("#selectall").click(function() {
			if ($("#selectall").is(':checked'))
				$("#buttonId").show().prop('disabled', false); // checked
			else
				$("#buttonId").show().prop('disabled', true); // unchecked

			$('.td').attr('checked', this.checked);

		});

		$(".td").click(function() {

			if ($(".td").length == $(".td:checked").length) {
				$("#selectall").attr("checked", "checked");
			} else {
				$("#selectall").removeAttr("checked");
			}

			if ($(".td").is(':checked'))
				$("#buttonId").show().prop('disabled', false); // checked
			else
				$("#buttonId").show().prop('disabled', true); // unchecked
		});
	});
</script>

