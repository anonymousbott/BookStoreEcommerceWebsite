<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> ${book.title} - Online Books Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div class="center">
		<table class="book">
			<tr>
				<td colspan="3" align="left">
					<p id="book-title"> ${book.title}</p>
					by <span id="author">${book.author}</span>
				</td>
			</tr>
			<tr>
				<td rowspan="2">
					<img class="book_large" alt="" src="data:image/jpg;base64,${book.base64Image}">
				</td>
				<td valign="top" align="left">
					<jsp:directive.include file="book_rating.jsp"/>&nbsp;&nbsp;
					<a href="#reviews">${fn:length(book.reviews)} Reviews</a>
				</td>
				<td rowspan="2" valign="top" width="20%">
					<h2>$${book.price}</h2>
					<br/><br/>
					<a href="add_to_cart?book_id=${book.bookId}"><button type="submit">Add to Cart</button></a>
				</td>
			</tr>
			<tr>
				<td id="description"> ${book.description} </td>
			</tr>
			<tr>
			<td>&nbsp;</td>
			</tr>
			<tr>
				<td align="left">
					<h2><a id="reviews">Customer Reviews</a></h2>
				</td>
				<td align="center" colspan="2">
					<a href="write_review?book_id=${book.bookId}"><button>Write a customer Review</button></a>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="left">
					<table class="normal">
						<c:forEach items="${book.reviews }" var="review">
							<tr>
								<td>
									<c:forTokens items="${review.stars}" delims="," var="start">
										<c:if test="${start eq 'on' }">
											<img alt="" src="images/rating_on.png">
										</c:if>
										<c:if test="${start eq 'off' }">
											<img alt="" src="images/rating_off.png">
										</c:if>
									</c:forTokens>
									-<b>${review.headline}</b>
								</td>
							</tr>
							<tr>
								<td>
									by ${review.customer.fullname } on ${review.reviewTime}
								</td>
							</tr>
							<tr>
								<td>
									<i>${review.comment}</i>
								</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>