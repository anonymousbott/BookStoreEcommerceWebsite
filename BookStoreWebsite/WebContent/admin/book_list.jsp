<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Users - Evergreen BookStore Administration</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
	<h1>Books Management</h1>
	<h3><a href="new_book">Create New Book</a></h3>
	</div>
	<div align="center">
		<c:if test="${message!=null}">
			<h4 class="message">
				${message}
			</h4>
		</c:if>
	</div>
	<div align="center">
	<table border="1" cellpadding="5">
		<tr>
		<th>Index</th>
		<th>Id</th>
		<th>Image</th>
		<th>Title</th>
		<th>Author</th>
		<th>Category</th>
		<th>Price</th>
		<th>Last Updated</th>
		<th>Actions</th>
		</tr>
		<c:forEach items="${listAllBook}" var="book" varStatus="status">
		<tr>
			<td>${status.index+1}</td>
			<td>${book.bookId}</td>
			<td><img alt="" src="data:image/jpg;base64,${book.base64Image}" width="84" height="110"></td>
			<td>${book.title}</td>
			<td>${book.author}</td>
			<td>${book.category.name}</td>
			<td>$ ${book.price}</td>
			<td><fmt:formatDate pattern = "MM/dd/yyyy" value = "${book.lastUpdateTime}" /></td>
			<td colspan="2">
			<a href="edit_book?id=${book.bookId}">Edit</a> &nbsp;
			<a href="javascript:void(0);" class="deleteLink" id="${book.bookId}">Delete</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript">
	$(document).ready(function(){
		$(".deleteLink").each(function(){
			$(this).on("click",function(){
				var bookId=$(this).attr("id");
				if(confirm("Are you sure you want to delete Book with Id "+bookId+" ?")){
					window.location='delete_book?id='+bookId;
				}
			});
		})
	});
	
	</script>
</body>
</html>