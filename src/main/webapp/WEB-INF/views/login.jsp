<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ include file="../layouts/taglib.jsp"%>
<br>
<br>
<style>
.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>

<c:url value="/login" var="loginUrl"/>
<form  action="${loginUrl}" class="form-signin" role="form" method="post">       
    <c:if test="${param.error != null}">        
        <div class="alert alert-danger"> <spring:message code="login.error"/> </div>
     
    </c:if>
    <c:if test="${param.logout != null}">       
       <div class="alert alert-success text-center"><spring:message code="login.logout"/></div>
           
    </c:if>
    <div class="row text-center">
  		<h2 class="form-signin-heading"><spring:message code="login.title"/></h2>
  		</div>
        <input type="text" id="username" name="username" class="form-control" placeholder="<spring:message code="login.login"/>"required autofocus/>	
        <input type="password" id="password" name="password" class="form-control" placeholder="<spring:message code="login.password"/>" required/>	
   
    <input type="hidden"                        
        name="${_csrf.parameterName}"
        value="${_csrf.token}"/>
   <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="login.singIn"/></button>
</form>