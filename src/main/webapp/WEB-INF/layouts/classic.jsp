<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>
<head>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<link rel="stylesheet" href="/resources/css/slide.css">

<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
	

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>

<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<script src="/resources/js/jquery.tablesorter.js"></script>


<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.min.js"></script>

<script src="/resources/js/slide.js"></script>


<script
	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-hover-dropdown/2.0.2/bootstrap-hover-dropdown.min.js"></script>

<script src="/resources/js/slider.js"></script>

<link rel="stylesheet" href="/resources/css/slider.css">

<link rel="stylesheet" href="/resources/css/imagescale.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title><tiles:getAsString name="title"></tiles:getAsString></title>

</head>

<body>

	<tiles:insertAttribute name="header" />
	<div class="container-fluid theme-showcase">
		<div class="jumbotron">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<tiles:insertAttribute name="footer" />

</body>
</html>