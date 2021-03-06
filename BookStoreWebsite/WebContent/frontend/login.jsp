<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Login</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<h2>Customer Login</h2>
		<c:if test="${message!=null}">
			<div align="center">
				<h4 class="message">${message}</h4>
			</div>
		</c:if>
		<form id="loginForm" action="login" method="POST">
			<table class="form">
				<tr>
					<td>E-mail:</td>
					<td><input type="text" name="email" id="email" size="20"/></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" id="password" size="20"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<button>
						Login
					</button>
					</td>		
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		$('document').ready(function(){
			$('#loginForm').validate({
				rules:{
					email:{
						required:true,
						email:true
					},
					password:"required"
				
				},
				messages:{
					email:{
						required:"Please enter email",
						email:"Please enter a valid email address"
					},
					password:"Please enter password"
				}
			});
		});
	</script>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>