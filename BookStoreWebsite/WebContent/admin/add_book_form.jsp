<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Book to Order</title>
</head>
<body>
	<div align="center">
		<h2>Add Book To Order ID: ${order.orderId}</h2>
		<form method="post" action="add_book_to_order" id="">
			<table>
				<tr>
					<td>Select a book:</td>
					<td>
						<select name="bookId">
						<c:forEach items="${listBook}" var="book">
							<option value="${book.bookId}">${book.title} - ${book.author}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Number of Copies:</td>
					<td>
						<select name="quantity">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr align="center">
					<td align="right"><button type="submit">Add</button></td>
					<td align="left"><button type="button" onclick="javascript:self.close();">Cancel</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>