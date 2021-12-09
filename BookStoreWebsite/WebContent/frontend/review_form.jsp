<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Write Review - Online BookStore</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<form id="reviewForm" action="submit_review" method="post">
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
					<td>
						<div id="rateYo"></div>
						<input type="hidden" name="rating" id="rating">
						<input type="hidden" name="bookId" value="${book.bookId}" >
						<br/>
						<input type="text" name="headline" size="60" placeholder="Headline or summary for your book(required)">
						<br/>
						<br/>
						<textarea rows="10" cols="70" name="comment" placeholder="Write your review details"></textarea>
					</td>
				</tr>
				<tr align="center">
					<td colspan="3" align="center"><button type="submit">Save</button>
						&nbsp;&nbsp;
						<button type="button" id="cancelButton">Cancel</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript">
		$('document').ready(function(){
			$('#cancelButton').click(function(){
				history.go(-1);
			});
			$('#reviewForm').validate({
				rules:{
					headline:"required",
					comment:"required"
				},
				messages:{
					headline:"Please enter headline",
					comment:"Please enter comment"
				}
			});
			$("#rateYo").rateYo({
			    fullStar: true,
			    onSet: function (rating, rateYoInstance) {
			    	$('#rating').val(rating);
			    }
			  });
		});
	</script>
</body>
</html>