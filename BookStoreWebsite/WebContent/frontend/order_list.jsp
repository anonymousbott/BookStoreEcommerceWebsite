<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Order History - Evergreen Bookstore Administration</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h3>My Order History</h3>
	</div>
	<div align="center">
		<c:if test="${fn:length(bookOrder)==0}">
			<h3 class="message">
				You have not placed any orders
			</h3>
		</c:if>
	</div>
	<c:if test="${fn:length(bookOrder)!=0}">
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>Order ID</th>
				<th>Quantity</th>
				<th>Total Amount</th>
				<th>Order Date</th>
				<th>Status</th>
				<th></th>
			</tr>
			<c:forEach items="${bookOrder}" var="bookOrder" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${bookOrder.orderId}</td>
					<td>${bookOrder.bookCopies}</td>
					<td><fmt:formatNumber value="${bookOrder.total}" type="currency" /></td>
					<td>${bookOrder.orderDate}</td>
					<td>${bookOrder.status}</td>
					<td><a href="show_order_detail?id=${bookOrder.orderId}">View Details</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</c:if>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>