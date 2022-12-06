
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dvops.maven.eclipse.Restaurant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class RestaurantServlet
 */
@WebServlet("/RestaurantServlet")
public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// list of variables used for database connections
	private String jdbcURL = "jdbc:mysql://localhost:3306/restaurant_review";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	// list of SQL prepared statements to perform CRUD to our database
	private static final String INSERT_RESTAURANT_SQL = "INSERT INTO RestaurantDetails"
			+ " (restaurantName, restaurantLocation, openingTime, closingTime, restaurantDescription, restaurantCuisine) VALUES "
			+ " (?, ?, ?, ?, ?, ?);";
	private static final String SELECT_RESTAURANT_BY_NAME = "select restaurant_name, restaurant_location, restaurant_open_time, restaurant_closing_time, restaurant_description, cuisine_category from RestaurantDetails where restaurant_name =?";
	private static final String SELECT_ALL_RESTAURANTS = "select * from RestaurantDetails ";
	private static final String DELETE_RESTAURANT_SQL = "delete from RestaurantDetails where restaurant_name = ?;";
	private static final String UPDATE_RESTAURANT_SQL = "update RestaurantDetails set restaurant_name = ?,restaurant_location= ?, restaurant_open_time =?,restaurant_closing_time =?, restaurant_description =?, cuisine_category =? where restaurant_name= ?;";

	// Implement the getConnection method which facilitates connection to the
	// database via JDBC
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RestaurantServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	// Step 5: listOfRestaurants function to connect to the database and retrieve
	// all restaurants
	// records
	private void listOfRestaurants(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Restaurant> restaurants = new ArrayList<>();
		try (Connection connection = getConnection();
				// Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RESTAURANTS);) {
			// Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Process the ResultSet object.
			while (rs.next()) {
				String restaurantName = rs.getString("restaurant_name");
				String restaurantLocation = rs.getString("restaurant_location");
				String openingTime = rs.getString("restaurant_open_time");
				String closingTime = rs.getString("restaurant_closing_time");
				String restaurantDescription = rs.getString("restaurant_description");
				String restaurantCuisine = rs.getString("cuisine_category");
				restaurants.add(new Restaurant(restaurantName, restaurantLocation, openingTime, closingTime,
						restaurantDescription, restaurantCuisine));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Step 5.4: Set the restaurants list into the listOfRestaurants attribute to be
		// pass to the restaurantManagement.jsp
		request.setAttribute("listOfRestaurants", restaurants);
		request.getRequestDispatcher("/restaurantManagement.jsp").forward(request, response);
	}

	// method to get parameter, query database for existing user data and redirect
	// to user edit page
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// get parameter passed in the URL
		String restaurantName = request.getParameter("restaurantName");
		Restaurant existingRestaurant = new Restaurant("", "", "", "", "", "");
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RESTAURANT_BY_NAME);) {
			preparedStatement.setString(1, restaurantName);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				restaurantName = rs.getString("restaurant_name");
				String restaurantLocation = rs.getString("restaurant_location");
				String openingTime = rs.getString("restaurant_open_time");
				String closingTime = rs.getString("restaurant_closing_time");
				String restaurantDescription = rs.getString("restaurant_description");
				String restaurantCuisine = rs.getString("cuisine_category");
				existingRestaurant = new Restaurant(restaurantName, restaurantLocation, openingTime, closingTime,
						restaurantDescription, restaurantCuisine);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Step 5: Set existingUser to request and serve up the userEdit form
		request.setAttribute("restaurant", existingRestaurant);
		request.getRequestDispatcher("/restaurantEdit.jsp").forward(request, response);
	}

	// method to update the restaurant table base on the form data
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// Step 1: Retrieve value from the request
		String oriRestaurantName = request.getParameter("oriRestaurantName");
		String restaurantName = request.getParameter("restaurantName");
		String restaurantLocation = request.getParameter("restaurantLocation");
		String openingTime = request.getParameter("openingTime");
		String closingTime = request.getParameter("closingTime");
		String restaurantDescription = request.getParameter("restaurantDescription");
		String restaurantCuisine = request.getParameter("restaurantCuisine");
		// Step 2: Attempt connection with database and execute update user SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_RESTAURANT_SQL);) {
			statement.setString(1, restaurantName);
			statement.setString(2, restaurantLocation);
			statement.setString(3, openingTime);
			statement.setString(4, closingTime);
			statement.setString(5, restaurantDescription);
			statement.setString(6, restaurantCuisine);
			statement.setString(7, oriRestaurantName);
			int i = statement.executeUpdate();
		}
		// Step 3: redirect back to UserServlet
		response.sendRedirect("http://localhost:8080/RestaurantReview/RestaurantServlet/dashboard");
	}

	// method to delete user
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// Step 1: Retrieve value from the request
		String restaurantName = request.getParameter("restaurantName");
		// Step 2: Attempt connection with database and execute delete user SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_RESTAURANT_SQL);) {
			statement.setString(1, restaurantName);
			int i = statement.executeUpdate();
		}
		// Step 3: redirect back to UserServlet dashboard
		response.sendRedirect("http://localhost:8080/RestaurantReview/RestaurantServlet/dashboard");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Step 4: Depending on the request servlet path, determine the function to
		// invoke using the follow switch statement.
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/RestaurantServlet/delete":
				deleteUser(request, response);
				break;
			case "/RestaurantServlet/edit":
				showEditForm(request, response);
				break;
			case "/RestaurantServlet/update":
				updateUser(request, response);
				break;
			case "/RestaurantServlet/dashboard":
				listOfRestaurants(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
