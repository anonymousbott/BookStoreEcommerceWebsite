<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New User</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<c:if test="${user==null}">
			<h2 class="pageheading">Create New User</h2>
		</c:if>
		<c:if test="${user!=null}">
			<h2 class="pageheading">Edit User</h2>
		</c:if>

	</div>
	<div align="center">
		<c:if test="${user==null}">
			<form action="create_user" method="post" id="userForm">
		</c:if>
		<c:if test="${user!=null}">
			<form action="update_user" method="post" id="userForm">
		</c:if>

		<table class="form">
			<tr>
				<td><input type="hidden" name="userId" value="${user.userId}">
				</td>
			</tr>
			<tr>
				<td align="right">E-mail:</td>
				<td align="left"><input type="text" id="email" name="email"
					value="${user.email}" size="20"></td>
			</tr>
			<tr>
				<td align="right">Full Name:</td>
				<td align="left"><input type="text" id="fullname"
					name="fullname" value="${user.fullName}" size="20"></td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td align="left"><input type="password" id="password"
					name="password" value="${user.password}" size="20"></td>
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

	<jsp:directive.include file="footer.jsp" /></body>
<script type="text/javascript">

	$('document').ready(function(){
		console.log('document ready');
		/* console.log($('#email').value());
		console.log($('#fullname'.value())) */;
		$("#userForm").validate({
			rules:{
				email:{
					required:true,
					email:true
					
				},
				fullname:"required",
				<c:if test="${user==null}">
				password:"required"
				</c:if>
			},
		messages:{
			email:{
				required:"Please enter email",
				email:"Please enter a valid email address"
				},
			fullname:"Please enter full name",
			<c:if test="${user==null}">
			password:"Please enter password"
			</c:if>
		}
		
		});
		$("#buttonCancel").click(function(){
			history.go(-1);
		});
	});
</script>
</html>