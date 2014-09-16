<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="stylesheet" href="/resources/css/tinycarousel.css" />

<!-- block for select category -->
<div class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="row">
			<div class="col-md-11">
				<div class="navbar-collapse collapse">
					<c:forEach var="i" begin="0" end="9">
						<c:if test="${allParents[i] !=null}">
							<ul class="nav navbar-nav">
								<c:choose>
									<c:when test="${language== 'ua'}">
										<li class="dropdown"><a
											href='<spring:url value="/selectCategory/${allParents[i].categoryId}.html"/>'
											class="dropdown-toggle" data-hover="dropdown">${allParents[i].categoryName ==null? allParents[i].categoryName: allParents[i].categoryNameUA}</a>
											<ul class="dropdown-menu">
												<c:forEach items="${subCategories}" var="sub">
													<c:if
														test="${sub.parent.categoryId == allParents[i].categoryId}">
														<li><a
															href='<spring:url value="/selectCategory/${sub.categoryId}.html"/>'>${sub.categoryNameUA == null?sub.categoryName:sub.categoryNameUA}</a></li>
													</c:if>
												</c:forEach>
											</ul></li>
									</c:when>
									<c:otherwise>
										<li class="dropdown"><a
											href='<spring:url value="/selectCategory/${allParents[i].categoryId}.html"/>'
											class="dropdown-toggle" data-hover="dropdown">${allParents[i].categoryName }</a>

											<ul class="dropdown-menu">
												<c:forEach items="${subCategories}" var="sub">
													<c:if
														test="${sub.parent.categoryId == allParents[i].categoryId}">
														<li><a
															href='<spring:url value="/selectCategory/${sub.categoryId}.html"/>'>${sub.categoryName}</a></li>
													</c:if>
												</c:forEach>
											</ul></li>
									</c:otherwise>
								</c:choose>
							</ul>
						</c:if>
					</c:forEach>
				</div>
			</div>
			<div class="col-md-1">
				<ul class="nav navbar-nav ">
					<li class="dropdown"><h4>
							<a href='<spring:url value="/selectCategory.html"/>'><spring:message
									code="header.all" /></a>
						</h4></li>
				</ul>
			</div>
		</div>
	</div>
</div>

<!-- lots block -->
<div
	style="font-family: Comic Sans MS; font-weight: bold; font-size: 24px">
	<img src=/resources/images/deal.png alt="Hot deals" class="img-rounded"
		style="margin: 11px">
	<spring:message code="main.deals" />
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 main-block">
		<div class="row">
			<c:forEach items="${lots}" var="lot">
				<div class="col-md-4 col-xs-12 col-sm-12 col-lg-4"
					style="heigth: 310px; width: 300px; margin: 10px; border: 1.7px solid white; border-radius: 25px; background-color: white">
					<div class="image " style="margin-top: 15px;">
						<div class="trick"></div>
						<a href='<spring:url value="/lots/${lot.lotId}.html"/>'> <img
							src="/showLotImage/${lot.lotId}" class="img">
						</a>
					</div>
					<br> ${lot.lotName} <br> Current price: ₴ ${lot.price}
				</div>
			</c:forEach>
		</div>
	</div>
</div>