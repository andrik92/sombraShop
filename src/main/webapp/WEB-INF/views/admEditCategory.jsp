<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags"%>
<link href="/resources/css/admpagecss.css" rel="stylesheet"
	type="text/css" />
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />

<div id="error" title="Bad category name" style="display: none">
	<h5 style="margin-left: 10px;">
		<font color="red">You have this category name already!Pick
			another.</font> <a href="#"><input type="button" value="Ok"
			class="btn btn-default"></a>
	</h5>
</div>


<div id="dialog" title="Delete category" style="display: none">
	<form:form class="form-group" action="editLots" commandName="category">
		<h5>
			<font style="margin-left: 10px;" color="red">[${categoryName}]
				has lots inside change their category before delete! </font>
		</h5>
		<h5 style="margin-left: 10px;">
			Choose new category for lots :
			<form:select path="categoryId">
				<form:options items="${categorie}" itemValue="categoryId"
					itemLabel="categoryName" />
			</form:select>
		</h5>
		<input type="hidden" name=categoryName value="${categoryName}">
		<h5 style="margin-left: 243px;">
			<input type="submit" value=" Confirm " class="btn btn-default">
			<a href="#"><input type="button" value="  Cancel "
				class="btn btn-default"></a>
		</h5>
	</form:form>
</div>

<div id="addCategory" title="Add new Category" style="display: none">
	<form:form action="addCategory" commandName="category">
		<div class="form-inline">
			<h5 style="margin-left: 10px;">
				<spring:message code="adminmenu.add" />
				:
				<form:input path="categoryName" class="form-control"
					placeholder="Category Name" />
				<form:errors path="categoryName" />
			</h5>
		</div>
		<input type="hidden" name=categoryId value="${categoryId}">
		<h5 style="margin-left: 183px;">
			<input type="submit" value="<spring:message code="adminmenu.addc" />"
				class="btn btn-default"> <a href="#/admEditCategory"><input
				type="button" value="  Cancel " class="btn btn-default"></a>
		</h5>
	</form:form>
</div>

<br>
<div class="breadcrumb">
	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div>
				<ul class="nav nav-pills ">
					<li><a href="/admEditUsers/1"><spring:message
								code="adminmenu.editUsers" /></a></li>
					<li class="active"><a href="/admEditCategory"><spring:message
								code="adminmenu.editCategories" /></a></li>
					<li><a href="/admEditCities"><spring:message
								code="adminmenu.city" /></a></li>
					<li><a href="/createLot"><spring:message
								code="adminmenu.addLot" /></a></li>
				</ul>
			</div>
		</div>

		<br>

		<div class="panel panel-default">
			<div class="panel-body">
				<br> <label><spring:message code="adminmenu.tree" />:</label>
				<myTags:recursiveTag list="${categories}" />
			</div>
		</div>
	</div>
</div>





