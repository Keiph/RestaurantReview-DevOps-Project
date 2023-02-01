<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Restaurant Management Application</title>
</head>
<body>
	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Restaurants</h3>
			<hr>
			<div class="container text-left">
				<!-- Add new restaurant button redirects to the restaurant.jsp page -->
				<a href="<%=request.getContextPath()%>/restaurant.jsp"
					class="btn btn-success" id="toResBtn">Add New Restaurant</a>
			</div>
			<br>
			<!-- Create a table to list out all current restaurants information -->
			<table class="table">
				<thead>
					<tr>
						<th>Name</th>
						<th>Location</th>
						<th>Opening Hours</th>
						<th>Closing Hours</th>
						<th>Description</th>
						<th>Cuisine Type</th>
						<th>Actions</th>
					</tr>
				</thead>
				<!-- Pass in the list of restaurants receive via the Servlet’s response to a loop
-->
				<tbody>
					<c:forEach var="restaurant" items="${listOfRestaurants}">
						<!-- For each restaurant in the database, display their
information accordingly -->
						<tr>
							<td><c:out value="${restaurant.restaurantName}" /></td>
							<td><c:out value="${restaurant.restaurantLocation}" /></td>
							<td><c:out value="${restaurant.openingTime}" /></td>
							<td><c:out value="${restaurant.closingTime}" /></td>
							<td><c:out value="${restaurant.restaurantDescription}" /></td>
							<td><c:out value="${restaurant.restaurantCuisine}" /></td>
							<!-- For each restaurant in the database, Edit/Delete
buttons which invokes the edit/delete functions -->
							<td><a
								href="edit?name=<c:out value='${restaurant.restaurantName}'
/>">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?name=<c:out
value='${restaurant.restaurantName}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>