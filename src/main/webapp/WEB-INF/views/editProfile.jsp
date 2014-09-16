<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../layouts/taglib.jsp"%>

<div class="row">
	<div class="row text-center">
		<div class="col-md-offset-4 col-md-5">
			<h1>
				<spring:message code="editProfile.title" />
			</h1>
		</div>
	</div>
	<div>
		<form:form commandName="user"
			cssClass="form-horizontal editProfileForm">
			<c:if test="${param.success eq true }">
				<div class="row text-center">
					<div class="col-md-offset-4 col-md-5">
						<div class="alert alert-success">
							<spring:message code="editProfile.successEdit" />
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${param.success eq false }">
				<div class="row text-center">
					<div class="col-md-offset-4 col-md-5">
						<div class="alert alert-danger">
							<spring:message code="editProfile.error" />
						</div>
					</div>
				</div>
			</c:if>
			<div class="form-group">
				<label for="firstName" class="col-sm-4 control-label"><spring:message
						code="editProfile.firstName" /></label>
				<div class="col-sm-5">
					<form:input path="firstName" cssClass="form-control"></form:input>

				</div>
			</div>

			<div class="form-group">
				<label for="lastName" class="col-sm-4 control-label"><spring:message
						code="editProfile.lastName" /></label>
				<div class="col-sm-5">
					<form:input path="lastName" cssClass="form-control"></form:input>
				</div>
			</div>

			<div class="form-group">
				<label for="email" class="col-sm-4 control-label"><spring:message
						code="editProfile.email" /></label>
				<div class="col-sm-5">
					<form:input path="email" cssClass="form-control"/>
					<form:errors path="email"/>

				</div>
			</div>
			<div class="form-group">
				<label for="phoneNumber" class="col-sm-4 control-label"><spring:message
						code="editProfile.phoneNumber" /></label>
				<div class="col-sm-5">
					<form:input path="phoneNumber" cssClass="form-control"/>
					<form:errors path="phoneNumber" />
				</div>
			</div>

			<div class="form-group modal-footer">
				<div class="col-md-9">
					<input type="submit"
						value="<spring:message code="editProfile.editButton"/>"
						class="btn btn-large btn-primary">
				</div>
			</div>

		</form:form>
	</div>
</div>

<a><h6 data-toggle="modal" data-target="#myModal">
		<spring:message code="editProfile.changePassword" />
	</h6></a>

<form:form cssClass="form-horizontal changePasswordForm"
	action="editProfile/change-password" method="post">
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						<spring:message code="editProfile.changePasswordForm" />
					</h4>
				</div>
				<div class="modal-body">


					<div class="form-group">
						<label for="oldPassword" class="col-sm-2 control-label"><spring:message
								code="editProfile.password" /></label>
						<div class="col-sm-10">
							<input type="password" name="oldPassword" id="oldPassword"
								class="form-control" />
						</div>
					</div>

					<div class="form-group">
						<label for="newPassword" class="col-sm-2 control-label"><spring:message
								code="editProfile.newPassword" /></label>
						<div class="col-sm-10">
							<input type="password" id="newPassword" name="newPassword"
								id="newPassword" class="form-control" />
						</div>
					</div>

					<div class="form-group">
						<label for="newPassword" class="col-sm-2 control-label"><spring:message
								code="editProfile.passwordAgain" /></label>
						<div class="col-sm-10">
							<input type="password" name="password_again" id="password_again"
								class="form-control" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<spring:message code="editProfile.backButton" />
					</button>
					<input class="btn btn-large btn-primary" type="submit"
						value="<spring:message code="editProfile.changePasswordButton"/>">
				</div>
			</div>

		</div>
	</div>
	</div>
</form:form>

<script type="text/javascript">
	$(document).ready(function() {
		
						$(".changePasswordForm").validate(
								{
										rules : {
												oldPassword : {
													required : true,
													remote : {
														url : "<c:url value='/editProfile/change-password/correctOldPassword.html'/>",
														type : "POST",
														data : {
															oldPassword : function() {
																return $("#oldPassword").val();
															}
														}
													}
												},
												
												newPassword : {
													minlength : 4,
													required : true
												},
													password_again : {
													required : true,
													equalTo : "#newPassword"
												}
											},
											highlight: function(element) {
												$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
											},
											unhighlight: function(element) {
												$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
											},
											messages : {
												oldPassword : {
													remote : "<spring:message code="editProfile.passwordError"/>",
													required : "<spring:message code="editProfile.requiredField"/>"
												},
												newPassword : {
													minlength : "<spring:message code="editProfile.newPasswordError"/>",
													required : "<spring:message code="editProfile.requiredField"/>"
												},
												password_again : {
													equalTo : "<spring:message code="editProfile.passwordAgainError"/>",
													required : "<spring:message code="editProfile.requiredField"/>"
												}
											}

										});
						$(".editProfileForm").validate({
							rules: {
								phoneNumber : {
									required : true,
									minlength : 10,
									maxlength : 10
									
								},
								email : {
									required : true,
									email : true
								}
							},
							messages: {
								phoneNumber : {
									required: "<spring:message code="editProfile.requiredField"/>",
									minlength : "<spring:message code="editProfile.phoneNumberError"/>",
									maxlength : "<spring:message code="editProfile.phoneNumberError"/>"
								},
								email : {
									required: "<spring:message code="editProfile.requiredField"/>",
									email : "<spring:message code="editProfile.emailError"/>"
								}
								
							},
							highlight: function(element) {
								$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
							},
							unhighlight: function(element) {
								$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
							},
							
						});
					});
</script>