<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Reviews - Evergreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h1>Review Management</h1>
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
				<th>ID</th>
				<th>Book</th>
				<th>Rating</th>
				<th>Headline</th>
				<th>Customer</th>
				<th>Review On</th>
				<th>Actions</th>
			</tr>
			<c:forEach items="${listReviews}" var="review" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${review.reviewId}</td>
					<td>${review.book.title}</td>
					<td>${review.rating}</td>
					<td>${review.headline}</td>
					<td>${review.customer.fullname}</td>
					<td>${review.reviewTime}</td>
					<td colspan="2"><a
						href="edit_review?id=${review.reviewId}">Edit</a> &nbsp; <a
						href="javascript:void(0);" class="deleteLink" id="${review.reviewId}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
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