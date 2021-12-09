<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit My Profile</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
			<h2>Edit My Profile</h2>
	</div>
	<div align="center">
			<form method="post" action="update_profile" id="customerForm">
			<table class="form">
				<tr>
					<td align="right">E-mail Address:</td>
					<td align="left"><b>${loggedCustomer.email}</b> (Cannot be Changed)</td>
				</tr>
				<tr>
					<td align="right">Full Name:</td>
					<td align="left"><input type="text" name="fullName" id="fullName"  size="45" value="${loggedCustomer.fullname}"></td>
				</tr>
				<tr>
					<td align="right">Phone Number:</td>
					<td align="left"><input type="text" name="phone" id="phone"  size="45" value="${loggedCustomer.phone}"></td>
				</tr>
				<tr>
					<td align="right">Address:</td>
					<td align="left"><input type="text" name="address" id="address"  size="45" value="${loggedCustomer.address}"></td>
				</tr>
				<tr>
					<td align="right">City:</td>
					<td align="left"><input type="text" name="city" id="city"  size="45" value="${loggedCustomer.city}"></td>
				</tr>
				<tr>
					<td align="right">Zip Code:</td>
					<td align="left"><input type="text" name="zipCode" id="zipCode"  size="45" value="${loggedCustomer.zipcode}"></td>
				</tr>
				<tr>
					<td align="right">Country:</td>
					<td align="left"><input type="text" name="country" id="country"  size="45" value="${loggedCustomer.country}"></td>
				</tr>
				<tr align="center">
					<td colspan="2">
						<i>Leave password fields blank if you dont want to change </i>
					</td>
				</tr>
				<tr>
					<td align="right">Password:</td>
					<td align="left"><input type="password" name="password" id="password"  size="45"></td>
				</tr>
				<tr>
					<td align="right">Confirm Password:</td>
					<td align="left"><input type="password" name="confirmPassword" id="confirmPassword" size="45"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Save</button>&nbsp; &nbsp; &nbsp;
						<button type="button" id="cancelCustomerButton">Cancel</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript">
	$('document').ready(function(){
		$("#customerForm").validate({
			rules:{
				email:{
					required:true,
					email:true
				},
				fullName:"required",
				confirmPassword:{
					equalTo:"#password"
				},
				phone:"required",
				address:"required",
				city:"required",
				zipCode:"required",
				country:"required"
			},
			messages:{
				email:{
					required:"Please enter email",
					email:"Please enter a valid email address"
					},
					fullName:"Please enter full name",
					confirmPassword:{
						equalTo:"Confirm password does not match password"
					},
					phone:"Please enter phone no",
					address:"Please enter address",
					city:"Please enter city",
					zipCode:"Please enter zipCode",
					country:"Please enter country"
			}
		});
		$("#cancelCustomerButton").click(function(){
			history.go(-1);
		});
	});
	</script>
</body>
</html>