<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Category</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2>
			<c:if test="${category==null }">
			Create New Category
			</c:if>
			<c:if test="${category!=null }">
			Edit Category
			</c:if>
		</h2>
	</div>

	<div align="center">
		<c:if test="${category==null }">
		<form action="create_category" method="POST" id="categoryForm">
		</c:if>
		<c:if test="${category!=null }">
		<form action="update_category" method="POST" id="categoryForm">
				
		</c:if>
			<table class="form">
				<tr>
					<td>
						<input type="hidden" name="catId" value="${category.categoryId}">
					</td>
				</tr>
				<tr>
					<td align="right">Name:</td>
				<td align="left"><input type="text" name="name" id="name"
					value="${ category.name}" /></td>
			</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<button type="submit">Save</button> &nbsp;&nbsp;&nbsp;
					<button id="buttonCancel" type="button">Cancel</button>
					</td>

				</tr>
			</table>
		</form>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
<script>

$('document').ready(function(){
	console.log('document ready');
	/* console.log($('#email').value());
	console.log($('#fullname'.value())) */;
	$("#categoryForm").validate({
		rules:{
			name:"required"
		},
	messages:{
		name:"Please enter category name"
	}
	
	});
	$("#buttonCancel").click(function(){
		history.go(-1);
	});
});
</script>
</html>