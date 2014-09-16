<%@ attribute name="list" type="java.util.List" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script type="text/javascript" src="/resources/js/recursiveTag.js"></script>

<body>
	<c:if test="${!empty list}">
		<ul>
			<c:forEach var="subcategory" items="${list}">

				<li><c:out value="${subcategory.getCategoryName()}" />
				 <a href="${pageContext.request.contextPath}/delete/${subcategory.getCategoryName()}.html">
						<span class="glyphicon glyphicon-trash"></span></a> 
						<a href="${pageContext.request.contextPath}/addCat/${subcategory.getCategoryId()}/a.html">
						<span class="glyphicon glyphicon-plus-sign"></span></a>
						</li>

				<myTags:recursiveTag list="${subcategory.getChildren()}" />
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>