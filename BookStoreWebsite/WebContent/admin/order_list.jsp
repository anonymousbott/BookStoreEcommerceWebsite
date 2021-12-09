<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Orders - Evergreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h1>Order Management</h1>
	</div>
	<div align="center">
		<c:if test="${ message!=null}">
			<h4 class="message">
				${message}
			</h4>
		</c:if>
	</div>
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>Order ID</th>
				<th>Ordered By</th>
				<th>Book Copies</th>
				<th>Total</th>
				<th>Payment Method</th>
				<th>Status</th>
				<th>Order Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach items="${listOrder}" var="order" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${order.orderId}</td>
					<td>${order.customer.fullname}</td>
					<td>${order.bookCopies}</td>
					<td><fmt:formatNumber value="${order.total}" type="currency"></fmt:formatNumber></td>
					<td>${order.paymentMethod}</td>
					<td>${order.status}</td>
					<td>${order.orderDate}</td>
					<td colspan="3">
						<a href="view_order?id=${order.orderId}">Details</a> &nbsp; 
						<a href="edit_order?id=${order.orderId}">Edit</a> &nbsp; 
						<a href="javascript:void(0);" class="deleteLink" id="${order.orderId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp" />
	<script>
	$(document).ready(function(){
		$(".deleteLink").each(function(){
			$(this).on("click",function(){
				orderId=$(this).attr("id");
				if(confirm("Are you sure you want to delete Order with Id "+orderId+" ?")){
					window.location='delete_order?id='+orderId;
				}
			});
		});
	});
	</script>
</body>
</html>