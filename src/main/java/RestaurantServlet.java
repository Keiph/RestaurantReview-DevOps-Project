
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
	private static final String SELECT_RESTAURANT_BY_NAME = "select * from RestaurantDetails where restaurantName =?";
	private static final String SELECT_ALL_RESTAURANTS = "select * from RestaurantDetails ";
	private static final String DELETE_RESTAURANT_SQL = "delete from RestaurantDetails where restaurantName = ?;";
	private static final String UPDATE_RESTAURANT_SQL = "update RestaurantDetails set restaurantName = ?,restaurantLocation= ?, openingTime =?,closingTime =?, restaurantDescription =?, restaurantCuisine =? where restaurantName = ?;";

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

	// Step 5: listUsers function to connect to the database and retrieve all users
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
				String restaurantName = rs.getString("restaurantName");
				String restaurantLocation = rs.getString("restaurantLocation");
				String openingTime = rs.getString("openingTime");
				String closingTime = rs.getString("closingTime");
				String restaurantDescription = rs.getString("restaurantDescription");
				String restaurantCuisine = rs.getString("restaurantCuisine");
				restaurants.add(new Restaurant(restaurantName, restaurantLocation, openingTime, closingTime, restaurantDescription, restaurantCuisine));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Step 5.4: Set the users list into the listUsers attribute to be pass to the userManagement.jsp
		request.setAttribute("listOfRestaurants", restaurants);
		request.getRequestDispatcher("/userManagement.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Step 4: Depending on the request servlet path, determine the function to
		// invoke using the follow switch statement.
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/insert":
				break;
			case "/delete":
				break;
			case "/edit":
				break;
			case "/update":
				break;
			default:
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
