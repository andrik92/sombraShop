<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<br>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<spring:message code="lotCreation.header" var="headerVar" />
<spring:message code="lotImageUplaod.submit" var="submitVar" />
<spring:message code="lotImageUplaod.cancel" var="cancelVar" />

<spring:message code="lotCreation.confirm" var="confirmVar" />
<spring:message code="lotCreation.message" var="messageVar" />

<spring:message code="lotCreation.saveLot" var="saveVar" />

<div class="container">
	<div class="row">
		<h2 class="text-center">${headerVar}</h2>
		<div class="offset4 span4">
			<form:form class="form-horizontal" commandName="lot" method="POST"
				id="lotForm">
				<div class="row text-center">
					<div class="col-md-offset-4 col-md-5">
						<div class="form-group">
							<label class=" control-label"> <spring:message
									code="lotCreation.lotName" /></label>
							<div class="">
								<form:input path="lotName" class="form-control" />
								<form:errors path="lotName" />
							</div>
						</div>
					</div>
				</div>
				
				<div class="row text-center">
					<div class="col-md-offset-4 col-md-5">
						<div class="form-group control-group center">
							<label for="category" class=" control-label l"> <spring:message
									code="lotCreation.category" /></label>
							<div class="">
								<div class="field">
									<form:select path="category" class="form-control">
										<form:option disabled="${hasCategories}" value="null"
											label="--- Category ---" />
										<form:options items="${categories}" itemValue="categoryId"
											itemLabel="categoryName" />
									</form:select>
									<form:errors path="category" />
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row text-center">
					<div class="col-md-offset-4 col-md-5">
						<div class="form-group control-group center">
							<label for="city" class=" control-label l"> <spring:message
									code="lotCreation.city" /></label>
							<div class="">
								<form:label path="city"></form:label>
								<div class="field">
									<form:select path="city" class="form-control">
										<form:option disabled="${hasCities}" value="null"
											label="--- City ---" />
										<form:options items="${cities}" itemValue="cityId"
											itemLabel="cityName" />
									</form:select>
									<form:errors path="city" />
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row text-center">
					<div class="col-md-offset-4 col-md-5">
						<div class="form-group control-group center">
							<label for="category" class=" control-label l"> <spring:message
									code="lotCreation.description" /></label>
							<div class="">
								<div class="field">
									<form:textarea path="description" rows="8" cols="60"
										class="form-control" />
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row text-center">
					<div class="col-md-offset-4 col-md-5">
						<div class="form-group">
							<div class="">
								<input type="submit" value="${saveVar}" class="btn btn-default"
									id="submitForm" />
							</div>
						</div>
					</div>
				</div>

			</form:form>
		</div>
	</div>
</div>