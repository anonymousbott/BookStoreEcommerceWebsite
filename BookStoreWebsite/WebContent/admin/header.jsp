<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center">
	<div>
		<a href="${pageContext.request.contextPath }/admin/">
			<img alt="" src="../images/BookstoreAdminLogo.png">
		</a>
	</div>
	<div>
		Welcome, <c:out value="${sessionScope.userEmail}"></c:out> | <a href="logout">Logout</a>
		<br/><br/>
	</div>
	<div id="headermenu">
	
	<b>
	<div>
		<a href="list_users">
			<img src="../images/users.png"/><br/>Users
		</a>
	</div>
	<div>
		<a href="list_category">
		<img src="../images/category.png"/><br/>Categories
		</a>
	</div>
	<div>
		<a href="list_books">
		<img src="../images/bookstack.png"/><br/>	Books
		</a>
	</div>
	<div>
		<a href="list_customers">
		<img src="../images/customer.png"/><br/>Customers
		</a>
	</div>
	<div>
		<a href="list_reviews">
		<img src="../images/review.png"/><br/>Reviews
		</a>
	</div>
	<div>
		<a href="list_order">
		<img src="../images/order.png"/><br/>Orders
		</a>
	</div>
	
	</b>
	</div>
</div>