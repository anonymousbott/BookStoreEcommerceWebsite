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
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2>Edit Order ID: ${order.orderId}</h2>
	</div>
	<div align="center">
		<c:if test="${ message!=null}">
			<h4 class="message">
				${message}
			</h4>
		</c:if>
	</div>
	<form action="update_order" method="POST" id="orderForm">
	<div align="center">
		<table class="normal">
			<tr>
				<td><b>Ordered By:</b></td>
				<td><input type="text" value="${order.customer.fullname}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td><b>Order Date:</b></td>
				<td>
					<div style="display:none">
						<fmt:formatDate value="${order.orderDate}" pattern="MMM dd,yyyy hh:mm:ss a" var="myDate" />
					</div>
					<input type="text" value="${myDate}" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td><b>Recipient Name:</b></td>
				<td> <input type="text" name="recipientName" value="${order.recipientName}" size="45"/></td>
			</tr>
			<tr>
				<td><b>Recipient Phone:</b></td>
				<td><input type="text" name="recipientPhone" value="${order.recipientPhone}" size="45"/></td>
			</tr>
			<tr>
				<td><b>Ship To:</b></td>
				<td><input type="text" name="shippingAddress" value="${order.shippingAddress}" size="45"/></td>
			</tr>
			<tr>
				<td><b>Payment Method:</b></td>
				<td>
					<select name="paymentMethod">
						<option value="Cash On Delivery">Cash On Delivery</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><b>Ordered Status:</b></td>
				<td>
					<select name="orderStatus">
							<option selected="selected" value="Processing">Processing</option>
							<option selected="selected" value="Shipping">Shipping</option>
							<option selected="selected" value="Delivered">Delivered</option>
							<option selected="selected" value="Completed">Completed</option>
							<option selected="selected" value="Cancelled">Cancelled</option>
					</select>
				</td>
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
					<th></th>
				</tr>
				<c:forEach items="${order.orderDetails}" var="orderDetails" varStatus="status">
					<tr>
						<td>${status.index+1}</td>
						<td>${orderDetails.book.title }</td>
						<td>${orderDetails.book.author}</td>
						<td>
							<input type="hidden" name="bookPrice" value="${orderDetails.book.price}">
							<fmt:formatNumber value="${orderDetails.book.price}" type="currency" />
						</td>
						<td>
							<input type="hidden" name="bookId" value="${orderDetails.book.bookId}">
							<input type="text" value="${orderDetails.quantity}" name="quantity${status.index+1}" size="5">
						</td>
						<td><fmt:formatNumber value="${orderDetails.subTotal}" type="currency"/></td>
						<td><a href="remove_book_from_order?id=${orderDetails.book.bookId}">Remove</a></td>
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
		<a href="javascript:showAddBookPopup()"><b>Add Books</b></a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="submit">Save</button>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="button" id="buttonCancel" onclick="javascript:window.location.href='list_order';">Cancel</button>
	</div>
	</form>
	<jsp:directive.include file="footer.jsp" />
	<script>
		$('document').ready(function(){
			$("#orderForm").validate({
				rules:{
					recipientName:"required",
					recipientPhone:"required",
					shippingAddress:"required",
					<c:forEach items="${order.orderDetails}" var="orderDetails" varStatus="status">
						quantity${status.index+1}:{
							required:true,number:true,min:1
							},
					</c:forEach>
					
				},
				messages:{
					recipientName:"Please enter recipientName",
					recipientPhone:"Please enter recipientPhone",
					shippingAddress:"Please enter shippingAddress",
					<c:forEach items="${order.orderDetails}" var="orderDetails" varStatus="status">
						quantity${status.index+1}:{
							required:"Please enter quantity",
							number:"Quantity must be a number",
							min:"Quantity must be greater than 0"
							},
					</c:forEach>
				}
		
		});
		});
	function showAddBookPopup(){
		var width = 500;
		var height = 300
		var top = (screen.height -height)/2;
		var left = (screen.width - width)/2;
		window.open('add_book_form','_blank','width='+width+',height='+height+',top='+top+',left='+left);
	}
	</script>
</body>
</html>