<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Review Posted- Online BookStore</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
			<table class="normal" width="60%">
				<tr>
					<td><h2>Your Reviews</h2></td>
					<td>&nbsp;</td>
					<td><h2>${loggedCustomer.fullname}</h2></td>
				</tr>
				<tr>
					<td colspan="3"><hr/></td>
				</tr>
				<tr>
					<td>
						<span id="book-title">${book.title}</span>
						<br/>
						<br/>
						<img class="book_large" alt="" src="data:image/jpg;base64,${book.base64Image}">
					</td>
					<td colspan="2">
						<h3>Your review has been posted. Thank you!</h3>
					</td>		
				</tr>
			</table>
	</div>
<jsp:include page="footer.jsp"></jsp:include>	
</body>
</html>