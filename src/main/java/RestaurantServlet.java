
import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//Import these libraries from java.io and java.sql
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class RestaurantServlet
 */
@WebServlet("/RestaurantServlet")
public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Initialize a PrintWriter object to return the html values via the response
		PrintWriter out = response.getWriter();

		// retrieve the six parameters from the request from the web form
		String restaurantName = request.getParameter("restaurantName");
		String restaurantLocation = request.getParameter("restaurantLocation");
		String openingTime = request.getParameter("restaurantOpening");
		String closingTime = request.getParameter("restaurantClosing");
		String restaurantDescription = request.getParameter("restaurantDescription");
		String restaurantCuisine = request.getParameter("restaurantCuisine");

		// attempt connection to database using JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant_review", "root",
					"password");

			// implement the sql query using prepared statement
			PreparedStatement ps = con.prepareStatement("insert into RESTAURANTDETAILS values(?,?,?,?,?,?)");

			// parse in the data retrieved from the web form request into the prepared
			// statement accordingly
			ps.setString(1, restaurantName);
			ps.setString(2, restaurantLocation);
			ps.setString(3, openingTime);
			ps.setString(4, closingTime);
			ps.setString(5, restaurantDescription);
			ps.setString(6, restaurantCuisine);

			// perform the query on the database using the prepared statement
			int i = ps.executeUpdate();

			// check if the query had been successfully execute
			if (i > 0) {
				PrintWriter writer = response.getWriter();
				writer.println("<h1>" + "Restaurant has been added successfully!" + "</h1>");
				writer.close();
			}

		}
		// catch and print out any exception
		catch (Exception exception) {
			System.out.println(exception);
			out.close();
		}
		doGet(request, response);
	}
}
