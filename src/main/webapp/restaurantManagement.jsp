<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Users</h3>
			<hr>
			<div class="container text-left">
				<!-- Add new user button redirects to the register.jsp page -->
				<a href="<%=request.getContextPath()%>/restaurant.jsp"
					class="btn btn-success">Add New User</a>
			</div>
			<br>
			<!-- Create a table to list out all current users information -->
			<table class="table">
				<thead>
					<tr>
						<th>Name</th>
						<th>Location</th>
						<th>Opening Hour</th>
						<th>Closing Hour</th>
						<th>Description</th>
						<th>Cuisine Type</th>
						<th>Actions</th>
					</tr>
				</thead>
				<!-- Pass in the list of users receive via the Servlet’s response to a loop
-->
				<tbody>
					<c:forEach var="user" items="${listOfRestaurants}">
						<!-- For each user in the database, display their
information accordingly -->
						<tr>
							<td><c:out value="${user.restaurantName}" /></td>
							<td><c:out value="${user.restaurantLocation}" /></td>
							<td><c:out value="${user.closingTime}" /></td>
							<td><c:out value="${user.openingTime}" /></td>
							<td><c:out value="${user.restaurantDescription}" /></td>
							<td><c:out value="${user.restaurantCuisine}" /></td>
							<!-- For each user in the database, Edit/Delete
buttons which invokes the edit/delete functions -->
							<td><a
								href="edit?name=<c:out value='${user.restaurantName}'
/>">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?name=<c:out
value='${user.restaurantName}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>