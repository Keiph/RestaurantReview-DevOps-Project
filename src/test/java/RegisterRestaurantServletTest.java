import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class RegisterRestaurantServletTest {
	StringWriter stringWriter = new StringWriter();
	PrintWriter printWriter = new PrintWriter(stringWriter);

	RegisterRestaurantServlet registerRestaurant;
	List<Restaurant> restaurants = new ArrayList<>();

	private static String jdbcURL = "jdbc:mysql://localhost:3306/restaurant_review";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "password";

	@BeforeAll
	@RepeatedTest(3)
	static void testDatabaseConnection() throws Exception {
		assertEquals("jdbc:mysql://localhost:3306/restaurant_review", jdbcURL, "Unable to find Database URL");
		assertEquals("root", jdbcUsername, "Invalid username and password, Access Denied!");
		assertEquals("password", jdbcPassword, "Invalid username and password, Access Denied");

		Class.forName("com.mysql.jdbc.Driver");

		assertAll(() -> DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword));

		DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

	}

	@BeforeEach
	void setUp() throws Exception {
		restaurants.add(new Restaurant("Test Name", "Test Location", "Test Opening ", "Test Closing",
				"Test Description", "Test Cuisine"));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testDoPostHttpServletRequestHttpServletResponse() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		when(response.getWriter()).thenReturn(printWriter);
		when(request.getParameter("restaurantName")).thenReturn(restaurants.get(0).getRestaurantName());
		when(request.getParameter("restaurantLocation")).thenReturn(restaurants.get(0).getRestaurantLocation());
		when(request.getParameter("restaurantOpening")).thenReturn(restaurants.get(0).getOpeningTime());
		when(request.getParameter("restaurantClosing")).thenReturn(restaurants.get(0).getClosingTime());
		when(request.getParameter("restaurantDescription")).thenReturn(restaurants.get(0).getRestaurantDescription());
		when(request.getParameter("restaurantCuisine")).thenReturn(restaurants.get(0).getRestaurantCuisine());

		assertAll(() -> new RegisterRestaurantServlet().doPost(request, response));
		
		verify(request, atLeast(1)).getParameter("restaurantName");
		verify(request, atLeast(1)).getParameter("restaurantLocation");
		verify(request, atLeast(1)).getParameter("restaurantOpening");
		verify(request, atLeast(1)).getParameter("restaurantClosing");
		verify(request, atLeast(1)).getParameter("restaurantDescription");
		verify(request, atLeast(1)).getParameter("restaurantCuisine");
		assertEquals(restaurants.get(0).getRestaurantName(),request.getParameter("restaurantName"));
		assertEquals(restaurants.get(0).getRestaurantLocation(),request.getParameter("restaurantLocation"));
		assertEquals(restaurants.get(0).getOpeningTime(),request.getParameter("restaurantOpening"));
		assertEquals(restaurants.get(0).getClosingTime(),request.getParameter("restaurantClosing"));
		assertEquals(restaurants.get(0).getRestaurantDescription(),request.getParameter("restaurantDescription"));
		assertEquals(restaurants.get(0).getRestaurantCuisine(),request.getParameter("restaurantCuisine"));
		

	}


	@AfterAll
	static void testCloseConnection() throws Exception {
		Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		con.close();

		assertTrue(con.isClosed(), "Connection is not closed");
	}

}
