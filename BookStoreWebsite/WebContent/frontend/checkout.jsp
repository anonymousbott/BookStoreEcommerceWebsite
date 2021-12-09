<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout - Online Book Store</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<c:if test="${message!=null}">
			<div align="center">
				<h4 class="message">${message}</h4>
			</div>
		</c:if>
		
		<c:set var="cart" value="${sessionScope['cart'] }"></c:set>
		<c:set var="customer" value="${sessionScope['loggedCustomer'] }"></c:set>
		<c:if test="${cart.totalItems ==0 or cart == null}">
			<h2>There's no item in your cart</h2>
		</c:if>
		<c:if test="${cart.totalItems > 0}">
				<div align="center">
				<h2>Review Your Order Details &nbsp;<a href="view_cart">Edit</a></h2>
				<table border="1">
					<tr>
						<th>No</th>
						<th colspan="2">Book</th>
						<th>Author</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>SubTotal</th>
					</tr>
					<c:forEach items="${cart.items}" var="item" varStatus="status">
						<tr>
							<td>${status.index+1}</td>
							<td>
								<img class="book_small" alt="" src="data:image/jpg;base64,${item.key.base64Image}">
							</td>
							<td>
								<span id="book-title">${item.key.title}</span>
							</td>
							<td>${item.key.author}</td>
							<td><fmt:formatNumber value="${item.key.price}" type="currency"></fmt:formatNumber></td>
							<td>
								${item.value }
							</td>
							<td><fmt:formatNumber value="${item.value * item.key.price}" type="currency"></fmt:formatNumber></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="3"></td>
						<td><b>${cart.totalQuantity}  Copies</b></td>
						<td>Total:</td>
						<td colspan="2"><b><fmt:formatNumber value="${cart.totalAmount}" type="currency"/></b></td>
					</tr>
				</table>
				</div>
				<div align="center">
					<h3>Your Shipping Information</h3>
					<form action="place_order" method="POST" id="orderForm">
					<table class="normal">
						<tr>
							<td align="right">Recipient Name:</td>
							<td align="left"><input type="text" name="recipientName" value="${customer.fullname}" size=""/></td>
						</tr>
						<tr>
							<td align="right">Recipient Phone:</td>
							<td align="left"><input type="text" name="recipientPhone" value="${customer.phone}" size=""/></td>
						</tr>
						<tr>
							<td align="right">Street Address:</td>
							<td align="left"><input type="text" name="address" value="${customer.address}" size=""/></td>
						</tr>
						<tr>
							<td align="right">City:</td>
							<td align="left"><input type="text" name="city" value="${customer.city}" size=""/></td>
						</tr>
						<tr>
							<td align="right">Zip Code:</td>
							<td align="left"><input type="text" name="zipcode" value="${customer.zipcode}" size=""/></td>
						</tr>
						<tr>
							<td align="right">Country :</td>
							<td align="left"><input type="text" name="country" value="${customer.country}" size=""/></td>
						</tr>
					</table>
					<div align="center">
					<h3>Payment</h3>
					&nbsp;
					<table class="normal">
						<tr>
							<td align="right">Choose Your Payment Method:</td>
							<td align="left">
								<select name="paymentMethod">
									<option value="Cash On Delivery">Cash On Delivery</option>
								</select>
							</td>
						</tr>
					</table>
				</div>
				<div align="center">
					<table class="normal">
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td colspan="2"></td>
							<td><button type="submit">Place Order</button></td>
							<td><a href="${pageContext.request.contextPath}/">Continue Shopping</a></td>
						</tr>
					</table>
				</div>
			</form>
			</div>
		</c:if>
	</div>
	<jsp:directive.include file="footer.jsp" />
	<script type="text/javascript">
		$('document').ready(function(){
			$('#orderForm').validate({
				rules:{
					recipientName:"required",
					recipientPhone:"required",
					address:"required",
					city:"required",
					zipcode:"required",
					country:"required"
				},
				messages:{
					recipientName:"Please enter recipient Name",
					recipientPhone:"Please enter recipient Name",
					address:"Please enter address",
					city:"Please enter city",
					zipcode:"Please enter zipcode",
					country:"Please enter country"
				}
			})
		});
	</script>
</body>
</html>