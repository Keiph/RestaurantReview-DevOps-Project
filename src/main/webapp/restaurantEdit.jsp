<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light">
		<div>
			<a class="navbar-brand"> Restaurant Management Application </a>
		</div>
		<ul class="navbar-nav">
			<li><a
				href="<%=request.getContextPath()%>/RestaurantServlet/dashboard"
				class="nav-link">Back to Dashboard</a></li>
		</ul>
	</nav>
	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<c:if test="${restaurant != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${restaurant == null}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${restaurant != null}">
Edit User
</c:if>
						<c:if test="${restaurant == null}">
Add New User
</c:if>
					</h2>
				</caption>
				<c:if test="${restaurant != null}">
					<input type="hidden" name="oriRestaurantName"
						value="<c:out
value='${restaurant.restaurantName}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>Restaurant Name</label> <input type="text"
						value="<c:out
value='${restaurant.restaurantName}' />" class="form-control"
						name="restaurantName" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Restaurant Location</label> <input type="text"
						value="<c:out
value='${restaurant.restaurantLocation}' />" class="form-control"
						name="restaurantLocation">
				</fieldset>
				<fieldset class="form-group">
					<label>Opening Time</label> <input type="text"
						value="<c:out
value='${restaurant.openingTime}' />" class="form-control"
						name="openingTime">
				</fieldset>
				<fieldset class="form-group">
					<label> Closing Time</label> <input type="text"
						value="<c:out
value='${restaurant.closingTime}' />" class="form-control"
						name="closingTime">
				</fieldset>
				<fieldset class="form-group">
					<label> Description</label> <input type="text"
						value="<c:out
value='${restaurant.restaurantDescription}' />" class="form-control"
						name="restaurantDescription">
				</fieldset>
				<fieldset class="form-group">
					<label> Cuisine Type</label> <input type="text"
						value="<c:out
value='${restaurant.restaurantCuisine}' />" class="form-control"
						name="restaurantCuisine">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>