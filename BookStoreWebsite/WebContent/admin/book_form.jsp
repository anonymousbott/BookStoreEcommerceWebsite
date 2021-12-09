<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Book</title>
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<link rel="stylesheet" href="../css/jquery-ui.min.css">
<link rel="stylesheet" href="../css/richtext.min.css">
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/jquery.richtext.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div align="center">
	<c:if test="${book==null}">
		<h2>Create New Book</h2>
	</c:if>
	<c:if test="${book!=null}">
		<h2>Edit New Book</h2>
	</c:if>
</div>
<div align="center">
	<c:if test="${book==null}">
		<form action="create_book" method="post" id="bookForm" enctype="multipart/form-data">
	</c:if>
	<c:if test="${book!=null}">
		<form action="update_book" method="post" id="bookForm" enctype="multipart/form-data">
	</c:if>
	<table class="form">
				<tr>
					<td>
						<input type="hidden" name="bookId" value="${book.bookId}">
					</td>
				</tr>
		<tr>
			<td align="right">Category:</td>
			<td align="left">
				
				<select name="category">
					<c:forEach items="${catListAll}" var="category">
					<c:if test="${categoryId!=null && category.categoryId==categoryId}">
						<option value="${category.categoryId}" selected="selected" >${category.name}</option>
					</c:if>
					<c:if test="${categoryId!=category.categoryId}">
						<option value="${category.categoryId}">${category.name}</option>
					</c:if>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td align="right">Title:</td>
			<td align="left"><input type="text" name="title" id="title" value="${book.title}" size="20"></td>
		</tr>
		<tr>
			<td align="right">Author:</td>
			<td align="left"><input type="text" name="author" id="author" value="${book.author}" size="20"></td>
		</tr>
		<tr>
			<td align="right">ISBN:</td>
			<td align="left"><input type="text" name="isbn" id="isbn" value="${book.isbn}" size="20"></td>
		</tr>
		<tr>
			<td align="right">Publish Date:</td>
			<td align="left"><input type="text" name="publishDate" id="publishDate" value="<fmt:formatDate pattern = "MM/dd/yyyy" 
         value = "${book.publishDate}" />" size="20"></td>
		</tr>
		<tr>
			<td align="right">Book Image:</td>
			<td align="left">
			<input type="file" id="bookImage" name="bookImage" size="20"> <br>
			<img alt="image Preview" id="thumbnail" style="width:20%; margin-top:10px;" src="data:image/jpg;base64,${book.base64Image}">
			</td>
		</tr>
		<tr>
			<td align="right">Price:</td>
			<td align="left"><input type="text" name="price" id="price" value="${book.price}" size="20"></td>
		</tr>
		<tr>
			<td align="right">Description:</td>
			<td align="left"><textarea id="description" name="description" rows="5" cols="50">${book.description}</textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<button type="submit">Save</button> &nbsp; &nbsp; &nbsp;
			<button type="button">Cancel</button>
			</td>
		</tr>
	</table>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<script>
	$(document).ready(function(){
		$("#publishDate").datepicker({
			/* dateFormat: 'dd-mm-yy' */
		});
		
		$("#description").richText();
		$("#bookImage").change(function(){
			showImageThumbNail(this);
		});
		$("#bookForm").validate({
			rules:{
				category:"required",
				title:"required",
				author:"required",
				isbn:"required",
				publishDate:"required",
				<c:if test="${book==null}">
				bookImage:"required",
				</c:if>
				price:"required",
				description:"required"
			},
			messages:{
				category:"Please select a category for the book",
				title:"Please enter title of the book",
				author:"Please enter author of the book",
				isbn:"Please enter ISBN of the book",
				publishDate:"Please enter publis date of the book",
				bookImage:"Please choose an image of the book",
				price:"Please enter price of the book",
				description:"Please enter description of the book"
			}
		});
	});
	function showImageThumbNail(fileInput){
		var file=fileInput.files[0];
		var reader=new FileReader();
		reader.onload = function (event) {
            $("#thumbnail")
              .attr("src", event.target.result);
        };
        reader.readAsDataURL(file);
	}
</script>
</body>
</html>