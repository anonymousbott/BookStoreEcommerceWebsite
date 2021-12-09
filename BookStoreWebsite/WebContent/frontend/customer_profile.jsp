<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Profile - Online Book Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div class="center">
		<h2>Welcome, ${loggedCustomer.fullname}</h2>
	</div>
	<div align="center">
		<table class="book">
			<tr>
				<td align="right"><b>E-mail Address</b></td>
				<td align="left">${loggedCustomer.email}</td>
			</tr>
			<tr>
				<td align="right"><b>Full Name:</b></td>
				<td align="left">${loggedCustomer.fullname}</td>
			</tr>
			<tr>
				<td align="right"><b>Phone Number:</b></td>
				<td align="left">${loggedCustomer.phone}</td>
			</tr>
			<tr>
				<td align="right"><b>Address</b></td>
				<td align="left">${loggedCustomer.address}</td>
			</tr>
			<tr>
				<td align="right"><b>City</b></td>
				<td align="left">${loggedCustomer.city}</td>
			</tr>
			<tr>
				<td align="right"><b>Zip Code:</b></td>
				<td align="left">${loggedCustomer.zipcode}</td>
			</tr>
			<tr>
				<td align="right"><b>Country</b></td>
				<td align="left">${loggedCustomer.country}</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr align="center">
				<td colspan="2"><b><a href="edit_profile?id=${loggedCustomer.customerId }">Edit My Profile</a></b></td>
			</tr>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>