<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Order Detail - Evergreen Bookstore</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<c:if test="${order == null}">
			<h4 class="message">
				Sorry,You are not authorized to view this order
			</h4>
		</c:if>
	</div>
	<c:if test="${order != null}">
	<div align="center">
		<h2>Your Order ID: ${order.orderId}</h2>
	</div>
	<div align="center">
		<table class="normal">
			<tr>
				<td><b>Ordered Status:</b></td>
				<td>${order.status}</td>
			</tr>
			<tr>
				<td><b>Order Date:</b></td>
				<td><fmt:formatDate value="${order.orderDate}" pattern="MMM dd,yyyy hh:mm:ss a" /></td>
			</tr>
			<tr>
				<td><b>Quantity:</b></td>
				<td>${order.bookCopies}</td>
			</tr>
			<tr>
				<td><b>Total Amount:</b></td>
				<td><fmt:formatNumber value="${order.total}" type="currency" /></td>
			</tr>
			<tr>
				<td><b>Recipient Name:</b></td>
				<td>${order.recipientName}</td>
			</tr>
			<tr>
				<td><b>Recipient Phone:</b></td>
				<td>${order.recipientPhone}</td>
			</tr>
			<tr>
				<td><b>Ship To:</b></td>
				<td>${order.shippingAddress}</td>
			</tr>
			<tr>
				<td><b>Payment Method:</b></td>
				<td>${order.paymentMethod}</td>
			</tr>
		</table>
	</div>
	
	<div align="center">
		<h2>Ordered Books:</h2>
			<table border="1">
				<tr>
					<th>No</th>
					<th>Book</th>
					<th>Author</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Sub Total</th>
				</tr>
				<c:forEach items="${order.orderDetails}" var="orderDetails" varStatus="status">
					<tr>
						<td>${status.index+1}</td>
						<td><img style="vertical-align: middle" alt="" src="data:image/jpg;base64,${orderDetails.book.base64Image}" width="48" height="64">
						${orderDetails.book.title }
						</td>
						<td>${orderDetails.book.author}</td>
						<td><fmt:formatNumber value="${orderDetails.book.price}" type="currency" /></td>
						<td>${orderDetails.quantity}</td>
						<td><fmt:formatNumber value="${orderDetails.subTotal}" type="currency"/></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4" align="right">
						<b><i>TOTAL</i></b>
					</td>
					<td>
						<b>${order.bookCopies}</b>
					</td>
					<td colspan="2">
						<fmt:formatNumber value="${order.total}" type="currency" />
					</td>
				</tr>
			</table>
	</div>	
	</c:if>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>