<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Evergreen Books - Online Books Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div class="center" style="margin:0 auto;">
	<br/><br/>
		
		<h2>New Books:</h2>
		<div>
			<c:forEach items="${listNewBook}" var="book">
				<jsp:directive.include file="book_group.jsp"/>
			</c:forEach>
		</div>
		<div class="next_row">
			<h2 style="clear:both">Best-Selling Books:</h2>
			&nbsp;
			<c:forEach items="${listBestSellingBooks}" var="book">
				<jsp:directive.include file="book_group.jsp"/>
			</c:forEach>
		</div>
		<div class="next_row">
			<h2 style="clear:both">Most-Favored Books:</h2>
			&nbsp;
			<c:forEach items="${listMostFavouredBooks}" var="book">
				<jsp:directive.include file="book_group.jsp"/>
			</c:forEach>
		</div>
		<br/><br/>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>