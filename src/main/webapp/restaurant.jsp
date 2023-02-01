<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Restaurant</title>
</head>
<body>
	<form action="RegisterRestaurantServlet" method="post">
		Name of Restaurant: <input type="text" name="restaurantName"><br>
		Restaurant Location: <input type="text" name="restaurantLocation"><br>
		Opening time: <input type="text" name="restaurantOpening"><br>
		Closing time: <input type="text" name="restaurantClosing"><br>
		Description of Restaurant: <input type="text"
			name="restaurantDescription"><br> Cuisine type: <select
			name="restaurantCuisine">
			<option>Western</option>
			<option>Chinese</option>
			<option>Vietnamese</option>
			<option>Korean</option>
			<option>German</option>
			<option>Indian</option>
		</select> <input type="submit" value="Add Restaurant" id="addRes"/>
	</form>

</body>
</html>