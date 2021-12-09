<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Customers - Evergreen BookStore Administration</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="center">
		<h2 class="pageheading">Customer Management</h2>
		<h3><a href="customer_form.jsp">Create New Customer</a></h3>
	</div>
	<div align="center">
		<c:if test="${message!=null }">
			<h4 class="message">
				${message}
			</h4>
		</c:if>
	</div>
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>E-mail</th>
				<th>Full Name</th>
				<th>City</th>
				<th>Country</th>
				<th>Registered Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach items="${customerList}" var="customer" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${customer.customerId}</td>
					<td>${customer.email}</td>
					<td>${customer.fullname}</td>
					<td>${customer.city}</td>
					<td>${customer.country}</td>
					<td>${customer.registerDate}</td>
					<td colspan="2">
						<a href="edit_customer?id=${customer.customerId}">Edit</a> &nbsp; &nbsp;
						<a href="javascript:void(0);" class="deleteLink" id="${customer.customerId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".deleteLink").each(function(){
				$(this).on('click',function(){
					var customerId=$(this).attr("id");
					if(confirm("Are you sure you want to delete customer with Id "+customerId+" ?")){
						window.location='delete_customer?id='+customerId;
					}
				})
				
			});
			
		});
	</script>
</body>
</html>