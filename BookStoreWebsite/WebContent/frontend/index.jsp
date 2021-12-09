<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Evergreen Books - Online Books Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div class="center" style="margin:0 auto;">
	<br/><br/>
		
		<h2>New Books:</h2>
		<div>
			<c:forEach items="${listNewBook}" var="book">
				<div class="book">
				
				<div>
					<a href="view_book?id=${book.bookId }">
						<img class="book_small" alt="" src="data:image/jpg;base64,${book.base64Image}">
					</a>
				</div>
				 <div>
				 	<a href="view_book?id=${book.bookId}">
				 		<b>${book.title}</b>
				 	</a>
				 </div>
				 <div>
				 	<jsp:directive.include file="book_rating.jsp"/>
				 </div>
				<div><i>${book.author}</i></div>
				<div>$${book.price}</div>
				
			</div>
			</c:forEach>
		</div>
		<div class="next_row">
			<h2 style="clear:both">Best-Selling Books:</h2>
		</div>
		<div class="next_row">
			<h2 style="clear:both">Most-Favored Books:</h2>
		</div>
		<br/><br/>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>