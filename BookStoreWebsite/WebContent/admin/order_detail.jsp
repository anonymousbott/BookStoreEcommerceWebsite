<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Detail - Evergreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2>Details of Order ID: ${order.orderId}</h2>
	</div>
	<div align="center">
		<c:if test="${ message!=null}">
			<h4 class="message">
				${message}
			</h4>
		</c:if>
	</div>
	<div align="center">
		<h2>Order Overview</h2>
		<table>
			<tr>
				<td><b>Ordered By:</b></td>
				<td>${order.customer.fullname}</td>
			</tr>
			<tr>
				<td><b>Book Copies:</b></td>
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
			<tr>
				<td><b>Ordered Status:</b></td>
				<td>${order.status}</td>
			</tr>
			<tr>
				<td><b>Order Date:</b></td>
				<td>${order.orderDate}</td>
			</tr>
		</table>
	</div>
	
	<div align="center">
		<h2>Ordered Books:</h2>
			<table border="1">
				<tr>
					<th>Index</th>
					<th>Book Title</th>
					<th>Author</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Sub Total</th>
				</tr>
				<c:forEach items="${order.orderDetails}" var="orderDetails" varStatus="status">
					<tr>
						<td>${status.index+1}</td>
						<td>${orderDetails.book.title }</td>
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
					<td>
						<fmt:formatNumber value="${order.total}" type="currency" />
					</td>
				</tr>
			</table>
	</div>
	<div align="center">
		<br/>
		<a href="">Edit This Order</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="">Delete This Order</a>
	</div>
	<jsp:directive.include file="footer.jsp" />
	<script>
	$(document).ready(function(){
		$(".deleteLink").each(function(){
			$(this).on("click",function(){
				reviewId=$(this).attr("id");
				if(confirm("Are you sure you want to delete Review with Id "+reviewId+" ?")){
					window.location='delete_review?id='+reviewId;
				}
			});
		});
	});
	</script>
</body>
</html>