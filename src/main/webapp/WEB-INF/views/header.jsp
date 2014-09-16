<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="language"
	value="${not empty param.language ? param.language : language}"
	scope="session" />

<c:set var="userId" value="${user.userId}" />

<div class="navbar navbar-default">
	<div class="container">
		<div class="row">
			<div class="navbar-header col-md-3">
				<a href="<spring:url value="/" />"> <img
					src=/resources/images/logo.png alt="Logo" class="img-rounded">
				</a>
			</div>

			<div class="nav navbar-nav col-md-4">
				<form:form class="navbar-form navbar-left" action="/searchLots"
					commandName="filter" modelAttribute="filter" method="post">
					<div class="row">
						<form:input type="hidden" path="filterUse" value="0" />
						<div class="col-md-5  col-md-offset-0">
							<form:input type="text" class="form-control"
								placeholder="${language=='ua'?'Пошук':'Search'}" path="subName" />
						</div>

						<div class="col-md-4 col-md-offset-1">
							<!-- column for select category -->
							<div class="form-group">
								<form:select path="categoryId" class="form-control">
									<option class="bg-danger" value="0"><spring:message
											code="header.allCategories" /></option>
									<c:choose>
										<c:when test="${language== 'ua'}">
											<form:options items="${allParents}" itemValue="categoryId"
												itemLabel="categoryNameUA" />
										</c:when>
										<c:otherwise>
											<form:options items="${allParents}" itemValue="categoryId"
												itemLabel="categoryName" />
										</c:otherwise>
									</c:choose>
								</form:select>
							</div>
						</div>

						<div class="col-md-1 col-md-offset-1">
							<button type="submit" class="btn btn-default">
								<spring:message code="header.go" />
							</button>
						</div>
					</div>
				</form:form>
			</div>
			<div class="nav navbar-nav navbar-right col-md-5">
				<ul class="nav navbar-nav navbar-right">

					<security:authorize access="isAuthenticated() && !hasRole('ROLE_ADMIN')">
						<li><a href="<spring:url value="/editProfile" />"><spring:message
									code="header.editProfile" /></a></li>
					</security:authorize>
					
					<security:authorize access="! isAuthenticated()">
						<li><a href="<spring:url value="/login" />"><spring:message
									code="header.login" /></a></li>
					</security:authorize>
					
					<security:authorize access="isAuthenticated()">
						<li><a href="<spring:url value="/logout" />"><spring:message
									code="header.logout" /></a></li>
					</security:authorize>
					
					<security:authorize access="! isAuthenticated()">
						<li><a href="<spring:url value="/register" />"><spring:message
									code="header.signup" /></a></li>
					</security:authorize>
					
					<security:authorize access="isAuthenticated() && !hasRole('ROLE_ADMIN')">
						<li><a href="<spring:url value="/cart/1" />"><spring:message
									code="header.myBbay" /></a></li>
					</security:authorize>

					<security:authorize access="hasRole('ROLE_ADMIN')">
						<li><a href="<spring:url value="/admEditUsers/1" />">Admin
								profile</a></li>
					</security:authorize>
					
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" data-hover="dropdown"> <spring:message
								code="header.language" /> <b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a href="?language=en">english</a></li>
							<li><a href="?language=ua">українcька</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>
</div>
